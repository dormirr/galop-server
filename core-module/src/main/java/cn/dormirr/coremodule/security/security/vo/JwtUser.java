package cn.dormirr.coremodule.security.security.vo;

import cn.dormirr.coremodule.role.domain.Role;
import cn.dormirr.coremodule.role.service.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author ZhangTianCi
 */
public class JwtUser implements UserDetails {

    private String userNumber;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPortrait;
    private Integer userFightingCapacity;
    private Role roleByRoleId;

    public JwtUser(UserDto userDto) {
        this.userNumber = userDto.getUserNumber();
        this.userName = userDto.getUserName();
        this.userPassword = userDto.getUserPassword();
        this.userEmail = userDto.getUserEmail();
        this.userPortrait = userDto.getUserPortrait();
        this.userFightingCapacity = userDto.getUserFightingCapacity();
        this.roleByRoleId = userDto.getRoleByRoleId();
    }

    public JwtUser() {
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

    public Integer getUserFightingCapacity() {
        return userFightingCapacity;
    }

    public void setUserFightingCapacity(Integer userFightingCapacity) {
        this.userFightingCapacity = userFightingCapacity;
    }

    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(getRoleByRoleId().getRoleName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return getUserPassword();
    }

    @Override
    public String getUsername() {
        return getUserNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
