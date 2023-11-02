package com.wanted.domain.restaurant.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CsvReader {

  private BufferedReader br;


  public void reader(String path) throws IOException {
    BufferedReader br = Files.newBufferedReader(Paths.get(path));
    try (br) {
      // 첫 번째 줄 (헤더) 건너뛰기
      br.readLine();
      String line;
      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }
    } catch (
        IOException e) {
      e.printStackTrace();
    }
  }
}
