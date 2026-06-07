package models;

public class registerUser {
    private  String name;
    private  String email;
    public  registerUser(){}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public registerUser(String name, String email){
        this.email=email;
        this.name=name;
    }
}
