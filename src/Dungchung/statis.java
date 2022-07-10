package Dungchung;

import java.util.Comparator;

public class statis {
	private String name;
	private int amount;
	private long total;
	
	public statis(String name, int amount, long total) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.amount = amount;
		this.total = total;
	}
	
	//structor for product
	public statis(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}
	
	// structor for brand (conclusion)
	public statis(String name, long total) {
		this.name = name;
		this.total = total;
	}
	
	// for compare
	public static Comparator<statis> amountComparator = new Comparator<statis>() {
		@Override
		public int compare(statis o1, statis o2) {
			int amount1 = o1.getAmount();
			int amount2 = o2.getAmount();
			return amount2-amount1;
		}
	};

	public static Comparator<statis> totalComparator = new Comparator<statis>() {
		@Override
		public int compare(statis o1, statis o2) {
			long total1 = o1.getTotal();
			long total2 = o2.getTotal();
			return (int) (total2-total1);
		}
	};
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amonnt) {
		this.amount = amonnt;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}