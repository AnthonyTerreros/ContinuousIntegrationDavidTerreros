package workshop_ci_tg;

import java.util.ArrayList;
import java.util.Arrays;

public class RestaurantCalculator {

	private ArrayList<Order> userOrdersSelected;
	private ArrayList<Integer> inventoryDinners;
	private ArrayList<String> dinerNames;
	private ArrayList<Integer> costDinners;
	private ArrayList<Integer> specialIdDinners;

	public RestaurantCalculator() {

		this.userOrdersSelected = new ArrayList<>();
		this.inventoryDinners = new ArrayList<>(Arrays.asList(50, 50, 50, 50, 50, 50));
		this.dinerNames = new ArrayList<>(Arrays.asList("Yapinyacho", "Encebollado", "Arroz con Pollo", "Lasaña",
				"Bolon Mixto", "Cangrejada de Infarto (20 Krabs)"));
		this.costDinners = new ArrayList<>(Arrays.asList(5, 5, 5, 55, 100, 75));
		this.specialIdDinners = this.generateSpecialsDinner();
	}

	public ArrayList<Order> getUserOrdersSelected() {
		return userOrdersSelected;	
	}

	public void setUserOrdersSelected(ArrayList<Order> userOrdersSelected) {
		this.userOrdersSelected = userOrdersSelected;
	}

	public ArrayList<Integer> generateSpecialsDinner() {
		int finalLength = this.dinerNames.size() / 2;
		ArrayList<Integer> premiunIdDinners = new ArrayList<>();
		for (int k = 0; k < finalLength; k++) {
			premiunIdDinners.add(finalLength + k);
		}
		return premiunIdDinners;
	}

	public int addOrder(String orders) {
		if(!orders.contains("|")) {
			orders += "|";
		}
		String[] ordersSplitedStrings = orders.split("\\|");
		for (int k = 0; k < ordersSplitedStrings.length; k++) {
			String orderSplit = ordersSplitedStrings[k];
			String[] resSplited = orderSplit.split(" ");
			int idDinnerUserSelected = Integer.parseInt(resSplited[0]) - 1;
			int amountUserSelected = Integer.parseInt(resSplited[1]);
			int resValidator = ValidatorInput.validateInputAmountDinner(orderSplit);
			this.showMessage(resValidator);
			if (resValidator != -1 || resValidator != -2) {
				Order order = new Order(idDinnerUserSelected, amountUserSelected);
				int resExistedId = ValidatorInput.validateExistingIdDinner(resValidator, userOrdersSelected);
				if (resExistedId == -4) {
					this.showMessage(resExistedId);
					return -999;
				} else {
					this.userOrdersSelected.add(order);
				}
			}else {
				return -999;
			}
		}

		return 0;
	}

	public double calculateFinalCost() {
		int totalAmount = this.getAmountTotal();
		int resCheckResultAmount = ValidatorInput.checkMaxAmountQuantity(totalAmount);
		if (resCheckResultAmount == -6) {
			this.showMessage(resCheckResultAmount);
			return -999.00;
		}
		int resCheckEmpty = ValidatorInput.checkUserOrderDinnerIsEmpty(this.userOrdersSelected);
		if (resCheckEmpty == -5) {
			this.showMessage(resCheckEmpty);
			return -999.00;
		}
		for (int k = 0; k < this.userOrdersSelected.size(); k++) {
			Order orderUser = userOrdersSelected.get(k);
			int resInventory = this.resetInventory(orderUser.getDinner(), orderUser.getAmount());
			if (resInventory == -4) {
				this.showMessage(resInventory, orderUser.getDinner());
				System.out.println("This dinner is not available. Please enter 0 in the dinner "
						+ "quantity. Make sure it is the same dinner with the respective id.");
				break;
			}
		}
		double finalCost = this.getTotalCost();
		double finaCostWithDiscounts = this.getDiscounts(finalCost, this.userOrdersSelected.size());
		return finaCostWithDiscounts;
	}

	public double getDiscounts(double totalCost, int lengthOfUserDinnerSelected) {
		if (lengthOfUserDinnerSelected > 5) {
			totalCost += totalCost * 0.05;
		}
		if (lengthOfUserDinnerSelected > 10) {
			totalCost += totalCost * 0.10;
		}
		if (totalCost > 50) {
			totalCost -= 10;
		}
		if (totalCost > 100) {
			totalCost -= 25;
		}
		return totalCost;
	}

	public int getTotalCost() {
		int result = 0;
		for (int m = 0; m < this.userOrdersSelected.size(); m++) {
			int idDinner = this.userOrdersSelected.get(m).getDinner();
			int amountDinner = this.userOrdersSelected.get(m).getDinner();
			if (this.specialIdDinners.contains(idDinner)) {
				result += (this.costDinners.get(m) * amountDinner + this.costDinners.get(m) * amountDinner * 0.05);
			} else {
				result += (this.costDinners.get(m) * amountDinner);
			}
		}
		return result;
	}

	public int getAmountTotal() {
		int total = this.userOrdersSelected.stream().map(element -> element.getAmount())
				.reduce((acu, element) -> acu + element).orElse(0);
		return total;
	}

	public int resetInventory(int id, int amount) {
		int amountTotal = this.inventoryDinners.get(id);
		if (amountTotal == 0) {
			return -3;
		}
		this.inventoryDinners.set(id, amountTotal - amount);
		return 0;
	}

	private void showMessage(int res, int... idDinnerUserSelected) {
		if (res == -1) {
			System.out.println("The amount digit is negative. Cannot be added to the order list. \n");
		}
		if (res == -2) {
			System.out.println("the id dinner digit is invalid.  Cannot be added to the order list. \n");
		}
		if (res == -3) {
			System.out.println("The Dinner with Id" + idDinnerUserSelected + "do not available. \n");
		}
		if (res == -4) {
			System.out.println(
					"The Id Dinner is already selected. Please go to Change Amount of Dinner for update dinner. \n");
		}
		if (res == -5) {
			System.out.println("You have not selected a dinner, please select a dinner to purchase.\n");
		}
		if (res == -6) {
			System.out.println("You have exceeded the number of dinners that can be ordered. "
					+ "Please correct the number of dinners.\n");
		}
	}

	public int changeOrder(String inputUser) {
		int resValidator = ValidatorInput.validateInputAmountDinner(inputUser);
		if (resValidator != 1 || resValidator != 2) {
			String[] inputSplited = inputUser.split(" ");
			int idDinnerUserSelecte = Integer.parseInt(inputSplited[0]);
			int amountDinner = Integer.parseInt(inputSplited[1]);
			Order order = this.userOrdersSelected.get(idDinnerUserSelecte);
			order.setAmount(amountDinner);
			System.out.println("ID" + idDinnerUserSelecte + "Dinner has been updated! \n");
			return 0;
		} else {
			this.showMessage(resValidator);
			return -999;
		}

	}

	public void cancelOrder() {
		this.inventoryDinners = new ArrayList<>();
	}

}
