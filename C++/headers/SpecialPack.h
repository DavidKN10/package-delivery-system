#pragma once

#include "Pack.h"

// inherits from Pack
class SpecialPack : public Pack {
public:
    int getTime() const;
    void setTime(int t);

    std::string toString() override;

    SpecialPack();
    SpecialPack(const std::string& r, const std::string& z, const Date& d, int w, int v, int t);

private:
    int time{};
    int DEFAULT_TIME = 9;
};