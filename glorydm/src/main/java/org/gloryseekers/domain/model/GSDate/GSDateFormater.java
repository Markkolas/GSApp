package org.gloryseekers.domain.model.GSDate;

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
     * @param date
     * @return
     * 
     * Better solution: Implement toString() method on GSDate
     */
    public String format(GSDate date) {
        return format(date);
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
    }

    private String formatShort(GSDate date) {
        return getDay(date) + " " + getMonth(date) + " " + getYear(date);
    }

    private String formatMedium(GSDate date) {
        return null;
    }

    private String formatLong(GSDate date) {
        return null;
    }

    private int getYear(GSDate date) {
        return (int) (date.getGSDTime() / 360); 
    }

    private int getMonth(GSDate date) {
        return (int) ((date.getGSDTime() % 360) / 30);
    }

    private int getDay(GSDate date) {
        return (int) ((date.getGSDTime() % 360) % 30);
    }

}
