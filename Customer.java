package application;


public class Customer extends Accounts
{
	String accountType, firstName, lastName, address, documentedusername;
	int phone = 0, chooseaccount = 0, menuchoice = 0, documentedPIN = 0, enteredPIN;
	double checkingbalance = 100, savingsbalance = 200, amount = 0;
	
	//Accessors
	public String getaccountType()
	{
		return accountType;
	}
	
	public String getfirstName()
	{
		return firstName;
	}
	
	public String getlastName()
	{
		return lastName;
	}
	
	public String getaddress()
	{
		return address;
	}
	
	public int getphone()
	{
		return phone;
	}
	
	public double getcheckingbalance(double checkbal)
	{
		//System.out.println("get method " + checkbal + " " + checkingbalance);
		checkingbalance = checkbal;
		return checkingbalance;
	}
	
	public double getsavingsbalance()
	{
		return this.savingsbalance;
	}
	
	//Mutators
	public void setcheckingbalance(double checkbal)
	{
		//System.out.println("set method " + checkbal + " " + checkingbalance);
		checkingbalance = checkbal;
	}
	
	public void setsavingsbalance(double savbal)
	{
		savingsbalance = savbal;
	}
	
	//other methods
	public void refresh(int menuchoice)
	{
		this.menuchoice = menuchoice;
	}
	
	public void checkingdeposit(double amount)
	{
		this.checkingbalance = this.checkingbalance + amount;
	}
	
	public void checkingwithdraw(double amount)
	{
		this.checkingbalance = this.checkingbalance - amount;
	}
	
	public void savingsdeposit(double amount)
	{
		this.savingsbalance = this.savingsbalance + amount;
	}
	
	public void savingswithdraw(double amount)
	{
		this.savingsbalance = this.savingsbalance + amount;
	}
	
	//constructors
	Customer()
	{}
	
	Customer(String username, int PIN)
	{
		this.username = username;
		this.PIN = PIN;
	}
	
	//used to navigate menu and update accounts
	Customer(int chooseaccount, int menuchoice, double amount)
	{
		this.chooseaccount = chooseaccount;
		this.menuchoice = menuchoice;
		this.amount = amount;
	}
	
	//make sure PIN entered matches username
	public void verifyPIN(int documentedPIN, int enteredPIN)
	{
		if (documentedPIN == enteredPIN)
			System.out.println("PIN matches.");
		else
		{
			System.out.println("No match. Locate your PIN and try again later.");
			System.exit(0);
		}
	}
}