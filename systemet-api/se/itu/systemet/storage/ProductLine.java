package se.itu.systemet.storage;

import java.util.function.Predicate;
import java.util.List;

import se.itu.systemet.domain.Product;

/**
 * Represents a ProductLine of beverages. This interface provides methods
 * for accessing the products in the product line.
 */
public interface ProductLine {

  /**
   * <p>Returns a List&lt;Product&gt; with all products of the product line that
   * satisfies the predicate.</p>
   * <p>Typical usage:</p>
   * <pre>
   * ProductLine productLine = ProductLineFactory.getProductLine();
   * List<Product> products = productLine.getAllProducts();
   * List<Product> strongStuff = productLine.getProductsFilteredBy(p -> p.alcohol() > 70.0);
   * </pre>
   * <p>Which filters out all Products with an alcohol level over 70.0%</p>
   * @param predicate A Predicate with the criteria for the products to return
   * @return A List&lt;Product&gt; with all the Products that match the predicate
   */
  public List<Product> getProductsFilteredBy(Predicate<Product> predicate);

  /**
   * Returns a list of all the products in the product line.
   * @return A List&lt;Product&gt; with all the Products in the product line.
   */
  public List<Product> getAllProducts();

}
