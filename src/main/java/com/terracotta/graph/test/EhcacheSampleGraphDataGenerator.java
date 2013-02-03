package com.terracotta.graph.test;

import com.terracotta.demopalooza.graph.dataprovider.DataPoint;
import com.terracotta.demopalooza.graph.dataprovider.DataPointProvider;
import com.terracotta.demopalooza.graph.dataprovider.JMXServerManager;

public class EhcacheSampleGraphDataGenerator {
	public static void main(String[] args) throws Exception {
		DataPointProvider ehcache = new DataPointProvider();
		JMXServerManager ehcacheManager = new JMXServerManager(9999, ehcache);
		ehcacheManager.startJMXServer();
		PerformanceDataProvider ehcacheProvider = new DemoEhcachePerfDataProvider();
		while (true) {
			ehcache.updateDataPoint(DataPoint.TPS.name(),
					(double) ehcacheProvider.getCurrentTPS());
			ehcache.updateDataPoint(DataPoint.QUERY_COUNT.name(),
					(double) ehcacheProvider.getTotalQueryProcessedCount());
			// System.out.println(ehcacheProvider.getCurrentTPS());
			// System.out.println(ehcacheProvider.getTotalQueryProcessedCount());
			Thread.sleep(1000);

		}

	}
}
