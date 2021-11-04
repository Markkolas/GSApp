package org.gloryseekers.domain.model.gsdate;

/**
 * GSDateFormater is a class which formats and parses GSDates.
 */
public class GSDateFormater {

    public GSDateFormater() {

    }

    // This singleton implementation avoids multithreading problems.
    private static class LazyHolder {
        static final GSDateFormater INSTANCE = new GSDateFormater();
    }

    /**
     * Gets the date formatter instance.
     * 
     * @return Gets the date formatter instance.
     */
    public static GSDateFormater getDateInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * Formats a GSDate into a string.
     * @param date
     * @return
     */
    public String format(GSDate date) {
        return format(date, GSDateFormat.SHORT);
    }

    public String format(GSDate date, GSDateFormat format) {
        switch(format) {
            case SHORT:
                return formatShort(date);
            case MEDIUM:
                return formatMedium(date);
            case LONG: 
                return formatLong(date);
        }
        return null;
    }

    private String formatShort(GSDate date) {
        return getDay(date) + "/" + getMonth(date) + "/" + getYear(date);
    }

    private String formatMedium(GSDate date) {
        return getMonthName(date) + ", " + getDay(date) + ", " + getYear(date);
    }

    private String formatLong(GSDate date) {
        return null;
    }

    public int getYear(GSDate date) {
        return (int) (date.getGSDTime() / 360) + 1; 
    }

    public int getMonth(GSDate date) {
        return (int) ((date.getGSDTime() % 360) / 30) + 1;
    }

    public int getDay(GSDate date) {
        return (int) ((date.getGSDTime() % 360) % 30) + 1;
    }

    public String getMonthName(GSDate date) {
        switch (this.getMonth(date)) {
            case 1: 
                return "ichigatsu";
            case 2:
                return "nigatsu";
            case 3:
                return "sangatsu";
            case 4:
                return "yongatsu";
            case 5:
                return "gogatsu";
            case 6:
                return "rokugatsu";
            case 7:
                return "sichigatsu";
            case 8:
                return "hachigatsu";
            case 9:
                return "kugatsu";
            case 10:
                return "yugatsu";
            case 11:
                return "yuichigatsu";
            case 12:
                return "yunigatsu";
            default:
                return null;
        }
    }
}
