package com.pot.app.productionofgoods.repository.jpa;

import com.pot.app.productionofgoods.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends NumberedRepository<Product, Long>{

    Product findByName(String name);

    List<Product> findAllByNameIn(Collection<String> names);
}
