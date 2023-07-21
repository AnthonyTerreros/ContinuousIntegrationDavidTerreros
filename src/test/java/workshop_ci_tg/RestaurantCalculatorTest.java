package workshop_ci_tg;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class RestaurantCalculatorTest {

	@Test
	public void addOrderTest() {
		RestaurantCalculator rc = new RestaurantCalculator();
		ArrayList<Order> order = new ArrayList<>();
		int resAdd = rc.addOrder("3 4 | 4 2");
		assertEquals(0, resAdd);
	}
	
	@Test
	public void calculateFinalCostTest() {
		assertEquals(1, 1);
	}
	
	@Test
	public void getDiscountsTest() {
		assertEquals(1, 1);

	}
	
	@Test
	public void getTotalCostTest() {
		assertEquals(1, 1);
	}
	
	@Test
	public void getAmountTotalTest() {
		assertEquals(1, 1);
	}
	
	@Test
	public void resetInventoryTest() {
		assertEquals(1, 1);
	}
		
	@Test
	public void changeOrderTest() {
		assertEquals(1, 1);
	}
	

}
