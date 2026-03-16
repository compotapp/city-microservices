package com.pot.app.mayorofcity.repository;

import com.pot.app.mayorofcity.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = """
            SELECT *
            FROM employees
            WHERE type = :type AND status = 'FREE'
            ORDER BY created_date
            LIMIT 1 FOR UPDATE SKIP LOCKED;""", nativeQuery = true)
    Optional<Employee> findFirstFreeByType(String type);
}
