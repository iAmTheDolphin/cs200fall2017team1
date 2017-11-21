/*
Hunter James
11/08/17
Member Class
 */

import java.util.*;

public class Member extends User{
	boolean isSuspended;
	Date lastPayDate;

	// constructs a Member
	public Member(String firstName, String lastName, String streetAddress, String city, String state, String zipCode,String email, int phoneNumber, int userID) {
	    super(firstName, lastName, streetAddress, city, state, zipCode, email, phoneNumber, userID);
    }

	// when a Member is suspended
	void suspend() {
		isSuspended = true;
	}

	// when a Member is no longer suspended
	void setActive() {
		isSuspended = false;
	}

	// the last date a Member paid
	void setLastPayDate(Date date) {
		lastPayDate = date;
	}
}
