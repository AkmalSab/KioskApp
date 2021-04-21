package clientinterface;
import kioskapp.order.Order;
import kioskapp.itemproduct.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;    
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;


public class UserInterface {
   private JFrame mainFrame;
   
   private JPanel orderModePanel, controlPanel, quantityPanel, creditCardPanel, listPanel;
   
   private JLabel headerLabel, orderModeLabel, itemQuantityLabel, creditCardLabel, quantityTotalLabel, quantityLabel, priceTotalLabel, priceLabel;
   
   private JComboBox quantitySelection;
   
   private JTextField creditCardField;
   
   private JOptionPane itemMessage;
   
   private JButton addButton, payButton;
   private JButton be;
   
   private JRadioButton eatInButton, takeAwayButton;
   private ButtonGroup group;
   
   private JTable table;   
   private JScrollPane scrollPane;   
   private DefaultTableModel tableModel;
   
   public String Data, Price;
   
   private DefaultListModel<String> list1;
   
   
   private JList<String> list;
   
   public int x, y = 0;
   
   public float z, a = 0;
   
   Order myOrder = new Order();
   ItemProduct itemprod = new ItemProduct();
      
	static String driver;
	static String dbName;
	static String connectionURL;
	static String username;
	static String password;
	
   
   Connection connection;//Creating object of Connection class
   Statement statement;//Creating object of Statement class

   public UserInterface()
   {      
		driver = "com.mysql.cj.jdbc.Driver";
		connectionURL ="jdbc:mysql://localhost:3306/";
		dbName = "kioskappdb_dev";
		username = "root";
		password = "";
		
		list1 = new DefaultListModel<>();		
		list = new JList<>(list1);
		list.setBounds(100,100,75,75);
   }
   
   public static void main(String[] args) throws ClassNotFoundException{
	   UserInterface UI = new UserInterface();      
	   UI.prepareGUI();
	   UI.showTableDemo();
   }
   
   @SuppressWarnings("unchecked")
private void prepareGUI(){
	   
	  //Frame 
      mainFrame = new JFrame("TCP Kiosk Application");
      mainFrame.setSize(600,600);
      mainFrame.setLayout(new BorderLayout());
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.setVisible(true);       
      
      //Label
      orderModeLabel = new JLabel("Order Mode: ");
      headerLabel = new JLabel("Selected Item: "); 
      itemQuantityLabel = new JLabel("Select Quantity: ");   
      creditCardLabel = new JLabel("Enter Credit Card Number: "); 
      quantityTotalLabel = new JLabel("Total Quantity: "); 
      quantityLabel = new JLabel(); 
      priceTotalLabel = new JLabel("Total Price: RM"); 
      priceLabel = new JLabel(); 
      
      //OptionPane
      itemMessage = new JOptionPane();
      
      //TextField
      creditCardField = new JTextField(4);
      
      //RadioButton
      eatInButton = new JRadioButton("Eat-In", true);
      eatInButton.setActionCommand("Eat-In");
      takeAwayButton = new JRadioButton("Take-Away");
      takeAwayButton.setActionCommand("Take-Away");
      
      //RadioButton Group
      group = new ButtonGroup();
      group.add(eatInButton);
      group.add(takeAwayButton);  
      
      //tableModel
      String[] columnNames = {"ItemProduct", "Name", "Price"};          
      tableModel = new DefaultTableModel(columnNames, 0);
      
      //table
      table = new JTable(tableModel);
      table.setCellSelectionEnabled(true);
      table.setFillsViewportHeight(true);
      
      //ComboBox
      String[] quantity = {"1","2","3","4"};
      quantitySelection = new JComboBox(quantity);
      quantitySelection.setSelectedIndex(0);
      
      //Button
      addButton = new JButton("Add to Cart");  
      addButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {	
			DecimalFormat df = new DecimalFormat("###.##");
			x = Integer.valueOf((String) quantitySelection.getSelectedItem());
			y = y + x;
			z = Float.valueOf(Price);
			a = a + (x * z);
			myOrder.setTotalAmount(a);
			float tot = myOrder.getTotalAmount();
			list1.addElement(Data + " \t" + String.valueOf(x) + " \t" + String.valueOf(df.format(x * z)));
			//System.out.println("Total Quantity" + String.valueOf(y));	
			quantityLabel.setText(String.valueOf(y));
			//priceLabel.setText(String.valueOf(df.format(a)));
			priceLabel.setText(String.valueOf(df.format(tot)));
			//System.out.println("Total Price" + String.valueOf(a));
			
			try {
				//int rand_int1 = ThreadLocalRandom.current().nextInt();
				Connection connection = DriverManager.getConnection(connectionURL+dbName+"?serverTimezone=UTC",username,password);
				//statement = connection.createStatement();
				//String insertOrder = "INSERT INTO order " + "VALUES (10, 1)";
				//String insertOrder = "INSERT INTO order " + "VALUES (1, 1, 1)";
				//statement.executeUpdate(insertOrder);
				//connection.close();
				
				
								
			    String query = "INSERT INTO Orders(TotalAmount, OrderReferenceNumber) VALUES (?,?)";
			    
			    PreparedStatement preparedStmt = connection.prepareStatement(query);
			    //preparedStmt.setInt(1, 1);
			    preparedStmt.setFloat(1, a);
			    preparedStmt.setInt(2, 20);
			    preparedStmt.executeUpdate();
			    preparedStmt.close();
			      
			    connection.close();
			    System.out.println("Inserted records into the table...");
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
    	  
      });
      payButton = new JButton("Pay");
      be = new JButton("East");
      
      //ScrollPane
      scrollPane = new JScrollPane(table);
      scrollPane.setSize(300, 300);
      
      //North Panel 
      orderModePanel = new JPanel();
      orderModePanel.setLayout(new FlowLayout());
      
      orderModePanel.add(orderModeLabel);
      orderModePanel.add(eatInButton);
      orderModePanel.add(takeAwayButton);
      
      //Center Panel
      controlPanel = new JPanel();
      controlPanel.setPreferredSize(new Dimension(100,100));
      controlPanel.setLayout(new GridLayout(5,1));     
      
      controlPanel.add(scrollPane);        
      controlPanel.add(headerLabel);
      controlPanel.add(itemQuantityLabel);
      controlPanel.add(quantitySelection);
      controlPanel.add(addButton);  
      
      //West Panel
      quantityPanel = new JPanel();
      quantityPanel.setPreferredSize(new Dimension(100,100));
      quantityPanel.setLayout(new FlowLayout());
//      quantityPanel.add(headerLabel);
//      quantityPanel.add(itemQuantityLabel);
//      quantityPanel.add(quantitySelection);
//      quantityPanel.add(addButton);   
      
      //South Panel
      creditCardPanel = new JPanel();
      creditCardPanel.setPreferredSize(new Dimension(250,250));
      creditCardPanel.setLayout(new FlowLayout());
      creditCardPanel.add(creditCardLabel);
      creditCardPanel.add(creditCardField);   
      creditCardPanel.add(payButton);   
      
      //East Panel
      listPanel = new JPanel();
      listPanel.setPreferredSize(new Dimension(250,250));
      listPanel.setLayout(new FlowLayout());
      listPanel.add(list);
      listPanel.add(quantityTotalLabel);
      listPanel.add(quantityLabel);
      listPanel.add(priceTotalLabel);
      listPanel.add(priceLabel);
      
      
      mainFrame.add(orderModePanel, BorderLayout.NORTH);
      mainFrame.add(controlPanel, BorderLayout.CENTER);
      mainFrame.add(creditCardPanel, BorderLayout.SOUTH);
      mainFrame.add(listPanel, BorderLayout.EAST);      
      mainFrame.add(quantityPanel, BorderLayout.WEST);  
      
   }
      
