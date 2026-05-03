package carRentalSystem.models;

public class User {
    private String userId;
    private String name;

    public User(String userId, String name){
        this.userId = userId;
        this.name = name;
    }

    public String getId(){
        return userId;
    }

    public String getName(){
        return name;
    }

}
