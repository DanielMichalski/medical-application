package employees;

import junit.framework.Assert;
import org.junit.Test;
import work.Branch;
import work.Specialization;

/**
 * Author: Daniel Michalski
 * Date: 12.02.13
 */

public class DoctorTest {
    public static final int EXPECTED_ID = 1;
    public static final String EXPECTED_FIRST_NAME = "Tomasz";
    public static final String EXPECTED_LAST_NAME = "Kowalski";
    public static final String EXPECTED_ADDRESS = "Konwaliowa 22";
    public static final String EXPECTED_PHONE_NO = "730483920";
    public static final String EXPECTED_EMAIL = "tomek_kow@onet.pl";
    public static final String EXPECTED_LOGIN = "tomek23";
    public static final String EXPECTED_PASSWORD = "tomek_abc";
    public static final Branch EXPECTED_BRANCH = new Branch(1, "Oddział dziecięcy");
    public static final Specialization EXPECTED_SPECIALIZATION = new Specialization(1, "Ortopedia");
    public static final AccountType EXPECTED_ACCOUNT_TYPE = AccountType.DOCTOR;

    @Test
    public void testFirstConstructor() throws Exception {
        Doctor doctor = new Doctor(
                EXPECTED_ID,
                EXPECTED_FIRST_NAME,
                EXPECTED_LAST_NAME,
                EXPECTED_ADDRESS,
                EXPECTED_PHONE_NO,
                EXPECTED_EMAIL,
                EXPECTED_LOGIN,
                EXPECTED_PASSWORD,
                EXPECTED_BRANCH,
                EXPECTED_SPECIALIZATION);

        Assert.assertEquals(EXPECTED_ID, (int) doctor.getId());
        Assert.assertEquals(EXPECTED_FIRST_NAME, doctor.getFirstName());
        Assert.assertEquals(EXPECTED_LAST_NAME, doctor.getLastName());
        Assert.assertEquals(EXPECTED_ADDRESS, doctor.getAddress());
        Assert.assertEquals(EXPECTED_PHONE_NO, doctor.getPhoneNo());
        Assert.assertEquals(EXPECTED_EMAIL, doctor.geteMail());
        Assert.assertEquals(EXPECTED_LOGIN, doctor.getLogin());
        Assert.assertEquals(EXPECTED_PASSWORD, doctor.getPassword());
        Assert.assertEquals(EXPECTED_BRANCH, doctor.getBranch());
        Assert.assertEquals(EXPECTED_SPECIALIZATION, doctor.getSpecialization());
    }

    @Test
    public void testSecondConstructor() throws Exception {
        Doctor doctor = new Doctor(
                EXPECTED_FIRST_NAME,
                EXPECTED_LAST_NAME,
                EXPECTED_ADDRESS,
                EXPECTED_PHONE_NO,
                EXPECTED_EMAIL,
                EXPECTED_LOGIN,
                EXPECTED_PASSWORD,
                EXPECTED_BRANCH,
                EXPECTED_SPECIALIZATION);

        Assert.assertEquals(EXPECTED_FIRST_NAME, doctor.getFirstName());
        Assert.assertEquals(EXPECTED_LAST_NAME, doctor.getLastName());
        Assert.assertEquals(EXPECTED_ADDRESS, doctor.getAddress());
        Assert.assertEquals(EXPECTED_PHONE_NO, doctor.getPhoneNo());
        Assert.assertEquals(EXPECTED_EMAIL, doctor.geteMail());
        Assert.assertEquals(EXPECTED_LOGIN, doctor.getLogin());
        Assert.assertEquals(EXPECTED_PASSWORD, doctor.getPassword());
        Assert.assertEquals(EXPECTED_BRANCH, doctor.getBranch());
        Assert.assertEquals(EXPECTED_SPECIALIZATION, doctor.getSpecialization());
    }

    @Test
    public void testNullObject() throws Exception {
        Doctor doctor = Doctor.nullObject();

        Assert.assertEquals(
                null,
                doctor.getId());

        Assert.assertEquals(
                Specialization.nullObject().getId(),
                doctor.getSpecialization().getId());

        Assert.assertEquals(
                Branch.nullObject().getId(),
                doctor.getBranch().getId());

        Assert.assertEquals(
                EXPECTED_ACCOUNT_TYPE,
                doctor.getAccountType());

    }

    @Test
    public void testAccountType() throws Exception {
        Doctor doctor = new Doctor(
                EXPECTED_FIRST_NAME,
                EXPECTED_LAST_NAME,
                EXPECTED_ADDRESS,
                EXPECTED_PHONE_NO,
                EXPECTED_EMAIL,
                EXPECTED_LOGIN,
                EXPECTED_PASSWORD,
                EXPECTED_BRANCH,
                EXPECTED_SPECIALIZATION);

        Assert.assertEquals(
                EXPECTED_ACCOUNT_TYPE,
                doctor.getAccountType());
    }
}
