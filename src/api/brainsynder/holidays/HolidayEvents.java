package api.brainsynder.holidays;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class HolidayEvents {
    private SimpleDateFormat format;
    private String month;

    public HolidayEvents (String month) {
        this.month = month;
        format = new SimpleDateFormat("MM");
    }

    public boolean isMonth () {
        Date date = new Date();
        return format.format(date).equals(month);
    }
}
