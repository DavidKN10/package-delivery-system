namespace PackageDeliverySystem
{
    class LargeTruck : Truck
    {
        public static int LARGE_MAX_VOLUME = 4000;
        public static int LARGE_MAX_WEIGHT = 8000;

        public LargeTruck() : base() { }

        public override void addPack(Pack pack)
        {
            if (totalVolume() + pack.Volume <= LARGE_MAX_VOLUME &&
                totalWeight() + pack.Weight <= LARGE_MAX_WEIGHT)
            {
                base.addPack(pack);
            }
        }

        public override int getMaxWeight()
        {
            return LARGE_MAX_WEIGHT; 
        }

        public override int getMaxVolume()
        {
            return LARGE_MAX_VOLUME;
        }
    }
}
