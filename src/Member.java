/*
 * Hunter James
 * 11/08/17
 * Member Class
 */

import java.util.*;

public class Member extends User{
	boolean isSuspended = false;
	Date lastPayDate;

	// constructs a Member
	public Member(String firstName, String lastName, String streetAddress, String city, String state, String zipCode,String email, String phoneNumber, int userID) {
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

	void setSuspended(boolean setting)  {
		isSuspended = setting;
	}

	// the last date a Member paid
	void setLastPayDate(Date date) {
		lastPayDate = date;
	}

	public String toString() {

		String suspendedState = "Active";

		if(isSuspended) {
			suspendedState = "Suspended";
		}

		return super.toString() + " | " + suspendedState + ";";

	}
}
