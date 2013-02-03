package com.terracotta.demopalooza.data;

import com.terracotta.demopalooza.service.NGramEhcacheService;
import com.terracotta.demopalooza.service.NgramJdbcService;
import com.terracotta.demopalooza.service.NgramService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceLocator {

  private static Map<String, NgramService> services = new ConcurrentHashMap<String, NgramService>();

  public static NgramService getService(String name) throws Exception {
    if (!services.containsKey(name)) {
      services.put(name, createService(name));
      return services.get(name);
    } else {
      return services.get(name);
    }
  }

  private static NgramService createService(final String name) throws Exception {
    if ("ehcache".equals(name)) {
      NGramEhcacheService service = new NGramEhcacheService();
      service.setup();
      return service;
    } else if ("sql".equals(name)) {
      NgramJdbcService ngramJdbcService = new NgramJdbcService();
      ngramJdbcService.setup();
      return ngramJdbcService;
    }
    return null;
  }
}
