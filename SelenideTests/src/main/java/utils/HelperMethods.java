package utils;

import java.util.ArrayList;

public class HelperMethods {

 public static boolean checkValuesInRange(int min, int max, ArrayList<Integer> list){

     Boolean result = true;

    for (int i : list){
        if (i < min || i > max || max < min){
            result = false;
            break;
        }
    } return result;
 }
}
