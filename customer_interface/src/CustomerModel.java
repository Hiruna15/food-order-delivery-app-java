import com.mysql.cj.protocol.Resultset;
import jdk.jfr.BooleanFlag;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialStruct;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.management.BufferPoolMXBean;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;

import static java.util.Date.*;

public class CustomerModel {

    private static Boolean isAddToOrderPanelOpen = false;

    public static Boolean getIsAddToOrderPanelOpen() {
        return isAddToOrderPanelOpen;
    }

    public static void setIsAddToOrderPanelOpen(Boolean isOpen) {
        isAddToOrderPanelOpen = isOpen;
    }

    private static int openFoodItemId;

    public static int getOpenFoodItemId() {
        return openFoodItemId;
    }

    public static void setOpenFoodItemId(int itemId) {
        openFoodItemId = itemId;
    }

    private static String openRestaurantAllowType;

    public static String getOpenRestaurantAllowType() {
        return openRestaurantAllowType;
    }

    public static void setOpenRestaurantAllowType(String allowType) {
        openRestaurantAllowType = allowType;
    }





    private boolean isHomeSideBarPanelOpen = false;
    private boolean isRestSignupFormOpen = false;
    private String storeName = "";
    private String storeAddress = "";
    private String storeEmail = "";
    private String storeMobile = "";
    private String storeCity = "";
    private String storePostalCode = "";
    private String storeBranchName = "";
    private boolean isDataInsertedSuccessful = false;

    private static boolean isHomePanelOpen = true;
    private static boolean isRestaurantPanelOpen = false;
    private static int openRestaurantId;
    private int emailConfirmCode;


    private String loggedCustomerUserName = null;

    private static int loggedCustomerID;

    public String getLoggedCustomerUserName() {
        return loggedCustomerUserName;
    }

    public void setLoggedCustomerUserName(String username) {
        loggedCustomerUserName = username;
    }

    public static int getLoggedCustomerID() {
        return loggedCustomerID;
    }

    public void setLoggedCustomerID(int id) {
        loggedCustomerID = id;
    }



    public int getEmailConfirmCode() {
        return emailConfirmCode;
    }

    public static int getOpenRestaurantId() {
        return openRestaurantId;
    }

    public static void setOpenRestaurantId(int restId) {
        openRestaurantId = restId;
    }

    public boolean getIsRestaurantPanelOpen() {
        return isRestaurantPanelOpen;
    }

    public static void setIsRestaurantPanelOpen(boolean isOpen) {
        isRestaurantPanelOpen = isOpen;
    }

    public static void setIsHomePanelOpen(boolean isOpen){
        isHomePanelOpen = isOpen;
    }

    public boolean getIsHomePanelOpen(){
        return  isHomePanelOpen;
    }

    private String filterRestaurantType = "All";

    private Boolean isRestaurantMyFeaturesPanelOpen = false;

    public Boolean getRestaurantMyFeaturesPanelOpen() {
        return isRestaurantMyFeaturesPanelOpen;
    }

    public void setRestaurantMyFeaturesPanelOpen(Boolean restaurantMyFeaturesPanelOpen) {
        isRestaurantMyFeaturesPanelOpen = restaurantMyFeaturesPanelOpen;
    }

    public void setFilterRestaurantType(String filterRestaurantType) {
        this.filterRestaurantType = filterRestaurantType;
    }

    public String getFilterRestaurantType(){
        return filterRestaurantType;
    }

    private static final String URL = "jdbc:mysql://localhost:3306/food_delivery_app";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public boolean isDataInsertedSuccessful(){
        return isDataInsertedSuccessful;
    }

    public void setIsDataInsertedSuccessful(boolean status){
        this.isDataInsertedSuccessful = status;
    }
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreEmail() {
        return storeEmail;
    }

    public void setStoreEmail(String storeEmail) {
        this.storeEmail = storeEmail;
    }

    public String getStoreMobile() {
        return storeMobile;
    }

    public void setStoreMobile(String storeMobile) {
        this.storeMobile = storeMobile;
    }

    public String getStoreCity() {
        return storeCity;
    }

    public void setStoreCity(String storeCity) {
        this.storeCity = storeCity;
    }

    public String getStorePostalCode() {
        return storePostalCode;
    }

    public void setStorePostalCode(String storePostalCode) {
        this.storePostalCode = storePostalCode;
    }

    public void setStoreBranchName(String branchName){
        this.storeBranchName = branchName;
    }

    public boolean isRestSignupFormOpen() {
        return isRestSignupFormOpen;
    }

    public void setRestSignupFormOpen(boolean restSignupFormOpen) {
        isRestSignupFormOpen = restSignupFormOpen;
    }

