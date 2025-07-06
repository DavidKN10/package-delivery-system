#include "headers/Pack.h"

int Pack::count = 0;

// default constructor;
Pack::Pack() {
    receiver = DEFAULT_RECEIVER;
    zone = DEFAULT_ZONE;
    deliveryDate = DEFAULT_DATE;
    weight = DEFAULT_WEIGHT;
    volume = DEFAULT_VOLUME;
    packageID = count;
    count++;
}

// non-default constructor
Pack::Pack(const std::string& r, const std::string& z, const Date& d, int w, int v) {
    receiver = r;
    zone = z;
    deliveryDate = d;
    weight = w;
    volume = v;
    packageID = count;
    count++;
}

// accessor methods
std::string Pack::getReceiver() const {return receiver;}
std::string Pack::getZone() const {return zone;}
Date Pack::getDeliveryDate() const {return deliveryDate;}
int Pack::getWeight() const {return weight;}
int Pack::getVolume() const {return volume;}
int Pack::getPackageID() const {return packageID;}
int Pack::getTime() {return 17;}

// mutator methods
void Pack::setReceiver(const std::string& n) {
    if (!n.empty() ) {
        receiver = n;
    }
    else receiver = DEFAULT_RECEIVER;
}

void Pack::setZone(const std::string& z) {
    if (!z.empty() && z.at(0) >= 'A' && z.at(0) <= 'Z' &&
        z.at(1) >= '1' && z.at(1) <= '9' ) {
        this->zone = z;
    }
    else this->zone = DEFAULT_ZONE;
}

void Pack::setDeliveryDate(const Date& d) {
    if (d.month > 0 && d.day > 0 && d.year > 0) {
        deliveryDate = d;
    }
    else deliveryDate = DEFAULT_DATE;
}

void Pack::setWeight(int w) {
    if (w > 0) {
        weight = w;
    }
    else weight = DEFAULT_WEIGHT;
}

void Pack::setVolume(int v) {
    if (v > 0) {
        volume = v;
    }
    else volume = DEFAULT_VOLUME;
}

// toString
std::string Pack::toString() {
    return "ID: " + std::to_string(getPackageID()) + " Company: " + getReceiver() + " Zone: " + getZone() +
        " Date: " + getDeliveryDate().toString() + " Weight: " + std::to_string(getWeight()) + " Volume: " + std::to_string(getVolume());
}

// comparison
int Pack::compareTo(const Pack& that) const {
    if (this->getZone()[0] == that.getZone()[0]) {
        if (this->getZone()[1] == that.getZone()[1]) {
            if (this->getVolume() > that.getVolume()) {
                return 1;
            }
            else if (this->getVolume() < that.getVolume()) {
                return -1;
            }
            else return 0;
        }
        else if (this->getZone()[1] < that.getZone()[1]) {
            return 1;
        }
        else return -1;
    }
    else if (this->getZone()[0] > that.getZone()[0]) {
        return -1;
    }
    else if (this->getZone()[0] < that.getZone()[0]) {
        return 1;
    }
    else return 0;
}

// overloading operators
bool Pack::operator==(const Pack& other) const {
    return (packageID == other.packageID);
}

std::ostream& operator<<(std::ostream& os, const Pack& p) {
    os << ": Pack(ID: " << p.getPackageID() << ", Company: " << p.getReceiver()
        << ", Zone: "<< p.getZone() << ", Date: " << p.getDeliveryDate().toString()
        << ", Weight: " << p.getWeight() << ", Volume: " << p.getVolume() << ")";
    return os;
}

