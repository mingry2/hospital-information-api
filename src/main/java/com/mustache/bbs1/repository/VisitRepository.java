package com.mustache.bbs1.repository;

import com.mustache.bbs1.domain.entity.User;
import com.mustache.bbs1.domain.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByUserId(User user);
}
