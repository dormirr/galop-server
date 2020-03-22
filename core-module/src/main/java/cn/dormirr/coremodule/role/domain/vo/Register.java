package cn.dormirr.coremodule.role.domain.vo;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * @author ZhangTianCi
 */
@Getter
@Setter
@ToString
public class Register implements Serializable {
    private String roleName;
    private MultipartFile[] uploadFiles;
}
