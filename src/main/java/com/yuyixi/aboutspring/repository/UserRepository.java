package com.yuyixi.aboutspring.repository;

import com.yuyixi.aboutspring.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends CrudRepository<User,Long>,
        JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
