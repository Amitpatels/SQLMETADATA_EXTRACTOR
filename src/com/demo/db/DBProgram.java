package com.demo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;

import com.demo.export.ExportDBMetadata;

public class DBProgram {

	/*
	 * private static final String jdbcDriver =
	 * "com.microsoft.sqlserver.jdbc.SQLServerDriver"; private static final String
	 * jdbcURL =
	 * "jdbc:sqlserver://172.28.15.166;databaseName=sc_cause;user=FundabaBE;password=Fund@ba123;";
	 */

	private static final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	private static final String jdbcURL = "jdbc:mysql://localhost:3306/schooldb";
	private static final String user = "root";
	private static final String password = "good";
	private String dbschemaGlb ="";

	Connection databaseConnection = null;
	java.sql.DatabaseMetaData databaseMetaData = null;

	List<MetadataEntity> listTableMetaData = new ArrayList<>();
	List<MetadataEntity> columndataList = new ArrayList<>();

	public void getConnectDb() {

		System.out.println("Program started");
		try {
			Class.forName(jdbcDriver);
			System.out.println("JDBC driver loaded");

			// Connect to the database
			databaseConnection = DriverManager.getConnection(jdbcURL, user, password);

			databaseMetaData = databaseConnection.getMetaData();
			System.out.println("Connected to the database");

		} catch (Exception err) {
			System.err.println("Error loading JDBC driver");
			err.printStackTrace(System.err);
			System.exit(0);
		}

	}
	
	
	public void getConnectDbMySql(String jdbcDriver,String jdbcUrl,String userName,String pass,String dbSchema) {

		System.out.println("Program started");
		dbschemaGlb = dbSchema;
		try {
			Class.forName(jdbcDriver);
			System.out.println("JDBC driver loaded");

			// Connect to the database
			databaseConnection = DriverManager.getConnection(jdbcUrl, userName, pass);

			databaseMetaData = databaseConnection.getMetaData();
			System.out.println("Connected to the MySQL database");

		} catch (Exception err) {
			System.err.println("Error loading MySQL JDBC driver");
			err.printStackTrace(System.err);
			System.exit(0);
		}

	}
	
	
	public void getConnectDBSqlServer(String jdbcDriver,String jdbcUrl,String userName,String pass,String dbSchema) {
		System.out.println("Program started");
		dbschemaGlb = dbSchema;
		String connectionURl = ""+jdbcUrl+";"
								+"user="+userName+";"
								+"password="+pass+";"
				
				;
		
		System.out.println("Connection URL SQL : "+connectionURl);
		try {
			Class.forName(jdbcDriver);
			System.out.println("JDBC driver loaded");

			// Connect to the database
			databaseConnection = DriverManager.getConnection(connectionURl);

			databaseMetaData = databaseConnection.getMetaData();
			System.out.println("Connected to the database SQL server...");

		} catch (Exception err) {
			System.err.println("Error loading SQL server JDBC driver");
			err.printStackTrace(System.err);
			System.exit(0);
		}
	}
	
	
	
	public void getTableName(String schema,String exportFilePath) throws SQLException {

		databaseMetaData = databaseConnection.getMetaData();
		ResultSet resultSet = databaseMetaData.getTables(schema, null, null, new String[] { "TABLE" });

		System.out.println("----------------------------------");
		String tableName = "";
		
		while (resultSet.next()) {
			// Print
			MetadataEntity data = new MetadataEntity();
			System.out.println("\n===================" + resultSet.getString("TABLE_NAME") + "===================\n");
			//System.out.println("columnName\t\tType\t\tsize\t\tdecimaldigit\t\tisNullable\t\tis_autoIncrment");
			tableName = resultSet.getString("TABLE_NAME");
			
			listTableMetaData.addAll(getTableColumnDetails(tableName));
			System.out.println("\n");
			//listTableMetaData.add(data);
		}
		
		//System.out.println(columndataList);
		ExportDBMetadata.exportListIntoExcel(columndataList,exportFilePath);
		System.out.println("Data exported to excel file ...");
	}

