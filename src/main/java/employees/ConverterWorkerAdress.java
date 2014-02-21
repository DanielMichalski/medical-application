package employees;

import util.Const;
import util.Worker;

public class ConverterWorkerAdress {

    public static String getFullAddress(Worker worker) {
        String[] splitAddress = getSplitAddressInTable(worker);
        return splitAddress[0] + " " + splitAddress[1] + ",  " + splitAddress[2];
    }

    // index [0] kod pocztowy np 20-436
    // index [1] miejscowosc np Lublin
    // index [2] adres np Władysława 135
    public static String[] getSplitAddressInTable(Worker worker) {
        //przyklad adresu w bazie (20-436;Lublin;Władysława 135)
        String[] splitAddress = worker.getAddress().split(Const.Separators.DATA);
        if (splitAddress.length != 3) {
            splitAddress = new String[3];
            splitAddress[0] = "";
            splitAddress[1] = "";
            splitAddress[2] = worker.getAddress();
        }
        return splitAddress;
    }

    public static String getAddressForPatientObj(String zipCode, String locality, String address) {
        return zipCode + Const.Separators.DATA + locality + Const.Separators.DATA + address;
    }

}