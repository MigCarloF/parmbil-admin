public class SingletonEditFarmer {
    private static SingletonEditFarmer instance = null;
    public static SingletonEditFarmer getInstance(){

        if(instance == null){
            instance = new SingletonEditFarmer();
        }

        return instance;
    }

    private Farmer farmer;

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
}
