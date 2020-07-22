package screenshot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {
    public static void main(String[] arg)
    {
        Date dNow = new Date( );
        SimpleDateFormat ft =new SimpleDateFormat ("yyyy-MM-dd-hh-mm-ss");

        System.out.println("Current Date: " + ft.format(dNow));
    }
}
