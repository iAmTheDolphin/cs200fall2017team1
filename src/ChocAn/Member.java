package ChocAn;/*
 * Hunter James
 * 11/08/17
 * Member Class
 */

import java.util.*;

/**
 * This class contains all data specific to Members; inherits the User class.
 * @author Hunter James
 */

public class Member extends User{
	public boolean isSuspended = false;
	Date lastPayDate;

	// constructs a Member
	/**
	 * Constructs a Member
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
	public Member(String firstName, String lastName, String streetAddress, String city, String state, String zipCode,String email, String phoneNumber, int userID) {
	    super(firstName, lastName, streetAddress, city, state, zipCode, email, phoneNumber, userID);
    }

	/**
	 * Suspends Member
	 * 
	 */
	void suspend() {
		System.out.println("Suspending " + getName() + "...");
		isSuspended = true;
	}

	/**
	 * Reactivates Member when they are no longer suspended
	 */
	void setActive() {
		isSuspended = false;
	}

	/**
	 * Sets User's suspension status
	 * 
	 */
	public void setSuspended(boolean setting)  {
		isSuspended = setting;
	}

	/**
	 * Sets last time Member paid
	 */
	void setLastPayDate(Date date) {
		lastPayDate = date;
	}

	/**
	 * Adds Member's suspension status to the readable string
	 * @return String
	 */
	public String toString() {

		String suspendedState = "Active";

		if(isSuspended) {
			suspendedState = "Suspended";
		}

		return super.toString() + " | " + suspendedState + ";";

	}

	/**
	 * Adds Member's suspension status to the displayed Member data string
	 * @return String
	 */
	public String toDisplayString() {
		String suspendedState = "ACTIVE : ";

		if(isSuspended) {
			suspendedState = "SUSPENDED : ";
		}

		return suspendedState + super.toDisplayString();
	}
}
