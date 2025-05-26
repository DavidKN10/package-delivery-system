#include "headers/testing.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include <memory>
#include <string>
#include <numeric>
#include <algorithm>

/* Helper functions */

// sorts a list of packs by date
void bisDay(CustomList<int> dayNum, std::vector<std::unique_ptr<Pack>>& packs) {
    for (int i = 0; i < dayNum.size(); i++) {
        int tempDay = dayNum.get(i);
        std::unique_ptr<Pack> tempPack = std::move(packs[i]);
        int low = 0;
        int high = i;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (dayNum.get(mid) < tempDay) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }

        for (int j = i-1; j >= low; j--) {
            dayNum.set(j+1, dayNum.get(j));
            packs[j + 1] = std::move(packs[j]);
        }
        dayNum.set(low, tempDay);
        packs[low] = std::move(tempPack);
    }
}

// finds the nth occurrence of a char in a string
int findNthOccurrence(const std::string& s, char ch, int n) {
    int occurrence = 0;
    for (int i = 0; i < s.size(); i++) {
        if (s[i] == ch) {
           occurrence += 1;
        }
        if (occurrence == n) {
            return i;
        }
    }
    return -1;
}

// helper function for parsePackData
std::unique_ptr<Pack> createPack(bool isSpecial, const std::string& r, const std::string& z,
                                 const Date& d, int w, int v, int t = 0 ) {
    if (isSpecial) {
        return std::make_unique<SpecialPack>(r, z, d, w, v, t);
    }
    else {
        return std::make_unique<Pack>(r, z, d, w, v);
    }
}

std::vector<std::string> split(const std::string& str, char delimiter) {
    std::vector<std::string> tokens;
    std::stringstream ss(str);
    std::string token;

    while (std::getline(ss, token, delimiter)) {
        tokens.push_back(token);
    }

    return tokens;
}

// will read an array of strings that contains pack data and returns a pointer to a pack object
std::unique_ptr<Pack> parsePackData(std::vector<std::string> data) {
    std::string& receiver = data[1];
    std::string& zone = data[2];

    std::vector<int> dateData;
    std::stringstream ss(data[3]);
    std::string token;
    while (std::getline(ss, token, '/')) {
        dateData.push_back(std::stoi(token));
    }
    Date deliveryDate = Date(dateData[0], dateData[1], dateData[2]);

    int weight = std::stoi(data[4]);
    int volume = std::stoi(data[5]);

    if (data[0] == "S") {
        int time = std::stoi(data[6]);
        // create a special pack
        return createPack(true, receiver, zone, deliveryDate, weight, volume, time);
    }
    else {
        // create a normal pack
        return createPack(false, receiver, zone, deliveryDate, weight, volume);
    }
}

// assigns packs to trucks
void assignPacks(std::vector<std::unique_ptr<Truck>>& truckList, std::vector<std::unique_ptr<Pack>>& packList) {
    std::vector<Pack*> assignedPacks;

    for (size_t i = 0; i < packList.size(); ++i) {
        Pack* current = packList[i].get();

        if (std::find(assignedPacks.begin(), assignedPacks.end(), current) != assignedPacks.end()) {
            break;
        }

        bool assigned = false;

        for (size_t j = 0; j < truckList.size(); ++j) {
            int totalVolume = std::accumulate(truckList.begin(), truckList.end(), 0,
                                              [](int sum, const std::unique_ptr<Truck>& t) {
                                                  return sum + t->totalPacksVolume();
                                              });
            int totalWeight = std::accumulate(truckList.begin(), truckList.end(), 0,
                                              [](int sum, const std::unique_ptr<Truck>& t) {
                                                  return sum + t->totalPacksWeight();
                                              });

            if (totalVolume + current->getVolume() < truckList[j]->getMaxVolume() &&
                totalWeight + current->getWeight() < truckList[j]->getMaxWeight()) {
                truckList[j]->addPack(*current);
                assignedPacks.push_back(current);
                assigned = true;
                break;
            }
        }

        if (!assigned) {
                auto newTruck = std::make_unique<LargeTruck>();
                newTruck->addPack(*current);
                assignedPacks.push_back(current);
                truckList.push_back(std::move(newTruck));
        }
    }
}

