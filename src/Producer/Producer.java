package Producer;
import java.io.Serializable;
public class Producer  implements Serializable{
    private String producerCode;
    private String producerName;
    private String country;
	
    public Producer() {
        producerCode=null;
        producerName=null;
        country=null;
    }
    
	public Producer(String producerCode, String producerName, String country) {
		this.producerCode = producerCode;
		this.producerName = producerName;
		this.country = country;
	}

	public String getProducerCode() {
		return producerCode;
	}

	public void setProducerCode(String producerCode) {
		this.producerCode = producerCode;
	}

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}

