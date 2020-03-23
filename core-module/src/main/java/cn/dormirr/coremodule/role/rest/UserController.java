package cn.dormirr.coremodule.role.rest;

import cn.dormirr.commonmodule.utils.FileUtils;
import cn.dormirr.commonmodule.utils.SecurityUtils;
import cn.dormirr.coremodule.role.domain.vo.UserNameAndEmail;
import cn.dormirr.coremodule.role.domain.vo.UserPassword;
import cn.dormirr.coremodule.role.service.RoleService;
import cn.dormirr.coremodule.role.service.UserService;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import cn.dormirr.coremodule.security.security.TokenProvider;
import cn.dormirr.coremodule.security.service.OnlineUserService;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author ZhangTianCi
 */
@Slf4j
@RestController
@RequestMapping("/role")
public class UserController {
    private final TokenProvider tokenProvider;
    private final OnlineUserService onlineUserService;
    private final UserService userService;

    public UserController(UserService userService, TokenProvider tokenProvider, OnlineUserService onlineUserService) {
        this.userService = userService;
        this.onlineUserService = onlineUserService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    @PreAuthorize("hasAnyAuthority('老师')")
    public ResponseEntity<Object> register(@RequestParam("file") MultipartFile file) {
        final int[] count = {0, 0};
        List<String> list = new ArrayList<>();
        // 创建行处理器
        RowHandler rowHandler = (sheetIndex, rowIndex, rowlist) -> {
            System.out.println("[{" + sheetIndex + "}]" + " " + "[{" + rowIndex + "}]" + " " + "{" + rowlist + "}");
            if (rowIndex >= 1 && rowlist.get(0) != null && rowlist.get(1) != null && rowlist.get(3) != null) {
                System.out.println(rowlist.get(0) + " " + rowlist.get(1) + " " + rowlist.get(3));
                userService.saveUser(rowlist);
                count[0]++;
            } else if (rowlist.get(0) != null) {
                list.add((String) rowlist.get(0));
            }
        };

        try (InputStream inputStream = file.getInputStream()) {
            ExcelUtil.readBySax(inputStream, 1, rowHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 返回成功信息
        Map<String, Object> status = new HashMap<>(1) {{
            put("status", 201);
            put("suc", count[0]);
            put("err", count[1]);
        }};
        if (count[1] > 0) {
            status.put("失败账号", list);
        }

        return ResponseEntity.ok(status);
    }

    @PutMapping("/updatePortrait")
    @PreAuthorize("hasAnyAuthority('老师','学生')")
    public ResponseEntity<Object> updatePortrait(@RequestParam("file") MultipartFile file) {
        UserDto user = userService.findByUserNumber(SecurityUtils.getUsername());
        String fileType = ".";
        if ("image/jpeg".equals(file.getContentType())) {
            fileType += "jpg";
        } else if ("image/png".equals(file.getContentType())) {
            fileType += "png";
        } else {
            Map<String, Object> status = new HashMap<>(1) {{
                put("status", 422);
            }};
            return ResponseEntity.ok(status);
        }

        String path = "D:\\IdeaProjects\\galop-server\\avatar\\" + user.getUserNumber() + fileType;

        try (InputStream inputStream = file.getInputStream()) {
            FileUtils.writeFromStream(inputStream, path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String userPortrait = "http://localhost:8080/avatar/" + user.getUserNumber() + fileType;
        user.setUserPortrait(userPortrait);
        userService.saveUserPortrait(user);

        // 返回成功信息
        Map<String, Object> status = new HashMap<>(1) {{
            put("status", 201);
        }};

        return ResponseEntity.ok(status);
    }

    @PutMapping("/updateNameAndEmail")
    @PreAuthorize("hasAnyAuthority('老师','学生')")
    public ResponseEntity<Object> updateNameAndEmail(@RequestBody UserNameAndEmail userNameAndEmail) {
        UserDto user = userService.findByUserNumber(SecurityUtils.getUsername());
        user.setUserName(userNameAndEmail.getUserName());
        user.setUserEmail(userNameAndEmail.getUserEmail());
        userService.saveUserNameAndEmail(user);

        // 返回成功信息
        Map<String, Object> status = new HashMap<>(1) {{
            put("status", 201);
        }};

        return ResponseEntity.ok(status);
    }

    @PutMapping("/updatePassword")
    @PreAuthorize("hasAnyAuthority('老师','学生')")
    public ResponseEntity<Object> updatePassword(@RequestBody UserPassword userPassword, HttpServletRequest request) {
        UserDto user = userService.findByUserNumber(SecurityUtils.getUsername());
        String password = new BCryptPasswordEncoder().encode(userPassword.getUserPassword());
        user.setUserPassword(password);
        userService.saveUserPassword(user);
        // 退出登录
        onlineUserService.logout(tokenProvider.getToken(request));

        // 返回成功信息
        Map<String, Object> status = new HashMap<>(1) {{
            put("status", 201);
        }};

        return ResponseEntity.ok(status);
    }
}