int main() {

    // function that does testing on the Date and Pack class.
    // objectTesting();

    /* Package Delivery System */

    std::string fileName;
    std::ifstream inputFile;
    int numPackages = 0;
    CustomList<std::string> list;
    std::vector<std::unique_ptr<Pack>> packs;
    std::vector<std::unique_ptr<Truck>> trucks;

    // file reading
    std::cout << "Enter the name of file with packages: " << std::endl;
    std::getline(std::cin >> std::ws, fileName);

    inputFile.open(fileName);

    /* creating/managing collection of package objects */

    // count how many packages are in the text file
    // make an array of strings where each element will be one line in the text file
    if (inputFile.is_open()) {
        std::string line;
        while (getline(inputFile, line)) {
            list.add(line);
            numPackages++;
        }
        inputFile.close();
    }
    else {
        std::cerr << "Error opening file" << std::endl;
        return 1;
    }

    // convert each string into a pack object and put in a pack array
    for (int i = 0; i < numPackages; i++) {
        std::vector<std::string> packData = split(list.get(i), ',');
        if (packData[0] == "S" || packData[0] == "R") {
            packs.push_back(parsePackData(packData));
        }
    }

    CustomList<std::string> stringDates;
    for (const auto & pack : packs) {
        stringDates.add(pack->getDeliveryDate().toString());
    }

    CustomList<std::string> days;
    for (int i = 0; i < stringDates.size(); i++) {
        size_t firstSlash = stringDates.get(i).find('/');
        size_t secondSlash = stringDates.get(i).find('/', firstSlash + 1);
        std::string day = stringDates.get(i).substr(firstSlash + 1, secondSlash - firstSlash - 1);
        days.add(day);
    }

    CustomList<int> intDays;
    for (int i = 0; i < days.size(); i++) {
        intDays.add(std::stoi(days.get(i)));
    }

    /* Implementation of Algorithm */

    // sort by day
    bisDay(intDays, packs);
    // start by adding one Small Truck to the truck list
    trucks.push_back(std::make_unique<SmallTruck>());
    // algorithm to assign packs
    assignPacks(trucks, packs);

    // output trucks and content of trucks into deliveries.txt
    int truckIndex = 1;
    std::ofstream outputFile;
    outputFile.open(R"(deliveries.txt)");

    outputFile << "List of Trucks With Package Info" << "\n";
    outputFile << "================================" << "\n" << "\n";

    if (outputFile) {
        for (const auto & truck : trucks) {
            outputFile << "Truck " << truckIndex << ": " << truck->toString() << "\n";
            outputFile << "--------------------------------" << "\n";

            for (int pack = 0; pack < truck->getPackages().size(); pack++) {
                outputFile << truck->getPackages().get(pack).toString() << "\n";
            }
            outputFile << "\n";
            truckIndex++;
        }
        outputFile.close();
    }
    else {
        std::cerr << "Error opening file for writing" << std::endl;
    }

   /* Calculate and display truck hours */
   size_t smallTruckCount = 0;
   size_t mediumTruckCount = 0;
   size_t largeTruckCount = 0;
   int totalTruckHours = 0;

   for (const auto& truck : trucks) {
       if (dynamic_cast<SmallTruck*>(truck.get())) {
           ++smallTruckCount;
           totalTruckHours += truck->totalTruckHours();
       }
       else if (dynamic_cast<MediumTruck*>(truck.get())) {
           ++mediumTruckCount;
           totalTruckHours += truck->totalTruckHours();
       }
       else if (dynamic_cast<LargeTruck*>(truck.get())) {
           ++largeTruckCount;
           totalTruckHours += truck->totalTruckHours();
       }
   }

   std::cout << "Number of Small Trucks: " << smallTruckCount <<  std::endl;
   std::cout << "Number of Medium Trucks: " << mediumTruckCount << std::endl;
   std::cout << "Number of Large Trucks: " << largeTruckCount << std::endl;
   std::cout << "Total Truck Hours: " << totalTruckHours << std::endl;

    return 0;
}