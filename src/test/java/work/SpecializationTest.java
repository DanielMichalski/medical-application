package work;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Author: Daniel Michalski
 * Date: 05.04.13
 */

public class SpecializationTest {

    public static final int EXPECTED_SPECIALIZATION_ID = 1;
    public static final String EXPECTED_SPECIALIZATION_NAME = "Ortopedia";

    @Test
    public void testConstructor() throws Exception {
        Specialization specialization = new Specialization(
                EXPECTED_SPECIALIZATION_ID,
                EXPECTED_SPECIALIZATION_NAME);

        Assert.assertEquals(EXPECTED_SPECIALIZATION_ID, (int)specialization.getId());
        Assert.assertEquals(EXPECTED_SPECIALIZATION_NAME, specialization.getName());
    }

    @Test
    public void testNullObject() throws Exception {
        Specialization specialization = Specialization.nullObject();

        Assert.assertEquals(0, (int)specialization.getId());
        Assert.assertEquals("brak", specialization.getName());
    }
}
