package com.mstone.reffy.govid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Scope("application")
public class CsvFileGovIdService implements GovIdService {
  @Value("classpath:data/govids.csv")
  private Resource csvFile;

  private Set<String> govIds;

  @Override
  public boolean isValidGovId(String govId) {
    return govIds.contains(govId);
  }

  @PostConstruct
  private void init() {
    log.info("running postconstruct");
    govIds = ConcurrentHashMap.newKeySet();
    try (var reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()))) {
      String line;
      do {
        line = reader.readLine();
        if (line != null) {
          govIds.add(line);
        }
      } while (line != null);
      log.info("govids after reading file: {}", govIds);
    } catch (IOException ex) {
      log.error("error validating gov id", ex);
    }
  }
}
