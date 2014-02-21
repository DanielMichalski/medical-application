package patient;

import util.Const;

public class ConverterPatientAddress {

    public static String getFullAddress(PatientCard patientCard) {
        String[] splitAddress = getSplitAddressInTable(patientCard);
        return splitAddress[0] + " " + splitAddress[1] + ",  " + splitAddress[2];
    }

    // index [0] kod pocztowy np 20-436
    // index [1] miejscowosc np Lublin
    // index [2] adres np Władysława 135
    public static String[] getSplitAddressInTable(PatientCard patientCard) {
        //przyklad adresu w bazie (20-436;Lublin;Władysława 135)
        String[] splitAddress = patientCard.getAddress().split(Const.Separators.DATA);
        if (splitAddress.length != 3) {
            splitAddress = new String[3];
            splitAddress[0] = "";
            splitAddress[1] = "";
            splitAddress[2] = patientCard.getAddress();
        }
        return splitAddress;
    }

    public static String getAddressForPatientObj(String zipCode, String locality, String address) {
        return zipCode + Const.Separators.DATA + locality + Const.Separators.DATA + address;
    }

}