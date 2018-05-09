import java.util.StringTokenizer;

public class Admin {
    public boolean active;
    public String name;
    public String password;
    public String username;

    public Admin(boolean active, String name, String password, String username){
        this.active = active;
        this.name = name;
        this.password = password;
        this.username = username;
    }

    public Admin() {}

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//Getters and setters

}
