package org.gloryseekers.domain.model.gsdate;

/**
 * GSDateFormater is a class which formats and parses GSDates.
 */
public class GSDateFormater {

    private GSDateFormater() {

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
     * @param date The GSDate to be formated.
     * @return A string containing the formatted GSDate.
     */
    public String format(GSDate date) {
        return format(date, GSDateFormat.SHORT);
    }

    /**
     * Formats a GSDate into a string.
     * @param date The GSDate to be formated.
     * @param format The desired format.
     * @return A string containing the formatted GSDate.
     */
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

    /**
     * Returns an int that represents the year number of the GSDate.
     * @param date The GSDate.
     * @return int that represents the year number of the date.
     */
    public int getYear(GSDate date) {
        return (int) (date.getGSDTime() / 360) + 1; 
    }
    /**
     * Returns an int that represents the month number of the GSDate.
     * @param date The GSDate.
     * @return int that represents the month number of the date.
     */
    public int getMonth(GSDate date) {
        return (int) ((date.getGSDTime() % 360) / 30) + 1;
    }
    /**
     * Returns an int that represents the day number of the GSDate.
     * @param date The GSDate.
     * @return int that represents the day number of the date.
     */
    public int getDay(GSDate date) {
        return (int) ((date.getGSDTime() % 360) % 30) + 1;
    }

    /**
     * Returns a String with the name of the month corresponding to the given GSDate.
     * @param date The GSDate.
     * @return String with the name of the month corresponding to the given GSDate.
     */
    public String getMonthName(GSDate date) {
        switch (this.getMonth(date)) {
            case 1: 
                return "Ichigatsu";
            case 2:
                return "Nigatsu";
            case 3:
                return "Sangatsu";
            case 4:
                return "Yongatsu";
            case 5:
                return "Gogatsu";
            case 6:
                return "Rokugatsu";
            case 7:
                return "Sichigatsu";
            case 8:
                return "Hachigatsu";
            case 9:
                return "Kugatsu";
            case 10:
                return "Yugatsu";
            case 11:
                return "Yuichigatsu";
            case 12:
                return "Yunigatsu";
            default:
                return null;
        }
    }
}
