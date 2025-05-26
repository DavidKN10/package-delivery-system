#pragma once

#include <vector>
#include "Pack.h"
#include "CustomList.h"

class Truck {
public:
    // attributes
    int truckID;
    int MAX_VOLUME = 0;
    int MAX_WEIGHT = 0;
    CustomList<Pack> packages;

    // accessor methods
    CustomList<Pack> getPackages() const;
    int getTruckID() const;
    int getTruckHours();
    // abstract accessor methods
    virtual int getMaxWeight() const = 0;
    virtual int getMaxVolume() const = 0;

    // other methods
    void removePack( const Pack& p);
    int totalTruckHours() const;
    // abstract methods
    virtual void addPack(const Pack& p) = 0;
    virtual int totalPacksVolume() = 0;
    virtual int totalPacksWeight() = 0;

    // toString
    std::string toString();

    // constructor
    Truck();

private:
    // attributes
    int truckHours = 0;
    int totalVolume = 0;
    int totalWeight = 0;

protected:
    // used to assign a unique ID
    static int count;
};