	public List<MetadataEntity> getTableColumnDetails(String tableName) throws SQLException {

		ResultSet columns = databaseMetaData.getColumns(null, null, tableName, null);
		
		
		String pk = getPrimaryKey(tableName);
		String fk = getForeignKey(tableName);
		while (columns.next()) {
			MetadataEntity data = new MetadataEntity();
			String columnName = columns.getString("COLUMN_NAME");
			String datatype = columns.getString("DATA_TYPE");
			String columnsize = columns.getString("COLUMN_SIZE");
			String decimaldigits = columns.getString("DECIMAL_DIGITS");
			String isNullable = columns.getString("IS_NULLABLE");
			String is_autoIncrment = columns.getString("IS_AUTOINCREMENT");
			String desc = columns.getString("REMARKS");
			
			JDBCType columnType = JDBCType.valueOf(Integer.parseInt(datatype));
			// Printing results
			System.out.println(columnName + "\t\t" + columnType+ "\t\t" + columnsize
					+ "\t\t" + decimaldigits + "\t\t" + isNullable + "\t\t\t\t" + is_autoIncrment + "\t\t" + desc);
			
			//identifyDateFormat(columnType);
			data.setTableName(tableName);
			data.setColumnName(columnName);
			data.setColumnSize(columnsize);
			data.setColumnType(""+columnType);
			data.setColumnIsNullable(isNullable);
			data.setColumnIsAutoIncrement(is_autoIncrment);
			data.setTablePrimaryKey(pk);
			data.setTableForeignKey(fk);
			
			
			
			
			if(isNullable.equalsIgnoreCase("NO")) {
				
				data.setColumnIsMandatory("Mandatory");
			}else {
				data.setColumnIsMandatory("Optional");
			}
			columndataList.add(data);
		}
		
		
		return columndataList;
	}

	public String getPrimaryKey(String tableName) throws SQLException {
		ResultSet PK = databaseMetaData.getPrimaryKeys(null, null, tableName);
		String pkName = "";
		System.out.println("------------PRIMARY KEYS-------------");
		while (PK.next()) {
			pkName = PK.getString("COLUMN_NAME");
			System.out.println(PK.getString("COLUMN_NAME") + "===" + PK.getString("PK_NAME"));
		}
		return pkName;
	}

	public String getForeignKey(String tableName) throws SQLException {

		ResultSet FK = databaseMetaData.getImportedKeys(null, null, tableName);
		String fkName = "";
		System.out.println("------------FOREIGN KEYS-------------");
		while (FK.next()) {
			fkName = "[ PK Table : "+FK.getString("PKTABLE_NAME")+", PK Column : "+FK.getString("PKCOLUMN_NAME")+"] , [ FK Table : "+FK.getString("FKTABLE_NAME")+" , FK Column :"+ FK.getString("FKCOLUMN_NAME")+" ]";
			/*
			 * System.out.println(FK.getString("PKTABLE_NAME") + "---" +
			 * FK.getString("PKCOLUMN_NAME") + "===" + FK.getString("FKTABLE_NAME") + "---"
			 * + FK.getString("FKCOLUMN_NAME"));
			 */
		}
		
		return fkName;
	}
	
	public String identifyDateFormat(JDBCType columnType) {
	
		String dateFormat = "";
		
		
		
		
		/*
		 * DATE
		 *  TIME WITHOUT TIME ZONE 
		 *  TIME WITH TIME ZONE 
		 *  TIMESTAMP WITHOUT TIME ZONE
		 *  TIMESTAMP WITH TIME ZONE
		 */
		System.out.println("Switch call start");
		switch( columnType ) {  

	    case DATE :  
	        
	    	System.out.println("Date format : (DATE) : "+columnType);
	    	
	        break ;

	    case TIME_WITH_TIMEZONE :
	    	System.out.println("Date format : (TIME_WITH_TIMEZONE) : "+columnType);
	        break ;
	        
	    case TIMESTAMP_WITH_TIMEZONE :
	    	System.out.println("Date format : (TIMESTAMP_WITH_TIMEZONE) : "+columnType);
	        break ;    

	        
	    default :
	        
	        break ;

	}
		
		
		System.out.println("Switch call END ===============");
		return "";
		
	}
	
	
}
