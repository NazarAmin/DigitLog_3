package com.example.digitlog;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.AxisValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Yasir on 02/06/16.
 */
public class HourAxisValueFormatter implements AxisValueFormatter
{

    private long referenceTimestamp; // minimum timestamp in your data set
    private DateFormat mDataFormat;
    private Date mDate;
    SimpleDateFormat sdf = new SimpleDateFormat("d/MMM HH:mm", Locale.ENGLISH);

    public HourAxisValueFormatter(long referenceTimestamp) {
        this.referenceTimestamp = referenceTimestamp;
        this.mDataFormat = new SimpleDateFormat("d/MMM HH:mm", Locale.ENGLISH);
        this.mDate = new Date();
    }

    /**
     * Called when a value from an axis is to be formatted
     * before being drawn. For performance reasons, avoid excessive calculations
     * and memory allocations inside this method.
     *
     * @param value the value to be formatted
     * @param axis  the axis the value belongs to
     * @return
     */
    @Override
    public String getFormattedValue(float value, AxisBase axis){
        long originalTimestamp = (long) value;

     //  long convertedTimestamp = originalTimestamp - referenceTimestamp;

        // Retrieve original timestamp
        //long originalTimestamp = convertedTimestamp - referenceTimestamp;

        // Convert timestamp to hour:minute
        return getHour(originalTimestamp);
    }

    @Override
    public int getDecimalDigits() {
        return 0;
    }

    private String getHour(long timestamp){
        try{
            mDate.setTime(timestamp);
            return mDataFormat.format(mDate);
        }
        catch(Exception ex){
            return "xx";
        }
    }
}