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
	private String phoneNumber;
	private int userID;

	public User() {

	}

	// construct a User
	public User(String firstName, String lastName, String streetAddress, String city, String state, String zipCode,String email, String phoneNumber, int userID) {

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

	String getFirstName() { return firstName; }

	String getLastName() { return lastName; }

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

	String getPhoneNumber() {
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

	User setCity(String newCity) {
		city = newCity;
		return this;
	}

	User setEmail(String newEmail) {
		email = newEmail;
		return this;
	}

	User setState(String newState) {
		state = newState;
		return this;
	}

	User setZipCode(String newZip) {
		zipCode = newZip;
		return this;
	}

	User setPhoneNumber(String newPhoneNum) {
		phoneNumber = newPhoneNum;
		return this;
	}

	User setFirstName(String newFirstName) {
		firstName = newFirstName;
		return this;
	}

	User setLastName(String newLastName) {
		firstName = newLastName;
		return this;
	}
//endregion

	public String toString() {

		return firstName + " | " + lastName + " | " + streetAddress + " | " + city + " | " + state + " | " +
				zipCode + " | " + email + " | " + phoneNumber + " | " + userID;

	}



}
