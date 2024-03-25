package com.ecom.productservice.util;

public class QueryUtil {
  public static final String GET_ACTIVE_PRODUCTS_QUERY =
      "SELECT * FROM PRODUCTS WHERE start_date_of_sale <= CURDATE() AND end_date_of_sale > CURDATE()";
}
