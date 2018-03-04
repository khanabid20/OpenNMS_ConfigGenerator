package com.khanabid20.opennms.util.methods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.csv.CSVRecord;

import com.khanabid20.opennms.util.poller.generated.Critical_service;
import com.khanabid20.opennms.util.poller.generated.DownTime;
import com.khanabid20.opennms.util.poller.generated.Monitor;
import com.khanabid20.opennms.util.poller.generated.Node_outage;
import com.khanabid20.opennms.util.poller.generated.Parameter;
import com.khanabid20.opennms.util.poller.generated.Poller_Package;
import com.khanabid20.opennms.util.poller.generated.Poller_configuration;
import com.khanabid20.opennms.util.poller.generated.Rrd;
import com.khanabid20.opennms.util.poller.generated.Service;

/**
 * This class generates Poller object
 * 
 * @author abid.khan
 *
 */
public class PollerGen {
		
	static Poller_configuration poll;
	
	/**
	 * This method creates <i>Poller_configuration</i> object
	 *  
	 * @return Returns Poller object
	 */
	public static Poller_configuration genPoller() {
		
		
		poll = new Poller_configuration();
		
		Poller_Package pack = genPackage();
		

		Critical_service critService = new Critical_service();
		critService.setName("ICMP");
		
		Node_outage node_outage = new Node_outage();
//		node_outage.setPollAllIfNoCriticalServiceDefined("pollall");
		node_outage.setStatus("On");
		node_outage.setCritical_service(critService);

		poll.setServiceUnresponsiveEnabled("false");
		poll.setNode_outage(node_outage);
		poll.setPackage(new Poller_Package[] { pack });
		poll.setPathOutageEnabled("false");
		poll.setThreads("30");
		
		return poll;
	}

	/**
	 * This method creates <i>Poller_Package</i> object
	 * 
	 * @return Returns Poller Package object
	 */
	private static Poller_Package genPackage() {
	      
		Rrd rrd = new Rrd();
		rrd.setRra(new String[] { "RRA:AVERAGE:0.5:1:2016", "RRA:AVERAGE:0.5:12:1488", "RRA:AVERAGE:0.5:288:366",
				"RRA:MAX:0.5:288:366", "RRA:MIN:0.5:288:366"});
		rrd.setStep("300");
		
		DownTime downtime1 = new DownTime();
		downtime1.setInterval("30000");
		downtime1.setBegin("0");
		downtime1.setEnd("30000");
		DownTime downtime2 = new DownTime();
		downtime2.setInterval("300000");
		downtime2.setBegin("300000");
		downtime2.setEnd("43200000");
		DownTime downtime3 = new DownTime();
		downtime3.setInterval("600000");
		downtime3.setBegin("43200000");
		downtime3.setEnd("432000000");
		DownTime downtime4 = new DownTime();
		downtime4.setInterval("3600000");
		downtime4.setBegin("432000000");
		
		
		List<CSVRecord> servicesList = ParseCSVFile.readCsvIntoRecords(OpenNMS_Utility_Constants.GENERATED_CSV_CONFIG_FILE);
		Service[] services = new Service[servicesList.size()];
		Monitor[] monitors = new Monitor[servicesList.size()];
		for (int i = 1; i < servicesList.size(); i++) {
			services[i] = genService(servicesList.get(i), i, monitors);
		}

		
		Poller_Package pack = new Poller_Package();
		pack.setName("example1");
		pack.setFilter("IPADDR != '0.0.0.0'");
		pack.setDowntime(new DownTime[] { downtime1, downtime2, downtime3, downtime4 });
		pack.setRrd(rrd);
		pack.setService(services);
		return pack;
	}

	/**
	 * This method generates service element
	 * 
	 * @param serviceRecord Service information(i.e. service name & port)
	 * @param index
	 * @param monitors
	 * @return
	 */
	private static Service genService(CSVRecord serviceRecord, int index, Monitor[] monitors) {
		

		Parameter[] parameters = new Parameter[10];
		Map<String, String> paramKeyValueMap = new HashMap<String,String>();
		paramKeyValueMap.put("port", serviceRecord.get(1));
		paramKeyValueMap.put("retry", "2");
		paramKeyValueMap.put("timeout", "300");
		paramKeyValueMap.put("protocol", "rmi");
		paramKeyValueMap.put("urlPath", "/jmxrmi");
		paramKeyValueMap.put("rrd-base-name", "java");
		paramKeyValueMap.put("ds-name", serviceRecord.get(0).toLowerCase());
		paramKeyValueMap.put("friendly-name", serviceRecord.get(0).toLowerCase());
		paramKeyValueMap.put("thresholding-enabled", "true");
		
		int i=0;
		for(Map.Entry<String,String> entry: paramKeyValueMap.entrySet()){
			parameters[i] = genParameter(entry);
			i++;
		}
		

		
		Service service = new Service();
		service.setStatus("On");
		service.setName(serviceRecord.get(0));
		service.setInterval("100000");
		service.setUser_defined("user-defined");
		service.setParameter(parameters);
		

		monitors[index] = genMonitor(service);
		poll.setMonitor(monitors);
		
		return service;
	}

	/**
	 * This method generates parameter element
	 * 
	 * @param entry Entry of a parameter (key, value)
	 * @return Returns Parameter object
	 */
	private static Parameter genParameter(Entry<String, String> entry) {

		Parameter param = new Parameter();
		param.setKey(entry.getKey());
		param.setValue(entry.getValue());
		return param;
	}
	
	/**
	 * This method generates monitor element with attributes(service name & class name)
	 * 
	 * @param service Service object
	 * @return Returns monitor object
	 */
	private static Monitor genMonitor(Service service) {
		Monitor monitor = new Monitor();
		monitor.setService(service.getName());
		monitor.setClass_name("org.opennms.netmgt.poller.monitors.Jsr160Monitor"); // read

		return monitor;
	}

}
