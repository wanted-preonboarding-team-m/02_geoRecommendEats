package com.wanted.domain.restaurant.utils;


import java.io.IOException;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * csv가 처음 어플리케이션로드할때 applicationContext가 초기화 된 이후 발동 되는곳
 */
@Component
@RequiredArgsConstructor
public class CsvFactory implements ApplicationListener<ContextRefreshedEvent> {

  //csv reader기
  private final CsvReader csvReader;
  private final String path = "src/main/resources/sgg_lat_lon.csv";
  private static final Logger LOG
      = Logger.getLogger(String.valueOf(CsvFactory.class));

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    LOG.info("starting to create csv");
    try {
      csvReader.reader(path);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
