package utils;

import java.util.ArrayList;

public class HelperMethods {

 public static boolean checkValuesInRange(int min, int max, ArrayList<Integer> list){

    for (int i : list){
        if (i < min || i > max){
            return false;
        }
    } return true;
 }
}
