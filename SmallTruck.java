public class SmallTruck extends Truck {
    public static final int MAX_VOLUME = 1000;
    public static final int MAX_WEIGHT = 2000;

    public SmallTruck() {
        super();
    }

    @Override
    public void addPack(Pack pack) {
        if (totalVolume() + pack.getVolume() <= MAX_VOLUME &&
                totalWeight() + pack.getWeight() <= MAX_WEIGHT) {
            packages.add(pack);
        }
    }

    @Override
    public int totalVolume() {
        int total = 0;
        for (Pack pack : packages) {
            total += pack.getVolume();
        }
        return total;
    }

    @Override
    public int totalWeight() {
        int total = 0;
        for (Pack pack : packages) {
            total += pack.getWeight();
        }
        return total;
    }
}
