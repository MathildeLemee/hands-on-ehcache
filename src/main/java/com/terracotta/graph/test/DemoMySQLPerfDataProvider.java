package com.terracotta.graph.test;

import java.util.Random;


public class DemoMySQLPerfDataProvider implements PerformanceDataProvider {
	private final Random r = new Random();
	private final long startTime; 
	private static int PERF_FACTOR=1;
	public DemoMySQLPerfDataProvider(){
		startTime = System.currentTimeMillis();
	}
	
	public int getCurrentTPS() {
		return PERF_FACTOR*(10000 + r.nextInt(1000));
	}

	public long getTotalQueryProcessedCount() {
		// TODO Auto-generated method stub
		return PERF_FACTOR* 11 * (System.currentTimeMillis() - startTime) ;
	}

}
