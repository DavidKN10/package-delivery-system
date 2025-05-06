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
    public static void main(String[] args) throws IOException {

        //input file reading
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of file with packages: ");
        String fileName = scan.nextLine();
        String deliveryFile ="deliveries.txt";
        String errorLog = "log.txt";

        //creating/manging collection of package objects

        //to count how many packages are in the text file
        int numPackages = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            while((br.readLine()) != null) {
                numPackages++;
            }
        } catch (IOException e) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(errorLog, true));
            writer.write("IOException");
            writer.newLine();;
            writer.close();
        }

        //reading file
        File file = new File(fileName);
        Scanner reader = new Scanner(file);
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Pack> packs = new ArrayList<>();

        //Make an array of strings. Each element will be one line in the text file
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //convert each string into a pack object and put in a pack array
        for (int i=0; i<numPackages; i++) {
            String[] data = list.get(i).split(",");
            if (data[0].equals("S") || data[0].equals("R")) {
                packs.add(parsePackData(data));
            } else {
                try {
                    BufferedWriter br = new BufferedWriter(new FileWriter(errorLog, true));
                    br.write("IOException\n");
                    br.close();;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        /*
        make an ArrayList of packs
        when a line in the text file is incorrectly formatted,
        the packs array will make a pack that is null
        when we convert it to an ArrayList,
        it will be able to remove that null object
         */

        List<String> stringPacks = new ArrayList<>();
        for (Pack pack : packs) {
            stringPacks.add(pack.getDeliveryDate().toString());
        }

        List<String> days = new ArrayList<>();
        for(String str : stringPacks) {
            int indexSlash = str.indexOf("/");
            days.add(str.substring(indexSlash+1, indexSlash+3));
        }


        ArrayList<Integer> intDays = new ArrayList<>();
        for (String str : days) {
            intDays.add(Integer.parseInt(str));
        }

        //implementation of algorithm

        //sort by day
        bisDay(intDays, packs);
        System.out.println("--------------------------");
        ArrayList<Truck> trucks = new ArrayList<>();
        trucks.add(new SmallTruck());
        assignPacks(trucks, packs);



        //output trucks and content of trucks into deliveries.txt
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(deliveryFile));
            br.write("LIST OF TRUCKS WITH PACKAGE INFO\n");
            br.write("================================\n");
            br.newLine();
            br.newLine();
            int truckIndex = 1;
            for(Truck truck : trucks) {
                br.write("Truck " + truckIndex + ": "+ truck.toString() + "\n");
                br.write("-------------------\n");
                for(Pack pack : truck.getPackages()) {
                    br.write(pack.toString() + "\n");
                }
                br.newLine();
                truckIndex++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //calculate and display truck hours
        int smallTruckCount = 0;
        int mediumTruckCount = 0;
        int largeTruckCount = 0;
        int totalTruckHours = 0;

        for(Truck truck : trucks) {
            if (truck instanceof SmallTruck) {
                smallTruckCount++;
                totalTruckHours += truck.totalTruckHours(); //Small Truck: 1 hour
            }else if (truck instanceof MediumTruck) {
                mediumTruckCount++;
                totalTruckHours += 2 * truck.totalTruckHours(); //Medium Truck: 2 hours
            }else if (truck instanceof LargeTruck) {
                largeTruckCount++;
                totalTruckHours += 3 * truck.totalTruckHours(); //Large Truck: 3 hours
            }
        }

        //display number of each type of truck and total truck hours to screen
        System.out.println("Number of Small Trucks: " + smallTruckCount);
        System.out.println("Number of Medium Trucks: " + mediumTruckCount);
        System.out.println("Number of Large Trucks: " + largeTruckCount);
        System.out.println("Total Truck Hours: " + totalTruckHours);

    }

    public static void bisDay(ArrayList<Integer> dayNum, ArrayList<Pack> packs) {
        for(int i = 0; i < dayNum.size(); i++){
            int temp = dayNum.get(i);
            Pack temps = packs.get(i);
            int low = 0;
            int high = i;
            while(low <= high){
                int mid = (low+high)/2;
                if(dayNum.get(mid) < temp){
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }
            for(int j=i-1; j>=low; j--){
                dayNum.set(j+1, dayNum.get(j));
                packs.set(j+1, packs.get(j));
            }
            dayNum.set(low, temp);
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
        Date deliveryDate = new Date(Integer.parseInt(dateData[0]),
                                    Integer.parseInt(dateData[1]),
                                        Integer.parseInt(dateData[2]));
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

    public static void assignPacks(ArrayList<Truck> truckList, ArrayList<Pack> packList) {
        List<Pack> assignedPackages = new ArrayList<>();
        boolean assigned;

        for (Pack packageItem : packList) {
            if (assignedPackages.contains(packageItem)) {
                break;
            }
            else {
                assigned = false;
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
                    LargeTruck newTruck = new LargeTruck();
                    truckList.add(newTruck);
                    newTruck.addPack(packageItem);
                    assignedPackages.add(packageItem);
                }
            }
        }
    }

}
