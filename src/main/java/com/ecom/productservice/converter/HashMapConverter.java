package com.ecom.productservice.converter;

import com.ecom.productservice.exception.ErrorKey;
import com.ecom.productservice.exception.LogicalException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@Converter(autoApply = true)
@Log4j2
public class HashMapConverter implements AttributeConverter<Map<Object, Object>, String> {

  @Override
  public String convertToDatabaseColumn(final Map<Object, Object> attribute) {
    final var mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(attribute);
    } catch (JsonProcessingException e) {
      log.error("Could not write the attribute map as string to the DB");
      throw new LogicalException(ErrorKey.SERVER_ERROR);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public Map<Object, Object> convertToEntityAttribute(String dbData) {
    final var mapper = new ObjectMapper();
    try {
      return mapper.readValue(dbData, HashMap.class);
    } catch (JsonProcessingException e) {
      log.error("Could not transform the string from DB to map object");
      throw new LogicalException(ErrorKey.SERVER_ERROR);
    }
  }
}
