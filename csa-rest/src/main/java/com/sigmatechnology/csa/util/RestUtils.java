package com.sigmatechnology.csa.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by lucianahaugen on 05/09/17.
 */
public class RestUtils {

    public static List<Integer> currentYears(){
        List<Integer> yearList = new ArrayList<>();
        int lastYear = GregorianCalendar.getInstance().get(Calendar.YEAR) -1;
        for(int i=0; i < 5; i++){
            yearList.add(lastYear+i);
        }
        return yearList;
    }
}
