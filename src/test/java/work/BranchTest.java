package work;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Author: Daniel Michalski
 * Date: 05.04.13
 */

public class BranchTest {

    public static final int EXPECTED_BANCH_ID = 1;
    public static final String EXPECTED_BRANCH_NAME = "Oddział dziecięcy";

    @Test
    public void testConstructor() throws Exception {
        Branch branch = new Branch(
                EXPECTED_BANCH_ID,
                EXPECTED_BRANCH_NAME);

        Assert.assertEquals(EXPECTED_BANCH_ID, (int)branch.getId());
        Assert.assertEquals(EXPECTED_BRANCH_NAME, branch.getName());
    }

    @Test
    public void testNullObject() throws Exception {
        Branch branch = Branch.nullObject();

        Assert.assertEquals(0, (int)branch.getId());
        Assert.assertEquals("brak", branch.getName());
    }
}
