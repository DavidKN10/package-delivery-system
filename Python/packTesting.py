from Date import Date
from Pack import Pack
from SpecialPack import SpecialPack


#testing the Date class
d = Date()
print(d)

new_date = Date(2,24,2004)
print(new_date)
print(new_date.get_day())
print(new_date.get_month())
print(new_date.get_year())

#testing the Pack class
p1 = Pack("Hi", "C2", 1,2,3)
print(p1)

p2 = Pack("hi", "A2", "m",2,3)
print(p2)


p3 = Pack(volume=1)
print(p3)
pp = Pack(volume=3)


p4 = Pack(receiver="Acme Corporation", zone="C3", date=Date(2,24,2004), weight=5, volume=3)
print(p4)

p5 = Pack(receiver="Hololive", zone="C3", date=Date(11,24,2005), weight=10, volume=23)
print(p5)

print(p4.compare_to(p5))

sp1 = SpecialPack(receiver ="Company", zone="H3", date=Date(3,23,1990), weight=200, volume=100, time=10)
print(sp1)
sp1.set_time(15)
print(sp1)

sp2 = SpecialPack(receiver ="Company", zone="A3", date=Date(3,23,1990), weight=200, volume=110)
print(sp2)

print(sp1.compare_to(sp2))

print(p4.compare_to(sp2))






