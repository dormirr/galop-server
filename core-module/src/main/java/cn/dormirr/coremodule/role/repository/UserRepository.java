package cn.dormirr.coremodule.role.repository;

import cn.dormirr.coremodule.role.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ZhangTianCi
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     * 根据学号查询用户
     *
     * @param userNumber 学号
     * @return 用户
     */
    User findByUserNumber(String userNumber);
}
