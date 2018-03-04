package com.khanabid20.opennms.util.methods;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

import com.khanabid20.opennms.util.jmxdatacollection.generated.Attrib;
import com.khanabid20.opennms.util.jmxdatacollection.generated.Jmx_collection;
import com.khanabid20.opennms.util.jmxdatacollection.generated.Jmx_datacollection_config;
import com.khanabid20.opennms.util.jmxdatacollection.generated.Mbean;
import com.khanabid20.opennms.util.jmxdatacollection.generated.Mbeans;
import com.khanabid20.opennms.util.jmxdatacollection.generated.Rrd;
import com.khanabid20.opennms.util.main.App;

/**
 * This class contains methods for generating jmx data file
 * 
 * @author abid.khan
 *
 */
public class JMXGen {
	
	/**
	 * This method generates jmx file by reading  generated config.csv file
	 * 
	 */
	public static void genJMX(){

		List<CSVRecord> servicesList = ParseCSVFile.readCsvIntoRecords(OpenNMS_Utility_Constants.GENERATED_CSV_CONFIG_FILE);
		
		for (CSVRecord service : servicesList) {
			
			
			if (service.getRecordNumber()!=1) {
				Jmx_datacollection_config jmxDataCollection = new Jmx_datacollection_config();
				
				Jmx_collection collection = genJMXCollection(service.get(0));				// generates JMXcollection object for each services
				jmxDataCollection.setJmx_collection(collection);
				
				jmxDataCollection.setRrdRepository("/opt/opennms/share/rrd/snmp/");
				
				// This method calls generate xml from java objects
				App.objectToXML(jmxDataCollection, OpenNMS_Utility_Constants.XML_OUTPUT_FOLDER + service.get(0) + "-datacollection.xml");
				
			}
			
		}
	}

	/**
	 * This method creates <i>Jmx-collection</i> object
	 * 
	 * @param serviceName Service name 
	 * @return Returns Jmx_collection object
	 */
	private static Jmx_collection genJMXCollection(String serviceName) {

		Jmx_collection collection = new Jmx_collection();

		String[] rra = new String[5];
		rra[0] = "RRA:AVERAGE:0.5:1:2016";
		rra[1] = "RRA:AVERAGE:0.5:12:1488";
		rra[2] = "RRA:AVERAGE:0.5:288:366";
		rra[3] = "RRA:MAX:0.5:288:366";
		rra[4] = "RRA:MIN:0.5:288:366";

		Rrd r = new Rrd();
		r.setRra(rra);
		r.setStep("300");
		
//		List<CSVRecord> mbeansInfo = CollectDGen.readCsvIntoRecords("./config/opennms-configuration/opennms-jvm.csv");
		
//		System.out.println(serviceName);
		
		// Reads particular service's mbean csv file
		List<CSVRecord> mbeansInfo = ParseCSVFile.readCsvIntoRecords(OpenNMS_Utility_Constants.GENERATED_CSV_FOLDER + serviceName+".csv");
		Mbeans mbeans = genMbeans(mbeansInfo);
		
//		System.out.println(mbeansInfo);
		
		collection.setName(serviceName);
		collection.setRrd(r);
		collection.setMbeans(mbeans);
		return collection;
	}

	/**
	 * This method generates mbeans object 
	 * 
	 * @param mbeansInfo Mbean information of a particular service 
	 * @return Returns <i>Mbeans</i> object
	 */
	private static Mbeans genMbeans(List<CSVRecord> mbeansInfo) {

		Mbean[] mbeansArray = new Mbean[mbeansInfo.size()];

		for (int i = 0; i < 1; i++) {
			mbeansArray[i] = genSingleMbean(mbeansInfo.get(1));
		}

		Mbeans mbeans = new Mbeans();
		mbeans.setMbean(mbeansArray);
	
		return mbeans;
	}

	/**
	 * This method creates single mbean
	 * 
	 * @param mbeanRecord Single mbean record
	 * @return Returns the <i>Mbean</i> object
	 */
	private static Mbean genSingleMbean(CSVRecord mbeanRecord) {

		Attrib[] attribs = getAttribArray(mbeanRecord.get(2));
		
//		System.out.println(mbeanRecord.get(2));
		
		Mbean mbean = new Mbean();
		mbean.setAttrib(attribs);
		mbean.setName(mbeanRecord.get(0));
		mbean.setObjectname(mbeanRecord.get(1));
		return mbean;

	}
	
	/**
	 * This method generates attribute array for a particular mbean
	 * 
	 * @param attribsCSV Comma(,) separated attributes 
	 * @return Returns attributes array
	 */
	private static Attrib[] getAttribArray(String attribsCSV) {
		String[] attribsArray = attribsCSV.split(",");
		
		Attrib[] attribs=new Attrib[attribsArray.length];
		for(int i=0; i<attribsArray.length; i++){
			Attrib attrib = new Attrib();
			attrib.setName(attribsArray[i]);
			attrib.setAlias(attribsArray[i]);
			attrib.setType("gauge");
						
			attribs[i] = attrib;
		}
		return attribs;
	}
}
