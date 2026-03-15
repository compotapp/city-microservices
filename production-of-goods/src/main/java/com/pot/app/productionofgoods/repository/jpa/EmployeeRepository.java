package com.pot.app.productionofgoods.repository.jpa;

import com.pot.app.productionofgoods.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends NumberedRepository<Employee, Long> {

    //              AND (status = 'FREE' OR (status = 'RELAX' AND last_modified_date <= (NOW() AT TIME ZONE 'UTC') - INTERVAL '1 minute'))
    @Query(value = """
            SELECT *
            FROM employees
            WHERE type = :type
              AND (status = 'FREE' OR (status = 'RELAX' AND last_modified_date <= (NOW() AT TIME ZONE 'UTC') - INTERVAL '20 second'))
            ORDER BY created_date
            LIMIT 1 FOR UPDATE SKIP LOCKED;""", nativeQuery = true)
    Optional<Employee> findFirstFreeByType(String type);
}
