package workshop_ci_tg;

import java.util.ArrayList;

public class ValidatorInput {
	
	public static final int MAX_AMOUNT_DINNERS_FOR_USER = 100;
	
	public static int validateInputAmountDinner(String inputString) {
		if(!inputString.contains("|")) {
			inputString += "|";
		}
		String[] inputs = inputString.split("\\|");
		for(String inpString: inputs) {
			String[] input = inpString.split(" ");
			int idDinner = Integer.parseInt(input[0]);
			int amount = Integer.parseInt(input[1]);
			if(idDinner < 0 && idDinner > 6) {
				return -2;
			}
			if(!(amount > 0)) {
				return -1;
			}
		}
		return 0;
	}
	
	public static int validateExistingIdDinner(int id, ArrayList<Order> userDinnersSelected) {
		if(userDinnersSelected.isEmpty()) return 0;
		for(int i = 0; i < userDinnersSelected.size(); i++) {
			if(userDinnersSelected.get(i).getDinner() == id) {
				return -4;
			}
		}
		return 0;
	}
	
	public static int checkUserOrderDinnerIsEmpty(ArrayList<Order> ordersUser) {
		if(ordersUser.isEmpty()) {
			return -5;
		}
		return 0;
	}
	
	public static int checkMaxAmountQuantity(int totalAmount) {
		if(totalAmount > MAX_AMOUNT_DINNERS_FOR_USER) {
			return -6;
		}
		return 0;
	}

}

