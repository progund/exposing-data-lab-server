package se.itu.systemet.storage;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import se.itu.systemet.domain.Product;

/**
 * <p>An implementation of ProuctLine which fakes some hard coded products.</p>
 */
public class FakeProductLine implements ProductLine {

  private List<Product> products;

  // Prevent instantiation from outside this package
  FakeProductLine() { }
  
  public List<Product> getProductsFilteredBy(Predicate<Product> predicate) {
    if (products == null) {
      createFakeProducts();
    }
    return products.stream().filter(predicate).collect(Collectors.toList());
  }
  
  public List<Product> getAllProducts() {
    if (products == null) {
      createFakeProducts();
    }
    return products;
  }

  private Product getFakeProduct(String name, String price,
                                 String alcohol,
                                 String volume,
                                 String nr,
                                 String productGroup,
                                 String type) {
    // Create a new Product.Builder here and call the builder
    // methods name() price(), alcohol() etc etc from the
    // parameters to this method, and return a Product
    // using the builder's build() method.
    // Remove the following line first! ;-)
    return null;    
  }
  private void createFakeProducts() {
    products = new ArrayList<>();
    // populate the products list like below, using
    // good values for
    // name, price, alcohol, volume, nr, productGroup, type
    //products.add(getFakeProduct("", "", "", "", "", "", "", "");
  }
  
}
