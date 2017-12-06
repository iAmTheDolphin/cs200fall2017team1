package ChocAnTest;

import static org.junit.Assert.*;

import ChocAn.DatabaseController;
import ChocAn.Member;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests Database Controller method
 * @author Aislinn
 *
 */
public class DatabaseControllerTest {

    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void test1() {
        Member x = DatabaseController.newMember("A","B","C","D","E","F","G","H");
        Member y = DatabaseController.newMember("Z", "Y", "X", "W", "V", "U", "T", "S");
        assertNotEquals(x.getUserID(),y.getUserID());

    }

    @Test
    public void test2() {
        Member x = DatabaseController.newMember("A","B","C","D","E","F","G","H");
        Member y = DatabaseController.newMember("Z", "Y", "X", "W", "V", "U", "T", "S");
        DatabaseController.deleteMember(x.getUserID());
        assertNull(x.getUserID());
    }

    @Test
    public void test3() {
        Member x = DatabaseController.newMember("A","B","C","D","E","F","G","H");
        Member y = DatabaseController.newMember("Z", "Y", "X", "W", "V", "U", "T", "S");
        DatabaseController.updateMemberFirstName(y.getUserID(), "A");
        assertEquals(x.getFirstName(),y.getFirstName());

    }

}

