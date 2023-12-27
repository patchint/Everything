package fr.patchli.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTime {

    public static String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

        String formattedDate = dateFormat.format(new Date());

        return formattedDate;
    }
}
