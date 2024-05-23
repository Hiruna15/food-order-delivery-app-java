import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminController implements Runnable{
    private AdminModel model;
    private LoginView loginview;
    private AdminView adminView;

    public AdminController(AdminModel model, LoginView view){
        this.model = model;
        this.loginview = view;

        loginview.addLoginButtonListner(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] userLog = view.getUsernamePassword();
                if(userLog[0].isEmpty() || userLog[1].isEmpty()){
                    view.displayInvalidLogMessage("enter username and password!");
                }
                else{
                    model.setAdminUsrname(userLog[0]);
                    model.setAdminPassword(userLog[1]);

                    if(model.checkUserLogin()){
                        loginview.closeLoginFrame();
                        adminView = new AdminView();
                        adminView.setAdminFrameVisible();
                        model.setHomePanelOpen(true);

                        adminView.addHomeBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                model.setHomePanelOpen(true);
                                model.setApproveSectionOpen(false);
                                adminView.changeContainer(true);

                                model.setIsApproveRestsListOpen(true);
                                model.setIsApproveDriversListOpen(false);
                                adminView.setClickedApproveOptionBtnStyles("rest");

                                adminView.setRestReviewPanelVisibility(false);
                                adminView.setDriverReviewPanelVisibility(false);
                            }
                        });

                        adminView.addApproveSectionBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                model.setApproveSectionOpen(true);
                                model.setHomePanelOpen(false);
                                adminView.changeContainer(false);

                                adminView.setIsAllRestsFilterOptionSelected(true);
                                adminView.setIsOpenRestsFilterOptionSelected(false);
                                adminView.setSelectedRestFilterOptionStyless("all");

                                model.setIsTodayBtnSelected(true);
                                model.setIsThisWeekBtnSelected(false);
                                model.setIsThisMonthBtnSelected(false);
                                model.setIsThisYearBtnSelected(false);
                                adminView.setSelectedTimePeriodBtnStyles("today");
                            }
                        });

                        adminView.addApproveRestOptionBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                model.setIsApproveRestsListOpen(true);
                                model.setIsApproveDriversListOpen(false);
                                adminView.setClickedApproveOptionBtnStyles("rest");

                                adminView.setDriverReviewPanelVisibility(false);
                            }
                        });

                        adminView.addApproveDriversOptionBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                model.setIsApproveRestsListOpen(false);
                                model.setIsApproveDriversListOpen(true);
                                adminView.setClickedApproveOptionBtnStyles("driver");

                                adminView.setRestReviewPanelVisibility(false);
                            }
                        });

                        adminView.addRestApproveBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int reviewRestId = adminView.getRestApproveBtnId();
                                String[] reviewRestDetails = adminView.getReviewRestDetails();

                                model.insertAprrovedRestData(reviewRestId, reviewRestDetails);
                                adminView.displayRestApprovedDialog();

                                new EmailSender("hirunadilmith15@gmail.com", reviewRestDetails[2], "Restaurant registration successfull", "Your restaurant '" +
                                        reviewRestDetails[6] + "' has been approved. now you can login to your restaurant from the username and password given below." + "\n\n" +
                                        "Username: " + reviewRestDetails[2] + "\n" + "Password: " + reviewRestDetails[7]);

                                adminView.setRestReviewPanelVisibility(false);
                            }
                        });

                        adminView.addRestDeclineBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int reviewRestId = adminView.getRestDeclineBtnId();
                                String[] reviewRestDetails = adminView.getReviewRestDetails();

                                model.deleteDeclinedRestData(reviewRestId);
                                adminView.displayRestDeclinedDialog();

                                new EmailSender("hirunadilmith15@gmail.com", reviewRestDetails[2], "Restaurant Declined", "Your restaurant '" +
                                        reviewRestDetails[6] + "' has been declined.");

                                adminView.setRestReviewPanelVisibility(false);
                            }
                        });

                        adminView.addDriverApproveBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String driverPwd = AdminModel.getRestDefaultPassword();
                                int driverId = adminView.getDriverApproveBtnId();
                                String driverName = adminView.getApproveDriverName();
                                String driverUsername = adminView.getApproveDriverUsername();
                                String driverEmail = adminView.getApproveDriverEmail();


                                model.approveTheDriver(driverId, driverPwd);
                                adminView.displayDriverApprovedMsge(driverName);

                                new EmailSender("hirunadilmith15@gmail.com", driverEmail, "Registration Successfull", "Hello " +
                                        driverName + ", you have been approved as a delivery driver. You can access your account using the username and password given below." +
                                        "\n\n" + "Username: " + driverUsername + "\n" + "Password: " + driverPwd);

                                adminView.setDriverReviewPanelVisibility(false);

                            }
                        });

                        adminView.addDriverDeclineBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int driverId = adminView.getDriverDeclineBtnId();
                                String driverName = adminView.getApproveDriverName();
                                String driverEmail = adminView.getApproveDriverEmail();

                                model.declineTheDriver(driverId);
                                adminView.displayDriverdeclinedMsge(driverName);

                                new EmailSender("hirunadilmith15@gmail.com", driverEmail, "registration has been declined", "Hello " +
                                        driverName + ", Your registration has been declined after reviewing your details");

                                adminView.setDriverReviewPanelVisibility(false);
                            }
                        });






                        /////////////////home panel/////////////////
                        adminView.addAllRestFilterBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                adminView.setIsAllRestsFilterOptionSelected(true);
                                adminView.setIsOpenRestsFilterOptionSelected(false);
                                adminView.setSelectedRestFilterOptionStyless("all");
                            }
                        });

                        adminView.addOpenRestFilterBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                adminView.setIsAllRestsFilterOptionSelected(false);
                                adminView.setIsOpenRestsFilterOptionSelected(true);
                                adminView.setSelectedRestFilterOptionStyless("open");
                            }
                        });

                        adminView.addTodayBtnBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                model.setIsTodayBtnSelected(true);
                                model.setIsThisWeekBtnSelected(false);
                                model.setIsThisMonthBtnSelected(false);
                                model.setIsThisYearBtnSelected(false);
                                adminView.setSelectedTimePeriodBtnStyles("today");
                            }
                        });

                        adminView.addThisWeekBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                model.setIsTodayBtnSelected(false);
                                model.setIsThisWeekBtnSelected(true);
                                model.setIsThisMonthBtnSelected(false);
                                model.setIsThisYearBtnSelected(false);
                                adminView.setSelectedTimePeriodBtnStyles("week");
                            }
                        });

                        adminView.addThiMonthBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                model.setIsTodayBtnSelected(false);
                                model.setIsThisWeekBtnSelected(false);
                                model.setIsThisMonthBtnSelected(true);
                                model.setIsThisYearBtnSelected(false);
                                adminView.setSelectedTimePeriodBtnStyles("month");
                            }
                        });

                        adminView.addThisYearBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                model.setIsTodayBtnSelected(false);
                                model.setIsThisWeekBtnSelected(false);
                                model.setIsThisMonthBtnSelected(false);
                                model.setIsThisYearBtnSelected(true);
                                adminView.setSelectedTimePeriodBtnStyles("year");
                            }
                        });

                    }
                    else{
                        loginview.displayInvalidLogMessage("Invalid login details!");
                    }
                }
            }
        });
    }

    public void run(){
        while(true){
            if(model.isApproveSectionOpen()){
                if(model.getIsApproveRestsListOpen()){
                    List<List<Object>> pendingRestsDetails = model.getRestaurantsToBeApproved();
                    adminView.loadApproveRestsList(pendingRestsDetails);
                }else{
                    List<List<Object>> pendingDrivers = model.getDriversToBeApproved();
                    adminView.loadApproveDriversList(pendingDrivers);
                }
            }else if(model.isHomePanelOpen()){
                if(adminView.getIsAllRestsFilterOptionSelected()){
                    List<List<Object>> restaurants = model.getAllOrOpenRestaurants("all");
                    adminView.loadRestaurantsToHome(restaurants);
                }else if(adminView.getIsOpenRestsFilterOptionSelected()){
                    List<List<Object>> restaurants = model.getAllOrOpenRestaurants("open");
                    adminView.loadRestaurantsToHome(restaurants);
                }

                int[] ordersStatics;
                if(model.getIsTodayBtnSelected()){
                    ordersStatics = model.getDashBoardOrderStatics("today");
                }else if(model.getIsThisWeekBtnSelected()){
                    ordersStatics = model.getDashBoardOrderStatics("thisWeak");
                }else if(model.getIsThisMonthBtnSelected()){
                    ordersStatics = model.getDashBoardOrderStatics("thisMonth");
                }else{
                    ordersStatics = model.getDashBoardOrderStatics("thisYear");
                }

                adminView.loadDashBoardOrderStatics(ordersStatics);


                int[] restaurantsStatics = model.getDashBoardRestaurantStatics();
                adminView.loadDashBoardRestaurantStatics(restaurantsStatics);
            }

            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

