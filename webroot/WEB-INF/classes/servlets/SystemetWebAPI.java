package servlets;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.Predicate;
import javax.servlet.*;
import javax.servlet.http.*;
import static java.nio.charset.StandardCharsets.UTF_8;

import se.itu.systemet.domain.Product;
import se.itu.systemet.storage.ProductLine;
import se.itu.systemet.storage.ProductLineFactory;


public class SystemetWebAPI extends HttpServlet {

  @Override
  public void init() throws ServletException {
    System.setProperty("ProductLine",
                       getServletContext().getInitParameter("ProductLine"));
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    
    request.setCharacterEncoding(UTF_8.name());
    response.setContentType("application/json;charset=" + UTF_8.name());
    response.setCharacterEncoding(UTF_8.name());
    PrintWriter out = response.getWriter();

    ParameterParser paramParser =
      new ParameterParser(request.getQueryString().split("&"));

    Predicate<Product> filter = paramParser.filter();

    ProductLine productLine = ProductLineFactory.getProductLine();

    List<Product> products = productLine.getProductsFilteredBy(filter);
    
    Formatter formatter = FormatterFactory.getFormatter();
    String json = formatter.format(products);

    StringBuilder sb = new StringBuilder(json);
    out.println(sb.toString());
    out.close();
  }
}
