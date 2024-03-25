package com.ecom.productservice.converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class DateConverter {
  public static LocalDateTime getLocalDateTimeFromDate(Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
  }
}
