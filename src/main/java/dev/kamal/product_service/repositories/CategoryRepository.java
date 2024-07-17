package dev.kamal.product_service.repositories;

import dev.kamal.product_service.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category save(Category category);
    Category findByTitle(String name);
    List<Category> findByTitleEndingWith(String ending);

}
