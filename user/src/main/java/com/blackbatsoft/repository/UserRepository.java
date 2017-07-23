package com.blackbatsoft.repository;

import com.blackbatsoft.model.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by sunb on 17-6-28.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByLoginName(String loginName);
}