   private void showTableDemo() throws ClassNotFoundException{
      
      try {
    	  Class.forName(driver);
  		  Connection connection = DriverManager.getConnection(connectionURL+dbName+"?serverTimezone=UTC",username,password); //Creating connection with database
          statement = connection.createStatement();//creating statement object
          String query = "select * from itemproduct";//Storing MySQL query in A string variable
          ResultSet resultSet = statement.executeQuery(query);//executing query and storing result in ResultSet
                              
          while (resultSet.next()) 
          {          
       	   	//Retrieving details from the database and storing it in the String variables
        	  int id = resultSet.getInt("ItemProduct");
              String name = resultSet.getString("Name");
              float price = resultSet.getFloat("Price");
              //String aaa = String.valueOf(id);       
              itemprod.setItemProduct(id);
              itemprod.setName(name);
              itemprod.setPrice(price);
              
              Object[] populateData = {itemprod.getItemProduct(), itemprod.getName(), itemprod.getPrice()}; 
              tableModel.addRow(populateData);
                            
              
              //System.out.println(resultSet.getInt("ItemProduct") + ", " + resultSet.getString("Name") + ", " + resultSet.getString("Price"));
          }  
          
          ListSelectionModel select = table.getSelectionModel();
          select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          select.addListSelectionListener(new ListSelectionListener() {  
              public void valueChanged(ListSelectionEvent e) 
              {
                Data = null;
                Price = null;
                int[] row = table.getSelectedRows();
                int[] columns = table.getSelectedColumns();
                for (int i = 0; i < row.length; i++) 
                {            
                  for (int j = 0; j < columns.length; j++) 
                  {                	
                    Data = String.valueOf(table.getValueAt(row[i], columns[j]));
                    Price = String.valueOf(table.getValueAt(row[i], 2));
                  }                                  
                }                
                headerLabel.setText(Data);  
                
              }
            });
      } catch (SQLException throwables) {
          throwables.printStackTrace();
      }      
   }
}
