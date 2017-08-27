package cc.comac.util;

import java.util.ArrayList;
import java.util.Arrays;

public class Functions {

    public static double getDoubleArrayMin(Double[] array) {
        ArrayList<Double> arrayList=new ArrayList<>(Arrays.asList(array));
        arrayList.sort(null);
        return arrayList.get(0);
    }
    
    public static double getDoubleArrayMax(Double[] array) {
        ArrayList<Double> arrayList=new ArrayList<>(Arrays.asList(array));
        arrayList.sort(null);
        return arrayList.get(arrayList.size()-1);
    }
}
