class Date:

    DEFAULT_YEAR = 2000
    DEFAULT_MONTH = 1
    DEFAULT_DAY = 1

    def __init__(self, mm=DEFAULT_MONTH, dd=DEFAULT_DAY, yyyy=DEFAULT_YEAR):
        self.set_date(mm, dd, yyyy)


    def get_month(self):
        return self.month

    def get_day(self):
        return self.day

    def get_year(self):
        return self.year

    def set_date(self, mm, dd, yyyy):
        self.set_year(yyyy)
        self.set_month(mm)
        self.set_day(dd)

    def set_day(self, dd):
        valid_days = [0, 31, 29, 31,
                      30, 31, 30, 31,
                      31, 30, 31, 30, 31]
        if 1 <= dd <= valid_days[self.month]:
            self.day = dd
        else:
            self.day = Date.DEFAULT_DAY

    def set_month(self, mm):
        if isinstance(mm, int) and 1 <= mm <= 12:
            self.month = mm
        else:
            self.month = Date.DEFAULT_MONTH

    def set_year(self, yyyy):
        self.year = yyyy

    def __str__(self):
        return f"{self.get_month()}/{self.get_day()}/{self.get_year()}"

    def equals(self, d):
        return self.month == d.get_month() and self.day == d.get_day() and self.year == d.get_year()

    def compare_to(self, that):
        if self.year < that.get_year():
            return -1
        elif self.year > that.get_year():
            return 1
        else:
            if self.month < that.get_month():
                return -1
            elif self.month > that.get_month():
                return 1
            else:
                if self.day < that.get_day():
                    return -1
                elif self.day > that.get_day():
                    return 1
                else:
                    return 0