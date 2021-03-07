package org.testtask.backtostock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    public enum UserStatus {
        PREMIUM,
        COMMON
    }

    private String userName;
    private UserStatus status;
    private Integer age;
}
