/*
 * Hunter James
 * 11/08/17
 * Provider Class
 */

/**
 * This class holds all Provider-specific data; inherits User class
 * @author Hunter James
 *
 */
public class Provider extends User {

	/**
	 * Constructs Provider
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
    public Provider(String firstName, String lastName, String streetAddress, String city, String state, String zipCode, String email, String phoneNumber, int userID) {

        super(firstName, lastName, streetAddress, city, state, zipCode, email, phoneNumber, userID);

    }
    /**
     * Allows the Provider data to be displayed via the display method in User class
     * @return String
     */
    public String toDisplayString() {
        return super.toDisplayString();
    }
}
