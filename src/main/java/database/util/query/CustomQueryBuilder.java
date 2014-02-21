package database.util.query;

import java.util.Arrays;
import java.util.List;

public class CustomQueryBuilder {

    /**
     * metoda pomaga zoptymalizowac laczenie stringow
     *
     * @param query - String... oznacza że liczba parametrow jest nie okreslona tzn mozna podawac 1 i wiecej
     */
    public static String construct(String... query) {
        List<String> queryElements = Arrays.asList(query);
        if (queryElements == null || queryElements.isEmpty()) {
            return "";
        }

        StringBuilder customQuery = new StringBuilder();
        for (String element : queryElements) {
            customQuery.append(element);
        }

        return customQuery.toString();
    }

}