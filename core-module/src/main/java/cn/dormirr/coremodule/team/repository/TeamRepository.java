package cn.dormirr.coremodule.team.repository;

import cn.dormirr.coremodule.role.domain.Role;
import cn.dormirr.coremodule.role.domain.User;
import cn.dormirr.coremodule.team.domain.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ZhangTianCi
 */
public interface TeamRepository extends JpaRepository<Team, Long>, JpaSpecificationExecutor<Team> {

    /**
     * 根据用户和角色查询队伍
     *
     * @param user     用户
     * @param pageable 分页排序
     * @return 队伍
     */
    Page<Team> findByUserByUserId(User user, Pageable pageable);

    /**
     * 根据角色查询队伍
     *
     * @param role     用户权限
     * @param pageable 分页排序
     * @return 队伍
     */
    Page<Team> findAllByRoleByRoleId(Role role, Pageable pageable);
}
