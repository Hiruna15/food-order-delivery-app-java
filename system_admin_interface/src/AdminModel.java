import com.mysql.cj.protocol.Resultset;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.Result;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;

public class AdminModel{
    private String adminUsrname;

    private String adminPassword;

    private boolean isHomePanelOpen = false;

    private static String restDefaultPassword;

    public static String getRestDefaultPassword(){
        return restDefaultPassword;
    }

    public boolean isHomePanelOpen() {
        return isHomePanelOpen;
    }

    public void setHomePanelOpen(boolean homePanelOpen) {
        isHomePanelOpen = homePanelOpen;
    }

    public boolean isApproveSectionOpen() {
        return isApproveSectionOpen;
    }

    public void setApproveSectionOpen(boolean approveSectionOpen) {
        isApproveSectionOpen = approveSectionOpen;
    }

    private boolean isApproveSectionOpen = false;
    private static final String URL = "jdbc:mysql://localhost:3306/food_delivery_app";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static Connection con;

    {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAdminUsrname() {
        return adminUsrname;
    }

    public void setAdminUsrname(String adminUsrname) {
        this.adminUsrname = adminUsrname;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public static void generateRestDefaultPwd(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        restDefaultPassword = password.toString();
    }

    public boolean checkUserLogin(){
        try {
            String sql = "select * from systemadmin where username=? and password=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, adminUsrname);
            statement.setString(2, adminPassword);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                return true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void insertAprrovedRestData(int id, String[] reviewRestDetails){
        try{
            String sql = "delete from pendingrestaurants where id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();

            sql = "insert into restaurants (name, address, email, mobile, city, postalCode, username, password, branchName)  values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = con.prepareStatement(sql);
            statement.setString(1, reviewRestDetails[0]);
            statement.setString(2, reviewRestDetails[1]);
            statement.setString(3, reviewRestDetails[2]);
            statement.setString(4, reviewRestDetails[3]);
            statement.setString(5, reviewRestDetails[4]);
            statement.setString(6, reviewRestDetails[5]);
            statement.setString(7, reviewRestDetails[2]);
            statement.setString(8, reviewRestDetails[7]);
            statement.setString(9, reviewRestDetails[6]);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void deleteDeclinedRestData(int id){
        try{
            String sql = "delete from pendingrestaurants where id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }


    private Boolean approveRestsListOpen = true;
    private Boolean approveDriversListOpen = false;

    public Boolean getIsApproveRestsListOpen() {
        return approveRestsListOpen;
    }

    public void setIsApproveRestsListOpen(Boolean isOpen) {
        this.approveRestsListOpen = isOpen;
    }

    public Boolean getIsApproveDriversListOpen() {
        return approveDriversListOpen;
    }

    public void setIsApproveDriversListOpen(Boolean isOpen) {
        this.approveDriversListOpen = isOpen;
    }

    public List<List<Object>> getRestaurantsToBeApproved(){
        List<List<Object>> pendingRestsDetails = new ArrayList<>();
        try {
            String sql = "select * from pendingrestaurants";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                List<Object> pendingRestDetails = new ArrayList<>();
                pendingRestDetails.add(resultSet.getInt("id"));
                pendingRestDetails.add(resultSet.getString("name"));
                pendingRestDetails.add(resultSet.getString("address"));
                pendingRestDetails.add(resultSet.getString("email"));
                pendingRestDetails.add(resultSet.getString("mobile"));
                pendingRestDetails.add(resultSet.getString("city"));
                pendingRestDetails.add(resultSet.getString("postalCode"));
                pendingRestDetails.add(resultSet.getString("branchName"));
                pendingRestDetails.add(resultSet.getDate("date"));

                pendingRestsDetails.add(pendingRestDetails);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return pendingRestsDetails;
    }


    public List<List<Object>> getDriversToBeApproved(){
        List<List<Object>> pendingDrivers = new ArrayList<>();

        try {
            String sql = "select * from deliverydriver where status=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "rev");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                List<Object> driver = new ArrayList<>();
                driver.add(resultSet.getInt("id"));
                driver.add(resultSet.getString("username"));
                driver.add(resultSet.getString("email"));
                driver.add(resultSet.getString("nic"));
                driver.add(resultSet.getString("vehicleNumber"));
                driver.add(resultSet.getString("vehicleType"));
                driver.add(resultSet.getString("name"));
                driver.add(resultSet.getString("mobile"));
                driver.add(resultSet.getDate("date"));

                pendingDrivers.add(driver);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return pendingDrivers;
    }

    public void approveTheDriver(int driverId, String driverPwd){
        try {
            String sql = "update deliverydriver set status=?, password=? where id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "done");
            statement.setString(2, driverPwd);
            statement.setInt(3, driverId);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void declineTheDriver(int driverId){
        try {
            String sql = "delete from deliverydriver where id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, driverId);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }




    /////////////////home panel/////////////////
    private Boolean isTodayBtnSelected = true;
    private Boolean isThisWeekBtnSelected = false;
    private Boolean isThisMonthBtnSelected = false;

    public Boolean getIsTodayBtnSelected() {
        return isTodayBtnSelected;
    }

    public void setIsTodayBtnSelected(Boolean todayBtnSelected) {
        isTodayBtnSelected = todayBtnSelected;
    }

    public Boolean getIsThisWeekBtnSelected() {
        return isThisWeekBtnSelected;
    }

    public void setIsThisWeekBtnSelected(Boolean thisWeekBtnSelected) {
        isThisWeekBtnSelected = thisWeekBtnSelected;
    }

    public Boolean getIsThisMonthBtnSelected() {
        return isThisMonthBtnSelected;
    }

    public void setIsThisMonthBtnSelected(Boolean thisMonthBtnSelected) {
        isThisMonthBtnSelected = thisMonthBtnSelected;
    }

    public Boolean getIsThisYearBtnSelected() {
        return isThisYearBtnSelected;
    }

    public void setIsThisYearBtnSelected(Boolean thisYearBtnSelected) {
        isThisYearBtnSelected = thisYearBtnSelected;
    }

    private Boolean isThisYearBtnSelected = false;

    public List<List<Object>> getAllOrOpenRestaurants(String type){
        List<List<Object>> restaurants = new ArrayList<>();
        try {
            String sql;
            PreparedStatement statement;

            if(Objects.equals(type, "all")){
                sql = "select * from restaurants where isLive=?";
                statement = con.prepareStatement(sql);
                statement.setString(1, "yes");
            }else{
                sql = "select * from restaurants where isLive=? and isOpen=?";
                statement = con.prepareStatement(sql);
                statement.setString(1, "yes");
                statement.setString(2, "yes");
            }

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                List<Object> restaurant = new ArrayList<>();
                restaurant.add(resultSet.getString("branchName"));
                restaurant.add(resultSet.getString("allowType"));
                restaurant.add(resultSet.getString("mobile"));
                restaurant.add(resultSet.getString("city"));
                restaurant.add(resultSet.getString("isOpen"));

                byte[] imgBytes = resultSet.getBytes("image");
                InputStream in = new ByteArrayInputStream(imgBytes);
                BufferedImage image = ImageIO.read(in);

                restaurant.add(image);

                restaurants.add(restaurant);
            }

        }catch(SQLException | IOException ex){
            ex.printStackTrace();
        }

        return restaurants;
    }

    public int[] getDashBoardOrderStatics(String timePeriod){
        int totalEarning = 0;
        int totalOrders = 0;
        int totalDelivered = 0;

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
                        totalOrders ++;
                        if(resultSet.getString("status").equals("completed") && resultSet.getString("orderType").equals("Delivery")){
                            totalEarning += resultSet.getInt("orderTotal");
                            totalDelivered++;
                        }
                    }
                }else if(Objects.equals(timePeriod, "thisMonth")){
                    if(orderDate.getYear() == today.getYear() && orderDate.getMonth() == today.getMonth()){
                        totalOrders ++;
                        if(resultSet.getString("status").equals("completed") && resultSet.getString("orderType").equals("Delivery")){
                            totalEarning += resultSet.getInt("orderTotal");
                            totalDelivered++;
                        }
                    }
                }else if(Objects.equals(timePeriod, "thisYear")){
                    if(orderDate.getYear() == today.getYear()){
                        totalOrders ++;
                        if(resultSet.getString("status").equals("completed") && resultSet.getString("orderType").equals("Delivery")){
                            totalEarning += resultSet.getInt("orderTotal");
                            totalDelivered++;
                        }
                    }
                }else{
                    if(dateWeekNumber == currentWeekNumber && orderDate.getYear() == today.getYear()){
                        totalOrders ++;
                        if(resultSet.getString("status").equals("completed") && resultSet.getString("orderType").equals("Delivery")){
                            totalEarning += resultSet.getInt("orderTotal");
                            totalDelivered++;
                        }
                    }
                }
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return new int[]{totalEarning, totalOrders, totalDelivered};
    }

    public int[] getDashBoardRestaurantStatics(){
        int totalRestaurants = 0;
        int openRestaurants = 0;
        int disableRestaurants = 0;

        try {
            String sql = "select * from restaurants";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                totalRestaurants++;
                if(Objects.equals(resultSet.getString("isOpen"), "yes")){
                    openRestaurants++;
                }

                if(Objects.equals(resultSet.getString("isLive"), "no")){
                    disableRestaurants++;
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return new int[]{totalRestaurants, openRestaurants, disableRestaurants};
    }
}
