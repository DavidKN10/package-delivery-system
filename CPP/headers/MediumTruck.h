#pragma once

#include <string>
#include "Truck.h"
#include "Pack.h"

// inherits from Truck
class MediumTruck : public Truck {
public:
    int MAX_VOLUME = 2000;
    int MAX_WEIGHT = 4000;

    int getMaxWeight() const override;
    int getMaxVolume() const override;
    void addPack(const Pack& p) override;
    int totalPacksVolume() override;
    int totalPacksWeight() override;

    MediumTruck();
};
