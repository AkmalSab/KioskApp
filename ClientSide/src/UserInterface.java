import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.*;    
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class UserInterface {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   
   Connection connection;//Creating object of Connection class
   Statement statement;//Creating object of Statement class

   public UserInterface(){
      prepareGUI();
   }
   public static void main(String[] args){
	   UserInterface swingControlDemo = new UserInterface();      
      swingControlDemo.showTableDemo();
   }
   private void prepareGUI(){
      mainFrame = new JFrame("Java Swing Examples");
      mainFrame.setSize(500,400);
      mainFrame.setLayout(new GridLayout(3, 1));

      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("", JLabel.CENTER);        
      statusLabel = new JLabel("",JLabel.CENTER);    
      statusLabel.setSize(350,100);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }
   private void showTableDemo(){
//      headerLabel.setText("Control in action: JTable"); 
//
//      String[] columnNames = {"Name", "Salary"};
//      Object[][] data = {
//         {"Wan Ismat", "5000"},
//         {"Wan Azmy", "7000"}
//      };
//      JTable table = new JTable(data, columnNames);
//      
//      table.setCellSelectionEnabled(true);
//      ListSelectionModel select= table.getSelectionModel();
//      select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//      select.addListSelectionListener(new ListSelectionListener() {  
//          public void valueChanged(ListSelectionEvent e) {
//            String Data = null;
//            int[] row = table.getSelectedRows();
//            int[] columns = table.getSelectedColumns();
//            for (int i = 0; i < row.length; i++) {
//              for (int j = 0; j < columns.length; j++) {
//                Data = (String) table.getValueAt(row[i], columns[j]);
//              } 
//            }
//            //System.out.println("Table element selected is: " + Data);
//            headerLabel.setText("Table element selected is: " + Data);
//          }
//        });
//      
//      JScrollPane scrollPane = new JScrollPane(table);
//      scrollPane.setSize(300, 300);
//      table.setFillsViewportHeight(true);
//      controlPanel.add(scrollPane);
//      mainFrame.setVisible(true);
      
      try {
          
          connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");//Crating connection with database
          statement = connection.createStatement();//crating statement object
          String query = "select * from users";//Storing MySQL query in A string variable
          ResultSet resultSet = statement.executeQuery(query);//executing query and storing result in ResultSet
          String[] columnNames = {"ID", "Name", "Salary"};
          
          DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

          while (resultSet.next()) {
          
       	   	//Retrieving details from the database and storing it in the String variables
        	  int id = resultSet.getInt("id");
              String name = resultSet.getString("Name");
              String salary = resultSet.getString("Salary");
              String aaa = String.valueOf(id);
              JTable table;
              
              Object[] populateData = {aaa, name, salary};
              tableModel.addRow(populateData);  
              
              table = new JTable(tableModel);
              table.setCellSelectionEnabled(true);
              ListSelectionModel select= table.getSelectionModel();
              select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
              select.addListSelectionListener(new ListSelectionListener() {  
                  public void valueChanged(ListSelectionEvent e) {
                    String Data = null;
                    int[] row = table.getSelectedRows();
                    int[] columns = table.getSelectedColumns();
                    for (int i = 0; i < row.length; i++) {
                      for (int j = 0; j < columns.length; j++) {
                        Data = (String) table.getValueAt(row[i], columns[j]);
                      } 
                    }
                    //System.out.println("Table element selected is: " + Data);
                    headerLabel.setText("Table element selected is: " + Data);
                  }
                });
              
              JScrollPane scrollPane = new JScrollPane(table);
              scrollPane.setSize(300, 300);
              table.setFillsViewportHeight(true);
              controlPanel.add(scrollPane);
              mainFrame.setVisible(true);
              
              System.out.println(resultSet.getInt("id") + ", "
                      + resultSet.getString("Name") + ", "
                      + resultSet.getString("Salary"));
          }
          


      } catch (SQLException throwables) {
          throwables.printStackTrace();
      }
      
   }
}