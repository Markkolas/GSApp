package org.gloryseekers.domain.model;

class GSDate {

    /**
     * The number of days since the day cero represented by this object.
     */
    private long daysFromDayCero;

    /**
     * Allocates a GSDate objetct and initializes it so that it represents the day one ohf the year 1000.
     */
    public GSDate(){
        this.daysFromDayCero = 1000;
    }

    private GSDate(long daysFromDayCero) {
        this.daysFromDayCero = daysFromDayCero;
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
     * Returns the number of days since the day cero represented by this object.
     * @return the number of days since the day cero.
     */
    public long getGSDTime() {
        return this.daysFromDayCero;
    }

}