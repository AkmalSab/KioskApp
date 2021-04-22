package clientinterface;

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
	public int order;
   private DefaultListModel<String> list1;    
   private JList<String> list; 
	
   ItemProduct itemprod = new ItemProduct();
   
   
	private JFrame frame;
	private JList itemid,item;
	private JLabel label1,label2;
	private JPanel lab,p;
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
		list.setFixedCellHeight(50);
		list.setFixedCellWidth(500);
		list.setBounds(100,100,75,75);
	}

	//main class
//	public static void main(String[] args)
//	{
//		KitchenInterface UI = new KitchenInterface();
//		UI.KitchenGUI();
//		UI.retrieve();
//	}
	
	private void KitchenGUI() {
		//create a new frame
				frame = new JFrame("Kitchen Frame");
				
				//create a object
				KitchenInterface s=new KitchenInterface();
				
				label1 = new JLabel();
				label2 = new JLabel("Order Number:");
	
				//NORTH
				lab =new JPanel();
				lab.setPreferredSize(new Dimension(250,100));
				lab.setLayout(new FlowLayout());
				lab.add(label2);
				lab.add(label1);
				
				//create a panel
				p =new JPanel();
				p.setPreferredSize(new Dimension(250,250));
				p.setLayout(new FlowLayout());
				p.add(list);
				
				frame.add(p, BorderLayout.CENTER);
				frame.add(lab, BorderLayout.NORTH);
				frame.setBackground(Color.BLACK);

				//set the size of frame
				frame.setSize(700,600);
				
				frame.setVisible(true);
				
				
	}
	
	public DefaultListModel retrieve() {
		
		KitchenGUI();
		
		DefaultListModel dm = new DefaultListModel();
		
		String sql = "select OrderedItemId, i.Name 'Itemproduct', Quantity , o.OrderReferenceNumber 'orders' from orders o, ordereditem t, itemproduct i where t.Orders = o.OrderId and t.ItemProduct = i.ItemProduct";
	
		try {
			Connection connection = DriverManager.getConnection(connectionURL+dbName+"?serverTimezone=UTC",username,password);
			statement = connection.prepareStatement(sql);
			resultset = statement.executeQuery(sql);
			
			order = 0;
			int i = 0;
			while (resultset.next())
			{
				i++;
				  //int id = resultset.getInt("OrderedItemId");
				  int id = i;
				  String item  = resultset.getString("ItemProduct");
				  int quantity = resultset.getInt("Quantity");
				  order = resultset.getInt("Orders");
	              itemprod.setName(item);
	              
	              list1.addElement(String.valueOf(id) +"  "+ " ITEM: " + itemprod.getName()+" "+ "QUANTITY: " + String.valueOf(quantity));
					/* list1.addElement("\t" +" "+ "QUANTITY: " + String.valueOf(quantity)); */			
	              }
			label1.setText(String.valueOf(order));
			return dm;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
}
