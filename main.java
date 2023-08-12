package ConsoleProjects;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ATMMachine {
	

	Scanner in=new Scanner(System.in);
	
	private boolean isFirstOpen=true;
	private boolean isPasswordChange=false;
	private short pin=1234;
	private short oldPin=1234;
	private String pinInString="1234";
	
	public boolean isPasswordChange() {
		return isPasswordChange;
	}

	public void setPasswordChange(boolean isPasswordChange) {
		this.isPasswordChange = isPasswordChange;
	}

	public boolean isFirstOpen() {
		return isFirstOpen;
	}

	public void setFirstOpen(boolean isFirstOpen) {
		this.isFirstOpen = isFirstOpen;
	}
	

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	String listOfNotAvailableCards[] = new String[] { "Debit Mastercard via Kaya Saving by BPI",
			"EON Visa Debit Card by Union Bank", "Visa Debit Card by Landbank",
			"Mastercard and Visa ATM Debit by BDO" };
	private long money;

	public void listOfATMCards() {
		System.out.println();
		System.out.println("-=> Available Card For this ATM Machine");
		System.out.println("1. Debit Masercard via Kaysa Saving by BPI");
		System.out.println("2. EON Visa Debit Card by Union Bank");
		System.out.println("3. Visa Debit Card by Landbank");
		System.out.println("4. Mastercard and Visa ATM Debit by BDO");
		System.out.println();
	}

	public void machineMenu() {
		System.out.println();
		System.out.println("W. Withdrawal\t\tD. Deposit");
		System.out.println("F. Fast Cash\t\tB. Balance Inquiry");
		System.out.println("P. Phone Cards\t\tPA. Payments");
		System.out.println("T. Transfers\t\tO. Others");
		System.out.println("E. Exit");
		System.out.println();
	}
	
	public int choiceForCard() {
		int choiceOfCard=0;
		try {
			System.out.print("Enter value:: ");
			choiceOfCard=in.nextInt();
		} catch (InputMismatchException IME) {
			System.out.println("Please input number only!");
			System.exit(1);
		}
		in.nextLine();
		return choiceOfCard;
	}
	
	public String choiceForMenu() {
		String choiceOfMenu="";
		try {
			System.out.print("Enter your choice:: ");
			choiceOfMenu=in.nextLine();
		} catch (Exception e) {
			System.out.println("Invalid Input!");
		}
		return choiceOfMenu;
	}
	
	public void deposit() {
		long moneyToDeposit;
		try {
			System.out.print("Enter amount to deposit:: ");
			moneyToDeposit=in.nextLong();
			this.money+=moneyToDeposit;
			in.nextLine();
			System.out.println("Deposit Success!");
		} catch(InputMismatchException IME) {
			System.out.println(IME);
		}
	}
		
	
	public void withdraw() {
		
		if(getMoney() > 0) {
			System.out.print("Enter money to withdraw:: ");
			long moneyToWithdraw;
			try {
				moneyToWithdraw=in.nextLong();
				in.nextLine();
				
				if(getMoney() >= moneyToWithdraw) {
					System.out.println("Withdraw Success!");
					
				} else {
					System.out.println("Not Enough Money!");
					System.out.print("Do you want to deposit? [y/n]:: ");
					char choiceIfDeposit=in.nextLine().charAt(0);
					if(choiceIfDeposit == 'y') {
						deposit();
					}
				}
				
			} catch (InputMismatchException IME) {
				System.out.println(IME);
			}
		} else {
			System.out.println("Not Enough Money!");
			System.out.print("Do you want to deposit? [y/n]:: ");
			char choiceIfDeposit=in.nextLine().charAt(0);
			if(choiceIfDeposit == 'y') {
				deposit();
			}
		}
	}
	public void balanceInquiry() {
		System.out.println("1. Savings");
		System.out.println("2. Checking");
		
		try {
			boolean isDone=false;
			
			while(!isDone) {
				System.out.print("Enter choice:: ");
				byte choice=in.nextByte();
				in.nextLine();
				
				if(choice != 1 && choice != 2) {
					System.out.println("Invalid Input!");
				} else {
					System.out.println("Current Balance:: " + this.money);
					isDone=true;
				}
			}
			
			
		} catch(InputMismatchException IME) {
			System.out.println("Invalid Input");
		}
	}
	public void others() {
		try {
			boolean isDone=false;
			
			while(!isDone) {
				System.out.println("1. Change PIN");
				System.out.print("Enter choice:: ");
				byte choice=in.nextByte();
				in.nextLine();
				
				if(choice==1) {
					isDone=true;
				} else {
					System.out.println("Invalid Input!");
				}
			}
			changePIN();
		} catch(InputMismatchException IME) {
			System.out.println("Invalid Input");
		}
	}
	public void changePIN() {
		try {
			
			if(!this.isPasswordChange) {
				
				System.out.print("Enter default PIN:: ");
				short checkIfDefaultPinIsEntered=in.nextShort();
				in.nextLine();
				if(checkIfDefaultPinIsEntered==this.oldPin) {
					
					System.out.print("Enter new PIN:: ");
					short pinInput=in.nextShort();
					in.nextLine();
					String pinInputInString=Integer.toString(pinInput);
					
					boolean nothingChange=false;
					
					for(int i=0; i<4; i++) {
						if(Character.toString(pinInputInString.charAt(i)).equals(Character.toString(this.pinInString.charAt(i)))) {
							nothingChange=true;
						} else nothingChange=false;
					}
					
					if(nothingChange) {
						System.out.println("Nothing Is Change!");
					} else {
						System.out.println("Changed Success!");
						this.oldPin=pinInput;
						this.pin=pinInput;
						this.pinInString=Integer.toString(this.pin);
					}	
					
				} else System.out.println("Enter the old PIN");
			}
		} catch(InputMismatchException IME) {
			System.out.println("Invalid Input!");
		}
	}
	// ------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		ATMMachine q=new ATMMachine();
		boolean doneChoice=false;
		while(true) {
			while(true) {
				if(q.isFirstOpen()) {
					q.listOfATMCards();
					int choice=q.choiceForCard();
					
					if(choice != 1 && choice != 2 && choice != 3 && choice != 4) { 
						System.out.println("Invalid Input");
					} else if(choice != 3) {
						System.out.println(q.listOfNotAvailableCards[--choice] + " is not available!");
					} else {
						doneChoice=true;
						break;
					}
				} else {
					break;
				}
				
			}
			
			if(doneChoice) {
				q.setFirstOpen(false);
				q.machineMenu();
				String myChoiceForMenu=q.choiceForMenu();
				
				if(myChoiceForMenu.equalsIgnoreCase("E")) {
					System.out.println("Exit!");
					System.exit(0);
				}
				
				if(myChoiceForMenu.equalsIgnoreCase("W")) {
					System.out.println("Withdrawal Area!");
					q.withdraw();
				} else if(myChoiceForMenu.equalsIgnoreCase("D")) {
					q.deposit();
				} else if(myChoiceForMenu.equalsIgnoreCase("B")) {
					q.balanceInquiry();
				} else if(myChoiceForMenu.equalsIgnoreCase("O")) {
					q.others();
				}
				
			}
		}
	}
}