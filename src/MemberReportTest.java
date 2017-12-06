import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

class MemberReportTest {

	MemberReport report;
	Member member;
	
	@BeforeEach
	void setUp() throws Exception {
		member = new Member("Member", "Name", "address", "city", "state", "zip", "email", "phonenum", 6565650);
		DatabaseController.addServiceRecord(new ServiceRecord(999999999, 100000001, "Provider Name", "Member Name", "notes", new Date(), new Date(), new ServiceCode("name", 100000, 1.00 )));
		report = new MemberReport(member);
	}

	@Test
	void testForSuccess() {
		assertTrue(report.member.equals(member));
	}
	
	@Test
	void testForFailure() {
		assertFalse(report.servDate == new Date(0));
	}
	
	@Test
	void testForSanity() {
		assertTrue(report.member.equals(member));
		Member member2 = new Member("Second", "Member", "address", "city", "state", "zip", "email", "phonenum", 100020010);
		report = new MemberReport(member2);
		assertFalse(report.member.equals(member));
		
	}
	
	
	

}
