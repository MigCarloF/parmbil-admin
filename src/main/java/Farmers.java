public class Farmers {
    private String favoriteCrop;
    private String location;
    private String name;
    private String pw;

    private String username;

    public Farmers(String favoriteCrop, String location, String name, String pw, String username){
        this.favoriteCrop =favoriteCrop;
        this.location = location;
        this.name = name;
        this.pw = pw;
        this.username = username;
    }

    /**
     * getters and setters
     */

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFavoriteCrop() {
        return favoriteCrop;
    }

    public void setFavoriteCrop(String favoriteCrop) {
        this.favoriteCrop = favoriteCrop;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