    public boolean isHomeSideBarPanelOpen() {
        return isHomeSideBarPanelOpen;
    }

    public void setIsHomeSideBarPanelOpen(boolean isOpen) {
        isHomeSideBarPanelOpen = isOpen;
    }

    public void addRestaurantToReviewList(){
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "insert into pendingrestaurants (name, address, email, mobile, city, postalCode, branchName) values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, storeName);
            statement.setString(2, storeAddress);
            statement.setString(3, storeEmail);
            statement.setString(4, storeMobile);
            statement.setString(5, storeCity);
            statement.setString(6, storePostalCode);
            statement.setString(7, storeBranchName);
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted > 0){
                isDataInsertedSuccessful = true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getRegisteredRestEmails(String email){
        int selectedRows1 = 0;
        int selectedRows2 = 0;
        int selectedRows3 = 0;
        try{
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "select email from restaurants where email=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return false;
            }

            sql = "select email from pendingrestaurants where email=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return false;
            }

            sql = "select email from customer where email=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return false;
            }

            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void generateEmailConfirmCode(){
        Random random = new Random();
        emailConfirmCode = random.nextInt(9000) + 1000;
    }

    Connection con;

    {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<List<Object>> getEnableRestaurants(String fromWhere, String searchText){
        List<List<Object>> restaurants = new ArrayList<>();
        try{
            String sql;
            PreparedStatement statement;
            if(Objects.equals(fromWhere, "home")){
                sql = "select * from restaurants where isLive=?";
                statement = con.prepareStatement(sql);
                statement.setString(1, "yes");
            }else{
                sql = "select * from restaurants where isLive=? and (branchName LIKE ? or name LIKE ?)";
                statement = con.prepareStatement(sql);
                statement.setString(1, "yes");
                statement.setString(2, searchText);
                statement.setString(3, searchText);
            }
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                List<Object> restaurant = new ArrayList<>();
                restaurant.add(resultSet.getInt("id"));
                restaurant.add(resultSet.getString("branchName"));
                restaurant.add(resultSet.getString("isOpen"));

                Blob blob = resultSet.getBlob("image");
                BufferedImage image;
                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                image = ImageIO.read(bis);
                restaurant.add(image);
                restaurant.add(resultSet.getString("allowType"));


                restaurants.add(restaurant);

                bis.close();
            }

            return restaurants;
        }catch(SQLException | IOException ex){
            ex.printStackTrace();
        }

        return restaurants;
    }


    public List<String> getRestCategories(){
        List<String> restCategories = new ArrayList<>();
        try{
            String sql = "select * from menucategories where restID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, openRestaurantId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                String category = resultSet.getString("category");
                sql = "select * from menus where category=? and restaurantID=?";
                statement = con.prepareStatement(sql);
                statement.setString(1, category);
                statement.setInt(2, openRestaurantId);
                ResultSet resultSet2 = statement.executeQuery();
                if(resultSet2.next()){
                    restCategories.add(category);
                }
            }

            return restCategories;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return restCategories;
    }


