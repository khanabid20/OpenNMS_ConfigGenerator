package com.khanabid20.opennms.util.methods;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

import com.khanabid20.opennms.util.collectd.generated.CollectDPackage;
import com.khanabid20.opennms.util.collectd.generated.Collectd_configuration;
import com.khanabid20.opennms.util.collectd.generated.Collector;
import com.khanabid20.opennms.util.collectd.generated.Parameter;
import com.khanabid20.opennms.util.collectd.generated.Service;

/**
 * This class generates collectD file
 * 
 * @author abid.khan
 *
 */
public class CollectDGen {


	static Collectd_configuration collectDConfig;

	/**
	 * This method creates <i>Collectd_configuration</i> object
	 * 
	 * @return Returns <i>Collectd_configuration</i> object
	 */
	public static Collectd_configuration genCollectD() {
		
		collectDConfig = new Collectd_configuration();
		collectDConfig.setThreads("50");

		CollectDPackage pack = genPackage(collectDConfig);
		collectDConfig.setPackage(new CollectDPackage[] { pack });
		
		return collectDConfig;
	}

	/**
	 * This method generates <i>CollectDPackage</i> object
	 * 
	 * @param collConfig Takes <i>Collectd_configuration</i> object
	 * @return Returns <i>CollectDPackage</i> object
	 */
	private static CollectDPackage genPackage(Collectd_configuration collConfig) {

		
		List<CSVRecord> servicesList = ParseCSVFile.readCsvIntoRecords(OpenNMS_Utility_Constants.GENERATED_CSV_CONFIG_FILE);
		Service[] services = new Service[servicesList.size()];
		Collector[] collectors = new Collector[servicesList.size()];
//		System.out.println(servicesList.size());
		for (int i = 1; i < servicesList.size()-1; i++) {
			services[i] = genService(servicesList.get(i), i, collectors);
		}

		CollectDPackage pack = new CollectDPackage();
		pack.setName("example1");
		pack.setFilter("IPADDR != '0.0.0.0'");
		pack.setService(services);
		pack.setRemote("");

		return pack;
	}

	/**
	 * This method generates Service object and calls a method to generate a collector
	 *  
	 * @param serviceRecord Sevrice information as a csv record
	 * @param index Index for collector object for a service
	 * @param collectors Empty collector array is passed, so for each service a collector is created
	 * @return Returns Service object
	 */
	private static Service genService(CSVRecord serviceRecord, int index, Collector[] collectors) {

		Parameter p1 = new Parameter();
		p1.setKey("port");
		p1.setValue(serviceRecord.get(1)); // reading from csv

		Parameter p2 = new Parameter();
		p2.setKey("factory");
		p2.setValue("STANDARD");
		Parameter p3 = new Parameter();
		p3.setKey("retry");
		p3.setValue("2");
		Parameter p4 = new Parameter();
		p4.setKey("timeout");
		p4.setValue("300");
		Parameter p5 = new Parameter();
		p5.setKey("rrd-repository");
		p5.setValue("/opt/opennms/share/rrd/response");
		Parameter p6 = new Parameter();
		p6.setKey("ds-name");
		p6.setValue(serviceRecord.get(0).toLowerCase());	//Service name : reading from csv
		Parameter p7 = new Parameter();
		p7.setKey("friendly-name");
		p7.setValue(serviceRecord.get(0).toLowerCase());

		Service service = new Service();
		service.setName(serviceRecord.get(0)); // reading from csv
		service.setInterval("300000");
		service.setStatus("on");
		service.setUser_defined("false");
		service.setParameter(new Parameter[] { p1, p2, p3, p4, p5, p6, p7 });

		collectors[index] = genCollector(service);
		collectDConfig.setCollector(collectors);

//		System.out.println(serviceRecord.get(0) + " " + serviceRecord.get(1));
//		System.out.println(service);

		return service;
	}

	/**
	 * This method creates collector object
	 * 
	 * @param service Takes service object as an argument for which this collector should be created
	 * @return
	 */
	private static Collector genCollector(Service service) {
		Collector collector = new Collector();
		collector.setClass_name("org.opennms.netmgt.collectd.Jsr160Collector");
		collector.setService(service.getName());
		return collector;
	}
}
