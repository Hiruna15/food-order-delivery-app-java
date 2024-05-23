public class Main {
    public static void main(String[] args) {
        CustomerModel model = new CustomerModel();
        CustomerView view = new CustomerView();
        CustomerController controller = new CustomerController(model, view);

        Thread getRestaurantsThread = new Thread(controller);
        getRestaurantsThread.start();
    }
}
