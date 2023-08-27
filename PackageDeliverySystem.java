import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
public class PackageDeliverySystem {
    public static void main(String[] args) {

    }

    public void bidDay(int[] dayNum, ArrayList<Pack> packs) {
        for(int i = 0; i < dayNum.length; i++){
            int temp = dayNum[i];
            Pack temps = packs.get(i);
            int low = 0;
            int high = i;
            while(low <= high){
                int mid = (low+high)/2;
                if(dayNum[mid] < temp){
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
            for(int j=i-1; j>=low; j--){
                dayNum[j+1] = dayNum[j];
                packs.set(j+1, packs.get(j));
            }
            dayNum[low] = temp;
            packs.set(low, temps);
        }
    }

    public static int findNthOccurrence(String s, char ch, int n) {
        int occurrence = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) ==ch){
                occurrence +=1;
            }
            if(occurrence == n){
                return i;
            }
        }
        return -1;
    }

    public static Pack parsePackData(String[] data) {
        String receiver = data[1];
        String zone = data[2];
        String[] dateData = data[3].split("/");
        Date deliveryDate = new Date(Integer.parseInt(dateData[2])-1900,
                                    Integer.parseInt(dateData[0])-1,
                                        Integer.parseInt(dateData[1]));
        int weight = Integer.parseInt(data[4]);
        int volume = Integer.parseInt(data[5]);

        if (data[0].equals("S")) {
            int time = Integer.parseInt(data[6]);
            return new SpecPack(receiver, zone, deliveryDate, weight, volume, time);
        }
        else {
            return new Pack(receiver, zone, deliveryDate, weight, volume);
        }
    }

    public void assignPacks(List<Truck> truckList, List<Pack> packList) {
        List<Pack> assignedPackages = new ArrayList<>();

        for (Pack packageItem : packList) {
            if (assignedPackages.contains(packageItem)) {
                continue;
            }
            else {
                boolean assigned = false;

                for (Truck truck : truckList) {
                    int totalVolume = truckList.stream().mapToInt(Truck::totalVolume).sum();
                    int totalWeight = truckList.stream().mapToInt(Truck::totalWeight).sum();

                    if (totalVolume + packageItem.getVolume() < truck.getMaxVolume() ||
                        totalWeight + packageItem.getWeight() < truck.getMaxWeight()) {
                        truck.addPack(packageItem);
                        assigned = true;
                        assignedPackages.add(packageItem);
                        break;
                    }
                }
                if(!assigned) {
                    Truck newTruck = new LargeTruck();
                    truckList.add(newTruck);
                    newTruck.addPack(packageItem);
                    assignedPackages.add(packageItem);
                }
            }
        }
    }

}
