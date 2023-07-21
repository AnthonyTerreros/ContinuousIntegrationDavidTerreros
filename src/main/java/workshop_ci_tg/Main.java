package workshop_ci_tg;

import java.util.Scanner;

public class Main {
	
	public static void main() {
		try {
			Scanner s = new Scanner(System.in);
			String userOptionSelected = "";
			RestaurantCalculator rs = new RestaurantCalculator();
			int actionUser = 1;
			while(actionUser != 5) {
				doActions();
				actionUser = s.nextInt();
				if(actionUser == 1 ) {
					showMenu();
					System.out.print("Write id dinner and amount: (Ex: 3 12 or 3 1 | 1 4 | 6 4 for select multiple dinner): ");
					userOptionSelected = s.next();
					int res = ValidatorInput.validateInputAmountDinner(userOptionSelected);
					if(res == -1) {
						System.out.println("You already selected this option. \n");
					}
					else if(res == -2) {
						System.out.println("Id dinner don't exists. Please write again. \n.");
					}
					else {
						rs.addOrder(userOptionSelected);
					}
				}
				if(actionUser == 2) {
					rs.changeOrder(userOptionSelected);
				}
				if(actionUser == 3) {
					double resRC = rs.calculateFinalCost();
					System.out.println("Fina Cost of the Dinners is: " + resRC + "\n");
				}
				
				if(actionUser == 4) {
					rs.cancelOrder();
				}
				
			}
			System.out.println("Thanks for using MyRestaurantApp");
		}catch(Exception e) {
			System.out.println("Ocurr a Error in the app.");
			System.out.println("App Closed!");
		}
	}
	
	public static void showMenu() {
		System.out.println("[+] Welcome to MyRestaurantApp [+]\n");
		System.out.println("Choose a Dining: \n");
		System.out.println("Basic Dining: \n1. Yapinyacho $5\n" + "2. Encebollado $6\n" + "3. Arroz con Pollo $5\n");
		System.out.println("Premiun Dining: \n4. Lasaña $55\n" + "5. Bolon Mixto $100\n" + "6. Cangrejada (20 Krabs)  $75\n");
	}
	
	public static void doActions() {
		System.out.println("1. Make a Order\n");
		System.out.println("2. Change amount Order\n");
		System.out.println("3. Confirm Order\n");
		System.out.println("4. Cancel Order\n");
		System.out.println("5. Exit App\n");
	}
}
