package com.myapp.spring.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import com.myapp.spring.model.Product;
@RestResource(path="products")
public interface ProductRepository extends JpaRepository<Product ,Integer> {

}
