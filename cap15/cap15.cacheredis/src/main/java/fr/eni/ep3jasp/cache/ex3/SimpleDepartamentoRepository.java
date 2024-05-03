package fr.eni.ep3jasp.cache.ex3;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// tag::code[]
@Component
public class SimpleDepartamentoRepository {
  @CacheEvict(value = "depCode", allEntries = true)
  public void cacheEvict() {
  }
  @CachePut(value = "depCode", key = "#depCode")
  public void patch(String depCode, String valor) {
    simulationBase.put(depCode,valor);
  }
  @Cacheable("depCode")
  public Departamento getByCode(String regionCode) {
    try {
      Thread.sleep(2000L);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
    return new Departamento(regionCode,  simulationBase.get(regionCode));
  }

  private Map<String,String> simulationBase = new ConcurrentHashMap<String,String>();
  public SimpleDepartamentoRepository() {
    simulationBase.put("01","ANDALUCÍA");
    simulationBase.put("02","ASTURIAS");
    simulationBase.put("27","GALICIA");
    simulationBase.put("44","PAÍS-VASCO");
    simulationBase.put("51","CANTABRIA");
    //[...]
    simulationBase.put("03","CEUTA");
    simulationBase.put("974","MELILLA");
    simulationBase.put("975","CATALUÑA");
  }
}
// end::code[]