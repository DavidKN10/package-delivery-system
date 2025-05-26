#pragma once

#include <string>
#include "Truck.h"
#include "Pack.h"

// inherits from Truck
class LargeTruck: public Truck {
public:
    int MAX_VOLUME = 4000;
    int MAX_WEIGHT = 8000;

    int getMaxWeight() const override;
    int getMaxVolume() const override;
    void addPack(const Pack& p) override;
    int totalPacksVolume() override;
    int totalPacksWeight() override;

    LargeTruck();
};