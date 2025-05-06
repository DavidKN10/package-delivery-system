import java.util.ArrayList;
import java.util.List;
public abstract class Truck{
    private static int truckHours = 0;
    private static int totalVolume = 0;
    private static int totalWeight = 0;
    List<Pack> packages = new ArrayList<>();
    private static int count = 1;
    int truckID;

    public static final int MAX_VOLUME = 0;
    public static final int MAX_WEIGHT = 0;


    public Truck(){
        truckID = count;
        count++;
        packages = new ArrayList<>();
    }

    public void addPack(Pack pack){packages.add(pack);}

    public void removePack(Pack pack){packages.remove(pack);}

    public List<Pack> getPackages(){
        return packages;
    }

    public int getTruckID(){
        return truckID;
    }
    public int totalVolume(){
        for (Pack aPackage : packages) {
            totalVolume += aPackage.getVolume();
        }
        return totalVolume;
    }

    public int totalWeight(){
        for (Pack aPackage : packages) {
            totalWeight += aPackage.getWeight();
        }
        return totalWeight;
    }

    public int getTruckHours(){
        for (Pack aPackage : packages) {
            truckHours += aPackage.getTime();
        }
        return truckHours;
    }

    public int getMaxWeight() {
        return MAX_WEIGHT;
    }

    public int getMaxVolume() {
        return MAX_VOLUME;
    }

    public int totalTruckHours(){
        int truckHours = 0;
        for(Pack pack : packages) {
            truckHours += pack.getTime();
        }
        return truckHours;
    }

    @Override
    public String toString(){
        return "Truck ID: "+getTruckID()+" Total Volume: "+ totalVolume()+" Total Weight: "+totalWeight();
    }


}

