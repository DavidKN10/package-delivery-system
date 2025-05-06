from abc import ABC, abstractmethod

class Truck(ABC):
    truck_hours = 0
    totalVolume = 0
    totalWeight = 0
    packages = []
    count = 1

    def __init__(self):
        self.truckID = Truck.count
        Truck.count += 1
        self.packages = []

    @abstractmethod
    def add_pack(self, pack):
        self.packages.append(pack)

    def remove_pack(self, pack):
        self.packages.remove(pack)

    def get_packages(self):
        return self.packages

    def get_truckID(self):
        return self.truckID

    @abstractmethod
    def total_volume(self):
        for pack in self.packages:
            self.totalVolume += pack.get_volume()
        return self.totalVolume

    @abstractmethod
    def total_weight(self):
        for pack in self.packages:
            self.totalWeight += pack.get_weight()
        return self.totalWeight

    def total_truck_hours(self):
        for pack in self.packages:
            self.truck_hours += pack.get_time()
        return self.truck_hours

    def __str__(self):
        return f"Truck ID: {self.get_truckID()} Total Volume: {self.total_volume()} Total Weight: {self.total_weight()}"
