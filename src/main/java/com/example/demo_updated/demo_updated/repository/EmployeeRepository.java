package com.example.demo_updated.demo_updated.repository;

import com.example.demo_updated.demo_updated.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Employee,Long> {
    Employee findByUsernameAndPassword(String userName, String password);
}
