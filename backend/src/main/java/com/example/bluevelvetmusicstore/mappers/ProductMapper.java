package com.example.bluevelvetmusicstore.mappers;

import com.example.bluevelvetmusicstore.model.entities.Category;
import com.example.bluevelvetmusicstore.model.entities.Image;
import com.example.bluevelvetmusicstore.model.entities.Product;
import com.example.bluevelvetmusicstore.model.vo.ProductDashboardVO;
import java.util.Objects;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper interface for converting a {@link Product} entity to a {@link ProductDashboardVO}.
 *
 * <p>This interface uses MapStruct to handle the conversion between the Product entity and the
 * ProductDashboardVO. The mapping includes custom logic for fields like the main image URL and the
 * category name.
 *
 * @see Product
 * @see ProductDashboardVO
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {

  /**
   * Converts a {@link Product} entity to a {@link ProductDashboardVO}.
   *
   * <p>This method maps the fields of the {@link Product} entity to the corresponding fields in the
   * {@link ProductDashboardVO}. It also applies custom logic to map the main image URL and category
   * name.
   *
   * @param entity The {@link Product} entity to be converted.
   * @return A {@link ProductDashboardVO} containing the mapped data.
   * @throws RuntimeException if no principal image is found for the product.
   */
  @Mapping(target = "imageUrl", expression = "java(getMainImageUrl(entity))")
  @Mapping(target = "category", source = "category", qualifiedByName = "categoryToCategoryName")
  @Mapping(target = "description", source = "shortDescription")
  ProductDashboardVO entityToDashVO(Product entity);

  /**
   * Helper method to retrieve the main image URL of a product.
   *
   * <p>This method searches for the image in the list of images associated with the product that is
   * marked as the principal image. If no principal image is found, a {@link RuntimeException} is
   * thrown.
   *
   * @param entity The {@link Product} entity from which the main image URL is to be extracted.
   * @return The URL of the principal image.
   * @throws RuntimeException if no principal image is found for the product.
   */
  default String getMainImageUrl(Product entity) {
    Optional<Image> mainImage =
        entity.getImages().stream().filter(Image::getIsPrincipal).findFirst();
    return mainImage
        .map(Image::getUrl)
        .orElseThrow(
            () ->
                new RuntimeException(
                    "No principal image found for product with ID: " + entity.getId()));
  }

  /**
   * Converts a {@link Category} to its name.
   *
   * <p>This method extracts the name of the category, which is used in the {@link
   * ProductDashboardVO}. If the category is {@code null}, it returns {@code null}.
   *
   * @param category The {@link Category} to be converted to its name.
   * @return The name of the category, or {@code null} if the category is {@code null}.
   */
  @Named("categoryToCategoryName")
  default String categoryToCategoryName(Category category) {
    return Objects.nonNull(category) ? category.getName() : null;
  }
}
