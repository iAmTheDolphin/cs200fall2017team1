/*
Hunter James
11/08/17
User Class
 */

public class User {

	private String firstName;
	private String lastName;
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String email;
	private int phoneNumber;
	private int userID;

	public User() {

	}

	// construct a User
	public User(String firstName, String lastName, String streetAddress, String city, String state, String zipCode,String email, int phoneNumber, int userID) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userID = userID;

	}


	//region variable access methods
	String getName() {
		return firstName + " " + lastName;
	}

	String getStreetAddress() {
		return streetAddress;
	}

	String getCity() {
		return city;
	}

	String getState() {
		return state;
	}

	String getZipCode() {
		return zipCode;
	}

	String getEmail() {
		return email;
	}

	int getPhoneNumber() {
		return phoneNumber;
	}

	int getUserID() {
		return userID;
	}

	//endregion


	//region variable set methods
	String setStreetAddress(String newAddress) {
		streetAddress = newAddress;
		return streetAddress;
	}

	String setCity(String newCity) {
		city = newCity;
		return city;
	}

	String setEmail(String newEmail) {
		email = newEmail;
		return email;
	}

	String setState(String newState) {
		state = newState;
		return state;
	}

	String setZipCode(String newZip) {
		zipCode = newZip;
		return zipCode;
	}

	int setPhoneNumber(int newPhoneNum) {
		phoneNumber = newPhoneNum;
		return phoneNumber;
	}
//endregion



}
