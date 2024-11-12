package com.example.bluevelvetmusicstore.service.product;

import com.example.bluevelvetmusicstore.model.entities.Product;
import com.example.bluevelvetmusicstore.model.vo.ProductDashboardVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

  /**
   * Retrieves a paginated list of products based on the provided search term and pagination
   * details. If a search term is provided, it performs a search across multiple fields (name, short
   * description, brand, and category name) of the products. If no search term is provided, it
   * retrieves all products.
   *
   * <p>The search is case-insensitive and uses partial matching for the search term in the relevant
   * fields.
   *
   * <p>The method also maps each {@link Product} entity to a {@link ProductDashboardVO} for the
   * response.
   *
   * @param search a search term to filter products. It can be {@code null} or empty, in which case
   *     all products will be retrieved. The search term is case-insensitive and matched to the
   *     product's name, short description, brand, and category name.
   * @param pageable pagination details (page number, page size, and sorting options).
   * @return a {@link Page} of {@link ProductDashboardVO} objects, which includes a list of products
   *     matching the search criteria and pagination details such as total number of products. If no
   *     search term is provided, all products are returned in a paginated format.
   */
  Page<ProductDashboardVO> retrieveAllProducts(String search, Pageable pageable);
}
