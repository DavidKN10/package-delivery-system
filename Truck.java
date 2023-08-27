import java.util.ArrayList;
import java.util.List;
public abstract class Truck{
    private static int truckHours = 0;
    private static int totalVolume = 0;
    private static int totalWeight = 0;
    List<Pack> packages = new ArrayList<>();
    private static int count = 1;
    int truckID;


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

    @Override
    public String toString(){
        return "Truck ID: "+getTruckID()+" Total Volume: "+ totalVolume()+" Total Weight: "+totalWeight();
    }


}

