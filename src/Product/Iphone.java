package Product;

public class Iphone extends Product{
	public Iphone() {
		super();
	}
	@Override
	public String getProducerName() {
		super.setProducerName("Iphone");
		return super.getProducerName();
	}
}
