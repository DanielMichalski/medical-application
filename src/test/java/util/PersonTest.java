package util;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Author: Daniel Michalski
 * Date: 05.04.13
 */

public class PersonTest {

    public static final int EXPECTED_ID = 1;
    public static final String EXPECTED_FIRST_NAME = "Tomasz";
    public static final String EXPECTED_LAST_NAME = "Kowalski";
    public static final String EXPECTED_ADDRESS = "Sezamowa 22";
    public static final String EXPECTED_PHONE_NO = "887383827";
    public static final String EXPECTED_MAIL = "tomasz_kowalski@wp.pl";

    @Test
    public void testFirstConstructor() throws Exception {
        Person person = new Person(
                EXPECTED_ID,
                EXPECTED_FIRST_NAME,
                EXPECTED_LAST_NAME,
                EXPECTED_ADDRESS,
                EXPECTED_PHONE_NO,
                EXPECTED_MAIL);

        Assert.assertEquals(EXPECTED_ID, (int)person.getId());
        Assert.assertEquals(EXPECTED_FIRST_NAME, person.getFirstName());
        Assert.assertEquals(EXPECTED_LAST_NAME, person.getLastName());
        Assert.assertEquals(EXPECTED_ADDRESS, person.getAddress());
        Assert.assertEquals(EXPECTED_PHONE_NO, person.getPhoneNo());
        Assert.assertEquals(EXPECTED_MAIL, person.geteMail());
    }

    @Test
    public void testSecondConstructor() throws Exception {
        Person person = new Person(
                EXPECTED_FIRST_NAME,
                EXPECTED_LAST_NAME,
                EXPECTED_ADDRESS,
                EXPECTED_PHONE_NO,
                EXPECTED_MAIL);

        Assert.assertEquals(EXPECTED_FIRST_NAME, person.getFirstName());
        Assert.assertEquals(EXPECTED_LAST_NAME, person.getLastName());
        Assert.assertEquals(EXPECTED_ADDRESS, person.getAddress());
        Assert.assertEquals(EXPECTED_PHONE_NO, person.getPhoneNo());
        Assert.assertEquals(EXPECTED_MAIL, person.geteMail());
    }
}
