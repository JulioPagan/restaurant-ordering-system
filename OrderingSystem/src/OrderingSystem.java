import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;


//Julio Pagan :)

public class OrderingSystem extends JFrame implements ActionListener
{
	  private static final long serialVersionUID = 1L;
//variables to be used in actionListener and printReceipt()
	String userpw="";
	String syspw="iitadmin";
	String payment = "";
	
	String amountOfOrders;
	String amountOfGyro, amountOfItalian, amountOfHotDog, amountOfHamburger,
	amountOfMeatball, amountOfBeverages, amountOfLargeFries, amountOfSmallFries;
	StringBuffer receipt = new StringBuffer();
	StringBuffer registry = new StringBuffer();
	int ordrsAmnt = 1;
	int i = 1;
	double discount = 0;
	double tax = 0.10;
	double totalCost, totalPostTax, totalPostDiscount;
	double finalTotal;
	int totalReceipts = 0;
	double totalRevenue;
	
//updatable variables to be manipulated by Action Listeners
//item selection will influence counters to add the totalCosts
	int gyroCount = 0;
	int hamburgerCount = 0;
	int italianCount = 0;
	int hotDogCount =  0;
	int meatballCount = 0;
	int lfriesCount = 0;
	int sfriesCount = 0;
	int beverageCount  = 0;
	
//firstPromptJPanel
	JButton chooseIndividual = new JButton("Individual order");
	JButton chooseGroup = new JButton("Group order");
	JButton chooseCatering = new JButton("Catering order");
//Components of JPanel
	JCheckBox gyro = new JCheckBox("Gyro");
	JButton gyroMultiplier = new JButton("# of gyro");
	JCheckBox italianBeef = new JCheckBox("Italian Beef");
	JButton italianBeefMultiplier = new JButton("# of italian beef");
	JCheckBox hotDog = new JCheckBox("Hot Dog");
	JButton hotDogMultiplier = new JButton("# of hot dog");
	JCheckBox hamburger = new JCheckBox("Hamburger");
	JButton hamburgerMultiplier = new JButton("# of hamburger");
	JCheckBox meatball = new JCheckBox("Meatball");
	JButton meatballMultiplier = new JButton("# of meatball");
	JCheckBox beverage = new JCheckBox("Any Beverage");
	JButton beverageMultiplier = new JButton("# of beverage");
	JCheckBox largeFries = new JCheckBox("Large GreekFries");
	JButton largeFriesMultiplier = new JButton("# of large fries");
	JCheckBox smallFries = new JCheckBox("Small GreekFries");
	JButton smallFriesMultiplier = new JButton("# of small fries");
	JCheckBox studentDiscount = new JCheckBox("Student Discount");
	JCheckBox seniorDiscount = new JCheckBox("Senior Discount");
	
	JButton doneIndividual = new JButton("Done");
	JButton doneGroup = new JButton("Done");
	JButton doneCatering = new JButton("Done");

//panel components to call in listener to change the JFrame
	JPanel firstPrompt = new JPanel();
	JPanel individualOrder = new JPanel();
	JPanel groupOrder = new JPanel();
	JPanel cateringOrder = new JPanel();
	JPanel sandwiches = new JPanel();
	JPanel drinks = new JPanel();
	JPanel fries = new JPanel();
	JPanel discounts = new JPanel();
//panel components to call in the listener for catering orders
	JPanel bulkSandwiches = new JPanel();
	JPanel bulkDrinks = new JPanel();
	JPanel bulkFries = new JPanel();
//first display for the user to take action
		OrderingSystem(){
		Date date = new Date();

//setting up the JFrame arrangement
		JMenuBar menuBar = new JMenuBar();
//file and help - MenuButtons to listen and complete varying methods
		JMenu mnFile = new JMenu("File");
		JMenuItem mntmExit = new JMenuItem("Exit");
		JMenu mnHelp = new JMenu("Help");
		JMenuItem mntmAbout = new JMenuItem("About");
		JMenuItem mntmLogin = new JMenuItem("Login");
//arrangement of the components of the JFrame
//declaring layout, dimensions, and components
		setSize(600, 650);
		add(menuBar);
		menuBar.add(mnFile);
		mnFile.add(mntmExit);
		menuBar.add(mnHelp);
		mnHelp.add(mntmAbout);
		mnFile.add(mntmLogin);
		setJMenuBar(menuBar);
//allow program to quit when button is clicked
		mntmExit.addActionListener(e -> System.exit(0));
//display about information when button is clicked
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				JOptionPane.showMessageDialog(null, "Programmer: JULIO PAGAN"
				+ "\ndeveloped: NOVEMBER 27, 2018"
				+ "\n\nOrdering System", 
					"About", JOptionPane.PLAIN_MESSAGE);
			}});
