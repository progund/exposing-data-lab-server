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

  private static final ProductLine SQL_PRODUCT_LINE = 
    new SQLBasedProductLine();
  
  private static final ProductLine FAKE_PRODUCT_LINE = 
    new FakeProductLine();
  
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
    //System.out.println("ProductLine: " + System.getProperty("ProductLine"));
    if ("DB".equals(System.getProperty("ProductLine"))) {
      return SQL_PRODUCT_LINE;
    } else {
      return FAKE_PRODUCT_LINE; // A product line with hard-coded products
    }
    //return new XMLBasedProductLine();
  }
}
