package ChocAnTest;

import static org.junit.jupiter.api.Assertions.*;

import ChocAn.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import ChocAn.ServiceRecord;
import ChocAn.*;

/**
 * Tests the Member Report class
 * @author Parker Jones
 *
 */

class MemberReportTest {

	MemberReport report;
	Member member;
	
	/**
	 * this adds some dummy service Records and a dummy member. then it creates a new member report with that member
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		member = new Member("Member", "Name", "address", "city", "state", "zip", "email", "phonenum", 6565650);
		DatabaseController.addServiceRecord(new ServiceRecord(999999999, 100000001, "Provider Name", "Member Name", "notes", new Date(), new Date(), new ServiceCode("name", 100000, 1.00 )));
		report = new MemberReport(member);
	}

	/**
	 * this tests to make sure that the member on record is the dummy member we set up
	 */
	@Test
	void testForSuccess() {
		assertTrue(report.member.equals(member));
	}
	
	/**
	 * this tests to make sure the date on the report is not the wrong date
	 */
	@Test
	void testForFailure() {
		assertFalse(report.servDate == new Date(0));
	}
	
	/**
	 * this makes sure that the member is assigned to the report then when a new report is generated with a different
	 * member, the other member is the one attatched.
	 */
	@Test
	void testForSanity() {
		assertTrue(report.member.equals(member));
		Member member2 = new Member("Second", "Member", "address", "city", "state", "zip", "email", "phonenum", 100020010);
		report = new MemberReport(member2);
		assertFalse(report.member.equals(member));
		
	}
	
	
	

}
