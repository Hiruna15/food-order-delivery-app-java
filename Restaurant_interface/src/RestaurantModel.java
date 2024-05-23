import com.mysql.cj.jdbc.exceptions.PacketTooBigException;

import javax.imageio.ImageIO;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.management.BufferPoolMXBean;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class RestaurantModel {

    private static final String URL = "jdbc:mysql://localhost:3306/food_delivery_app";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private String restaurantUsrname;
    private String restaurantPassword;

    private String restBranchName;
    private String restAddress;
    private String restMobile;
    private static int restaurantId;

    public String getRestMobile() {
        return restMobile;
    }

    public String getRestBranchName() {
        return restBranchName;
    }

    public String getRestAddress() {
        return restAddress;
    }

    public int getRestaurantId(){
        return restaurantId;
    }

    private boolean isHomePanelOpen = false;
    private boolean isOrdersPanelOpen = false;
    private boolean isMenuPanelOpen = false;
    private boolean isEditPanelOpen = false;
    private boolean isCheckOutPanelOpen = false;

    public boolean isHomePanelOpen() {
        return isHomePanelOpen;
    }

    public void setHomePanelOpen(boolean homePanelOpen) {
        isHomePanelOpen = homePanelOpen;
    }

    public boolean isOrdersPanelOpen() {
        return isOrdersPanelOpen;
    }

    public void setOrdersPanelOpen(boolean ordersPanelOpen) {
        isOrdersPanelOpen = ordersPanelOpen;
    }

    public boolean isMenuPanelOpen() {
        return isMenuPanelOpen;
    }

    public void setMenuPanelOpen(boolean menuPanelOpen) {
        isMenuPanelOpen = menuPanelOpen;
    }

    public boolean isEditPanelOpen() {
        return isEditPanelOpen;
    }

    public void setEditPanelOpen(boolean editPanelOpen) {
        isEditPanelOpen = editPanelOpen;
    }

    public boolean isCheckOutPanelOpen(){
        return isCheckOutPanelOpen;
    }

    public void setCheckOutPanelOpen(boolean checkOutPanelOpen){
        isCheckOutPanelOpen = checkOutPanelOpen;
    }

    public void setRestaurantUsrname(String username){
        this.restaurantUsrname = username;
    }

    public String getRestaurantUsrname(){
        return restaurantUsrname;
    }

    public void setRestaurantPassword(String password){
        this.restaurantPassword = password;
    }

    public String getRestaurantPassword(){
        return restaurantPassword;
    }

    public boolean checkUserLogin(String username, String password){
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "select * from restaurants where username=? and password=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                restaurantId = result.getInt("id");
                restBranchName = result.getString("branchName");
                restAddress = result.getString("address");
                restMobile = result.getString("mobile");
                return true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean addMenuItem(String[] details, BufferedImage image){
        try{
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "insert into menus(restaurantID, description, image, itemName, price, category) values(?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, restaurantId);
            statement.setString(2, details[2]);
            statement.setString(4, details[0]);
            statement.setInt(5, Integer.parseInt(details[1]));
            statement.setString(6, details[3]);

            byte[] imageBytes = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            imageBytes = baos.toByteArray();
            statement.setBytes(3, imageBytes);
            statement.executeUpdate();
            return true;
        }catch(SQLException | IOException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static List<List<Object>> getMenu(){
        List<List<Object>> menuItems = new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "select * from menus where restaurantID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, restaurantId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                List<Object> menuItemsDetails = new ArrayList<>();
                menuItemsDetails.add(resultSet.getInt("itemID"));
                menuItemsDetails.add(resultSet.getString("itemName"));
                menuItemsDetails.add(resultSet.getInt("price"));
                menuItemsDetails.add(resultSet.getString("description"));

                Blob blob = resultSet.getBlob("image");
                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                menuItemsDetails.add(imageBytes);

                menuItemsDetails.add(resultSet.getString("category"));

                menuItems.add(menuItemsDetails);
            }

            return menuItems;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static int deleteMenuItem(int id){
        try{
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "delete from menus where itemID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            int efectedRows = statement.executeUpdate();
            return efectedRows;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public boolean updateMenuItems(List<List<Object>> menuItems){
        try{
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "update menus set itemName=?, price=?, description=?, image=?, category=? where itemID=?";
            PreparedStatement statement = con.prepareStatement(sql);

            for(List<Object> menuItem : menuItems){
                int id = (int) menuItem.get(0);
                String name = (String) menuItem.get(1);
                int price = (int) menuItem.get(2);
                String desc = (String) menuItem.get(3);
                String category = (String) menuItem.get(4);
                BufferedImage image = (BufferedImage) menuItem.get(5);

                statement.setString(1, name);
                statement.setInt(2, price);
                statement.setString(3, desc);

                byte[] imageBytes = null;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", baos);
                imageBytes = baos.toByteArray();
                statement.setBytes(4, imageBytes);


                statement.setString(5, category);
                statement.setInt(6, id);
                statement.executeUpdate();
                statement.clearParameters();
            }

            return true;

        }catch(SQLException | IOException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public List<Object> getRestaurantDetails(){
        try{
            List<Object> restaurantDetails = new ArrayList<>();
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "select * from restaurants where id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, restaurantId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            Blob blob = resultSet.getBlob("image");
            BufferedImage image = null;
            if(blob != null){
                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                image = ImageIO.read(bis);
                bis.close();
            }

            restaurantDetails.add(resultSet.getString("name"));
            restaurantDetails.add(resultSet.getString("address"));
            restaurantDetails.add(resultSet.getString("mobile"));
            restaurantDetails.add(resultSet.getString("city"));
            restaurantDetails.add(resultSet.getInt("postalCode"));
            restaurantDetails.add(image);
            restaurantDetails.add(resultSet.getString("allowType"));
            restaurantDetails.add(resultSet.getString("isLive"));
            restaurantDetails.add(resultSet.getString("branchName"));

            return restaurantDetails;
        }catch(SQLException | IOException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static Boolean checkIfTheRestHasFoodItems(){
        try {
            String sql = "select * from menus where restaurantId=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, String.valueOf(restaurantId));
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return false;
    }

    public boolean updateRestChangedData(List<Object> editPanelMiddleData){
        try{
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "update restaurants set image=?, mobile=?, allowType=? where id=?";
            PreparedStatement statement = con.prepareStatement(sql);

            byte[] imageBytes = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(((BufferedImage) editPanelMiddleData.get(0)), "jpg", baos);
            imageBytes = baos.toByteArray();

            statement.setBytes(1, imageBytes);
            statement.setString(2, (String) editPanelMiddleData.get(1));
            if(((int) editPanelMiddleData.get(2)) == 1){
                statement.setString(3, "All");
            }else if(((int) editPanelMiddleData.get(2)) == 2){
                statement.setString(3, "Pick Up");
            }
            else{
                statement.setString(3, "Delivery");
            }
            statement.setInt(4, restaurantId);
            statement.executeUpdate();

            return true;
        }catch (PacketTooBigException ex){
//            ex.printStackTrace();
        }
        catch(SQLException | IOException ex){
//            ex.printStackTrace();
        }

        return false;
    }

    public static void updateIsLive(String isLive){
        try{
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "update restaurants set isLive=? where id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, isLive);
            statement.setInt(2, restaurantId);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public boolean checkIfCategoryExist(String newCategory){
        try{
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "select category from menucategories where restID=? and category=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, restaurantId);
            statement.setString(2, newCategory);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return false;
            }else{
                return true;
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean addCategory(String newCategory){
        try{
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "insert into menucategories (restID, category) values (?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, restaurantId);
            statement.setString(2, newCategory);
            statement.executeUpdate();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public static List<String> getCategories(){
        List<String> categories = new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "select * from menucategories where restID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, restaurantId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                categories.add(resultSet.getString("category"));
            }
            return categories;
        }catch(SQLException ex){
            ex.printStackTrace();
            return categories;
        }
    }

    public static int getCategoryItemsCount(String category){
        int itemCount = 0;
        try{
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "SELECT COUNT(*) FROM menus WHERE restaurantID=? and category=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, restaurantId);
            statement.setString(2, category);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                itemCount = resultSet.getInt(1);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return itemCount;
    }

    public static boolean deleteCategory(String category){
        try{
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "delete from menucategories where category=? and restID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, category);
            statement.setInt(2, restaurantId);
            statement.executeUpdate();

            sql = "update menus set category=null where category=? and restaurantID=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, category);
            statement.setInt(2, restaurantId);
            statement.executeUpdate();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean updateCategoryName(List<List<String>> newCategoryNames){
        try{
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            for(List<String> newName : newCategoryNames){
                String sql = "update menucategories set category=? where category=? and restID=?";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, newName.get(0));
                statement.setString(2, newName.get(1));
                statement.setInt(3, restaurantId);
                statement.executeUpdate();

                sql = "update menus set category=? where category=? and restaurantID=?";
                statement = con.prepareStatement(sql);
                statement.setString(1, newName.get(0));
                statement.setString(2, newName.get(1));
                statement.setInt(3, restaurantId);
                statement.executeUpdate();
            }
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }







    ////////orders////////
    private static Boolean isNewOrdersPanelOpen = true;
    private static Boolean isAcceptedOrdersPanelOpen = false;
    private static Boolean isPreparingPanelOpen = false;
    private Boolean isDeliveryOrdersOptionSelected = true;
    private Boolean isPickUpOrdersOptionSelected = false;
    private static String ordersPanelOpenOrderCode;

    public static Boolean getIsPreparingPanelOpen() {
        return isPreparingPanelOpen;
    }

    public void setIsPreparingPanelOpen(Boolean isOpen) {
        isPreparingPanelOpen = isOpen;
    }

    public static Boolean getIsAcceptedOrdersPanelOpen() {
        return isAcceptedOrdersPanelOpen;
    }

    public void setIsAcceptedOrdersPanelOpen(Boolean isOpen) {
        isAcceptedOrdersPanelOpen = isOpen;
    }

    public void setIsNewOrdersPanelOpen(Boolean isOpen){
        isNewOrdersPanelOpen = isOpen;
    }

    public static Boolean getIsNewOrdersPanelOpen(){
        return isNewOrdersPanelOpen;
    }

    public void setIsDeliveryOrdersOptionSelected(Boolean isSelected){
        isDeliveryOrdersOptionSelected = isSelected;
    }

    public Boolean getIsDeliveryOrdersOptionSelected(){
        return isDeliveryOrdersOptionSelected;
    }

    public void setIsPickUpOrdersOptionSelected(Boolean isSelected) {
        isPickUpOrdersOptionSelected = isSelected;
    }

    public Boolean getIsPickUpOrdersOptionSelected() {
        return isPickUpOrdersOptionSelected;
    }

    static Connection con;

    {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<List<Object>> getNewOrders(String status, String type){
        List<List<Object>> newOrders = new ArrayList<>();
        try{
            String sql = "select * from orders where restID=? and status=? and orderType=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, restaurantId);
            statement.setString(2, status);
            statement.setString(3, type);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                List<Object> newOrder = new ArrayList<>();
                newOrder.add(resultSet.getInt("orderID"));
                newOrder.add(resultSet.getDate("date"));
                newOrder.add(resultSet.getTime("time"));
                newOrder.add(resultSet.getInt("orderTotal"));
                newOrder.add(resultSet.getString("orderCode"));
                newOrder.add(resultSet.getString("address"));

                newOrders.add(newOrder);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return newOrders;
    }

    public static List<List<Object>> getOrderItems(int orderId){
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

    public void changeOrderStatus(String action, int orderId){
        try {
            String sql = "update orders set status=? where orderID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, action);
            statement.setInt(2, orderId);
            statement.executeUpdate();

            if(Objects.equals(action, "accept")){
                sql = "select orderTotal from orders where orderID=?";
                statement = con.prepareStatement(sql);
                statement.setInt(1, orderId);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                int orderTotal = resultSet.getInt("orderTotal");
                int com = (orderTotal * 10) / 100;
                orderTotal = orderTotal - com;

                sql = "update orders set orderTotal=?, commission=? where orderID=?";
                statement = con.prepareStatement(sql);
                statement.setInt(1, orderTotal);
                statement.setInt(2, com);
                statement.setInt(3, orderId);
                statement.executeUpdate();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void setOrdersPanelOpenOrderCode(String orderCode){
        ordersPanelOpenOrderCode = orderCode;
    }

    public String getOrdersPanelOpenOrderCode(){
        return ordersPanelOpenOrderCode;
    }


    public String getUserEmailOfTheOpenOrder(int openOrderId){
        try {
            String sql = "select userID from orders where orderID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, openOrderId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int userId = resultSet.getInt("userID");

            sql = "select email from customer where customerID=?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            resultSet.next();

            return resultSet.getString("email");

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return "";
    }

    public int getOpenOrderTotalPrice(int orderId){
        try {
            String sql = "select orderTotal from orders where orderID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("orderTotal");

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }


    /////////dashboard////////////
    private Boolean isTodayBtnSelected = true;
    private Boolean isThisWeekBtnSelected =  false;
    private Boolean isThisMonthBtnSelected = false;
    private Boolean isThisYearBtnSelected = false;
    private static Boolean isDashBoardSearchOrderViewPanelOpen = false;
    private static int dashBoardSearchSelectedOrderId;

    public static void setDashBoardSearchSelectedOrderId(int orderId) {
        dashBoardSearchSelectedOrderId = orderId;
    }

    public Boolean getIsDashBoardSearchOrderViewPanelOpen() {
        return isDashBoardSearchOrderViewPanelOpen;
    }

    public static void setIsDashBoardSearchOrderViewPanelOpen(Boolean isOpen) {
        isDashBoardSearchOrderViewPanelOpen = isOpen;
    }

    public Boolean getIsTodayBtnSelected() {
        return isTodayBtnSelected;
    }

    public void setIsTodayBtnSelected(Boolean isSelected) {
        isTodayBtnSelected = isSelected;
    }

    public Boolean getIsThisWeekBtnSelected() {
        return isThisWeekBtnSelected;
    }

    public void setIsThisWeekBtnSelected(Boolean isSelected) {
        isThisWeekBtnSelected = isSelected;
    }

    public Boolean getIsThisMonthBtnSelected() {
        return isThisMonthBtnSelected;
    }

    public void setIsThisMonthBtnSelected(Boolean isSelected) {
        isThisMonthBtnSelected = isSelected;
    }

    public Boolean getIsThisYearBtnSelected() {
        return isThisYearBtnSelected;
    }

    public void setIsThisYearBtnSelected(Boolean isSelected) {
        isThisYearBtnSelected = isSelected;
    }

    public int[] getDashBoardOrderStatics(String timePeriod){
//        int[] ordersStatics = new int[6];
        int totalEarning = 0;
        int recievedOrders = 0;
        int deliveredOrders = 0;
        int pickedUpOrders = 0;
        int readyToPickupOrders = 0;
        int cancelledOrders = 0;

        try {
            String sql = "select * from orders";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            LocalDate today = LocalDate.now();
            WeekFields weekFields = WeekFields.of(Locale.getDefault());

            while(resultSet.next()){
                LocalDate orderDate = resultSet.getDate("date").toLocalDate();
                int dateWeekNumber = orderDate.get(weekFields.weekOfWeekBasedYear());
                int currentWeekNumber = today.get(weekFields.weekOfWeekBasedYear());

                if(Objects.equals(timePeriod, "today")) {
                    if(orderDate.isEqual(today)){
                        recievedOrders ++;
                        if(resultSet.getString("status").equals("completed") && resultSet.getString("orderType").equals("Delivery")){
                            totalEarning += resultSet.getInt("orderTotal");
                            deliveredOrders++;
                        }else if(resultSet.getString("status").equals("completed") && resultSet.getString("orderType").equals("Pickup")){
                            totalEarning += resultSet.getInt("orderTotal");
                            pickedUpOrders++;
                        }else if(resultSet.getString("status").equals("prepared") && resultSet.getString("orderType").equals("Pickup")){
                            readyToPickupOrders++;
                        }else if(resultSet.getString("status").equals("decline")){
                            cancelledOrders++;
                        }
                    }
                }else if(Objects.equals(timePeriod, "thisMonth")){
                    if(orderDate.getYear() == today.getYear() && orderDate.getMonth() == today.getMonth()){
                        recievedOrders ++;
                        if(resultSet.getString("status").equals("completed") && resultSet.getString("orderType").equals("Delivery")){
                            totalEarning += resultSet.getInt("orderTotal");
                            deliveredOrders++;
                        }else if(resultSet.getString("status").equals("completed") && resultSet.getString("orderType").equals("Pickup")){
                            totalEarning += resultSet.getInt("orderTotal");
                            pickedUpOrders++;
                        }else if(resultSet.getString("status").equals("prepared") && resultSet.getString("orderType").equals("Pickup")){
                            readyToPickupOrders++;
                        }else if(resultSet.getString("status").equals("decline")){
                            cancelledOrders++;
                        }
                    }
                }else if(Objects.equals(timePeriod, "thisYear")){
                    if(orderDate.getYear() == today.getYear()){
                        recievedOrders ++;
                        if(resultSet.getString("status").equals("completed") && resultSet.getString("orderType").equals("Delivery")){
                            totalEarning += resultSet.getInt("orderTotal");
                            deliveredOrders++;
                        }else if(resultSet.getString("status").equals("completed") && resultSet.getString("orderType").equals("Pickup")){
                            totalEarning += resultSet.getInt("orderTotal");
                            pickedUpOrders++;
                        }else if(resultSet.getString("status").equals("prepared") && resultSet.getString("orderType").equals("Pickup")){
                            readyToPickupOrders++;
                        }else if(resultSet.getString("status").equals("decline")){
                            cancelledOrders++;
                        }
                    }
                }else{
                    if(dateWeekNumber == currentWeekNumber && orderDate.getYear() == today.getYear()){
                        recievedOrders ++;
                        if(resultSet.getString("status").equals("completed") && resultSet.getString("orderType").equals("Delivery")){
                            totalEarning += resultSet.getInt("orderTotal");
                            deliveredOrders++;
                        }else if(resultSet.getString("status").equals("completed") && resultSet.getString("orderType").equals("Pickup")){
                            totalEarning += resultSet.getInt("orderTotal");
                            pickedUpOrders++;
                        }else if(resultSet.getString("status").equals("prepared") && resultSet.getString("orderType").equals("Pickup")){
                            readyToPickupOrders++;
                        }else if(resultSet.getString("status").equals("decline")){
                            cancelledOrders++;
                        }
                    }
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return new int[]{totalEarning, recievedOrders, deliveredOrders, pickedUpOrders, readyToPickupOrders, cancelledOrders};
    }

    public List<List<Object>> getOrdersOfTheSelectedDate(LocalDate selectedDate, String filter, String searchCode){
        List<List<Object>> orders = new ArrayList<>();
        try {
            String sql;
            PreparedStatement statement;
            if(Objects.equals(filter, "all")){
                sql = "select * from orders where date=?";
                statement = con.prepareStatement(sql);
                statement.setDate(1, Date.valueOf(selectedDate));
            }else if(Objects.equals(filter, "toPickUp")){
                sql = "select * from orders where date=? and status=? and orderType=?";
                statement = con.prepareStatement(sql);
                statement.setDate(1, Date.valueOf(selectedDate));
                statement.setString(2, "prepared");
                statement.setString(3, "pickup");
            }else{
                sql = "select * from orders where date=? and status=? and orderType=? and orderCode LIKE ?";
                statement = con.prepareStatement(sql);
                statement.setDate(1, Date.valueOf(selectedDate));
                statement.setString(2, "prepared");
                statement.setString(3, "pickup");
                statement.setString(4, searchCode);
            }

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                List<Object> order = new ArrayList<>();
                order.add(resultSet.getInt("orderID"));
                order.add(resultSet.getString("orderCode"));
                order.add(resultSet.getString("status"));
                order.add(resultSet.getString("orderType"));
                order.add(resultSet.getInt("orderTotal"));
                order.add(resultSet.getDate("date"));
                order.add(resultSet.getString("address"));

                int userid = resultSet.getInt("userID");
                sql = "select username, email from customer where customerID=?";
                statement = con.prepareStatement(sql);
                statement.setInt(1, userid);
                ResultSet resultSet1 = statement.executeQuery();
                resultSet1.next();

                order.add(resultSet1.getString("username"));

                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                order.add(formatter.format(resultSet.getTime("time")));

                order.add(resultSet1.getString("email"));



                orders.add(order);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return orders;
    }

    public String getDashBoardSearchSelectedOrderStatus(){
        try {
            String sql = "select status from orders where orderID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, dashBoardSearchSelectedOrderId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getString("status");
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return  "";
    }

    public List<List<Object>> getTheOrderOfTheSearchedId(String orderCode){
        List<List<Object>> orders = new ArrayList<>();
        List<Object> order = new ArrayList<>();
        try {
            String sql = "select * from orders where orderCode=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, orderCode);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                order.add(resultSet.getInt("orderID"));
                order.add(resultSet.getString("orderCode"));
                order.add(resultSet.getString("status"));
                order.add(resultSet.getString("orderType"));
                order.add(resultSet.getInt("orderTotal"));
                order.add(resultSet.getDate("date"));
                order.add(resultSet.getString("address"));

                int userid = resultSet.getInt("userID");
                sql = "select username from customer where customerID=?";
                statement = con.prepareStatement(sql);
                statement.setInt(1, userid);
                ResultSet resultSet1 = statement.executeQuery();
                resultSet1.next();

                order.add(resultSet1.getString("username"));
                orders.add(order);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return orders;
    }

    private static int checkOutOrderTotal;

    public int getCheckOutOrderTotal() {
        return checkOutOrderTotal;
    }

    public static void setCheckOutOrderTotal(int total) {
        checkOutOrderTotal = total;
    }

    private static int checkOutOrderId;

    public int getCheckOutOrderId() {
        return checkOutOrderId;
    }

    public static void setCheckOutOrderId(int orderId) {
        checkOutOrderId = orderId;
    }

    private static String checkOutOrderCode = "";

    public String getCheckOutOrderCode() {
        return checkOutOrderCode;
    }

    public static void setCheckOutOrderCode(String orderCode) {
        checkOutOrderCode = orderCode;
    }

    private static String checkOutOrderCustEmail = "";

    public static void setCheckOutOrderCustEmail(String email) {
        checkOutOrderCustEmail = email;
    }

    public String getCheckOutOrderCustEmail() {
        return checkOutOrderCustEmail;
    }

    public Boolean chekRestOpenOrClose(){
        try {
            String sql = "select isOpen from restaurants where id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, restaurantId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String isOpen = resultSet.getString("isOpen");
            if(Objects.equals(isOpen, "yes")){
                return true;
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return false;
    }

    public void setRestOpenOrClose(String openOrClose){
        try {
            String sql = "update restaurants set isOpen=? where id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, openOrClose);
            statement.setInt(2, restaurantId);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

}
