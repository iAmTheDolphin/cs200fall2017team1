package ChocAnTest;

import static org.junit.Assert.*;

import ChocAn.Member;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests Member class
 * @author Riley Manning
 *
 */

public class MemberTest {

	Member member;
	
	@Before
	public void setUp() throws Exception {
		member = new Member("first", "last", "address", "city", "state", "zip", "email", "phonenum", 6565650);
	}

	@Test
	public void testSuccess() {
		member.setSuspended(true);
	}
	
	@Test 
	public void testException() {
		member.setCity("Tuscaloosa");
		assertFalse("city" == member.getCity());
		}

	@Test
	public void testSanity() {
		member.setSuspended(true);
		assertEquals(true, member.isSuspended);
	}
}