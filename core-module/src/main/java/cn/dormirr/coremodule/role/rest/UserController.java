package cn.dormirr.coremodule.role.rest;

import cn.dormirr.commonmodule.utils.FileUtils;
import cn.dormirr.commonmodule.utils.PageUtils;
import cn.dormirr.commonmodule.utils.SecurityUtils;
import cn.dormirr.coremodule.role.domain.vo.*;
import cn.dormirr.coremodule.role.repository.UserRepository;
import cn.dormirr.coremodule.role.service.UserService;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import cn.dormirr.coremodule.role.service.mapper.UserMapper;
import cn.dormirr.coremodule.security.security.TokenProvider;
import cn.dormirr.coremodule.security.service.OnlineUserService;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserController(UserService userService, TokenProvider tokenProvider, OnlineUserService onlineUserService, UserRepository userRepository, UserMapper userMapper) {
        this.userService = userService;
        this.onlineUserService = onlineUserService;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    @PreAuthorize("hasAnyAuthority('老师')")
    public ResponseEntity<Object> register(@RequestParam("file") MultipartFile file) {
        final int[] count = {0, 0};
        List<String> list = new ArrayList<>();
        // 创建行处理器
        RowHandler rowHandler = (sheetIndex, rowIndex, rowlist) -> {
            if (rowIndex >= 1 && rowlist.get(0) != null && rowlist.get(1) != null && rowlist.get(3) != null) {
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
    @PreAuthorize("hasAnyAuthority('老师','学生','队长')")
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

        String userPortrait = "https://localhost:8080/avatar/" + user.getUserNumber() + fileType;
        user.setUserPortrait(userPortrait);
        userService.saveUserPortrait(user);

        // 返回成功信息
        Map<String, Object> status = new HashMap<>(1) {{
            put("status", 201);
        }};

        return ResponseEntity.ok(status);
    }

    @PutMapping("/updateNameAndEmail")
    @PreAuthorize("hasAnyAuthority('老师','学生','队长')")
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
    @PreAuthorize("hasAnyAuthority('老师','学生','队长')")
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

    @GetMapping("/find-user-fighting-capacity")
    @PreAuthorize("hasAnyAuthority('老师','学生')")
    public ResponseEntity<Object> findUserFightingCapacity() {
        List<UserDto> userDto = userService.findUserFightingCapacity();

        Map<String, Object> result = new HashMap<>(4) {{
            put("code", 200);
            put("success", true);
            put("userDto", userDto);
            put("message", "查询成功！");
        }};

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/forget")
    public ResponseEntity<Object> forget(@RequestBody UserNumber userNumber) {
        UserDto userDto = userService.findByUserNumber(userNumber.getUserNumber());
        if (userDto == null || "".equals(userDto.getUserEmail())) {
            Map<String, Object> status = new HashMap<>(1) {{
                put("status", 400);
            }};

            return ResponseEntity.ok(status);
        }

        userService.email(userDto);

        Map<String, Object> status = new HashMap<>(1) {{
            put("status", 201);
            put("userNumber", userDto.getUserNumber());
        }};

        return ResponseEntity.ok(status);
    }

    @GetMapping(value = "/forget-code", produces = "text/plain;charset=UTF-8")
    public String forget(String uuid) {
        if (uuid == null) {
            return "重置失败，链接错误或邮件已过期！";
        }

        userService.forget(uuid);

        return "重置成功！";
    }

    @PostMapping("/remove")
    @PreAuthorize("hasAnyAuthority('老师')")
    public ResponseEntity<Object> remove(@RequestParam("file") MultipartFile file) {
        final int[] count = {0, 0};
        List<String> list = new ArrayList<>();
        // 创建行处理器
        RowHandler rowHandler = (sheetIndex, rowIndex, rowlist) -> {
            if (rowIndex >= 1 && rowlist.get(0) != null) {
                userService.removeUser((String) rowlist.get(0));
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

    @GetMapping("/find-user")
    @PreAuthorize("hasAnyAuthority('老师')")
    public ResponseEntity<Object> findUser(FindUser findUser) {
        UserDto userDto = new UserDto();
        if (findUser.getUserName() != null) {
            userDto.setUserName(findUser.getUserName());
        }
        if (findUser.getUserNumber() != null) {
            userDto.setUserNumber(findUser.getUserNumber());
        }
        int pageSize = findUser.getPageSize();
        int current = findUser.getCurrent();
        String sorter = null;
        if (!"".equals(findUser.getSorter())) {
            sorter = findUser.getSorter();
        }

        PageUtils<UserDto> data = userService.findUser(userDto, pageSize, current, sorter);

        // 返回成功信息
        Map<String, Object> status = new HashMap<>(5) {{
            put("status", 200);
            put("data", data.getContent());
            put("total", data.getTotalElements());
            put("current", data.getTotalPages());
            put("pageSize", pageSize);
        }};
        return new ResponseEntity<>(status, HttpStatus.OK);
    }


    @PutMapping("/forget-user")
    @PreAuthorize("hasAnyAuthority('老师')")
    public ResponseEntity<Object> forgetUser(@RequestBody ForgetUser userNumber) {
        UserDto userDto = userService.findByUserNumber(userNumber.getUserNumber());
        userDto.setUserPassword("$2a$10$vE9HsVXW3aWQM1bbeojfB.aaFHS19Ts7C/GWjgCE3Gs8Escp/3/om");
        userService.forgetUser(userDto);

        // 返回成功信息
        Map<String, Object> status = new HashMap<>(1) {{
            put("status", 201);
            put("success", true);
        }};

        return ResponseEntity.ok(status);
    }
}
