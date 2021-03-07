package org.testtask.backtostock.model;

public class User {
    public enum UserStatus {
        PREMIUM,
        COMMON
    }

    private String userName;
    private UserStatus status;
    private Integer age;

    public User(String userName, UserStatus status, Integer age) {
        this.userName = userName;
        this.status = status;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{"
                + "userName='" + userName + '\''
                + ", status=" + status
                + ", age=" + age
                + '}';
    }
}
