package com.lagunabreezelodge.service;

import com.lagunabreezelodge.db.dao.UserDAO;
import com.lagunabreezelodge.db.dao.impl.UserDAOImpl;
import com.lagunabreezelodge.model.User;

public class AuthService {
    private final UserDAO userDAO;
    private static User loggedInUser = null;

    // Hardcoded admin account
    private static final String ADMIN_EMAIL = "admin@lagunabreeze.com";
    private static final String ADMIN_PASSWORD = "admin123";

    public AuthService() {
        this.userDAO = new UserDAOImpl();
    }

    // Login method
    public User login(String email, String password) {
        System.out.println("Trying login for email: " + email);

        // 1. Check hardcoded admin
        if (email.equalsIgnoreCase(ADMIN_EMAIL)) {
            if (password.equals(ADMIN_PASSWORD)) {
                User admin = new User(0, "Admin", ADMIN_EMAIL, ADMIN_PASSWORD, "admin");
                loggedInUser = admin;
                System.out.println("Hardcoded admin login successful.");
                return admin;
            } else {
                System.out.println("Incorrect hardcoded admin password.");
                return null;
            }
        }

        // 2. Normal database login
        User user = userDAO.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            System.out.println("User login successful. Role: " + user.getRole());
            return user;
        }

        System.out.println("Login failed.");
        return null;
    }

    // Register user
    public boolean register(User user) {
        if (userDAO.findByEmail(user.getEmail()) != null) {
            return false; // User already exists
        }

        // Ensure role is set if not already
        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("user"); // Default role
        }

        return userDAO.insert(user);
    }


    // Get current session
    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public static void logout() {
        loggedInUser = null;
    }

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static int getLoggedInUserId() {
        return loggedInUser != null ? loggedInUser.getId() : -1;
    }
}
