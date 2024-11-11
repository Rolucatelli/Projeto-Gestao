package com.example.bluevelvetmusicstore.service.product;

import com.example.bluevelvetmusicstore.model.vo.ProductDashboardVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

  /**
   * Retrieves a paginated list of all products in the system.
   *
   * <p>This method fetches products from the system based on the provided pagination and sorting
   * criteria, and returns them as a list of {@link ProductDashboardVO} objects. Each {@link
   * ProductDashboardVO} contains the necessary details to display product information on a
   * dashboard.
   *
   * <p>The products are retrieved in the order defined by the {@link Pageable} parameter, which can
   * include sorting by various fields and pagination settings like page number and page size.
   *
   * @param pageable The {@link Pageable} object containing pagination and sorting information.
   * @return A Page of {@link ProductDashboardVO} representing the products available in the system,
   *     paginated according to the provided {@link Pageable}.
   */
  Page<ProductDashboardVO> retrieveAllProducts(Pageable pageable);
}
