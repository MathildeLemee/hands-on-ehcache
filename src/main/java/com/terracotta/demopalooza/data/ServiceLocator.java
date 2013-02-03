package com.terracotta.demopalooza.data;

import com.terracotta.demopalooza.service.NgramEx1Service;
import com.terracotta.demopalooza.service.NgramEx2Service;
import com.terracotta.demopalooza.service.NgramEx3Service;
import com.terracotta.demopalooza.service.NgramService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceLocator {

  private static Map<String, NgramService> services = new HashMap<String, NgramService>();

/*
  static {
    Map<String, NgramService> initMap = new ConcurrentHashMap<String, NgramService>();
    services.put("ex1", new NgramEx1Service());
    services.put("ex2", new NgramEx2Service());
    services.put("ex3", new NgramEx3Service());
    services = Collections.unmodifiableMap(initMap);
  }
*/

  public static NgramService getService(String name) throws Exception {
    services.put("ex1", new NgramEx1Service());
    services.put("ex2", new NgramEx2Service());
    services.put("ex3", new NgramEx3Service());
    return services.get(name);
  }

}
