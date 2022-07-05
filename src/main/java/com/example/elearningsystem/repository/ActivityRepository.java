package com.example.elearningsystem.repository;

import com.example.elearningsystem.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
