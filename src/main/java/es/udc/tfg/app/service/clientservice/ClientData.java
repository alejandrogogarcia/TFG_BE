package es.udc.tfg.app.service.clientservice;

import java.util.Calendar;

public class ClientData {

	private String firstName;

	private String lastName;

	private String dni;

	private String address;

	private String city;

	private Long postCode;

	private String province;

	private String email;

	private Long phoneNumber;

	@SuppressWarnings("unused")
	private Calendar modifyDate;

	private String tax;

	public ClientData() {
	}

	public ClientData(String firstName, String lastName, String dni, String address, String city, Long postCode,
			String province, String email, Long phoneNumber, String tax) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.address = address;
		this.city = city;
		this.postCode = postCode;
		this.province = province;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.modifyDate = Calendar.getInstance();
		this.tax = tax;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getPostCode() {
		return postCode;
	}

	public void setPostCode(Long postCode) {
		this.postCode = postCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

}
