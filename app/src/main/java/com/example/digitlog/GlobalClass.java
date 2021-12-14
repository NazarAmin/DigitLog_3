package com.example.digitlog;

import android.app.Activity;
import android.app.Application;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Date;

//import java.util.Map;

public class GlobalClass{

public static String engine_number;
public static String sheet_number;
public static String user_name_string;
public static String hsrg;
public static String Faults_Category;
public static String actual_user_name;
public static String engine_focal;
public static String current_engine_focal;
public static String general_admin = "Nazar Amin";
public static String database = "data";

public static long now = System.currentTimeMillis();
//public static Date end_date = new Date(now);

public static Date end_date = new Date(new Date(now).getTime() + (1000 * 60 * 60 * 24));

public static String date = "2021_06_18 00:00:00";
public static SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss", Locale.ENGLISH);

public static Date start_date;

static {
    try {
        start_date = formatter2.parse(date);
    } catch (ParseException e) {
        e.printStackTrace();
    }
}

public static String block_number;
public static ArrayList<String> chart_params;

/**
 *         LocalDate localDate = LocalDate.of(Integer.parseInt(2021), Integer.parseInt(5), Integer.parseInt(1));
 *         Date start_date = localDate.toDate();
 *
 *         LocalDate localDate2 = java.time.LocalDate.now();
 *         Date end_date = localDate2.toDate();
 */

}


