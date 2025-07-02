package com.lagunabreezelodge.db.dao.impl;

import com.lagunabreezelodge.db.DBConnection;
import com.lagunabreezelodge.db.dao.AdminUserDAO;
import com.lagunabreezelodge.model.AdminUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminUserDAOImpl implements AdminUserDAO {

    @Override
    public boolean insert(AdminUser admin) {
        String query = "INSERT INTO admin_users(name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getEmail());
            stmt.setString(3, admin.getPassword());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Admin insert failed: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public AdminUser findByEmail(String email) {
        String query = "SELECT * FROM admin_users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new AdminUser(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("created_at")
                );
            }

        } catch (SQLException e) {
            System.err.println("❌ Failed to fetch admin by email: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public AdminUser getAdminByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM admin_users WHERE email = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new AdminUser(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("created_at")
                );
            }

        } catch (SQLException e) {
            System.err.println("❌ Failed to login admin: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<AdminUser> findAll() {
        List<AdminUser> list = new ArrayList<>();
        String query = "SELECT * FROM admin_users ORDER BY created_at DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                list.add(new AdminUser(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("created_at")
                ));
            }

        } catch (SQLException e) {
            System.err.println("❌ Failed to fetch all admins: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
