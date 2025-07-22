using PackageDeliverySystem;
using System;
using System.IO;

static void bisDay(List<int> dayNum, List<Pack> packs)
{
    for (int i = 0; i < dayNum.Count(); i++)
    {
        int temp = dayNum[i];
        Pack packTemp = packs[i];
        int low = 0;
        int high = i;

        while (low <= high)
        {
            int mid = (low + high) / 2;
            if (dayNum[mid] < temp)
            {
                low = mid + 1;
            }
            else
            {
                high = mid - 1;
            }
        }
        for (int j = i - 1; j >= low; j--)
        {
            
        }
        
    }
}

static int findNthOccurrence(String s, char ch, int n)
{
    int occurrence = 0;
    for (int i = 0; i < s.Count(); i++ )
    {
        if (s[i] == ch)
        {
            occurrence++;
        }
        if (occurrence == n)
        {
            return i;
        }
    }
    return -1;
}

static Pack parsePackData(String[] data)
{
    String receiver = data[1];
    String zone = data[2];
    String[] dateData = data[3].Split("/");
    Date deliveryDate = new Date(int.Parse(dateData[0]), 
                                    int.Parse(dateData[1]), 
                                    int.Parse(dateData[2]));
    int weight = int.Parse(data[4]);
    int volume = int.Parse(data[5]);

    if (data[0] == "S")
    {
        int time = int.Parse(data[6]);
        return new SpecialPack(receiver, zone, deliveryDate, weight, volume, time);
    }
    else
    {
        return new Pack(receiver, zone, deliveryDate, weight, volume);
    }
}

static void assignPacks(List<Truck> truckList, List<Pack> packList)
{
    List<Pack> assignedPackages = new List<Pack>();
    bool assigned;

    foreach (Pack packageItem in packList)
    {
        if (assignedPackages.Contains(packageItem))
        {
            break;
        }
        else
        {
            assigned = false;
            int totalVolume = 0;
            int totalWeight = 0;
            foreach (Truck truck in truckList)
            {
                totalVolume += truck.totalVolume();
                totalWeight += truck.totalWeight();

                if (totalVolume + packageItem.Volume < truck.getMaxVolume() ||
                    totalWeight + packageItem.Weight < truck.getMaxWeight())
                {
                    truck.addPack(packageItem);
                    assigned = true;
                    assignedPackages.Add(packageItem);
                    break;
                }
            }
            if (!assigned)
            {
                LargeTruck newTruck = new LargeTruck();
                truckList.Add(newTruck);
                newTruck.addPack(packageItem);
                assignedPackages.Add(packageItem);
            }
        }
    }
}

/*
Test test = new Test();
test.startTest();
Console.WriteLine("done testing");
*/
Console.WriteLine("Enter the name of file with packages: ");
String fileName = Console.ReadLine();
String deliveryFile = "deliveries.txt";
String errorLog = "log.txt";
int smallTruckCount = 0;
int mediumTruckCount = 0;
int largeTruckCount = 0;
int totalTruckCount = 0;
int totalTruckHours = 0;
List<String> fileLinesList = new List<String>();
List<Pack> packs = new List<Pack>();
List<String> stringPacks = new List<String>();
List<String> stringDays = new List<String>();
List<int> intDays = new List<int>();
List<Truck> trucks = new List<Truck>();


// creating/managing collection of packages

int numPackages = 0;
try
{
    StreamReader sr = new StreamReader(fileName);
    String line = sr.ReadLine();
    while (line != null)
    {
        fileLinesList.Add(line);
        numPackages++;
        line = sr.ReadLine();
    }
    sr.Close();
}
catch (Exception ex)
{
    Console.WriteLine($"Exception: {ex.Message}");
}

for (int i = 0; i < numPackages; i++)
{
    String[] data = fileLinesList[i].Split(",");
    if (data[0].Equals("S") || data[0].Equals("R"))
    {
        packs.Add(parsePackData(data));
    }
    else
    {
        try
        {
            StreamWriter output = new StreamWriter(errorLog);
            output.WriteLine("IOException\n");
            output.Close();
        }
        catch (Exception ex)
        {
            Console.WriteLine($"Exception: {ex.Message}");
        }
    }
}

foreach (Pack pack in packs)
{
    stringPacks.Add(pack.DeliveryDate.toString());
}

foreach (String str in stringPacks)
{
    int indexSlash = str.IndexOf('/');
    stringDays.Add(str.Substring(indexSlash + 1, indexSlash + 3));
}

// algorithm
bisDay(intDays, packs);
Console.WriteLine("--------------------");
trucks.Add(new SmallTruck());
assignPacks(trucks, packs);


// output trucks and pack content into deliveries.txt
try
{
    StreamWriter output = new StreamWriter(deliveryFile);
    output.WriteLine("LIST OF TRUCKS WITH PACKAGE INFO");
    output.WriteLine("========================================\n\n");
    
    int truckIndex = 1;
    foreach (Truck truck in trucks)
    {
        output.WriteLine($"Truck: {truckIndex}: {truck.toString()}\n");
        output.WriteLine("--------------------\n");
        foreach (Pack pack in truck.getPackages())
        {
            output.WriteLine($"{pack.toString()}\n");
        }
        output.WriteLine("\n");
        truckIndex++;
    }
    
    output.Close();
}
catch (Exception ex)
{
    Console.WriteLine(ex.ToString());
}

// calculate and display truck hours
foreach (Truck truck in  trucks)
{
    if (truck.GetType().Name == "SmallTruck")
    {
        smallTruckCount++;
        totalTruckHours += truck.totalTruckHours();
    }
    else if (truck.GetType().Name == "MediumTruck")
    {
        mediumTruckCount++;
        totalTruckHours += truck.totalTruckHours();
    }
    else if (truck.GetType().Name == "LargeTruck")
    {
        largeTruckCount++;
        totalTruckHours += truck.totalTruckHours();
    }
}

Console.WriteLine($"Number of Small Trucks: {smallTruckCount}");
Console.WriteLine($"Number of Medium Trucks: {mediumTruckCount}");
Console.WriteLine($"Number of Large Trucks: {largeTruckCount}");
Console.WriteLine($"Total Truck Hours: {totalTruckHours}");


    
