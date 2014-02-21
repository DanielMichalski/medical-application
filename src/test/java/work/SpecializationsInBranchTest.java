package work;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Daniel Michalski
 * Date: 05.04.13
 */

public class SpecializationsInBranchTest {

    public static final int EXPECTED_ID = 1;
    public static final Branch EXPECTED_BRANCH = new Branch(1, "Oddział dziecięcy");
    public static final Specialization EXPECTED_ONE_SPECIALIZATION = new Specialization(1, "Ortopedia");
    public static final ArrayList<Specialization> EXPECTED_SPECIALIZATIONS = new ArrayList<Specialization>() {};

    @Test
    public void testFirstConstructor() throws Exception {
        SpecializationsInBranch specInBranch = new SpecializationsInBranch(
                EXPECTED_ID,
                EXPECTED_BRANCH,
                EXPECTED_SPECIALIZATIONS);

        Assert.assertEquals(EXPECTED_ID, (int)specInBranch.getId());
        Assert.assertEquals(EXPECTED_BRANCH, specInBranch.getBranch());
        Assert.assertEquals(EXPECTED_SPECIALIZATIONS, specInBranch.getSpecializations());
    }

    @Test
    public void testSecondConstructor() throws Exception {
        SpecializationsInBranch specInBranch = new SpecializationsInBranch(
                EXPECTED_BRANCH,
                EXPECTED_SPECIALIZATIONS);

        Assert.assertEquals(EXPECTED_BRANCH, specInBranch.getBranch());
        Assert.assertEquals(EXPECTED_SPECIALIZATIONS, specInBranch.getSpecializations());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testaddSpecialization() throws Exception {
        SpecializationsInBranch specializationsInBranch = new SpecializationsInBranch(
                EXPECTED_BRANCH,
                EXPECTED_SPECIALIZATIONS);

        specializationsInBranch.addSpecialization(EXPECTED_ONE_SPECIALIZATION);

        List<Specialization> specializations = specializationsInBranch.getSpecializations();

        Assert.assertEquals(EXPECTED_SPECIALIZATIONS, specializations.get(specializations.size()));
    }
}
