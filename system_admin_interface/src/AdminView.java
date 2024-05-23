import com.mysql.cj.exceptions.CJPacketTooBigException;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.JavaBean;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Flow;

public class AdminView {

    JButton homeeBtn, approveBtn, declineBtn;
    JButton approveSectionBtn;
    JPanel homeContainerPanel;
    JPanel approveSectionPanel;
    JTable tobeApprovedTable;
    DefaultTableModel tableModel;
    JTextField approveIdText, approveNameText, approveUnameText;
    JTextField approvePwdText;
    JButton approveRestBtn, approveDriversBtn;
    JFrame adminFrame;
    JPanel restReviewPanel;
    JPanel approveListPanel;
    JLabel approveRestName, approveRestBranchName, approveRestEmail, approveRestCity, approveRestPCode;
    JTextArea approveRestAddress;
    JTextField approveRestUsernameText, restPasswordText;
    JButton restApproveBtn, restDeclineBtn;
    JPanel driverReviewPanel;
    JLabel approveDriverName, approveDriverUsername, approveDriverEmail, approveDriverNic, approveDriverVNumber, approveDriverVType, approveDriverMobile;
    JTextField approveDriverUsernameTxt, approveDriverPwdTxt;
    JButton driverApproveBtn, driverDeclineBtn;
    JButton allRestFilterBtn, openRestFilterBtn;
    JScrollPane homeContainerPanelScrollPane;
    JPanel homePanelRestsListPanel;
    JButton todayBtn, thisWeekBtn, thisMonthBtn, thisYearBtn;
    JLabel totalEarnings, totalOrders, totalDeliveredOrders;
    JLabel totalRestaurants, totalOpenRestaurants, totalDisabledRestaurants;

    public AdminView(){
        adminFrame = new JFrame();
        adminFrame.setSize(1500, 800);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setResizable(false);
        adminFrame.setLocationRelativeTo(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1500, 800));

        JPanel adminPanel = new JPanel();
        adminPanel.setBounds(0, 0, 1500, 800);
//        adminPanel.setLayout(new BorderLayout());
        adminPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
//        adminFrame.add(adminPanel);
        layeredPane.add(adminPanel, JLayeredPane.DEFAULT_LAYER);

        JPanel sideBarPanel = new JPanel();
        sideBarPanel.setPreferredSize(new Dimension(221, 800));
        sideBarPanel.setBackground(new Color(0x262626));
        sideBarPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
        adminPanel.add(sideBarPanel);

        //////////////////////////////////
        homeContainerPanel = new JPanel();
        homeContainerPanel.setPreferredSize(new Dimension(1279, 1140));
        homeContainerPanel.setLayout(null);
        homeContainerPanel.setBackground(new Color(0x404040));

