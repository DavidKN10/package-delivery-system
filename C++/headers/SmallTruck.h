#pragma once

#include <string>
#include "Truck.h"
#include "Pack.h"

class SmallTruck : public Truck {
public:
    int MAX_VOLUME = 1000;
    int MAX_WEIGHT = 2000;

    int getMaxWeight() const override;
    int getMaxVolume() const override;
    void addPack(const Pack& p) override;
    int totalPacksVolume() override;
    int totalPacksWeight() override;

    SmallTruck();

};