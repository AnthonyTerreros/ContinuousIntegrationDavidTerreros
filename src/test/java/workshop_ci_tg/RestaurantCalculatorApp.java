package workshop_ci_tg;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RestaurantCalculatorApp {

	RestaurantCalculator rc;

	@BeforeEach
	public void setUp() {
		rc = new RestaurantCalculator();
		ArrayList<Order> ordersUser = new ArrayList<>();
		ordersUser.add(new Order(1, 3));
		ordersUser.add(new Order(4, 3));
		ordersUser.add(new Order(2, 4));
		ordersUser.add(new Order(3, 0));
		rc.setUserOrdersSelected(ordersUser);
		Main.showMenu();
		Main.doActions();
	}

	// class RestaurantCalculator
	@Test
	public void addOrderTest() {
		RestaurantCalculator rc = new RestaurantCalculator();
		int resAdd = rc.addOrder("3 3|2 2");
		assertEquals(0, resAdd);
	}

	@Test
	public void calculateFinalCostTest() {

		double finalCostForUser = rc.calculateFinalCost();
		double testEye = 174;
		assertEquals(testEye, finalCostForUser);
	}

	@Test
	public void getDiscountsTest() {
		double totalCostUser = 200;
		int userDinnerSelectedSize = 12;
		if (userDinnerSelectedSize > 5)
			totalCostUser += totalCostUser * 0.05;
		if (userDinnerSelectedSize > 10)
			totalCostUser += totalCostUser * 0.10;
		if (totalCostUser > 50)
			totalCostUser -= 10;
		if (totalCostUser > 100)
			totalCostUser -= 25;
		RestaurantCalculator rc = new RestaurantCalculator();
		double totalCostUserTest = 200;
		double resDiscount = rc.getDiscounts(totalCostUserTest, userDinnerSelectedSize);
		assertEquals(totalCostUser, resDiscount);
	}

	@Test
	public void getTotalCostTest() {
		int result = rc.getTotalCost();
		assertEquals(209, result);
	}

	@Test
	public void getAmountTotalTest() {
		int totalAmount = rc.getAmountTotal();
		int testValueAmountTotal = rc.getUserOrdersSelected().stream().map(element -> element.getAmount())
				.reduce((acu, element) -> acu + element).orElse(0);
		assertEquals(testValueAmountTotal, totalAmount);
	}

	@Test
	public void resetInventoryTest() {
		int resResetInventory = rc.resetInventory(4, 4);
		assertEquals(0, resResetInventory);
	}

	@Test
	public void changeOrderTest() {
		int resChangeOrder = rc.changeOrder("1 4");
		assertEquals(0, resChangeOrder);
	}

	// class ValidateInputAmount
	@Test
	public void validateInputAmountDinnerTest() {
		int resValitedateI1 = ValidatorInput.validateInputAmountDinner("3 2|2 1|3 4");
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
		ordersUser.add(new Order(3, 1));
		int resCheckOrderDinnerEmpty = ValidatorInput.checkUserOrderDinnerIsEmpty(ordersUser);
		assertEquals(resCheckOrderDinnerEmpty, 0);
	}

	@Test
	public void checkMaxAmountQuantity() {
		int resCheckMaxQuantity = ValidatorInput.checkMaxAmountQuantity(1000);
		assertEquals(-6, resCheckMaxQuantity);
	}
	
	// *********** NEW TEST FOR PATH COVERAGE  *************
	
	@Test
	public void mainClassTest() {
		Main main = new Main();
		assertEquals(main.getClass(), Main.class);
	}
	
	@Test
	public void validatorInputClassTest() {
		ValidatorInput validatorInput = new ValidatorInput();
		assertEquals(validatorInput.getClass(), ValidatorInput.class);
	}
	
	@Test
	public void validateInputAmountDinnerFailTest1() {
		int result = rc.addOrder("1 -1");
		assertEquals(result, -999);
	}
	
	@Test
	public void validateInputAmountDinnerFailTest2() {
		int result = rc.addOrder("3 3");
		assertEquals(result, -999);
	}
	
	@Test
	public void resetInventoryEmptyTest() {
		ArrayList<Integer> inventory = new ArrayList<>(Arrays.asList(50, 50, 50, 0, 50, 50));
		rc.setInventoryDinners(inventory);
		int result = rc.resetInventory(3, 10);
		assertEquals(-3, result);
	}
	
	@Test
	public void changeOrderFailTest() {
		rc.cancelOrder();
		int resChangeOrder = rc.changeOrder("10 4");
		assertEquals(-999, resChangeOrder);
	}
	
	@Test
	public void checkUserOrderDinnerIsEmptyCorrectTest() {
		ArrayList<Order> ordersUser = new ArrayList<>();
		int resCheckOrderDinnerEmpty = ValidatorInput.checkUserOrderDinnerIsEmpty(ordersUser);
		assertEquals(resCheckOrderDinnerEmpty, -5);
	}
	
	@Test
	public void calculateFinalCostFail6Test() {
		ArrayList<Order> ordersUser = new ArrayList<>();
		ordersUser.add(new Order(1, 2000));
		ordersUser.add(new Order(4, 1000));
		ordersUser.add(new Order(2, 4000));
		ordersUser.add(new Order(3, 100));
		rc.setUserOrdersSelected(ordersUser);
		double result = rc.calculateFinalCost();
		assertEquals(result, -6.00);
	}
	
	@Test
	public void calculateFinalCostFail5Test() {
		rc.cancelOrder();
		double result = rc.calculateFinalCost();
		assertEquals(result, -5.00);
	}
	
	@Test
	public void calculateFinalCostFail4Test() {
		ArrayList<Integer> inventory = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
		rc.setInventoryDinners(inventory);
		double result = rc.calculateFinalCost();
		assertEquals(result, -3.00);
	}
	
}
