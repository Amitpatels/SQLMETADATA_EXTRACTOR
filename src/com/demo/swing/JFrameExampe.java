package com.demo.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.demo.db.DBProgram;

public class JFrameExampe {
	
	public JFrameExampe() {
		intialize();
	}
	
	String  dbType = "";
	String outPutTypeSelected = "";
	
	public void intialize() {
		
			JFrame frame = new JFrame("DB Metadata Extractor APP( Amit Patel )");  
	        JPanel panel = new JPanel();  
	        panel.setLayout(new FlowLayout());  
	        
	        String dbList[]={"MySql","SqlServer"}; 
	        String outPutType [] = {"Combine Table","Seprate Table"};
	        
	        JTextArea txtarea=new JTextArea(15,40);  
	        //txtarea.setBounds(100,300, 200,200);  
	        
	        JComboBox cb=new JComboBox(dbList);   
	        cb.setBounds(50, 50,90,20);   
	        JComboBox cbOut=new JComboBox(outPutType);   
	        cb.setBounds(70, 50,90,20);
	        JLabel lblDBType = new JLabel("Select Type of DB : "); 
	        JLabel lblOutPutType = new JLabel("Select excel OutPutType : "); 
	        JLabel lblDriver = new JLabel("DB Driver : ");  
	        JLabel lbljdbcURL = new JLabel("DB URL : "); 
	        JLabel lblShcema = new JLabel("DB Schema : "); 
	        JLabel lblUsr = new JLabel("DB User : ");  
	        JLabel lblpss = new JLabel("DB Password : "); 
	        JLabel lblExportExcl = new JLabel("Export File Path : "); 
	        JLabel lblout = new JLabel(""); 
	        
	        JTextField txtDriver = new JTextField(40);
	        JTextField txtjdbcURL = new JTextField(40);
	        JTextField txtshcema= new JTextField(40);
	        JTextField txtUsr = new JTextField(40);
	        JTextField txtpss = new JTextField(40);
	        JTextField txtExportPath = new JTextField(40);
	        
	        JButton button = new JButton();  
	        button.setText("Execute");  
	        
	        panel.add(lblDBType);
	        panel.add(cb);
	        panel.add(lblOutPutType);
	        panel.add(cbOut);
	        panel.add(lblDriver);  
	        panel.add(txtDriver);
	        panel.add(lbljdbcURL);
	        panel.add(txtjdbcURL);
	        panel.add(lblShcema);
	        panel.add(txtshcema);  
	        panel.add(lblUsr);
	        panel.add(txtUsr);
	        panel.add(lblpss);
	        panel.add(txtpss);
	        panel.add(lblExportExcl);
	        panel.add(txtExportPath);
	        panel.add(button);
	        panel.add(lblout);
	        panel.add(txtarea);
	        frame.add(panel);  
	        frame.setSize(500, 700);  
	        frame.setLocationRelativeTo(null);  
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        frame.setVisible(true);  
	        
	        //text Area comment
	        txtarea.append("\nFor Example : Please enter your DB details as per below format");
	        
	        //SQL 
	        txtarea.append("\n\n SQL Server : ");
	        txtarea.append("\n DB Driver : com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        txtarea.append("\n DB URL : jdbc:sqlserver://172.28.15.166;databaseName=sc_cause");
	        txtarea.append("\n DB Schema : sc_cause");
	        txtarea.append("\n DB User : ****");
	        txtarea.append("\n DB Password : ****");
	        txtarea.append("\n Export File Path : C:\\\\Users\\\\F5422167\\\\AMIT STUFF\\\\AMIT SOFT\\\\MetadataEntity.xlsx");   
	      
	        txtarea.append("\n\n MYSQL Server : ");
	        txtarea.append("\n DB Driver : com.mysql.cj.jdbc.Driver");
	        txtarea.append("\n DB URL : jdbc:mysql://localhost:3306/schooldb");
	        txtarea.append("\n DB Schema : schooldb");
	        txtarea.append("\n DB User : ****");
	        txtarea.append("\n DB Password : ****");
	        txtarea.append("\n Export File Path : C:\\\\Users\\\\F5422167\\\\AMIT STUFF\\\\AMIT SOFT\\\\MetadataEntity.xlsx");
	       
	        
	        
	        
	        //action 
	        cbOut.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					
	                JComboBox comboBox = (JComboBox) event.getSource();

	                Object selected = comboBox.getSelectedItem();
	                if(selected.toString().equals("Combine Table")) {
	                	
	                	outPutTypeSelected = "combine";
	                	}
	                else if(selected.toString().equals("Seprate Table")) {
	                	
	                	outPutTypeSelected = "seprate";
	                }

	            }
					
				
			});
	        
	        cb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					
					JComboBox comboBox = (JComboBox) event.getSource();

	                Object selected = comboBox.getSelectedItem();
	                if(selected.toString().equals("MySql")) {
	                	txtDriver.setText("com.mysql.cj.jdbc.Driver");
	                	dbType = "MySql";
	                	}
	                else if(selected.toString().equals("SqlServer")) {
	                	txtDriver.setText("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	                	dbType = "SqlServer";
	                }
					
				}
			});
	        
	        button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					DBProgram db = new DBProgram();
					String jdbcDriver = trimLefRight(txtDriver.getText());
					String jdbcUrl= trimLefRight(txtjdbcURL.getText());
					String userName= trimLefRight(txtUsr.getText());
					String pass= trimLefRight(txtpss.getText());
					String dbSchema= trimLefRight(txtshcema.getText());
					String exportFilePath = trimLefRight(txtExportPath.getText());
					
					
					
					if(dbType.equalsIgnoreCase("MySql")) {
						
						//public void getConnectDbMySql(String jdbcDriver,String jdbcUrl,String userName,String pass,String dbSchema) {

						db.getConnectDbMySql(jdbcDriver, jdbcUrl, userName, pass, dbSchema);
						try {
							lblout.setText("Started...");
							db.getTableName(dbSchema,exportFilePath,outPutTypeSelected);
							
							lblout.setText("Metadata mining completed, Please check your exported file on given location");
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
					}else if(dbType.equalsIgnoreCase("SqlServer")) {
						db.getConnectDBSqlServer(jdbcDriver, jdbcUrl, userName, pass, dbSchema);
						try {
							String connectionURl = ""+"jdbcUrl"+";"
									+"user="+userName+";"
									+"password="+pass+";";
			
							System.out.println("Connect url for SQL server "+connectionURl);
							lblout.setText("Started...");
							db.getTableName(dbSchema,exportFilePath,outPutTypeSelected);
							lblout.setText("Metada mining completed, Please check your exported file on given location");
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
					}
					
					//db.getConnectDb();
					//db.getTableName();
					
					
				}
			});
	        
	        
	        
	        
	        
	}
	
	
	public String trimLefRight(String str) {	
		return rtrim(ltrim(str));
	}
	
	public  String ltrim(String str)
    {
        int i = 0;
        while (i < str.length() && Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        return str.substring(i);
    }
 
    public String rtrim(String str)
    {
        int i = str.length() - 1;
        while (i >= 0 && Character.isWhitespace(str.charAt(i))) {
            i--;
        }
        return str.substring(0, i + 1);
    }
	
	
	
}	
