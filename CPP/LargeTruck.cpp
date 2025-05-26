#include "headers/LargeTruck.h"

LargeTruck::LargeTruck() {
    truckID = count;
}

int LargeTruck::getMaxWeight() const {
    return MAX_WEIGHT;
}

int LargeTruck::getMaxVolume() const {
    return MAX_VOLUME;
}

void LargeTruck::addPack(const Pack &p) {
    if (totalPacksVolume() + p.getVolume() <= MAX_VOLUME &&
        totalPacksWeight() + p.getWeight() <= MAX_WEIGHT) {
        packages.add(p);
    }
}

int LargeTruck::totalPacksVolume() {
    int total = 0;
    for (int i = 0; i < packages.size(); i++) {
        total += packages.get(i).getVolume();
    }
    return total;
}

int LargeTruck::totalPacksWeight() {
    int total = 0;
    for (int i = 0; i < packages.size(); i++) {
        total += packages.get(i).getWeight();
    }
    return total;
}