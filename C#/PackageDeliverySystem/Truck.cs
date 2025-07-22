namespace PackageDeliverySystem
{
    abstract class Truck
    {
        private static int truckHours = 0;
        private static int volumeSum = 0;
        private static int weightSum = 0;
        private static int count = 1;
        int truckID;
        List<Pack> packages = new List<Pack>();

        public static int MAX_VOLUME = 0;
        public static int MAX_WEIGHT = 0;

        public Truck()
        {
            truckID = count;
            count++;
            packages = new List<Pack>();

        }

        public virtual void addPack(Pack pack)
        {
            packages.Add(pack);
        }

        public void removePack(Pack pack)
        {
            packages.Remove(pack);
        }

        public List<Pack> getPackages()
        {
            return packages;
        }

        public int getTruckID()
        {
            return truckID;
        }

        public int totalVolume()
        {
            int volumeSum = 0;
            foreach (Pack pack in packages)
            {
                volumeSum += pack.Volume; 
            }
            return volumeSum;
        }

        public int totalWeight()
        {
            int weightSum = 0;
            foreach (Pack pack in packages)
            {
                weightSum += pack.Weight;
            }
            return weightSum;
        }

        public int getTruckHours()
        {
            foreach (Pack pack in packages)
            {
                truckHours += pack.Time;
            }
            return truckHours;
        }

        public virtual int getMaxWeight()
        {
            return MAX_WEIGHT; 
        }

        public virtual int getMaxVolume()
        {
            return MAX_VOLUME;
        }

        public int totalTruckHours()
        {
            int truckHours = 0;
            foreach (Pack pack in packages)
            {
                truckHours += pack.Time;
            }
            return truckHours;
        }

        public String toString()
        {
            return "Truck ID: " + getTruckID() + " Total Volume: " + totalVolume() + " Total Weight: " + totalWeight();
        }
    }
}
