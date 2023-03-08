package com.mustache.bbs1.repository;

import com.mustache.bbs1.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByUid(String uid);

    User findByUserName(String userName);
}
