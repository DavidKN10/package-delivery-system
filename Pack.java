public class Pack implements Comparable<Pack> {
    private int packageID;
    private static int count=1;
    private String receiver;
    private String zone;
    private Date deliveryDate =new Date();
    private int weight;
    private int volume;

    private static final String DEFAULT_RECEIVER = "Default Company";
    private static final String DEFAULT_ZONE = "A1";
    private static final Date DEFAULT_DATE = new Date();
    private static final int DEFAULT_WEIGHT = 1;
    private static final int DEFAULT_VOLUME = 1;

    //default constructor
    public Pack(){
        setReceiver(DEFAULT_RECEIVER);
        setZone(DEFAULT_ZONE);
        deliveryDate = DEFAULT_DATE;
        setWeight(DEFAULT_WEIGHT);
        setVolume(DEFAULT_VOLUME);
        packageID = count;
        count++;
    }

    //non-default constructor
    public Pack(String receiver, String zone, Date date, int weight, int volume){
        setReceiver(receiver);
        setZone(zone);
        setDeliveryDate(date);
        setWeight(weight);
        setVolume(volume);
        packageID = count;
        count++;
    }

    //accessor methods
    public String getReceiver(){return receiver;}
    public String getZone(){return zone;}
    public Date getDeliveryDate(){return new Date(deliveryDate);}
    public int getWeight(){return weight;}
    public int getVolume(){return volume;}
    public int getPackageID(){return packageID;}
    public int getTime(){return 17;}

    //mutator methods
    public void setReceiver(String name){
        if(name!=null){
            receiver = name;
        }
        else receiver = DEFAULT_RECEIVER;
    }
    public void setZone(String zone){
        if(zone!=null && zone.charAt(0)>='A' && zone.charAt(0)<='Z' &&
                zone.charAt(1)>='1' && zone.charAt(1)<='9') {
            this.zone = zone;
        }
        else this.zone = DEFAULT_ZONE;
    }
    public void setDeliveryDate(Date d){
        if(d!=null) {
            deliveryDate = new Date(d);

        }
        else deliveryDate = DEFAULT_DATE;
    }
    public void setWeight(int w){
        if(w>0){
            weight = w;
        }
        else weight = DEFAULT_WEIGHT;
    }
    public void setVolume(int v){
        if(v>0){
            volume = v;
        }
        else volume = DEFAULT_VOLUME;
    }

    //toString method
    public String toString(){
        return "ID:"+getPackageID()+" Company:"+ getReceiver()+" Zone:"+ getZone()+" Date:"+getDeliveryDate()+
                " Weight:"+getWeight()+" Volume:"+getVolume();
    }

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
