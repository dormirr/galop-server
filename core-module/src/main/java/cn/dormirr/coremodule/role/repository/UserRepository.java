package cn.dormirr.coremodule.role.repository;

import cn.dormirr.coremodule.role.domain.RoleEntity;
import cn.dormirr.coremodule.role.domain.UserEntity;
import cn.dormirr.coremodule.role.service.dto.RoleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ZhangTianCi
 */
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    /**
     * 根据学号查询用户
     *
     * @param userNumber 学号
     * @return 用户
     */
    UserEntity findByUserNumber(String userNumber);
}
