#include "headers/SpecialPack.h"

SpecialPack::SpecialPack(){
    time = DEFAULT_TIME;
}

SpecialPack::SpecialPack(const std::string& r, const std::string& z, const Date& d, int w, int v, int t)
        : Pack(r, z, d, w, v) {
    time = t;
}

int SpecialPack::getTime() const {
    return time;
}

void SpecialPack::setTime(int t) {
    if (time >= 9 && time <= 16) {
        this->time = t;
    }
    else this->time = DEFAULT_TIME;
}

std::string SpecialPack::toString() {
    return "ID: " + std::to_string(getPackageID()) + " Company: " + getReceiver() + " Zone: " + getZone() +
           " Date: " + getDeliveryDate().toString() + " Weight: " + std::to_string(getWeight()) + " Volume: " +
           std::to_string(getVolume()) + " Time: " + std::to_string(getTime());
}
