package com.mstone.reffy.govid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CsvFileGovIdService implements GovIdService {
  @Value("classpath:data/govids.csv")
  private Resource csvFile;

  @Override
  public boolean isValidGovId(String govId) {
    try (var reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()))) {
      String line;
      do {
        line = reader.readLine();
        if (line.equals(govId)) {
          return true;
        }
      } while (line != null);
    } catch (IOException ex) {
      log.error("error validating gov id", ex);
      return false;
    }
    return false;
  }
}