package fr.eni.ep3jasp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
@Service("loaderprinter")
public class ResourceLoaderAndPrinter {
  /**
   * Tratar el  recurso
   *
   * @param resource el recurso
   */
  public void tratarRecurso(Resource resource, String type) {
    log.info("============================");
    log.info("tipo:{}",type);
    try {
      InputStream is = resource.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(is));

      String linea;

      while ((linea = br.readLine()) != null) {
        log.info(linea);
      }

      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }}
