#include "headers/MediumTruck.h"

MediumTruck::MediumTruck() {
    truckID = count;
}

int MediumTruck::getMaxWeight() const {
    return MAX_WEIGHT;
}

int MediumTruck::getMaxVolume() const {
    return MAX_VOLUME;
}

void MediumTruck::addPack(const Pack &p) {
    if (totalPacksVolume() + p.getVolume() <= MAX_VOLUME &&
        totalPacksWeight() + p.getWeight() <= MAX_WEIGHT) {
        packages.add(p);
    }
}

int MediumTruck::totalPacksVolume() {
    int total = 0;
    for (int i = 0; i < packages.size(); i++) {
        total += packages.get(i).getVolume();
    }
    return total;
}

int MediumTruck::totalPacksWeight() {
    int total = 0;
    for (int i = 0; i < packages.size(); i++) {
        total += packages.get(i).getWeight();
    }
    return total;
}
