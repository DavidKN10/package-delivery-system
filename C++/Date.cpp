#include "headers/Date.h"

// default constructor
Date::Date() {
    day = DEFAULT_DAY;
    month = DEFAULT_MONTH;
    year = DEFAULT_YEAR;
}

// non-default constructor
Date::Date(int m, int d, int y) {
    day = d;
    month = m;
    year = y;
}

// non-default constructor
Date::Date(const Date& newDate) {
    day = newDate.day;
    month = newDate.month;
    year = newDate.year;
}

// accessor methods
int Date::getDay() const {
    return day;
}

int Date::getMonth() const {
    return month;
}

int Date::getYear() const {
    return year;
}

// mutator methods
void Date::setDate(int mm, int dd, int yyyy) {
    setMonth(mm);
    setDay(dd);
    setYear(yyyy);
}

void Date::setDay(int dd) {
    int validDays[] = {0, 31, 29, 31, 30,
                       31, 30, 31, 31, 30,
                       31, 30, 31};
    if (dd >= 1 && dd <= validDays[month]) {
        day = dd;
    }
    else {
        day = DEFAULT_DAY;
    }
}

void Date::setMonth(int mm) {
    if (mm >= 1 && mm <= 12) {
        month = mm;
    }
    else {
        month = DEFAULT_MONTH;
    }
}

void Date::setYear(int yyyy) {
    year = yyyy;
}

// toString
std::string Date::toString() const {
    return std::to_string(month) + "/" + std::to_string(day) + "/" + std::to_string(year);
}

// comparison
bool Date::equals(const Date& date) const {
    if (month == date.month && day == date.day && year == date.year) {
        return true;
    }
    else {
        return false;
    }
}

int Date::compareTo(const Date& that) const {
    if (this->year < that.year) {
        return -1;
    }
    else if (this->year > that.year) {
        return 1;
    }
    else {
        if (this->month < that.month) {
            return -1;
        }
        else if (this->month > that.month) {
            return 1;
        }
        else {
            if (this->day < that.day) {
                return -1;
            }
            else if (this->day > that.day) {
                return 1;
            }
            else return 0;
        }
    }
}