package com.pot.app.productionofgoods.repository.jpa;

import com.pot.app.productionofgoods.entity.NumberedEntity;
import com.pot.app.productionofgoods.exception.ProductNotFoundException;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface NumberedRepository<T extends NumberedEntity, ID> extends GeneralRepository<T, ID> {

    Optional<T> findByNumber(String number);

    default T findByNumberOrThrow(String number) {
        return findByNumber(number)
                .orElseThrow(() -> new ProductNotFoundException("Number: " + number + " not found"));
    }
}
