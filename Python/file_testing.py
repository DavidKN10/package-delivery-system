from Date import Date
from Pack import Pack
from SpecialPack import SpecialPack
from Truck import Truck
from small_truck import SmallTruck
from medium_truck import MediumTruck
from large_truck import LargeTruck


#getting all text files
packages_file = input("enter packages file location: ")
delivery_file = "deliveries.txt"
error_log = "log.txt"

#reading file and writing to file

try:
    with open(packages_file, 'r') as input_file:
        data = input_file.read()

    with open(delivery_file, 'w') as output_file:
        output_file.write(data)
except FileNotFoundError:
    print("file not found")
except Exception as e:
    print("error ocurred")




