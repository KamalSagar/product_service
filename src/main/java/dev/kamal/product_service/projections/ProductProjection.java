package dev.kamal.product_service.projections;

import dev.kamal.product_service.models.Category;

import java.math.BigDecimal;

public interface ProductProjection {
    Long getId();
    String getTitle();
    String getDescription();
    BigDecimal getPrice();
    Category getCategory();
    String getImageUrl();
}
