from Truck import Truck
from Pack import Pack
from SpecialPack import SpecialPack
from small_truck import SmallTruck
from medium_truck import MediumTruck
from large_truck import LargeTruck
from Date import Date


pack1 = Pack(receiver="Company A", zone="A1", date=Date(2,24,2004), volume=500, weight=800)
pack2 = Pack(receiver="Company B", zone="B2", date=Date(11,24,2000), volume=600, weight=1000)
pack3 = Pack(receiver="Company C", zone="A3", date=Date(7,15,200), volume=300, weight=3000)

st1 = SmallTruck()
st1.add_pack(pack1)
st1.add_pack(pack2)
st1.add_pack(pack3)

print(st1)
print(st1.total_weight())
print(st1.total_volume())

mt1 = MediumTruck()
mt1.add_pack(pack1)
mt1.add_pack(pack2)
mt1.add_pack(pack3)

print(mt1)
print(mt1.total_weight())
print(mt1.total_volume())

lt1 = LargeTruck()
lt1.add_pack(pack1)
lt1.add_pack(pack2)
lt1.add_pack(pack3)

print(lt1)
print(lt1.total_weight())
print(lt1.total_volume())

