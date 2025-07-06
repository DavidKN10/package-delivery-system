#include "headers/SmallTruck.h"

SmallTruck::SmallTruck() {
    truckID = count;
}

int SmallTruck::getMaxWeight() const {
    return MAX_WEIGHT;
}

int SmallTruck::getMaxVolume() const {
    return MAX_VOLUME;
}

void SmallTruck::addPack(const Pack &p) {
    if (totalPacksVolume() + p.getVolume() <= MAX_VOLUME &&
        totalPacksWeight() + p.getWeight() <= MAX_WEIGHT) {
        packages.add(p);
    }
}

int SmallTruck::totalPacksVolume() {
    int total = 0;
    for (int i = 0; i < packages.size(); i++) {
        total += packages.get(i).getVolume();
    }
    return total;
}

int SmallTruck::totalPacksWeight() {
    int total = 0;
    for (int i = 0; i < packages.size(); i++) {
        total += packages.get(i).getWeight();
    }
    return total;
}
