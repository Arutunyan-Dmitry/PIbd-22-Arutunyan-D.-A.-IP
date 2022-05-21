package com.ulstu.SushiBar.user.models;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMIN,
    STOREKEEPER,
    CLIENT,
    CHEF;

    private static final String PREFIX = "ROLE_";

    @Override
    public String getAuthority() {
        return PREFIX + this.name();
    }

    public static final class AsString {
        public static final String ADMIN = PREFIX + "ADMIN";
        public static final String STOREKEEPER = PREFIX + "STOREKEEPER";
        public static final String CLIENT = PREFIX + "CLIENT";
        public static final String CHEF = PREFIX + "CHEF";
    }
}
