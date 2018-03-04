package com.khanabid20.opennms.util.methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class contains method to convert excel workbook to CSV files for individual sheet of the given workbook
 * 
 * @author abid.khan
 *
 */
public class ParseExcelFile {

	static Logger log = Logger.getLogger(ParseExcelFile.class);
	
	private static int lastCellInRow;
	
	
	/**
	 * This method Reads excel sheet and generates CSV file and names them
	 * according to the name of the sheet.
	 * 
	 * @param inputFile Excel workbook with it's location
	 */
	public static void xlsxToCSV(File inputFile){
		// For storing data into CSV files
		StringBuffer data = new StringBuffer();
		List<String> sheetNames = new ArrayList<String>();
		File directory = new File(
				OpenNMS_Utility_Constants.OUTPUT_FOLDER + inputFile.getName().replace(OpenNMS_Utility_Constants.XLSX_FILE_EXTENSION, "") + "/");
		directory.mkdirs();
		int dataInNumericCell;
		try {
			FileOutputStream fileOutputStream;
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(inputFile));
			XSSFSheet sheet;
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				sheet = workbook.getSheetAt(i);

				if (sheet != null) {
					for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
						if (sheet.getRow(j) == null && sheet.getFirstRowNum() == sheet.getLastRowNum()) {
							workbook.removeSheetAt(i);
						}
					}
				}
			}

			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				sheet = workbook.getSheetAt(i);

				sheetNames.add(sheet.getSheetName().trim().replace(" ", ""));
				Cell cell;
				Row row;
				fileOutputStream = new FileOutputStream(
						directory + "/" + sheetNames.get(i) + OpenNMS_Utility_Constants.CSV_FILE_EXTENSION);
				for (int rowNumber = sheet.getFirstRowNum(); rowNumber <= sheet.getLastRowNum(); rowNumber++) {
					row = sheet.getRow(rowNumber);
					if (row == null) {
						// row which is never been used
						continue;
					} else {
						boolean containsData = false;
						for (Cell c : row) {
							if (c.getCellType() != Cell.CELL_TYPE_BLANK) {
								containsData = true;
								break;
							}
						}
						if (containsData) {
							if (rowNumber == sheet.getFirstRowNum()) {
								lastCellInRow = row.getLastCellNum();
							}
							for (int columnNumber = 0; columnNumber < lastCellInRow; columnNumber++) {
								cell = row.getCell(columnNumber);
								if (cell == null) {
									data.append("\"\"" + ",");
								} else {
									if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
										dataInNumericCell = (int) cell.getNumericCellValue();
										data.append("\"" + dataInNumericCell + "\"" + ",");
									} else
										data.append("\"" + cell + "\"" + ",");
								}
							}
						}
						data.deleteCharAt(data.length() - 1);
						data.append('\n');
					}
				}
				fileOutputStream.write(data.toString().getBytes());
				fileOutputStream.flush();
				data.delete(0, data.length());
				fileOutputStream.close();
//				log.info(sheetNames.get(i) + ".csv generated");
			}

			workbook.close();
		} catch (FileNotFoundException e) {
			log.error(inputFile + " not found ");
			
		} catch (IOException e) {
			log.info("Data is not written into the file " + inputFile + " " + e);
		}
	}

}
