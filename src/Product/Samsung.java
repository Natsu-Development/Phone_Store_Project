package Product;

public class Samsung extends Product {
	public Samsung() {
		super();
	}
	@Override
	public String getProducerName() {
		super.setProducerName("Samsung");
		return super.getProducerName();
	}
}
