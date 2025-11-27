import java.util.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class FoodSupplierRunnerA  {

	public static void main(String[] args)throws IOException {
		File file = new File("supplyOrders.dat");
		Scanner scan = new Scanner(file);
		int num = scan.nextInt();
		FoodSupplierA food = new FoodSupplierA();
		
		int n = 0;
		System.out.println(scan.nextLine());
		for(int i=0; i<num;i++) {
		food.add(scan.nextLine());
	}	
		
		//12-30-2015
		/**
		 * 
01-09-2010 Hardees#97 burgers-4 buns-3
01-09-2010 FiveGuys#2 burgers-3 buns-4 fries-8 cups-1 straws-3
01-09-2010 McDonalds#41 buns-1 cups-1 napkins-5 straws-2
01-09-2010 McDonalds#17 burgers-2 buns-3 fries-2 cups-5 napkins-6 straws-1
		 */
		
		Date date = new Date(2015-1900, 12-1, 30);
	//	System.out.println(date);
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	//	System.out.println(simpleDateFormat.format(food.hasMost(type)));
		String type = "burgers";
	//	System.out.println(food.hasMostCompany(type) + "ordered the most amount of " + type);
	
		//fix this one later
		//	System.out.println(food.longestWaitTime());
		
		//works
		//System.out.println(food.companyThatOrderedTheMost(type, 2010));
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Welcome to fast food data base please select one of the options by typing the number to the left of the option");
		while(scan.nextInt() != -1) {
			
		}
				
		
		
		
		

	}

}
