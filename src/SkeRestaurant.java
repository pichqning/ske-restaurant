
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.SortingFocusTraversalPolicy;
/**Main class is showing the menues and prices .
 * This program is made for the customer to select the food and beverages.
 *
 * @author Pichaaun popukdee
 *
 */

public class SkeRestaurant {

	private static String[] menuItems ;
	private static double[] prices ;
	private static double total = 0;
	static RestaurantManager rm;
	static Scanner input = new Scanner(System.in);

	static double calculate(String choice , int quant , ArrayList<Integer> order){
        double price = 0;
        int order2,amount;
        ArrayList<Integer> rQuant= new ArrayList<Integer>();
        for (int y = 0; y<prices.length; y++){
            rQuant.add(0);
        }
        int choice2 = Integer.parseInt(choice);
        for (int i = 0; i < prices.length; i++){
            if (choice2 == i+1){
                order2 = order.get(i) + quant;
                order.add(i, order2);
                order.remove(i+1);

                amount = quant - rQuant.get(i);
                rQuant.add(i, amount);
                rQuant.remove(i+1);
                price = amount * prices[i];
                break;
            }
        }
        return price;
    }


	public  static int quant (){
		int quant;
		System.out.print("Enter Quantity: ");
		quant = input.nextInt();
		return quant;
	}
	 static void printMenu () {
	    menuItems = RestaurantManager.getMenuItems();
	    prices = RestaurantManager.getPrices();
	    String choice = "(T) Total\n" +  "(E) Exit \n " ;
	    System.out.printf("---------- Welcome to SKE Restaurant ----------");
		for ( int k = 0 ; k < menuItems.length ; k++) {
			System.out.printf("\n%d.) %s\t%5.2f\tBaht.", (k + 1), menuItems[k], prices[k]);
		}
        System.out.print("\n"+choice);
        System.out.println();
	}
	static void makeOrder() throws IOException {
        ArrayList<Integer> order = orderValue();
        int quant = 0;
		int total = 0;
		while ( true ){
			String choice = getChoice();
			switch (choice) {
                case "T" :
				total = (int) getTotal(order);
				printRecipe(order , total);
				recordOrder(order);
				break;

                case "E" :
                    break;

				default:
				   quant = quant();

			}
			if (choice.equals("E")) {
				System.out.print("==========THANK YOU==========");
				
			break;

			}
			total += calculate(choice,quant,order);


		}
	}
	public static String getChoice(){
		System.out.printf("\nEnter your Choice: ");
		String choice = input.next();
		return choice;
	}
	public static boolean stopMenu (String choice) {
		if(choice.equals("E") )return false;
		return true;
	}
	public static double getTotal(ArrayList<Integer> order) {
	     double total = 0.0;
	     for(int k=0; k<order.size(); k++) total +=order.get(k);
	     return total;
	 }
	public static void printRecipe(ArrayList<Integer> order , int total) {
		System.out.println("\n+---------Menu----------+------QTY------+-----Price-----+");
        for (int j = 0; j<prices.length; j++){
            if (prices[j]*order.get(j) != 0){
                System.out.printf("|%-10s\t\t   |\t%d    |\t%9.2f  |\n",menuItems[j],order.get(j),prices[j]*order.get(j));
            }
        }
		System.out.println("+-----------------------+---------------+---------------+");
		System.out.printf("| Total\t\t\t\t\t|\t%d\t|\n", total);
		System.out.println("+-----------------------+---------------+---------------+");
	}
    public static ArrayList<Integer> orderValue() {
        ArrayList<Integer> Order = new ArrayList<Integer>();
        for (int z = 0; z<RestaurantManager.getMenuItems().length; z++){
            Order.add(0);
        }
        return Order;
    }

    public static void recordOrder(ArrayList<Integer> order) throws IOException {
        int[] rec = new int[order.size()];
        for (int i = 0; i<rec.length; i++){
            rec[i] = order.get(i);
        }
        String allOrder = RestaurantManager.recordOrder(rec,total);
        RestaurantManager.writeToFile(allOrder);
    }

		
	public static void main(String[] args) throws IOException {
	    RestaurantManager.readFile();
		printMenu();
		makeOrder();
			
		}
	
		}

	


