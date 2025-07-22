namespace PackageDeliverySystem
{
    class SpecialPack : Pack
    {
        private int time; 
        private static int DEFAULT_TIME = 17;

        public SpecialPack() : base()
        {
            this.Time = DEFAULT_TIME;
        }

        public SpecialPack(String receiver, String zone, Date date, int weight, int volume, int time) 
            : base(receiver, zone, date, weight, volume)
        {
            this.Time = time;
        }

        public override int Time
        {
            get { return time; }
            set 
            { 
                if (value >= 0 && time <=16)
                {
                    time = value;
                }
                else
                {
                    time = DEFAULT_TIME;
                }
            }
        }

        public override String toString()
        {
            return $"ID:{this.PackageID} Company:{this.Receiver} Zone:{this.Zone} Date:{this.DeliveryDate.toString()}" +
                $" Weight:{this.Weight} Volume:{this.Volume} Time:{this.Time}";
        }
    }
}
