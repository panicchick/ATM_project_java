//Program: ATMFXMainProgram.java
//Author: Briana Downing
//Date: 10/16/2022
//Purpose: Create a GUI ATM program

package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;


public class ATMFXMainProgram extends Application
{
	static String[] username =  new String[10];
	static String[] PIN = new String[10];
	static String[] accountnumber = new String[10];
	static String[] firstname = new String[10];
	static String[] lastname = new String[10];
	static String[] address = new String[10];
	static String[] phone = new String[10];
	static Double[] checkingbalance = new Double[10];
	static Double[] savingsbalance = new Double[10];
	
	private TextField textUsername = new TextField();
	private TextField textPIN = new TextField();
	private TextField textAmount = new TextField();
	
	Scene scene1, scene2, scWithdraw, scWithdrawChecking, scWithdrawSavings, scDepositChecking, scDepositSavings;
	Scene scAmountToChecking, scAmountFromChecking, scAmountToSavings, scAmountFromSavings, scDeposit, scTransfer, scBalance, scSavingsToChecking, scCheckingToSavings;
	int menuchoice = 0, amount = 0, enteredPIN, pos = 0, position = 0, i = 0;
	String sPIN, susername, documentedusername, enteredusername;
	double checkbal = 0, savbal = 0;
	boolean test = true;
	
	//GUI
	@Override
	public void start(Stage primaryStage) 
	{
		//Create labels
		Label lblAmount = new Label();
		lblAmount.setText("Amount:");
		Label lblToAccount = new Label();
		lblToAccount.setText("Transfer funds to:");
		
		//create buttons
		Button btSignIn = new Button("Sign In");
		Button btWithdraw = new Button("Withdraw");
		Button btDeposit = new Button("Deposit");
		Button btTransfer = new Button("Transfer");
		Button btBalance = new Button("Check Balances");
		Button btSignOut = new Button("Sign Out");
		Button btToChecking = new Button("Checking");
		Button btToSavings = new Button("Savings");
		Button btFromChecking = new Button("Checking");
		Button btFromSavings = new Button("Savings");
		Button btGoWithdrawChecking = new Button("Go");
		Button btGoWithdrawSavings = new Button("Go");
		Button btGoDepositChecking = new Button("Go");
		Button btGoDepositSavings = new Button("Go");
		Button btTransToChecking = new Button("Checking");
		Button btTransToSavings = new Button("Savings");
		Button btMain = new Button("Main Menu");
		Button btGoSavingsToChecking = new Button("Go");
		Button btGoCheckingToSavings = new Button("Go");
		
		
		//create new objects
		Customer newCustomer = new Customer(susername, enteredPIN);
		Accounts userAccount = new Accounts(susername, enteredPIN);
		Menu usermenu = new Menu(menuchoice);
		
		//assign button actions
		btMain.setOnAction(e -> primaryStage.setScene(scene2));
		
		btSignIn.setOnAction(e -> 
		{ 
			isInt(textPIN, textPIN.getText());
			enteredPIN = (Integer.parseInt(sPIN = textPIN.getText()));
			enteredusername = textUsername.getText();
			for(i = 0; i < 10; i++)
			{
				//System.out.println(enteredusername);
				//System.out.println("i = " + i);
				//System.out.println("username in array: " + username[i]);
				
				//checks for username against file
				if(username[i].equals(enteredusername))
				{
					System.out.println("Match at i = " + i);
					pos = i; //updates position of username in file to keep track of other elements for user
					documentedusername = username[pos].toString(); //array to String
					savbal = savingsbalance[pos]; //array to double
					checkbal = checkingbalance[pos]; //array to double
					newCustomer.setcheckingbalance(checkbal);
					//System.out.println("Checking: $" + checkbal + " Savings: $" + savbal);
					primaryStage.setScene(scene2);
				}
				else
					System.out.println("No match at i = " + i);
			}
			newCustomer.verifyPIN(Integer.parseInt(PIN[pos]), enteredPIN);
		});
		
		btWithdraw.setOnAction(e -> usermenu.validateMenuChoice(menuchoice = 1, checkbal, savbal));
		btWithdraw.setOnAction(e -> primaryStage.setScene(scWithdraw));
		
		btDeposit.setOnAction(e -> usermenu.validateMenuChoice(menuchoice = 2, checkbal, savbal));
		btDeposit.setOnAction(e -> primaryStage.setScene(scDeposit));
		
		btTransfer.setOnAction(e -> usermenu.validateMenuChoice(menuchoice = 3, checkingbalance[i], savbal));
		btTransfer.setOnAction(e -> primaryStage.setScene(scTransfer));
		
		btBalance.setOnAction(e -> 
		{
			usermenu.validateMenuChoice(menuchoice = 4, checkbal, savbal);
			primaryStage.setScene(scBalance);
		});
		
		btSignOut.setOnAction(e -> 
		{
			usermenu.validateMenuChoice(menuchoice = 5, checkbal, savbal);
			primaryStage.close();
			System.exit(0);
		});
		
		btFromChecking.setOnAction(e -> 
		{
			newCustomer.chooseaccount = 1;
			usermenu.validateMenuChoice(menuchoice, checkbal, savbal);
		});
		btFromChecking.setOnAction(e -> primaryStage.setScene(scAmountFromChecking));
		
		btToChecking.setOnAction(e -> 
		{
			newCustomer.chooseaccount = 1;
			usermenu.validateMenuChoice(menuchoice, checkbal, savbal);
			primaryStage.setScene(scAmountToChecking);
		});
		
		btFromSavings.setOnAction(e -> 
		{
			newCustomer.chooseaccount = 2;
			usermenu.validateMenuChoice(menuchoice, checkbal, savbal);
			primaryStage.setScene(scAmountFromSavings);
		});
		
		btToSavings.setOnAction(e -> 
		{
			newCustomer.chooseaccount = 2;
			usermenu.validateMenuChoice(menuchoice, checkbal, savbal);
			primaryStage.setScene(scAmountToSavings);
		});
		
		btTransToChecking.setOnAction(e -> 
		{
			usermenu.chooseaccount = 1;
			usermenu.validateMenuChoice(menuchoice, checkbal, savbal);
			primaryStage.setScene(scSavingsToChecking);
		});
		
		btTransToSavings.setOnAction(e -> 
		{
			usermenu.chooseaccount = 2;
			usermenu.validateMenuChoice(menuchoice, checkbal, savbal);
			primaryStage.setScene(scCheckingToSavings);
		});
		
		btGoWithdrawChecking.setOnAction(e -> 
		{
			isInt(textAmount, textAmount.getText());
			usermenu.checkingwithdraw(amount);
			primaryStage.setScene(scBalance);
		});
		
		btGoDepositChecking.setOnAction(e -> 
		{
			isInt(textAmount, textAmount.getText());
			usermenu.checkingwithdraw(amount);
			primaryStage.setScene(scBalance);
		});
		
		btGoWithdrawSavings.setOnAction(e -> 
		{
			isInt(textAmount, textAmount.getText());
			usermenu.savingswithdraw(amount);
			primaryStage.setScene(scBalance);
		});
		
		btGoDepositSavings.setOnAction(e -> 
		{
			isInt(textAmount, textAmount.getText());
			usermenu.savingswithdraw(amount);
			primaryStage.setScene(scBalance);
		});
		
		btGoSavingsToChecking.setOnAction(e -> 
		{
			isInt(textAmount, textAmount.getText());
			usermenu.savingswithdraw(amount);
			usermenu.checkingdeposit(amount);
			primaryStage.setScene(scBalance);
		});
		
		btGoCheckingToSavings.setOnAction(e -> 
		{
			isInt(textAmount, textAmount.getText());
			usermenu.checkingwithdraw(amount);
			usermenu.savingsdeposit(amount);
			primaryStage.setScene(scBalance);
		});
		
		////////Scenes///////////
		//Sign in
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(20, 20, 20, 20));
		pane.add(new Label("Username:"), 0, 0);
		pane.add(textUsername, 1, 0);
		pane.add(new Label("PIN:"), 0, 1);
		pane.add(textPIN, 1, 1);
		pane.add(btSignIn, 1, 2);
		
