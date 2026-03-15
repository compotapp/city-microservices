package com.pot.app.productionofnumbers.repository;

import com.pot.app.productionofnumbers.entity.NumberSequence;
import com.pot.app.productionofnumbers.entity.NumberSequence.SequenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static jakarta.persistence.LockModeType.PESSIMISTIC_WRITE;
import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface NumberSequenceRepository extends JpaRepository<NumberSequence, SequenceType> {

    @Lock(PESSIMISTIC_WRITE)
    @Query("SELECT ns FROM NumberSequence ns WHERE ns.type = :type")
    Optional<NumberSequence> findByType(SequenceType type);
}
