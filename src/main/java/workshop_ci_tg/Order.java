package workshop_ci_tg;

public class Order {

	private int idDinner;
	private int amount;

	public Order(int idDinner, int amount) {
		super();
		this.idDinner = idDinner;
		this.amount = amount;
	}
	
	
	public int getDinner() {
		return idDinner;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
