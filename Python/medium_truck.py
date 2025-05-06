from Truck import Truck
from abc import ABC

class MediumTruck(Truck, ABC):
    MAX_VOLUME = 2000
    MAX_WEIGHT = 4000

    def add_pack(self, pack):
        if self.total_volume() + pack.get_volume() <= MediumTruck.MAX_VOLUME and \
                self.total_weight() + pack.get_weight() <= MediumTruck.MAX_WEIGHT:
            self.packages.append(pack)
        else:
            return False

    def total_volume(self):
        total = 0
        for pack in self.packages:
            total += pack.get_volume()
        return total

    def total_weight(self):
        total = 0
        for pack in self.packages:
            total += pack.get_weight()
        return total