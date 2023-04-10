package apiMethods;

public class User {
    private String email;
    private String password;
    private String name;

    public User(String email, String password, String name){
        this.email = email;
        this.password =password;
        this.name = name;
    }

    public User(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String password) {
        this.name = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
