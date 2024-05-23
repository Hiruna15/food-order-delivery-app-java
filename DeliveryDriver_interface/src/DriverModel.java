import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class DriverModel {
    private static final String URL = "jdbc:mysql://localhost:3306/food_delivery_app";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static int loggedUserId;
    private String loggedUsername;
    private Boolean isSideBarOpen = false;

    private Boolean isOrdersPanelOpen = true;

    private static int pickedOrderId;
    private int deliveryOrderSubTotal;
    private int deliveryOrderTotal;
    private String deliveryOrderCode;
    private String customerEmail;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getDeliveryOrderCode() {
        return deliveryOrderCode;
    }

    public int getDeliveryOrderTotal() {
        return deliveryOrderTotal;
    }

    public void setDeliveryOrderTotal(int total) {
        deliveryOrderTotal = total;
    }

    public int getDeliveryOrderSubTotal() {
        return deliveryOrderSubTotal;
    }

    public static int getPickedOrderId() {
        return pickedOrderId;
    }

    public static void setPickedOrderId(int orderId) {
        pickedOrderId = orderId;
    }

    public Boolean getIsOrdersPanelOpen() {
        return isOrdersPanelOpen;
    }

    public void setIsOrdersPanelOpen(Boolean isOpen) {
        isOrdersPanelOpen = isOpen;
    }

    public Boolean getIsSideBarOpen() {
        return isSideBarOpen;
    }

    public void setIsSideBarOpen(Boolean isOpen) {
        isSideBarOpen = isOpen;
    }

    static Connection con;

    public static int getLoggedUserId() {
        return loggedUserId;
    }

    public String getLoggedUsername() {
        return loggedUsername;
    }

    public void setLoggedUserId(int id) {
        loggedUserId = id;
    }

    public void setLoggedUsername(String username) {
        loggedUsername = username;
    }


    {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean logTheUser(String username, String pwd){
        try {
            String sql = "select id, username from deliverydriver where username=? and password=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, pwd);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                loggedUserId = resultSet.getInt("id");
                loggedUsername = resultSet.getString("username");
                return true;
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public List<List<Object>> getOrdersToBeDelivered(){
        List<List<Object>> orders = new ArrayList<>();
        try {
            String sql = "select address, orderTotal, orderID, orderCode, restID, userID from orders where status=? and orderType=? and date=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "prepared");
            statement.setString(2, "delivery");
            LocalDate currentDate = LocalDate.now();
            statement.setDate(3, Date.valueOf(currentDate));

            ResultSet resultSet1 = statement.executeQuery();
            while(resultSet1.next()){
                List<Object> order = new ArrayList<>();
                order.add(resultSet1.getInt("orderID"));
                order.add(resultSet1.getInt("orderTotal"));
                order.add(resultSet1.getString("orderCode"));
                order.add(resultSet1.getString("address"));

                sql = "select branchName, address from restaurants where id=?";
                statement = con.prepareStatement(sql);
                statement.setInt(1, resultSet1.getInt("restID"));
                ResultSet resultSet2 = statement.executeQuery();
                resultSet2.next();
                order.add(resultSet2.getString("branchName"));
                order.add(resultSet2.getString("address"));

                sql = "select username from customer where customerID=?";
                statement = con.prepareStatement(sql);
                statement.setInt(1, resultSet1.getInt("userID"));
                resultSet2 = statement.executeQuery();
                resultSet2.next();
                order.add(resultSet2.getString("username"));

                orders.add(order);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return orders;
    }

    public List<Object> getCheckOutPanelDetails(int orderId){
        List<Object> orderDetails = new ArrayList<>();
        try {
            String sql = "select orderCode, orderTotal, address, restID, userID from orders where orderID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            orderDetails.add(resultSet.getString("orderCode"));
            deliveryOrderCode = resultSet.getString("orderCode");
            orderDetails.add(resultSet.getInt("orderTotal"));
            deliveryOrderSubTotal = resultSet.getInt("orderTotal");
            orderDetails.add(resultSet.getString("address"));
            int restId = resultSet.getInt("restID");
            int userId = resultSet.getInt("userID");

            sql = "select branchName, address from restaurants where id=?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, restId);
            resultSet = statement.executeQuery();
            resultSet.next();
            orderDetails.add(resultSet.getString("branchName"));
            orderDetails.add(resultSet.getString("address"));

            sql = "select username, email from customer where customerID=?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            resultSet.next();
            orderDetails.add(resultSet.getString("username"));
            customerEmail = resultSet.getString("email");
            orderDetails.add(orderId);
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return orderDetails;
    }

    public List<List<Object>> getCheckOutPanelOrderItems(int orderId){
        List<List<Object>> orderItems = new ArrayList<>();
        try{
            String sql = "select * from orderitems where orderID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, orderId);
            ResultSet resultSet =  statement.executeQuery();
            ResultSet resultSet1;
            while (resultSet.next()){
                List<Object> item = new ArrayList<>();
                int itemId = resultSet.getInt("itemID");
                item.add(resultSet.getString("itemName"));
                item.add(resultSet.getInt("itemQnt"));
                item.add(resultSet.getInt("itemTotalPrice"));

                sql = "select image from menus where itemID=?";
                statement = con.prepareStatement(sql);
                statement.setInt(1, itemId);
                resultSet1 = statement.executeQuery();
                BufferedImage image = null;
                if(resultSet1.next()){
                    Blob blob = resultSet1.getBlob("image");
                    if(blob != null){
                        byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                        image = ImageIO.read(bis);
                        bis.close();
                    }
                }

                item.add(image);

                orderItems.add(item);
            }
        }catch(SQLException | IOException ex){
            ex.printStackTrace();
        }

        return orderItems;
    }

    public static void addPickedUpOrderToDriverOrdersTable(int driverId, int orderId, int dCharge, String status){
        try {
            String sql = "insert into driverorders (driverId, orderId, deliveryCharge, status) values (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, driverId);
            statement.setInt(2, orderId);
            statement.setInt(3, dCharge);
            statement.setString(4, status);
            statement.executeUpdate();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void updateTheOrderStatusAsCompleted(int orderId, String status, int deliveryCharge){
        try {
            String sql = "update orders set status=? where orderID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, orderId);
            statement.executeUpdate();

            sql = "update driverorders set status=?, deliveryCharge=? where driverId=? and orderId=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, deliveryCharge);
            statement.setInt(3, loggedUserId);
            statement.setInt(4, orderId);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void updateOrderStatusAsDeliveringInOrdersTable(int orderId){
        try {
            String sql = "update orders set status=? where orderID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "delivering");
            statement.setInt(2, orderId);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public static List<Object> checkIfDriverHasAOngoingOrder(){
        List<Object> deliveryOrderPanelDetails = new ArrayList<>();
        try {
            String sql = "select orderId from driverorders where driverId=? and status=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, loggedUserId);
            statement.setString(2, "delivering");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int orderId = resultSet.getInt("orderId");
                sql = "select orderCode, restID from orders where orderID=?";
                statement = con.prepareStatement(sql);
                statement.setInt(1, orderId);
                resultSet = statement.executeQuery();
                resultSet.next();
                String orderCode = resultSet.getString("orderCode");
                int restId = resultSet.getInt("restID");

                sql = "select branchName from restaurants where id=?";
                statement = con.prepareStatement(sql);
                statement.setInt(1, restId);
                resultSet = statement.executeQuery();
                resultSet.next();
                String branchName = resultSet.getString("branchName");

                deliveryOrderPanelDetails.add(orderId);
                deliveryOrderPanelDetails.add(orderCode);
                deliveryOrderPanelDetails.add(branchName);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return deliveryOrderPanelDetails;
    }
}
