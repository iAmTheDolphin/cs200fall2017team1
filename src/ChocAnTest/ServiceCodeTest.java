package ChocAnTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ChocAn.ServiceCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Tests Service Code class
 * @author Hunter James
 *
 */

class ServiceCodeTest {
	ServiceCode serviceCode;
//	String name = "Nutritionist";
//	int code = 999999;
//	double fee = 100.00;
	
	
	@BeforeEach
	void setUp() throws Exception {
		serviceCode = new ServiceCode();  
	}

	@Test
	void testForSuccess() {
		serviceCode.serviceName = "Nutritionist";
		assertEquals("Nutritionist", serviceCode.serviceName);
	}

	@Test
	void testForFailure() {
		serviceCode.serviceCode = 999999;
		String toString = serviceCode.toString();
		assertFalse("-1 | -1 | -1.00" == toString);
	}

	@Test
	void testForSanity() {
		serviceCode.serviceFee = 100.00;
		serviceCode = new ServiceCode();
		assertTrue(serviceCode.serviceFee == -1);
	}
}
