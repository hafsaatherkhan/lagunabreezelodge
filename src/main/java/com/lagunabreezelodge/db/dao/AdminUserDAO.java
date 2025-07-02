package com.lagunabreezelodge.db.dao;

import com.lagunabreezelodge.model.AdminUser;

import java.util.List;

public interface AdminUserDAO {
    boolean insert(AdminUser admin);
    AdminUser findByEmail(String email);
    List<AdminUser> findAll();

    AdminUser getAdminByEmailAndPassword(String email, String password);

}
