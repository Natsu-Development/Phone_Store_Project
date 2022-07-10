package Dungchung;
import java.io.Serializable;
public abstract class person implements Serializable {
    private String name;
    private String numberPhone;
    private String address;
    public person(){
        name=null;
        numberPhone=null;
        address=null;
    }
    
    public person(String name, String numberPhone, String address) {
		this.name = name;
		this.numberPhone = numberPhone;
		this.address = address;
	}

	public abstract void display();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
    
}
