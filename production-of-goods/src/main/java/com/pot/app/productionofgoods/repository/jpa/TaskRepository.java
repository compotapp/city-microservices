package com.pot.app.productionofgoods.repository.jpa;

import com.pot.app.productionofgoods.entity.Task;
import com.pot.app.productionofgoods.enums.TaskStatus;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static jakarta.persistence.LockModeType.PESSIMISTIC_WRITE;

@Repository
public interface TaskRepository extends NumberedRepository<Task, Long> {

    @Lock(PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "jakarta.persistence.lock.timeout", value = "-2")  // 👈 добавляет SKIP LOCKED
    })
    Optional<Task> findFirstByStatus(TaskStatus status);
}
