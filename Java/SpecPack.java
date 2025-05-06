public class SpecPack extends Pack implements Comparable<Pack>{
    private int time;
    private static final int DEFAULT_TIME = 9;

    //default constructor
    public SpecPack() throws InvalidSpecPackException{
        super();
        setTime(DEFAULT_TIME);
    }

    //non-default constructor
    public SpecPack(String receiver, String zone, Date date, int weight, int volume, int time){
        super(receiver, zone, date, weight, volume);
        setTime(time);
    }

    //accessor method
    public int getTime(){return time;}

    //mutator method
    public void setTime(int time){
        if(time>=9 && time<=16){
            this.time = time;
        }
        else this.time = DEFAULT_TIME;
    }

    //toString method
    public String toString(){return super.toString()+" Time:"+getTime();}

    //compareTo method
    public int compareTo(Pack pack){
        if(this.getZone().charAt(0) == pack.getZone().charAt(0)){
            if(this.getZone().charAt(1) == pack.getZone().charAt(1)){
                if(this.getVolume() > pack.getVolume()){
                    return 1;
                }
                else if(this.getVolume() < pack.getVolume()){
                    return -1;
                }
                else return 0;
            }
            else if(this.getZone().charAt(1) < pack.getZone().charAt(1)){
                return 1;
            }
            else return -1;
        }
        else if(this.getZone().charAt(0) > pack.getZone().charAt(0)){
            return -1;
        }
        else if(this.getZone().charAt(0) < pack.getZone().charAt(0)){
            return 1;
        }
        else return 0;
    }

}
