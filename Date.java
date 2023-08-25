public class Date {
    private int day;
    private int month;
    private int year;
    private static final int DEFAULT_YEAR=2000,  DEFAULT_MONTH=1,  DEFAULT_DAY=1;

    /**
     *  default constructor - sets month to 1, day to 1 and year to 2000 (the defaults)
     */
    public Date( )   {
        setDate( DEFAULT_MONTH, DEFAULT_DAY, DEFAULT_YEAR);
    }

    /**
     *  non-default constructor - passes parameters to setDate method
     *  @param mm    initial value for month
     *  @param dd    initial value for day
     *  @param yyyy  initial value for year
     */
    public Date( int mm, int dd, int yyyy )   {
        setDate( mm, dd, yyyy );
    }

    public Date( Date newD )   {
        if (newD!=null) setDate( newD.getMonth(), newD.getDay(), newD.getYear() );
        else setDate( DEFAULT_MONTH, DEFAULT_DAY, DEFAULT_YEAR);
    }
    /**
     *  accessor method - returns the month
     *  @return the integer month
     */
    public int getMonth( ) { return month; }

    /**
     *  accessor method - returns the day
     *  @return the integer day
     */
    public int getDay( )   { return day; }

    /**
     *  accessor method - returns the year
     *  @return the integer year
     */
    public int getYear( )  { return year; }

    /**
     *  mutator method - passes parameters to private setMonth, setDay, and setYear for validation and setting
     *  @param mm    new value for month
     *  @param dd    new value for day
     *  @param yyyy  new value for year
     */
    public void setDate( int mm, int dd, int yyyy )   {
        setYear(yyyy);
        setMonth( mm );
        setDay( dd );
    }

    /**
     *  private helper method - if dd is legal day for current month, sets day to dd, otherwise, sets day to 1 (default)
     *  not leap year aware
     *  @param dd new value for day
     */
    private void setDay( int dd )   {
        int [] validDays = { 0,  31, 29, 31, 30,
                31, 30, 31, 31, 30,
                31, 30, 31 };
        if (dd >= 1 && dd <= validDays[month] ) {
            day = dd;
        }
        else {
            day = DEFAULT_DAY;
        }
    }
    /**
     *  private helper method - if mm is between 1 and 12, sets month to mm, otherwise, sets month to 1 (default)
     *  @param mm new value for month
     */
    private void setMonth( int mm ) {
        if (mm >= 1 && mm <= 12) {
            month = mm;
        }
        else {
            month = DEFAULT_MONTH;
        }
    }
    /**
     *  private helper method - sets year to yyyy, no validation
     *  @param yyyy new value for year
     */
    private void setYear( int yyyy ) {
        year = yyyy;
    }

    /**
     *  returns date in mm/dd/yyyy format
     *  @return String
     */
    public String toString( ) {
        return month + "/" + day + "/" + year;
    }

    /** equals
     *  @param   d  Date object to compare to this
     *  @return  true if d is equal to this
     *           false, otherwise
     */
    public boolean equals( Date d ) {
        if ( month == d.month
                && day == d.day
                && year == d.year )
            return true;
        else
            return false;
    }

    public int compareTo(Date that){    // improvement would to return the number of days between, like String returns the difference in ASCII values
        if (this.year<that.year) return -1;
        else if (this.year>that.year) return 1;
        else {
            if (this.month<that.month) return -1;
            else if (this.month>that.month) return 1;
            else {
                if (this.day<that.day) return -1;
                else if (this.day>that.day) return 1;
                else return 0;
            }
        }
    }
}