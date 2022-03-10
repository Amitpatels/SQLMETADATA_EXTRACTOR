package com.demo.db;

public class MetadataEntity {
	
	private String tableName;
	private String columnName;
	private String columnType;
	private String columnSize;
	private String columnDescription;
	private String columnIsNullable;
	private String columnIsAutoIncrement;
	private String tablePrimaryKey;
	private String columnIsMandatory;
	private String tableForeignKey;
	
	
	
	public MetadataEntity() {
		super();
		
	}
	
	
	public MetadataEntity(String tableName, String columnName, String columnType, String columnSize,
			String columnDescription, String columnIsNullable, String columnIsAutoIncrement, String tablePrimaryKey,
			String columnIsMandatory, String tableForeignKey) {
		super();
		this.tableName = tableName;
		this.columnName = columnName;
		this.columnType = columnType;
		this.columnSize = columnSize;
		this.columnDescription = columnDescription;
		this.columnIsNullable = columnIsNullable;
		this.columnIsAutoIncrement = columnIsAutoIncrement;
		this.tablePrimaryKey = tablePrimaryKey;
		this.columnIsMandatory = columnIsMandatory;
		this.tableForeignKey = tableForeignKey;
	}


	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getColumnSize() {
		return columnSize;
	}
	public void setColumnSize(String columnSize) {
		this.columnSize = columnSize;
	}
	public String getColumnDescription() {
		return columnDescription;
	}
	public void setColumnDescription(String columnDescription) {
		this.columnDescription = columnDescription;
	}
	public String getColumnIsNullable() {
		return columnIsNullable;
	}
	public void setColumnIsNullable(String columnIsNullable) {
		this.columnIsNullable = columnIsNullable;
	}
	public String getColumnIsAutoIncrement() {
		return columnIsAutoIncrement;
	}
	public void setColumnIsAutoIncrement(String columnIsAutoIncrement) {
		this.columnIsAutoIncrement = columnIsAutoIncrement;
	}
	public String getTablePrimaryKey() {
		return tablePrimaryKey;
	}
	public void setTablePrimaryKey(String tablePrimaryKey) {
		this.tablePrimaryKey = tablePrimaryKey;
	}
	public String getColumnIsMandatory() {
		return columnIsMandatory;
	}
	public void setColumnIsMandatory(String columnIsMandatory) {
		this.columnIsMandatory = columnIsMandatory;
	}


	public String getTableForeignKey() {
		return tableForeignKey;
	}


	public void setTableForeignKey(String tableForeignKey) {
		this.tableForeignKey = tableForeignKey;
	}


	@Override
	public String toString() {
		return "MetadataEntity [tableName=" + tableName + ", columnName=" + columnName + ", columnType=" + columnType
				+ ", columnSize=" + columnSize + ", columnDescription=" + columnDescription + ", columnIsNullable="
				+ columnIsNullable + ", columnIsAutoIncrement=" + columnIsAutoIncrement + ", tablePrimaryKey="
				+ tablePrimaryKey + ", columnIsMandatory=" + columnIsMandatory + ", tableForeignKey=" + tableForeignKey
				+ "]";
	}
	
	
	
}
