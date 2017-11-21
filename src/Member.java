/*
Hunter James
11/08/17
Member Class
 */

import java.util.*;

public class Member extends User{
	boolean isSuspended;
	Date lastPayDate;

	public Member(String firstName, String lastName, String streetAddress, String city, String state, String zipCode,String email, int phoneNumber, int userID) {
	    super(firstName, lastName, streetAddress, city, state, zipCode, email, phoneNumber, userID);
    }

	
	void suspend() {
		isSuspended = true;
	}

	void setActive() {
		isSuspended = false;
	}

	void setLastPayDate(Date date) {
		lastPayDate = date;
	}
}
