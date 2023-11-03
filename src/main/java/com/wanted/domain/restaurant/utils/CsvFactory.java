package com.wanted.domain.restaurant.utils;


import com.wanted.global.config.error.BusinessException;
import com.wanted.global.config.error.ErrorCode;
import java.io.IOException;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * csv가 처음 어플리케이션로드할때 applicationContext가 초기화 된 이후 발동 되는곳
 */
@Component
@RequiredArgsConstructor
public class CsvFactory {

  //csv reader기
  private final CsvReader csvReader;
  private final String PATH = "src/main/resources/sgg_lat_lon.csv";
  private static final Logger LOG
      = Logger.getLogger(String.valueOf(CsvFactory.class));

  @EventListener(ApplicationReadyEvent.class)
  public void onApplicationEvent() {
    LOG.info("starting to create csv");
    try {
      csvReader.reader(PATH);
    } catch (IOException e) {
      throw new BusinessException(e, "csv err", ErrorCode.CSV_PARSER_ERROR);
    }
  }
}