        homeContainerPanelScrollPane = new JScrollPane(homeContainerPanel);
        homeContainerPanelScrollPane.setPreferredSize(new Dimension(1279, 800));
        homeContainerPanelScrollPane.setBorder(null);
        adminPanel.add(homeContainerPanelScrollPane);
        homeContainerPanelScrollPane.setVisible(true);
        homeContainerPanelScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));

        JLabel dashBoardLabel = new JLabel("Dashboard");
        dashBoardLabel.setFont(new Font(null, Font.BOLD, 30));
        dashBoardLabel.setForeground(Color.WHITE);
        dashBoardLabel.setBounds(40, 25, 300, 35);
        homeContainerPanel.add(dashBoardLabel);

        allRestFilterBtn = new JButton("ALL");
        allRestFilterBtn.setFont(new Font(null,Font.PLAIN, 15));
        allRestFilterBtn.setForeground(Color.WHITE);
        allRestFilterBtn.setBounds(40, 110, 95, 30);
        allRestFilterBtn.setBackground(new Color(0x1035F4));
        allRestFilterBtn.setFocusable(false);
        homeContainerPanel.add(allRestFilterBtn);

        openRestFilterBtn = new JButton("OPEN");
        openRestFilterBtn.setFont(new Font(null,Font.PLAIN, 15));
        openRestFilterBtn.setForeground(Color.WHITE);
        openRestFilterBtn.setBounds(136, 110, 95, 30);
        openRestFilterBtn.setBackground(new Color(0x7994C7));
        openRestFilterBtn.setFocusable(false);
        homeContainerPanel.add(openRestFilterBtn);

        /////////////....................
        homePanelRestsListPanel = new JPanel();
        homePanelRestsListPanel.setPreferredSize(new Dimension(1163, 380));
        homePanelRestsListPanel.setBackground(new Color(0x2D2D2D));
        homePanelRestsListPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));

        JScrollPane homePanelRestsListPanelScrollPane = new JScrollPane(homePanelRestsListPanel);
        homePanelRestsListPanelScrollPane.setBounds(40, 160, 1163, 380);
        homePanelRestsListPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        homePanelRestsListPanelScrollPane.setBorder(null);
        homeContainerPanel.add(homePanelRestsListPanelScrollPane);
        //////////////..................

        todayBtn = new JButton("Today");
        todayBtn.setFont(new Font(null, Font.PLAIN, 14));
        todayBtn.setForeground(Color.WHITE);
        todayBtn.setBounds(120, 570, 110, 30);
        todayBtn.setFocusable(false);
        todayBtn.setBackground(new Color(0x1035F4));
        todayBtn.setForeground(Color.WHITE);
        homeContainerPanel.add(todayBtn);

        thisWeekBtn = new JButton("This Week");
        thisWeekBtn.setFont(new Font(null, Font.PLAIN, 14));
        thisWeekBtn.setForeground(Color.WHITE);
        thisWeekBtn.setBounds(231, 570, 110, 30);
        thisWeekBtn.setFocusable(false);
        thisWeekBtn.setBackground(new Color(0x7994C7));
        thisWeekBtn.setForeground(Color.WHITE);
        homeContainerPanel.add(thisWeekBtn);

        thisMonthBtn = new JButton("This Month");
        thisMonthBtn.setFont(new Font(null, Font.PLAIN, 14));
        thisMonthBtn.setForeground(Color.WHITE);
        thisMonthBtn.setBounds(342, 570, 110, 30);
        thisMonthBtn.setFocusable(false);
        thisMonthBtn.setBackground(new Color(0x7994C7));
        thisMonthBtn.setForeground(Color.WHITE);
        homeContainerPanel.add(thisMonthBtn);

        thisYearBtn = new JButton("This Year");
        thisYearBtn.setFont(new Font(null, Font.PLAIN, 14));
        thisYearBtn.setForeground(Color.WHITE);
        thisYearBtn.setBounds(453, 570, 110, 30);
        thisYearBtn.setFocusable(false);
        thisYearBtn.setBackground(new Color(0x7994C7));
        thisYearBtn.setForeground(Color.WHITE);
        homeContainerPanel.add(thisYearBtn);

        JPanel totalEarningsPanel = new JPanel();
        totalEarningsPanel.setBounds(120, 635, 283, 124);
        totalEarningsPanel.setBackground(new Color(0x272727));
        totalEarningsPanel.setLayout(null);
        homeContainerPanel.add(totalEarningsPanel);

        JLabel totalEarningsLabel = new JLabel("Total Earnings");
        totalEarningsLabel.setFont(new Font(null, Font.BOLD, 18));
        totalEarningsLabel.setForeground(Color.WHITE);
        totalEarningsLabel.setBounds(80, 15, 150, 20);
        totalEarningsPanel.add(totalEarningsLabel);

        totalEarnings = new JLabel();
        totalEarnings.setFont(new Font(null, Font.BOLD, 22));
        totalEarnings.setForeground(Color.WHITE);
        totalEarnings.setBounds(10, 70, 270, 20);
        totalEarnings.setHorizontalAlignment(SwingConstants.CENTER);
        totalEarningsPanel.add(totalEarnings);

        JPanel totalOrdersPanel = new JPanel();
        totalOrdersPanel.setBounds(450, 635, 283, 124);
        totalOrdersPanel.setBackground(new Color(0x272727));
        totalOrdersPanel.setLayout(null);
        homeContainerPanel.add(totalOrdersPanel);

        JLabel totalOrdersLabel = new JLabel("Total Orders");
        totalOrdersLabel.setFont(new Font(null, Font.BOLD, 18));
        totalOrdersLabel.setForeground(Color.WHITE);
        totalOrdersLabel.setBounds(90, 15, 150, 20);
        totalOrdersPanel.add(totalOrdersLabel);

        totalOrders = new JLabel();
        totalOrders.setBounds(10, 50, 270, 50);
        totalOrders.setFont(new Font(null, Font.BOLD, 45));
        totalOrders.setForeground(Color.WHITE);
        totalOrders.setHorizontalAlignment(SwingConstants.CENTER);
        totalOrdersPanel.add(totalOrders);

        JPanel totalDeliveredPanel = new JPanel();
        totalDeliveredPanel.setBounds(780, 635, 283, 124);
        totalDeliveredPanel.setBackground(new Color(0x272727));
        totalDeliveredPanel.setLayout(null);
        homeContainerPanel.add(totalDeliveredPanel);

        JLabel totalDeliveredLabel = new JLabel("Total Delivered");
        totalDeliveredLabel.setFont(new Font(null, Font.BOLD, 18));
        totalDeliveredLabel.setForeground(Color.WHITE);
        totalDeliveredLabel.setBounds(90, 15, 150, 20);
        totalDeliveredPanel.add(totalDeliveredLabel);

        totalDeliveredOrders = new JLabel();
        totalDeliveredOrders.setBounds(10, 50, 270, 50);
        totalDeliveredOrders.setFont(new Font(null, Font.BOLD, 45));
        totalDeliveredOrders.setForeground(Color.WHITE);
        totalDeliveredOrders.setHorizontalAlignment(SwingConstants.CENTER);
        totalDeliveredPanel.add(totalDeliveredOrders);

        JPanel horizontalLinePanel = new JPanel();
        horizontalLinePanel.setBounds(120, 810, 937, 4);
        horizontalLinePanel.setBackground(new Color(0x2C2C2C));
        homeContainerPanel.add(horizontalLinePanel);

        //////////
        JPanel totalRestaurantsPanel = new JPanel();
        totalRestaurantsPanel.setBounds(120, 870, 283, 124);
        totalRestaurantsPanel.setBackground(new Color(0x272727));
        totalRestaurantsPanel.setLayout(null);
        homeContainerPanel.add(totalRestaurantsPanel);

        JLabel totalRestaurantsLabel = new JLabel("Total Restaurants");
        totalRestaurantsLabel.setFont(new Font(null, Font.BOLD, 18));
        totalRestaurantsLabel.setForeground(Color.WHITE);
        totalRestaurantsLabel.setBounds(60, 15, 170, 20);
        totalRestaurantsPanel.add(totalRestaurantsLabel);

        totalRestaurants = new JLabel();
        totalRestaurants.setBounds(10, 50, 270, 50);
        totalRestaurants.setFont(new Font(null, Font.BOLD, 45));
        totalRestaurants.setForeground(Color.WHITE);
        totalRestaurants.setHorizontalAlignment(SwingConstants.CENTER);
        totalRestaurantsPanel.add(totalRestaurants);

        JPanel openRestaurantsPanel = new JPanel();
        openRestaurantsPanel.setBounds(450, 870, 283, 124);
        openRestaurantsPanel.setBackground(new Color(0x272727));
        openRestaurantsPanel.setLayout(null);
        homeContainerPanel.add(openRestaurantsPanel);

        JLabel totalOpenRestaurantsLabel = new JLabel("Open Restaurants");
        totalOpenRestaurantsLabel.setFont(new Font(null, Font.BOLD, 18));
        totalOpenRestaurantsLabel.setForeground(Color.WHITE);
        totalOpenRestaurantsLabel.setBounds(60, 15, 170, 20);
        openRestaurantsPanel.add(totalOpenRestaurantsLabel);

        totalOpenRestaurants = new JLabel();
        totalOpenRestaurants.setBounds(10, 50, 270, 50);
        totalOpenRestaurants.setFont(new Font(null, Font.BOLD, 45));
        totalOpenRestaurants.setForeground(Color.WHITE);
        totalOpenRestaurants.setHorizontalAlignment(SwingConstants.CENTER);
        openRestaurantsPanel.add(totalOpenRestaurants);

        JPanel disabledRestaurantsPanel = new JPanel();
        disabledRestaurantsPanel.setBounds(780, 870, 283, 124);
        disabledRestaurantsPanel.setBackground(new Color(0x272727));
        disabledRestaurantsPanel.setLayout(null);
        homeContainerPanel.add(disabledRestaurantsPanel);

        JLabel totalDisabledRestaurantsLabel = new JLabel("Disable Restaurants");
        totalDisabledRestaurantsLabel.setFont(new Font(null, Font.BOLD, 18));
        totalDisabledRestaurantsLabel.setForeground(Color.WHITE);
        totalDisabledRestaurantsLabel.setBounds(60, 15, 190, 20);
        disabledRestaurantsPanel.add(totalDisabledRestaurantsLabel);

        totalDisabledRestaurants = new JLabel();
        totalDisabledRestaurants.setBounds(10, 50, 270, 50);
        totalDisabledRestaurants.setFont(new Font(null, Font.BOLD, 45));
        totalDisabledRestaurants.setForeground(Color.WHITE);
        totalDisabledRestaurants.setHorizontalAlignment(SwingConstants.CENTER);
        disabledRestaurantsPanel.add(totalDisabledRestaurants);
        /////////////

        ///////////////////////////////////

        JLabel logo = new JLabel("LOGO");
        logo.setFont(new Font(null, Font.BOLD, 30));
        logo.setForeground(Color.white);
        sideBarPanel.add(logo);

        homeeBtn = new JButton("Home");
        homeeBtn.setPreferredSize(new Dimension(200, 40));
        homeeBtn.setFocusable(false);
        homeeBtn.setBackground(new Color(0x1035F4));
        homeeBtn.setForeground(Color.white);
        sideBarPanel.add(homeeBtn);

        approveSectionBtn = new JButton("Approve Section");
        approveSectionBtn.setPreferredSize(new Dimension(200, 40));
        approveSectionBtn.setFocusable(false);
        approveSectionBtn.setBackground(new Color(0x1035F4));
        approveSectionBtn.setForeground(Color.white);
        sideBarPanel.add(approveSectionBtn);

        JPanel signoutPanel = new JPanel();
        signoutPanel.setPreferredSize(new Dimension(221, 450));
        signoutPanel.setBackground(new Color(0x262626));
        signoutPanel.setLayout(new BorderLayout());
        sideBarPanel.add(signoutPanel);

        JButton signoutBtn = new JButton("Sign Out");
        signoutBtn.setPreferredSize(new Dimension(200, 40));
        signoutBtn.setFocusable(false);
        signoutBtn.setForeground(Color.white);
        signoutBtn.setBackground(new Color(0x1035F4));
        signoutPanel.add(signoutBtn, BorderLayout.SOUTH);


        //////////////////////////////////
        approveSectionPanel = new JPanel();
        approveSectionPanel.setPreferredSize(new Dimension(1279, 800));
        approveSectionPanel.setBackground(new Color(0x4E4E4E));
        approveSectionPanel.setLayout(null);
        approveSectionPanel.setVisible(false);
        adminPanel.add(approveSectionPanel);

        JPanel approveListContainerPanel = new JPanel();
        approveListContainerPanel.setBounds(0, 0, 384, 800);
        approveListContainerPanel.setBackground(new Color(0x3C3C3C));
        approveListContainerPanel.setLayout(null);
        approveSectionPanel.add(approveListContainerPanel);

        approveRestBtn = new JButton("Restaurants");
        approveRestBtn.setBounds(30, 20, 157, 36);
        approveRestBtn.setFont(new Font(null, Font.BOLD, 14));
        approveRestBtn.setForeground(Color.WHITE);
        approveRestBtn.setFocusable(false);
        approveRestBtn.setBackground(new Color(0x1035F4));
        approveListContainerPanel.add(approveRestBtn);

        approveDriversBtn = new JButton("Delivery Drivers");
        approveDriversBtn.setBounds(188, 20, 157, 36);
        approveDriversBtn.setFont(new Font(null, Font.BOLD, 14));
        approveDriversBtn.setForeground(Color.WHITE);
        approveDriversBtn.setFocusable(false);
        approveDriversBtn.setBackground(new Color(0x5F5F5F));
        approveListContainerPanel.add(approveDriversBtn);

        ////....
        approveListPanel = new JPanel();
        approveListPanel.setPreferredSize(new Dimension(350, 670));
        approveListPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        approveListPanel.setBackground(new Color(0x636363));

        JScrollPane approveListPanelScrollPane = new JScrollPane(approveListPanel);
        approveListPanelScrollPane.setBounds(14, 75, 350, 670);
        approveListPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        approveListPanelScrollPane.setBorder(null);
        approveListContainerPanel.add(approveListPanelScrollPane);
        ////....

        restReviewPanel = new JPanel();
        restReviewPanel.setBounds(385, 0, 895, 800);
        restReviewPanel.setBackground(new Color(0x4E4E4E));
        restReviewPanel.setLayout(null);
        restReviewPanel.setVisible(false);
        approveSectionPanel.add(restReviewPanel);

        JPanel restReviewPanelRestDetailsPanel = new JPanel();
        restReviewPanelRestDetailsPanel.setBounds(20, 100, 465, 578);
        restReviewPanelRestDetailsPanel.setBackground(new Color(0x3A3A3A));
        restReviewPanelRestDetailsPanel.setLayout(null);
        restReviewPanel.add(restReviewPanelRestDetailsPanel);

        JLabel approveRestNameLabel = new JLabel("Restaurant Name:");
        approveRestNameLabel.setFont(new Font(null, Font.BOLD, 18));
        approveRestNameLabel.setForeground(new Color(0xBBBBBB));
        approveRestNameLabel.setBounds(30, 25, 200, 20);
        restReviewPanelRestDetailsPanel.add(approveRestNameLabel);

        approveRestName = new JLabel("Pizza Hut");
        approveRestName.setFont(new Font(null,Font.PLAIN, 17));
        approveRestName.setForeground(Color.WHITE);
        approveRestName.setBounds(30, 50, 400, 20);
        restReviewPanelRestDetailsPanel.add(approveRestName);

        JLabel approveRestBranchNameLabel = new JLabel("Branch Name:");
        approveRestBranchNameLabel.setFont(new Font(null, Font.BOLD, 18));
        approveRestBranchNameLabel.setForeground(new Color(0xBBBBBB));
        approveRestBranchNameLabel.setBounds(30, 100, 200, 20);
        restReviewPanelRestDetailsPanel.add(approveRestBranchNameLabel);

        approveRestBranchName = new JLabel("Pizza Hut Matara");
        approveRestBranchName.setFont(new Font(null,Font.PLAIN, 17));
        approveRestBranchName.setForeground(Color.WHITE);
        approveRestBranchName.setBounds(30, 125, 400, 20);
        restReviewPanelRestDetailsPanel.add(approveRestBranchName);

        JLabel approveRestEmailLabel = new JLabel("Email:");
        approveRestEmailLabel.setFont(new Font(null, Font.BOLD, 18));
        approveRestEmailLabel.setForeground(new Color(0xBBBBBB));
        approveRestEmailLabel.setBounds(30, 175, 200, 20);
        restReviewPanelRestDetailsPanel.add(approveRestEmailLabel);

        approveRestEmail = new JLabel("hirunadilmith15@gmail.com");
        approveRestEmail.setFont(new Font(null,Font.PLAIN, 17));
        approveRestEmail.setForeground(Color.WHITE);
        approveRestEmail.setBounds(30, 200, 400, 20);
        restReviewPanelRestDetailsPanel.add(approveRestEmail);

        JLabel approveRestCityLabel = new JLabel("City:");
        approveRestCityLabel.setFont(new Font(null, Font.BOLD, 18));
        approveRestCityLabel.setForeground(new Color(0xBBBBBB));
        approveRestCityLabel.setBounds(30, 250, 200, 20);
        restReviewPanelRestDetailsPanel.add(approveRestCityLabel);

        approveRestCity = new JLabel("Matara");
        approveRestCity.setFont(new Font(null,Font.PLAIN, 17));
        approveRestCity.setForeground(Color.WHITE);
        approveRestCity.setBounds(30, 275, 400, 20);
        restReviewPanelRestDetailsPanel.add(approveRestCity);

        JLabel approveRestPCodeLabel = new JLabel("Postal Code:");
        approveRestPCodeLabel.setFont(new Font(null, Font.BOLD, 18));
        approveRestPCodeLabel.setForeground(new Color(0xBBBBBB));
        approveRestPCodeLabel.setBounds(30, 325, 200, 20);
        restReviewPanelRestDetailsPanel.add(approveRestPCodeLabel);

        approveRestPCode = new JLabel("12345");
        approveRestPCode.setFont(new Font(null,Font.PLAIN, 17));
        approveRestPCode.setForeground(Color.WHITE);
        approveRestPCode.setBounds(30, 350, 400, 20);
        restReviewPanelRestDetailsPanel.add(approveRestPCode);

        JLabel approveRestAddressLabel = new JLabel("Address:");
        approveRestAddressLabel.setFont(new Font(null, Font.BOLD, 18));
        approveRestAddressLabel.setForeground(new Color(0xBBBBBB));
        approveRestAddressLabel.setBounds(30, 400, 200, 20);
        restReviewPanelRestDetailsPanel.add(approveRestAddressLabel);

        approveRestAddress = new JTextArea();
        approveRestAddress.setText("No 15 Henewaththa thihagoda");
        approveRestAddress.setBounds(30, 425, 300, 80);
        approveRestAddress.setFont(new Font(null, Font.PLAIN, 17));
        approveRestAddress.setLineWrap(true);
        approveRestAddress.setWrapStyleWord(true);
        approveRestAddress.setEditable(false);
        approveRestAddress.setFocusable(false);
        approveRestAddress.setBackground(null);
        approveRestAddress.setForeground(Color.WHITE);
        restReviewPanelRestDetailsPanel.add(approveRestAddress);

        JPanel restApprovePanel = new JPanel();
        restApprovePanel.setBounds(495, 240, 365, 300);
        restApprovePanel.setBackground(new Color(0x3A3A3A));
        restApprovePanel.setLayout(null);
        restReviewPanel.add(restApprovePanel);

        JLabel restUserNameLabel = new JLabel("Username");
        restUserNameLabel.setBounds(30, 30, 100, 18);
        restUserNameLabel.setForeground(Color.WHITE);
        restUserNameLabel.setFont(new Font(null, Font.PLAIN, 16));
        restApprovePanel.add(restUserNameLabel);

        approveRestUsernameText = new JTextField();
        approveRestUsernameText.setBounds(30, 52, 307, 26);
        approveRestUsernameText.setBorder(null);
        restApprovePanel.add(approveRestUsernameText);

        JLabel restPasswordLabel = new JLabel("Password");
        restPasswordLabel.setBounds(30, 100, 100, 18);
        restPasswordLabel.setForeground(Color.WHITE);
        restPasswordLabel.setFont(new Font(null, Font.PLAIN, 16));
        restApprovePanel.add(restPasswordLabel);

        restPasswordText = new JTextField();
        restPasswordText.setBounds(30, 122, 307, 26);
        restPasswordText.setBorder(null);
        restApprovePanel.add(restPasswordText);

        restApproveBtn = new JButton("APPROVE");
        restApproveBtn.setBounds(30, 200, 110, 33);
        restApproveBtn.setBackground(new Color(0x0085FF));
        restApproveBtn.setForeground(Color.WHITE);
        restApproveBtn.setFocusable(false);
        restApproveBtn.setFont(new Font(null, Font.PLAIN, 14));
        restApprovePanel.add(restApproveBtn);

        restDeclineBtn = new JButton("DECLINE");
        restDeclineBtn.setBounds(250, 200, 98, 33);
        restDeclineBtn.setBackground(new Color(0x3A3A3A));
        restDeclineBtn.setBorder(null);
        restDeclineBtn.setFocusable(false);
        restDeclineBtn.setForeground(new Color(0xFF0000));
        restDeclineBtn.setFont(new Font(null, Font.PLAIN, 14));
        restApprovePanel.add(restDeclineBtn);



        //////////////////////////
        driverReviewPanel = new JPanel();
        driverReviewPanel.setBounds(385, 0, 895, 800);
        driverReviewPanel.setBackground(new Color(0x4E4E4E));
        driverReviewPanel.setLayout(null);
        driverReviewPanel.setVisible(false);
        approveSectionPanel.add(driverReviewPanel);

        JPanel driverReviewPanelDriverDetailsPanel = new JPanel();
        driverReviewPanelDriverDetailsPanel.setBounds(20, 100, 465, 600);
        driverReviewPanelDriverDetailsPanel.setBackground(new Color(0x3A3A3A));
        driverReviewPanelDriverDetailsPanel.setLayout(null);
        driverReviewPanel.add(driverReviewPanelDriverDetailsPanel);

        JLabel approveDriverNameLbl = new JLabel("Driver Name:");
        approveDriverNameLbl.setFont(new Font(null, Font.BOLD, 18));
        approveDriverNameLbl.setForeground(new Color(0xBBBBBB));
        approveDriverNameLbl.setBounds(30, 25, 200, 20);
        driverReviewPanelDriverDetailsPanel.add(approveDriverNameLbl);

        approveDriverName = new JLabel("Hiruna Dilmith");
        approveDriverName.setFont(new Font(null,Font.PLAIN, 17));
        approveDriverName.setForeground(Color.WHITE);
        approveDriverName.setBounds(30, 50, 400, 20);
        driverReviewPanelDriverDetailsPanel.add(approveDriverName);

        JLabel approveDriverUsernameLbl = new JLabel("Username:");
        approveDriverUsernameLbl.setFont(new Font(null, Font.BOLD, 18));
        approveDriverUsernameLbl.setForeground(new Color(0xBBBBBB));
        approveDriverUsernameLbl.setBounds(30, 100, 200, 20);
        driverReviewPanelDriverDetailsPanel.add(approveDriverUsernameLbl);

        approveDriverUsername = new JLabel("Hiruvah");
        approveDriverUsername.setFont(new Font(null,Font.PLAIN, 17));
        approveDriverUsername.setForeground(Color.WHITE);
        approveDriverUsername.setBounds(30, 125, 400, 20);
        driverReviewPanelDriverDetailsPanel.add(approveDriverUsername);

        JLabel approveDriverEmailLbl = new JLabel("Email:");
        approveDriverEmailLbl.setFont(new Font(null, Font.BOLD, 18));
        approveDriverEmailLbl.setForeground(new Color(0xBBBBBB));
        approveDriverEmailLbl.setBounds(30, 175, 200, 20);
        driverReviewPanelDriverDetailsPanel.add(approveDriverEmailLbl);

        approveDriverEmail = new JLabel("hirunadilmith15@gmail.com");
        approveDriverEmail.setFont(new Font(null,Font.PLAIN, 17));
        approveDriverEmail.setForeground(Color.WHITE);
        approveDriverEmail.setBounds(30, 200, 400, 20);
        driverReviewPanelDriverDetailsPanel.add(approveDriverEmail);

        JLabel approveDriverNicLbl = new JLabel("NIC:");
        approveDriverNicLbl.setFont(new Font(null, Font.BOLD, 18));
        approveDriverNicLbl.setForeground(new Color(0xBBBBBB));
        approveDriverNicLbl.setBounds(30, 250, 200, 20);
        driverReviewPanelDriverDetailsPanel.add(approveDriverNicLbl);

        approveDriverNic  = new JLabel("1234567890");
        approveDriverNic.setFont(new Font(null,Font.PLAIN, 17));
        approveDriverNic.setForeground(Color.WHITE);
        approveDriverNic.setBounds(30, 275, 400, 20);
        driverReviewPanelDriverDetailsPanel.add(approveDriverNic);

        JLabel approveDriverVNumberLbl = new JLabel("Vehicle Number:");
        approveDriverVNumberLbl.setFont(new Font(null, Font.BOLD, 18));
        approveDriverVNumberLbl.setForeground(new Color(0xBBBBBB));
        approveDriverVNumberLbl.setBounds(30, 325, 200, 20);
        driverReviewPanelDriverDetailsPanel.add(approveDriverVNumberLbl);

        approveDriverVNumber = new JLabel("CAE-9018");
        approveDriverVNumber.setFont(new Font(null,Font.PLAIN, 17));
        approveDriverVNumber.setForeground(Color.WHITE);
        approveDriverVNumber.setBounds(30, 350, 400, 20);
        driverReviewPanelDriverDetailsPanel.add(approveDriverVNumber);

        JLabel approveDriverVTypeLbl = new JLabel("Vehicle Type:");
        approveDriverVTypeLbl.setFont(new Font(null, Font.BOLD, 18));
        approveDriverVTypeLbl.setForeground(new Color(0xBBBBBB));
        approveDriverVTypeLbl.setBounds(30, 400, 200, 20);
        driverReviewPanelDriverDetailsPanel.add(approveDriverVTypeLbl);

        approveDriverVType = new JLabel("Motor Bike");
        approveDriverVType.setFont(new Font(null,Font.PLAIN, 17));
        approveDriverVType.setForeground(Color.WHITE);
        approveDriverVType.setBounds(30, 425, 300, 20);
        driverReviewPanelDriverDetailsPanel.add(approveDriverVType);

        JLabel approveDriverMobileLbl = new JLabel("Mobile:");
        approveDriverMobileLbl.setFont(new Font(null, Font.BOLD, 18));
        approveDriverMobileLbl.setForeground(new Color(0xBBBBBB));
        approveDriverMobileLbl.setBounds(30, 475, 200, 20);
        driverReviewPanelDriverDetailsPanel.add(approveDriverMobileLbl);

        approveDriverMobile = new JLabel("0767139018");
        approveDriverMobile.setFont(new Font(null,Font.PLAIN, 17));
        approveDriverMobile.setForeground(Color.WHITE);
        approveDriverMobile.setBounds(30, 500, 300, 20);
        driverReviewPanelDriverDetailsPanel.add(approveDriverMobile);

        JPanel driverApprovePanel = new JPanel();
        driverApprovePanel.setBounds(495, 240, 365, 300);
        driverApprovePanel.setBackground(new Color(0x3A3A3A));
        driverApprovePanel.setLayout(null);
        driverReviewPanel.add(driverApprovePanel);

        JLabel driverUserNameLabel = new JLabel("Username");
        driverUserNameLabel.setBounds(30, 30, 100, 18);
        driverUserNameLabel.setForeground(Color.WHITE);
        driverUserNameLabel.setFont(new Font(null, Font.PLAIN, 16));
        driverApprovePanel.add(driverUserNameLabel);

        approveDriverUsernameTxt = new JTextField();
        approveDriverUsernameTxt.setBounds(30, 52, 307, 26);
        approveDriverUsernameTxt.setBorder(null);
        driverApprovePanel.add(approveDriverUsernameTxt);

        JLabel driverPwdLabel = new JLabel("Password");
        driverPwdLabel.setBounds(30, 100, 100, 18);
        driverPwdLabel.setForeground(Color.WHITE);
        driverPwdLabel.setFont(new Font(null, Font.PLAIN, 16));
        driverApprovePanel.add(driverPwdLabel);

        approveDriverPwdTxt = new JTextField();
        approveDriverPwdTxt.setBounds(30, 122, 307, 26);
        approveDriverPwdTxt.setBorder(null);
        driverApprovePanel.add(approveDriverPwdTxt);

        driverApproveBtn = new JButton("APPROVE");
        driverApproveBtn.setBounds(30, 200, 110, 33);
        driverApproveBtn.setBackground(new Color(0x0085FF));
        driverApproveBtn.setForeground(Color.WHITE);
        driverApproveBtn.setFocusable(false);
        driverApproveBtn.setFont(new Font(null, Font.PLAIN, 14));
        driverApprovePanel.add(driverApproveBtn);

        driverDeclineBtn = new JButton("DECLINE");
        driverDeclineBtn.setBounds(250, 200, 98, 33);
        driverDeclineBtn.setBackground(new Color(0x3A3A3A));
        driverDeclineBtn.setBorder(null);
        driverDeclineBtn.setFocusable(false);
        driverDeclineBtn.setForeground(new Color(0xFF0000));
        driverDeclineBtn.setFont(new Font(null, Font.PLAIN, 14));
        driverApprovePanel.add(driverDeclineBtn);
        /////////////////////////

        //////////////////

        adminFrame.add(layeredPane);
        adminFrame.setVisible(true);
    }

    public void setAdminFrameVisible(){
        adminFrame.setVisible(true);
    }

    public void addHomeBtnListener(ActionListener listener){
        homeeBtn.addActionListener(listener);
    }

    public void addApproveSectionBtnListener(ActionListener listener){
        approveSectionBtn.addActionListener(listener);
    }

    public void changeContainer(boolean open){
        homeContainerPanelScrollPane.setVisible(open);
        approveSectionPanel.setVisible(!open);
    }

    public void displayRestApprovedDialog(){
        JOptionPane.showMessageDialog(approveSectionPanel, "Restaurant approved", null,  JOptionPane.PLAIN_MESSAGE);
    }

    public void displayRestDeclinedDialog(){
        JOptionPane.showMessageDialog(approveSectionPanel, "Restaurant Declined", null,  JOptionPane.PLAIN_MESSAGE);
    }

    public void addApproveRestOptionBtnListener(ActionListener listener){
        approveRestBtn.addActionListener(listener);
    }

    public void addApproveDriversOptionBtnListener(ActionListener listener){
        approveDriversBtn.addActionListener(listener);
    }

    public void setClickedApproveOptionBtnStyles(String btn){
        if(Objects.equals(btn, "rest")){
            approveRestBtn.setBackground(new Color(0x1035F4));
            approveDriversBtn.setBackground(new Color(0x5F5F5F));
        }else{
            approveRestBtn.setBackground(new Color(0x5F5F5F));
            approveDriversBtn.setBackground(new Color(0x1035F4));
        }
    }

    public void setRestReviewPanelVisibility(Boolean visibility){
        restReviewPanel.setVisible(visibility);
    }

    public void setDriverReviewPanelVisibility(Boolean visibility){
        driverReviewPanel.setVisible(visibility);
    }


    String[] reviewRestDetails = new String[8];

    public String[] getReviewRestDetails(){
        return reviewRestDetails;
    }
    public void loadApproveRestsList(List<List<Object>> pendingRestsDetails){
        approveListPanel.removeAll();

        for(List<Object> pendingRestDetails : pendingRestsDetails){
            JPanel approveListRestPanel = new JPanel();
            approveListRestPanel.setLayout(null);
            approveListRestPanel.setPreferredSize(new Dimension(334, 78));
            approveListRestPanel.setBackground(new Color(0x363636));
            approveListPanel.add(approveListRestPanel);

            approveListRestPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    restReviewPanel.setVisible(true);
                    approveRestName.setText((String)pendingRestDetails.get(1));
                    approveRestBranchName.setText((String)pendingRestDetails.get(7));
                    approveRestEmail.setText((String)pendingRestDetails.get(3));
                    approveRestCity.setText((String)pendingRestDetails.get(5));
                    approveRestPCode.setText((String)pendingRestDetails.get(6));
                    approveRestAddress.setText((String)pendingRestDetails.get(2));

                    approveRestUsernameText.setText((String)pendingRestDetails.get(3));

                    AdminModel.generateRestDefaultPwd();
                    restPasswordText.setText(AdminModel.getRestDefaultPassword());

                    restApproveBtn.putClientProperty("ID", String.valueOf((int)pendingRestDetails.getFirst()));
                    restDeclineBtn.putClientProperty("ID", String.valueOf((int)pendingRestDetails.getFirst()));

                    reviewRestDetails[0] = (String)pendingRestDetails.get(1);
                    reviewRestDetails[1] = (String)pendingRestDetails.get(2);
                    reviewRestDetails[2] = (String)pendingRestDetails.get(3);
                    reviewRestDetails[3] = (String)pendingRestDetails.get(4);  //mobile
                    reviewRestDetails[4] = (String)pendingRestDetails.get(5);
                    reviewRestDetails[5] = (String)pendingRestDetails.get(6);
                    reviewRestDetails[6] = (String)pendingRestDetails.get(7);
                    reviewRestDetails[7] = AdminModel.getRestDefaultPassword();
                }
            });

            JLabel approveListRestPanelRestNameLbl = new JLabel((String)pendingRestDetails.get(7));
            approveListRestPanelRestNameLbl.setFont(new Font(null, Font.BOLD, 16));
            approveListRestPanelRestNameLbl.setForeground(Color.WHITE);
            approveListRestPanelRestNameLbl.setBounds(15, 7, 300, 18);
            approveListRestPanel.add(approveListRestPanelRestNameLbl);

            Date date = (Date)pendingRestDetails.get(8);
            LocalDate localDateFromDB = LocalDate.parse(date.toString());
            LocalDate currentDate = LocalDate.now();

            JLabel approveListRestPanelDateLbl = new JLabel();

            if(localDateFromDB.equals(currentDate)){
                approveListRestPanelDateLbl.setText("Today");
            }else{
                approveListRestPanelDateLbl.setText(date.toString());
            }
            approveListRestPanelDateLbl.setFont(new Font(null, Font.PLAIN, 16));
            approveListRestPanelDateLbl.setForeground(Color.WHITE);
            approveListRestPanelDateLbl.setBounds(15, 30, 300, 18);
            approveListRestPanelDateLbl.setHorizontalAlignment(SwingConstants.RIGHT);
            approveListRestPanel.add(approveListRestPanelDateLbl);

            JLabel approveListRestPanelCityLbl = new JLabel((String)pendingRestDetails.get(5));
            approveListRestPanelCityLbl.setFont(new Font(null, Font.PLAIN, 16));
            approveListRestPanelCityLbl.setForeground(Color.WHITE);
            approveListRestPanelCityLbl.setBounds(15, 54, 300, 18);
            approveListRestPanel.add(approveListRestPanelCityLbl);
        }

        int totalPreferredHeight = 0;
        for (Component component : approveListPanel.getComponents()) {
            totalPreferredHeight += component.getPreferredSize().height + 5;
        }
        approveListPanel.setPreferredSize(new Dimension(350, totalPreferredHeight));

        approveListPanel.revalidate();
        approveListPanel.repaint();
    }

    public void loadApproveDriversList(List<List<Object>> pendingDrivers){
        approveListPanel.removeAll();

        for(List<Object> driver : pendingDrivers){
            JPanel approveListDriverPanel = new JPanel();
            approveListDriverPanel.setLayout(null);
            approveListDriverPanel.setPreferredSize(new Dimension(334, 78));
            approveListDriverPanel.setBackground(new Color(0x363636));
            approveListPanel.add(approveListDriverPanel);

            approveListDriverPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    driverReviewPanel.setVisible(true);

                    approveDriverName.setText((String) driver.get(6));
                    approveDriverUsername.setText((String)driver.get(1));
                    approveDriverEmail.setText((String)driver.get(2));
                    approveDriverNic.setText((String)driver.get(3));
                    approveDriverVNumber.setText((String)driver.get(4));
                    approveDriverVType.setText((String)driver.get(5));
                    approveDriverMobile.setText((String)driver.get(7));

                    approveDriverUsernameTxt.setText((String)driver.get(1));
                    AdminModel.generateRestDefaultPwd();
                    approveDriverPwdTxt.setText(AdminModel.getRestDefaultPassword());

                    driverApproveBtn.putClientProperty("ID", String.valueOf((int)driver.getFirst()));
                    driverDeclineBtn.putClientProperty("ID", String.valueOf((int)driver.getFirst()));
                }
            });

            JLabel approveListDriverPanelNameLbl = new JLabel((String)driver.get(6));
            approveListDriverPanelNameLbl.setFont(new Font(null, Font.BOLD, 16));
            approveListDriverPanelNameLbl.setForeground(Color.WHITE);
            approveListDriverPanelNameLbl.setBounds(15, 7, 300, 18);
            approveListDriverPanel.add(approveListDriverPanelNameLbl);

            Date date = (Date)driver.get(8);
            LocalDate localDateFromDB = LocalDate.parse(date.toString());
            LocalDate currentDate = LocalDate.now();

            JLabel approveListDriverPanelDateLbl = new JLabel();

            if(localDateFromDB.equals(currentDate)){
                approveListDriverPanelDateLbl.setText("Today");
            }else{
                approveListDriverPanelDateLbl.setText(date.toString());
            }

            approveListDriverPanelDateLbl.setFont(new Font(null, Font.PLAIN, 16));
            approveListDriverPanelDateLbl.setForeground(Color.WHITE);
            approveListDriverPanelDateLbl.setBounds(15, 30, 300, 18);
            approveListDriverPanelDateLbl.setHorizontalAlignment(SwingConstants.RIGHT);
            approveListDriverPanel.add(approveListDriverPanelDateLbl);

            JLabel approveListDriverPanelVehicleLbl = new JLabel((String)driver.get(5));
            approveListDriverPanelVehicleLbl.setFont(new Font(null, Font.PLAIN, 16));
            approveListDriverPanelVehicleLbl.setForeground(Color.WHITE);
            approveListDriverPanelVehicleLbl.setBounds(15, 54, 300, 18);
            approveListDriverPanel.add(approveListDriverPanelVehicleLbl);
        }

        int totalPreferredHeight = 0;
        for (Component component : approveListPanel.getComponents()) {
            totalPreferredHeight += component.getPreferredSize().height + 5;
        }
        approveListPanel.setPreferredSize(new Dimension(350, totalPreferredHeight));

        approveListPanel.revalidate();
        approveListPanel.repaint();
    }

    public void addRestApproveBtnListener(ActionListener listener){
        restApproveBtn.addActionListener(listener);
    }

    public void addRestDeclineBtnListener(ActionListener listener){
        restDeclineBtn.addActionListener(listener);
    }

    public int getRestApproveBtnId(){
        return Integer.parseInt((String) restApproveBtn.getClientProperty("ID"));
    }

    public int getRestDeclineBtnId(){
        return Integer.parseInt((String) restDeclineBtn.getClientProperty("ID"));
    }

    public void addDriverApproveBtnListener(ActionListener listener){
        driverApproveBtn.addActionListener(listener);
    }

    public void addDriverDeclineBtnListener(ActionListener listener){
        driverDeclineBtn.addActionListener(listener);
    }

    public int getDriverApproveBtnId(){
        return Integer.parseInt((String) driverApproveBtn.getClientProperty("ID"));
    }

    public int getDriverDeclineBtnId(){
        return Integer.parseInt((String) driverDeclineBtn.getClientProperty("ID"));
    }

    public String getApproveDriverUsername(){
        return approveDriverUsernameTxt.getText();
    }

    public String getApproveDriverName(){
        return approveDriverName.getText();
    }

    public String getApproveDriverEmail(){
        return approveDriverEmail.getText();
    }

    public void displayDriverApprovedMsge(String name){
        JOptionPane.showMessageDialog(approveSectionPanel, "Delivery drive: " + name + " approved", null,  JOptionPane.PLAIN_MESSAGE);
    }

    public void displayDriverdeclinedMsge(String name){
        JOptionPane.showMessageDialog(approveSectionPanel, "Delivery drive: " + name + " declined", null,  JOptionPane.PLAIN_MESSAGE);
    }




    //////////////home panel///////////////////
    private Boolean isAllRestsFilterOptionSelected = true;
    private Boolean isOpenRestsFilterOptionSelected = false;

    public Boolean getIsAllRestsFilterOptionSelected() {
        return isAllRestsFilterOptionSelected;
    }

    public void setIsAllRestsFilterOptionSelected(Boolean isSelected) {
        isAllRestsFilterOptionSelected = isSelected;
    }

    public Boolean getIsOpenRestsFilterOptionSelected() {
        return isOpenRestsFilterOptionSelected;
    }

    public void setIsOpenRestsFilterOptionSelected(Boolean isSelected) {
        isOpenRestsFilterOptionSelected = isSelected;
    }

    public void addAllRestFilterBtnListener(ActionListener listener){
        allRestFilterBtn.addActionListener(listener);
    }

    public void addOpenRestFilterBtnListener(ActionListener listener){
        openRestFilterBtn.addActionListener(listener);
    }

    public void setSelectedRestFilterOptionStyless(String btn){
        if(Objects.equals(btn, "all")){
            allRestFilterBtn.setBackground(new Color(0x1035F4));
            openRestFilterBtn.setBackground(new Color(0x7994C7));
        }else{
            allRestFilterBtn.setBackground(new Color(0x7994C7));
            openRestFilterBtn.setBackground(new Color(0x1035F4));
        }
    }

    public void loadRestaurantsToHome(List<List<Object>> restaurants){
        homePanelRestsListPanel.removeAll();

        for(List<Object> restaurant : restaurants){
            JPanel homePanelRestsListRestPanel = new JPanel();
            homePanelRestsListRestPanel.setPreferredSize(new Dimension(1124, 68));
            homePanelRestsListRestPanel.setBackground(new Color(0x4E4E4E));
            homePanelRestsListRestPanel.setLayout(null);
            homePanelRestsListPanel.add(homePanelRestsListRestPanel);

            BufferedImage restImage = (BufferedImage)restaurant.get(5);
            JPanel restImagePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (restImage != null) {
                        g.drawImage(restImage, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            restImagePanel.setBounds(5, 4, 80, 60);
            homePanelRestsListRestPanel.add(restImagePanel);

            JLabel homeRestNameLabel = new JLabel((String)restaurant.getFirst());
            homeRestNameLabel.setFont(new Font(null, Font.PLAIN, 15));
            homeRestNameLabel.setForeground(Color.WHITE);
            homeRestNameLabel.setBounds(120, 24, 350, 20);
            homePanelRestsListRestPanel.add(homeRestNameLabel);

            JLabel restAllowTypeLabel = new JLabel();
            if(Objects.equals((String) restaurant.get(1), "All")){
                restAllowTypeLabel.setText("PickUp|Delivery");
            }else{
                restAllowTypeLabel.setText((String) restaurant.get(1));
            }
            restAllowTypeLabel.setFont(new Font(null, Font.PLAIN, 15));
            restAllowTypeLabel.setForeground(Color.WHITE);
            restAllowTypeLabel.setBounds(390, 24, 200, 20);
            homePanelRestsListRestPanel.add(restAllowTypeLabel);

            JLabel restMobileLabel = new JLabel((String)restaurant.get(2));
            restMobileLabel.setFont(new Font(null, Font.PLAIN, 15));
            restMobileLabel.setForeground(Color.WHITE);
            restMobileLabel.setBounds(600, 24, 100, 20);
            homePanelRestsListRestPanel.add(restMobileLabel);

            JLabel restCityLabel = new JLabel((String)restaurant.get(3));
            restCityLabel.setFont(new Font(null, Font.PLAIN, 15));
            restCityLabel.setForeground(Color.WHITE);
            restCityLabel.setBounds(810, 24, 50, 20);
            homePanelRestsListRestPanel.add(restCityLabel);

            JLabel restOpenStatus = new JLabel();
            if(Objects.equals((String) restaurant.get(4), "yes")){
                restOpenStatus.setText("OPEN");
                restOpenStatus.setForeground(new Color(0x01FD66));
            }else{
                restOpenStatus.setText("CLOSE");
                restOpenStatus.setForeground(new Color(0xFF1616));
            }
            restOpenStatus.setFont(new Font(null, Font.BOLD, 15));
            restOpenStatus.setBounds(1050, 24, 70, 20);
            homePanelRestsListRestPanel.add(restOpenStatus);
        }

        int totalPreferredHeight = 0;
        for (Component component : homePanelRestsListPanel.getComponents()) {
            totalPreferredHeight += component.getPreferredSize().height + 5;
        }
        homePanelRestsListPanel.setPreferredSize(new Dimension(1163, totalPreferredHeight));

        homePanelRestsListPanel.revalidate();
        homePanelRestsListPanel.repaint();
    }

    public void addTodayBtnBtnListener(ActionListener listener){
        todayBtn.addActionListener(listener);
    }

    public void addThisWeekBtnListener(ActionListener listener){
        thisWeekBtn.addActionListener(listener);
    }

    public void addThiMonthBtnListener(ActionListener listener){
        thisMonthBtn.addActionListener(listener);
    }

    public void addThisYearBtnListener(ActionListener listener){
        thisYearBtn.addActionListener(listener);
    }

    public void setSelectedTimePeriodBtnStyles(String btn){
        if(Objects.equals(btn, "today")){
            todayBtn.setBackground(new Color(0x1035F4));
            thisWeekBtn.setBackground(new Color(0x7994C7));
            thisMonthBtn.setBackground(new Color(0x7994C7));
            thisYearBtn.setBackground(new Color(0x7994C7));
        }else if(Objects.equals(btn, "week")){
            todayBtn.setBackground(new Color(0x7994C7));
            thisWeekBtn.setBackground(new Color(0x1035F4));
            thisMonthBtn.setBackground(new Color(0x7994C7));
            thisYearBtn.setBackground(new Color(0x7994C7));
        }else if(Objects.equals(btn, "month")){
            todayBtn.setBackground(new Color(0x7994C7));
            thisWeekBtn.setBackground(new Color(0x7994C7));
            thisMonthBtn.setBackground(new Color(0x1035F4));
            thisYearBtn.setBackground(new Color(0x7994C7));
        }else{
            todayBtn.setBackground(new Color(0x7994C7));
            thisWeekBtn.setBackground(new Color(0x7994C7));
            thisMonthBtn.setBackground(new Color(0x7994C7));
            thisYearBtn.setBackground(new Color(0x1035F4));
        }
    }

    public void loadDashBoardOrderStatics(int[] ordersStatics){
        totalEarnings.setText("LKR " + ordersStatics[0]);
        totalOrders.setText("" + ordersStatics[1]);
        totalDeliveredOrders.setText("" + ordersStatics[2]);
    }

    public void loadDashBoardRestaurantStatics(int[] restaurantsStatics){
        totalRestaurants.setText("" + restaurantsStatics[0]);
        totalOpenRestaurants.setText("" + restaurantsStatics[1]);
        totalDisabledRestaurants.setText("" + restaurantsStatics[2]);
    }
}
