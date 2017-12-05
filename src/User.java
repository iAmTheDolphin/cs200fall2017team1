/*
 * Hunter James
 * 11/08/17
 * User Class
 */

/** 
 * This class holds all User data. It is able to construct a User, as well as set and get all data pertaining to a User.
 * @author Hunter James
 *
 */

public class User{

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

	/** Constructs a User
	 * 
	 * @param firstName
	 * @param lastName
	 * @param streetAddress
	 * @param city
	 * @param state
	 * @param zipCode
	 * @param email
	 * @param phoneNumber
	 * @param userID
	 */
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
	/**
 	* Accesses User's full name
 	* @return String
 	*/
	String getName() {
		return firstName + " " + lastName;
	}
	
	/**
	 * Accesses User's first name
	 * @return String
	 */
	String getFirstName() { return firstName; }
	
	/**
	 * Accesses User's last name
	 * @return String
	 */
	String getLastName() { return lastName; }

	/**
	 * Accesses User's street address
	 * @return String
	 */
	String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * Accesses User's city
	 * @return String
	 */
	String getCity() {
		return city;
	}

	/**
	 * Accesses User's state
	 * @return String
	 */
	String getState() {
		return state;
	}

	/**
	 * Accesses User's zip code
	 * @return String
	 */
	String getZipCode() {
		return zipCode;
	}

	/**
	 * Accesses User's email
	 * @return String
	 */
	String getEmail() {
		return email;
	}

	/**
	 * Accesses User's phone number
	 * @return String
	 */
	String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Accesses User's userID
	 * @return Int
	 */
	int getUserID() {
		return userID;
	}

	//endregion


	//region variable set methods
	/**
	 * Edits User's street address
	 * @return User
	 */
	User setStreetAddress(String newAddress) {
		streetAddress = newAddress;
		return this;
	}

	/**
	 * Edits User's city
	 * @return User
	 */
	User setCity(String newCity) {
		city = newCity;
		return this;
	}

	/**
	 * Edits User's email
	 * @return User
	 */
	User setEmail(String newEmail) {
		email = newEmail;
		return this;
	}

	/**
	 * Edits User's state
	 * @return User
	 */
	User setState(String newState) {
		state = newState;
		return this;
	}

	/**
	 * Edits User's zip code
	 * @return User
	 */
	User setZipCode(String newZip) {
		zipCode = newZip;
		return this;
	}

	/**
	 * Edits User's phone number
	 * @return User
	 */
	User setPhoneNumber(String newPhoneNum) {
		phoneNumber = newPhoneNum;
		return this;
	}

	/**
	 * Edits User's first name
	 * @return User
	 */
	User setFirstName(String newFirstName) {
		firstName = newFirstName;
		return this;
	}

	/**
	 * Edits User's last name
	 * @return User
	 */
	User setLastName(String newLastName) {
		firstName = newLastName;
		return this;
	}
//endregion

	/**
	 * Converts User data into a readable string containing all User info to use in .txt files
	 * @return String
	 */
	public String toString() {

		return firstName + " | " + lastName + " | " + streetAddress + " | " + city + " | " + state + " | " +
				zipCode + " | " + email + " | " + phoneNumber + " | " + userID;

	}

	/**
	 * Displays User data in easy-to-read display with logical spacing
	 * @return String
	 */
	public String toDisplayString() {

		return firstName + " " + lastName + " : " + userID + "\n" + streetAddress + ", " + city + ", " + state + " " + zipCode + "\n" + email + "\n" + phoneNumber + "\n";

	}



}
