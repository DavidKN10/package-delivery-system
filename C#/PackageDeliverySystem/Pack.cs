namespace PackageDeliverySystem
{
    class Pack
    {
        private int packageID;
        private static int count = 1;
        private String? receiver;
        private String? zone;
        private Date deliveryDate = new Date();
        private int weight;
        private int volume;

        private static String DEFAULT_RECEIVER = "Default Company";
        private static String DEFAULT_ZONE = "A1";
        private static Date DEFAULT_DATE = new Date();
        private static int DEFAULT_WEIGHT = 1;
        private static int DEFAULT_VOLUME = 1;

        public Pack()
        {
            this.Receiver = DEFAULT_RECEIVER;
            this.Zone = DEFAULT_ZONE;
            this.DeliveryDate = DEFAULT_DATE;
            this.Weight = DEFAULT_WEIGHT;
            this.Volume = DEFAULT_VOLUME;
            packageID = count;
            count++;
        }

        public Pack(string? receiver, string? zone, Date date, int weight, int volume)
        {
            this.Receiver = receiver;
            this.Zone = zone;
            this.DeliveryDate = date;
            this.Weight = weight;
            this.volume = volume; 
            packageID = count;
            count++;
        }

        public string Receiver
        {
            get { return receiver; }
            set 
            {
                if (value != null)
                {
                    receiver = value;
                }
                else
                {
                    receiver = DEFAULT_RECEIVER;
                }
            }
        }

        public string Zone
        {
            get { return zone; }
            set 
            {
                if (value != null && value[0] >= 'A' && value[0] <= 'Z' &&
                    value[1] >= '1' && value[1] <= '9')
                {
                    zone = value;
                }
                else
                {
                    zone = DEFAULT_ZONE;
                } 
            }
        }

        public Date DeliveryDate
        {
            get { return new Date(deliveryDate);  }
            set 
            {
                if (value != null)
                {
                    deliveryDate = new Date(value);
                }
                else
                {
                    deliveryDate = DEFAULT_DATE;
                }
            }
        }

        public int Weight
        {
            get { return weight; }
            set 
            { 
                if (value > 0)
                {
                    weight = value;
                }
                else
                {
                    weight = DEFAULT_WEIGHT;
                }
            }
        }

        public int Volume
        {
            get { return volume; }
            set 
            { 
                if (value > 0)
                {
                    volume = value;
                }
                else
                {
                    volume = DEFAULT_VOLUME;
                }
            }
        }

        public int PackageID
        {
            get { return packageID; }
        }

        public virtual int Time
        {
            get { return 17; }
            set { }
        } 

        public virtual String toString()
        {
            return $"ID:{this.PackageID} Company:{this.Receiver} Zone:{this.Zone} " +
                $" Date:{this.DeliveryDate.toString()} Weight:{this.Weight} Volume:{this.Volume}";
        }

        public int compareTo(Pack pack)
        {
            if (this.Zone[0] == pack.Zone[0])
            {
                if (this.Zone[1] == pack.Zone[1])
                {
                    if (this.Volume > pack.Volume)
                    {
                        return 1;
                    }
                    else if (this.Volume < pack.Volume)
                    {
                        return -1;
                    }
                    else return 0;
                }
                else if (this.Zone[1] < pack.Zone[1])
                {
                    return 1;
                }
                else return -1;
            }
            else if (this.Zone[0] > pack.Zone[0])
            {
                return -1;
            }
            else if (this.Zone[0] < pack.Zone[0])
            {
                return 1;
            }
            else return 0;
        }
    }
}
