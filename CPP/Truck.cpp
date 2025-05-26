#include "headers/Truck.h"
#include <string>

int Truck::count = 0;

// default constructor
Truck::Truck() {
    truckID = count;
    count++;
}

// accessor methods
CustomList<Pack> Truck::getPackages() const {
    return packages;
}

int Truck::getTruckID() const {
    return truckID;
}

// check back on this after implementing SpecialPack
int Truck::getTruckHours() {
    for (int i=0; i < packages.size(); i++) {
        truckHours += Pack::getTime();
    }
    return truckHours;
}

int Truck::getMaxWeight() const {
    return MAX_WEIGHT;
}

int Truck::getMaxVolume() const  {
    return MAX_VOLUME;
}

// other methods
void Truck::addPack(const Pack& p) {
    packages.add(p);
}

void Truck::removePack(const Pack& p) {
    packages.remove(p);
}

int Truck::totalPacksVolume() {
    for (int i=0; i < packages.size(); i++) {
        totalVolume += packages.get(i).getVolume();
    }
    return totalVolume;
}

int Truck::totalPacksWeight() {
   for (int i=0; i < packages.size(); i++) {
       totalWeight += packages.get(i).getWeight();
   }
   return totalWeight;
}

// check back on this after implementing SpecialPack
int Truck::totalTruckHours() const {
    int truckHrs = 0;
    for (int i=0; i < packages.size(); i++) {
        truckHrs += Pack::getTime();
    }
    return truckHrs;
}

std::string Truck::toString() {
    return "Truck ID: " + std::to_string(getTruckID()) + " Total Volume: " + std::to_string(totalPacksVolume()) +
    " Total Weight: " + std::to_string(totalPacksWeight()) ;
}

