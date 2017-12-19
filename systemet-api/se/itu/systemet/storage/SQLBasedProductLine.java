package se.itu.systemet.storage;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.*;
  
import se.itu.systemet.domain.Product;

/**
 * <p>An implementation of ProuctLine which reads products from the database.
 * </p>
 */
public class SQLBasedProductLine implements ProductLine {

  private List<Product> products;

  // Prevent instantiation from outside this package
  SQLBasedProductLine() { }
  
  public List<Product> getProductsFilteredBy(Predicate<Product> predicate) {
    if (products == null) {
      readProductsFromDatabase();
    }
    return products.stream().filter(predicate).collect(Collectors.toList());
  }
  
  public List<Product> getAllProducts() {
    if (products == null) {
      readProductsFromDatabase();
    }
    return products;
  }

  private void readProductsFromDatabase() {
    /* You will get code to get you started here in Lab 3 */
    /* Write and use the FakeProductLine for now! */
  }  
}
