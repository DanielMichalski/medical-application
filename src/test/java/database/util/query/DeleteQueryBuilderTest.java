package database.util.query;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Author: Daniel Michalski
 * Date: 05.04.13
 */

public class DeleteQueryBuilderTest {

    public static final String NAME_OF_TABLE = "nameOfTable";
    public static final String CONDITION = "x>1";

    public static final String EXPECTED_QUERY =
            "DELETE FROM " +
            NAME_OF_TABLE +
            " WHERE " +
            CONDITION;

    @Test
    public void testConstruct() throws Exception {
        String constructedQuery =
                DeleteQueryBuilder.construct(NAME_OF_TABLE, CONDITION);

        Assert.assertEquals(EXPECTED_QUERY, constructedQuery);
    }
}











