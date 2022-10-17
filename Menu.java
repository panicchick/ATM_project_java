package application;

import java.util.Scanner;

public class Menu extends Customer
{
	int chooseaccount = 0;
	double amount = 0;
	
	//menu for user - not needed with GUI
	public static void showMenu()
	{
		//System.out.println("ATM MENU");
		//System.out.println("1 for withdraw");
		//System.out.println("2 for deposit");
		//System.out.println("3 for transfer funds");
		//System.out.println("4 for check balance");
		//System.out.println("5 for sign out");
	}
	
	//constructors
	public Menu()
	{}
	
	public Menu(int menuchoice)
	{
		//System.out.println("This is the menu constructor with menuchoice");
	}
	
	//process user's menu choice
	public void validateMenuChoice(int menuchoice, double checkbal, double savbal)
	{
		
		Scanner input = new Scanner(System.in);
		System.out.print("You have chosen option " + menuchoice + ": ");
		Customer accountupdate = new Customer(chooseaccount, menuchoice, amount);
		Customer newCustomer = new Customer();
		
		//switch statement based off of which button is pressed
		switch(menuchoice)
		{
			case 1: System.out.println("withdraw. Which account will you withdraw from? 1 for checking, 2 for savings");
				chooseaccount = input.nextInt();
				System.out.print("Amount: ");
				amount = input.nextDouble();
				accountupdate.refresh(menuchoice);
				if (chooseaccount == 1)
				{
					accountupdate.checkingwithdraw(amount);
					//accountupdate.setcheckingbalance(amount, menuchoice);
					System.out.println("Checking balance: " + accountupdate.getcheckingbalance(checkbal));
				}
				else
				{
					accountupdate.savingswithdraw(amount);
					//accountupdate.setsavingsbalance(amount, menuchoice);
					System.out.println("Savings balance: " + accountupdate.getsavingsbalance());
				}
				break;
			case 2: System.out.println("deposit. Which account will you deposit into? 1 for checking, 2 for savings");
				chooseaccount = input.nextInt();
				System.out.print("Amount: ");
				amount = input.nextDouble();
				accountupdate.refresh(menuchoice);
				if (chooseaccount == 1)
				{
					accountupdate.checkingdeposit(amount);
					//accountupdate.setcheckingbalance(amount, menuchoice);
					System.out.println("Checking balance: " + accountupdate.getcheckingbalance(checkbal));
				}
				else
				{
					accountupdate.savingsdeposit(amount);
					//accountupdate.setsavingsbalance(amount, menuchoice);
					System.out.println("Savings balance: " + accountupdate.getsavingsbalance());
				}
				break;
			case 3:System.out.println("transfer funds. Which account will you transfer money into?");
				chooseaccount = input.nextInt();
				System.out.print("Amount: ");
				amount = input.nextDouble();
				accountupdate.refresh(menuchoice);
				//accountupdate.setcheckingbalance(amount, menuchoice);
				if(chooseaccount == 1)
				{
					accountupdate.checkingdeposit(amount);
					accountupdate.savingswithdraw(amount);
				}
				else
				{
					accountupdate.savingsdeposit(amount);
					accountupdate.checkingwithdraw(amount);
				}
				System.out.println("Checking balance: " + accountupdate.getcheckingbalance(checkbal));
				//accountupdate.setsavingsbalance(amount,  menuchoice);
				System.out.println("Savings balance: " + accountupdate.getsavingsbalance());
				break;
			case 4: System.out.println("check your balances.");
				accountupdate.refresh(menuchoice);
				System.out.println("Checking balance: $" + accountupdate.getcheckingbalance(checkbal));
				System.out.println("Savings balance: $" + accountupdate.getsavingsbalance());
				break;
			case 5: System.out.println("sign out. Goodbye!");
				break;
			default: System.out.println("You have entered an invalid response.");
		}
		
	}
}