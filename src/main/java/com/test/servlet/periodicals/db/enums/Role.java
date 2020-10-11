package com.test.servlet.periodicals.db.enums;

import com.test.servlet.periodicals.db.entity.User;

public enum Role {
    ADMIN, READER;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId-1];
    }

    public String getName() {
        return name().toLowerCase();
    }

}

