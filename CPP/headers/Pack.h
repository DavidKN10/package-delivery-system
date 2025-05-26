#pragma once

#include <string>
#include "Date.h"
#include <iostream>

class Pack {
public:
    // accessor methods
    std::string getReceiver() const;
    std::string getZone() const;
    Date getDeliveryDate() const;
    int getWeight() const;
    int getVolume() const;
    int getPackageID() const;
    static int getTime();

    // mutator methods
    void setReceiver(const std::string& n);
    void setZone(const std::string& z);
    void setDeliveryDate(const Date& d);
    void setWeight(int w);
    void setVolume(int v);

    // toString()
    virtual std::string toString();

    // comparison
    int compareTo(const Pack& that) const;

    // overload quantity operator for comparison in remove method for CustomList
    bool operator==(const Pack& other) const;

    // overload output operator for display method
    friend std::ostream& operator<<(std::ostream& os, const Pack& p);

    // default constructor
    Pack();

    // non-default constructor
    Pack(const std::string& r, const std::string& z, const Date& d, int w, int v);

private:
    // attributes
    int packageID;
    std::string receiver;
    std::string zone;
    Date deliveryDate;
    int weight{};
    int volume{};

protected:
    static int count;

    // attributes default values
    std::string DEFAULT_RECEIVER = "Default Company";
    std::string DEFAULT_ZONE = "A1";
    Date DEFAULT_DATE = Date(1, 1, 2000);
    int DEFAULT_WEIGHT = 1;
    int DEFAULT_VOLUME = 1;
};