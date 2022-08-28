package com.doublegrooverecords.vinyl;

import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
class TimeWrapper {
  public ZonedDateTime now() {
    return ZonedDateTime.now();
  }
}
