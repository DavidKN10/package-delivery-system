namespace PackageDeliverySystem
{
    class SmallTruck : Truck
    {
        public static int SMALL_MAX_VOLUME = 1000;  
        public static int SMALL_MAX_WEIGHT = 2000;
        
        public SmallTruck() : base() { }

        public override void addPack(Pack pack)
        {
            if (totalVolume() + pack.Volume <= SMALL_MAX_VOLUME &&
                totalWeight() + pack.Weight <= SMALL_MAX_WEIGHT)
            {
                base.addPack(pack);
            }
        }

        public override int getMaxWeight()
        {
            return SMALL_MAX_WEIGHT;
        }

        public override int getMaxVolume()
        {
            return SMALL_MAX_VOLUME;
        }

    }
}
