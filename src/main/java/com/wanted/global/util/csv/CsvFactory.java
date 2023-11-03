package com.wanted.global.util.csv;


import com.wanted.global.error.BusinessException;
import com.wanted.global.error.ErrorCode;
import java.io.IOException;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * csv가 처음 어플리케이션로드할때 applicationContext가 초기화 된 이후 발동 되는곳
 */
@Component
@RequiredArgsConstructor
public class CsvFactory {

  // 대한민국 시별 위도 경도 csv파일 경로
  @Value("${csv.sgg_lat_lon}")
  private String SSG_LAT_LON_PATH;

  //csv reader기
  private final CsvReader csvReader;
  private static final Logger LOG
      = Logger.getLogger(String.valueOf(CsvFactory.class));

  @EventListener(ApplicationReadyEvent.class)
  public void onApplicationEvent() {
    LOG.info("starting to create csv");
    try {
      csvReader.reader(SSG_LAT_LON_PATH);
    } catch (IOException e) {
      throw new BusinessException(e, "csv err", ErrorCode.CSV_PARSER_ERROR);
    }
  }
}
