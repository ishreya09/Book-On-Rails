package com.bookonrails.ooad.Interface;

import java.util.List;

import com.bookonrails.ooad.Model.Admin;

public interface AdminManagement {
    public Admin authenticate(Admin a);
    public void saveAdmin(Admin a);
    public void deleteAdmin(Long id);
    public Admin getAdmin(Long id);
    public List<Admin> findAll();
    public Admin findByUsername(String username);

    
}
