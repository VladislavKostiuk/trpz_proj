package org.example.security.context;

import org.example.security.identity.User;

public class SecurityContext {
    static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        SecurityContext.user = user;
    }
}
