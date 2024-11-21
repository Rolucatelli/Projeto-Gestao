package com.example.bluevelvetmusicstore.repository;

import com.example.bluevelvetmusicstore.model.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  /**
   * Searches for products based on the provided search term. This search is case-insensitive and
   * looks for matches in the following fields: product name, short description, brand, and category
   * name.
   *
   * <p>This method supports pagination via the {@link Pageable} parameter, allowing for efficient
   * retrieval of large datasets.
   *
   * @param search the search term to look for in the product's name, short description, brand, and
   *     category name. It may be {@code null} or empty, in which case no filtering will be applied.
   * @param pageable the pagination information, including page number, size, and sort order.
   * @return a {@link Page} of {@link Product} objects that match the search criteria. The page will
   *     contain the products matching the search term, if any, along with the total number of
   *     matching products for pagination.
   */
  @Query(
      "SELECT p FROM Product p WHERE "
          + "LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) OR "
          + "LOWER(p.shortDescription) LIKE LOWER(CONCAT('%', :search, '%')) OR "
          + "LOWER(p.brand) LIKE LOWER(CONCAT('%', :search, '%')) OR "
          + "LOWER(p.category.name) LIKE LOWER(CONCAT('%', :search, '%'))")
  Page<Product> searchProducts(@Param("search") String search, Pageable pageable);
}
