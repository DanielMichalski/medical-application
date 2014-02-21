package database.util;

import util.MyProgramLogger;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author Daniel Michalski
 */


/**
 * Klasa dzieki ktorej możemy hashowac haslo i przechowywac je w postaci zaszyfrowanego hasla metoda SHA1 w bazie danych
 */
public class Hash {
    private final static Logger LOGGER = MyProgramLogger.getMyLogger();
    private MessageDigest md;

    public Hash() {
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (Exception e) {
            LOGGER.warning(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Metoda zamieniajaca zwykly String na zaszyfrowany metodą SHA1
     *
     * @param inputString String ktory chcemy zaszyfrowac
     * @return zaszyfrowany String
     */
    public String HashString(String inputString) {
        md.update(inputString.getBytes());
        byte[] output = md.digest();
        return bytesToHex(output);
    }

    /**
     * Metoda zamieniajaca tablice bajtową na String
     *
     * @param b tablica bajtowa którą chcemy zamienić na String
     * @return String ktory został zamieniony z talbicy bajtowej
     */
    private String bytesToHex(byte[] b) {
        char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder buf = new StringBuilder();
        for (byte aB : b) {
            buf.append(hexDigit[(aB >> 4) & 0x0f]);
            buf.append(hexDigit[aB & 0x0f]);
        }
        return buf.toString();
    }
}
