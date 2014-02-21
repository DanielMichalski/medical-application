package employees;

import junit.framework.Assert;
import org.junit.Test;
import work.Branch;

/**
 * Author: Daniel Michalski
 * Date: 12.02.13
 */

public class ReceptionistTest {
    public static final int EXPECTED_ID = 1;
    public static final String EXPECTED_FIRST_NAME = "Tomasz";
    public static final String EXPECTED_LAST_NAME = "Kowalski";
    public static final String EXPECTED_ADDRESS = "Konwaliowa 22";
    public static final String EXPECTED_PHONE_NO = "730483920";
    public static final String EXPECTED_EMAIL = "tomek_kow@onet.pl";
    public static final String EXPECTED_LOGIN = "tomek23";
    public static final String EXPECTED_PASSWORD = "tomek_abc";
    public static final Branch EXPECTED_BRANCH = new Branch(1, "Oddział dziecięcy");
    public static final AccountType EXPECTED_ACCOUNT_TYPE = AccountType.RECEPTIONIST;

    @Test
    public void testFirstConstructor() throws Exception {
        Receptionist receptionist = new Receptionist(
                EXPECTED_ID,
                EXPECTED_FIRST_NAME,
                EXPECTED_LAST_NAME,
                EXPECTED_ADDRESS,
                EXPECTED_PHONE_NO,
                EXPECTED_EMAIL,
                EXPECTED_LOGIN,
                EXPECTED_PASSWORD,
                EXPECTED_BRANCH);

        Assert.assertEquals(EXPECTED_ID, (int) receptionist.getId());
        Assert.assertEquals(EXPECTED_FIRST_NAME, receptionist.getFirstName());
        Assert.assertEquals(EXPECTED_LAST_NAME, receptionist.getLastName());
        Assert.assertEquals(EXPECTED_ADDRESS, receptionist.getAddress());
        Assert.assertEquals(EXPECTED_PHONE_NO, receptionist.getPhoneNo());
        Assert.assertEquals(EXPECTED_EMAIL, receptionist.geteMail());
        Assert.assertEquals(EXPECTED_LOGIN, receptionist.getLogin());
        Assert.assertEquals(EXPECTED_PASSWORD, receptionist.getPassword());
        Assert.assertEquals(EXPECTED_BRANCH, receptionist.getBranch());
    }

    @Test
    public void testSecondConstructor() throws Exception {
        Receptionist receptionist = new Receptionist(
                EXPECTED_FIRST_NAME,
                EXPECTED_LAST_NAME,
                EXPECTED_ADDRESS,
                EXPECTED_PHONE_NO,
                EXPECTED_EMAIL,
                EXPECTED_LOGIN,
                EXPECTED_PASSWORD,
                EXPECTED_BRANCH);

        Assert.assertEquals(EXPECTED_FIRST_NAME, receptionist.getFirstName());
        Assert.assertEquals(EXPECTED_LAST_NAME, receptionist.getLastName());
        Assert.assertEquals(EXPECTED_ADDRESS, receptionist.getAddress());
        Assert.assertEquals(EXPECTED_PHONE_NO, receptionist.getPhoneNo());
        Assert.assertEquals(EXPECTED_EMAIL, receptionist.geteMail());
        Assert.assertEquals(EXPECTED_LOGIN, receptionist.getLogin());
        Assert.assertEquals(EXPECTED_PASSWORD, receptionist.getPassword());
        Assert.assertEquals(EXPECTED_BRANCH, receptionist.getBranch());
    }

    @Test
    public void testNullObject() throws Exception {
        Receptionist receptionist =
                Receptionist.nullObject();

        Assert.assertEquals(
                null,
                receptionist.getId());

        Assert.assertEquals(
                Branch.nullObject().getId(),
                receptionist.getBranch().getId());

        Assert.assertEquals(
                EXPECTED_ACCOUNT_TYPE,
                receptionist.getAccountType());

    }

    @Test
    public void testAccountType() throws Exception {
        Receptionist receptionist = new Receptionist(
                EXPECTED_FIRST_NAME,
                EXPECTED_LAST_NAME,
                EXPECTED_ADDRESS,
                EXPECTED_PHONE_NO,
                EXPECTED_EMAIL,
                EXPECTED_LOGIN,
                EXPECTED_PASSWORD,
                EXPECTED_BRANCH);

        Assert.assertEquals(
                EXPECTED_ACCOUNT_TYPE,
                receptionist.getAccountType());
    }
}
