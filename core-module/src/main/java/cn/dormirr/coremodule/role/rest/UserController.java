package cn.dormirr.coremodule.role.rest;

import cn.dormirr.coremodule.role.service.RoleService;
import cn.dormirr.coremodule.role.service.UserService;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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
            ExcelUtil.readBySax(inputStream, 0, rowHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 返回成功信息
        Map<String, Object> status = new HashMap<>(1) {{
            put("status", 200);
            put("添加成功数量", count[0]);
            put("添加失败数量", count[1]);
        }};
        if (count[1] > 0) {
            status.put("失败账号", list);
        }

        return ResponseEntity.ok(status);
    }
}
