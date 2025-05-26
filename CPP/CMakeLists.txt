cmake_minimum_required(VERSION 3.31)
project(recreatingCS166Project)

set(CMAKE_CXX_STANDARD 14)

include_directories(headers)

add_executable(recreatingCS166Project
        main.cpp
        Date.cpp
        Pack.cpp
        testing.cpp
        Truck.cpp
        headers/Truck.h
        headers/CustomList.h
        headers/SmallTruck.h
        SmallTruck.cpp
        headers/MediumTruck.h
        MediumTruck.cpp
        headers/LargeTruck.h
        LargeTruck.cpp
        SpecialPack.cpp
        headers/SpecialPack.h
)
