package com.example.bluevelvetmusicstore.model.vo;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import lombok.Getter;

@Getter
public class ExceptionVO {

  private final Long timestamp;
  private final String message;

  public ExceptionVO(String message) {
    this.message = message;
    this.timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
  }
}
