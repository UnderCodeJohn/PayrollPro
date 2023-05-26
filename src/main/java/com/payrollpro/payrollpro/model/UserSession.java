package com.payrollpro.payrollpro.model;

public class UserSession {
    private static User loggedUser;

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User user) {
        loggedUser = user;
    }
}
