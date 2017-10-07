
import java.util.Scanner;
/**Main class is showing the menues and prices .
 * This program is made for the customer to select the food and beverages.
 *
 * @author Pichaaun popukdee
 *
 */

public class arrayrestaurant {
	static String[] menu = {"Pizza","Chickens","Coke"};
	static int[] price = {250,120,45};
	int total;
	static Scanner input = new Scanner(System.in);
	static int calculate(int price , int quant){
		int t = price * quant ;
		return t ;
	}
	static public int quant (){ 
		int quant;
		System.out.print("Enter Quantity: ");
		quant = input.nextInt();
		return quant;
	}
	static public void printMenu () {
		int k = 0;
		System.out.printf("---------- Welcome to SKE Restaurant ----------");
		while (k < 3) {
			System.out.printf("\n%d.) %s\t%5d\tBaht.", (k + 1), menu[k], price[k]);
			k++;
		}
		System.out.printf("\n4.) Total");
		System.out.printf("\n5.) Exit");
		System.out.println();
	}
	static int[] makeOrder() {
		int[] order = new int[menu.length];
		int total = 0;
		while ( true ){
			int choice = getChoice();
			switch (choice) {
			case 1:
				order[0]= calculate(250,quant());
				break;
			case 2:
				order[1] = calculate(120,quant());
				break;
			case 3:
				order[2] = calculate(45,quant());
				break;
			case 4:
				total = (int) getTotal(order);
				printRecipe(order , total);
			}
			if (choice == 5) {
				System.out.print("==========THANK YOU==========");
				
			break;
			}

		}
		
		return order;
	}
	public static int getChoice(){
		System.out.printf("\nEnter your Choice: ");
		int choice = input.nextInt();
		return choice;
	}
	public static boolean stopMenu (int choice) {
		if(choice == 5)return false;
		return true;
	}
	public static double getTotal(int[] order) {
	     double total = 0.0;
	     for(int k=0; k<order.length; k++) total +=order[k];
	     return total;
	 }
	public static void printRecipe(int[] order , int total) {
		System.out.println("\n+---------Menu----------+------QTY------+-----Price-----+");

		if (order[0] != 0) {
			System.out.printf("| Pizza\t\t\t|\t%d\t|\t%d\t|\n", order[0]/price[0], order[0]);
		}
		if (order[1] != 0) {
			System.out.printf("| Chickens\t\t|\t%d\t|\t%d\t|\n", order[1]/price[1], order[1]);
		}
		if (order[2] != 0) {
			System.out.printf("| Coke\t\t\t|\t%d\t|\t%d\t|\n", order[2]/price[2], order[2]);
		}
		System.out.println("+-----------------------+---------------+---------------+");
		System.out.printf("| Total\t\t\t\t\t|\t%d\t|\n", total);
		System.out.println("+-----------------------+---------------+---------------+");
	}
		
	public static void main(String[] args) {
		printMenu();
		makeOrder();
			
		}
	
		}

	


