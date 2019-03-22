package com.yuyixi.aboutspring.repository;

import com.yuyixi.aboutspring.entity.Orders;
import com.yuyixi.aboutspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderRepository extends CrudRepository<Orders,Long>,
        JpaRepository<Orders, Long>, JpaSpecificationExecutor<User> {
}
