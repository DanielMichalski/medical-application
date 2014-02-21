package employees;

import junit.framework.Assert;
import org.junit.Test;
import work.Branch;
import work.Specialization;

/**
 * Author: Daniel Michalski
 * Date: 12.02.13
 */

public class AdminTest {

    public static final int EXPECTED_ID = 1;
    public static final String EXPECTED_FIRST_NAME = "Tomasz";
    public static final String EXPECTED_LAST_NAME = "Kowalski";
    public static final String EXPECTED_ADDRESS = "Konwaliowa 22";
    public static final String EXPECTED_PHONE_NO = "730483920";
    public static final String EXPECTED_EMAIL = "tomek_kow@onet.pl";
    public static final String EXPECTED_LOGIN = "tomek23";
    public static final String EXPECTED_PASSWORD = "tomek_abc";
    public static final AccountType EXPECTED_ACCOUNT_TYPE = AccountType.ADMIN;

    @Test
    public void testFirstConstructor() throws Exception {
        Admin admin = new Admin(
                EXPECTED_ID,
                EXPECTED_FIRST_NAME,
                EXPECTED_LAST_NAME,
                EXPECTED_ADDRESS,
                EXPECTED_PHONE_NO,
                EXPECTED_EMAIL,
                EXPECTED_LOGIN,
                EXPECTED_PASSWORD);

        Assert.assertEquals(EXPECTED_ID, (int)admin.getId());
        Assert.assertEquals(EXPECTED_FIRST_NAME, admin.getFirstName());
        Assert.assertEquals(EXPECTED_LAST_NAME, admin.getLastName());
        Assert.assertEquals(EXPECTED_ADDRESS, admin.getAddress());
        Assert.assertEquals(EXPECTED_PHONE_NO, admin.getPhoneNo());
        Assert.assertEquals(EXPECTED_EMAIL, admin.geteMail());
        Assert.assertEquals(EXPECTED_LOGIN, admin.getLogin());
        Assert.assertEquals(EXPECTED_PASSWORD, admin.getPassword());
    }

    @Test
    public void testSecondConstructor() throws Exception {
        Admin admin = new Admin(
                EXPECTED_FIRST_NAME,
                EXPECTED_LAST_NAME,
                EXPECTED_ADDRESS,
                EXPECTED_PHONE_NO,
                EXPECTED_EMAIL,
                EXPECTED_LOGIN,
                EXPECTED_PASSWORD);

        Assert.assertEquals(EXPECTED_FIRST_NAME, admin.getFirstName());
        Assert.assertEquals(EXPECTED_LAST_NAME, admin.getLastName());
        Assert.assertEquals(EXPECTED_ADDRESS, admin.getAddress());
        Assert.assertEquals(EXPECTED_PHONE_NO, admin.getPhoneNo());
        Assert.assertEquals(EXPECTED_EMAIL, admin.geteMail());
        Assert.assertEquals(EXPECTED_LOGIN, admin.getLogin());
        Assert.assertEquals(EXPECTED_PASSWORD, admin.getPassword());
    }

    @Test
    public void testNullObject() throws Exception {
        Admin admin = Admin.nullObject();

        Assert.assertEquals(
                null,
                admin.getId());

        Assert.assertEquals(
                Specialization.nullObject().getId(),
                admin.getSpecialization().getId());

        Assert.assertEquals(
                Branch.nullObject().getId(),
                admin.getBranch().getId());

        Assert.assertEquals(
                EXPECTED_ACCOUNT_TYPE,
                admin.getAccountType());
    }

    @Test
    public void testAccountType() throws Exception {
        Admin admin = new Admin(
                EXPECTED_FIRST_NAME,
                EXPECTED_LAST_NAME,
                EXPECTED_ADDRESS,
                EXPECTED_PHONE_NO,
                EXPECTED_EMAIL,
                EXPECTED_LOGIN,
                EXPECTED_PASSWORD);

        Assert.assertEquals(
                EXPECTED_ACCOUNT_TYPE,
                admin.getAccountType());
    }
}
