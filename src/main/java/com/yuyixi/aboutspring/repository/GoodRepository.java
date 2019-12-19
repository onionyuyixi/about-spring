package com.yuyixi.aboutspring.repository;

import com.yuyixi.aboutspring.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface GoodRepository extends CrudRepository<Good, Long>,
        JpaRepository<Good, Long>, JpaSpecificationExecutor<Good> {
}
