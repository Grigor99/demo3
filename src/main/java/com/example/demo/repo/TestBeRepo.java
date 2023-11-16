package com.example.demo.repo;

import com.example.demo.model.TestBE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestBeRepo extends JpaRepository<TestBE,Long> {
}
