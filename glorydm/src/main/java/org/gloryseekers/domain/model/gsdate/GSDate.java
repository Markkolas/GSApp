package org.gloryseekers.domain.model.gsdate;

/**
 * The class GSDate represents a specific day in the Setting time
 */
public class GSDate {

    /**
     * The number of days represented by this object.
     */
    private long timeValue;

    /**
     * Allocates a GSDate objetct and initializes it so that it represents the day one of the year 1000.
     */
    public GSDate() {
        this.timeValue = 359640;//It indexes at 0 but the time not
    }

    private GSDate(long daysFromDayCero) {
        this.timeValue = daysFromDayCero;
    }

    /**
     * Obtains an instance of GSDate from a long value.
     * @param daysFromDayCero days from day cero.
     * @return an instance of GSDate.
     */
    public GSDate from(long daysFromDayCero) {
        return new GSDate(daysFromDayCero);
    }

    /**
     * Returns the number of days represented by this object.
     * @return the number of days represented by this object.
     */
    public long getGSDTime() {
        return this.timeValue;
    }

}