namespace PackageDeliverySystem
{ 
    class Date
    {
        private int day;
        private int month;
        private int year;
        private static int DEFAULT_YEAR = 2000, DEFAULT_MONTH = 1, DEFAULT_DAY = 1;

        public Date()
        {
            setDate(DEFAULT_MONTH, DEFAULT_DAY, DEFAULT_YEAR);
        } 

        public Date(int mm, int dd, int yyyy)
        {
            setDate(mm, dd, yyyy);
        }

        public Date(Date newDate)
        {
            if (newDate != null)
            {
                setDate(newDate.Month, newDate.Day, newDate.Year);
            }
            else
            {
                setDate(DEFAULT_MONTH, DEFAULT_DAY, DEFAULT_YEAR);
            }
        }

        public int Month
        {
            get { return month; }
            set
            {
                if (value >= 1 && value <= 12)
                {
                    month = value;
                }
                else
                {
                    month = DEFAULT_MONTH;
                }
            }
        }

        public int Day 
        {
            get { return day; }
            set 
            {
                int[] validDays = { 0, 31, 29, 31, 30,
                    31, 30, 31, 31, 30,
                    31, 30, 31 };

                if (value >= 1 && value <= validDays[month])
                {
                    day = value;
                }
                else
                {
                    day = DEFAULT_DAY;
                }
            }
        }

        public int Year
        {
            get { return year; }
            set { year = value; }
        }

        public void setDate(int mm, int dd, int yyyy)
        {
            this.Month = mm;
            this.Day = dd;
            this.Year = yyyy;
        } 
       
        public string toString()
        {
            return month + "/" + day + "/" + year;
        }

        public bool equals(Date d)
        {
            if (month == d.month
                && day == d.day
                && year == d.year)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public int compareTo(Date that)
        {
            if (this.year < that.year)
            {
                return -1;
            }
            else if (this.year > that.year)
            {
                return 1;
            }
            else
            {
                if (this.month < that.month)
                {
                    return -1;
                }
                else if (this.month > that.month)
                {
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
        }
   }    
}
