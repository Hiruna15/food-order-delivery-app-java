public class Main {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        DriverModel model = new DriverModel();
        DriverController controller = new DriverController(loginView, model);
    }
}