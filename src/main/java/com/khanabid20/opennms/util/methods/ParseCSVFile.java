package com.khanabid20.opennms.util.methods;

import java.io.FileReader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * This class is use to parse CSV file
 * 
 * @author abid.khan
 *
 */
public class ParseCSVFile {

	/**
	 * This method reads csv file and returns list of csv record
	 * 
	 * @param inputFile Input csv file name
	 * @return Returns list of CSVRecord
	 */
	public static List<CSVRecord> readCsvIntoRecords(String inputFile) {
		List<CSVRecord> str = null;
		try {
			CSVParser csvParser = CSVFormat.EXCEL.parse(new FileReader(inputFile));
			str = csvParser.getRecords();
			csvParser.close();
		} catch (Exception e) {
			System.err.println("File Not Found");
		}
		return str;
	}
}
