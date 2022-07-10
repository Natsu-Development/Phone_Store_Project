package Product;

import java.util.List;

public class Xiaomi extends Product {
	public Xiaomi() {
		super();
	}
	
	@Override
	public String getProducerName() {
		super.setProducerName("Xiaomi");
		return super.getProducerName();
	}
}
