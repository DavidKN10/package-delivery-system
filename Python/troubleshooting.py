from Date import Date
from Pack import Pack
from SpecialPack import SpecialPack
from Truck import Truck
from small_truck import SmallTruck
from medium_truck import MediumTruck
from large_truck import LargeTruck



def bis_day(day_num, p):
    for i in range(len(day_num)):
        temp = day_num[i]
        temps = p[i]
        low = 0
        high = i
        while low <= high:
            mid = (low + high) // 2
            if day_num[mid] < temp:
                low = mid + 1
            else:
                high = mid - 1
        for j in range(i - 1, low - 1, -1):
            day_num[j+1] = day_num[j]
            p[j + 1] = p[j]
        day_num[low] = temp
        p[low] = temps


def assign_packs(truck_list, pack_list):
    assigned_packages = []
    for package in pack_list:
        print("starting")
        if package in assigned_packages:
            print("pack was already in assigned_packages " + str(package))
            continue
        else:
            assigned = False
            for truck_item in truck_list:
                totalVolume = sum(truck_item.total_volume() for truck_item in truck_list)
                totalWeight = sum(truck_item.total_weight() for truck_item in truck_list)
                if totalVolume + package.get_volume() < truck_item.MAX_VOLUME or \
                        totalWeight + package.get_weight() < truck_item.MAX_WEIGHT:
                    print("added package " + str(package))
                    truck_item.add_pack(package)
                    assigned = True
                    assigned_packages.append(package)
                    break
            if not assigned:
                print("adding package to new truck " + str(package))
                new_truck = LargeTruck()
                truck_list.append(new_truck)
                new_truck.add_pack(package)
                assigned_packages.append(package)



trucks = []
trucks.append(SmallTruck())
packs = []
packs.append(SpecialPack(receiver="Proctor & Gamble",zone= "Z2", date=Date(3/17/2023), weight=169, volume=29,time= 16))
packs.append(SpecialPack(receiver="JPMorgan Chase", zone="E6", date=Date(3/17/2023), weight=347, volume=156, time=10))
packs.append(SpecialPack(receiver="Alphabet Class A",zone="G8",date=Date(3/13/2023),weight=300,volume=115,time=15))
packs.append(SpecialPack(receiver="Bank of America",zone="J3",date=Date(3/17/2023),weight=274,volume=90,time=13))
packs.append(SpecialPack(receiver="Home Depot",zone="U6",date=Date(3/17/2023),weight=114,volume=169,time=16))
packs.append(Pack(receiver="AbbVie Inc.",zone="C1",date=Date(3/14/2023),weight=160,volume=204))
packs.append(SpecialPack(receiver="Eli Lilly",zone="N2",date=Date(3/16/2023),weight=241,volume=116,time=15))
packs.append(SpecialPack(receiver="Chevron Corporation",zone="H7",date=Date(3/14/2023),weight=186,volume=77))
packs.append(SpecialPack(receiver="Visa Class A",zone="B5",date=Date(3/16/2023),weight=327,volume=70,time=10))
packs.append(Pack(receiver="Exxon Mobil",zone="Q1",date=Date(3/17/2023),weight=295,volume=82))






assign_packs(trucks, packs)


for i, truck in enumerate(trucks, start=1):
    print(f"Truck {i}: " + str(truck))
    print("------------------")
    for j in truck.get_packages():
        print(str(j))
    print("")

