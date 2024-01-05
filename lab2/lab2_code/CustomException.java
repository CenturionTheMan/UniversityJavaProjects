package lab2_code;

import java.util.List;

public class CustomException extends Exception {


    public CustomException(List<Car> cars) throws Exception {
        String res = "";
        
        if(cars == null || cars.size() == 0)
        {
            throw new Exception("\n There is no car for this querry");
        }

        for (int i = 0; i < cars.size(); i++) {
            res += cars.get(i).toString() + "\n";
        }

        throw new Exception("\n" + res);
    }
}
