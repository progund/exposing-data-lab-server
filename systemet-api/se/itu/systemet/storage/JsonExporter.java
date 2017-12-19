package se.itu.systemet.storage;

import se.itu.systemet.domain.Product;

/**
 * <p>This class is used when we want to export the state of a Product
 * for use in a different context or format.</p>
 * <p>A JsonExporter object can, produce a String with the Json
 * representing a Product.
 * </p>
 * 
 * <p>Typical usage:
 * <pre>
 *     Product p1 = new Product.Builder()
 *     .name("Renat")
 *     .price(209.00)
 *     .alcohol(37.50)
 *     .volume(700)
 *     .nr(101)
 *     .productGroup("Okryddad sprit")
 *     .build();
 *
 *     JsonExporter jsonExp = new JsonExporter();
 *     p1.export(jsonExp);
 *     System.out.println(jsonExp.toJsonString());
 * </pre>
 * The above will print (on a single line):    // note: should we say productGroup or product_group?
 * <pre>
 *{
 *       "name": "Renat",
 *       "price": 209.00,
 *       "alcohol": 37.50,
 *       "volume": 700,
 *       "nr": 101,
 *       "product_group": "Okryddad sprit" 
 *}
 * </pre>
 * </p>
 */
public class JsonExporter implements Product.Exporter {
  private String name;
  private double price;
  private double alcohol;
  private int volume;
  private int nr;
  private String productGroup;
  private String type;

  @Override
  public void addName(String name) {
    this.name = name;
  }

  @Override
  public void addPrice(double price) {
    this.price = price;
  }

  @Override
  public void addAlcohol(double alcohol) {
    this.alcohol = alcohol;
  }

  @Override
  public void addVolume(int volume) {
    this.volume = volume;
  }

  @Override
  public void addNr(int nr) {
    this.nr = nr;
  }

  @Override
  public void addProductGroup(String productGroup) {
    this.productGroup = productGroup;
  }

  @Override
  public void addType(String type) {
    this.type = type;
  }

  /**
   * Returns the exported Product as a plain String 
   * for debuggin purposes
   * @return The exported Product as a plain String
   */
  public String toString() {
    return new StringBuilder(name)
      .append(" ")
      .append(price)
      .append(" ")
      .append(alcohol)
      .append(" ")
      .append(volume)
      .append(" ")
      .append(nr)
      .append(" ")
      .append(productGroup)
      .append(" ")
      .append(type)
      .toString();
  }
  private String escape(String string) {
    if (string == null) {
      return "null"; // This is questionable...
    }
    return string
      .replace("\"", "\\\"");
  }
  
  /**
   * Returns a String with a Json representation of an object.
   * @return A String with a Json object representation of a product
   */
  public String toJsonString() {
    StringBuilder json = new StringBuilder("  {\n")
      .append("    \"name\": \"")
      .append(escape(name)).append("\",\n")
      .append("    \"price\": ")
      .append(String.format("%.2f", price)).append(",\n")
      .append("    \"volume\": ")
      .append(String.format("%d", volume)).append(",\n")
      .append("    \"alcohol\": ")
      .append(String.format("%.2f", alcohol)).append(",\n")
      .append("    \"nr\": ")
      .append(String.format("%d", nr)).append(",\n")
      .append("    \"product_group\": \"")
      .append(escape(productGroup)).append("\"\n")
      .append("  }");
    return json.toString();
      
  }
  /*
 *{
 *       "name": "Renat",
 *       "price": 209.00,
 *       "alcohol": 37.50,
 *       "volume": 700,
 *       "nr": 101,
 *       "product_group": "Okryddad sprit" 
 *}

   */
}
