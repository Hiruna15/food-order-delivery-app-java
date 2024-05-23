public class Main {
    public static void main(String[] args) {
        RestaurantModel model = new RestaurantModel();
        LoginView loginView = new LoginView();
        RestaurantController controller = new RestaurantController(model, loginView);

        Thread getNewOrdersThread = new Thread(controller);
        getNewOrdersThread.start();
    }
}
