package lab2_code;

import java.util.ArrayList;
import java.util.List;

public class CarsHandler {

    public enum QuerryType {
        OLDEST,
        NOT_OLDER_THAN,
        NEWEST,
        NOT_NEWER_THAN
    }

    private List<Car> _cars;

    //ctor, sets data base
    public CarsHandler(int carsAmount, float cenaMin, float cenaMax, int rocznikMin, int rocznikMax) {
        _cars = new ArrayList<Car>();

        if (carsAmount < 1)
            throw new IllegalArgumentException("carsAmount must be greater than zero");

        for (int i = 0; i < carsAmount; i++) {
            _cars.add(new Car(cenaMin, cenaMax, rocznikMin, rocznikMax));
        }

    }

    // Prints all cars in data base
    public void PrintAllCars() {
        PrintCarsList(_cars);
    }

    // Gets cars by querry type, year param is used if needed
    public List<Car> GetQuerryCars(QuerryType querryType, int year) {
        switch (querryType) {
            case OLDEST:
                return GetOldestCars();
            case NOT_OLDER_THAN:
                return GetNotOlderThan(year);
            case NEWEST:
                return GetNewestCars();
            case NOT_NEWER_THAN:
                return GetNotNewerThan(year);

            default:
                return null;
        }
    }

    // Prints given cars list in console
    public void PrintCarsList(List<Car> list) {

        if (list == null || list.size() == 0)
            System.out.println("NONE");

        String res = "";
        for (Car sam : list) {
            res += sam + "\n";
        }
        System.out.println(res);
    }

    // returns cars list querried by: cars not older than {year}
    private List<Car> GetNotOlderThan(int year) {
        var temp = _cars;
        temp.removeIf(s -> s.GetRocznik() < year);
        return temp;
    }


    // returns cars list querried by: cars not newer than {year}
    private List<Car> GetNotNewerThan(int year) {
        var temp = _cars;
        temp.removeIf(s -> s.GetRocznik() > year);
        return temp;
    }

    
    // returns cars list querried by: gets newest cars
    private List<Car> GetNewestCars() {
        int year = _cars.get(0).GetRocznik();
        for (int i = 1; i < _cars.size(); i++) {
            if (_cars.get(i).GetRocznik() > year)
                year = _cars.get(i).GetRocznik();
        }

        final int tempYear = year;

        var temp = _cars;
        temp.removeIf(s -> s.GetRocznik() != tempYear);
        return temp;
    }

    
    // returns cars list querried by: gets oldest cars
    private List<Car> GetOldestCars() {
        int oldersYear = _cars.get(0).GetRocznik();
        for (int i = 1; i < _cars.size(); i++) {
            if (_cars.get(i).GetRocznik() < oldersYear)
                oldersYear = _cars.get(i).GetRocznik();
        }

        final int tempOld = oldersYear;

        var temp = _cars;
        temp.removeIf(s -> s.GetRocznik() != tempOld);
        return temp;
    }
}
