package application;

public class Accounts 
{
	String username, susername;
	int PIN = 0, accountNum = 0, iPIN = 0;
	
	//Accessors
	public String getusername()
	{
		return susername;
	}
	
	//constructors
	Accounts()
	{}
	
	Accounts(String susername, int iPIN)
	{
		//System.out.println("Welcome, " + susername + "!");
	}
}