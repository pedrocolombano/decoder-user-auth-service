package com.ead.userauth.repository;

import com.ead.userauth.dto.param.UserFiltersDTO;
import com.ead.userauth.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select user from User user "
    + "where (:#{#filters.email} is null OR user.email like %:#{#filters.email}%) "
    + "and (:#{#filters.userStatus} is null OR user.userStatus = :#{#filters.userStatus}) "
    + "and (:#{#filters.userType} is null OR user.userType = :#{#filters.userType})")
    Page<User> find(Pageable pageable, UserFiltersDTO filters);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
