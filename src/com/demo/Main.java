package com.demo;

import java.sql.SQLException;


import com.demo.db.DBProgram;
import com.demo.swing.JFrameExampe;

public class Main {

	public static void main(String[] args) throws SQLException {
	
		System.out.println("Main class loaded... ");
		//DBProgram db = new DBProgram();
		//db.getConnectDb();
		//db.getTableName();
		
		JFrameExampe jframe = new JFrameExampe();
	}
}
