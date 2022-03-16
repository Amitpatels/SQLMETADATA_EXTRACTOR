package com.demo.export;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.demo.db.MetadataEntity;

public class ExportDBMetadata {

	public static void exportListIntoExcel(List<MetadataEntity> columndataList, String exportFilePath,
			String outPutTypeSelected) {

		try {
			XSSFWorkbook workbook = new XSSFWorkbook();

			if (outPutTypeSelected.equals("seprate")) {
				HashSet<String> tableName = getTablesName(columndataList);

				for (String table : tableName) {
					XSSFSheet sheet = workbook.createSheet(table);// creating a blank sheet
					writeHeaderLineWithSeprateSheet(sheet);
					int rownum = 1;

					for (MetadataEntity entity : columndataList) {
						if (entity.getTableName().equalsIgnoreCase(table)) {
							Row row = sheet.createRow(rownum++);
							createListWithSeprateSheet(entity, row, rownum);
						}
					}
				}

			} else {

				XSSFSheet sheet = workbook.createSheet("sheet1");// creating a blank sheet
				writeHeaderLine(sheet);
				int rownum = 1;

				for (MetadataEntity entity : columndataList) {
					Row row = sheet.createRow(rownum++);
					createList(entity, row);

				}

			}

			// FileOutputStream out = new FileOutputStream(new
			// File("C:\\Users\\F5422167\\AMIT STUFF\\AMIT SOFT\\MetadataEntity.xlsx")); //
			// file name with path
			FileOutputStream out = new FileOutputStream(new File(exportFilePath)); // file name with path
			workbook.write(out);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void createList(MetadataEntity entity, Row row) // creating cells for each row
	{
		Cell cell = row.createCell(0);
		cell.setCellValue(entity.getTableName());

		cell = row.createCell(1);
		cell.setCellValue(entity.getColumnName());

		cell = row.createCell(2);
		cell.setCellValue(entity.getColumnType());

		cell = row.createCell(3);
		cell.setCellValue(entity.getColumnSize());

		cell = row.createCell(4);
		cell.setCellValue(entity.getColumnDescription());

		cell = row.createCell(5);
		cell.setCellValue(entity.getColumnIsNullable());

		cell = row.createCell(6);
		cell.setCellValue(entity.getColumnIsAutoIncrement());

		cell = row.createCell(7);
		cell.setCellValue(entity.getColumnIsMandatory());

		cell = row.createCell(8);
		cell.setCellValue(entity.getTablePrimaryKey());

		cell = row.createCell(9);
		cell.setCellValue(entity.getTableForeignKey());

	}

	private static void createListWithSeprateSheet(MetadataEntity entity, Row row, int rowno) // creating cells for each
																								// row
	{
		Cell cell = row.createCell(0);
		cell.setCellValue(--rowno);

		cell = row.createCell(1);
		cell.setCellValue(entity.getColumnName());

		cell = row.createCell(2);
		cell.setCellValue(entity.getColumnType());

		cell = row.createCell(3);
		cell.setCellValue(entity.getColumnSize());

		cell = row.createCell(4);
		cell.setCellValue("");

		cell = row.createCell(5);
		cell.setCellValue("");

		cell = row.createCell(6);
		cell.setCellValue("");

		cell = row.createCell(7);
		cell.setCellValue("");

	}

	public static void writeHeaderLineWithSeprateSheet(XSSFSheet sheet) {

		Row headerRow = sheet.createRow(0);

		Cell headerCell = headerRow.createCell(0);
		headerCell.setCellValue("POS");

		headerCell = headerRow.createCell(1);
		headerCell.setCellValue("COLUMN_NAME");

		headerCell = headerRow.createCell(2);
		headerCell.setCellValue("INPUT_DATATYPE");

		headerCell = headerRow.createCell(3);
		headerCell.setCellValue("INPUT_LENGTH");

		headerCell = headerRow.createCell(4);
		headerCell.setCellValue("DATE_FORMAT");

		headerCell = headerRow.createCell(5);
		headerCell.setCellValue("OUTPUT_DATATYPE");

		headerCell = headerRow.createCell(6);
		headerCell.setCellValue("OUTPUT_LENGTH");

		headerCell = headerRow.createCell(7);
		headerCell.setCellValue("DML");

	}

	public static void writeHeaderLine(XSSFSheet sheet) {

		Row headerRow = sheet.createRow(0);

		Cell headerCell = headerRow.createCell(0);
		headerCell.setCellValue("Table Name");

		headerCell = headerRow.createCell(1);
		headerCell.setCellValue("Column Name");

		headerCell = headerRow.createCell(2);
		headerCell.setCellValue("Column Type");

		headerCell = headerRow.createCell(3);
		headerCell.setCellValue("Column Size");

		headerCell = headerRow.createCell(4);
		headerCell.setCellValue("Column Description");

		headerCell = headerRow.createCell(5);
		headerCell.setCellValue("Column IsNullable");

		headerCell = headerRow.createCell(6);
		headerCell.setCellValue("Column IsAutoIncrement");

		headerCell = headerRow.createCell(7);
		headerCell.setCellValue("Column IsMandatory");

		headerCell = headerRow.createCell(8);
		headerCell.setCellValue("PrimaryKey in Table");

		headerCell = headerRow.createCell(9);
		headerCell.setCellValue("ForeignKey in Table");

	}

	public static HashSet<String> getTablesName(List<MetadataEntity> columndataList) {
		HashSet<String> tableName = new HashSet<>();
		if (columndataList != null) {
			for (MetadataEntity m : columndataList) {
				tableName.add(m.getTableName());
			}
		}
		return tableName;
	}

}