		//menu
		VBox menu = new VBox(10);
		menu.getChildren().addAll(btWithdraw, btDeposit, btTransfer, btBalance, btSignOut);
		menu.setPadding(new Insets(10, 10, 10, 10));
		scene2 = new Scene(menu, 300, 200);
		
		//withdraw scene
		VBox withdraw = new VBox(10);
		withdraw.getChildren().addAll(btFromChecking, btFromSavings);
		menu.setPadding(new Insets(10, 10, 10, 10));
		scWithdraw = new Scene(withdraw, 300, 200);
		
		//amountToChecking scene
		GridPane amountToChecking = new GridPane();
		amountToChecking.setAlignment(Pos.CENTER);
		amountToChecking.setPadding(new Insets(20, 20, 20, 20));
		amountToChecking.add(new Label("Amount:"), 0, 0);
		amountToChecking.add(textAmount, 1, 0);
		amountToChecking.add(btGoDepositChecking, 1, 2);
		scAmountToChecking = new Scene(amountToChecking, 300, 200);
		
		//amountFromChecking scene
		GridPane amountFromChecking = new GridPane();
		amountFromChecking.setAlignment(Pos.CENTER);
		amountFromChecking.setPadding(new Insets(20, 20, 20, 20));
		amountFromChecking.add(new Label("Amount:"), 0, 0);
		amountFromChecking.add(textAmount, 1, 0);
		amountFromChecking.add(btGoWithdrawChecking, 1, 2);
		scAmountFromChecking = new Scene(amountFromChecking, 300, 200);
		
