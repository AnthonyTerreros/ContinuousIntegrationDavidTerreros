package workshop_ci_tg;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class RestaurantCalculatorApp {
	
	// class RestaurantCalculator
	@Test
	public void addOrderTest() {
		RestaurantCalculator rc = new RestaurantCalculator();
		int resAdd = rc.addOrder("3 4|4 2");
		System.out.println(resAdd);
		assertEquals(0, resAdd);
	}
	
	@Test
	public void calculateFinalCostTest() {
		ArrayList<Order> ordersUser = new ArrayList<>();
		ordersUser.add(new Order(1, 10));
		ordersUser.add(new Order(2, 10));
		ordersUser.add(new Order(3, 10));
		RestaurantCalculator rc = new RestaurantCalculator();
		rc.setUserOrdersSelected(ordersUser);
		double finalCostForUser = rc.calculateFinalCost();
		double testEye = 115.00;
		assertEquals(finalCostForUser, testEye);
	}
	
	@Test
	public void getDiscountsTest() {
		double totalCostUser = 234.00;
		int userDinnerSelectedSize = 5;
		if (userDinnerSelectedSize > 5) totalCostUser = totalCostUser * 0.05;
		if (userDinnerSelectedSize > 10) totalCostUser = totalCostUser * 0.10;
		if (totalCostUser > 50) totalCostUser -= 10;
		if (totalCostUser > 100) totalCostUser -= 25;
		RestaurantCalculator rc = new RestaurantCalculator();
		double resDiscount = rc.getDiscounts(totalCostUser, userDinnerSelectedSize);
		assertEquals(totalCostUser, resDiscount);
	}
	
	@Test
	public void getTotalCostTest() {
		ArrayList<Order> ordersUser = new ArrayList<>();
		ordersUser.add(new Order(1, 10));
		ordersUser.add(new Order(2, 10));
		ordersUser.add(new Order(3, 10));
		RestaurantCalculator rc = new RestaurantCalculator();
		rc.setUserOrdersSelected(ordersUser);
		int result = rc.getTotalCost();
		assertEquals(150, result);
		
	}
	
	@Test
	public void getAmountTotalTest() {
		ArrayList<Order> ordersUser = new ArrayList<>();
		ordersUser.add(new Order(3, 10));
		ordersUser.add(new Order(4, 10));
		ordersUser.add(new Order(5, 10));
		RestaurantCalculator rc = new RestaurantCalculator();
		rc.setUserOrdersSelected(ordersUser);
		int totalAmount = rc.getAmountTotal();
		int testValueAmountTotal = ordersUser.stream().map(element -> element.getAmount())
		.reduce((acu, element) -> acu + element).orElse(0);
		assertEquals(testValueAmountTotal, totalAmount);
	}
	
	@Test
	public void resetInventoryTest() {
		ArrayList<Order> ordersUser = new ArrayList<>();
		ordersUser.add(new Order(3, 10));
		ordersUser.add(new Order(4, 10));
		ordersUser.add(new Order(5, 10));
		RestaurantCalculator rc = new RestaurantCalculator();
		rc.setUserOrdersSelected(ordersUser);
		int resResetInventory = rc.resetInventory(4, 4);
		assertEquals(0, resResetInventory);
	}
		
	@Test
	public void changeOrderTest() {
		ArrayList<Order> ordersUser = new ArrayList<>();
		ordersUser.add(new Order(3, 1));
		ordersUser.add(new Order(4, 1));
		ordersUser.add(new Order(5, 1));
		RestaurantCalculator rc = new RestaurantCalculator();
		rc.setUserOrdersSelected(ordersUser);
		int resChangeOrder = rc.changeOrder("3 4");
		assertEquals(0, resChangeOrder);
	}
	
	
	// class ValidateInputAmount
	@Test
	public void validateInputAmountDinnerTest() {
		int resValitedateI1 = ValidatorInput.validateInputAmountDinner("3 2|2 1");
		assertEquals(0, resValitedateI1);
	}
	
	@Test
	public void validateExistingIdDinnerTest() {
		ArrayList<Order> ordersUser = new ArrayList<>();
		ordersUser.add(new Order(3, 1));
		ordersUser.add(new Order(4, 1));
		ordersUser.add(new Order(5, 1));
		int resValidateI2 = ValidatorInput.validateExistingIdDinner(3, ordersUser);
		assertEquals(-4, resValidateI2);
	}
	
	@Test
	public void checkUserOrderDinnerIsEmptyTest() {
		ArrayList<Order> ordersUser = new ArrayList<>();
		int resCheckOrderDinnerEmpty = ValidatorInput.checkUserOrderDinnerIsEmpty(ordersUser);
		assertEquals(resCheckOrderDinnerEmpty, 0);
	}
	
	@Test
	public void checkMaxAmountQuantity() {
		int resCheckMaxQuantity = ValidatorInput.checkMaxAmountQuantity(1000);
		assertEquals(0, resCheckMaxQuantity);
	}

}
