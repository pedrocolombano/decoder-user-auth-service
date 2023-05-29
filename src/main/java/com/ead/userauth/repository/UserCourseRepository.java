package com.ead.userauth.repository;

import com.ead.userauth.entity.User;
import com.ead.userauth.entity.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, UUID> {

    boolean existsByUserAndCourseId(User user, UUID courseId);

}
