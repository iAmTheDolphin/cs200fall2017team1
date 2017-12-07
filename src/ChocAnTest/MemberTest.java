package ChocAnTest;

import static org.junit.Assert.*;

import ChocAn.Member;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests Member class
 * @author Riley Manning
 * @version 1.0
 */

public class MemberTest {

	Member member;
	
	/**
	 * Constructs/sets up member class
	 */
	
	@Before
	public void setUp() throws Exception {
		member = new Member("first", "last", "address", "city", "state", "zip", "email", "phonenum", 6565650);
	}

	/**
	 * Tests for success
	 */
	
	@Test
	public void testSuccess() {
		member.setSuspended(true);
	}
	
	/**
	 * Tests for failure
	 */
	
	@Test 
	public void testException() {
		member.setCity("Tuscaloosa");
		assertFalse("city" == member.getCity());
		}

	/**
	 * Tests for sanity
	 */
	
	@Test
	public void testSanity() {
		member.setSuspended(true);
		assertEquals(true, member.isSuspended);
	}
}
