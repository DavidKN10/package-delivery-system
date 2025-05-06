from Pack import Pack

class SpecialPack(Pack):
    time = 0
    DEFAULT_TIME=9

    def __init__(self, receiver=None, zone=None,
                 date=None, weight=None,
                 volume=None, time=None):

        super().__init__(receiver=receiver, zone=zone, date=date,
                         weight=weight,volume=volume)

        self.set_time(time)

    def get_time(self):
        return self.time

    def set_time(self, t):
        if t is not None:
            if 9 <= t <= 16:
                self.time = t
            else:
                self.time = SpecialPack.DEFAULT_TIME
        else:
            self.time = SpecialPack.DEFAULT_TIME

    def __str__(self):
        return f"ID:{self.get_packageID()} Company:{self.receiver} Zone:{self.zone} Date:{self.date} Weight:{self.weight} Volume:{self.volume} Time:{self.time}"

