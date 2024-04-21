// AdminRepository.java

package com.bookonrails.ooad.Repository;

import com.bookonrails.ooad.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    public Admin findByUsername(String username);
    public Admin findByUsernameAndPassword(String username,String password);
}
