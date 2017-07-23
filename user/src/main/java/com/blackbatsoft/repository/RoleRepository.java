package com.blackbatsoft.repository;

import com.blackbatsoft.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by sunb on 2017/7/13.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
