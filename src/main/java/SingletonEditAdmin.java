public class SingletonEditAdmin {
    private static SingletonEditAdmin instance = null;
    public static SingletonEditAdmin getInstance(){

        if(instance == null){
            instance = new SingletonEditAdmin();
        }

        return instance;
    }

    private Admin admin;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
