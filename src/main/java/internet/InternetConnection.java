package internet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class InternetConnection {

    /**
     * Metoda sprawdzajaca polaczenie z internetem
     */
    public static void isReachable() throws NoInternetException {
        try {
            URL url = new URL("http://www.google.com");
            HttpURLConnection urlConnect = (HttpURLConnection) url.openConnection();

            //probuje pobrac dane ze zrodla. Jesli nie ma polaczenia, ta linia sie nie powiedzie
            urlConnect.getContent();
        } catch (IOException e) {
            throw new NoInternetException("Brak polaczenia z internetem");
        }
    }
}
