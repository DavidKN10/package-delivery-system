from Date import Date

class Pack:
    DEFAULT_RECEIVER = "Default Company"
    DEFAULT_ZONE = "A1"
    DEFAULT_DATE = Date()
    DEFAULT_WEIGHT = 1
    DEFAULT_VOLUME = 1
    count = 1

    def __init__(self, receiver=None, zone=None,
                 date=None, weight=None,
                 volume=None):
        self.packageID = Pack.count
        self.set_receiver(receiver)
        self.set_zone(zone)
        self.set_delivery_date(date)
        self.set_weight(weight)
        self.set_volume(volume)
        Pack.packageID = Pack.count
        Pack.count += 1


    def get_receiver(self):
        return self.receiver

    def get_zone(self):
        return self.zone

    def get_delivery_date(self):
        return self.date

    def get_weight(self):
        return self.weight

    def get_volume(self):
        return self.volume

    def get_packageID(self):
        return self.packageID

    def get_time(self):
        return 17

    def set_receiver(self, name):
        if name is not None:
            self.receiver = name
        else:
            self.receiver = Pack.DEFAULT_RECEIVER

    def set_zone(self, z):
        if z is not None and "A" <= z[0] <= "Z" and "1" <= z[1] <= "9":
            self.zone = z
        else:
            self.zone = Pack.DEFAULT_ZONE

    def set_delivery_date(self, d):
        if d is not None and isinstance(d, Date):
            self.date = d
        else:
            self.date = Date(Date.DEFAULT_MONTH, Date.DEFAULT_DAY, Date.DEFAULT_YEAR)

    def set_weight(self, w):
        if w is not None:
            if w > 0:
                self.weight = w
            else:
                self.weight = Pack.DEFAULT_WEIGHT
        else:
            self.weight = Pack.DEFAULT_WEIGHT

    def set_volume(self, v):
        if v is not None:
            if v > 0:
                self.volume = v
            else:
                self.volume = Pack.DEFAULT_VOLUME
        else:
            self.volume = Pack.DEFAULT_VOLUME

    def __str__(self):
        return f"ID:{self.get_packageID()} Company:{self.receiver} Zone:{self.zone} Date:{self.date} Weight:{self.weight} Volume:{self.volume}"

    def compare_to(self, pack):
        if self.get_zone() < pack.get_zone():
            return 1
        elif self.get_zone() > pack.get_zone():
            return -1
        else:
            if self.get_volume() > pack.get_volume():
                return 1
            elif self.get_volume() < pack.get_volume():
                return -1
            else:
                return 0

