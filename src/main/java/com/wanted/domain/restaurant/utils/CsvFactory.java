package com.wanted.domain.restaurant.utils;


import java.io.IOException;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CsvFactory implements ApplicationListener<ContextRefreshedEvent> {

  private final CsvReader csvReader;
  private final String path = "src/main/resources/sgg_lat_lon.csv";
  private static final Logger LOG
      = Logger.getLogger(String.valueOf(CsvFactory.class));

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    LOG.info("Increment counter");
    try {
      csvReader.reader(path);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
