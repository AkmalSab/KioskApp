package clientinterface;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import kioskapp.itemproduct.ItemProduct;

import java.sql.*;

public class KitchenInterface {
	
	public String Data;
   private DefaultListModel<String> list1;    
   private JList<String> list; 
	
   ItemProduct itemprod = new ItemProduct();
   
   
	static JFrame frame;
	static JList itemid,item;
	static JLabel label1;
	
	static String driver;
	static String dbName;
	static String connectionURL;
	static String username;
	static String password;
	
	Connection connection;//Creating object of Connection class
	Statement statement;//Creating object of Statement class
	ResultSet resultset;
	
	public KitchenInterface() {
		driver = "com.mysql.cj.jdbc.Driver";
		connectionURL ="jdbc:mysql://localhost:3306/";
		dbName = "kioskappdb_dev";
		username = "root";
		password = "";
		
		list1= new DefaultListModel<>();
		list = new JList<>(list1);
		list.setBounds(100,100,75,75);
	}

	//main class
	public static void main(String[] args)
	{
		KitchenInterface UI = new KitchenInterface();
		UI.KitchenGUI();
		UI.retrieve();
	}
	
	private void KitchenGUI() {
		//create a new frame
				frame = new JFrame("Kitchen Frame");
				
				//create a object
				KitchenInterface s=new KitchenInterface();
			
				//create a panel
				JPanel p =new JPanel();
				p.setLayout(new FlowLayout());
				p.add(list);
				frame.add(p);
				
				
				/*
				 * //create a new label label1 = new JLabel(); JLabel labels= new
				 * JLabel("select the day of the week");
				 * 
				 * //String array to store weekdays String orderid[]= { "1","2","3",
				 * "4","5","6","7"};
				 * 
				 * String items[]= {
				 * "McChicken","Ayam Goreng McD Spicy (2pcs)","Ayam Goreng McD Spicy (5pcs)",
				 * "Spicy Chicken McDeluxe","Chicken McNuggets (6pcs)","Double Cheeseburger"
				 * ,"Big Mac"};
				 * 
				 * //create list itemid= new JList(orderid); //set a selected index
				 * itemid.setSelectedIndex(2); //add list to panel p.add(itemid); frame.add(p);
				 */
				
				
				//set the size of frame
				frame.setSize(700,600);
				
				frame.setVisible(true);
	}
	
	public DefaultListModel retrieve() {
		
		DefaultListModel dm = new DefaultListModel();
		
		String sql = "SELECT name,price FROM itemproduct";
		
		try {
			Connection connection = DriverManager.getConnection(connectionURL+dbName+"?serverTimezone=UTC",username,password);
			statement = connection.prepareStatement(sql);
			resultset = statement.executeQuery(sql);
			
			while (resultset.next())
			{
	              String name = resultset.getString("Name");
	              Float price = resultset.getFloat("Price");
	              itemprod.setName(name);
	              list1.addElement(itemprod.getName()+" " + String.valueOf(price));
	              
					/*
					 * item= new JList(itemprod.getName()); //set a selected index
					 * item.setSelectedIndex(2); //add list to panel p.add(item);
					 * frame.add(p,BorderLayout.CENTER);
					 */
			}
			return dm;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
}
