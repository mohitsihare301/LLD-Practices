package makeMyTrip.models;

public class Airport {
    private String id;
    private String name;
    private City city;

    public Airport(String id,String name,City city){
        this.id=id;
        this.name=name;
        this.city=city;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public City getCity(){
        return city;
    }
}
