package api.brainsynder.holidays;

import api.brainsynder.Core;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class HolidayEvents {
    private SimpleDateFormat format;
    private String month;
    public final Core instance;

    public HolidayEvents (String month) {
        this.month = month;
        instance = Core.get();
        format = new SimpleDateFormat("MM");
    }

    public boolean isMonth () {
        Date date = new Date();
        return format.format(date).equals(month);
    }

    public abstract void load ();
}
