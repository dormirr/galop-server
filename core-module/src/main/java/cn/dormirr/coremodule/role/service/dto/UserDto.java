package cn.dormirr.coremodule.role.service.dto;

import cn.dormirr.coremodule.role.domain.RoleEntity;

import java.util.Objects;

/**
 * @author ZhangTianCi
 */
public class UserDto {
    private Long id;
    private String userNumber;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPortrait;
    private Integer userFightingCapacity;
    private RoleEntity roleByRoleId;

    public UserDto() {
    }

    public UserDto(Long id, String userNumber, String userName, String userPassword, String userEmail, String userPortrait, Integer userFightingCapacity, RoleEntity roleByRoleId) {
        this.id = id;
        this.userNumber = userNumber;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userPortrait = userPortrait;
        this.userFightingCapacity = userFightingCapacity;
        this.roleByRoleId = roleByRoleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public RoleEntity getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(RoleEntity roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(getId(), userDto.getId()) &&
                Objects.equals(getUserNumber(), userDto.getUserNumber()) &&
                Objects.equals(getUserName(), userDto.getUserName()) &&
                Objects.equals(getUserPassword(), userDto.getUserPassword()) &&
                Objects.equals(getUserEmail(), userDto.getUserEmail()) &&
                Objects.equals(getUserPortrait(), userDto.getUserPortrait()) &&
                Objects.equals(getUserFightingCapacity(), userDto.getUserFightingCapacity()) &&
                Objects.equals(getRoleByRoleId(), userDto.getRoleByRoleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserNumber(), getUserName(), getUserPassword(), getUserEmail(), getUserPortrait(), getUserFightingCapacity(), getRoleByRoleId());
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userNumber='" + userNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPortrait='" + userPortrait + '\'' +
                ", userFightingCapacity=" + userFightingCapacity +
                ", roleByRoleId=" + roleByRoleId +
                '}';
    }
}
