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

    /*
    default constructor - sets receiver as "Default Company", zone as "A1", date as 1/1/2000,
    weight as 1, volume as 1. Also sets packageID as the current value of count and then increases
    the value of count. This is so when a new pack is created, it will have packageID = 2.
    */
    public Pack(){
        setReceiver(DEFAULT_RECEIVER);
        setZone(DEFAULT_ZONE);
        deliveryDate = DEFAULT_DATE;
        setWeight(DEFAULT_WEIGHT);
        setVolume(DEFAULT_VOLUME);
        packageID = count;
        count++;
    }


    /*
    non-default constructor - passes parameters to setReceiver, setZone, setDeliveryDate,
    setWeight, and setVolume. It also sets packageID to current value of count.
     */
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

    //getReceiver - return the receiver
    public String getReceiver(){return receiver;}

    //getZone - returns the delivery zone
    public String getZone(){return zone;}

    //getDeliveryDate - returns the date
    public Date getDeliveryDate(){return new Date(deliveryDate);}

    //getWeight - returns the weight
    public int getWeight(){return weight;}

    //getVolume - returns the volume
    public int getVolume(){return volume;}

    //getPackageID - return packageID
    public int getPackageID(){return packageID;}

    //getTime - returns 17, the default time, this will be changed for SpecPack
    public int getTime(){return 17;}

    //mutator methods

    /*
    setReceiver - will check if name is not null so receiver = name.
    Otherwise, receiver = DEFAULT_RECEIVER
     */
    public void setReceiver(String name){
        if(name!=null){
            receiver = name;
        }
        else receiver = DEFAULT_RECEIVER;
    }

    /*
    setZone - Will check if letter part of the zone is from A-Z
    and the number part from 1-9. If both cases are true, it will
    set the package's this.zone to zone.
    If not, it will set the package's this.zone to DEFAULT_ZONE.
     */
    public void setZone(String zone){
        if(zone!=null && zone.charAt(0)>='A' && zone.charAt(0)<='Z' &&
                zone.charAt(1)>='1' && zone.charAt(1)<='9') {
            this.zone = zone;
        }
        else this.zone = DEFAULT_ZONE;
    }

    /*
    setDeliveryDate - will check if d is null to make a new Date out of d.
    If not, then it will set the default date.
     */
    public void setDeliveryDate(Date d){
        if(d!=null) {
            deliveryDate = new Date(d);

        }
        else deliveryDate = DEFAULT_DATE;
    }

    /*
    setWeight - Will check if w is positive to make weight = w.
    If not, then weight will be the default weight.
     */
    public void setWeight(int w){
        if(w>0){
            weight = w;
        }
        else weight = DEFAULT_WEIGHT;
    }

    /*
    setVolume - will check if v is positive to make volume = v.
    If not, then volume will be the default volume.
     */
    public void setVolume(int v){
        if(v>0){
            volume = v;
        }
        else volume = DEFAULT_VOLUME;
    }

    /*
    toString method
    When called, will out put a string like this:
    ID:1 Company:Company Name Zone:A1 Date:1/1/2001 Weight:1 Volume:1
     */
    public String toString(){
        return "ID:"+getPackageID()+" Company:"+ getReceiver()+" Zone:"+ getZone()+" Date:"+getDeliveryDate()+
                " Weight:"+getWeight()+" Volume:"+getVolume();
    }

    /*
    compareTo - Will compare current pack with another pack.
    Pack with the letter that comes the earliest in the alphabet is considered greater.
    If the pack's letters are the same, then pack with number closest to 1 is considered greater.
    If both letter and number are the same, then pack with the greater volume is considered greater.
    If volume is also the same, then the packs are considred equal.
     */
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
