package lab2_code;

import lab2_code.CarsHandler.QuerryType;
import lab2_code.edu.colorado.io.EasyReader;

public class InputReader {
    private QuerryType _querryType;
    private int _year;

    public QuerryType GetQuerryType() {
        return _querryType;
    }

    public int GetYear() {
        return _year;
    }

    // ctor, stup data form user
    public InputReader() {
        var easyReader = new EasyReader(System.in);

        System.out.println(" Podaj kryterium przegladania: ");
        System.out.println("    1. Najstarszy");
        System.out.println("    2. Nie starszy niz {ROK}");
        System.out.println("    3. Najmlodszy");
        System.out.println("    4. Nie mlodszy niz {ROK}");

        SetQuerryType(easyReader);
        SetYear(easyReader);
    }

    // set type of querrying
    private void SetQuerryType(EasyReader easyReader) {
        try {
            int choice = 0;
            while (true) {
                String input = easyReader.stringInputLine();
                choice = Integer.parseInt(input);
                if (choice > 4 || choice < 1) {
                    System.out.println(" Podane wejscie musi byc z zakresu 1 do 4");
                } else {
                    break;
                }
            }

            switch (choice) {
                case 1:
                    _querryType = QuerryType.OLDEST;
                    break;
                case 2:
                    _querryType = QuerryType.NOT_OLDER_THAN;
                    break;
                case 3:
                    _querryType = QuerryType.NEWEST;
                    break;
                case 4:
                    _querryType = QuerryType.NOT_NEWER_THAN;
                    break;
            }

            System.out.println("");

        } catch (Exception e) {
            throw new IllegalArgumentException(" Podane wejscie musi byc liczba calkowita z zakresu od 1 do 4");
        }

    }

    // Set year (if needed)
    private void SetYear(EasyReader easyReader) {
        if (_querryType == QuerryType.NEWEST || _querryType == QuerryType.OLDEST)
            return;

        System.out.println(" Podaj {ROK}: ");

        try {
            int choice = 0;
            String input = easyReader.stringInputLine();
            choice = Integer.parseInt(input);
            _year = choice;
            System.out.println("");

        } catch (Exception e) {
            throw new IllegalArgumentException(" Podane wejscie musi byc liczba calkowita");
        }

    }
}
