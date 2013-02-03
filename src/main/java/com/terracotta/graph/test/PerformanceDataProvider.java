package com.terracotta.graph.test;

public interface PerformanceDataProvider {

	int getCurrentTPS();
	long getTotalQueryProcessedCount();
	
}
