package se.itu.systemet.storage;

import java.util.function.Predicate;
import java.util.List;

import se.itu.systemet.domain.Product;

/**
 * <p>Factory class for creating ProductLine objects.</p>
 * <p>Typical usage:</p>
 * <pre>
 * ProductLine productLine = ProductLineFactory.getProductLine();
 * List&lt;Product&gt; products = productLine.getAllProducts();
 * </pre>
 */
public class ProductLineFactory {

  /**
   * Prevent instantiation.
   */
  private ProductLineFactory() {
    
  }

  /**
   * Creates a ProductLine object.
   * @return A new ProductLine object
   */
  public static ProductLine getProductLine() {
    if ("DB".equals(System.getProperty("ProductLine"))) {
      return new SQLBasedProductLine();
    } else {
      return new FakeProductLine(); // A product line with hard-coded products
    }
  }
}
