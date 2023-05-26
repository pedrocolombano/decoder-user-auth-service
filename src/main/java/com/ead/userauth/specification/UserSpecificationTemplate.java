package com.ead.userauth.specification;

import com.ead.userauth.entity.User;
import com.ead.userauth.entity.UserCourse;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.util.UUID;

public class UserSpecificationTemplate {

    @And({
            @Spec(path = "userType", spec = Equal.class),
            @Spec(path = "userStatus", spec = Equal.class),
            @Spec(path = "email", spec = Like.class),
            @Spec(path = "fullName", spec = LikeIgnoreCase.class)
    })
    public interface UserSpecification extends Specification<User> {
    }

    public static Specification<User> userByCourseId(final UUID courseId) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            Join<User, UserCourse> userCourse = root.join("userCourses");
            return criteriaBuilder.equal(userCourse.get("courseId"), courseId);
        };
    }

}
