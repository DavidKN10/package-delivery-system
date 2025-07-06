#pragma once

#include <string>

class Date{
  public:
    // constants
    static constexpr int DEFAULT_YEAR = 2000;
    static constexpr int DEFAULT_MONTH = 1;
    static constexpr int DEFAULT_DAY = 1;

    // attributes
    int day;
    int month;
    int year;

    // accessor methods
    int getMonth() const;
    int getDay() const;
    int getYear() const;

    // mutator method
    void setDate(int mm, int dd, int yyyy);

    // toString
    std::string toString() const;

    // comparison
    bool equals(const Date& date) const;
    int compareTo(const Date& that) const;

    // default constructor
    Date();

    // non-default constructors
    Date(int m, int d, int y);
    Date(const Date& newDate);

  private:
    // mutator methods to be used with setDate
    void setDay(int dd);
    void setMonth(int mm);
    void setYear(int yyyy);
};
