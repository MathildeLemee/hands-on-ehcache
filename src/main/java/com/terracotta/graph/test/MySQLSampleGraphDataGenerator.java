package com.terracotta.graph.test;

import com.terracotta.demopalooza.graph.dataprovider.DataPoint;
import com.terracotta.demopalooza.graph.dataprovider.DataPointProvider;
import com.terracotta.demopalooza.graph.dataprovider.JMXServerManager;

public class MySQLSampleGraphDataGenerator {
	public static void main(String[] args) throws Exception {
		DataPointProvider mySQL = new DataPointProvider();
		JMXServerManager mySQLManager = new JMXServerManager(9898, mySQL);
		mySQLManager.startJMXServer();
		PerformanceDataProvider mysqlProvider = new DemoMySQLPerfDataProvider();
		while (true) {
			mySQL.updateDataPoint(DataPoint.TPS.name(),
					(double) mysqlProvider.getCurrentTPS());
			mySQL.updateDataPoint(DataPoint.QUERY_COUNT.name(),
					(double) mysqlProvider.getTotalQueryProcessedCount());
			// System.out.println(mysqlProvider.getCurrentTPS());
			// System.out.println(mysqlProvider.getTotalQueryProcessedCount());
			Thread.sleep(1000);

		}

	}
}
