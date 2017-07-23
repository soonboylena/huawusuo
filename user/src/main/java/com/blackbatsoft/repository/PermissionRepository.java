package com.blackbatsoft.repository;

import com.blackbatsoft.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sunb on 2017/7/13.
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
