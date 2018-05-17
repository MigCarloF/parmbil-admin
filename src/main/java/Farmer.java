public class Farmer {
    private String favoriteCrop;
    private String location;
    private String name;
    private String pw;
    private String username;

    public Farmer(String favoriteCrop, String location, String name, String pw, String username){
        setFavoriteCrop(favoriteCrop);
        setLocation(location);
        setName(name);
        setPw(pw);
        setUsername(username);
    }
    public Farmer(){

    }
    /**
     * getters and setters
     */
    public String getFavoriteCrop() {
        return favoriteCrop;
    }

    public void setFavoriteCrop(String favoriteCrop) {
        this.favoriteCrop = favoriteCrop;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}