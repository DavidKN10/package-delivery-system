using System;

namespace PackageDeliverySystem
{
    class Test
    {
        public void startTest()
        {
            // Date object testing
            Console.WriteLine("========== Date Object Testing ==========");
            Console.WriteLine("Constructor Testing");
            Date date1 = new Date();
            Date date2 = new Date(2, 24, 2004);
            Console.WriteLine("Default Construtor: " + date1.toString());
            Console.WriteLine("Non-Default Construtor: " + date2.toString());
            Console.WriteLine("Testing Getters");
            Console.WriteLine("Month: " + date2.Month);
            Console.WriteLine("Day: " + date2.Day);
            Console.WriteLine("Year: " + date2.Year); 
            Console.WriteLine("Testing Setters");
            Date date3 = new Date(7, 14, 2025);
            Console.WriteLine("Before: " + date3.toString());
            date3.setDate(2, 24, 2022);
            Console.WriteLine("setDate(2, 24, 2022): " + date3.toString());
            Console.WriteLine("Testing Equality");
            Date date4 = new Date(5, 15, 1995);
            Date date5 = new Date(1, 1, 1990);
            Date date6 = new Date(1, 1, 1990);
            bool equalityTest1 = date4.equals(date5);
            bool equalityTest2 = date5.equals(date6);
            Console.WriteLine("Date 1: " + date4.toString() + " Date 2: " + date5.toString());
            Console.WriteLine("Result: " + equalityTest1); 
            Console.WriteLine("Date 1: " + date5.toString() + " Date 2: " + date6.toString());
            Console.WriteLine("Result: " + equalityTest2);
            // 1: >, -1: <, 0: =
            int comparisonTest1 = date4.compareTo(date5);
            int comparisonTest2 = date5.compareTo(date4);
            int comparisonTest3 = date5.compareTo(date6);
            Console.WriteLine(date4.toString() + " > " + date5.toString() + "?");
            Console.WriteLine("Result: " + comparisonTest1);
            Console.WriteLine(date5.toString() + " > " + date4.toString() + "?");
            Console.WriteLine("Result: " + comparisonTest2);
            Console.WriteLine(date5.toString() + " > " + date6.toString() + "?");
            Console.WriteLine("Result: " + comparisonTest3);

            // Pack object 
            Console.WriteLine("\n========== Pack Object Testing ==========");
            Console.WriteLine("Testing Constructors");
            Pack pack1 = new Pack();
            Pack pack2 = new Pack("Cover", "G4", new Date(2, 6, 1999), 100, 200);
            Console.WriteLine("Default Consturctor: \n\t" + pack1.toString());
            Console.WriteLine("Non-Default Consturctor: \n\t" + pack2.toString());
            Console.WriteLine("Testing Getters");
            Console.WriteLine("getReceiver(): " + pack2.Receiver);
            Console.WriteLine("getZone(): " + pack2.Zone);
            Console.WriteLine("getDeliveryDate(): " + pack2.DeliveryDate.toString());
            Console.WriteLine("getTime(): " + pack2.Time);
            Console.WriteLine("Testing Setters");
            Console.WriteLine("Before Setters: \n\t" + pack2.toString());
            pack2.Receiver = "Other Company";
            pack2.Zone = "E2";
            pack2.DeliveryDate = new Date(9, 13, 2020);
            pack2.Weight = 445;
            pack2.Volume = 300;
            Console.WriteLine("After Setters: \n\t" + pack2.toString());
            Console.WriteLine("Testing Comparison");
            Pack pack3 = new Pack("Company", "A3", new Date(1, 1, 2000), 300, 200);
            Pack pack4 = new Pack("Company", "A3", new Date(1, 1, 2002), 100, 99);
            Console.WriteLine("Pack 1: " + pack2.toString());
            Console.WriteLine("Pack 2: " + pack3.toString());
            Console.WriteLine("Pack 3: " + pack4.toString());
            int result1 = pack2.compareTo(pack3);
            int result2 = pack3.compareTo(pack2);
            int result3 = pack3.compareTo(pack4);
            Console.WriteLine("Pack 1 > Pack 2: " + result1);
            Console.WriteLine("Pack 1 < Pack 2: " + result2);
            Console.WriteLine("Pack 2 > Pack 3: " + result3);
            
            // Special Pack object testing
            Console.WriteLine("\n========== Special Pack Object Testing ==========");
            Console.WriteLine("Testing Constructors");
            SpecialPack specPack1 = new SpecialPack();
            SpecialPack specPack2 = new SpecialPack("Compnay1", "J3", new Date(5, 19, 2025), 193, 432, 11);
            Console.WriteLine("Default Constructor: \n\t" + specPack1.toString());
            Console.WriteLine("Non-Default Constructor: \n\t" + specPack2.toString());
            Console.WriteLine($"Testing Time: {specPack2.Time}");
            specPack2.Time = 10;
            Console.WriteLine($"Testing Time = 10: {specPack2.Time}");
            
            // Truck objects testing
            Console.WriteLine("\n========== Truck Objects Testing ==========");
            Console.WriteLine("\n---------- Small Truck Testing ----------");
            Pack packOne = new Pack("Company1", "A1", new Date(5, 19, 2025), 200, 300);
            Pack packTwo = new Pack("Company2", "A2", new Date(5, 20, 2025), 300, 100);
            Pack packThree = new Pack("Company3", "A3", new Date(5, 21, 2025), 12, 34);
            SmallTruck st1 = new SmallTruck();
            Console.WriteLine("Creating Empty Truck:");
            Console.WriteLine($"\t{st1.toString()}");
            Console.WriteLine("SmallTruck after adding three packs: ");
            st1.addPack(packOne);
            st1.addPack(packTwo);
            st1.addPack(packThree);
            Console.WriteLine($"\t{st1.toString()}");
            Console.WriteLine("SmallTruck after removing one pack:");
            st1.removePack(packThree);
            Console.WriteLine($"\t{st1.toString()}");
            Console.WriteLine($"SmallTruck getTruckHours(): {st1.getTruckHours()}");
            Console.WriteLine($"SmallTruck totalTruckHours(): {st1.totalTruckHours()}");
            Console.WriteLine($"SmallTruck getMaxWeight(): {st1.getMaxWeight()}");
            Console.WriteLine($"SmallTruck getMaxVolume(): {st1.getMaxVolume()}");
            Console.WriteLine("Display packs in small truck:");
            foreach (Pack pack in st1.getPackages())
            {
                Console.WriteLine($"\t{pack.toString()}");
            }
            Console.WriteLine("Show all small trucks with different IDs");
            SmallTruck st2 = new SmallTruck();
            SmallTruck st3 = new SmallTruck();
            Console.WriteLine($"\t{st1.toString()}");
            Console.WriteLine($"\t{st2.toString()}");
            Console.WriteLine($"\t{st3.toString()}");

            Console.WriteLine("\n---------- Medium Truck Testing ----------");
            Pack packFour = new Pack("Company1", "A1", new Date(5, 19, 2025), 200, 300);
            Pack packFive = new Pack("Company2", "A2", new Date(5, 20, 2025), 300, 100);
            Pack packSix = new Pack("Company3", "A3", new Date(5, 21, 2025), 12, 34);
            MediumTruck mt1 = new MediumTruck();
            Console.WriteLine("Creating Empty Truck:");
            Console.WriteLine($"\t{mt1.toString()}");
            Console.WriteLine("MediumTruck after adding three packs:");
            mt1.addPack(packFour);
            mt1.addPack(packFive);
            mt1.addPack(packSix);
            Console.WriteLine($"\t{mt1.toString()}");
            Console.WriteLine($"MediumTruck getTruckHours(): {mt1.getTruckHours()}");
            Console.WriteLine($"MediumTruck totalTruckHours(): {mt1.totalTruckHours()}");
            Console.WriteLine($"MediumTruck getMaxWeight(): {mt1.getMaxWeight()}");
            Console.WriteLine($"MediumTruck getMaxVolume(): {mt1.getMaxVolume()}");
            Console.WriteLine("Display packs in small truck:");
            foreach (Pack pack in mt1.getPackages())
            {
                Console.WriteLine($"\t{pack.toString()}");
            }
            Console.WriteLine("\n---------- Large Truck Testing ----------");  
            Pack packSeven = new Pack("Company1", "A1", new Date(5, 19, 2025), 200, 300);
            Pack packEight = new Pack("Company2", "A2", new Date(5, 20, 2025), 300, 100);
            Pack packNine = new Pack("Company3", "A3", new Date(5, 21, 2025), 12, 34);
            LargeTruck lt1 = new LargeTruck();
            Console.WriteLine("Creating Empty Truck:");
            Console.WriteLine($"\t{st1.toString()}");
            Console.WriteLine("LargeTruck after adding three packs:");
            st1.addPack(packSeven);
            st1.addPack(packEight);
            st1.addPack(packNine);
            Console.WriteLine($"\t{st1.toString()}");
            Console.WriteLine("LargeTruck after removing one pack:");
            st1.removePack(packEight);
            Console.WriteLine($"\t{st1.toString()}");
            Console.WriteLine($"LargeTruck getTruckHours(): {lt1.getTruckHours()}");
            Console.WriteLine($"LargeTruck totalTruckHours(): {lt1.totalTruckHours()}");
            Console.WriteLine($"LargeTruck getMaxWeight(): {lt1.getMaxWeight()}");
            Console.WriteLine($"LargeTruck getMaxVolume(): {lt1.getMaxVolume()}");
            Console.WriteLine("Display packs in small truck:");
            foreach (Pack pack in lt1.getPackages())
            {
                Console.WriteLine($"\t{pack.toString()}");
            }
            Console.WriteLine("Show all small trucks with differnt IDs");
            SmallTruck st4 = new SmallTruck();
            SmallTruck st5 = new SmallTruck();
            Console.WriteLine($"\t{st1.toString()}");
            Console.WriteLine($"\t{st2.toString()}");
            Console.WriteLine($"\t{st3.toString()}");
            Console.WriteLine($"\t{st4.toString()}");
            Console.WriteLine($"\t{st5.toString()}");
 
        }
    }
}
