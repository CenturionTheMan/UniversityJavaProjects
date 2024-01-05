package lab2_code;

import java.text.DecimalFormat;
import java.util.Random;

public class Car {
    public enum Marki {
        POLONEZ,
        FIAT,
        SYRENA;
    }

    private Marki _marka;

    public Marki GetMarka() {
        return _marka;
    }

    private float _cena;

    public float GetCena() {
        return _cena;
    }

    private int _rocznik;

    public int GetRocznik() {
        return _rocznik;
    }


    //ctor
    public Car(float cenaMin, float cenaMax, int rocznikMin, int rocznikMax) {

        var rand = new Random();
        _cena = rand.nextFloat(cenaMin, cenaMax);
        _rocznik = rand.nextInt(rocznikMin, rocznikMax);

        switch (rand.nextInt(0, 3)) {
            case 0:
                _marka = Marki.FIAT;
                break;
            case 1:
                _marka = Marki.POLONEZ;
                break;
            case 2:
                _marka = Marki.SYRENA;
                break;
            default:
                throw new IllegalArgumentException("Invalid value for marka");
        }
    }

    // custom class to str
    @Override
    public String toString() {
        return "Marka: " + GetMarka() + " | Rocznik: " + _rocznik + " | Cena: "
                + new DecimalFormat("0.00").format(_cena);
    }
}