		//amountToSavings scene
		GridPane amountToSavings = new GridPane();
		amountToSavings.setAlignment(Pos.CENTER);
		amountToSavings.setPadding(new Insets(20, 20, 20, 20));
		amountToSavings.add(new Label("Amount:"), 0, 0);
		amountToSavings.add(textAmount, 1, 0);
		amountToSavings.add(btGoDepositSavings, 1, 2);
		scAmountToSavings = new Scene(amountToSavings, 300, 200);
		
		//amountFromSavings scene
		GridPane amountFromSavings = new GridPane();
		amountFromSavings.setAlignment(Pos.CENTER);
		amountFromSavings.setPadding(new Insets(20, 20, 20, 20));
		amountFromSavings.add(new Label("Amount:"), 0, 0);
		amountFromSavings.add(textAmount, 1, 0);
		amountFromSavings.add(btGoWithdrawSavings, 1, 2);
		scAmountFromSavings = new Scene(amountFromSavings, 300, 200);
		
		//deposit scene
		VBox depositScreen = new VBox(10);
		depositScreen.getChildren().addAll(btToChecking, btToSavings);
		menu.setPadding(new Insets(10, 10, 10, 10));
		scDeposit = new Scene(depositScreen, 300, 200);
		
		//transfer scene
		VBox transferScreen = new VBox(10);
		transferScreen.getChildren().addAll(lblToAccount, btTransToChecking, btTransToSavings);
		menu.setPadding(new Insets(10, 10, 10, 10));
		scTransfer = new Scene(transferScreen, 300, 200);
		
		//transfer to checking amount scene
		GridPane SavingsToChecking = new GridPane();
		SavingsToChecking.setAlignment(Pos.CENTER);
		SavingsToChecking.setPadding(new Insets(20, 20, 20, 20));
		SavingsToChecking.add(new Label("Amount:"), 0, 0);
		SavingsToChecking.add(textAmount, 1, 0);
		SavingsToChecking.add(btGoSavingsToChecking, 1, 2);
		scSavingsToChecking = new Scene(SavingsToChecking, 300, 200);
		
		//transfer to savings amount scene
		GridPane CheckingToSavings = new GridPane();
		CheckingToSavings.setAlignment(Pos.CENTER);
		CheckingToSavings.setPadding(new Insets(20, 20, 20, 20));
		CheckingToSavings.add(new Label("Amount:"), 0, 0);
		CheckingToSavings.add(textAmount, 1, 0);
		CheckingToSavings.add(btGoCheckingToSavings, 1, 2);
		scCheckingToSavings = new Scene(CheckingToSavings, 300, 200);
		
		//check balances scene
		GridPane balance = new GridPane();
		balance.setAlignment(Pos.CENTER);
		balance.setPadding(new Insets(20, 20, 20, 20));
		balance.add(new Label("Checking:"), 0, 0);
		balance.add(new Label(NumberFormat.getCurrencyInstance().format(newCustomer.getcheckingbalance(checkingbalance[pos]))), 1, 0);
		balance.add(new Label("Savings:"), 0, 1);
		balance.add(new Label(NumberFormat.getCurrencyInstance().format(newCustomer.getsavingsbalance())), 1, 1);
		balance.add(btMain, 2, 1); //back to main menu button
		scBalance = new Scene(balance, 300, 200);

		scene1 = new Scene(pane);
		primaryStage.setTitle("ATM");
		primaryStage.setScene(scene1);
		primaryStage.show();
		
		
	}	
		
	//validate user text entry (can only use whole dollars, so int is appropriate here)
	private boolean isInt(TextField input, String message)
	{
		try
		{
			int amount = Integer.parseInt(input.getText());
			return true;
		}
		catch (NumberFormatException e)
		{
			System.out.println("Invalid input: " + message + " is not a number.");
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		List<String> listOfStrings = new ArrayList<String>();
   
		//load the data from file
		listOfStrings = Files.readAllLines(Paths.get
        	("C:\\Users\\fanta\\OneDrive\\Documents\\Eclipse\\ATMprojectFX\\accountlist.txt"));
   
		//convert arraylist to array
		String[] array = listOfStrings.toArray(new String[0]);
		int position = 0; 
		
		//print each line of string in array
		for (String eachString : array) 
		{
	        //System.out.println(eachString);
	        convert(eachString,position);
	        position++;
		}
		
		//gather user input for sign-in
		launch(args);
		
	}
	
	//splits each line into individual components and loads them into separate arrays
	private static void convert(String eachString, int pos) 
	{
		String[] strArray = eachString.split(", ");
		
		username[pos] = strArray[0];
		PIN[pos] = strArray[1];
		accountnumber[pos] = strArray[2];
		firstname[pos] = strArray[3];;
		lastname[pos] = strArray[4];;
		address[pos] = strArray[5];
		phone[pos] = strArray[6];
		checkingbalance[pos] =Double.parseDouble(strArray[7]);
		savingsbalance[pos] = Double.parseDouble(strArray[8]);
	}
}