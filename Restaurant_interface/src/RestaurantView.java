import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.sun.jdi.connect.TransportTimeoutException;
import com.sun.source.tree.BreakTree;
import com.sun.source.tree.ForLoopTree;

import javax.imageio.ImageIO;
import javax.security.auth.login.FailedLoginException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.JavaBean;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class RestaurantView {

    private JButton homeeBtn, ordersBtn, menuBtn, editBtn, checkOutBtn;
    private JPanel homePanel, ordersPanel, menuPanel, editPanel, checkOutPanel;
    private JButton menuAddBtn;
    private JPanel menuAddContainerPanel;
    private JButton menuAddContainerCloseBtn, imageAddBtn;

    private JPanel addItemImgPanel, addRestBannerPanel;

    private JButton menuItemAddBtn, editedMenuItemSaveBtn;

    private JTextField addItemNameTxt, addItemPriceTxt;

    private JTextArea addItemDecTxt;

    private JPanel editItemsBtnPanel, editCategoriesBtnPanel, addItemsBtnPanel, addCategoriesBtnPanel;
    private JLabel editItemsLbl, editCategoriesLabel, addItemsLbl, addCategoriesLbl;

    private JScrollPane menuBottomScrollPane;

    private JPanel addCategoryContainerPanel, editCategoryContainerPanel;

    private JButton addCategoryBtn;

    private JPanel editSelectCategoryPanel;

    JPanel menuBottomPanel;
    private JTextField categoryTxt;

    private BufferedImage selectedImage, selectedEditImage, selectedRestPoster;

    private JPanel selectCategoryPanel;
    private JTextField selectCategoryTxt;

    private JScrollPane editSelectCategoryScrollPane;

    private JPanel editCategoryPanel;
    private JButton editCategorySaveChangesBtn;
    private JButton todayBtn, thisWeekBtn, thisMonthBtn, thisYearBtn;
    private JLabel totalEarning, totalRecievedOrders, totalDeliveredOrders, totalPickedUpOrders, totalReadyToPickUpOrders, totalCancelledOrders;
    private JButton findOrdersPanelSearchByDateBtn, findOrdersPanelSearchByIdBtn;
    private JComboBox<String> dayComboBox, monthComboBox, yearComboBox;
    private JPanel dashBoardSearchOrderSelectPanel;
    private JPanel dashBoardSearchOrderViewPanel;
    private JLabel orderViewPanelOrderCode, orderViewPanelOrderStatus, orderViewPanelOrderType, orderViewPanelOrderDate, orderViewPanelCustName, orderTotal;
    private JTextArea orderViewPanelCustAddress;
    private JPanel dashBoardSearchOrderItemsPanel;
    private JButton dashboardSearchPanelCloseBtn1, dashboardSearchPanelCloseBtn2;
    private JPanel dashBoardSearchOrderContainerPanel;
    private JTextField findOrdersPanelOCodeTxt;
    private JPanel checkOutOrdersListPanel;
    private JLabel checkOutOrderViewPanelDate, checkOutOrderViewPanelTime, checkOutOrderViewPanelOrderTotal;
    private JButton checkOutOrderViewPanelCheckOutBtn;
    private JPanel checkOutOrderItemsPanel, checkOutOrderViewPanel;
    private JTextField checkOutOrderSearchBarTxt;
    private JPanel checkOutOrderPaymentPanel;
    private JButton paymentPanelCloseBtn;
    private JLabel total;
    private JButton paymentDoneBtn;
    private JTextField paidAmountText, balanceText;
    private JLabel loggedRestNameLabel;
    private JButton restOpenCloseBtn;
    JFrame restaurantFrame;


    public RestaurantView(){
        restaurantFrame = new JFrame();
        restaurantFrame.setSize(1500, 800);
        restaurantFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        restaurantFrame.setResizable(false);
        restaurantFrame.setLocationRelativeTo(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1500, 800));

        JPanel restaurantPanel = new JPanel();
        restaurantPanel.setBounds(0, 0, 1500, 800);
        restaurantPanel.setBackground(Color.red);
        restaurantPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        layeredPane.add(restaurantPanel, JLayeredPane.DEFAULT_LAYER);

        JPanel sideBarPanel = new JPanel();
        sideBarPanel.setPreferredSize(new Dimension(221, 800));
        sideBarPanel.setBackground(new Color(0x262626));
        sideBarPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
        restaurantPanel.add(sideBarPanel);

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

        ordersBtn = new JButton("Orders");
        ordersBtn.setPreferredSize(new Dimension(200, 40));
        ordersBtn.setFocusable(false);
        ordersBtn.setBackground(new Color(0x1035F4));
        ordersBtn.setForeground(Color.white);
        sideBarPanel.add(ordersBtn);

        menuBtn = new JButton("Menu");
        menuBtn.setPreferredSize(new Dimension(200, 40));
        menuBtn.setFocusable(false);
        menuBtn.setBackground(new Color(0x1035F4));
        menuBtn.setForeground(Color.white);
        sideBarPanel.add(menuBtn);

        editBtn = new JButton("Edit");
        editBtn.setPreferredSize(new Dimension(200, 40));
        editBtn.setFocusable(false);
        editBtn.setBackground(new Color(0x1035F4));
        editBtn.setForeground(Color.white);
        sideBarPanel.add(editBtn);

        checkOutBtn = new JButton("checkOut");
        checkOutBtn.setPreferredSize(new Dimension(200, 40));
        checkOutBtn.setFocusable(false);
        checkOutBtn.setBackground(new Color(0x1035F4));
        checkOutBtn.setForeground(Color.white);
        sideBarPanel.add(checkOutBtn);

        JPanel rightContainerPanel = new JPanel();
        rightContainerPanel.setPreferredSize(new Dimension(1279, 800));
        rightContainerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        restaurantPanel.add(rightContainerPanel);


        JPanel topbarPanel = new JPanel();
        topbarPanel.setPreferredSize(new Dimension(1279, 80));
        topbarPanel.setLayout(null);
        topbarPanel.setBackground(new Color(0x3A3939));
        rightContainerPanel.add(topbarPanel);

        loggedRestNameLabel = new JLabel();
        loggedRestNameLabel.setBounds(500, 25, 500, 35);
        loggedRestNameLabel.setForeground(Color.WHITE);
        loggedRestNameLabel.setFont(new Font(null, Font.BOLD, 30));
        topbarPanel.add(loggedRestNameLabel);

        homePanel = new JPanel();
        homePanel.setPreferredSize(new Dimension(1279, 720));
        homePanel.setLayout(null);
        homePanel.setBackground(new Color(0x4E4E4E));
        homePanel.setVisible(true);
        rightContainerPanel.add(homePanel);

        ////////
        JLabel homePanelDashBoardLabel = new JLabel("Dashboard");
        homePanelDashBoardLabel.setFont(new Font(null, Font.BOLD, 30));
        homePanelDashBoardLabel.setForeground(Color.WHITE);
        homePanelDashBoardLabel.setBounds(50, 20, 200, 40);
        homePanel.add(homePanelDashBoardLabel);

        JLabel findOrdersLabel = new JLabel("Find Orders");
        findOrdersLabel.setFont(new Font(null, Font.PLAIN, 20));
        findOrdersLabel.setForeground(Color.WHITE);
        findOrdersLabel.setBounds(180, 90, 200, 25);
        homePanel.add(findOrdersLabel);

        JPanel homePanelFindOrdersPanel = new JPanel();
        homePanelFindOrdersPanel.setBounds(180, 120, 912, 99);
        homePanelFindOrdersPanel.setBackground(new Color(0x393939));
        homePanelFindOrdersPanel.setLayout(null);
        homePanel.add(homePanelFindOrdersPanel);

        JLabel findOrdersPanelOCodeLabel = new JLabel("Order Code");
        findOrdersPanelOCodeLabel.setFont(new Font(null, Font.PLAIN, 16));
        findOrdersPanelOCodeLabel.setForeground(Color.WHITE);
        findOrdersPanelOCodeLabel.setBounds(60, 37, 90, 20);
        homePanelFindOrdersPanel.add(findOrdersPanelOCodeLabel);

        findOrdersPanelOCodeTxt = new JTextField();
        findOrdersPanelOCodeTxt.setBounds(155, 35, 118, 27);
        findOrdersPanelOCodeTxt.setBorder(null);
        findOrdersPanelOCodeTxt.setHorizontalAlignment(SwingConstants.CENTER);
        homePanelFindOrdersPanel.add(findOrdersPanelOCodeTxt);

        findOrdersPanelSearchByIdBtn = new JButton("SEARCH");
        findOrdersPanelSearchByIdBtn.setBounds(278, 34, 90, 30);
        findOrdersPanelSearchByIdBtn.setForeground(Color.WHITE);
        findOrdersPanelSearchByIdBtn.setBackground(new Color(0x1456FF));
        findOrdersPanelSearchByIdBtn.setFocusable(false);
        homePanelFindOrdersPanel.add(findOrdersPanelSearchByIdBtn);

        JLabel findOrdersPanelODateLabel = new JLabel("By Date");
        findOrdersPanelODateLabel.setFont(new Font(null, Font.PLAIN, 16));
        findOrdersPanelODateLabel.setForeground(Color.WHITE);
        findOrdersPanelODateLabel.setBounds(480, 37, 60, 20);
        homePanelFindOrdersPanel.add(findOrdersPanelODateLabel);

        dayComboBox = new JComboBox<>();
        dayComboBox.setBounds(545, 35, 50, 27);
        dayComboBox.addItem("DD");
        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(String.valueOf(i));
        }
        homePanelFindOrdersPanel.add(dayComboBox);

        monthComboBox = new JComboBox<>(new String[]{"MM", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
        monthComboBox.setBounds(595, 35, 100, 27);
        homePanelFindOrdersPanel.add(monthComboBox);

        yearComboBox = new JComboBox<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        yearComboBox.setBounds(695, 35, 80, 27);
        yearComboBox.addItem("YYYY");
        for (int i = currentYear - 10; i <= currentYear; i++) {
            yearComboBox.addItem(String.valueOf(i));
        }
        homePanelFindOrdersPanel.add(yearComboBox);

        findOrdersPanelSearchByDateBtn = new JButton("SEARCH");
        findOrdersPanelSearchByDateBtn.setBounds(780, 34, 90, 30);
        findOrdersPanelSearchByDateBtn.setForeground(Color.WHITE);
        findOrdersPanelSearchByDateBtn.setBackground(new Color(0x1456FF));
        findOrdersPanelSearchByDateBtn.setFocusable(false);
        homePanelFindOrdersPanel.add(findOrdersPanelSearchByDateBtn);

        todayBtn = new JButton("Today");
        todayBtn.setBounds(110, 265, 114, 30);
        todayBtn.setBackground(new Color(0x0601FF));
        todayBtn.setFocusable(false);
        todayBtn.setForeground(Color.WHITE);
        homePanel.add(todayBtn);

        thisWeekBtn = new JButton("This Week");
        thisWeekBtn.setBounds(225, 265, 114, 30);
        thisWeekBtn.setBackground(new Color(0xC0BFFE));
        thisWeekBtn.setFocusable(false);
        thisWeekBtn.setForeground(Color.BLACK);
        homePanel.add(thisWeekBtn);

        thisMonthBtn = new JButton("This Month");
        thisMonthBtn.setBounds(340, 265, 114, 30);
        thisMonthBtn.setBackground(new Color(0xC0BFFE));
        thisMonthBtn.setFocusable(false);
        thisMonthBtn.setForeground(Color.BLACK);
        homePanel.add(thisMonthBtn);

        thisYearBtn = new JButton("This Year");
        thisYearBtn.setBounds(454, 265, 114, 30);
        thisYearBtn.setBackground(new Color(0xC0BFFE));
        thisYearBtn.setFocusable(false);
        thisYearBtn.setForeground(Color.BLACK);
        homePanel.add(thisYearBtn);

        JPanel totalEarningsPanel = new JPanel();
        totalEarningsPanel.setBounds(110, 330 ,306, 140);
        totalEarningsPanel.setBackground(new Color(0x929292));
        totalEarningsPanel.setLayout(null);
        homePanel.add(totalEarningsPanel);

        try {
            File imageFile = new File("images/money.png");
            BufferedImage moneyIcon = ImageIO.read(imageFile);
            JPanel moneyIconPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (moneyIcon != null) {
                        g.drawImage(moneyIcon, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            moneyIconPanel.setBounds(10, 30, 80, 80);
            moneyIconPanel.setBackground(new Color(0x929292));
            totalEarningsPanel.add(moneyIconPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        totalEarning = new JLabel();
        totalEarning.setBounds(100, 42, 200, 20);
        totalEarning.setFont(new Font(null, Font.BOLD, 17));
        totalEarning.setForeground(Color.WHITE);
        totalEarning.setHorizontalAlignment(SwingConstants.CENTER);
        totalEarningsPanel.add(totalEarning);

        JLabel totalEarningsLabel = new JLabel("Total Earnings");
        totalEarningsLabel.setFont(new Font(null, Font.PLAIN, 20));
        totalEarningsLabel.setForeground(Color.WHITE);
        totalEarningsLabel.setBounds(100, 70, 200, 25);
        totalEarningsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalEarningsPanel.add(totalEarningsLabel);

        JPanel totalRecievedOrdersPanel = new JPanel();
        totalRecievedOrdersPanel.setBounds(480, 330 ,306, 140);
        totalRecievedOrdersPanel.setBackground(new Color(0x929292));
        totalRecievedOrdersPanel.setLayout(null);
        homePanel.add(totalRecievedOrdersPanel);

        try {
            File imageFile = new File("images/recievedOrders.png");
            BufferedImage recievedOrdersIcon = ImageIO.read(imageFile);
            JPanel recievedOrdersIconPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (recievedOrdersIcon != null) {
                        g.drawImage(recievedOrdersIcon, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            recievedOrdersIconPanel.setBounds(10, 30, 80, 80);
            recievedOrdersIconPanel.setBackground(new Color(0x929292));
            totalRecievedOrdersPanel.add(recievedOrdersIconPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        totalRecievedOrders = new JLabel();
        totalRecievedOrders.setFont(new Font(null, Font.BOLD, 40));
        totalRecievedOrders.setForeground(Color.WHITE);
        totalRecievedOrders.setBounds(100, 30, 200, 40);
        totalRecievedOrders.setHorizontalAlignment(SwingConstants.CENTER);
        totalRecievedOrdersPanel.add(totalRecievedOrders);

        JLabel orderRecievedLabel = new JLabel("Order Recieved");
        orderRecievedLabel.setFont(new Font(null, Font.PLAIN, 20));
        orderRecievedLabel.setForeground(Color.WHITE);
        orderRecievedLabel.setBounds(100, 80, 200, 20);
        orderRecievedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalRecievedOrdersPanel.add(orderRecievedLabel);

        JPanel totalDeliveredOrdersPanel = new JPanel();
        totalDeliveredOrdersPanel.setBounds(850, 330 ,306, 140);
        totalDeliveredOrdersPanel.setBackground(new Color(0x929292));
        totalDeliveredOrdersPanel.setLayout(null);
        homePanel.add(totalDeliveredOrdersPanel);

        try {
            File imageFile = new File("images/delivered.png");
            BufferedImage deliveredOrdersIcon = ImageIO.read(imageFile);
            JPanel deliveredOrdersIconPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (deliveredOrdersIcon != null) {
                        g.drawImage(deliveredOrdersIcon, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            deliveredOrdersIconPanel.setBounds(10, 30, 80, 80);
            deliveredOrdersIconPanel.setBackground(new Color(0x929292));
            totalDeliveredOrdersPanel.add(deliveredOrdersIconPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        totalDeliveredOrders = new JLabel();
        totalDeliveredOrders.setFont(new Font(null, Font.BOLD, 40));
        totalDeliveredOrders.setForeground(Color.WHITE);
        totalDeliveredOrders.setBounds(100, 30, 200, 40);
        totalDeliveredOrders.setHorizontalAlignment(SwingConstants.CENTER);
        totalDeliveredOrdersPanel.add(totalDeliveredOrders);

        JLabel orderDeliveredLabel = new JLabel("Order Delivered");
        orderDeliveredLabel.setFont(new Font(null, Font.PLAIN, 20));
        orderDeliveredLabel.setForeground(Color.WHITE);
        orderDeliveredLabel.setBounds(100, 80, 200, 20);
        orderDeliveredLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalDeliveredOrdersPanel.add(orderDeliveredLabel);

        JPanel totalPickedUpOrdersPanel = new JPanel();
        totalPickedUpOrdersPanel.setBounds(110, 510 ,306, 140);
        totalPickedUpOrdersPanel.setBackground(new Color(0x929292));
        totalPickedUpOrdersPanel.setLayout(null);
        homePanel.add(totalPickedUpOrdersPanel);

        try {
            File imageFile = new File("images/pickedUp.png");
            BufferedImage pickedUpOrdersIcon = ImageIO.read(imageFile);
            JPanel pickedUpOrdersIconPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (pickedUpOrdersIcon != null) {
                        g.drawImage(pickedUpOrdersIcon, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            pickedUpOrdersIconPanel.setBounds(10, 30, 80, 80);
            pickedUpOrdersIconPanel.setBackground(new Color(0x929292));
            totalPickedUpOrdersPanel.add(pickedUpOrdersIconPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        totalPickedUpOrders = new JLabel();
        totalPickedUpOrders.setFont(new Font(null, Font.BOLD, 40));
        totalPickedUpOrders.setForeground(Color.WHITE);
        totalPickedUpOrders.setBounds(100, 30, 200, 40);
        totalPickedUpOrders.setHorizontalAlignment(SwingConstants.CENTER);
        totalPickedUpOrdersPanel.add(totalPickedUpOrders);

        JLabel orderPickedUpLabel = new JLabel("Order Picked-Up");
        orderPickedUpLabel.setFont(new Font(null, Font.PLAIN, 20));
        orderPickedUpLabel.setForeground(Color.WHITE);
        orderPickedUpLabel.setBounds(100, 80, 200, 25);
        orderPickedUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalPickedUpOrdersPanel.add(orderPickedUpLabel);

        JPanel totalReadyToPickUpOrdersPanel = new JPanel();
        totalReadyToPickUpOrdersPanel.setBounds(480, 510 ,306, 140);
        totalReadyToPickUpOrdersPanel.setBackground(new Color(0x929292));
        totalReadyToPickUpOrdersPanel.setLayout(null);
        homePanel.add(totalReadyToPickUpOrdersPanel);

        try {
            File imageFile = new File("images/readyToPickUp.png");
            BufferedImage readyToPickUpOrdersIcon = ImageIO.read(imageFile);
            JPanel readyToPickedUpOrdersIconPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (readyToPickUpOrdersIcon != null) {
                        g.drawImage(readyToPickUpOrdersIcon, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            readyToPickedUpOrdersIconPanel.setBounds(10, 30, 80, 80);
            readyToPickedUpOrdersIconPanel.setBackground(new Color(0x929292));
            totalReadyToPickUpOrdersPanel.add(readyToPickedUpOrdersIconPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        totalReadyToPickUpOrders = new JLabel();
        totalReadyToPickUpOrders.setFont(new Font(null, Font.BOLD, 40));
        totalReadyToPickUpOrders.setForeground(Color.WHITE);
        totalReadyToPickUpOrders.setBounds(100, 30, 200, 40);
        totalReadyToPickUpOrders.setHorizontalAlignment(SwingConstants.CENTER);
        totalReadyToPickUpOrdersPanel.add(totalReadyToPickUpOrders);

        JLabel readyToPickUpLabel = new JLabel("ready to pick-up");
        readyToPickUpLabel.setFont(new Font(null, Font.PLAIN, 20));
        readyToPickUpLabel.setForeground(Color.WHITE);
        readyToPickUpLabel.setBounds(100, 80, 200, 25);
        readyToPickUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalReadyToPickUpOrdersPanel.add(readyToPickUpLabel);

        JPanel totalCancelledOrdersPanel = new JPanel();
        totalCancelledOrdersPanel.setBounds(850, 510 ,306, 140);
        totalCancelledOrdersPanel.setBackground(new Color(0x929292));
        totalCancelledOrdersPanel.setLayout(null);
        homePanel.add(totalCancelledOrdersPanel);

        try {
            File imageFile = new File("images/cancelledOrders.png");
            BufferedImage cancelledOrdersIcon = ImageIO.read(imageFile);
            JPanel cancelledOrdersIconPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (cancelledOrdersIcon != null) {
                        g.drawImage(cancelledOrdersIcon, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            cancelledOrdersIconPanel.setBounds(10, 30, 80, 80);
            cancelledOrdersIconPanel.setBackground(new Color(0x929292));
            totalCancelledOrdersPanel.add(cancelledOrdersIconPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        totalCancelledOrders = new JLabel();
        totalCancelledOrders.setFont(new Font(null, Font.BOLD, 40));
        totalCancelledOrders.setForeground(Color.WHITE);
        totalCancelledOrders.setBounds(100, 30, 200, 40);
        totalCancelledOrders.setHorizontalAlignment(SwingConstants.CENTER);
        totalCancelledOrdersPanel.add(totalCancelledOrders);

        JLabel orderCancelledLabel = new JLabel("Order Cancelled");
        orderCancelledLabel.setFont(new Font(null, Font.PLAIN, 20));
        orderCancelledLabel.setForeground(Color.WHITE);
        orderCancelledLabel.setBounds(100, 80, 200, 25);
        orderCancelledLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalCancelledOrdersPanel.add(orderCancelledLabel);


























        //////dashBoard searchOrder view Panel////////
        dashBoardSearchOrderContainerPanel = new JPanel();
        dashBoardSearchOrderContainerPanel.setBounds(250, 310, 1210, 440);
        dashBoardSearchOrderContainerPanel.setBackground(new Color(0x191919));
        dashBoardSearchOrderContainerPanel.setLayout(null);
        dashBoardSearchOrderContainerPanel.setVisible(false);
        layeredPane.add(dashBoardSearchOrderContainerPanel, JLayeredPane.PALETTE_LAYER);

        dashBoardSearchOrderSelectPanel = new JPanel();
        dashBoardSearchOrderSelectPanel.setPreferredSize(new Dimension(236, 440));
        dashBoardSearchOrderSelectPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
        dashBoardSearchOrderSelectPanel.setBackground(new Color(0x2F2F2F));

        JScrollPane dashBoardSearchOrderSelectPanelScrollPane = new JScrollPane(dashBoardSearchOrderSelectPanel);
        dashBoardSearchOrderSelectPanelScrollPane.setBounds(0, 0, 236, 440);
        dashBoardSearchOrderSelectPanelScrollPane.setBorder(null);
        dashBoardSearchOrderSelectPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        dashBoardSearchOrderSelectPanelScrollPane.setBackground(new Color(0x2F2F2F));
        dashBoardSearchOrderContainerPanel.add(dashBoardSearchOrderSelectPanelScrollPane);

        dashBoardSearchOrderViewPanel = new JPanel();
        dashBoardSearchOrderViewPanel.setVisible(false);
        dashBoardSearchOrderViewPanel.setBounds(236, 0, 974, 440);
        dashBoardSearchOrderViewPanel.setBackground(new Color(0x191919));
        dashBoardSearchOrderViewPanel.setLayout(null);
        dashBoardSearchOrderContainerPanel.add(dashBoardSearchOrderViewPanel);

        JLabel orderViewPanelOrderLabel = new JLabel("Order");
        orderViewPanelOrderLabel.setBounds(20, 20, 50, 20);
        orderViewPanelOrderLabel.setFont(new Font(null, Font.BOLD, 18));
        orderViewPanelOrderLabel.setForeground(Color.WHITE);
        dashBoardSearchOrderViewPanel.add(orderViewPanelOrderLabel);

        orderViewPanelOrderCode = new JLabel();
        orderViewPanelOrderCode.setBounds(75, 20, 140, 20);
        orderViewPanelOrderCode.setFont(new Font(null, Font.BOLD, 18));
        orderViewPanelOrderCode.setForeground(Color.WHITE);
        dashBoardSearchOrderViewPanel.add(orderViewPanelOrderCode);

        JLabel orderViewPanelOrderStatusLabel = new JLabel("Status:");
        orderViewPanelOrderStatusLabel.setBounds(20, 55, 70, 20);
        orderViewPanelOrderStatusLabel.setFont(new Font(null, Font.PLAIN, 18));
        orderViewPanelOrderStatusLabel.setForeground(new Color(0xD2D2D2));
        dashBoardSearchOrderViewPanel.add(orderViewPanelOrderStatusLabel);

        orderViewPanelOrderStatus = new JLabel();
        orderViewPanelOrderStatus.setBounds(20, 80, 180, 20);
        orderViewPanelOrderStatus.setFont(new Font(null, Font.BOLD, 18));
        orderViewPanelOrderStatus.setForeground(Color.WHITE);
        dashBoardSearchOrderViewPanel.add(orderViewPanelOrderStatus);

        JLabel orderViewPanelOrderTypeLabel = new JLabel("Type:");
        orderViewPanelOrderTypeLabel.setBounds(20, 120, 70, 20);
        orderViewPanelOrderTypeLabel.setFont(new Font(null, Font.PLAIN, 18));
        orderViewPanelOrderTypeLabel.setForeground(new Color(0xD2D2D2));
        dashBoardSearchOrderViewPanel.add(orderViewPanelOrderTypeLabel);

        orderViewPanelOrderType = new JLabel();
        orderViewPanelOrderType.setBounds(20, 145, 100, 20);
        orderViewPanelOrderType.setFont(new Font(null, Font.BOLD, 18));
        orderViewPanelOrderType.setForeground(Color.WHITE);
        dashBoardSearchOrderViewPanel.add(orderViewPanelOrderType);

        JLabel orderViewPanelOrderDateLabel = new JLabel("Date:");
        orderViewPanelOrderDateLabel.setBounds(20, 185, 70, 20);
        orderViewPanelOrderDateLabel.setFont(new Font(null, Font.PLAIN, 18));
        orderViewPanelOrderDateLabel.setForeground(new Color(0xD2D2D2));
        dashBoardSearchOrderViewPanel.add(orderViewPanelOrderDateLabel);

        orderViewPanelOrderDate = new JLabel();
        orderViewPanelOrderDate.setBounds(20, 215, 180, 20);
        orderViewPanelOrderDate.setFont(new Font(null, Font.BOLD, 18));
        orderViewPanelOrderDate.setForeground(Color.WHITE);
        dashBoardSearchOrderViewPanel.add(orderViewPanelOrderDate);

        JLabel orderViewPanelCustNameLabel = new JLabel("Customer Name:");
        orderViewPanelCustNameLabel.setBounds(20, 255, 180, 20);
        orderViewPanelCustNameLabel.setFont(new Font(null, Font.PLAIN, 18));
        orderViewPanelCustNameLabel.setForeground(new Color(0xD2D2D2));
        dashBoardSearchOrderViewPanel.add(orderViewPanelCustNameLabel);

        orderViewPanelCustName = new JLabel();
        orderViewPanelCustName.setBounds(20, 280, 180, 20);
        orderViewPanelCustName.setFont(new Font(null, Font.BOLD, 18));
        orderViewPanelCustName.setForeground(Color.WHITE);
        dashBoardSearchOrderViewPanel.add(orderViewPanelCustName);

        JLabel orderViewPanelCustAddressLabel = new JLabel("Customer Address:");
        orderViewPanelCustAddressLabel.setBounds(20, 320, 180, 20);
        orderViewPanelCustAddressLabel.setFont(new Font(null, Font.PLAIN, 18));
        orderViewPanelCustAddressLabel.setForeground(new Color(0xD2D2D2));
        dashBoardSearchOrderViewPanel.add(orderViewPanelCustAddressLabel);

        orderViewPanelCustAddress = new JTextArea();
        orderViewPanelCustAddress.setBounds(20, 345, 180, 80);
        orderViewPanelCustAddress.setFont(new Font(null, Font.PLAIN, 18));
        orderViewPanelCustAddress.setLineWrap(true);
        orderViewPanelCustAddress.setWrapStyleWord(true);
        orderViewPanelCustAddress.setEditable(false);
        orderViewPanelCustAddress.setFocusable(false);
        orderViewPanelCustAddress.setBackground(null);
        orderViewPanelCustAddress.setForeground(Color.WHITE);
        dashBoardSearchOrderViewPanel.add(orderViewPanelCustAddress);

        orderTotal = new JLabel();
        orderTotal.setFont(new Font(null, Font.BOLD, 20));
        orderTotal.setForeground(Color.WHITE);
        orderTotal.setBounds(228, 390, 732, 25);
        orderTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        dashBoardSearchOrderViewPanel.add(orderTotal);

        dashboardSearchPanelCloseBtn1 = new JButton("x");
        dashboardSearchPanelCloseBtn1.setFont(new Font(null, Font.PLAIN, 14));
        dashboardSearchPanelCloseBtn1.setBounds(940, 5, 25, 25);
        dashboardSearchPanelCloseBtn1.setFocusable(false);
        dashboardSearchPanelCloseBtn1.setBackground(Color.BLACK);
        dashboardSearchPanelCloseBtn1.setForeground(Color.WHITE);
        dashboardSearchPanelCloseBtn1.setBorder(null);
        dashBoardSearchOrderViewPanel.add(dashboardSearchPanelCloseBtn1);

        dashboardSearchPanelCloseBtn2 = new JButton("x");
        dashboardSearchPanelCloseBtn2.setFont(new Font(null, Font.PLAIN, 14));
        dashboardSearchPanelCloseBtn2.setBounds(1180, 5, 25, 25);
        dashboardSearchPanelCloseBtn2.setFocusable(false);
        dashboardSearchPanelCloseBtn2.setBackground(Color.BLACK);
        dashboardSearchPanelCloseBtn2.setForeground(Color.WHITE);
        dashboardSearchPanelCloseBtn2.setBorder(null);
        dashBoardSearchOrderContainerPanel.add(dashboardSearchPanelCloseBtn2);

        //..
        dashBoardSearchOrderItemsPanel = new JPanel();
        dashBoardSearchOrderItemsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
        dashBoardSearchOrderItemsPanel.setPreferredSize(new Dimension(732, 350));

        JScrollPane dashBoardSearchOrderItemsPanelScrollPane = new JScrollPane(dashBoardSearchOrderItemsPanel);
        dashBoardSearchOrderItemsPanelScrollPane.setBounds(228, 30, 732, 350);
        dashBoardSearchOrderItemsPanelScrollPane.setBorder(null);
        dashBoardSearchOrderItemsPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        dashBoardSearchOrderViewPanel.add(dashBoardSearchOrderItemsPanelScrollPane);


        //..

        ///////////////

        ////////





























        ordersPanel = new JPanel();
        ordersPanel.setPreferredSize(new Dimension(1279, 720));
        ordersPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0,0));
        ordersPanel.setBackground(new Color(0x4E4E4E));
        ordersPanel.setVisible(false);
        rightContainerPanel.add(ordersPanel);

        ////////////////////////////////////////////////////////////////
        JPanel ordersSelectPanel = new JPanel();
        ordersSelectPanel.setPreferredSize(new Dimension(373, 685));
        ordersSelectPanel.setBackground(new Color(0x3D3D3D));
        ordersSelectPanel.setLayout(null);
        ordersPanel.add(ordersSelectPanel);

        newOrdersBtn = new JButton("New");
        newOrdersBtn.setBounds(40, 20, 94, 37);
        newOrdersBtn.setBackground(new Color(0x52DB12));
        newOrdersBtn.setFocusable(false);
        ordersSelectPanel.add(newOrdersBtn);

        acceptedOrdersBtn = new JButton("Accepted");
        acceptedOrdersBtn.setBounds(134, 20, 94, 37);
        acceptedOrdersBtn.setBackground(new Color(0x3D3D3D));
        acceptedOrdersBtn.setForeground(Color.WHITE);
        acceptedOrdersBtn.setFocusable(false);
        ordersSelectPanel.add(acceptedOrdersBtn);

        preparingOrdersBtn = new JButton("Preparing");
        preparingOrdersBtn.setBounds(229, 20, 94, 37);
        preparingOrdersBtn.setBackground(new Color(0x3D3D3D));
        preparingOrdersBtn.setForeground(Color.WHITE);
        preparingOrdersBtn.setFocusable(false);
        ordersSelectPanel.add(preparingOrdersBtn);

        deliveryOrdersBtn = new JButton("Delivery");
        deliveryOrdersBtn.setBounds(10, 80, 85, 34);
        deliveryOrdersBtn.setBackground(Color.BLACK);
        deliveryOrdersBtn.setForeground(Color.WHITE);
        deliveryOrdersBtn.setFocusable(false);
        ordersSelectPanel.add(deliveryOrdersBtn);

        pickupOrdersBtn = new JButton("Pick up");
        pickupOrdersBtn.setBounds(95, 80, 85, 34);
        pickupOrdersBtn.setBackground(new Color(0xDFDFDF));
        pickupOrdersBtn.setFocusable(false);
        pickupOrdersBtn.setForeground(Color.BLACK);
        ordersSelectPanel.add(pickupOrdersBtn);

        //....
        ordersViewPanel = new JPanel();
        ordersViewPanel.setPreferredSize(new Dimension(350, 540));
        ordersViewPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));

        JScrollPane ordersViewPanelScrollPane = new JScrollPane(ordersViewPanel);
        ordersViewPanelScrollPane.setBounds(10, 125, 350, 540);
        ordersViewPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ordersSelectPanel.add(ordersViewPanelScrollPane);


        //....

        orderViewPanel = new JPanel();
        orderViewPanel.setPreferredSize(new Dimension(906, 685));
        orderViewPanel.setBackground(new Color(0x4E4E4E));
        orderViewPanel.setLayout(null);
        orderViewPanel.setVisible(false);
        ordersPanel.add(orderViewPanel);

        JLabel orderInfoLbl = new JLabel("Order info");
        orderInfoLbl.setFont(new Font(null, Font.BOLD, 30));
        orderInfoLbl.setForeground(Color.WHITE);
        orderInfoLbl.setBounds(40, 20, 200, 30);
        orderViewPanel.add(orderInfoLbl);

        JLabel addressLbl = new JLabel("Address:");
        addressLbl.setFont(new Font(null, Font.BOLD, 18));
        addressLbl.setBounds(40, 65, 200, 30);
        addressLbl.setForeground(Color.WHITE);
        orderViewPanel.add(addressLbl);

        orderAddressLbl = new JLabel("No 15 henewatta thihagoda");
        orderAddressLbl.setForeground(Color.WHITE);
        orderAddressLbl.setBounds(40, 95, 800, 20);
        orderAddressLbl.setFont(new Font(null, Font.PLAIN, 16));
        orderViewPanel.add(orderAddressLbl);

        ///......
        orderViewOrderItemsPanel = new JPanel();
        orderViewOrderItemsPanel.setPreferredSize(new Dimension(860, 425));
        orderViewOrderItemsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        orderViewOrderItemsPanel.setBackground(new Color(0x353535));

        JScrollPane orderViewOrderItemsPanelScrollPane = new JScrollPane(orderViewOrderItemsPanel);
        orderViewOrderItemsPanelScrollPane.setBounds(15, 125, 860, 430);
        orderViewOrderItemsPanelScrollPane.setBorder(null);
        orderViewOrderItemsPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        orderViewPanel.add(orderViewOrderItemsPanelScrollPane);

        ////.....

        orderViewOrderTotalPriceLbl = new JLabel("LKR 120000");
        orderViewOrderTotalPriceLbl.setForeground(Color.WHITE);
        orderViewOrderTotalPriceLbl.setBounds(650, 565, 200, 40);
        orderViewOrderTotalPriceLbl.setFont(new Font(null, Font.BOLD, 30));
        orderViewPanel.add(orderViewOrderTotalPriceLbl);

        orderDesclineBtn = new JButton("Decline");
        orderDesclineBtn.setFont(new Font(null, Font.BOLD, 18));
        orderDesclineBtn.setForeground(Color.WHITE);
        orderDesclineBtn.setFocusable(false);
        orderDesclineBtn.setBounds(200, 615, 147, 52);
        orderDesclineBtn.setBackground(new Color(0x8F3A0B));
        orderViewPanel.add(orderDesclineBtn);

        orderAcceptBtn = new JButton("Accept order");
        orderAcceptBtn.setFont(new Font(null, Font.BOLD, 18));
        orderAcceptBtn.setForeground(Color.WHITE);
        orderAcceptBtn.setFocusable(false);
        orderAcceptBtn.setBounds(360, 615, 211, 52);
        orderAcceptBtn.setBackground(new Color(0x39C622));
        orderViewPanel.add(orderAcceptBtn);

        orderStartToPrepareBtn = new JButton("Start to prepare");
        orderStartToPrepareBtn.setFont(new Font(null, Font.BOLD, 18));
        orderStartToPrepareBtn.setFocusable(false);
        orderStartToPrepareBtn.setBounds(360, 615, 211, 52);
        orderStartToPrepareBtn.setBackground(new Color(0x39C622));
        orderStartToPrepareBtn.setForeground(Color.WHITE);
        orderStartToPrepareBtn.setVisible(false);
        orderViewPanel.add(orderStartToPrepareBtn);

        orderPreparationCompleteBtn = new JButton("Order complete");
        orderPreparationCompleteBtn.setFont(new Font(null, Font.BOLD, 18));
        orderPreparationCompleteBtn.setFocusable(false);
        orderPreparationCompleteBtn.setBounds(360, 615, 211, 52);
        orderPreparationCompleteBtn.setBackground(new Color(0x39C622));
        orderPreparationCompleteBtn.setForeground(Color.WHITE);
        orderPreparationCompleteBtn.setVisible(false);
        orderViewPanel.add(orderPreparationCompleteBtn);

        ////////////////////////////////////////////////////////////////

        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(1279, 720));
        menuPanel.setLayout(new FlowLayout());
        menuPanel.setBackground(new Color(0x4E4E4E));
        menuPanel.setVisible(false);
        rightContainerPanel.add(menuPanel);

        editPanel = new JPanel();
        editPanel.setPreferredSize(new Dimension(1279, 720));
        editPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        editPanel.setBackground(new Color(0x4E4E4E));
        editPanel.setVisible(false);
        rightContainerPanel.add(editPanel);

        checkOutPanel = new JPanel();
        checkOutPanel.setPreferredSize(new Dimension(1279, 720));
        checkOutPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        checkOutPanel.setBackground(new Color(0x4E4E4E));
        checkOutPanel.setVisible(false);
        rightContainerPanel.add(checkOutPanel);








        ///////////
        JPanel checkOutOrdersSideBarPanel = new JPanel();
        checkOutOrdersSideBarPanel.setPreferredSize(new Dimension(330, 720));
        checkOutOrdersSideBarPanel.setBackground(new Color(0x323232));
        checkOutOrdersSideBarPanel.setLayout(null);
        checkOutPanel.add(checkOutOrdersSideBarPanel);

        JLabel checkOutPanelSideBarTopLbl = new JLabel("Ready to pick up orders");
        checkOutPanelSideBarTopLbl.setFont(new Font(null, Font.PLAIN, 18));
        checkOutPanelSideBarTopLbl.setForeground(Color.WHITE);
        checkOutPanelSideBarTopLbl.setBounds(40, 20, 250, 25);
        checkOutPanelSideBarTopLbl.setHorizontalAlignment(SwingConstants.CENTER);
        checkOutOrdersSideBarPanel.add(checkOutPanelSideBarTopLbl);


        try {
            File imageFile = new File("images/search.png");
            BufferedImage searchIcon = ImageIO.read(imageFile);
            JPanel checkOutOrderSearchIconPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (searchIcon != null) {
                        g.drawImage(searchIcon, 10, 0, 30, getHeight(), this);
                    }
                }
            };
            checkOutOrderSearchIconPanel.setBounds(70, 65, 50, 30);
            checkOutOrderSearchIconPanel.setBackground(new Color(0x000000));
            checkOutOrdersSideBarPanel.add(checkOutOrderSearchIconPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        checkOutOrderSearchBarTxt = new JTextField();
        checkOutOrderSearchBarTxt.setBounds(120, 65, 140, 30);
        checkOutOrderSearchBarTxt.setBorder(null);
        checkOutOrdersSideBarPanel.add(checkOutOrderSearchBarTxt);

        ////.........
        checkOutOrdersListPanel = new JPanel();
        checkOutOrdersListPanel.setPreferredSize(new Dimension(330, 580));
        checkOutOrdersListPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
        checkOutOrdersListPanel.setBackground(new Color(0x3B3B3B));

        JScrollPane checkOutOrdersListPanelScrollPane = new JScrollPane(checkOutOrdersListPanel);
        checkOutOrdersListPanelScrollPane.setBounds(0, 105, 330, 580);
        checkOutOrdersListPanelScrollPane.setBorder(null);
        checkOutOrdersListPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        checkOutOrdersListPanelScrollPane.setBackground(new Color(0x2F2F2F));
        checkOutOrdersSideBarPanel.add(checkOutOrdersListPanelScrollPane);
        ////..........

        checkOutOrderViewPanel = new JPanel();
        checkOutOrderViewPanel.setPreferredSize(new Dimension(949, 720));
        checkOutOrderViewPanel.setLayout(null);
        checkOutOrderViewPanel.setVisible(false);
        checkOutOrderViewPanel.setBackground(new Color(0x4E4E4E));
        checkOutPanel.add(checkOutOrderViewPanel);

        JLabel checkOutOrderViewPanelDateLbl = new JLabel("Date:");
        checkOutOrderViewPanelDateLbl.setFont(new Font(null, Font.BOLD, 18));
        checkOutOrderViewPanelDateLbl.setForeground(Color.WHITE);
        checkOutOrderViewPanelDateLbl.setBounds(35, 30, 50, 20);
        checkOutOrderViewPanel.add(checkOutOrderViewPanelDateLbl);

        checkOutOrderViewPanelDate = new JLabel("2024-05-08");
        checkOutOrderViewPanelDate.setBounds(90, 30, 150, 20);
        checkOutOrderViewPanelDate.setForeground(Color.WHITE);
        checkOutOrderViewPanelDate.setFont(new Font(null, Font.PLAIN, 18));
        checkOutOrderViewPanel.add(checkOutOrderViewPanelDate);

        JLabel checkOutOrderViewPanelTimeLbl = new JLabel("Time:");
        checkOutOrderViewPanelTimeLbl.setFont(new Font(null, Font.BOLD, 18));
        checkOutOrderViewPanelTimeLbl.setForeground(Color.WHITE);
        checkOutOrderViewPanelTimeLbl.setBounds(270, 30, 50, 20);
        checkOutOrderViewPanel.add(checkOutOrderViewPanelTimeLbl);

        checkOutOrderViewPanelTime = new JLabel("10.58");
        checkOutOrderViewPanelTime.setBounds(325, 30, 150, 20);
        checkOutOrderViewPanelTime.setForeground(Color.WHITE);
        checkOutOrderViewPanelTime.setFont(new Font(null, Font.PLAIN, 18));
        checkOutOrderViewPanel.add(checkOutOrderViewPanelTime);

        //....
        checkOutOrderItemsPanel = new JPanel();
        checkOutOrderItemsPanel.setPreferredSize(new Dimension(870, 498));
        checkOutOrderItemsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

        JScrollPane checkOutOrderItemsPanelScrollPane = new JScrollPane(checkOutOrderItemsPanel);
        checkOutOrderItemsPanelScrollPane.setBounds(35, 60, 870, 498);
        checkOutOrderItemsPanelScrollPane.setBorder(null);
        checkOutOrderItemsPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        checkOutOrderViewPanel.add(checkOutOrderItemsPanelScrollPane);
        //....

        checkOutOrderViewPanelOrderTotal = new JLabel("LKR 120000");
        checkOutOrderViewPanelOrderTotal.setBounds(35, 570, 870, 30);
        checkOutOrderViewPanelOrderTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        checkOutOrderViewPanelOrderTotal.setFont(new Font(null, Font.BOLD, 25));
        checkOutOrderViewPanelOrderTotal.setForeground(Color.WHITE);
        checkOutOrderViewPanel.add(checkOutOrderViewPanelOrderTotal);

        checkOutOrderViewPanelCheckOutBtn = new JButton("Check Out");
        checkOutOrderViewPanelCheckOutBtn.setFont(new Font(null, Font.BOLD, 20));
        checkOutOrderViewPanelCheckOutBtn.setForeground(Color.WHITE);
        checkOutOrderViewPanelCheckOutBtn.setBounds(625, 610, 280, 48);
        checkOutOrderViewPanelCheckOutBtn.setBackground(new Color(0x02B160));
        checkOutOrderViewPanelCheckOutBtn.setFocusable(false);
        checkOutOrderViewPanel.add(checkOutOrderViewPanelCheckOutBtn);

        ////....
        checkOutOrderPaymentPanel = new JPanel();
        checkOutOrderPaymentPanel.setBounds(520, 100, 475, 480);
        checkOutOrderPaymentPanel.setBackground(new Color(0x222222));
        checkOutOrderPaymentPanel.setLayout(null);
        checkOutOrderPaymentPanel.setVisible(false);
        layeredPane.add(checkOutOrderPaymentPanel, JLayeredPane.PALETTE_LAYER);

        JLabel paidAmountLabel = new JLabel("Paid Amount");
        paidAmountLabel.setFont(new Font(null, Font.PLAIN, 20));
        paidAmountLabel.setForeground(Color.WHITE);
        paidAmountLabel.setBounds(80, 50, 200, 30);
        checkOutOrderPaymentPanel.add(paidAmountLabel);

        paidAmountText = new JTextField();
        paidAmountText.setBounds(80, 85, 305, 37);
        paidAmountText.setBorder(null);
        checkOutOrderPaymentPanel.add(paidAmountText);

        JLabel balanceLabel = new JLabel("Balance");
        balanceLabel.setFont(new Font(null, Font.PLAIN, 20));
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setBounds(80, 150, 200, 30);
        checkOutOrderPaymentPanel.add(balanceLabel);

        balanceText = new JTextField();
        balanceText.setBounds(80, 185, 305, 37);
        balanceText.setBorder(null);
        checkOutOrderPaymentPanel.add(balanceText);

        JLabel totalLabel = new JLabel("TOTAL: ");
        totalLabel.setFont(new Font(null, Font.BOLD, 20));
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setBounds(100, 270, 150, 25);
        checkOutOrderPaymentPanel.add(totalLabel);

        total = new JLabel();
        total.setFont(new Font(null, Font.BOLD, 20));
        total.setForeground(Color.WHITE);
        total.setBounds(260, 270, 300, 25);
        checkOutOrderPaymentPanel.add(total);

        paymentDoneBtn = new JButton("Payment Done");
        paymentDoneBtn.setBounds(80, 350, 305, 37);
        paymentDoneBtn.setFocusable(false);
        paymentDoneBtn.setFont(new Font(null, Font.PLAIN, 20));
        paymentDoneBtn.setForeground(Color.WHITE);
        paymentDoneBtn.setBackground(new Color(0x02B160));
        checkOutOrderPaymentPanel.add(paymentDoneBtn);

        paymentPanelCloseBtn = new JButton("X");
        paymentPanelCloseBtn.setBorder(null);
        paymentPanelCloseBtn.setBounds(445, 5, 25, 25);
        paymentPanelCloseBtn.setBackground(Color.BLACK);
        paymentPanelCloseBtn.setForeground(Color.WHITE);
        paymentPanelCloseBtn.setFocusable(false);
        checkOutOrderPaymentPanel.add(paymentPanelCloseBtn);
        ////....
        ///////////









        JPanel editPanelTop = new JPanel();
        editPanelTop.setPreferredSize(new Dimension(1200, 70));
        editPanelTop.setBackground(new Color(0x4E4E4E));
        editPanelTop.setLayout(null);
        editPanel.add(editPanelTop);

        restEnableBtn = new JButton();
//        restEnableBtn.setText("ENABLE");
        restEnableBtn.setBounds(500, 15, 150, 40);
//        restEnableBtn.setBackground(new Color(0x35F116));
        restEnableBtn.setFocusable(false);
        restEnableBtn.setForeground(Color.WHITE);
        editPanelTop.add(restEnableBtn);

        restOpenCloseBtn = new JButton("Open");
        restOpenCloseBtn.setBounds(750, 15, 150, 40);
        restOpenCloseBtn.setFocusable(false);
        restOpenCloseBtn.setForeground(Color.WHITE);
        editPanelTop.add(restOpenCloseBtn);


        editPanelMiddle = new JPanel();
        editPanelMiddle.setPreferredSize(new Dimension(1200, 380));
        editPanelMiddle.setBackground(new Color(0x3E3E3E));
        editPanelMiddle.setLayout(null);
        editPanel.add(editPanelMiddle);

        imageAddBtn = new JButton("+");
        imageAddBtn.setBounds(30, 35, 44, 138);
        editPanelMiddle.add(imageAddBtn);

        JLabel allowTypeLbl = new JLabel("Allow Type");
        allowTypeLbl.setBounds(250, 220, 200, 30);
        allowTypeLbl.setForeground(Color.WHITE);
        allowTypeLbl.setFont(new Font(null, Font.PLAIN, 20));
        editPanelMiddle.add(allowTypeLbl);

        String[] types = {"Select an allow type", "All", "Pick Up", "Delivery"};
        allowTypesComboBox = new JComboBox<>(types);
        allowTypesComboBox.setBounds(250, 255, 200, 40);
        editPanelMiddle.add(allowTypesComboBox);


        JLabel mobileNumLbl = new JLabel("Mobile Number");
        mobileNumLbl.setBounds(600, 220, 200, 30);
        mobileNumLbl.setForeground(Color.WHITE);
        mobileNumLbl.setFont(new Font(null, Font.PLAIN, 20));
        editPanelMiddle.add(mobileNumLbl);

        restMobileNumTxt = new JTextField();
        restMobileNumTxt.setBounds(600, 255, 200, 40);
        editPanelMiddle.add(restMobileNumTxt);

        restEditedDataSaveBtn = new JButton("Save Changes");
        restEditedDataSaveBtn.setBounds(450, 300, 200, 40);
        restEditedDataSaveBtn.setBackground(new Color(0x3AC050));
        restEditedDataSaveBtn.setForeground(Color.WHITE);
        restEditedDataSaveBtn.setFont(new Font(null, Font.BOLD, 20));
        editPanelMiddle.add(restEditedDataSaveBtn);

        JPanel editPanelBottom = new JPanel();
        editPanelBottom.setPreferredSize(new Dimension(1200, 126));
        editPanelBottom.setLayout(null);
        editPanelBottom.setBackground(new Color(0x3E3E3E));
        editPanel.add(editPanelBottom);

        JButton editBranchNameBtn = new JButton("Change branch name");
        editBranchNameBtn.setBounds(240, 40, 200, 40);
        editBranchNameBtn.setBackground(new Color(0x3E3E3E));
        editBranchNameBtn.setForeground(Color.WHITE);
        editBranchNameBtn.setFont(new Font(null, Font.BOLD,16));
        Border editPanelBottomBtnBorder = BorderFactory.createLineBorder(new Color(0xA04909), 4);
        editBranchNameBtn.setBorder(editPanelBottomBtnBorder);
        editPanelBottom.add(editBranchNameBtn);

        JButton editBranchEmailBtn = new JButton("Change Email");
        editBranchEmailBtn.setBounds(480, 40, 200, 40);
        editBranchEmailBtn.setBackground(new Color(0x3E3E3E));
        editBranchEmailBtn.setForeground(Color.WHITE);
        editBranchEmailBtn.setFont(new Font(null, Font.BOLD,16));
        editBranchEmailBtn.setBorder(editPanelBottomBtnBorder);
        editPanelBottom.add(editBranchEmailBtn);

        JButton editBranchPwdBtn = new JButton("Change Password");
        editBranchPwdBtn.setBounds(720, 40, 200, 40);
        editBranchPwdBtn.setBackground(new Color(0x3E3E3E));
        editBranchPwdBtn.setForeground(Color.WHITE);
        editBranchPwdBtn.setFont(new Font(null, Font.BOLD,16));
        editBranchPwdBtn.setBorder(editPanelBottomBtnBorder);
        editPanelBottom.add(editBranchPwdBtn);

        addRestBannerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (selectedRestPoster != null) {
                    g.drawImage(selectedRestPoster, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        addRestBannerPanel.setBackground(Color.WHITE);

        imageAddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectBannerImage();
            }
        });

        addRestBannerPanel.setBounds(73, 10, 700, 200);
        addRestBannerPanel.setBackground(Color.black);
        editPanelMiddle.add(addRestBannerPanel);



        JPanel menuTopPanel = new JPanel();
        menuTopPanel.setPreferredSize(new Dimension(1200, 50));
        menuTopPanel.setLayout(null);
        menuTopPanel.setBackground(new Color(0x4E4E4E));
        menuPanel.add(menuTopPanel);

        //////
        JPanel menuTopNavigationPanel = new JPanel();
        menuTopNavigationPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        menuTopNavigationPanel.setBounds(0, 0, 600, 50);
        menuTopNavigationPanel.setBackground(new Color(0x4E4E4E));
        menuTopPanel.add(menuTopNavigationPanel);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        editItemsBtnPanel = new JPanel();
        editItemsBtnPanel.setPreferredSize(new Dimension(150, 44));
        editItemsBtnPanel.setBackground(new Color(0x262626));
        editItemsBtnPanel.setBorder(border);
        editItemsBtnPanel.setLayout(new BorderLayout());
        menuTopNavigationPanel.add(editItemsBtnPanel);

        editCategoriesBtnPanel = new JPanel();
        editCategoriesBtnPanel.setPreferredSize(new Dimension(150, 44));
        editCategoriesBtnPanel.setBackground(Color.WHITE);
        editCategoriesBtnPanel.setBorder(border);
        editCategoriesBtnPanel.setLayout(new BorderLayout());
        menuTopNavigationPanel.add(editCategoriesBtnPanel);

        addItemsBtnPanel = new JPanel();
        addItemsBtnPanel.setPreferredSize(new Dimension(150, 44));
        addItemsBtnPanel.setBackground(Color.WHITE);
        addItemsBtnPanel.setBorder(border);
        addItemsBtnPanel.setLayout(new BorderLayout());
        menuTopNavigationPanel.add(addItemsBtnPanel);

        addCategoriesBtnPanel = new JPanel();
        addCategoriesBtnPanel.setPreferredSize(new Dimension(150, 44));
        addCategoriesBtnPanel.setBackground(Color.WHITE);
        addCategoriesBtnPanel.setBorder(border);
        addCategoriesBtnPanel.setLayout(new BorderLayout());
        menuTopNavigationPanel.add(addCategoriesBtnPanel);

        editItemsLbl = new JLabel("Edit Items");
        editItemsLbl.setFont(new Font(null, Font.PLAIN, 17));
        editItemsLbl.setForeground(Color.WHITE);
        editItemsLbl.setBorder(new EmptyBorder(22, 35, 22, 0));
        editItemsBtnPanel.add(editItemsLbl, BorderLayout.CENTER);

        editCategoriesLabel = new JLabel("Edit Categories");
        editCategoriesLabel.setFont(new Font(null, Font.PLAIN, 17));
        editCategoriesLabel.setForeground(Color.BLACK);
        editCategoriesLabel.setBorder(new EmptyBorder(22, 15, 22, 0));
        editCategoriesBtnPanel.add(editCategoriesLabel, BorderLayout.CENTER);

        addItemsLbl = new JLabel("Add Items");
        addItemsLbl.setFont(new Font(null, Font.PLAIN, 17));
        addItemsLbl.setForeground(Color.BLACK);
        addItemsLbl.setBorder(new EmptyBorder(22, 35, 22, 0));
        addItemsBtnPanel.add(addItemsLbl, BorderLayout.CENTER);

        addCategoriesLbl = new JLabel("Add Categories");
        addCategoriesLbl.setFont(new Font(null, Font.PLAIN, 17));
        addCategoriesLbl.setForeground(Color.BLACK);
        addCategoriesLbl.setBorder(new EmptyBorder(22, 15, 22, 0));
        addCategoriesBtnPanel.add(addCategoriesLbl, BorderLayout.CENTER);
        /////


        editedMenuItemSaveBtn = new JButton("Save Changes");
        editedMenuItemSaveBtn.setFont(new Font(null, Font.BOLD, 20));
        editedMenuItemSaveBtn.setForeground(Color.WHITE);
        editedMenuItemSaveBtn.setBackground(Color.GREEN);
        editedMenuItemSaveBtn.setBounds(900, 0, 200, 40);
        editedMenuItemSaveBtn.setFocusable(false);
        menuTopPanel.add(editedMenuItemSaveBtn);






////////////
        menuBottomPanel = new JPanel();
        menuBottomPanel.setLayout(new FlowLayout());
        menuBottomPanel.setPreferredSize(new Dimension(1200, 600));

        menuBottomScrollPane = new JScrollPane(menuBottomPanel);
        menuBottomScrollPane.setPreferredSize(new Dimension(1200, 600));
        menuBottomScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        menuPanel.add(menuBottomScrollPane);


        menuAddContainerPanel = new JPanel();
        menuAddContainerPanel.setPreferredSize(new Dimension(1200, 600));
        menuAddContainerPanel.setBackground(Color.red);
        menuAddContainerPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        menuAddContainerPanel.setVisible(false);
        menuPanel.add(menuAddContainerPanel);


        JPanel menuAddPanel = new JPanel();
        menuAddPanel.setPreferredSize(new Dimension(1200, 600));
        menuAddPanel.setLayout(null);
        menuAddPanel.setBackground(Color.darkGray);
        menuAddContainerPanel.add(menuAddPanel);

        JLabel addItemNameLabel = new JLabel("Item name");
        addItemNameLabel.setBounds(40, 100, 100, 40);
        addItemNameLabel.setForeground(Color.WHITE);
        addItemNameLabel.setFont(new Font(null, Font.BOLD, 20));
        menuAddPanel.add(addItemNameLabel);

        addItemNameTxt = new JTextField();
        addItemNameTxt.setBounds(40, 140, 350, 40);
        menuAddPanel.add(addItemNameTxt);

        JLabel addItemPriceLabel = new JLabel("Price");
        addItemPriceLabel.setBounds(500, 100, 100, 40);
        addItemPriceLabel.setForeground(Color.WHITE);
        addItemPriceLabel.setFont(new Font(null, Font.BOLD, 20));
        menuAddPanel.add(addItemPriceLabel);

        addItemPriceTxt = new JTextField();
        addItemPriceTxt.setBounds(500, 140, 300, 40);
        menuAddPanel.add(addItemPriceTxt);

        JLabel addItemDesLabel = new JLabel("Description");
        addItemDesLabel.setBounds(40, 220, 150, 40);
        addItemDesLabel.setForeground(Color.WHITE);
        addItemDesLabel.setFont(new Font(null, Font.BOLD, 20));
        menuAddPanel.add(addItemDesLabel);

        addItemDecTxt = new JTextArea();
        addItemDecTxt.setBounds(40, 260, 350, 160);
        menuAddPanel.add(addItemDecTxt);

        JLabel addItemImgLabel = new JLabel("Image");
        addItemImgLabel.setBounds(500, 220, 150, 40);
        addItemImgLabel.setForeground(Color.WHITE);
        addItemImgLabel.setFont(new Font(null, Font.BOLD, 20));
        menuAddPanel.add(addItemImgLabel);

        addItemImgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (selectedImage != null) {
                    g.drawImage(selectedImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        addItemImgPanel.setBackground(Color.WHITE);
        addItemImgPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectImage();
            }
        });

        addItemImgPanel.setBounds(500, 260, 300, 160);
        menuAddPanel.add(addItemImgPanel);

        menuItemAddBtn = new JButton("Add Item");
        menuItemAddBtn.setBounds(350, 500, 150, 40);
        menuItemAddBtn.setFont(new Font(null, Font.BOLD, 20));
        menuAddPanel.add(menuItemAddBtn);

        JLabel selectCategoryLbl = new JLabel("Select Category");
        selectCategoryLbl.setFont(new Font(null, Font.BOLD, 20));
        selectCategoryLbl.setForeground(Color.WHITE);
        selectCategoryLbl.setBounds(830, 60, 150, 40);
        menuAddPanel.add(selectCategoryLbl);

        ////
        selectCategoryPanel = new JPanel();
        selectCategoryPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 5));
        selectCategoryPanel.setBackground(Color.BLACK);
        selectCategoryPanel.setPreferredSize(new Dimension(350, 400));

        JScrollPane selectCategoryScrollPane = new JScrollPane(selectCategoryPanel);
        selectCategoryScrollPane.setBounds(830, 100, 350, 400);
        selectCategoryScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        menuAddPanel.add(selectCategoryScrollPane);

        ///////

        selectCategoryTxt = new JTextField();
        selectCategoryTxt.setEditable(false);
        selectCategoryTxt.setBounds(830, 500, 350, 40);
        menuAddPanel.add(selectCategoryTxt);

        addCategoryContainerPanel = new JPanel();
        addCategoryContainerPanel.setPreferredSize(new Dimension(1200, 600));
        addCategoryContainerPanel.setBackground(Color.darkGray);
        addCategoryContainerPanel.setLayout(null);
        addCategoryContainerPanel.setVisible(false);
        menuPanel.add(addCategoryContainerPanel);

        JPanel addCategoryPanel = new JPanel();
        addCategoryPanel.setBounds(300, 100, 600, 400);
        addCategoryPanel.setBackground(Color.BLACK);
        addCategoryPanel.setLayout(null);
        addCategoryContainerPanel.add(addCategoryPanel);

        categoryTxt = new JTextField();
        categoryTxt.setBounds(100, 100, 400, 50);
        categoryTxt.setFont(new Font(null, Font.PLAIN, 18));
        addCategoryPanel.add(categoryTxt);

        addCategoryBtn = new JButton("ADD");
        addCategoryBtn.setBounds(180, 200, 250, 40);
        addCategoryBtn.setFont(new Font(null, Font.BOLD, 20));
        addCategoryBtn.setBackground(Color.green);
        addCategoryBtn.setForeground(Color.WHITE);
        addCategoryBtn.setFocusable(false);
        addCategoryPanel.add(addCategoryBtn);

        //////
        editCategoryContainerPanel = new JPanel();
        editCategoryContainerPanel.setPreferredSize(new Dimension(1200, 600));
        editCategoryContainerPanel.setBackground(Color.darkGray);
        editCategoryContainerPanel.setLayout(null);
        editCategoryContainerPanel.setVisible(false);
        menuPanel.add(editCategoryContainerPanel);

        editCategoryPanel = new JPanel();
        editCategoryPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 5));
        editCategoryPanel.setBackground(Color.DARK_GRAY);
        editCategoryPanel.setPreferredSize(new Dimension(700, 490));

        JScrollPane editCategoryPanelScrollPane = new JScrollPane(editCategoryPanel);
        editCategoryPanelScrollPane.setBounds(20, 40, 700, 500);
        editCategoryPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        editCategoryContainerPanel.add(editCategoryPanelScrollPane);

        editCategorySaveChangesBtn = new JButton("Save changes");
        editCategorySaveChangesBtn.setBounds(250, 550, 200, 40);
        editCategorySaveChangesBtn.setBackground(Color.GREEN);
        editCategorySaveChangesBtn.setForeground(Color.WHITE);
        editCategorySaveChangesBtn.setFont(new Font(null, Font.BOLD, 18));
        editCategorySaveChangesBtn.setFocusable(false);
        editCategoryContainerPanel.add(editCategorySaveChangesBtn);


        /////
        editSelectCategoryPanel = new JPanel();
        editSelectCategoryPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 5));
        editSelectCategoryPanel.setBackground(Color.BLACK);
        editSelectCategoryPanel.setPreferredSize(new Dimension(350, 400));

        editSelectCategoryScrollPane = new JScrollPane(editSelectCategoryPanel);
        editSelectCategoryScrollPane.setBounds(650, 200, 350, 400);
        editSelectCategoryScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        editSelectCategoryScrollPane.setVisible(false);
        layeredPane.add(editSelectCategoryScrollPane, JLayeredPane.PALETTE_LAYER);
        ////////

        restaurantFrame.add(layeredPane);
        restaurantFrame.setVisible(true);
    }

    private void selectBannerImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith(".png")
                        || file.getName().toLowerCase().endsWith(".jpg")
                        || file.getName().toLowerCase().endsWith(".jpeg")
                        || file.getName().toLowerCase().endsWith(".gif");
            }

            public String getDescription() {
                return "Image files (*.png, *.jpg, *.jpeg, *.gif)";
            }
        });

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                selectedRestPoster = ImageIO.read(selectedFile);
                addRestBannerPanel.repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading image: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void addHomeBtnListener(ActionListener listener){
        homeeBtn.addActionListener(listener);
    }

    public void addOrderBtnListener(ActionListener listener){
        ordersBtn.addActionListener(listener);
    }

    public void addMenuBtnListener(ActionListener listener){
        menuBtn.addActionListener(listener);
    }

    public void addEditBtnListener(ActionListener listener){
        editBtn.addActionListener(listener);
    }

    public void addCheckOutBtnSideBarListener(ActionListener listener){
        checkOutBtn.addActionListener(listener);
    }

    public void openHomePanel(boolean open){
        homePanel.setVisible(open);
        ordersPanel.setVisible(!open);
        menuPanel.setVisible(!open);
        editPanel.setVisible(!open);
        checkOutPanel.setVisible(!open);
    }

    public void openOrdersPanel(boolean open){
        ordersPanel.setVisible(open);
        homePanel.setVisible(!open);
        menuPanel.setVisible(!open);
        editPanel.setVisible(!open);
        checkOutPanel.setVisible(!open);
    }

    public void openMenuPanel(boolean open){
        menuPanel.setVisible(open);
        ordersPanel.setVisible(!open);
        homePanel.setVisible(!open);
        editPanel.setVisible(!open);
        checkOutPanel.setVisible(!open);
    }

    public void openEditPanel(boolean open){
        editPanel.setVisible(open);
        menuPanel.setVisible(!open);
        ordersPanel.setVisible(!open);
        homePanel.setVisible(!open);
        checkOutPanel.setVisible(!open);
    }

    public void openCheckOutPanel(boolean open){
        checkOutPanel.setVisible(open);
        editPanel.setVisible(!open);
        menuPanel.setVisible(!open);
        ordersPanel.setVisible(!open);
        homePanel.setVisible(!open);
    }

    public void openMenuAddContainerPanel(boolean open){
        menuAddContainerPanel.setVisible(open);
    }

    //////////
    private void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith(".png")
                        || file.getName().toLowerCase().endsWith(".jpg")
                        || file.getName().toLowerCase().endsWith(".jpeg")
                        || file.getName().toLowerCase().endsWith(".gif");
            }

            public String getDescription() {
                return "Image files (*.png, *.jpg, *.jpeg, *.gif)";
            }
        });

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                selectedImage = ImageIO.read(selectedFile);
                addItemImgPanel.repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading image: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void selectChangeImage(String panelID, JPanel editMenuItemPanel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith(".png")
                        || file.getName().toLowerCase().endsWith(".jpg")
                        || file.getName().toLowerCase().endsWith(".jpeg")
                        || file.getName().toLowerCase().endsWith(".gif");
            }

            public String getDescription() {
                return "Image files (*.png, *.jpg, *.jpeg, *.gif)";
            }
        });

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage selectedImage = ImageIO.read(selectedFile);
                panelImages.put(panelID, selectedImage);
                for (Component comp : editMenuItemPanel.getComponents()) {
                    if (comp instanceof JPanel) {
                        JPanel ImgPanel = (JPanel) comp;
                        if (Objects.equals(ImgPanel.getClientProperty("ID"), panelID)) {
                            ImgPanel.repaint();
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading image: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    ///////

    public void addMenuItemAddBtnListener(ActionListener listener){
        menuItemAddBtn.addActionListener(listener);
    }

    public String[] getAddMenuItemDetails(){
        String[] details = {addItemNameTxt.getText(), addItemPriceTxt.getText(), addItemDecTxt.getText(), selectCategoryTxt.getText()};
        return details;
    }

    public BufferedImage getSelectedMenuItemImage(){
        return selectedImage;
    }

    public void displayMenuItemAddValidateDialog(String msg){
        JOptionPane.showMessageDialog(menuAddContainerPanel, msg, null,  JOptionPane.PLAIN_MESSAGE);
    }

    public void clearMenuAddPanel(){
        addItemNameTxt.setText("");
        addItemPriceTxt.setText("");
        addItemDecTxt.setText("");
        selectCategoryTxt.setText("");

        selectedImage = null;
        addItemImgPanel.repaint();
    }

    private Map<String, BufferedImage> panelImages = new HashMap<>();
    public void addMenuItemsToEditPanel(List<Object> menuItem){
        int id = (int) menuItem.get(0);
        String name = (String) menuItem.get(1);
        int price = (int) menuItem.get(2);
        String description = (String) menuItem.get(3);
        byte[] imageBytes = (byte[]) menuItem.get(4);
        String category = (String) menuItem.get(5);

        try{
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            BufferedImage ItemImage = ImageIO.read(bis);
            panelImages.put(Integer.toString(id), ItemImage);
            bis.close();
        }catch (IOException e) {
            e.printStackTrace();
        }


        JPanel editMenuItemPanel = new JPanel();
        editMenuItemPanel.setPreferredSize(new Dimension(1185, 108));
        editMenuItemPanel.setBackground(new Color(0x3C3C3C));
        editMenuItemPanel.setLayout(null);
        editMenuItemPanel.putClientProperty("ID", Integer.toString(id));
        menuBottomPanel.add(editMenuItemPanel);

        JTextField editMenuItemIdTxt = new JTextField(String.valueOf(id));
        editMenuItemIdTxt.setBounds(10, 30, 63, 44);
        editMenuItemPanel.add(editMenuItemIdTxt);

        JTextField editMenuItemNameTxt = new JTextField(name);
        editMenuItemNameTxt.setBounds(90, 30, 230, 44);
        editMenuItemPanel.add(editMenuItemNameTxt);

        JTextField editMenuItemPriceTxt = new JTextField(String.valueOf(price));
        editMenuItemPriceTxt.setBounds(330, 30, 90, 44);
        editMenuItemPanel.add(editMenuItemPriceTxt);

        JTextArea editMenuItemDesTxt = new JTextArea(description);
        editMenuItemDesTxt.setLineWrap(true);
        JScrollPane editMenuItemDesScrollPane = new JScrollPane(editMenuItemDesTxt);
        editMenuItemDesScrollPane.setBounds(435, 10, 300, 82);
        editMenuItemPanel.add(editMenuItemDesScrollPane);

        JTextField editMenuItemCategoryTxt = new JTextField();
        if(category == null){
            editMenuItemCategoryTxt.setText("not selected");
        }else{
            editMenuItemCategoryTxt.setText(category);
        }
        editMenuItemCategoryTxt.setBounds(745, 10, 160, 44);
        editMenuItemCategoryTxt.setEditable(false);
        editMenuItemPanel.add(editMenuItemCategoryTxt);

        JButton editMenuItemCategorySelectBtn = new JButton("change");
        editMenuItemCategorySelectBtn.setFont(new Font(null, Font.BOLD, 18));
        editMenuItemCategorySelectBtn.setBounds(745, 54, 160, 38);
        editMenuItemCategorySelectBtn.setBackground(Color.DARK_GRAY);
        editMenuItemCategorySelectBtn.setForeground(Color.WHITE);
        editMenuItemCategorySelectBtn.setFocusable(false);
        editMenuItemPanel.add(editMenuItemCategorySelectBtn);

        editMenuItemCategorySelectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editSelectCategoryPanel.removeAll();
                editSelectCategoryPanel.revalidate();
                editSelectCategoryPanel.repaint();

                editSelectCategoryScrollPane.setVisible(true);
                List<String> categories = RestaurantModel.getCategories();

                for(String categoryy : categories){

                    JPanel categoryPanel = new JPanel();
                    categoryPanel.setPreferredSize(new Dimension(350, 40));
                    categoryPanel.setBackground(Color.RED);
                    categoryPanel.setLayout(new BorderLayout());
                    editSelectCategoryPanel.add(categoryPanel);

                    categoryPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            editMenuItemCategoryTxt.setText(categoryy);
                            editSelectCategoryScrollPane.setVisible(false);
                        }
                    });

                    JLabel categoryLbl = new JLabel(categoryy);
                    categoryLbl.setFont(new Font(null, Font.PLAIN, 18));
                    categoryPanel.setBackground(Color.DARK_GRAY);
                    categoryLbl.setForeground(Color.WHITE);
                    categoryPanel.add(categoryLbl, BorderLayout.CENTER);

                    int totalPreferredHeight = 0;
                    for (Component component : editSelectCategoryPanel.getComponents()) {
                        totalPreferredHeight += component.getPreferredSize().height;
                    }

                    editSelectCategoryPanel.setPreferredSize(new Dimension(350, totalPreferredHeight + 100));
                    /////
                }
            }
        });

        JPanel editMenuItemImgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                String panelID = (String) getClientProperty("ID");
                BufferedImage panelImage = panelImages.get(panelID);
                if (panelImage != null) {
                    g.drawImage(panelImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        editMenuItemImgPanel.setBackground(Color.WHITE);
        editMenuItemImgPanel.putClientProperty("ID", Integer.toString(id));
        editMenuItemImgPanel.setBounds(920, 10, 130, 82);
        editMenuItemPanel.add(editMenuItemImgPanel);
        editMenuItemImgPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String imgPanelID = (String) editMenuItemImgPanel.getClientProperty("ID");
                selectChangeImage(imgPanelID, editMenuItemPanel);
            }
        });

        JButton menuItemDeleteBtn = new JButton("DELETE");
        menuItemDeleteBtn.setFont(new Font(null, Font.BOLD, 16));
        menuItemDeleteBtn.setBounds(1060, 28, 110, 44);
        menuItemDeleteBtn.setBackground(new Color(0xFF0000));
        menuItemDeleteBtn.setForeground(Color.WHITE);
        menuItemDeleteBtn.putClientProperty("ID", Integer.toString(id));
        editMenuItemPanel.add(menuItemDeleteBtn);

        //////////
        menuItemDeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = (String) menuItemDeleteBtn.getClientProperty("ID");
                System.out.println(id);
                if(RestaurantModel.deleteMenuItem(Integer.parseInt(id)) > 0){
                    JOptionPane.showMessageDialog(menuAddContainerPanel, "Menu item deleted", null,  JOptionPane.PLAIN_MESSAGE);
                    if(!RestaurantModel.checkIfTheRestHasFoodItems()){
                        setIsLive("no");
                        RestaurantModel.updateIsLive("no");
                        changeRestEnableBtnStyle("no");
                        setRestEnableDisableMsge("Restaurant Disabled");
                    }

                    clearMenuBottomPanel();
                    List<List<Object>> menuItems = RestaurantModel.getMenu();
                    for (List<Object> menuItem : menuItems) {
                        addMenuItemsToEditPanel(menuItem);
                    }
                }
            }
        });
        //////

        int totalPreferredHeight = 0;
        for (Component component : menuBottomPanel.getComponents()) {
            totalPreferredHeight += component.getPreferredSize().height;
        }

        menuBottomPanel.setPreferredSize(new Dimension(1200, totalPreferredHeight + 50));
    }

    public void clearMenuBottomPanel(){
        menuBottomPanel.removeAll();
        menuBottomPanel.revalidate();
        menuBottomPanel.repaint();
    }

    public JPanel getMenuBottomPanel(){
        return menuBottomPanel;
    }

    public Map<String, BufferedImage> getItemImages(){
        return panelImages;
    }

    public void addSaveChangesBtnListener(ActionListener listener){
        editedMenuItemSaveBtn.addActionListener(listener);
    }

    JTextField restEnableStatusTxt;
    JButton restEnableBtn;
    JComboBox<String> allowTypesComboBox;
    JTextField restMobileNumTxt;
    JButton restEditedDataSaveBtn;
    JPanel editPanelMiddle;

    private String isLive;

    public void setEditPanelData(List<Object> restaurantDetails){
        selectedRestPoster = (BufferedImage) restaurantDetails.get(5);
        String mobile = (String) restaurantDetails.get(2);
        String allowType = (String) restaurantDetails.get(6);
        isLive = (String) restaurantDetails.get(7);

        if(Objects.equals(isLive, "no")){
            restEnableBtn.setText("ENABLE");
            restEnableBtn.setBackground(Color.GREEN);
        }else {
            restEnableBtn.setText("DISABLE");
            restEnableBtn.setBackground(Color.RED);
        }

        addRestBannerPanel.repaint();
        restMobileNumTxt.setText(mobile);

        if(allowType == null){
            allowTypesComboBox.setSelectedIndex(0);
        }
        else {
            allowTypesComboBox.setSelectedItem(allowType);
        }

    }

    public List<Object> getEditPanelMiddleData(){
        List<Object> editPanelMiddleData = new ArrayList<>();
        editPanelMiddleData.add(selectedRestPoster);
        editPanelMiddleData.add(restMobileNumTxt.getText());
        editPanelMiddleData.add(allowTypesComboBox.getSelectedIndex());

        return editPanelMiddleData;
    }

    public void displayEditPanelDataValidateMsge(String msg){
        JOptionPane.showMessageDialog(editPanel, msg, null,  JOptionPane.WARNING_MESSAGE);
    }

    public void displayUpdateDataMsge(){
        JOptionPane.showMessageDialog(editPanel, "Changes Saved", null,  JOptionPane.PLAIN_MESSAGE);
    }

    public void addRestEditedDataSaveBtnListener(ActionListener listener){
        restEditedDataSaveBtn.addActionListener(listener);
    }

    public void addRestEnableBtnListener(ActionListener listener){
        restEnableBtn.addActionListener(listener);
    }

    public String getIsLive(){
        return isLive;
    }

    public void setIsLive(String islive){
        isLive = islive;
    }

    public void changeRestEnableBtnStyle(String isLive){
        if(Objects.equals(isLive, "no")){
            restEnableBtn.setText("ENABLE");
            restEnableBtn.setBackground(Color.GREEN);
        }else {
            restEnableBtn.setText("DISABLE");
            restEnableBtn.setBackground(Color.RED);
        }
    }

    public void setRestEnableDisableMsge(String msge){
        JOptionPane.showMessageDialog(editPanel, msge, null,  JOptionPane.PLAIN_MESSAGE);
    }

    public void repainRestBannerPanel(){
        selectedRestPoster = null;
        addRestBannerPanel.repaint();
    }

    public void addEditItemsBtnPanelListener(MouseListener listener){
        editItemsBtnPanel.addMouseListener(listener);
    }

    public void addEditCategoriesBtnPanelListener(MouseListener listener){
        editCategoriesBtnPanel.addMouseListener(listener);
    }

    public void addItemsBtnPanelListener(MouseListener listener){
        addItemsBtnPanel.addMouseListener(listener);
    }

    public void addCategoriesBtnPanelListener(MouseListener listener){
        addCategoriesBtnPanel.addMouseListener(listener);
    }

    public void applyNavigationBtnsStyles(String type){
        if(Objects.equals(type, "editItem")){
            editItemsLbl.setForeground(Color.WHITE);
            editItemsBtnPanel.setBackground(new Color(0x262626));

            editCategoriesLabel.setForeground(Color.BLACK);
            addItemsLbl.setForeground(Color.BLACK);
            addCategoriesLbl.setForeground(Color.BLACK);
            editCategoriesBtnPanel.setBackground(Color.WHITE);
            addItemsBtnPanel.setBackground(Color.WHITE);
            addCategoriesBtnPanel.setBackground(Color.WHITE);
        }else if(Objects.equals(type, "editCategory")){
            editItemsLbl.setForeground(Color.BLACK);
            editItemsBtnPanel.setBackground(Color.WHITE);
            addItemsLbl.setForeground(Color.BLACK);
            addCategoriesLbl.setForeground(Color.BLACK);

            editCategoriesLabel.setForeground(Color.WHITE);
            editCategoriesBtnPanel.setBackground(new Color(0x262626));

            addItemsBtnPanel.setBackground(Color.WHITE);
            addCategoriesBtnPanel.setBackground(Color.WHITE);
        }else if(Objects.equals(type, "addItem")){
            editItemsLbl.setForeground(Color.BLACK);
            editItemsBtnPanel.setBackground(Color.WHITE);
            editCategoriesLabel.setForeground(Color.BLACK);
            addCategoriesLbl.setForeground(Color.BLACK);
            editCategoriesBtnPanel.setBackground(Color.WHITE);

            addItemsLbl.setForeground(Color.WHITE);
            addItemsBtnPanel.setBackground(new Color(0x262626));

            addCategoriesBtnPanel.setBackground(Color.WHITE);
        }else{
            editItemsLbl.setForeground(Color.BLACK);
            editItemsBtnPanel.setBackground(Color.WHITE);
            editCategoriesLabel.setForeground(Color.BLACK);
            editCategoriesBtnPanel.setBackground(Color.WHITE);
            addItemsLbl.setForeground(Color.BLACK);
            addItemsBtnPanel.setBackground(Color.WHITE);

            addCategoriesLbl.setForeground(Color.WHITE);
            addCategoriesBtnPanel.setBackground(new Color(0x262626));
        }
    }

    public void openMenuEditPanel(boolean open){
        menuBottomScrollPane.setVisible(open);
    }

    public void openAddCategoryContainerPanel(boolean open){
        addCategoryContainerPanel.setVisible(open);
    }

    public void openEditCategoryContainerPanel(boolean open){
        editCategoryContainerPanel.setVisible(open);
    }

    public void setVisibilityOfMenuSaveChangesBtn(boolean open){
        editedMenuItemSaveBtn.setVisible(open);
    }

    public void addCategoryBtnListener(ActionListener listener){
        addCategoryBtn.addActionListener(listener);
    }

    public String getCategory(){
        return categoryTxt.getText();
    }

    public void displayInputValidateErorMsge(String msge){
        JOptionPane.showMessageDialog(addCategoryContainerPanel, msge, null,  JOptionPane.WARNING_MESSAGE);
    }

    public void displayEditCategoryPanelCantOpenMsge(String msge){
        JOptionPane.showMessageDialog(editCategoryContainerPanel, msge, null,  JOptionPane.WARNING_MESSAGE);
    }

    public void displayCategoryAddedMsge(String msge){
        JOptionPane.showMessageDialog(addCategoryContainerPanel, msge, null,  JOptionPane.PLAIN_MESSAGE);
    }

    public void clearAddCategoryField(){
        categoryTxt.setText("");
    }

    public void loadCategoriesAddItemPanel(String category){
        JPanel categoryPanel = new JPanel();
        categoryPanel.setPreferredSize(new Dimension(350, 40));
        categoryPanel.setBackground(Color.RED);
        categoryPanel.setLayout(new BorderLayout());
        selectCategoryPanel.add(categoryPanel);

        categoryPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectCategoryTxt.setText(category);
            }
        });

        JLabel categoryLbl = new JLabel(category);
        categoryLbl.setFont(new Font(null, Font.PLAIN, 18));
        categoryPanel.setBackground(Color.DARK_GRAY);
        categoryLbl.setForeground(Color.WHITE);
        categoryPanel.add(categoryLbl, BorderLayout.CENTER);

        int totalPreferredHeight = 0;
        for (Component component : selectCategoryPanel.getComponents()) {
            totalPreferredHeight += component.getPreferredSize().height;
        }

        selectCategoryPanel.setPreferredSize(new Dimension(350, totalPreferredHeight + 100));
    }

    public void clearSelectCategoryPanel(){
        selectCategoryPanel.removeAll();
        selectCategoryPanel.revalidate();
        selectCategoryPanel.repaint();
    }

    public void displayMenuItemsSaveChangesErrorMsge(String msge){
        JOptionPane.showMessageDialog(menuPanel, msge, null,  JOptionPane.WARNING_MESSAGE);
    }

    public void displayMenuItemsSaveChangesMsge(String msge){
        JOptionPane.showMessageDialog(menuPanel, msge, null,  JOptionPane.PLAIN_MESSAGE);
    }

    public void closeEditSelectCategoryScrollPane(){
        editSelectCategoryScrollPane.setVisible(false);
    }

    public void loadCategoriesToCategoryEditPanel(String category, int count){
        JPanel categoryPanel = new JPanel();
        categoryPanel.setPreferredSize(new Dimension(700, 50));
        categoryPanel.setBackground(Color.LIGHT_GRAY);
        categoryPanel.setLayout(null);
        editCategoryPanel.add(categoryPanel);

        JTextField editCategoryNameTxt = new JTextField();
        editCategoryNameTxt.putClientProperty("ID", category);
        editCategoryNameTxt.setText(category);
        editCategoryNameTxt.setBounds(10, 7, 200, 35);
        categoryPanel.add(editCategoryNameTxt);

        JLabel categoryItemCountLbl = new JLabel();
        if(count == 0){
            categoryItemCountLbl.setText("No Items");
        }else{
            categoryItemCountLbl.setText(count + " Items");
        }
        categoryItemCountLbl.setFont(new Font(null,Font.PLAIN, 18));
        categoryItemCountLbl.setBounds(350, 4, 150, 40);
        categoryPanel.add(categoryItemCountLbl);

        JButton categoryDeleteBtn = new JButton("Delete");
        categoryDeleteBtn.setBounds(580, 10, 100, 30);
        categoryDeleteBtn.setBackground(Color.RED);
        categoryDeleteBtn.setForeground(Color.WHITE);
        categoryDeleteBtn.setFocusable(false);
        categoryPanel.add(categoryDeleteBtn);

        categoryDeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(RestaurantModel.deleteCategory(category)){
                    JOptionPane.showMessageDialog(menuPanel, "category '" + category + "' deleted", null,  JOptionPane.PLAIN_MESSAGE);
                    clearCategoriesInCategoryEditPanel();

                    List<String> categories = RestaurantModel.getCategories();
                    if(!categories.isEmpty()){
                        for(String category : categories){
                            int count = RestaurantModel.getCategoryItemsCount(category);
                            loadCategoriesToCategoryEditPanel(category, count);
                        }
                    }
                }
            }
        });

        int totalPreferredHeight = 0;
        for (Component component : editCategoryPanel.getComponents()) {
            totalPreferredHeight += component.getPreferredSize().height;
        }

        editCategoryPanel.setPreferredSize(new Dimension(700, totalPreferredHeight + 100));
    }

    public void clearCategoriesInCategoryEditPanel(){
        editCategoryPanel.removeAll();
        editCategoryPanel.revalidate();
        editCategoryPanel.repaint();
    }

    public void addEditCategorySaveChangesBtnListener(ActionListener listener){
        editCategorySaveChangesBtn.addActionListener(listener);
    }

    public JPanel getEditCategoryPanel(){
        return editCategoryPanel;
    }






    ///////////orders//////////////
    JButton newOrdersBtn, acceptedOrdersBtn, preparingOrdersBtn;
    JButton deliveryOrdersBtn, pickupOrdersBtn;
    JPanel ordersViewPanel;
    JPanel orderViewPanel;
    JPanel orderViewOrderItemsPanel;
    JButton orderDesclineBtn, orderAcceptBtn, orderStartToPrepareBtn, orderPreparationCompleteBtn;
    JLabel orderViewOrderTotalPriceLbl, orderAddressLbl;

    public void addNewOrdersBtnListener(ActionListener listener){
        newOrdersBtn.addActionListener(listener);
    }

    public void addAcceptedOrdersBtnListener(ActionListener listener){
        acceptedOrdersBtn.addActionListener(listener);
    }

    public void addPreparingOrdersBtnListener(ActionListener listener){
        preparingOrdersBtn.addActionListener(listener);
    }

    public void setClickedOrderBtnStyles(String btn){
        if(btn.equals("new")){
            newOrdersBtn.setBackground(new Color(0x52DB12));
            newOrdersBtn.setForeground(Color.BLACK);
            acceptedOrdersBtn.setBackground(new Color(0x3D3D3D));
            acceptedOrdersBtn.setForeground(Color.WHITE);
            preparingOrdersBtn.setBackground(new Color(0x3D3D3D));
            preparingOrdersBtn.setForeground(Color.WHITE);
        }else if(btn.equals("accepted")){
            newOrdersBtn.setBackground(new Color(0x3D3D3D));
            newOrdersBtn.setForeground(Color.WHITE);
            acceptedOrdersBtn.setBackground(new Color(0x52DB12));
            acceptedOrdersBtn.setForeground(Color.BLACK);
            preparingOrdersBtn.setBackground(new Color(0x3D3D3D));
            preparingOrdersBtn.setForeground(Color.WHITE);
        }else{
            newOrdersBtn.setBackground(new Color(0x3D3D3D));
            newOrdersBtn.setForeground(Color.WHITE);
            acceptedOrdersBtn.setBackground(new Color(0x3D3D3D));
            acceptedOrdersBtn.setForeground(Color.WHITE);
            preparingOrdersBtn.setBackground(new Color(0x52DB12));
            preparingOrdersBtn.setForeground(Color.BLACK);
        }
    }

    public void addDeliveryOrdersBtnListener(ActionListener listener){
        deliveryOrdersBtn.addActionListener(listener);
    }

    public void addPickupOrdersBtnListener(ActionListener listener){
        pickupOrdersBtn.addActionListener(listener);
    }

    public void setClickedOrderTypeOptionBtnStyles(String btn){
        if(Objects.equals(btn, "delivery")){
            deliveryOrdersBtn.setBackground(Color.BLACK);
            deliveryOrdersBtn.setForeground(Color.WHITE);
            pickupOrdersBtn.setBackground(new Color(0xDFDFDF));
            pickupOrdersBtn.setForeground(Color.BLACK);
        }else{
            deliveryOrdersBtn.setBackground(new Color(0xDFDFDF));
            deliveryOrdersBtn.setForeground(Color.BLACK);
            pickupOrdersBtn.setBackground(Color.BLACK);
            pickupOrdersBtn.setForeground(Color.WHITE);
        }
    }

    public void loadNewOrdersPanel(List<List<Object>> newOrders){

        ordersViewPanel.removeAll();
        for(List<Object> newOrder : newOrders){
            JPanel ordersViewOrderPanel = new JPanel();
            ordersViewOrderPanel.setPreferredSize(new Dimension(329, 81));
            ordersViewOrderPanel.setBackground(new Color(0x848484));
            ordersViewOrderPanel.setLayout(null);
            ordersViewOrderPanel.putClientProperty("ID", String.valueOf((int)newOrder.get(0)));
            ordersViewPanel.add(ordersViewOrderPanel);

            ordersViewOrderPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(RestaurantModel.getIsNewOrdersPanelOpen()){
                        orderAcceptBtn.setVisible(true);
                        orderDesclineBtn.setVisible(true);
                        orderPreparationCompleteBtn.setVisible(false);
                        orderStartToPrepareBtn.setVisible(false);

                        orderAcceptBtn.putClientProperty("ID", String.valueOf((int)newOrder.getFirst()));
                        orderDesclineBtn.putClientProperty("ID", String.valueOf((int)newOrder.getFirst()));
                    }else if(RestaurantModel.getIsAcceptedOrdersPanelOpen()){
                        orderStartToPrepareBtn.setVisible(true);
                        orderAcceptBtn.setVisible(false);
                        orderDesclineBtn.setVisible(false);
                        orderPreparationCompleteBtn.setVisible(false);

                        orderStartToPrepareBtn.putClientProperty("ID", String.valueOf((int)newOrder.getFirst()));
                    }else{
                        orderPreparationCompleteBtn.setVisible(true);
                        orderStartToPrepareBtn.setVisible(false);
                        orderAcceptBtn.setVisible(false);
                        orderDesclineBtn.setVisible(false);

                        orderPreparationCompleteBtn.putClientProperty("ID", String.valueOf((int)newOrder.getFirst()));
                    }

                    orderViewPanel.setVisible(true);
                    orderAddressLbl.setText((String) newOrder.get(5));
                    orderViewOrderTotalPriceLbl.setText("LKR " + (int) newOrder.get(3));
                    RestaurantModel.setOrdersPanelOpenOrderCode((String)newOrder.get(4));

                    int orderId = Integer.parseInt((String)ordersViewOrderPanel.getClientProperty("ID"));
                    List<List<Object>> orderItems = RestaurantModel.getOrderItems(orderId);

                    orderViewOrderItemsPanel.removeAll();
                    orderViewOrderItemsPanel.revalidate();
                    orderViewOrderItemsPanel.repaint();
                    for(List<Object> item : orderItems){
                        JPanel orderViewOrderItemPanel = new JPanel();
                        orderViewOrderItemPanel.setPreferredSize(new Dimension(842, 74));
                        orderViewOrderItemPanel.setBackground(new Color(0x5B5B5B));
                        orderViewOrderItemPanel.setLayout(null);
                        orderViewOrderItemsPanel.add(orderViewOrderItemPanel);

                        BufferedImage itemImage = (BufferedImage) item.get(3);
                        JPanel orderItemImagePanel = new JPanel() {
                            @Override
                            protected void paintComponent(Graphics g) {
                                super.paintComponent(g);
                                if (itemImage != null) {
                                    g.drawImage(itemImage, 0, 0, getWidth(), getHeight(), this);
                                }
                            }
                        };
                        orderItemImagePanel.setBounds(10, 7, 80, 60);
                        orderViewOrderItemPanel.add(orderItemImagePanel);

                        JLabel orderItemNameLabel = new JLabel((String) item.getFirst());
                        orderItemNameLabel.setForeground(Color.WHITE);
                        orderItemNameLabel.setBounds(110, 20, 350, 30);
                        orderItemNameLabel.setFont(new Font(null, Font.PLAIN, 16));
                        orderViewOrderItemPanel.add(orderItemNameLabel);

                        JLabel orderItemQntLabel = new JLabel("x" + (int)item.get(1));
                        orderItemQntLabel.setForeground(Color.WHITE);
                        orderItemQntLabel.setFont(new Font(null, Font.PLAIN, 16));
                        orderItemQntLabel.setBounds(470, 20, 120, 30);
                        orderViewOrderItemPanel.add(orderItemQntLabel);

                        JLabel orderItemTotalPrice = new JLabel("LKR " + (int)item.get(2));
                        orderItemTotalPrice.setBounds(650, 20, 190,30);
                        orderItemTotalPrice.setForeground(Color.WHITE);
                        orderItemTotalPrice.setFont(new Font(null, Font.BOLD, 18));
                        orderViewOrderItemPanel.add(orderItemTotalPrice);
                    }
                }
            });

            JLabel orderPanelOrderCodeLbl = new JLabel("#" + (String) newOrder.get(4));
            orderPanelOrderCodeLbl.setFont(new Font(null, Font.BOLD, 18));
            orderPanelOrderCodeLbl.setForeground(Color.WHITE);
            orderPanelOrderCodeLbl.setBounds(20, 5, 160, 30);
            ordersViewOrderPanel.add(orderPanelOrderCodeLbl);


            JLabel orderPanelOrderDatelbl = new JLabel();
            java.sql.Date orderDate = (java.sql.Date) newOrder.get(1);
            LocalDate orderDate2 = orderDate.toLocalDate();

            LocalDate today = LocalDate.now();

            if((orderDate2.equals(today))){
                orderPanelOrderDatelbl.setText("Today");
            }else{
                orderPanelOrderDatelbl.setText(orderDate2.toString());
            }
            orderPanelOrderDatelbl.setForeground(Color.WHITE);
            orderPanelOrderDatelbl.setFont(new Font(null, Font.PLAIN, 12));
            orderPanelOrderDatelbl.setBounds(20, 35, 160, 20);
            ordersViewOrderPanel.add(orderPanelOrderDatelbl);


            Time orderTime = (Time) newOrder.get(2);
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            String formattedTime = timeFormat.format(orderTime);


            JLabel orderPanelOrderTimelbl = new JLabel();
            orderPanelOrderTimelbl.setText(formattedTime);
            orderPanelOrderTimelbl.setForeground(Color.WHITE);
            orderPanelOrderTimelbl.setFont(new Font(null, Font.PLAIN, 12));
            orderPanelOrderTimelbl.setBounds(20, 55, 120, 20);
            ordersViewOrderPanel.add(orderPanelOrderTimelbl);
            

            JLabel orderPanelOrderPriceLbl = new JLabel("LKR " + (int)newOrder.get(3));
            orderPanelOrderPriceLbl.setForeground(Color.WHITE);
            orderPanelOrderPriceLbl.setFont(new Font(null, Font.BOLD, 18));
            orderPanelOrderPriceLbl.setBounds(185, 25, 140, 30);
            ordersViewOrderPanel.add(orderPanelOrderPriceLbl);

            int totalPreferredHeight = 0;
            for (Component component : ordersViewPanel.getComponents()) {
                totalPreferredHeight += component.getPreferredSize().height + 5;
            }
            ordersViewPanel.setPreferredSize(new Dimension(350, totalPreferredHeight));
        }

        ordersViewPanel.revalidate();
        ordersViewPanel.repaint();
    }

    public void addOrderAcceptBtnListener(ActionListener listener){
        orderAcceptBtn.addActionListener(listener);
    }

    public String getAcceptBtnId(){
        return (String) orderAcceptBtn.getClientProperty("ID");
    }

    public void addOrderDeclineBtnListener(ActionListener listener){
        orderDesclineBtn.addActionListener(listener);
    }

    public String getDeclineBtnId(){
        return (String) orderDesclineBtn.getClientProperty("ID");
    }

    public void displayOrderAcceptOrDeclineMsge(String msge){
        JOptionPane.showMessageDialog(ordersPanel, msge, null,  JOptionPane.PLAIN_MESSAGE);
    }

    public void setOrderViewPanelVisibility(Boolean visibility){
        orderViewPanel.setVisible(visibility);
    }

    public void addOrderStartToPrepareBtnListener(ActionListener listener){
        orderStartToPrepareBtn.addActionListener(listener);
    }

    public String getStartToPrepareBtnId(){
        return (String) orderStartToPrepareBtn.getClientProperty("ID");
    }

    public void addOrderPreparationCompleteBtnListener(ActionListener listener){
        orderPreparationCompleteBtn.addActionListener(listener);
    }

    public String getOrderPreparationCompleteBtnId(){
        return (String) orderPreparationCompleteBtn.getClientProperty("ID");
    }



    ///////////dashboard/////////////////
    public void addTodayBtnListener(ActionListener listener){
        todayBtn.addActionListener(listener);
    }

    public void addThisWeekBtnListener(ActionListener listener){
        thisWeekBtn.addActionListener(listener);
    }

    public void addThisMonthBtnListener(ActionListener listener){
        thisMonthBtn.addActionListener(listener);
    }

    public void addThisYearBtnListener(ActionListener listener){
        thisYearBtn.addActionListener(listener);
    }

    public void setTimePeriodBtnStyles(String clickedBtn){
        if(Objects.equals(clickedBtn, "today")){
            todayBtn.setBackground(new Color(0x0601FF));
            todayBtn.setForeground(Color.WHITE);
            thisWeekBtn.setBackground(new Color(0xC0BFFE));
            thisWeekBtn.setForeground(Color.BLACK);
            thisMonthBtn.setBackground(new Color(0xC0BFFE));
            thisMonthBtn.setForeground(Color.BLACK);
            thisYearBtn.setBackground(new Color(0xC0BFFE));
            thisYearBtn.setForeground(Color.BLACK);
        }else if(Objects.equals(clickedBtn,"thisweek")){
            thisWeekBtn.setBackground(new Color(0x0601FF));
            thisWeekBtn.setForeground(Color.WHITE);
            todayBtn.setBackground(new Color(0xC0BFFE));
            todayBtn.setForeground(Color.BLACK);
            thisMonthBtn.setBackground(new Color(0xC0BFFE));
            thisMonthBtn.setForeground(Color.BLACK);
            thisYearBtn.setBackground(new Color(0xC0BFFE));
            thisYearBtn.setForeground(Color.BLACK);
        }else if(Objects.equals(clickedBtn, "thismonth")){
            thisMonthBtn.setBackground(new Color(0x0601FF));
            thisMonthBtn.setForeground(Color.WHITE);
            thisWeekBtn.setBackground(new Color(0xC0BFFE));
            thisWeekBtn.setForeground(Color.BLACK);
            todayBtn.setBackground(new Color(0xC0BFFE));
            todayBtn.setForeground(Color.BLACK);
            thisYearBtn.setBackground(new Color(0xC0BFFE));
            thisYearBtn.setForeground(Color.BLACK);
        }else{
            thisYearBtn.setBackground(new Color(0x0601FF));
            thisYearBtn.setForeground(Color.WHITE);
            thisWeekBtn.setBackground(new Color(0xC0BFFE));
            thisWeekBtn.setForeground(Color.BLACK);
            thisMonthBtn.setBackground(new Color(0xC0BFFE));
            thisMonthBtn.setForeground(Color.BLACK);
            todayBtn.setBackground(new Color(0xC0BFFE));
            todayBtn.setForeground(Color.BLACK);
        }
    }

    public void loadDashBoardOrderStatics(int[] ordersStatics){
        totalEarning.setText("LKR " + ordersStatics[0]);
        totalRecievedOrders.setText(String.valueOf(ordersStatics[1]));
        totalDeliveredOrders.setText(String.valueOf(ordersStatics[2]));
        totalPickedUpOrders.setText(String.valueOf(ordersStatics[3]));
        totalReadyToPickUpOrders.setText(String.valueOf(ordersStatics[4]));
        totalCancelledOrders.setText(String.valueOf(ordersStatics[5]));
    }

    public void addFindOrdersPanelSearchByDateBtnListener(ActionListener listener){
        findOrdersPanelSearchByDateBtn.addActionListener(listener);
    }

    public LocalDate getSelectedDate(){
        LocalDate selectedDate = null;
        if(dayComboBox.getSelectedIndex() == 0 || monthComboBox.getSelectedIndex() == 0 || yearComboBox.getSelectedIndex() == 0){
            return selectedDate;
        }else{
            String day = (String) dayComboBox.getSelectedItem();
            String month =  (String) monthComboBox.getSelectedItem();
            String year = (String) yearComboBox.getSelectedItem();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
            selectedDate = LocalDate.parse(day + " " + month + " " + year, formatter);
            return selectedDate;
        }
    }

    public void loadSearchOrdersSelectPanel(List<List<Object>> orders){
        dashBoardSearchOrderSelectPanel.removeAll();
        dashBoardSearchOrderSelectPanel.revalidate();
        dashBoardSearchOrderSelectPanel.repaint();

        for(List<Object> order : orders){
            JPanel ordersSelectPanelOrderPanel = new JPanel();
            ordersSelectPanelOrderPanel.setPreferredSize(new Dimension(213, 90));
            ordersSelectPanelOrderPanel.setLayout(null);
            ordersSelectPanelOrderPanel.setBackground(new Color(0x848484));
            dashBoardSearchOrderSelectPanel.add(ordersSelectPanelOrderPanel);

            if(orders.size() == 1){
                RestaurantModel.setDashBoardSearchSelectedOrderId((int)order.getFirst());
                RestaurantModel.setIsDashBoardSearchOrderViewPanelOpen(true);
                dashBoardSearchOrderViewPanel.setVisible(true);

                orderViewPanelOrderCode.setText("#" + order.get(1));
                orderViewPanelOrderStatus.setText((String)order.get(2));
                orderViewPanelOrderType.setText((String)order.get(3));
                orderViewPanelOrderDate.setText(String.valueOf(order.get(5)));
                orderViewPanelCustName.setText((String)order.get(7));
                orderViewPanelCustAddress.setText((String)order.get(6));
                orderTotal.setText("LKR " + (int)order.get(4));

                List<List<Object>> orderItems = RestaurantModel.getOrderItems((int)order.get(0));
                loadSearchOrderItems(orderItems);
            }else{
                ordersSelectPanelOrderPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        RestaurantModel.setDashBoardSearchSelectedOrderId((int)order.getFirst());
                        RestaurantModel.setIsDashBoardSearchOrderViewPanelOpen(true);
                        dashBoardSearchOrderViewPanel.setVisible(true);

                        orderViewPanelOrderCode.setText("#" + order.get(1));
                        orderViewPanelOrderStatus.setText((String)order.get(2));
                        orderViewPanelOrderType.setText((String)order.get(3));
                        orderViewPanelOrderDate.setText(String.valueOf(order.get(5)));
                        orderViewPanelCustName.setText((String)order.get(7));
                        orderViewPanelCustAddress.setText((String)order.get(6));
                        orderTotal.setText("LKR " + (int)order.get(4));

                        List<List<Object>> orderItems = RestaurantModel.getOrderItems((int)order.get(0));
                        loadSearchOrderItems(orderItems);
                    }
                });
            }

            JLabel orderLabel = new JLabel("Order");
            orderLabel.setFont(new Font(null, Font.BOLD, 18));
            orderLabel.setForeground(Color.WHITE);
            orderLabel.setBounds(10, 5, 50, 20);
            ordersSelectPanelOrderPanel.add(orderLabel);

            JLabel orderCodeLabel = new JLabel("#" + order.get(1));
            orderCodeLabel.setFont(new Font(null, Font.BOLD, 18));
            orderCodeLabel.setForeground(Color.WHITE);
            orderCodeLabel.setBounds(65, 5, 140, 20);
            ordersSelectPanelOrderPanel.add(orderCodeLabel);

            JLabel ordersSelectPanelOrderPrice = new JLabel("LKR " + (int)order.get(4));
            ordersSelectPanelOrderPrice.setFont(new Font(null, Font.BOLD, 18));
            ordersSelectPanelOrderPrice.setForeground(Color.WHITE);
            ordersSelectPanelOrderPrice.setBounds(10, 35, 190, 20);
            ordersSelectPanelOrderPrice.setHorizontalAlignment(SwingConstants.RIGHT);
            ordersSelectPanelOrderPanel.add(ordersSelectPanelOrderPrice);

            JLabel ordersSelectPanelOrderCustName = new JLabel((String) order.get(7));
            ordersSelectPanelOrderCustName.setBounds(10, 65, 190, 20);
            ordersSelectPanelOrderCustName.setFont(new Font(null, Font.PLAIN, 15));
            ordersSelectPanelOrderCustName.setForeground(Color.WHITE);
            ordersSelectPanelOrderPanel.add(ordersSelectPanelOrderCustName);
        }

        int totalPreferredHeight = 0;
        for (Component component : dashBoardSearchOrderSelectPanel.getComponents()) {
            totalPreferredHeight += component.getPreferredSize().height + 5;
        }
        dashBoardSearchOrderSelectPanel.setPreferredSize(new Dimension(236, totalPreferredHeight));
    }

    public void loadSearchOrderItems(List<List<Object>> orderItems){
        dashBoardSearchOrderItemsPanel.removeAll();
        dashBoardSearchOrderItemsPanel.revalidate();
        dashBoardSearchOrderItemsPanel.repaint();

        for(List<Object> item : orderItems){
            JPanel dashBoardSearchOrderItemPanel = new JPanel();
            dashBoardSearchOrderItemPanel.setPreferredSize(new Dimension(720, 74));
            dashBoardSearchOrderItemPanel.setBackground(new Color(0x5B5B5B));
            dashBoardSearchOrderItemPanel.setLayout(null);
            dashBoardSearchOrderItemsPanel.add(dashBoardSearchOrderItemPanel);

            BufferedImage itemImage = (BufferedImage) item.get(3);
            JPanel orderItemImagePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (itemImage != null) {
                        g.drawImage(itemImage, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            orderItemImagePanel.setBounds(10, 7, 80, 60);
            dashBoardSearchOrderItemPanel.add(orderItemImagePanel);

            JLabel orderItemNameLabel = new JLabel((String)item.getFirst());
            orderItemNameLabel.setForeground(Color.WHITE);
            orderItemNameLabel.setBounds(110, 20, 300, 30);
            orderItemNameLabel.setFont(new Font(null, Font.PLAIN, 16));
            dashBoardSearchOrderItemPanel.add(orderItemNameLabel);

            JLabel orderItemQntLabel = new JLabel("x" + (int)item.get(1));
            orderItemQntLabel.setForeground(Color.WHITE);
            orderItemQntLabel.setFont(new Font(null, Font.PLAIN, 16));
            orderItemQntLabel.setBounds(430, 20, 60, 30);
            dashBoardSearchOrderItemPanel.add(orderItemQntLabel);

            JLabel orderItemTotalPrice = new JLabel("LKR " + (int)item.get(2));
            orderItemTotalPrice.setBounds(510, 20, 190,30);
            orderItemTotalPrice.setForeground(Color.WHITE);
            orderItemTotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
            orderItemTotalPrice.setFont(new Font(null, Font.BOLD, 18));
            dashBoardSearchOrderItemPanel.add(orderItemTotalPrice);
        }

        int totalPreferredHeight1 = 0;
        for (Component component : dashBoardSearchOrderItemsPanel.getComponents()) {
            totalPreferredHeight1 += component.getPreferredSize().height + 5;
        }
        dashBoardSearchOrderItemsPanel.setPreferredSize(new Dimension(732, totalPreferredHeight1));
    }

    public void setDashBoardSearchSelectedOrderStatus(String status){
        orderViewPanelOrderStatus.setText(status);
    }

    public void addDashboardSearchPanelCloseBtn1Listener(ActionListener listener){
        dashboardSearchPanelCloseBtn1.addActionListener(listener);
    }

    public void addDashboardSearchPanelCloseBtn2Listener(ActionListener listener){
        dashboardSearchPanelCloseBtn2.addActionListener(listener);
    }

    public void setDashBoardSearchOrderViewPanelVisibility(Boolean visibility){
        dashBoardSearchOrderViewPanel.setVisible(visibility);
    }

    public void setDashBoardSearchOrderContainerPanelVisibility(Boolean visibility){
        dashBoardSearchOrderContainerPanel.setVisible(visibility);
    }

    public void displayNoSearchResultMsge(String msge){
        JOptionPane.showMessageDialog(homePanel, msge, null,  JOptionPane.PLAIN_MESSAGE);
    }

    public void addFindOrdersPanelSearchByIdBtnListener(ActionListener listener){
        findOrdersPanelSearchByIdBtn.addActionListener(listener);
    }

    public String getSearchOrderCode(){
        return findOrdersPanelOCodeTxt.getText();
    }


    /////checkout panel//////
    public void loadCheckOutOrdersListPanel(List<List<Object>> orders){
        checkOutOrdersListPanel.removeAll();
        checkOutOrdersListPanel.revalidate();
        checkOutOrdersListPanel.repaint();

        for(List<Object> order : orders){
            JPanel checkOutOrdersListOrderPanel = new JPanel();
            checkOutOrdersListOrderPanel.setPreferredSize(new Dimension(303, 87));
            checkOutOrdersListOrderPanel.setLayout(null);
            checkOutOrdersListOrderPanel.setBackground(new Color(0x7A7575));
            checkOutOrdersListPanel.add(checkOutOrdersListOrderPanel);

            checkOutOrdersListOrderPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    checkOutOrderViewPanel.setVisible(true);
                    checkOutOrderPaymentPanel.setVisible(false);
                    balanceText.setText("");
                    paidAmountText.setText("");

                    checkOutOrderViewPanelDate.setText(String.valueOf((Date)order.get(5)));
                    checkOutOrderViewPanelTime.setText((String)order.get(8));
                    checkOutOrderViewPanelOrderTotal.setText("LKR " + (int)order.get(4));
                    checkOutOrderViewPanelCheckOutBtn.putClientProperty("ID", String.valueOf((int)order.getFirst()));

                    List<List<Object>> orderItems = RestaurantModel.getOrderItems((int)order.getFirst());
                    loadCheckOutOrderItems(orderItems);

                    RestaurantModel.setCheckOutOrderTotal((int)order.get(4));
                    RestaurantModel.setCheckOutOrderId((int)order.getFirst());
                    RestaurantModel.setCheckOutOrderCode((String)(order.get(1)));
                    RestaurantModel.setCheckOutOrderCustEmail((String)(order.get(9)));
                }
            });

            JLabel checkOutListOrderPanelOrderLbl = new JLabel("Order");
            checkOutListOrderPanelOrderLbl.setFont(new Font(null, Font.BOLD, 18));
            checkOutListOrderPanelOrderLbl.setForeground(Color.WHITE);
            checkOutListOrderPanelOrderLbl.setBounds(10, 10, 50, 20);
            checkOutOrdersListOrderPanel.add(checkOutListOrderPanelOrderLbl);

            JLabel checkOutListOrderPanelOrderCode = new JLabel("#" + order.get(1));
            checkOutListOrderPanelOrderCode.setFont(new Font(null, Font.BOLD, 18));
            checkOutListOrderPanelOrderCode.setForeground(Color.WHITE);
            checkOutListOrderPanelOrderCode.setBounds(65, 10, 100, 20);
            checkOutOrdersListOrderPanel.add(checkOutListOrderPanelOrderCode);

            JLabel checkOutListOrderPanelOrderTotal = new JLabel("LKR " + (int)order.get(4));
            checkOutListOrderPanelOrderTotal.setBounds(10, 35, 280, 18);
            checkOutListOrderPanelOrderTotal.setFont(new Font(null, Font.BOLD, 18));
            checkOutListOrderPanelOrderTotal.setForeground(Color.WHITE);
            checkOutListOrderPanelOrderTotal.setHorizontalAlignment(SwingConstants.RIGHT);
            checkOutOrdersListOrderPanel.add(checkOutListOrderPanelOrderTotal);

            JLabel checkOutListOrderPanelOrderCustName = new JLabel((String)order.get(7));
            checkOutListOrderPanelOrderCustName.setFont(new Font(null, Font.BOLD, 16));
            checkOutListOrderPanelOrderCustName.setForeground(Color.WHITE);
            checkOutListOrderPanelOrderCustName.setBounds(10, 60, 280, 20);
            checkOutOrdersListOrderPanel.add(checkOutListOrderPanelOrderCustName);
        }

        int totalPreferredHeight = 0;
        for (Component component : checkOutOrdersListPanel.getComponents()) {
            totalPreferredHeight += component.getPreferredSize().height + 5;
        }
        checkOutOrdersListPanel.setPreferredSize(new Dimension(330, totalPreferredHeight));
    }

    public void loadCheckOutOrderItems(List<List<Object>> orderItems){
        checkOutOrderItemsPanel.removeAll();
        checkOutOrderItemsPanel.revalidate();
        checkOutOrderItemsPanel.repaint();

        for(List<Object> item : orderItems){
            JPanel checkOutOrderItemPanel = new JPanel();
            checkOutOrderItemPanel.setPreferredSize(new Dimension(842, 74));
            checkOutOrderItemPanel.setBackground(new Color(0x5B5B5B));
            checkOutOrderItemPanel.setLayout(null);
            checkOutOrderItemsPanel.add(checkOutOrderItemPanel);

            BufferedImage itemImage = (BufferedImage)item.get(3);
            JPanel orderItemImagePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (itemImage != null) {
                        g.drawImage(itemImage, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            orderItemImagePanel.setBounds(10, 7, 80, 60);
            checkOutOrderItemPanel.add(orderItemImagePanel);

            JLabel orderItemNameLabel = new JLabel((String)item.getFirst());
            orderItemNameLabel.setForeground(Color.WHITE);
            orderItemNameLabel.setBounds(110, 20, 350, 30);
            orderItemNameLabel.setFont(new Font(null, Font.PLAIN, 16));
            checkOutOrderItemPanel.add(orderItemNameLabel);

            JLabel orderItemQntLabel = new JLabel("x" + (int)item.get(1));
            orderItemQntLabel.setForeground(Color.WHITE);
            orderItemQntLabel.setFont(new Font(null, Font.PLAIN, 16));
            orderItemQntLabel.setBounds(470, 20, 120, 30);
            checkOutOrderItemPanel.add(orderItemQntLabel);

            JLabel orderItemTotalPrice = new JLabel("LKR " + (int)item.get(2));
            orderItemTotalPrice.setBounds(650, 20, 190,30);
            orderItemTotalPrice.setForeground(Color.WHITE);
            orderItemTotalPrice.setFont(new Font(null, Font.BOLD, 18));
            checkOutOrderItemPanel.add(orderItemTotalPrice);
        }

        int totalPreferredHeight1 = 0;
        for (Component component : checkOutOrderItemsPanel.getComponents()) {
            totalPreferredHeight1 += component.getPreferredSize().height + 5;
        }
        checkOutOrderItemsPanel.setPreferredSize(new Dimension(870, totalPreferredHeight1));
    }

    public void closecheckOutOrderViewPanel(){
        checkOutOrderViewPanel.setVisible(false);
    }

    public void addCheckOutOrderSearchBarTxtListener(DocumentListener listener){
        checkOutOrderSearchBarTxt.getDocument().addDocumentListener(listener);
    }

    public String getCheckOutOrderSearchBarCode(){
        return checkOutOrderSearchBarTxt.getText();
    }

    public void addOrderCheckOutBtnListener(ActionListener listener){
        checkOutOrderViewPanelCheckOutBtn.addActionListener(listener);
    }

    public void setCheckOutOrderPaymentPanelVisibility(Boolean visibility){
        checkOutOrderPaymentPanel.setVisible(visibility);
    }

    public void addPaymentPanelCloseBtnListener(ActionListener listener){
        paymentPanelCloseBtn.addActionListener(listener);
    }

    public void setPaymentPanelOrderTotal(int orderTotal){
        total.setText("LKR " + orderTotal);
    }

    public void addPaidAmountTextFieldListener(DocumentListener listener){
        paidAmountText.getDocument().addDocumentListener(listener);
    }

    public String getPaidAmount(){
        return paidAmountText.getText();
    }

    public void setCheckOutOrderbalance(String balance){
        balanceText.setText(balance);
    }

    public String getCheckOutOrderBalance(){
        return balanceText.getText();
    }

    public void addPaymentDoneBtnListener(ActionListener listener){
        paymentDoneBtn.addActionListener(listener);
    }

    public void clearPaymentPanel(){
        balanceText.setText("");
        paidAmountText.setText("");
    }

    public void closeCheckOutOrderViewPanel(){
        checkOutOrderViewPanel.setVisible(false);
    }

    public void displayPaymentSuccessMsge(){
        JOptionPane.showMessageDialog(checkOutPanel, "Payment successfull", null,  JOptionPane.PLAIN_MESSAGE);
    }

    public void displayPaymentFailedMsge(){
        JOptionPane.showMessageDialog(checkOutPanel, "Payment failed", null,  JOptionPane.ERROR_MESSAGE);
    }

    public void clearCheckOutPanelSearchBar(){
        checkOutOrderSearchBarTxt.setText("");
    }

    public void setLoggedRestName(String restName){
        loggedRestNameLabel.setText(restName);
    }

    public void addRestOpenCloseBtnListener(ActionListener listener){
        restOpenCloseBtn.addActionListener(listener);
    }

    public void displayRestOpenCloseMsge(String msge){
        JOptionPane.showMessageDialog(editPanel,  msge, null,  JOptionPane.PLAIN_MESSAGE);
    }

    public void setRestOpenCloseBtnStyles(String btn){
        if(Objects.equals(btn, "open")){
            restOpenCloseBtn.setText("Close");
            restOpenCloseBtn.setBackground(Color.RED);
        }else{
            restOpenCloseBtn.setText("Open");
            restOpenCloseBtn.setBackground(Color.GREEN);
        }
    }
}
