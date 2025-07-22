namespace PackageDeliverySystem
{
    class MediumTruck : Truck
    {
        public static int MEDIUM_MAX_VOLUME = 2000;
        public static int MEDIUM_MAX_WEIGHT = 4000;

        public MediumTruck() : base() { }

        public override void addPack(Pack pack)
        {
            if (totalVolume() + pack.Volume <= MEDIUM_MAX_VOLUME &&
                totalWeight() + pack.Weight <= MEDIUM_MAX_WEIGHT)
            {
                base.addPack(pack);
            }      
        }

        public override int getMaxWeight()
        {
            return MEDIUM_MAX_WEIGHT;
        }

        public override int getMaxVolume()
        {
            return MEDIUM_MAX_VOLUME;
        }

    }
}
