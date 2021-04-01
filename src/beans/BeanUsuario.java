package beans;

import java.io.Serializable;

public class BeanUsuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int uid;
	private String name;
	private String lastName;
	private String email;
	private String phoneNumber;
	
	public BeanUsuario() {
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
