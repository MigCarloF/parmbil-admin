public class Admin {
    public String username;
    public String password;
    public String name;
    public boolean active;

    public Admin(String username, String password, String name, boolean active){
        this.username = username;
        this.password = password;
        this.name = name;
        this.active = active;
    }


    //Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
