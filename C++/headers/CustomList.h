#pragma once

#include <iostream>
#include <vector>
/*
  A generic class for a list that can store any type of object
 */

template <typename T>
class CustomList {
private:
    std::vector<T> elements;

public:
    // constructor
    CustomList() = default;

    // add an object to the list
    void add(const T& object);

    // remove an object from a list
    bool remove(const T& object);

    // display contents of the list
    void display() const;

    // return size of the list
    size_t size() const;

    // get the object at a specific index
    T get(size_t index) const;

    // set/replace the object at the specified index with a new value
    void set(size_t index, const T& newValue);

    // check if a specified element exists in the list
    bool contains(const T& object) const;
};

template <typename T>
void CustomList<T>::add(const T& object) {
    elements.push_back(object);
}

template <typename T>
bool CustomList<T>::remove(const T& object) {
    for (auto it = elements.begin(); it != elements.end(); ++it) {
        if (*it == object) {
            elements.erase(it);
            return true;
        }
    }
    return false;
}

template <typename T>
void CustomList<T>::display() const {
    if (elements.empty()) {
        std::cout << "This list is empty" << std::endl;
        return;
    }

    std::cout << "List of contents:" <<std::endl;
    for (size_t i = 0; i < elements.size(); ++i) {
        std::cout << "[" << i << "]" << elements[i] << std::endl;
    }
}

template <typename T>
size_t CustomList<T>::size() const {
    return elements.size();
}

template <typename T>
T CustomList<T>::get(size_t index) const {
    if (index >= elements.size()) {
        throw std::out_of_range("Index out of range");
    }
    return elements[index];
}

template <typename T>
void CustomList<T>::set(size_t index, const T& newValue) {
    if (index >= elements.size()) {
        throw std::out_of_range("Index out of range");
    }
    elements[index] = newValue;
}

template <typename T>
bool CustomList<T>::contains(const T &object) const {
    for (const auto& element : elements) {
        if (element == object) {
            return true;
        }
    }
    return false;
}