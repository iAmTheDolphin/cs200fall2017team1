public class Member extends Provider {
	bool isSuspended;
	Date lastPayDate;
	
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
