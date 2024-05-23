public class Main {
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        AdminModel model = new AdminModel();
        AdminController controller = new AdminController(model, loginView);

        Thread getToBeApprovedRestsAndDriversThread = new Thread(controller);
        getToBeApprovedRestsAndDriversThread.start();
    }
}
