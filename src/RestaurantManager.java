import com.sun.deploy.util.OrderedHashSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
* @author Pichaaun
*
*/

public class RestaurantManager{
    static ArrayList<String> menues = new ArrayList<>();
    static ArrayList<Double> prices = new ArrayList<>();
    static final String Ordered = "src/data/Ordered.txt";

    public static void readFile() {
        ClassLoader cl = RestaurantManager.class.getClassLoader();
        InputStream is = cl.getResourceAsStream("data/MenuItems.txt");

        Scanner fileScanner = new Scanner(is);

        while (fileScanner.hasNextLine()) {
            String list = fileScanner.nextLine();
            String holder[] = list.split("-");
            menues.add(holder[0]);
            prices.add(Double.parseDouble(holder[1]));
        }
        fileScanner.close();
    }

    static void writeToFile(String Orders) throws IOException{
        File or = new File(Ordered);
        FileOutputStream out;
        try {
            out = new FileOutputStream(or, true);
            out.write(Orders.getBytes());
        } catch (FileNotFoundException ex) {
            System.out.println("Couldn't open output file "+or);
            return;
        }
    }

    public static String[] getMenuItems() {
        String[] itemsmenu = menues.toArray(new String[menues.size()]);
        return itemsmenu;
    }

    public static double[] getPrices() {
        double[] eachPrice = new double[prices.size()];
        for (int i = 0; i < eachPrice.length; i++) {
            eachPrice[i] = prices.get(i);
        }
        return eachPrice;
    }

    public static String recordOrder(int[] order, double total) {
        String[] menu = getMenuItems();
        String t = "";
        int i = 0;
        for (String name : menu){
            if (order[i] != 0){
                t = String.format(t + "%-15s%5d\n", menu[i],order[i]);
            }
            i++;
        }
        return String.format("Orders\n\n%s\n%20.2f Baht\n========= Thanks ========",t,total);
    }

}