//allow Admin to login 
		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				callRegistry();//password is: iitadmin;
			}});
//add Listeners to the firstMenu buttons 
		chooseIndividual.addActionListener(this);
		chooseGroup.addActionListener(this);
		chooseCatering.addActionListener(this);
		setTitle("Ordering System");
//add Listeners to the Panel's components 
		gyro.addActionListener(this);
		gyroMultiplier.addActionListener(this);
		italianBeef.addActionListener(this);
		italianBeefMultiplier.addActionListener(this);
		hotDog.addActionListener(this);
		hotDogMultiplier.addActionListener(this);
		hamburger.addActionListener(this);
		hamburgerMultiplier.addActionListener(this);
		meatball.addActionListener(this);
		meatballMultiplier.addActionListener(this);
		beverage.addActionListener(this);
		beverageMultiplier.addActionListener(this);
		largeFries.addActionListener(this);
		largeFriesMultiplier.addActionListener(this);
		smallFries.addActionListener(this);
		smallFriesMultiplier.addActionListener(this);
		studentDiscount.addActionListener(this);
		seniorDiscount.addActionListener(this);
//add Listeners to the "done" buttons to execute independent code for each order type
		doneGroup.addActionListener(this);
		doneIndividual.addActionListener(this);
		doneCatering.addActionListener(this);
//add set the layout for the main menu Panel 	
		firstPrompt.setLayout(new GridLayout(3, 1));
		firstPrompt.setSize(600,600);
		firstPrompt.add(chooseIndividual);
		firstPrompt.add(chooseGroup);
		firstPrompt.add(chooseCatering);
//add check boxes to familiar panels
//to be added later to the frame accordingly to the user's input
		sandwiches.add(gyro);
		sandwiches.add(italianBeef);
		sandwiches.add(hotDog);
		sandwiches.add(hamburger);
		sandwiches.add(meatball);
		drinks.add(beverage);
		fries.add(largeFries);
		fries.add(smallFries);
		discounts.add(studentDiscount);
		discounts.add(seniorDiscount);
//buttons to add into the catering order menu 
		bulkSandwiches.add(gyroMultiplier);
		bulkSandwiches.add(italianBeefMultiplier);
		bulkSandwiches.add(hotDogMultiplier);
		bulkSandwiches.add(hamburgerMultiplier);
		bulkSandwiches.add(meatballMultiplier);
		bulkDrinks.add(beverageMultiplier);
		bulkFries.add(largeFriesMultiplier);
		bulkFries.add(smallFriesMultiplier);
		cateringOrder.add(doneCatering);
//adding main panels into the frame
		add(firstPrompt,BorderLayout.CENTER);
		add(individualOrder,BorderLayout.CENTER);
		add(groupOrder,BorderLayout.CENTER);
		add(cateringOrder,BorderLayout.CENTER);
//set desired Panel visible
		firstPrompt.setVisible(true);
		individualOrder.setVisible(false);
		groupOrder.setVisible(false);
		cateringOrder.setVisible(false);
		setVisible(true);
		} 
