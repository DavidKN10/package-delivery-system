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

def find_nth_occurrence(s, ch, n):
    occurrence = 0
    for i in range(len(s)):
        if s[i] == ch:
            occurrence += 1
        if occurrence == n:
            return i
    return -1

def parse_pack_data(data):
    if data[0] == "S":
        receiver = data[1]
        zone = data[2]
        date_data = data[3].split("/")
        delivery_date = Date(int(date_data[0]), int(date_data[1]), int(date_data[2]))
        weight = int(data[4])
        volume = int(data[5])
        time = int(data[6])
        return SpecialPack(receiver, zone, delivery_date, weight, volume, time)
    else:
        receiver = data[1]
        zone = data[2]
        date_data = data[3].split("/")
        delivery_date = Date(int(date_data[0]), int(date_data[1]), int(date_data[2]))
        weight = int(data[4])
        volume = int(data[5])
        return Pack(receiver, zone, delivery_date, weight, volume)

def assign_packs(truck_list, pack_list):
    assigned_packages = []
    for package in pack_list:
        if package in assigned_packages:
            continue
        else:
            assigned = False
            for truck_item in truck_list:
                totalVolume = sum(truck_item.total_volume() for truck_item in truck_list)
                totalWeight = sum(truck_item.total_weight() for truck_item in truck_list)
                if totalVolume + package.get_volume() < truck_item.MAX_VOLUME or \
                        totalWeight + package.get_weight() < truck_item.MAX_WEIGHT:
                    truck_item.add_pack(package)
                    assigned = True
                    assigned_packages.append(package)
                    break
            if not assigned:
                new_truck = LargeTruck()
                truck_list.append(new_truck)
                new_truck.add_pack(package)
                assigned_packages.append(package)

#getting all text files
packages_file = input("enter packages file location: ")

#creating/managing collection of package objects

delivery_file = "deliveries.txt"
error_log = "log.txt"
#count how many packages are in the text file

number_of_packages = 0
#read each line in the text file to see how many packages there are
#IOError will write to a text file named log.txt

try:
    with open(packages_file, "r") as pack_file:
        for line in pack_file:
            number_of_packages += 1
except IOError as e:
    with open(error_log, "a") as log_file:
        log_file.write("IOException\n")
#make a list called list and a list named packs
#list will be used to read each line in the text file and store it
#each line in the text file will be converted to pack objects and then will be stored in packs
list = []
packs = []
#read lines from the file and store them in the list


with open(packages_file, "r") as pack_file:
    list = pack_file.readlines()
#convert each line in the list into a pack object and store in a pack list
#IOError will write to log.txt

for i in range(number_of_packages):
    if list[i][0] == "S" or list[i][0] == "R" and list[i][1] == ",":
        data = list[i].split(",")
        packs.append(parse_pack_data(data))
    else:
        with open(error_log, 'a') as writer:
            writer.write("IOException\n")
#make a string list of pack days
#this is done in order to sort the packs by day
#string list of packs
string_packs = []

for i in packs:
    string_packs.append(i.get_delivery_date().__str__())
#string list of days
days = []

for i in string_packs:
    index_slash = i.find("/")
    days.append(i[index_slash+1:index_slash+3])

"""
#just to check the days for the pack objects
for i in days:
    print(i)

"""
#string list to int list
int_days = []

for i in days:
    int_days.append(int(i))

#implementing algorithm
#sort packs by day by using binary insertion
#make a list of trucks and package ids
#we need a list of package ids to see which ones are already in a truck

bis_day(int_days, packs)
trucks = [SmallTruck()]
assign_packs(trucks, packs)


#output trucks and content of trucks into deliveries.txt
with open(delivery_file, "w") as output:
    output.write("LIST OF TRUCKS WITH PACKAGE INFO\n")
    output.write("================================\n")
    output.write("\n")
    output.write("\n")
    for i, truck in enumerate(trucks, start=1):
        output.write(f"Truck {i}: " + str(truck) +"\n")
        output.write("------------------\n")
        for j in truck.get_packages():
            output.write(str(j) + "\n")
        output.write("\n")

small_truck_count = 0
medium_truck_count = 0
large_truck_count = 0
total_truck_hours = 0

#calculate truck hours
for truck in trucks:
    if isinstance(truck, SmallTruck):
        small_truck_count += 1
        total_truck_hours += 1 * truck.total_truck_hours()  # Small truck: 1 hour
    elif isinstance(truck, MediumTruck):
        medium_truck_count += 1
        total_truck_hours += 2 * truck.total_truck_hours()  # Medium truck: 2 hours
    elif isinstance(truck, LargeTruck):
        large_truck_count += 1
        total_truck_hours += 3 * truck.total_truck_hours()  # Large truck: 3 hours

# Display results to the screen
print(f"Number of Small Trucks: {small_truck_count}")
print(f"Number of Medium Trucks: {medium_truck_count}")
print(f"Number of Large Trucks: {large_truck_count}")
print(f"Total Truck Hours: {total_truck_hours}")
