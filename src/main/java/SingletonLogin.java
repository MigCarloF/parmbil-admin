public class SingletonLogin {
    private static SingletonLogin instance = null;

    private SingletonLogin(){}

    public static SingletonLogin getInstance(){
        if(instance == null){
            instance = new SingletonLogin();
        }
        return instance;
    }

    private Admin currentLogin;

    public Admin getCurrentLogin() {
        return currentLogin;
    }

    public void setCurrentLogin(Admin currentLogin) {
        this.currentLogin = currentLogin;
    }
}