//method to be called when the individual order type is selected
	public void individualOrderMenu() {
		individualOrder.setLayout(new GridLayout(5, 1));
		individualOrder.setSize(600, 600);
		individualOrder.add(sandwiches, BorderLayout.CENTER);
		individualOrder.add(drinks, BorderLayout.CENTER);
		individualOrder.add(fries, BorderLayout.CENTER);
		individualOrder.add(discounts, BorderLayout.CENTER);
		individualOrder.add(doneIndividual,BorderLayout.CENTER);
		firstPrompt.setVisible(false);
		groupOrder.setVisible(false);
		cateringOrder.setVisible(false);
		individualOrder.setVisible(true);	
		}
//method to be called when the group selection is made
	public void groupOrderMenu() {
		groupOrder.setLayout(new GridLayout(5,1));
		groupOrder.setSize(600, 600);
		groupOrder.add(sandwiches, BorderLayout.WEST);
		groupOrder.add(drinks, BorderLayout.CENTER);
		groupOrder.add(fries, BorderLayout.EAST);
		groupOrder.add(discounts, BorderLayout.SOUTH);
		groupOrder.add(doneGroup);
		firstPrompt.setVisible(false);
		individualOrder.setVisible(false);
		cateringOrder.setVisible(false);
		groupOrder.setVisible(true);
		//add(groupOrder);
		amountOfOrders = JOptionPane.showInputDialog("Enter number of orders: ");
		ordrsAmnt = Integer.parseInt(amountOfOrders);
		}		
//method to be called when the catering selection is made
	public void cateringOrderMenu() {
		cateringOrder.setLayout(new GridLayout(5, 1));
		cateringOrder.setSize(600, 600);
		cateringOrder.add(bulkSandwiches, BorderLayout.CENTER);
		cateringOrder.add(bulkDrinks, BorderLayout.CENTER);
		cateringOrder.add(bulkFries, BorderLayout.CENTER);
		cateringOrder.add(doneCatering);
		firstPrompt.setVisible(false);
		individualOrder.setVisible(false);
		groupOrder.setVisible(false);
		cateringOrder.setVisible(true);
	}
//method to be called when the administrator Logs in
	public void callRegistry() {
		userpw = JOptionPane.showInputDialog(null, "Enter System password:");
		int tries = 1;
		while(tries<=3) {
		if (userpw.equals(syspw))
		{
			JOptionPane.showMessageDialog(null, registry.toString());
			break;
		}else{
			userpw = JOptionPane.showInputDialog(null, "Password incorrect please try again");
			tries++; //increment count by 1 up to 3 tries
		}
		}}
//code to calculate the final receipt for the customers
	public void printReceipt() {
		payment = JOptionPane.showInputDialog(null, "Choose payment method\n\ncreditCard\n\ndebitCard\n\ncash");
		switch (payment) {
		case"creditCard":
			receipt.append("Your Payment Option:" + payment + "\n");
			break;
		case"debitCard":
			receipt.append("Your Payment Option:" + payment + "\n");
			break;
		case"cash":
			receipt.append("Your Payment Option:" + payment + "\n");
			break;
		}
		Date date = new Date();
		receipt.append(date);
		receipt.append(String.format("\n%.2f", totalCost) + "-------SUB-TOTAL\n");
		receipt.append(String.format("%.2f",(totalCost * discount)) +  "-------DISCOUNT\n");
		totalPostDiscount = (totalCost - (totalCost * discount));
		receipt.append(String.format("%.2f",totalPostDiscount) + "-------TOTAL\n");
		receipt.append(String.format("%.2f",totalPostDiscount * tax) + "-------TAX\n");
		totalPostTax = (totalPostDiscount + (totalPostDiscount * tax));
		finalTotal = totalPostTax;
		totalRevenue += finalTotal;
		receipt.append(String.format("%.2f",finalTotal) + "-------FINAL-TOTAL\n");
//populate the registry for the orders and totals to be accessible by the admin
		registry.setLength(0);
		registry.append(String.format("$%.2f", totalRevenue) + " TOTAL-REVENUE TODAY\n");
		registry.append(totalReceipts + " receipts have been printed");
		JOptionPane.showMessageDialog(null, receipt.toString());		
		receipt.setLength(0);
		totalCost = 0;
		discount = 0;
		totalPostTax = 0;
		totalPostDiscount = 0;
		finalTotal = 0;
		totalReceipts += 1;
	}
