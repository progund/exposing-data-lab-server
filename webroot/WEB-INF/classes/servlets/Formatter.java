package servlets;

import java.util.List;
import se.itu.systemet.domain.Product;

public interface Formatter {
  public String format(List<Product> products);
}