    public List<List<Object>> getRestFoodItems(String category){
        List<List<Object>> categoryFoodItems = new ArrayList<>();

        try{
            String sql = "select * from menus where category=? and restaurantID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, category);
            statement.setInt(2, openRestaurantId);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                List<Object> categoryFoodItem = new ArrayList<>();
                categoryFoodItem.add(resultSet.getString("itemName"));
                categoryFoodItem.add(resultSet.getInt("price"));
                categoryFoodItem.add(resultSet.getString("description"));

                Blob blob = resultSet.getBlob("image");
                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                BufferedImage itemImage = ImageIO.read(bis);
                categoryFoodItem.add(itemImage);

                categoryFoodItem.add(resultSet.getInt("itemID"));

                categoryFoodItems.add(categoryFoodItem);
            }

            return categoryFoodItems;

        }catch(SQLException | IOException e){
            e.printStackTrace();
        }
        return categoryFoodItems;
    }

    public boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*@gmail\\.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void registerCustomer(String[] details){
        try{
            String sql = "insert into customer (username, email, password) values (?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, details[0]);
            statement.setString(2, details[1]);
            statement.setString(3, details[2]);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public Boolean loginTheCustomer(String[] details){
        try{
            String sql = "select * from customer where email=? and password=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, details[0]);
            statement.setString(2, details[1]);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                loggedCustomerID = resultSet.getInt("customerID");
                loggedCustomerUserName = resultSet.getString("username");
                System.out.println(loggedCustomerID);////////////
                return true;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return false;
    }

    static Boolean isRestaurantOpen;

    public static Boolean getIsRestaurantOpen(){
         return isRestaurantOpen;
    }

    public void setIsRestaurantOpen(){
        try{
            String sql = "select isOpen from restaurants where id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, openRestaurantId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String isOpen = resultSet.getString("isOpen");
                if(Objects.equals(isOpen, "no")){
                    isRestaurantOpen = false;
                }else{
                    isRestaurantOpen = true;
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }





    ///////add to order panel ////////////
    private static int openFavMealRestId;

    public static void setOpenFavMealRestId(int restId){
        openFavMealRestId = restId;
    }

    private static int openFavMealId;

    public static void setOpenFavMealId(int itemId){
        openFavMealId = itemId;
    }

    public List<Object> getOpenFoodItemDetails(String from){
        List<Object> foodItemDetails = new ArrayList<>();
        try{
            String sql = "select * from menus where itemID=? and restaurantID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            if(Objects.equals(from, "rest")){
                statement.setInt(1, openFoodItemId);
                statement.setInt(2, openRestaurantId);
            }else{
                statement.setInt(1, openFoodItemId);
                statement.setInt(2, openFavMealRestId);
            }
            ResultSet resultSet2 = statement.executeQuery();
            if(resultSet2.next()){
                foodItemDetails.add(resultSet2.getString("itemName"));
                foodItemDetails.add(resultSet2.getInt("price"));
                foodItemDetails.add(resultSet2.getString("description"));

                Blob blob = resultSet2.getBlob("image");
                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                BufferedImage itemImage = ImageIO.read(bis);
                foodItemDetails.add(itemImage);

                return foodItemDetails;
            }
        }catch(SQLException | IOException ex){
            ex.printStackTrace();
        }
        return foodItemDetails;
    }

    public void addItemToCart(List<Object> orderItemDetails){
        try{
            String sql = "select * from carts where userID=? and restID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, loggedCustomerID);
            statement.setInt(2, openRestaurantId);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                sql = "insert into carts (userID, restID) values(?, ?)";
                statement = con.prepareStatement(sql);
                statement.setInt(1, loggedCustomerID);
                statement.setInt(2, openRestaurantId);
                statement.executeUpdate();
            }

            sql = "select cartID from carts where userID=? and restID=?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, loggedCustomerID);
            statement.setInt(2, openRestaurantId);
            resultSet =  statement.executeQuery();
            resultSet.next();
            int cartId = resultSet.getInt("cartID");

            sql = "select * from cart_items where cartID=? and itemID=? and orderType=?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, cartId);
            statement.setInt(2, openFoodItemId);
            statement.setString(3, (String) orderItemDetails.get(1));
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                if(Objects.equals((String) orderItemDetails.get(1), "delivery")){
                    sql = "update cart_items set d_itemQnt=?, d_itemSubTotal=? where cartID=? and itemID=? and orderType=?";
                }else{
                    sql = "update cart_items set p_itemQnt=?, p_itemSubTotal=? where cartID=? and itemID=? and orderType=?";
                }

                statement = con.prepareStatement(sql);
                statement.setInt(1, (int)orderItemDetails.get(0));
                statement.setInt(2, (int)orderItemDetails.get(2));
                statement.setInt(3, cartId);
                statement.setInt(4, openFoodItemId);
                statement.setString(5, (String) orderItemDetails.get(1));
                statement.executeUpdate();
            }else{
                if(Objects.equals((String) orderItemDetails.get(1), "delivery")){
                    sql = "insert into cart_items(cartID, itemID, orderType, d_itemQnt, d_itemSubTotal) values(?, ?, ?, ?, ?)";
                }else{
                    sql = "insert into cart_items(cartID, itemID, orderType, p_itemQnt, p_itemSubTotal) values(?, ?, ?, ?, ?)";
                }
                statement = con.prepareStatement(sql);
                statement.setInt(1, cartId);
                statement.setInt(2, openFoodItemId);
                statement.setString(3, (String) orderItemDetails.get(1));
                statement.setInt(4, (int)orderItemDetails.get(0));
                statement.setInt(5, (int)orderItemDetails.get(2));
                statement.executeUpdate();
            }


        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }





    //////cart ////////////
    private static Boolean isCartContainerPanelOpen = false;
    private static int openCartId;
    private static String openCartRestAllowType;
    private static String cartOpenOption;
    private static String openCartBranchName;

    public static String getOpenCartBranchName() {
        return openCartBranchName;
    }

    public static String getCartOpenOption() {
        return cartOpenOption;
    }

    public static void setCartOpenOption(String option) {
        cartOpenOption = option;
    }

    public static String getOpenCartRestAllowType() {
        return openCartRestAllowType;
    }

    public static int getOpenCartId() {
        return openCartId;
    }

    public static void setOpenCartId(int cartId) {
        openCartId = cartId;
    }

    public Boolean getIsCartContainerPanelOpen() {
        return isCartContainerPanelOpen;
    }

    public static void setIsCartContainerPanelOpen(Boolean isOpen) {
        isCartContainerPanelOpen = isOpen;
    }

    public int checkIfTheUserHasACartFromThatRest(){
        try {
            String sql = "select * from carts where userID=? and restID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, loggedCustomerID);
            statement.setInt(2, openRestaurantId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                int cartId = resultSet.getInt("cartID");
                return cartId;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }

    public static List<List<Object>> getCartItems(int cartId, String orderT){
        List<List<Object>> cartItemsDetails = new ArrayList<>();
        String orderType;

//        if(orderT.startsWith("D")){
//            orderType = "delivery";
//        }else if(orderT.startsWith("P")){
//            orderType = "pickup";
//        }else{
//            orderType = "delivery";
//        }

        if(orderT.startsWith("D")){
            orderType = "delivery";
        }else {
            orderType = "pickup";
        }

        try {
            String sql = "select * from cart_items where cartID=? and orderType=?";
            PreparedStatement statement = con2.prepareStatement(sql);
            statement.setInt(1, cartId);
            statement.setString(2, orderType);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                List<Object> cartItemDetails = new ArrayList<>();

                if(orderType.equals("delivery")){
                    cartItemDetails.add(resultSet.getInt("d_itemQnt"));
                }else{
                    cartItemDetails.add(resultSet.getInt("p_itemQnt"));
                }

                int itemId = resultSet.getInt("itemID");
                sql = "select * from menus where itemID=?";
                statement = con2.prepareStatement(sql);
                statement.setInt(1, itemId);
                ResultSet resultSet2 = statement.executeQuery();

                resultSet2.next();
                cartItemDetails.add(resultSet2.getString("itemName"));
                cartItemDetails.add(resultSet2.getInt("price"));

                Blob blob = resultSet2.getBlob("image");
                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                BufferedImage itemImage = ImageIO.read(bis);

                cartItemDetails.add(itemImage);
                cartItemDetails.add(itemId);

                cartItemsDetails.add(cartItemDetails);
            }

            return cartItemsDetails;

        }catch(SQLException | IOException ex){
            ex.printStackTrace();
        }
        return cartItemsDetails;
    }

    public static Boolean checkIfDeliveryOrdersInTheCart(int cartId){
        boolean hasDeliveryItems = false;
        boolean hasPickUpItems = false;
        try {
            String sql = "select * from cart_items where cartID=? and orderType=?";
            PreparedStatement statement = con2.prepareStatement(sql);
            statement.setInt(1, cartId);
            statement.setString(2, "delivery");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                hasDeliveryItems = true;
            }

            statement = con2.prepareStatement(sql);
            statement.setInt(1, cartId);
            statement.setString(2, "pickup");
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                hasPickUpItems = true;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return hasDeliveryItems;
    }

    public static void setOpenCartRestAllowTypeAndNameAndId(int cartId){
        try {
            String sql = "select restID from carts where cartID=?";
            PreparedStatement statement = con2.prepareStatement(sql);
            statement.setInt(1, cartId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int restId = resultSet.getInt("restID");

            sql = "select allowType, branchName from restaurants where id=?";
            statement = con2.prepareStatement(sql);
            statement.setInt(1, restId);
            resultSet = statement.executeQuery();
            resultSet.next();
            openCartRestAllowType = resultSet.getString("allowType");
            openCartBranchName = resultSet.getString("branchName");
            openCartRestId = restId;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    static Connection con2;

    {
        try {
            con2 = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int cartOpenSectionSubTotal;

    public static int getCartOpenSectionSubTotal(){
        return cartOpenSectionSubTotal;
    }
    public static void updateCartItemQntAndTotal(int qnt, int itemId, int itemSubTotal){
        try {
            String sql;
            if(cartOpenOption.startsWith("D")){
                sql = "update cart_items set d_itemQnt=?, d_itemSubTotal=? where cartID=? and itemID=? and orderType=?";
            }else{
                sql = "update cart_items set p_itemQnt=?, p_itemSubTotal=? where cartID=? and itemID=? and orderType=?";
            }

            PreparedStatement statement = con2.prepareStatement(sql);
            statement.setInt(1, qnt);
            statement.setInt(2, itemSubTotal);
            statement.setInt(3, openCartId);
            statement.setInt(4, itemId);
            statement.setString(5, cartOpenOption);

            statement.executeUpdate();

            if(cartOpenOption.startsWith("D")){
                sql = "select d_itemSubTotal from cart_items where cartID=? and orderType=?";
                statement = con2.prepareStatement(sql);
                statement.setInt(1, openCartId);
                statement.setString(2, "delivery");
            }else{
                sql = "select p_itemSubTotal from cart_items where cartID=? and orderType=?";
                statement = con2.prepareStatement(sql);
                statement.setInt(1, openCartId);
                statement.setString(2, "pickup");
            }

            ResultSet resultSet = statement.executeQuery();

            cartOpenSectionSubTotal = 0;
            while(resultSet.next()){
                if(cartOpenOption.startsWith("D")){
                    cartOpenSectionSubTotal += resultSet.getInt("d_itemSubTotal");
                }else{
                    cartOpenSectionSubTotal += resultSet.getInt("p_itemSubTotal");
                }
            }


        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public static String deleteCartItem(int itemId){
        try {
            String sql = "delete from cart_items where cartID=? and itemID=? and orderType=?";
            PreparedStatement statement = con2.prepareStatement(sql);
            statement.setInt(1, openCartId);
            statement.setInt(2, itemId);
            statement.setString(3, cartOpenOption);
            statement.executeUpdate();

            sql = "select * from cart_items where cartID=?";
            statement = con2.prepareStatement(sql);
            statement.setInt(1, openCartId);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                sql = "delete from carts where cartID=?";
                statement = con2.prepareStatement(sql);
                statement.setInt(1, openCartId);
                statement.executeUpdate();
                return "close";
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return "";
    }

    public List<List<Object>> getPlaceOrderDetails(){
        List<List<Object>> placeOrderItemsDetails = new ArrayList<>();
        try {
            String sql;
            PreparedStatement statement;

            if(Objects.equals(cartOpenOption, "Delivery")){
                sql = "select itemId, d_itemSubTotal, d_itemQnt from cart_items where cartID=? and orderType=?";
                statement = con2.prepareStatement(sql);
                statement.setInt(1, openCartId);
                statement.setString(2, "delivery");
            }else{
                sql = "select itemId, p_itemSubTotal, p_itemQnt from cart_items where cartID=? and orderType=?";
                statement = con2.prepareStatement(sql);
                statement.setInt(1, openCartId);
                statement.setString(2, "pickup");
            }

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                List<Object> placeOrderItemDetails = new ArrayList<>();

                int itemId = resultSet.getInt("itemId");
                int itemTotal;
                int itemQnt;

                if(Objects.equals(cartOpenOption, "Delivery")){
                    itemTotal = resultSet.getInt("d_itemSubTotal");
                    itemQnt = resultSet.getInt("d_itemQnt");
                }else{
                    itemTotal = resultSet.getInt("p_itemSubTotal");
                    itemQnt = resultSet.getInt("p_itemQnt");
                }

                sql = "select itemName from menus where itemID=?";
                statement = con2.prepareStatement(sql);
                statement.setInt(1, itemId);
                ResultSet resultSet1 = statement.executeQuery();
                resultSet1.next();
                String itemName = resultSet1.getString("itemName");

                placeOrderItemDetails.add(itemId);
                placeOrderItemDetails.add(itemName);
                placeOrderItemDetails.add(itemQnt);
                placeOrderItemDetails.add(itemTotal);

                placeOrderItemsDetails.add(placeOrderItemDetails);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return placeOrderItemsDetails;
    }



    //////cart list/////

    private static Boolean isCartsListPanelOpen = false;

    public Boolean getCartsListPanelOpen() {
        return isCartsListPanelOpen;
    }

    public static void setIsCartsListPanelOpen(Boolean isOpen) {
        isCartsListPanelOpen = isOpen;
    }

    public List<List<Object>> getAllCarts(){
        List<List<Object>> cartListCartsDetails = new ArrayList<>();
        try {
            String sql = "select * from carts where userID=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, loggedCustomerID);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                List<Object> cartListCartDetails = new ArrayList<>();

                int cartId = resultSet.getInt("cartID");
                cartListCartDetails.add(cartId);

                int cartRestId = resultSet.getInt("restID");

                sql = "select d_itemQnt, p_itemQnt from cart_items where cartID=?";
                statement = con.prepareStatement(sql);
                statement.setInt(1, cartId);
                ResultSet resultSet2 = statement.executeQuery();
                int totalItemsInCart = 0;
                while(resultSet2.next()){
                    int d_qnt = resultSet2.getInt("d_itemQnt");
                    int p_qnt = resultSet2.getInt("p_itemQnt");

                    totalItemsInCart += (d_qnt + p_qnt);
                }

                cartListCartDetails.add(totalItemsInCart);

                sql = "select branchName, image from restaurants where id=?";
                statement = con.prepareStatement(sql);
                statement.setInt(1, cartRestId);
                resultSet2 = statement.executeQuery();
                resultSet2.next();

                cartListCartDetails.add(resultSet2.getString("branchName"));

                Blob blob = resultSet2.getBlob("image");
                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                BufferedImage itemImage = ImageIO.read(bis);

                cartListCartDetails.add(itemImage);

                cartListCartsDetails.add(cartListCartDetails);
            }

            return cartListCartsDetails;

        }catch(SQLException | IOException ex){
            ex.printStackTrace();
        }

        return cartListCartsDetails;
    }

    /////place the order////////
    private static int openCartRestId;

    public String placeTheOrder(List<List<Object>> cartItemsDetails, String address){
        try {
            int orderTotal = 0;
            for(List<Object> cartItem : cartItemsDetails){
                orderTotal += (int)cartItem.get(3);
            }

            LocalDate today = LocalDate.now();


            LocalTime currentTime = LocalTime.now();
            Time time = Time.valueOf(currentTime);

            String sql;
            PreparedStatement statement;
            ResultSet resultSet;
            String orderCode;
            while(true){
                Random random = new Random();
                orderCode = String.valueOf(random.nextInt(90000) + 10000);
                sql = "select orderCode from orders where orderCode=?";
                statement = con.prepareStatement(sql);
                statement.setString(1, orderCode);
                resultSet = statement.executeQuery();
                if(!resultSet.next()){
                    break;
                }
            }

            sql = "insert into orders (restID, userID, orderType, status, date, time, orderTotal, address, orderCode) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = con.prepareStatement(sql);
            statement.setInt(1, openCartRestId);
            statement.setInt(2, loggedCustomerID);
            statement.setString(3, cartOpenOption);
            statement.setString(4, "new");
//            statement.setDate(5, Date.valueOf(today));
            statement.setDate(5, java.sql.Date.valueOf(today));
//            statement.setTime(6, Time.valueOf(formattedTime));
            statement.setTime(6, time);
            statement.setInt(7, orderTotal);
            statement.setString(8, address);
            statement.setString(9, orderCode);
            statement.executeUpdate();

            sql = "select orderID from orders where restID=? and userID=?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, openCartRestId);
            statement.setInt(2, loggedCustomerID);
            resultSet = statement.executeQuery();
            int orderId = 0;
            while(resultSet.next()){
                orderId = resultSet.getInt("orderID");
            }

            for(List<Object> cartItem : cartItemsDetails){
                int itemId = (int)cartItem.get(0);
                String itemName = (String) cartItem.get(1);
                int qnt = (int) cartItem.get(2);
                int itemTotalPrice = (int) cartItem.get(3);

                sql = "insert into orderitems (orderID, itemID, itemName, itemQnt, itemTotalPrice) values (?, ?, ?, ?, ?)";
                statement = con.prepareStatement(sql);
                statement.setInt(1, orderId);
                statement.setInt(2, itemId);
                statement.setString(3, itemName);
                statement.setInt(4, qnt);
                statement.setInt(5, itemTotalPrice);
                statement.executeUpdate();

                sql = "delete from cart_items where itemID=? and cartID=? and orderType=?";
                statement = con.prepareStatement(sql);
                statement.setInt(1, itemId);
                statement.setInt(2, openCartId);
                statement.setString(3, cartOpenOption);
                statement.executeUpdate();
            }

            sql = "select * from cart_items where cartID=?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, openCartId);
            resultSet = statement.executeQuery();
            if(!resultSet.next()){
                sql = "delete from carts where cartID=?";
                statement = con.prepareStatement(sql);
                statement.setInt(1, openCartId);
                statement.executeUpdate();
                return "close";
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return "";
    }

    public Boolean checkDeliverySignUpUserNameExist(String username){
        try {
            String sql = "select username from deliverydriver where username=?";
            PreparedStatement statement = con2.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return true;
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public Boolean checkIfDeliverySignUpEmailExist(String email){
        try {
            String sql = "select email from deliverydriver where email=?";
            PreparedStatement statement = con2.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return true;
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public Boolean isValidNICNumber(String nic){
        String regex = "^(([5,6,7,8,9]{1})([0-9]{1})([0,1,2,3,5,6,7,8]{1})([0-9]{6})([v|V|x|X]))|(([1,2]{1})([0,9]{1})([0-9]{2})([0,1,2,3,5,6,7,8]{1})([0-9]{7}))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nic);
        return matcher.matches();
    }

    public void addANewDeliveryDriverToReview(String[] details){
        try {
            String sql = "insert into deliverydriver (username, email, nic, vehicleNumber, vehicleType, name, mobile, status) values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con2.prepareStatement(sql);
            statement.setString(1, details[0]);
            statement.setString(2, details[1]);
            statement.setString(3, details[2]);
            statement.setString(4, details[3]);
            statement.setString(5, details[4]);
            statement.setString(6, details[5]);
            statement.setString(7, details[6]);
            statement.setString(8, "rev");

            statement.executeUpdate();


        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    private Boolean isSideBarHomeMyOrdersListPanelOpen= false;

    public Boolean getSideBarHomeMyOrdersListPanelOpen() {
        return isSideBarHomeMyOrdersListPanelOpen;
    }

    public void setSideBarHomeMyOrdersListPanelOpen(Boolean isOpen) {
        isSideBarHomeMyOrdersListPanelOpen = isOpen;
    }

    public List<List<Object>> getCustomerLiveOrders(){
        List<List<Object>> orders = new ArrayList<>();
        try {
            String sql = "SELECT * FROM orders WHERE userID=? AND status!=? AND status!=?";
            PreparedStatement statement = con2.prepareStatement(sql);
            statement.setInt(1, loggedCustomerID);
            statement.setString(2, "decline");
            statement.setString(3, "completed");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                List<Object> order = new ArrayList<>();
                order.add(resultSet.getString("orderCode"));
                order.add(resultSet.getInt("orderTotal"));
                order.add(resultSet.getString("status"));
                order.add(resultSet.getInt("orderID"));

                int restId = resultSet.getInt("restID");
                sql = "SELECT branchName, address, mobile from restaurants where id=?";
                statement = con2.prepareStatement(sql);
                statement.setInt(1, restId);
                ResultSet resultSet1 = statement.executeQuery();
                resultSet1.next();

                order.add(resultSet1.getString("branchName"));
                order.add(resultSet1.getString("address"));
                order.add(resultSet1.getString("mobile"));

                order.add(resultSet.getString("orderType"));

                orders.add(order);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return orders;
    }

    public static List<List<Object>> getLiveOrderItems(int orderId){
        List<List<Object>> orderItems = new ArrayList<>();
        try{
            String sql = "select * from orderitems where orderID=?";
            PreparedStatement statement = con2.prepareStatement(sql);
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
                statement = con2.prepareStatement(sql);
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

    private static Boolean isLiveOrderInfoPanelOpen = false;

    public Boolean getIsLiveOrderInfoPanelOpen() {
        return isLiveOrderInfoPanelOpen;
    }

    public static void setIsLiveOrderInfoPanelOpen(Boolean isOpen) {
        isLiveOrderInfoPanelOpen = isOpen;
    }

    private static int openLiveOrderInfoPanelOrderId;

    public static void setOpenLiveOrderInfoPanelOrderId(int orderId) {
        openLiveOrderInfoPanelOrderId = orderId;
    }

    public String getOpenLiveOrderStatus(){
        String status = "";
        try {
            String sql = "select status from orders where orderID=?";
            PreparedStatement statement = con2.prepareStatement(sql);
            statement.setInt(1, openLiveOrderInfoPanelOrderId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            status = resultSet.getString("status");
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return status;
    }


    /////search panel/////
    private static String restSearchBarText = "";
    private static Boolean isRestSearchPanelOpen = false;

    public static void setIsRestSearchPanelOpen(Boolean isOpen){
        isRestSearchPanelOpen = isOpen;
    }

    public Boolean getIsRestSearchPanelOpen(){
        return isRestSearchPanelOpen;
    }

    public String getRestSearchBarText() {
        return restSearchBarText;
    }

    public static void setRestSearchBarText(String searchText) {
        restSearchBarText = searchText;
    }



    ////////favorites panel/////////
    private static Boolean isFavoritesPanelOpen = false;

    public Boolean getIsFavoritesPanelOpen() {
        return isFavoritesPanelOpen;
    }

    public static void setIsFavoritesPanelOpen(Boolean isOpen) {
        isFavoritesPanelOpen = isOpen;
    }

    private Boolean isFavRestsPanelOpen = true;
    private Boolean isFavMealsPanelOpen = false;

    public Boolean getIsFavMealsPanelOpen() {
        return isFavMealsPanelOpen;
    }

    public void setIsFavMealsPanelOpen(Boolean isOpen) {
        isFavMealsPanelOpen = isOpen;
    }

    public Boolean getIsFavRestsPanelOpen() {
        return isFavRestsPanelOpen;
    }

    public void setIsFavRestsPanelOpen(Boolean isOpen) {
        isFavRestsPanelOpen = isOpen;
    }

    public static Boolean checkIfTheRestOrMealIsInFavList(int restId, String type, int mealId){
        try {
            String sql;
            PreparedStatement statement;

            if(Objects.equals(type, "rest")){
                sql = "select * from favrestaurants where userId=? and restId=?";
                statement = con2.prepareStatement(sql);
                statement.setInt(2, restId);
            }else{
                sql = "select * from favmeals where userId=? and mealId=?";
                statement = con2.prepareStatement(sql);
                statement.setInt(2, mealId);
            }

            statement.setInt(1, loggedCustomerID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return true;
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return false;
    }

    public static void updateFavorites(int restId, String action, String type, int mealId){
        try {
            String sql;
            PreparedStatement statement;

            if(Objects.equals(type, "rest")){
                if(Objects.equals(action, "remove")){
                    sql = "delete from favrestaurants where userId=? and restId=?";
                }else{
                    sql = "insert into favrestaurants (userId, restId) values(?, ?)";
                }
                statement = con2.prepareStatement(sql);
                statement.setInt(2, restId);
            }else{
                if(Objects.equals(action, "remove")){
                    sql = "delete from favmeals where userId=? and mealId=?";
                }else{
                    sql = "insert into favmeals (userId, mealId) values(?, ?)";
                }
                statement = con2.prepareStatement(sql);
                statement.setInt(2, mealId);
            }

            statement.setInt(1, loggedCustomerID);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public List<List<Object>> getFavoriteRestaurants(){
        List<List<Object>> favRestaurants = new ArrayList<>();
        try {
            String sql = "select restId from favrestaurants where userId=?";
            PreparedStatement statement = con2.prepareStatement(sql);
            statement.setInt(1, loggedCustomerID);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                List<Object> restaurant = new ArrayList<>();
                int restId = resultSet.getInt("restId");

                sql = "select * from restaurants where id=? and isLive=?";
                statement = con2.prepareStatement(sql);
                statement.setInt(1, restId);
                statement.setString(2, "yes");
                ResultSet resultSet1 = statement.executeQuery();

                if(resultSet1.next()){
                    restaurant.add(resultSet1.getInt("id"));
                    restaurant.add(resultSet1.getString("branchName"));
                    restaurant.add(resultSet1.getString("isOpen"));

                    Blob blob = resultSet1.getBlob("image");
                    BufferedImage image;
                    byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                    image = ImageIO.read(bis);
                    restaurant.add(image);
                    restaurant.add(resultSet1.getString("allowType"));

                    favRestaurants.add(restaurant);
                }
            }
        }catch(SQLException | IOException ex){
            ex.printStackTrace();
        }

        return favRestaurants;
    }

    public List<List<Object>> getFavoriteMeals(){
        List<List<Object>> favMeals = new ArrayList<>();
        try {
            String sql = "select * from favmeals where userId=?";
            PreparedStatement statement = con2.prepareStatement(sql);
            statement.setInt(1, loggedCustomerID);
            ResultSet resultset = statement.executeQuery();
            while(resultset.next()){
                List<Object> favMeal = new ArrayList<>();

                int mealId = resultset.getInt("mealId");
                sql = "select * from menus where itemID=?";
                statement = con2.prepareStatement(sql);
                statement.setInt(1, mealId);
                ResultSet resultset1 = statement.executeQuery();

                resultset1.next();

                favMeal.add(resultset1.getString("itemName"));
                favMeal.add(resultset1.getInt("price"));
                favMeal.add(resultset1.getString("description"));

                Blob blob = resultset1.getBlob("image");
                byte[] imageBytes = blob.getBytes(1, (int) blob.length());
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                BufferedImage itemImage = ImageIO.read(bis);
                favMeal.add(itemImage);
                favMeal.add(resultset1.getInt("itemID"));

                sql = "select restaurantID from menus where itemID=?";
                statement = con2.prepareStatement(sql);
                statement.setInt(1, mealId);
                resultset1 = statement.executeQuery();
                resultset1.next();

                favMeal.add(resultset1.getInt("restaurantID"));

                favMeals.add(favMeal);
            }
        }catch(SQLException | IOException ex){
            ex.printStackTrace();
        }

        return favMeals;
    }

    public static void checkIfOpenFavMealRestOpen(int restId){
        try{
            String sql = "select isOpen from restaurants where id=?";
            PreparedStatement statement = con2.prepareStatement(sql);
            statement.setInt(1, restId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String isOpen = resultSet.getString("isOpen");
                if(Objects.equals(isOpen, "no")){
                    isRestaurantOpen = false;
                }else{
                    isRestaurantOpen = true;
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public static String getOpenFavMealRestAllowType(int restId){
        try {
            String sql = "select allowType from restaurants where id=?";
            PreparedStatement statement = con2.prepareStatement(sql);
            statement.setInt(1, restId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getString("allowType");
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return "";
    }
}