//method to clear the GUI from previous selections
	//clearGUI
	public void clearGui() {
		gyro.setSelected(false);
		italianBeef.setSelected(false);
		hotDog.setSelected(false);
		hamburger.setSelected(false);
		meatball.setSelected(false);
		beverage.setSelected(false);
		largeFries.setSelected(false);
		smallFries.setSelected(false);
		studentDiscount.setSelected(false);
		seniorDiscount.setSelected(false); 
	}
//action handler
	public void actionPerformed(ActionEvent ae) {
//orderingSystem for an individual order
	  if (ae.getSource() == chooseIndividual) {
		  firstPrompt.setVisible(false);
		  individualOrderMenu();
	  }
//orderingSystem for a group order  
	  if (ae.getSource() == chooseGroup) {
		  firstPrompt.setVisible(false);
		  groupOrderMenu();
	  }
//orderingSystem for a catering order
	  if(ae.getSource() == chooseCatering) {
		  cateringOrderMenu();
		  discount = .10;
	  }
//tasks to complete after an individual order is satisfied  
	  if(ae.getSource() == doneIndividual) {
		  if (gyro.isSelected() == true) {
			  totalCost += 5.50;
			  receipt.append("gyro ------------ $5.50\n");	  
		  }
		  if(italianBeef.isSelected() == true) {
			  totalCost += 4.50;
			  receipt.append("italianBeef ----- $4.50\n");
		  }
		  if(hotDog.isSelected() == true) {
			  totalCost += 3.50;
			  receipt.append("hotDog ---------- $3.50\n");
		  }
		  if(hamburger.isSelected() == true) {
			  totalCost += 3.80;
			  receipt.append("hamburger ------- $3.80\n");
		  }
		  if(meatball.isSelected() == true) {
			  totalCost += 4.25;
			  receipt.append("meatball -------- $4.25\n");
		  }
		  if(beverage.isSelected() == true) {
			  totalCost += 1.00;
			  receipt.append("beverage -------- $1.00\n");
		  }
		  if(largeFries.isSelected() == true) {
			  totalCost += 1.50;
			  receipt.append("largeFries ------ $1.50\n");
		  }
		  if(smallFries.isSelected() == true) {
			  totalCost += 1.00;
			  receipt.append("smallFries ------ $1.00\n");
		  }
		  if(studentDiscount.isSelected() == true) {
			  discount = 0.10;
		  }
		  if(seniorDiscount.isSelected() == true) {
			  discount = 0.20;
		  }
		  receipt.append("------------------------\n");
		  printReceipt();
		  clearGui();
		  individualOrder.setVisible(false);
		  firstPrompt.setVisible(true);
	  }
//tasks to complete after a group order is satisfied  
	  if(ae.getSource() == doneGroup){
		  if (gyro.isSelected() == true) {
			  totalCost += 5.50;
			  receipt.append("gyro ------------ $5.50\n");	  
		  }
		  if(italianBeef.isSelected() == true) {
			  totalCost += 4.50;
			  receipt.append("italianBeef ----- $4.50\n");
		  }
		  if(hotDog.isSelected() == true) {
			  totalCost += 3.50;
			  receipt.append("hotDog ---------- $3.50\n");
		  }
		  if(hamburger.isSelected() == true) {
			  totalCost += 3.80;
			  receipt.append("hamburger ------- $3.80\n");
		  }
		  if(meatball.isSelected() == true) {
			  totalCost += 4.25;
			  receipt.append("meatball -------- $4.25\n");
		  }
		  if(beverage.isSelected() == true) {
			  totalCost += 1.00;
			  receipt.append("beverage -------- $1.00\n");
		  }
		  if(largeFries.isSelected() == true) {
			  totalCost += 1.50;
			  receipt.append("largeFries ------ $1.50\n");
		  }
		  if(smallFries.isSelected() == true) {
			  totalCost += 1.00;
			  receipt.append("smallFries ------ $1.00\n");
		  }
		  if(studentDiscount.isSelected() == true) {
			  discount = 0.10;
		  }
		  if(seniorDiscount.isSelected() == true) {
			  discount = 0.20;
		  }
		  receipt.append("------------------------\n");
		  //populateRegistry();
		  i ++;
		  clearGui();
		  if(i > ordrsAmnt) {
			  printReceipt();
			  groupOrder.setVisible(false);
			  firstPrompt.setVisible(true);
			  ordrsAmnt = 0;
			  i = 1;
		  }
	  }
//tasks to complete after a catering order is satisfied 
	  if(ae.getSource() == gyroMultiplier) {
		  amountOfGyro = JOptionPane.showInputDialog("Enter quantity for gyro: ");
		  gyroCount = Integer.parseInt(amountOfGyro);
		  totalCost += 5.50 * gyroCount;
		  receipt.append("x" + gyroCount + " gryro ------------ $5.50\n");
	  }
	  if(ae.getSource() == italianBeefMultiplier) {
		  amountOfItalian = JOptionPane.showInputDialog("Enter quantity for italian beef: ");
		  italianCount = Integer.parseInt(amountOfItalian);
		  totalCost += 4.50 * italianCount;
		  receipt.append("x" + italianCount + " italianBeef ----- $4.50\n");
	  }
	  if(ae.getSource() == hotDogMultiplier) {
		  amountOfHotDog = JOptionPane.showInputDialog("Enter quantity for hot dog: ");
		  hotDogCount = Integer.parseInt(amountOfHotDog);
		  totalCost += 3.50 * hotDogCount;
		  receipt.append("x" + hotDogCount + " hotDog ---------- $3.50\n");
	  }
	  if(ae.getSource() == hamburgerMultiplier) {
		  amountOfHamburger = JOptionPane.showInputDialog("Enter quantity for hamburger: ");
		  hamburgerCount = Integer.parseInt(amountOfHamburger);
		  totalCost += 3.80 * hamburgerCount;
		  receipt.append("x" + hamburgerCount + " hamburger ------- $3.80\n");
	  }
	  if(ae.getSource() == meatballMultiplier) {
		  amountOfMeatball = JOptionPane.showInputDialog("Enter quantity for meatball: ");
		  meatballCount = Integer.parseInt(amountOfMeatball);
		  totalCost += 4.15 * meatballCount;
		  receipt.append("x" + meatballCount + " meatball -------- $4.25\n");
	  }
	  if(ae.getSource() == beverageMultiplier) {
		  amountOfBeverages = JOptionPane.showInputDialog("Enter quantity for beverage:");
		  beverageCount = Integer.parseInt(amountOfBeverages);
		  totalCost += 1.00 * beverageCount;
		  receipt.append("x" + beverageCount + " beverage -------- $1.00\n");
	  }
	  if(ae.getSource() == largeFriesMultiplier) {
		  amountOfLargeFries = JOptionPane.showInputDialog("Enter quantity for large fries:");
		  lfriesCount = Integer.parseInt(amountOfLargeFries);
		  totalCost += 1.50 * lfriesCount;
		  receipt.append("x" + lfriesCount + " largeFries ------ $1.50\n");
	  }
	  if(ae.getSource() == smallFriesMultiplier) {
		  amountOfSmallFries = JOptionPane.showInputDialog("Enter quantity for small fries");
		  sfriesCount = Integer.parseInt(amountOfSmallFries);
		  totalCost += 1.00 * sfriesCount;
		  receipt.append("x" + sfriesCount + " smallFries ------ $1.00\n");
	  }
	  if(ae.getSource() == doneCatering) {
		  receipt.append("------------------------\n");
		  //populateRegistry();
		  printReceipt();
		  clearGui();
		  cateringOrder.setVisible(false);
		  firstPrompt.setVisible(true);
	  }  
  }
//main execution of the code, calls the parent fame and the child components inside
  public static void main(String args[])
  {
    OrderingSystem a = new OrderingSystem();
    a.setVisible(true);
    a.setLocation(200,200);
  }
}