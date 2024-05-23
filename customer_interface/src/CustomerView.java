import com.mysql.cj.exceptions.CJPacketTooBigException;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.sun.source.tree.ForLoopTree;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.IconUIResource;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainView;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.nio.Buffer;
import java.time.chrono.JapaneseChronology;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PrimitiveIterator;

public class CustomerView {
    JLabel menuIconLabel;
    JPanel sideBarHome;
    JButton addRestBtn;
    JPanel restSignupPanelContainer;
    JPanel homePanel;
    JLayeredPane layeredPane;
    JFrame homeFrame;
    JLabel restSignUpCloseLabel;
    JButton restSignupSubmitBtn;
    JTextField storeNameTextField, storeAddressTextField, storeEmailTextField, storeMobileTextField, storeCityTextField, storePostalCodeTextField;
    JLabel emailConfirmMsgLabel;
    JPanel emailConfirmPanel;
    JPanel restSignupPanel;
    JTextField emailConfirmCodeTextField;
    JButton emailConfirmBtn;

    JPanel restaurantsPanel;

    JScrollPane RestaurantsScrollPane;
    JPanel filterPanelOption1, filterPanelOption2, filterPanelOption3;
    JLabel allLabel, deliveryLabel, pickupLabel;

    JPanel restMenuIconBtn;
    JPanel restaurantMyFeaturesPanel;
    JPanel restaurantPanel;
    JPanel restaurantMenuPanel;
    JPanel restMenuItemsPanel;
    JScrollPane RestMenuItemsScrollPane;
    JButton loginBtnSidebar, signupBtnSidebar;
    JButton loginBtn, signupBtn;
    JPanel userLogSignupContainer;
    JLabel registerFormCloseBtn;
    JPanel registerFormPanelLeft, loginFormPanelLeft;
    JLabel loginFormSignUpLbl, registerFormLoginLbl;
    JButton registerFormSignUpBtn;
    JTextField usernameTxt, registerFormEmailTxt, registerFormPwdTxt, registerFormConfirmPwdTxt;
    JPanel logSignUpFormPanel;
    JPanel customerEmailConfirmPanel;
    JButton customerEmailConfirmBtn;
    JTextField customerEmailConfirmCodeTxt;
    JTextField loginFormEmailTxt, loginFormPwdTxt;
    JButton loginFormLoginBtn;
    JPanel homePanelUserProfilePanel;
    JButton logoutBtnSidebar;
    JPanel restaurantPanelUserProfilePanel;
    JButton restaurantPanelLoginBtn;
    JPanel restaurantBackBtnPanel;
    JPanel addToOrderPanel;
    JButton addToOrderPanelCloseBtn;
    JLabel orderItemNameLbl, orderItemPriceLbl, orderItemSubTotalLbl;
    JTextArea orderItemDescriptionTextArea;
    JRadioButton orderItemDeliveryRadioBtn, orderItemPickupRadioBtn;
    JLabel orderItemPickupLbl, orderItemDeliveryLbl;
    JPanel addToOrderLeftPanel;
    BufferedImage openFoodItemImage = null;
    JButton orderItemAddQntPlusBtn, orderItemAddQntMinusBtn;
    JTextField orderItemQntTxt;
    JButton addToOrderBtn;
    JTextField cartOrderAddressTxt;
    JPanel cartBtnPanel;
    JPanel cartContainerPanel;
    JButton cartContainerPanelCloseBtn;
    JPanel cartItemsPanel;
    JLabel cartSubTotalPriceLabel;
    JButton cartDeliveryBtn, cartPickUpBtn;
    JLabel cartRestNameLbl;
    JLabel cartItemsCountLbl;
    JPanel cartsListPanel;
    JPanel homePanelCartBtnPanel;
    JPanel cartSelectBtnPanel;
    JButton cartPlaceOrderBtn;
    JButton signupDeliverBtn;
    JPanel deliverySignUpContainer;
    JLabel deliverySignUpContainerCloseLbl;
    JButton deliverySignUpBtn;
    JTextField deliveryUsernameTxt, deliveryEmailTxt, deliveryNicTxt, deliveryVehicleNumberTxt;
    JComboBox<String> deliveryVehicleTypeComboBox;
    JTextField deliveryFullNameTxt, deliveryTelTxt;
    JPanel deliverySignUpEmailConfirmPanel;
    JPanel deliverySignUpPanel;
    JPanel deliverySignUpEmailConfirmPanelBackBtn;
    JButton deliveryEmailConfirmBtn;
    JTextField  deliveryConfirmEmailTxt;
    JPanel sideBarHomeMyOrdersListPanel, sideBarHomeMyOrdersPanel;
    JPanel myOrdersListPanelBackBtnPanel;
    JPanel sideBarHomeMyOrdersViewPanel;
    JPanel liveOrderInfoOrderItemsPanel;
    JPanel liveOrderInfoPanel;
    JButton liveOrderInfoPanelCloseBtn;
    JLabel liveOrderStatus, liveOrderOrderType, liveOrderRestName, liveOrderRestTel, liveOrderTotal;
    JTextArea liveOrderRestAddress;
    JTextField searchPanelSearchBar;
    JPanel restSearchResultsPanel;
    JPanel restSearchPanel;
    JPanel searchBarPanel;
    JPanel searchPanelBackBtnPanel;
    JPanel sideBarHomeFavoritesPanel;
    JPanel myFavoritesPanel, myFavoriteBackBtnPanel;
    JButton favRestsBtn, favMealsBtn;
    JPanel favItemsPanel;
    ButtonGroup group;
    JButton restSignUpCloseBtn;
    JTextField storeBranchNameTextField;
    JPanel restSignUpContainerImagePanel;

    JLabel restOpenLabel, restNameLabel;
    JPanel restOpenStatusPanel, restNamePanel;
    private Image offscreenBuffer;

    public CustomerView() {
        homeFrame = new JFrame();
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(1500, 800);
        homeFrame.setResizable(false);

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1500, 800));

        homePanel = new JPanel();
//        homePanel.setBackground(new Color(0xF4F4F4));
        homePanel.setBounds(0, 0, 1500, 800);
        homePanel.setLayout(new BorderLayout());

        JPanel homeTopPanel = new JPanel();
        homeTopPanel.setPreferredSize(new Dimension(1350, 130));
        homeTopPanel.setBackground(new Color(0x1C1C1C));
        homeTopPanel.setLayout(new BorderLayout());

        JPanel homeBottomPanel = new JPanel();
        homeBottomPanel.setPreferredSize(new Dimension(1350, 670));
        homeBottomPanel.setBackground(new Color(0x303030));
        homeBottomPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
        homeBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        homePanel.add(homeTopPanel, BorderLayout.NORTH);
        homePanel.add(homeBottomPanel, BorderLayout.SOUTH);


        sideBarHome = new JPanel();
        sideBarHome.setBounds(0, 130, 320, 670);
        sideBarHome.setBackground(new Color(0x191919));
        sideBarHome.setLayout(null);
        sideBarHome.setVisible(false);

        sideBarHomeMyOrdersPanel = new JPanel();
        sideBarHomeMyOrdersPanel.setBounds(50, 20, 220, 40);
        sideBarHomeMyOrdersPanel.setBackground(new Color(0x191919));
        sideBarHomeMyOrdersPanel.setLayout(null);
        sideBarHome.add(sideBarHomeMyOrdersPanel);


        try {
            File imageFile = new File("Images/orders.png");
            BufferedImage ordersIcon = ImageIO.read(imageFile);
            JPanel myOrdersIconPanelHome = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (ordersIcon != null) {
                        g.drawImage(ordersIcon, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };

            myOrdersIconPanelHome.setBounds(5, 0, 40, 40);
            myOrdersIconPanelHome.setBackground(new Color(0x191919));
            sideBarHomeMyOrdersPanel.add(myOrdersIconPanelHome);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        JLabel myOrdersLabelHome = new JLabel("MY ORDERS");
        myOrdersLabelHome.setFont(new Font(null, Font.BOLD, 17));
        myOrdersLabelHome.setForeground(Color.WHITE);
        myOrdersLabelHome.setBounds(65, 0, 150, 40);
        sideBarHomeMyOrdersPanel.add(myOrdersLabelHome);

        sideBarHomeFavoritesPanel = new JPanel();
        sideBarHomeFavoritesPanel.setBounds(50, 70, 220, 40);
        sideBarHomeFavoritesPanel.setBackground(new Color(0x191919));
        sideBarHomeFavoritesPanel.setLayout(null);
        sideBarHome.add(sideBarHomeFavoritesPanel);

        try {
            File imageFile = new File("Images/favorites.png");
            BufferedImage favoritesIcon = ImageIO.read(imageFile);
            JPanel favoritesIconPanelHome = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (favoritesIcon != null) {
                        g.drawImage(favoritesIcon, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };

            favoritesIconPanelHome.setBounds(10, 7, 25, 25);
            favoritesIconPanelHome.setBackground(new Color(0x191919));
            sideBarHomeFavoritesPanel.add(favoritesIconPanelHome);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        JLabel favoritesLabelHome = new JLabel("FAVORITES");
        favoritesLabelHome.setFont(new Font(null, Font.BOLD, 17));
        favoritesLabelHome.setForeground(Color.WHITE);
        favoritesLabelHome.setBounds(65, 0, 150, 40);
        sideBarHomeFavoritesPanel.add(favoritesLabelHome);

        sideBarHomeMyOrdersListPanel = new JPanel();
        sideBarHomeMyOrdersListPanel.setBounds(0, 130, 320, 670);
        sideBarHomeMyOrdersListPanel.setBackground(new Color(0x191919));
        sideBarHomeMyOrdersListPanel.setVisible(false);
        sideBarHomeMyOrdersListPanel.setLayout(null);
        layeredPane.add(sideBarHomeMyOrdersListPanel, JLayeredPane.PALETTE_LAYER);

        try {
            File imageFile = new File("Images/backArrow.png");
            BufferedImage backBtn = ImageIO.read(imageFile);
            myOrdersListPanelBackBtnPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (backBtn != null) {
                        g.drawImage(backBtn, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };

            myOrdersListPanelBackBtnPanel.setBounds(5, 5, 30, 30);
            myOrdersListPanelBackBtnPanel.setBackground(new Color(0x191919));
            sideBarHomeMyOrdersListPanel.add(myOrdersListPanelBackBtnPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        JLabel myOrdersLabel = new JLabel("MY ORDERS");
        myOrdersLabel.setFont(new Font(null, Font.BOLD, 17));
        myOrdersLabel.setForeground(Color.WHITE);
        myOrdersLabel.setBounds(100, 50, 150, 30);
        sideBarHomeMyOrdersListPanel.add(myOrdersLabel);

        ///////////////////////
        sideBarHomeMyOrdersViewPanel = new JPanel();
        sideBarHomeMyOrdersViewPanel.setPreferredSize(new Dimension(320, 500));
        sideBarHomeMyOrdersViewPanel.setBackground(new Color(0x2B2B2B));
        sideBarHomeMyOrdersViewPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 5));

        JScrollPane sideBarHomeMyOrdersViewPanelScrollPane = new JScrollPane(sideBarHomeMyOrdersViewPanel);
        sideBarHomeMyOrdersViewPanelScrollPane.setBounds(0, 90, 320, 500);
        sideBarHomeMyOrdersViewPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sideBarHomeMyOrdersViewPanelScrollPane.setBorder(null);
        sideBarHomeMyOrdersListPanel.add(sideBarHomeMyOrdersViewPanelScrollPane);
        ///////////////////////


        JPanel sideBarHomeTop = new JPanel();
        sideBarHomeTop.setLayout(new FlowLayout());
        sideBarHomeTop.setBackground(new Color(0x191919));
        sideBarHomeTop.setBounds(0, 390, 320, 100);
        sideBarHome.add(sideBarHomeTop);

        JPanel sideBarHomeBottom = new JPanel();
        sideBarHomeBottom.setLayout(new FlowLayout());
        sideBarHomeBottom.setBackground(new Color(0x191919));
        sideBarHomeBottom.setBounds(0, 500, 320, 120);
        sideBarHome.add(sideBarHomeBottom);

        addRestBtn = new JButton("Add your restaurant");
        addRestBtn.setBackground(new Color(0x3B3B3B));
        addRestBtn.setBorder(null);
        addRestBtn.setForeground(Color.WHITE);
        addRestBtn.setPreferredSize(new Dimension(300, 35));
        sideBarHomeTop.add(addRestBtn);

        signupDeliverBtn = new JButton("Sign up to deliver");
        signupDeliverBtn.setBackground(new Color(0x3B3B3B));
        signupDeliverBtn.setBorder(null);
        signupDeliverBtn.setForeground(Color.WHITE);
        signupDeliverBtn.setPreferredSize(new Dimension(300, 35));
        sideBarHomeTop.add(signupDeliverBtn);

        loginBtnSidebar = new JButton("LOG IN");
        loginBtnSidebar.setPreferredSize(new Dimension(300, 50));
        loginBtnSidebar.setVisible(true);
        sideBarHomeBottom.add(loginBtnSidebar);

        signupBtnSidebar = new JButton("SIGN UP");
        signupBtnSidebar.setPreferredSize(new Dimension(300, 50));
        signupBtnSidebar.setVisible(true);
        sideBarHomeBottom.add(signupBtnSidebar);

        logoutBtnSidebar = new JButton("LOG Out");
        logoutBtnSidebar.setPreferredSize(new Dimension(300, 50));
        logoutBtnSidebar.setVisible(false);
        sideBarHomeBottom.add(logoutBtnSidebar);

        ImageIcon menuBarIcon = new ImageIcon("Images/hamburger-menu.png");
        Image originalImage = menuBarIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(61, 61, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        menuIconLabel = new JLabel(resizedIcon);
        menuIconLabel.setBorder(new EmptyBorder(0, 40, 0, 0));
        homeTopPanel.add(menuIconLabel, BorderLayout.WEST);

        JLabel logoLabel = new JLabel("LOGO");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 56));
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setBorder(new EmptyBorder(0, 450, 0, 0));
        homeTopPanel.add(logoLabel, BorderLayout.CENTER);

        JPanel logPanel = new JPanel();
        logPanel.setPreferredSize(new Dimension(300, 50));
        logPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 42));
        logPanel.setBackground(new Color(0x1C1C1C));
        homeTopPanel.add(logPanel, BorderLayout.EAST);

        ///////
        liveOrderInfoPanel = new JPanel();
        liveOrderInfoPanel.setBounds(340, 150, 1120, 592);
        liveOrderInfoPanel.setBackground(new Color(0x191919));
        liveOrderInfoPanel.setLayout(null);
        liveOrderInfoPanel.setVisible(false);
        layeredPane.add(liveOrderInfoPanel, JLayeredPane.PALETTE_LAYER);

        JLabel liveOrderStatusLabel = new JLabel("Status:");
        liveOrderStatusLabel.setBounds(20, 20, 50, 20);
        liveOrderStatusLabel.setForeground(Color.WHITE);
        liveOrderStatusLabel.setFont(new Font(null, Font.PLAIN, 16));
        liveOrderInfoPanel.add(liveOrderStatusLabel);

        liveOrderStatus = new JLabel("to be delivered");
        liveOrderStatus.setFont(new Font(null, Font.PLAIN, 16));
        liveOrderStatus.setForeground(new Color(0x22B10B));
        liveOrderStatus.setBounds(75, 20, 150, 20);
        liveOrderInfoPanel.add(liveOrderStatus);

        JLabel liveOrderOrderTypeLabel = new JLabel("Order Type:");
        liveOrderOrderTypeLabel.setBounds(20, 60, 100, 20);
        liveOrderOrderTypeLabel.setForeground(Color.WHITE);
        liveOrderOrderTypeLabel.setFont(new Font(null, Font.PLAIN, 16));
        liveOrderInfoPanel.add(liveOrderOrderTypeLabel);

        liveOrderOrderType = new JLabel("Delivery");
        liveOrderOrderType.setFont(new Font(null, Font.PLAIN, 16));
        liveOrderOrderType.setForeground(Color.WHITE);
        liveOrderOrderType.setBounds(120, 60, 100, 20);
        liveOrderInfoPanel.add(liveOrderOrderType);

        liveOrderRestName = new JLabel("KFC-MATARA");
        liveOrderRestName.setBounds(20, 100, 200, 20);
        liveOrderRestName.setForeground(Color.WHITE);
        liveOrderRestName.setFont(new Font(null, Font.PLAIN, 16));
        liveOrderInfoPanel.add(liveOrderRestName);

        JLabel liveOrderRestAddressLabel = new JLabel("Address:");
        liveOrderRestAddressLabel.setBounds(20, 140, 100, 20);
        liveOrderRestAddressLabel.setForeground(Color.WHITE);
        liveOrderRestAddressLabel.setFont(new Font(null, Font.PLAIN, 16));
        liveOrderInfoPanel.add(liveOrderRestAddressLabel);

        liveOrderRestAddress = new JTextArea();
        liveOrderRestAddress.setText("No 15 Henewatta thihagoda matara");
        liveOrderRestAddress.setBounds(20, 165, 200, 80);
        liveOrderRestAddress.setFont(new Font(null, Font.PLAIN, 16));
        liveOrderRestAddress.setLineWrap(true);
        liveOrderRestAddress.setWrapStyleWord(true);
        liveOrderRestAddress.setEditable(false);
        liveOrderRestAddress.setFocusable(false);
        liveOrderRestAddress.setForeground(Color.WHITE);
        liveOrderRestAddress.setBackground(null);
        liveOrderInfoPanel.add(liveOrderRestAddress);

        JLabel liveOrderRestTelLabel = new JLabel("Hotline:");
        liveOrderRestTelLabel.setBounds(20, 260, 60, 20);
        liveOrderRestTelLabel.setForeground(Color.WHITE);
        liveOrderRestTelLabel.setFont(new Font(null, Font.PLAIN, 16));
        liveOrderInfoPanel.add(liveOrderRestTelLabel);

        liveOrderRestTel = new JLabel("076-7139018");
        liveOrderRestTel.setBounds(85, 260, 150, 20);
        liveOrderRestTel.setForeground(Color.WHITE);
        liveOrderRestTel.setFont(new Font(null, Font.PLAIN, 16));
        liveOrderInfoPanel.add(liveOrderRestTel);

        liveOrderTotal = new JLabel("LKR 1200000");
        liveOrderTotal.setFont(new Font(null, Font.BOLD, 30));
        liveOrderTotal.setForeground(Color.WHITE);
        liveOrderTotal.setBounds(240, 530, 850, 40);
        liveOrderTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        liveOrderInfoPanel.add(liveOrderTotal);

        liveOrderInfoPanelCloseBtn = new JButton("X");
        liveOrderInfoPanelCloseBtn.setFont(new Font(null, Font.PLAIN, 10));
        liveOrderInfoPanelCloseBtn.setBorder(null);
        liveOrderInfoPanelCloseBtn.setFocusable(false);
        liveOrderInfoPanelCloseBtn.setBackground(Color.WHITE);
        liveOrderInfoPanelCloseBtn.setForeground(Color.BLACK);
        liveOrderInfoPanelCloseBtn.setBounds(1095, 5, 20, 20);
        liveOrderInfoPanel.add(liveOrderInfoPanelCloseBtn);

        //....
        liveOrderInfoOrderItemsPanel = new JPanel();
        liveOrderInfoOrderItemsPanel.setPreferredSize(new Dimension(850, 500));
        liveOrderInfoOrderItemsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

        JScrollPane liveOrderInfoOrderItemsPanelScrollPane = new JScrollPane(liveOrderInfoOrderItemsPanel);
        liveOrderInfoOrderItemsPanelScrollPane.setBounds(240, 20, 850, 500);
        liveOrderInfoOrderItemsPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        liveOrderInfoOrderItemsPanelScrollPane.setBorder(null);
        liveOrderInfoPanel.add(liveOrderInfoOrderItemsPanelScrollPane);
        //....
        ////////


        //////////
        homePanelCartBtnPanel = new JPanel();
        homePanelCartBtnPanel.setPreferredSize(new Dimension(120, 45));
        homePanelCartBtnPanel.setLayout(null);
        homePanelCartBtnPanel.setBackground(Color.BLACK);
        homePanelCartBtnPanel.setBorder(BorderFactory.createLineBorder(new Color(0x555555)));
        logPanel.add(homePanelCartBtnPanel);

        try {
            File imageFile = new File("Images/cart.png");
            BufferedImage cartImage = ImageIO.read(imageFile);
            JPanel cartLogoPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (cartImage != null) {
                        g.drawImage(cartImage, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };

            cartLogoPanel.setBounds(15, 7, 40, 30);
            cartLogoPanel.setBackground(Color.BLACK);
            homePanelCartBtnPanel.add(cartLogoPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        JLabel homePanelCartLabel = new JLabel("CART");
        homePanelCartLabel.setBounds(70, 0, 40, 45);
        homePanelCartLabel.setForeground(Color.WHITE);
        homePanelCartBtnPanel.add(homePanelCartLabel);
        //////////


        loginBtn = new JButton("LOG IN");
        loginBtn.setFocusable(false);
        loginBtn.setBackground(new Color(0x686262));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBorder(null);
        loginBtn.setVisible(true);
        loginBtn.setPreferredSize(new Dimension(119, 46));
        loginBtn.setFont(new Font(null, Font.PLAIN, 20));
        logPanel.add(loginBtn);


        try{
            File imageFile = new File("Images/userprofile2.png");
            BufferedImage cartImage = ImageIO.read(imageFile);
            homePanelUserProfilePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (cartImage != null) {
                        g.drawImage(cartImage, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };

            homePanelUserProfilePanel.setPreferredSize(new Dimension(50, 50));
            homePanelUserProfilePanel.setBackground(new Color(0x262626));
            homePanelUserProfilePanel.setVisible(false);
            logPanel.add(homePanelUserProfilePanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        JPanel homeBottomPanelTopbar = new JPanel();
        homeBottomPanelTopbar.setLayout(new BorderLayout());
        homeBottomPanelTopbar.setPreferredSize(new Dimension(1350, 90));
        homeBottomPanelTopbar.setBackground(new Color(0x303030));
        homeBottomPanelTopbar.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        homeBottomPanel.add(homeBottomPanelTopbar);

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        filterPanel.setPreferredSize(new Dimension(280, 0));
        filterPanel.setBackground(new Color(0x303030));
        homeBottomPanelTopbar.add(filterPanel, BorderLayout.WEST);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        filterPanelOption1 = new JPanel();
        filterPanelOption1.setPreferredSize(new Dimension(86, 44));
        filterPanelOption1.setBackground(new Color(0x000000));
        filterPanelOption1.setBorder(border);
        filterPanelOption1.setLayout(new BorderLayout());
        filterPanel.add(filterPanelOption1);

        filterPanelOption2 = new JPanel();
        filterPanelOption2.setPreferredSize(new Dimension(86, 44));
        filterPanelOption2.setBackground(Color.WHITE);
        filterPanelOption2.setBorder(border);
        filterPanelOption2.setLayout(new BorderLayout());
        filterPanel.add(filterPanelOption2);

        filterPanelOption3 = new JPanel();
        filterPanelOption3.setPreferredSize(new Dimension(86, 44));
        filterPanelOption3.setBackground(Color.WHITE);
        filterPanelOption3.setBorder(border);
        filterPanelOption3.setLayout(new BorderLayout());
        filterPanel.add(filterPanelOption3);

        allLabel = new JLabel("All");
        allLabel.setFont(new Font(null, Font.PLAIN, 17));
        allLabel.setForeground(Color.WHITE);
        allLabel.setBorder(new EmptyBorder(22, 35, 22, 0));

        deliveryLabel = new JLabel("Delivery");
        deliveryLabel.setFont(new Font(null, Font.PLAIN, 17));
        deliveryLabel.setForeground(Color.WHITE);
        deliveryLabel.setForeground(Color.BLACK);
        deliveryLabel.setBorder(new EmptyBorder(22, 12, 22, 0));

        pickupLabel = new JLabel("Pick Up");
        pickupLabel.setFont(new Font(null, Font.PLAIN, 17));
        pickupLabel.setForeground(Color.WHITE);
        pickupLabel.setForeground(Color.BLACK);
        pickupLabel.setBorder(new EmptyBorder(22, 12, 22, 0));

        filterPanelOption1.add(allLabel, BorderLayout.CENTER);
        filterPanelOption2.add(deliveryLabel, BorderLayout.CENTER);
        filterPanelOption3.add(pickupLabel, BorderLayout.CENTER);

        searchBarPanel = new JPanel();
        searchBarPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        searchBarPanel.setPreferredSize(new Dimension(500, 0));
        searchBarPanel.setBackground(new Color(0x303030));
        homeBottomPanelTopbar.add(searchBarPanel, BorderLayout.EAST);

        JPanel searchIconPanel = new JPanel();
        searchIconPanel.setPreferredSize(new Dimension(100, 44));
        searchIconPanel.setBackground(new Color(0x0A0A0A));
        searchBarPanel.add(searchIconPanel);

        ImageIcon searchIcon = new ImageIcon("Images/search.png");
        Image originalImage2 = searchIcon.getImage();
        Image resizedImage2 = originalImage2.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
        JLabel searchLabel = new JLabel(resizedIcon2);
        searchIconPanel.add(searchLabel);

        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(400, 44));
        searchField.setFont(new Font(null, Font.PLAIN, 20));
        searchField.setBorder(null);
        searchField.setBackground(new Color(0xD9D9D9));
        searchBarPanel.add(searchField);

        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(1350, 50));
        titlePanel.setBackground(new Color(0x303030));
        titlePanel.setLayout(new BorderLayout());
        homeBottomPanel.add(titlePanel);

        JLabel titleLabel = new JLabel("RESTAURANTS");
        titleLabel.setFont(new Font(null, Font.PLAIN, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(new EmptyBorder(10, 45, 10, 0));
        titlePanel.add(titleLabel, BorderLayout.WEST);

        //////////////

        restaurantsPanel = new JPanel();
        restaurantsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 37, 20));
        restaurantsPanel.setBackground(new Color(0x222222));
        restaurantsPanel.setPreferredSize(new Dimension(1320, 430));

        RestaurantsScrollPane = new JScrollPane(restaurantsPanel);
        RestaurantsScrollPane.setPreferredSize(new Dimension(1320, 430));
        RestaurantsScrollPane.setBorder(null);
        RestaurantsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        RestaurantsScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        homeBottomPanel.add(RestaurantsScrollPane);

        /////////

        layeredPane.add(homePanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(sideBarHome, JLayeredPane.PALETTE_LAYER);

        restSignupPanelContainer = new JPanel();
        restSignupPanelContainer.setBackground(new Color(0x535353));
        restSignupPanelContainer.setLayout(null);
        restSignupPanelContainer.setBounds(0, 0, 1500, 800);
        restSignupPanelContainer.setVisible(false);
        restSignupPanelContainer.setBorder(new EmptyBorder(20, 0, 0, 0));
        layeredPane.add(restSignupPanelContainer, JLayeredPane.PALETTE_LAYER);

        restSignupPanel = new JPanel();
        restSignupPanel.setBounds(150, 30, 540, 700);
        restSignupPanel.setBackground(new Color(0x2D2D2D));
        restSignupPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 10));
        restSignupPanelContainer.add(restSignupPanel);

        try {
            File imageFile = new File("Images/signup2.jpg");
            BufferedImage restBackBtnImage = ImageIO.read(imageFile);
            restSignUpContainerImagePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (restBackBtnImage != null) {
                        g.drawImage(restBackBtnImage, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            restSignUpContainerImagePanel.setBounds(690, 30, 650, 700);
            restSignUpContainerImagePanel.setLayout(null);
            restSignupPanelContainer.add(restSignUpContainerImagePanel);

            JLabel signUpFormAppNameLabel = new JLabel("FoodFlow");
            signUpFormAppNameLabel.setBounds(70, 290, 500, 110);
            signUpFormAppNameLabel.setFont(new Font(null, Font.BOLD,100));
            signUpFormAppNameLabel.setForeground(new Color(0x1035F4));
            restSignUpContainerImagePanel.add(signUpFormAppNameLabel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        JLabel storeNameLabel = new JLabel("Store name");
        storeNameLabel.setFont(new Font(null, Font.PLAIN, 18));
        storeNameLabel.setForeground(Color.WHITE);
        storeNameLabel.setPreferredSize(new Dimension(200, 30));
        restSignupPanel.add(storeNameLabel);

        storeNameTextField = new JTextField();
        storeNameTextField.setBorder(null);
        storeNameTextField.setFont(new Font(null, Font.PLAIN, 17));
        storeNameTextField.setPreferredSize(new Dimension(500, 40));
        storeNameTextField.setBackground(new Color(0x666666));
        restSignupPanel.add(storeNameTextField);

        JLabel storeBranchNameLabel = new JLabel("Store BranchName");
        storeBranchNameLabel.setFont(new Font(null, Font.PLAIN, 18));
        storeBranchNameLabel.setForeground(Color.WHITE);
        storeBranchNameLabel.setPreferredSize(new Dimension(200, 30));
        restSignupPanel.add(storeBranchNameLabel);

        storeBranchNameTextField = new JTextField();
        storeBranchNameTextField.setBorder(null);
        storeBranchNameTextField.setBackground(new Color(0x666666));
        storeBranchNameTextField.setPreferredSize(new Dimension(500, 40));
        restSignupPanel.add(storeBranchNameTextField);

        JLabel storeEmailLabel = new JLabel("Email");
        storeEmailLabel.setFont(new Font(null, Font.PLAIN, 18));
        storeEmailLabel.setForeground(Color.WHITE);
        storeEmailLabel.setPreferredSize(new Dimension(200, 30));
        restSignupPanel.add(storeEmailLabel);

        storeEmailTextField = new JTextField();
        storeEmailTextField.setBorder(null);
        storeEmailTextField.setBackground(new Color(0x666666));
        storeEmailTextField.setPreferredSize(new Dimension(500, 40));
        restSignupPanel.add(storeEmailTextField);

        JLabel storeMobileLabel = new JLabel("Phone number");
        storeMobileLabel.setFont(new Font(null, Font.PLAIN, 18));
        storeMobileLabel.setForeground(Color.WHITE);
        storeMobileLabel.setPreferredSize(new Dimension(200, 30));
        restSignupPanel.add(storeMobileLabel);

        storeMobileTextField = new JTextField();
        storeMobileTextField.setBackground(new Color(0x666666));
        storeMobileTextField.setBorder(null);
        storeMobileTextField.setPreferredSize(new Dimension(500, 40));
        restSignupPanel.add(storeMobileTextField);

        JLabel storeCityLabel = new JLabel("City");
        storeCityLabel.setFont(new Font(null, Font.PLAIN, 18));
        storeCityLabel.setForeground(Color.WHITE);
        storeCityLabel.setPreferredSize(new Dimension(200, 30));
        restSignupPanel.add(storeCityLabel);

        storeCityTextField = new JTextField();
        storeCityTextField.setBackground(new Color(0x666666));
        storeCityTextField.setBorder(null);
        storeCityTextField.setPreferredSize(new Dimension(500, 40));
        restSignupPanel.add(storeCityTextField);

        JLabel storePostalCodeLabel = new JLabel("Postal Code");
        storePostalCodeLabel.setFont(new Font(null, Font.PLAIN, 18));
        storePostalCodeLabel.setForeground(Color.WHITE);
        storePostalCodeLabel.setPreferredSize(new Dimension(200, 30));
        restSignupPanel.add(storePostalCodeLabel);

        storePostalCodeTextField = new JTextField();
        storePostalCodeTextField.setBackground(new Color(0x666666));
        storePostalCodeTextField.setBorder(null);
        storePostalCodeTextField.setPreferredSize(new Dimension(500, 40));
        restSignupPanel.add(storePostalCodeTextField);

        JLabel storeAddressLabel = new JLabel("Store Address");
        storeAddressLabel.setFont(new Font(null, Font.PLAIN, 18));
        storeAddressLabel.setForeground(Color.WHITE);
        storeAddressLabel.setPreferredSize(new Dimension(200, 30));
        restSignupPanel.add(storeAddressLabel);

        storeAddressTextField = new JTextField();
        storeAddressTextField.setBackground(new Color(0x666666));
        storeAddressTextField.setBorder(null);
        storeAddressTextField.setPreferredSize(new Dimension(500, 40));
        restSignupPanel.add(storeAddressTextField);

        restSignupSubmitBtn = new JButton("SUBMIT");
        restSignupSubmitBtn.setPreferredSize(new Dimension(500, 40));
        restSignupSubmitBtn.setFocusable(false);
        restSignupSubmitBtn.setBackground(new Color(0x1035F4));
        restSignupSubmitBtn.setForeground(Color.WHITE);
        restSignupPanel.add(restSignupSubmitBtn);

        restSignUpCloseBtn = new JButton("X");
        restSignUpCloseBtn.setBounds(1450, 10, 30, 30);
        restSignUpCloseBtn.setBorder(null);
        restSignUpCloseBtn.setBackground(Color.BLACK);
        restSignUpCloseBtn.setForeground(Color.WHITE);
        restSignUpCloseBtn.setFocusable(false);
        restSignupPanelContainer.add(restSignUpCloseBtn);

        emailConfirmPanel = new JPanel();
        emailConfirmPanel.setBounds(350, 40, 500, 500);
        emailConfirmPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
        emailConfirmPanel.setBackground(Color.gray);
        emailConfirmPanel.setVisible(false);
        restSignupPanelContainer.add(emailConfirmPanel);

        JLabel emailConfirmLabel = new JLabel("Confirm your email address");
        emailConfirmLabel.setFont(new Font(null, Font.BOLD, 28));
        emailConfirmLabel.setPreferredSize(new Dimension(400, 40));
        emailConfirmPanel.add(emailConfirmLabel);

        emailConfirmMsgLabel = new JLabel();
        emailConfirmMsgLabel.setHorizontalAlignment(JLabel.CENTER);
        emailConfirmPanel.add(emailConfirmMsgLabel);

        emailConfirmCodeTextField = new JTextField();
        emailConfirmCodeTextField.setPreferredSize(new Dimension(200, 40));
        emailConfirmPanel.add(emailConfirmCodeTextField);

        emailConfirmBtn = new JButton("Confirm");
        emailConfirmBtn.setPreferredSize(new Dimension(200, 40));
        emailConfirmPanel.add(emailConfirmBtn);






        ///////////////////////
        restaurantPanel = new JPanel();
        restaurantPanel.setBounds(0, 0, 1500, 800);
        restaurantPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        restaurantPanel.setVisible(false);
        layeredPane.add(restaurantPanel, JLayeredPane.DEFAULT_LAYER);

        JPanel restaurantTopPanel = new JPanel();
        restaurantTopPanel.setPreferredSize(new Dimension(1500, 95));
        restaurantTopPanel.setBackground(new Color(0x262626));
        restaurantTopPanel.setLayout(null);
        restaurantPanel.add(restaurantTopPanel);

        try {
            File imageFile = new File("Images/arrowBack.png");
            BufferedImage restBackBtnImage = ImageIO.read(imageFile);
            restaurantBackBtnPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (restBackBtnImage != null) {
                        g.drawImage(restBackBtnImage, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };

            restaurantBackBtnPanel.setBounds(15, 20, 50, 50);
            restaurantBackBtnPanel.setBackground(new Color(0x262626));
            restaurantTopPanel.add(restaurantBackBtnPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        JLabel restPanelTopLogoLbl = new JLabel("LOGO");
        restPanelTopLogoLbl.setFont(new Font(null, Font.BOLD, 40));
        restPanelTopLogoLbl.setBounds(650, 20, 200, 50);
        restPanelTopLogoLbl.setForeground(Color.WHITE);
        restaurantTopPanel.add(restPanelTopLogoLbl);

        cartBtnPanel = new JPanel();
        cartBtnPanel.setBounds(1200, 25, 120, 45);
        cartBtnPanel.setLayout(null);
        cartBtnPanel.setBackground(Color.BLACK);
        cartBtnPanel.setBorder(BorderFactory.createLineBorder(new Color(0x555555)));
        restaurantTopPanel.add(cartBtnPanel);

        restaurantPanelLoginBtn = new JButton("LOG IN");
        restaurantPanelLoginBtn.setFocusable(false);
        restaurantPanelLoginBtn.setBackground(new Color(0x686262));
        restaurantPanelLoginBtn.setForeground(Color.WHITE);
        restaurantPanelLoginBtn.setBorder(null);
        restaurantPanelLoginBtn.setVisible(true);
        restaurantPanelLoginBtn.setBounds(1340, 25, 119, 45);
        restaurantPanelLoginBtn.setFont(new Font(null, Font.PLAIN, 20));
        restaurantTopPanel.add(restaurantPanelLoginBtn);

        try{
            File imageFile = new File("Images/userprofile2.png");
            BufferedImage cartImage = ImageIO.read(imageFile);
            restaurantPanelUserProfilePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (cartImage != null) {
                        g.drawImage(cartImage, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };

            restaurantPanelUserProfilePanel.setBackground(new Color(0x262626));
            restaurantPanelUserProfilePanel.setVisible(false);
            restaurantPanelUserProfilePanel.setBounds(1340, 20, 50, 50);
            restaurantTopPanel.add(restaurantPanelUserProfilePanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        try {
            File imageFile = new File("Images/cart.png");
            BufferedImage cartImage = ImageIO.read(imageFile);
            JPanel cartLogoPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (cartImage != null) {
                        g.drawImage(cartImage, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };

            cartLogoPanel.setBounds(15, 7, 40, 30);
            cartLogoPanel.setBackground(Color.BLACK);
            cartBtnPanel.add(cartLogoPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        JLabel cartLabel = new JLabel("CART");
        cartLabel.setBounds(70, 0, 40, 45);
        cartLabel.setForeground(Color.WHITE);
        cartBtnPanel.add(cartLabel);

        JPanel restaurantBottomPanel = new JPanel();
        restaurantBottomPanel.setPreferredSize(new Dimension(1500, 705));
//        restaurantBottomPanel.setBackground(Color.RED);
        restaurantBottomPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        restaurantPanel.add(restaurantBottomPanel);

        JPanel restaurantSideBarPanel = new JPanel();
        restaurantSideBarPanel.setPreferredSize(new Dimension(258, 705));
        restaurantSideBarPanel.setBackground(new Color(0x353535));
        restaurantSideBarPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        restaurantBottomPanel.add(restaurantSideBarPanel);

        try{
            File imageFile = new File("Images/hamburger-menu.png");
            BufferedImage cartImage = ImageIO.read(imageFile);
            restMenuIconBtn = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (cartImage != null) {
                        g.drawImage(cartImage, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };

            restMenuIconBtn.setPreferredSize(new Dimension(60, 60));
            restMenuIconBtn.setBackground(new Color(0x353535));
            restaurantSideBarPanel.add(restMenuIconBtn);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        //.....
        restaurantMenuPanel = new JPanel();
        restaurantMenuPanel.setPreferredSize(new Dimension(240, 555));
        restaurantMenuPanel.setBackground(new Color(0x3F3F3F));
        restaurantMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        JScrollPane restaurantMenuPanelScrollPane = new JScrollPane(restaurantMenuPanel);
        restaurantMenuPanelScrollPane.setPreferredSize(new Dimension(240, 560));
        restaurantMenuPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        restaurantMenuPanelScrollPane.setBorder(null);
        restaurantSideBarPanel.add(restaurantMenuPanelScrollPane);

        //.....

        JPanel restBottomRightContainerPanel = new JPanel();
        restBottomRightContainerPanel.setPreferredSize(new Dimension(1228, 705));
        restBottomRightContainerPanel.setBackground(new Color(0x212121));
        restBottomRightContainerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        restaurantBottomPanel.add(restBottomRightContainerPanel);

        restNamePanel = new JPanel();
        restNamePanel.setPreferredSize(new Dimension(1170, 60));
        restNamePanel.setLayout(null);
        restNamePanel.setBackground(new Color(0x212121));
        restBottomRightContainerPanel.add(restNamePanel);

        restNameLabel = new JLabel("KFC");
        restNameLabel.setBounds(0, 5, 500, 50);
        restNameLabel.setForeground(Color.WHITE);
        restNameLabel.setFont(new Font(null, Font.BOLD, 40));
        restNamePanel.add(restNameLabel);

        restOpenStatusPanel = new JPanel();
        restOpenStatusPanel.setBounds(1102, 15, 68, 26);
        restOpenStatusPanel.setLayout(null);
        restOpenStatusPanel.setBackground(new Color(0x2CA30E));
        restNamePanel.add(restOpenStatusPanel);

        restOpenLabel = new JLabel("open");
        restOpenLabel.setForeground(Color.WHITE);
        restOpenLabel.setFont(new Font(null, Font.PLAIN, 15));
        restOpenLabel.setBounds(0, 0, 68, 26);
        restOpenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        restOpenStatusPanel.add(restOpenLabel);



        /////////////////menuItemsPanel////////////////////////////
        restMenuItemsPanel = new JPanel();
        restMenuItemsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 10));
        restMenuItemsPanel.setPreferredSize(new Dimension(1170, 565));
        restMenuItemsPanel.setBackground(new Color(0x373737));

        RestMenuItemsScrollPane = new JScrollPane(restMenuItemsPanel);
        RestMenuItemsScrollPane.setPreferredSize(new Dimension(1170, 570));
        RestMenuItemsScrollPane.setBorder(null);
        RestMenuItemsScrollPane.setBackground(new Color(0x373737));
        RestMenuItemsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        restBottomRightContainerPanel.add(RestMenuItemsScrollPane);


        //////////////////..............

        restaurantMyFeaturesPanel = new JPanel();
        restaurantMyFeaturesPanel.setBounds(0, 180, 258, 585);
        restaurantMyFeaturesPanel.setVisible(false);
        layeredPane.add(restaurantMyFeaturesPanel, JLayeredPane.PALETTE_LAYER);
        ////////////





        //////register/login form///////
//        userLogSignupContainer = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.setColor(new Color(255, 0, 0, 150));
//                g.fillRect(0, 0, getWidth(), getHeight());
//            }
//        };
//        userLogSignupContainer.setOpaque(false);
//        userLogSignupContainer.setBounds(0, 0, 1500, 800);
//        userLogSignupContainer.setVisible(false);
//        userLogSignupContainer.setLayout(null);
//        userLogSignupContainer.setBackground(new Color(0x636363));
//        layeredPane.add(userLogSignupContainer, JLayeredPane.PALETTE_LAYER);

        userLogSignupContainer = new JPanel();
        userLogSignupContainer.setBounds(0, 0, 1500, 800);
        userLogSignupContainer.setVisible(false);
        userLogSignupContainer.setLayout(null);
        userLogSignupContainer.setBackground(Color.BLACK);
        layeredPane.add(userLogSignupContainer, JLayeredPane.PALETTE_LAYER);

        logSignUpFormPanel = new JPanel();
        logSignUpFormPanel.setBounds(120, 70, 1246, 643);
        logSignUpFormPanel.setBackground(new Color(0x505050));
        logSignUpFormPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        userLogSignupContainer.add(logSignUpFormPanel);

        registerFormPanelLeft = new JPanel();
        registerFormPanelLeft.setPreferredSize(new Dimension(569, 643));
        registerFormPanelLeft.setBackground(new Color(0x434343));
        registerFormPanelLeft.setLayout(null);
        registerFormPanelLeft.setVisible(false);
        logSignUpFormPanel.add(registerFormPanelLeft);

        ////////
        loginFormPanelLeft = new JPanel();
        loginFormPanelLeft.setPreferredSize(new Dimension(569, 643));
        loginFormPanelLeft.setBackground(new Color(0x434343));
        loginFormPanelLeft.setLayout(null);
        loginFormPanelLeft.setVisible(true);
        logSignUpFormPanel.add(loginFormPanelLeft);




        customerEmailConfirmPanel = new JPanel();
        customerEmailConfirmPanel.setPreferredSize(new Dimension(569, 643));
        customerEmailConfirmPanel.setBackground(new Color(0x434343));
        customerEmailConfirmPanel.setLayout(null);
        customerEmailConfirmPanel.setVisible(false);
        logSignUpFormPanel.add(customerEmailConfirmPanel);

        customerEmailConfirmCodeTxt = new JTextField();
        customerEmailConfirmCodeTxt.setBounds(60, 230, 433, 50);
        customerEmailConfirmPanel.add(customerEmailConfirmCodeTxt);

        customerEmailConfirmBtn = new JButton("Confirm");
        customerEmailConfirmBtn.setBounds(160, 320, 200, 40);
        customerEmailConfirmBtn.setFont(new Font(null, Font.BOLD, 20));
        customerEmailConfirmBtn.setBackground(Color.GREEN);
        customerEmailConfirmBtn.setFocusable(false);
        customerEmailConfirmBtn.setForeground(Color.WHITE);
        customerEmailConfirmPanel.add(customerEmailConfirmBtn);



        JLabel loginFormLogoLbl = new JLabel("LOGO");
        loginFormLogoLbl.setFont(new Font(null, Font.BOLD, 48));
        loginFormLogoLbl.setBounds(200, 40, 200, 50);
        loginFormLogoLbl.setForeground(Color.WHITE);
        loginFormPanelLeft.add(loginFormLogoLbl);

        JLabel loginFormSignInLbl = new JLabel("Sign In");
        loginFormSignInLbl.setFont(new Font(null, Font.PLAIN, 25));
        loginFormSignInLbl.setBounds(240, 110, 200, 40);
        loginFormSignInLbl.setForeground(Color.WHITE);
        loginFormPanelLeft.add(loginFormSignInLbl);

        JLabel loginFormEmailLbl = new JLabel("Email Address");
        loginFormEmailLbl.setFont(new Font(null, Font.PLAIN, 16));
        loginFormEmailLbl.setForeground(Color.WHITE);
        loginFormEmailLbl.setBounds(60, 200, 200, 20);
        loginFormPanelLeft.add(loginFormEmailLbl);

        loginFormEmailTxt = new JTextField("chathukaraveesha12@gmail.com");
        loginFormEmailTxt.setBounds(60, 230, 433, 50);
        loginFormPanelLeft.add(loginFormEmailTxt);

        JLabel loginFormPwdLbl = new JLabel("Password");
        loginFormPwdLbl.setFont(new Font(null, Font.PLAIN, 16));
        loginFormPwdLbl.setForeground(Color.WHITE);
        loginFormPwdLbl.setBounds(60, 320, 200, 20);
        loginFormPanelLeft.add(loginFormPwdLbl);

        loginFormPwdTxt = new JTextField("2345");
        loginFormPwdTxt.setBounds(60, 350, 433, 50);
        loginFormPanelLeft.add(loginFormPwdTxt);

        JLabel forgotPwdLbl = new JLabel("Forgot Password?");
        forgotPwdLbl.setFont(new Font(null, Font.PLAIN, 14));
        forgotPwdLbl.setForeground(new Color(0xC0C0C0));
        forgotPwdLbl.setBounds(370, 410, 200, 20);
        loginFormPanelLeft.add(forgotPwdLbl);

        loginFormLoginBtn = new JButton("Log In");
        loginFormLoginBtn.setFont(new Font(null, Font.BOLD, 16));
        loginFormLoginBtn.setBackground(new Color(0x4B9934));
        loginFormLoginBtn.setForeground(Color.WHITE);
        loginFormLoginBtn.setBounds(160, 480, 220, 46);
        loginFormPanelLeft.add(loginFormLoginBtn);

        JLabel dontHaveAccountLbl = new JLabel("Don't have an account?");
        dontHaveAccountLbl.setFont(new Font(null, Font.PLAIN, 14));
        dontHaveAccountLbl.setForeground(Color.WHITE);
        dontHaveAccountLbl.setBounds(60, 580, 170, 30);
        loginFormPanelLeft.add(dontHaveAccountLbl);

        loginFormSignUpLbl = new JLabel("Sign Up");
        loginFormSignUpLbl.setFont(new Font(null, Font.BOLD, 16));
        loginFormSignUpLbl.setForeground(new Color(0x4B9934));
        loginFormSignUpLbl.setBounds(220, 580, 100, 30);
        loginFormPanelLeft.add(loginFormSignUpLbl);

        //////////

        JLabel registerFormLogoLbl = new JLabel("LOGO");
        registerFormLogoLbl.setFont(new Font(null, Font.BOLD, 48));
        registerFormLogoLbl.setBounds(200, 20, 200, 50);
        registerFormLogoLbl.setForeground(Color.WHITE);
        registerFormPanelLeft.add(registerFormLogoLbl);

        JLabel registerPanelSignUpLbl = new JLabel("Sign Up");
        registerPanelSignUpLbl.setFont(new Font(null, Font.PLAIN, 25));
        registerPanelSignUpLbl.setBounds(220, 90, 200, 40);
        registerPanelSignUpLbl.setForeground(Color.WHITE);
        registerFormPanelLeft.add(registerPanelSignUpLbl);

        JLabel registerFormUsernameLbl = new JLabel("Username");
        registerFormUsernameLbl.setFont(new Font(null, Font.PLAIN, 16));
        registerFormUsernameLbl.setBounds(60, 160, 200, 20);
        registerFormUsernameLbl.setForeground(Color.WHITE);
        registerFormPanelLeft.add(registerFormUsernameLbl);

        usernameTxt = new JTextField();
        usernameTxt.setBounds(60, 185, 433, 50);
        registerFormPanelLeft.add(usernameTxt);

        JLabel registerFormEmailLbl = new JLabel("Email Address");
        registerFormEmailLbl.setFont(new Font(null, Font.PLAIN, 16));
        registerFormEmailLbl.setForeground(Color.WHITE);
        registerFormEmailLbl.setBounds(60, 255, 200, 20);
        registerFormPanelLeft.add(registerFormEmailLbl);

        registerFormEmailTxt = new JTextField();
        registerFormEmailTxt.setBounds(60, 280, 433, 50);
        registerFormPanelLeft.add(registerFormEmailTxt);

        JLabel registerFormPwdLbl = new JLabel("Password");
        registerFormPwdLbl.setFont(new Font(null, Font.PLAIN, 16));
        registerFormPwdLbl.setForeground(Color.WHITE);
        registerFormPwdLbl.setBounds(60, 350, 200, 20);
        registerFormPanelLeft.add(registerFormPwdLbl);

        registerFormPwdTxt = new JTextField();
        registerFormPwdTxt.setBounds(60, 375, 433, 50);
        registerFormPanelLeft.add(registerFormPwdTxt);

        JLabel registerFormConfirmPwdLbl = new JLabel("Confirm Password");
        registerFormConfirmPwdLbl.setFont(new Font(null, Font.PLAIN, 16));
        registerFormConfirmPwdLbl.setForeground(Color.WHITE);
        registerFormConfirmPwdLbl.setBounds(60, 445, 200, 20);
        registerFormPanelLeft.add(registerFormConfirmPwdLbl);

        registerFormConfirmPwdTxt = new JTextField();
        registerFormConfirmPwdTxt.setBounds(60, 470, 433, 50);
        registerFormPanelLeft.add(registerFormConfirmPwdTxt);

        registerFormSignUpBtn = new JButton("Sign Up");
        registerFormSignUpBtn.setFont(new Font(null, Font.BOLD, 16));
        registerFormSignUpBtn.setBackground(new Color(0x4B9934));
        registerFormSignUpBtn.setForeground(Color.WHITE);
        registerFormSignUpBtn.setBounds(160, 540, 220, 46);
        registerFormPanelLeft.add(registerFormSignUpBtn);

        JLabel haveAccountLbl = new JLabel("Already have an account?");
        haveAccountLbl.setFont(new Font(null, Font.PLAIN, 14));
        haveAccountLbl.setForeground(Color.WHITE);
        haveAccountLbl.setBounds(60, 600, 170, 30);
        registerFormPanelLeft.add(haveAccountLbl);

        registerFormLoginLbl = new JLabel("Log in");
        registerFormLoginLbl.setFont(new Font(null, Font.BOLD, 16));
        registerFormLoginLbl.setForeground(new Color(0x4B9934));
        registerFormLoginLbl.setBounds(240, 600, 100, 30);
        registerFormPanelLeft.add(registerFormLoginLbl);

        try{
            File imageFile = new File("Images/loginFormImage.jpg");
            BufferedImage registerLoginFormImage = ImageIO.read(imageFile);
            JPanel registerFormRight = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (registerLoginFormImage != null) {
                        g.drawImage(registerLoginFormImage, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };

            registerFormRight.setPreferredSize(new Dimension(677, 643));
            registerFormRight.setLayout(null);
            logSignUpFormPanel.add(registerFormRight);

            registerFormCloseBtn = new JLabel("X");
            registerFormCloseBtn.setBounds(600, 10, 50, 50);
            registerFormCloseBtn.setFont(new Font(null, Font.BOLD, 20));
            registerFormCloseBtn.setForeground(Color.WHITE);
            registerFormRight.add(registerFormCloseBtn);
        }catch(IOException ex){
            ex.printStackTrace();
        }



        ///////////sign up to delivery/////////////////////
        deliverySignUpContainer = new JPanel();
        deliverySignUpContainer.setBounds(0, 0, 1500, 800);
        deliverySignUpContainer.setLayout(null);
        deliverySignUpContainer.setBackground(new Color(0x636363));
        deliverySignUpContainer.setVisible(false);
        layeredPane.add(deliverySignUpContainer, JLayeredPane.PALETTE_LAYER);

        deliverySignUpContainerCloseLbl = new JLabel("X");
        deliverySignUpContainerCloseLbl.setBounds(1420, 20, 30, 30);
        deliverySignUpContainerCloseLbl.setFont(new Font(null, Font.BOLD, 15));
        deliverySignUpContainerCloseLbl.setForeground(Color.WHITE);
        deliverySignUpContainer.add(deliverySignUpContainerCloseLbl);

        deliverySignUpPanel = new JPanel();
        deliverySignUpPanel.setBounds(100, 25, 572, 720);
        deliverySignUpPanel.setBackground(new Color(0x434343));
        deliverySignUpPanel.setLayout(null);
        deliverySignUpPanel.setVisible(true);
        deliverySignUpContainer.add(deliverySignUpPanel);

        JLabel deliverySignUpPanelLogoLbl = new JLabel("LOGO");
        deliverySignUpPanelLogoLbl.setFont(new Font(null, Font.BOLD, 48));
        deliverySignUpPanelLogoLbl.setBounds(200, 20, 200, 40);
        deliverySignUpPanelLogoLbl.setForeground(Color.WHITE);
        deliverySignUpPanel.add(deliverySignUpPanelLogoLbl);

        JLabel deliverySignUpTopLabel = new JLabel("Sign up as a delivery driver");
        deliverySignUpTopLabel.setFont(new Font(null, Font.PLAIN, 20));
        deliverySignUpTopLabel.setBounds(150, 70, 300, 30);
        deliverySignUpTopLabel.setForeground(Color.WHITE);
        deliverySignUpPanel.add(deliverySignUpTopLabel);

        JLabel deliveryUsernameLbl = new JLabel("Username");
        deliveryUsernameLbl.setFont(new Font(null, Font.PLAIN, 16));
        deliveryUsernameLbl.setForeground(Color.WHITE);
        deliveryUsernameLbl.setBounds(80, 120, 100, 20);
        deliverySignUpPanel.add(deliveryUsernameLbl);

        deliveryUsernameTxt = new JTextField();
        deliveryUsernameTxt.setBounds(80, 145, 433, 40);
        deliverySignUpPanel.add(deliveryUsernameTxt);

        JLabel deliveryEmailLbl = new JLabel("Email Address");
        deliveryEmailLbl.setFont(new Font(null, Font.PLAIN, 16));
        deliveryEmailLbl.setForeground(Color.WHITE);
        deliveryEmailLbl.setBounds(80, 195, 150, 20);
        deliverySignUpPanel.add(deliveryEmailLbl);

        deliveryEmailTxt = new JTextField();
        deliveryEmailTxt.setBounds(80, 220, 433, 40);
        deliverySignUpPanel.add(deliveryEmailTxt);

        JLabel deliveryNicLbl = new JLabel("NIC number");
        deliveryNicLbl.setFont(new Font(null, Font.PLAIN, 16));
        deliveryNicLbl.setForeground(Color.WHITE);
        deliveryNicLbl.setBounds(80, 270, 150, 20);
        deliverySignUpPanel.add(deliveryNicLbl);

        deliveryNicTxt = new JTextField();
        deliveryNicTxt.setBounds(80, 295, 433, 40);
        deliverySignUpPanel.add(deliveryNicTxt);

        JLabel deliveryVehicleTypeLbl = new JLabel("Vehicle type");
        deliveryVehicleTypeLbl.setFont(new Font(null, Font.PLAIN, 16));
        deliveryVehicleTypeLbl.setForeground(Color.WHITE);
        deliveryVehicleTypeLbl.setBounds(80, 345, 150, 20);
        deliverySignUpPanel.add(deliveryVehicleTypeLbl);

        String[] options = {"Select your vehicle type", "Motor Bike", "Car", "Three wheeler"};
        deliveryVehicleTypeComboBox = new JComboBox<>(options);
        deliveryVehicleTypeComboBox.setBounds(80, 370, 433, 40);
        deliverySignUpPanel.add(deliveryVehicleTypeComboBox);

        JLabel deliveryVehicleNumberLbl = new JLabel("Vehicle number");
        deliveryVehicleNumberLbl.setFont(new Font(null, Font.PLAIN, 16));
        deliveryVehicleNumberLbl.setForeground(Color.WHITE);
        deliveryVehicleNumberLbl.setBounds(80, 420, 150, 20);
        deliverySignUpPanel.add(deliveryVehicleNumberLbl);

        deliveryVehicleNumberTxt = new JTextField();
        deliveryVehicleNumberTxt.setBounds(80, 445, 433, 40);
        deliverySignUpPanel.add(deliveryVehicleNumberTxt);

        JLabel deliveryFullNameLbl = new JLabel("Full name");
        deliveryFullNameLbl.setFont(new Font(null, Font.PLAIN, 16));
        deliveryFullNameLbl.setForeground(Color.WHITE);
        deliveryFullNameLbl.setBounds(80, 495, 150, 20);
        deliverySignUpPanel.add(deliveryFullNameLbl);

        deliveryFullNameTxt = new JTextField();
        deliveryFullNameTxt.setBounds(80, 520, 433, 40);
        deliverySignUpPanel.add(deliveryFullNameTxt);

        JLabel deliveryTelLbl = new JLabel("Mobile number");
        deliveryTelLbl.setFont(new Font(null, Font.PLAIN, 16));
        deliveryTelLbl.setForeground(Color.WHITE);
        deliveryTelLbl.setBounds(80, 570, 150, 20);
        deliverySignUpPanel.add(deliveryTelLbl);

        deliveryTelTxt = new JTextField();
        deliveryTelTxt.setBounds(80, 595, 433, 40);
        deliverySignUpPanel.add(deliveryTelTxt);


        deliverySignUpBtn = new JButton("Sign Up");
        deliverySignUpBtn.setBounds(170, 650, 220, 46);
        deliverySignUpBtn.setForeground(Color.WHITE);
        deliverySignUpBtn.setFont(new Font(null, Font.PLAIN, 16));
        deliverySignUpBtn.setFocusable(false);
        deliverySignUpBtn.setBackground(new Color(0x4B9934));
        deliverySignUpPanel.add(deliverySignUpBtn);


        try {
            File imageFile = new File("Images/delivery.jpg");
            BufferedImage registerLoginFormImage = ImageIO.read(imageFile);
            JPanel deliverySignUpImagePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (registerLoginFormImage != null) {
                        g.drawImage(registerLoginFormImage, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            deliverySignUpImagePanel.setBounds(672, 25, 700, 720);
            deliverySignUpContainer.add(deliverySignUpImagePanel);

        }catch(IOException ex){
            ex.printStackTrace();
        }

        deliverySignUpEmailConfirmPanel = new JPanel();
        deliverySignUpEmailConfirmPanel.setBounds(100, 25, 572, 720);
        deliverySignUpEmailConfirmPanel.setBackground(new Color(0x434343));
        deliverySignUpEmailConfirmPanel.setLayout(null);
        deliverySignUpEmailConfirmPanel.setVisible(false);
        deliverySignUpContainer.add(deliverySignUpEmailConfirmPanel);

        deliverySignUpEmailConfirmPanelBackBtn = new JPanel();
        deliverySignUpEmailConfirmPanelBackBtn.setBounds(10, 10, 30, 30);
        deliverySignUpEmailConfirmPanelBackBtn.setBackground(Color.BLACK);
        deliverySignUpEmailConfirmPanelBackBtn.setLayout(null);
        deliverySignUpEmailConfirmPanel.add(deliverySignUpEmailConfirmPanelBackBtn);

        JLabel deliverySignUpEmailConfirmPanelBacklabel = new JLabel("<");
        deliverySignUpEmailConfirmPanelBacklabel.setBounds(0, 0, 30, 30);
        deliverySignUpEmailConfirmPanelBacklabel.setForeground(Color.WHITE);
        deliverySignUpEmailConfirmPanelBacklabel.setHorizontalAlignment(SwingConstants.CENTER);
        deliverySignUpEmailConfirmPanelBacklabel.setFont(new Font(null, Font.BOLD, 20));
        deliverySignUpEmailConfirmPanelBackBtn.add(deliverySignUpEmailConfirmPanelBacklabel);

        JLabel deliverySignUpEmailConfirmPanelLogoLbl = new JLabel("LOGO");
        deliverySignUpEmailConfirmPanelLogoLbl.setFont(new Font(null, Font.BOLD, 48));
        deliverySignUpEmailConfirmPanelLogoLbl.setBounds(200, 20, 200, 40);
        deliverySignUpEmailConfirmPanelLogoLbl.setForeground(Color.WHITE);
        deliverySignUpEmailConfirmPanel.add(deliverySignUpEmailConfirmPanelLogoLbl);

        JLabel deliverySignUpEmailConfirmPanelTopLabel = new JLabel("Sign up as a delivery driver");
        deliverySignUpEmailConfirmPanelTopLabel.setFont(new Font(null, Font.PLAIN, 20));
        deliverySignUpEmailConfirmPanelTopLabel.setBounds(150, 70, 300, 30);
        deliverySignUpEmailConfirmPanelTopLabel.setForeground(Color.WHITE);
        deliverySignUpEmailConfirmPanel.add(deliverySignUpEmailConfirmPanelTopLabel);

        JLabel deliveryConfirmEmailLabel = new JLabel("Enter the email confirmation code we sent to hirunadilmith15@gmail.com");
        deliveryConfirmEmailLabel.setFont(new Font(null, Font.PLAIN, 16));
        deliveryConfirmEmailLabel.setForeground(Color.WHITE);
        deliveryConfirmEmailLabel.setBounds(50, 270, 600, 50);
        deliverySignUpEmailConfirmPanel.add(deliveryConfirmEmailLabel);

        deliveryConfirmEmailTxt = new JTextField();
        deliveryConfirmEmailTxt.setBounds(140, 330, 250, 40);
        deliverySignUpEmailConfirmPanel.add(deliveryConfirmEmailTxt);

        deliveryEmailConfirmBtn = new JButton("Confirm");
        deliveryEmailConfirmBtn.setBounds(160, 390, 220, 46);
        deliveryEmailConfirmBtn.setForeground(Color.WHITE);
        deliveryEmailConfirmBtn.setFont(new Font(null, Font.PLAIN, 16));
        deliveryEmailConfirmBtn.setFocusable(false);
        deliveryEmailConfirmBtn.setBackground(new Color(0x4B9934));
        deliverySignUpEmailConfirmPanel.add(deliveryEmailConfirmBtn);


        ///////////////////////





        ////////add to order container///////////

        addToOrderPanel = new JPanel();
        addToOrderPanel.setBounds(170, 140, 1200, 567);
        addToOrderPanel.setBackground(new Color(0x484848));
        addToOrderPanel.setLayout(null);
        addToOrderPanel.setVisible(false);
        layeredPane.add(addToOrderPanel, JLayeredPane.PALETTE_LAYER);

        addToOrderLeftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (openFoodItemImage != null) {
                    g.drawImage(openFoodItemImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        addToOrderLeftPanel.setBounds(10, 10, 559, 548);
        addToOrderPanel.add(addToOrderLeftPanel);

        JPanel addToOrderRightPanel = new JPanel();
        addToOrderRightPanel.setBounds(580, 0, 620, 567);
        addToOrderRightPanel.setBackground(new Color(0x484848));
        addToOrderRightPanel.setLayout(null);
        addToOrderPanel.add(addToOrderRightPanel);

        addToOrderPanelCloseBtn = new JButton("X");
        addToOrderPanelCloseBtn.setBounds(580, 10, 20, 20);
        addToOrderPanelCloseBtn.setFocusable(false);
        addToOrderPanelCloseBtn.setBackground(Color.BLACK);
        addToOrderPanelCloseBtn.setForeground(Color.WHITE);
        addToOrderPanelCloseBtn.setBorder(null);
        addToOrderPanelCloseBtn.setFont(new Font(null, Font.BOLD, 18));
        addToOrderRightPanel.add(addToOrderPanelCloseBtn);

        orderItemNameLbl = new JLabel();
        orderItemNameLbl.setText("Chicken Kottu");
        orderItemNameLbl.setFont(new Font(null, Font.BOLD, 40));
        orderItemNameLbl.setForeground(Color.WHITE);
        orderItemNameLbl.setBounds(20, 20, 600, 50);
        addToOrderRightPanel.add(orderItemNameLbl);

        orderItemPriceLbl = new JLabel();
        orderItemPriceLbl.setText("LKR 1200");
        orderItemPriceLbl.setFont(new Font(null, Font.BOLD, 22));
        orderItemPriceLbl.setForeground(Color.WHITE);
        orderItemPriceLbl.setBounds(25, 75, 500, 40);
        addToOrderRightPanel.add(orderItemPriceLbl);

        orderItemDescriptionTextArea = new JTextArea();
        orderItemDescriptionTextArea.setText("Crispy Chicken Drumlets that is tender and juicy topped with  hot and spicy coating");
        orderItemDescriptionTextArea.setBounds(25, 125, 500, 60);
        orderItemDescriptionTextArea.setFont(new Font(null, Font.PLAIN, 14));
        orderItemDescriptionTextArea.setLineWrap(true);
        orderItemDescriptionTextArea.setWrapStyleWord(true);
        orderItemDescriptionTextArea.setEditable(false);
        orderItemDescriptionTextArea.setFocusable(false);
        orderItemDescriptionTextArea.setForeground(Color.WHITE);
        orderItemDescriptionTextArea.setBackground(null);
        addToOrderRightPanel.add(orderItemDescriptionTextArea);

        orderItemAddQntMinusBtn = new JButton("-");
        orderItemAddQntMinusBtn.setBounds(25, 210, 26, 26);
        orderItemAddQntMinusBtn.setFont(new Font(null, Font.BOLD, 22));
        orderItemAddQntMinusBtn.setBackground(Color.BLACK);
        orderItemAddQntMinusBtn.setForeground(Color.WHITE);
        orderItemAddQntMinusBtn.setBorder(null);
        orderItemAddQntMinusBtn.setFocusable(false);
        addToOrderRightPanel.add(orderItemAddQntMinusBtn);

        orderItemQntTxt = new JTextField();
        orderItemQntTxt.setText("1");
        orderItemQntTxt.setEditable(false);
        orderItemQntTxt.setBounds(60, 200, 91, 42);
        addToOrderRightPanel.add(orderItemQntTxt);

        orderItemAddQntPlusBtn = new JButton("+");
        orderItemAddQntPlusBtn.setBounds(160, 210, 26, 26);
        orderItemAddQntPlusBtn.setFont(new Font(null, Font.BOLD, 22));
        orderItemAddQntPlusBtn.setBackground(Color.BLACK);
        orderItemAddQntPlusBtn.setForeground(Color.WHITE);
        orderItemAddQntPlusBtn.setBorder(null);
        orderItemAddQntPlusBtn.setFocusable(false);
        addToOrderRightPanel.add(orderItemAddQntPlusBtn);

        orderItemDeliveryLbl = new JLabel("Delivery");
        orderItemDeliveryLbl.setBounds(25, 280, 100, 30);
        orderItemDeliveryLbl.setForeground(Color.WHITE);
        orderItemDeliveryLbl.setFont(new Font(null, Font.BOLD, 16));
        addToOrderRightPanel.add(orderItemDeliveryLbl);

        orderItemDeliveryRadioBtn = new JRadioButton();
        orderItemDeliveryRadioBtn.setBounds(140, 287, 20, 20);
        orderItemDeliveryRadioBtn.setBackground(new Color(0x484848));
        addToOrderRightPanel.add(orderItemDeliveryRadioBtn);

        orderItemPickupLbl = new JLabel("Pick Up");
        orderItemPickupLbl.setBounds(25, 320, 100, 30);
        orderItemPickupLbl.setForeground(Color.WHITE);
        orderItemPickupLbl.setFont(new Font(null, Font.BOLD, 16));
        addToOrderRightPanel.add(orderItemPickupLbl);

        orderItemPickupRadioBtn = new JRadioButton();
        orderItemPickupRadioBtn.setBounds(140, 327, 20, 20);
        orderItemPickupRadioBtn.setBackground(new Color(0x484848));
        addToOrderRightPanel.add(orderItemPickupRadioBtn);

        group = new ButtonGroup();
        group.add(orderItemDeliveryRadioBtn);
        group.add(orderItemPickupRadioBtn);

        JLabel orderItemSubTotalTxtLbl = new JLabel("Subtotal: LKR ");
        orderItemSubTotalTxtLbl.setBounds(25, 450, 100, 30);
        orderItemSubTotalTxtLbl.setFont(new Font(null, Font.BOLD, 20));
        orderItemSubTotalTxtLbl.setForeground(Color.WHITE);
        addToOrderRightPanel.add(orderItemSubTotalTxtLbl);

        orderItemSubTotalLbl = new JLabel();
        orderItemSubTotalLbl.setBounds(130, 450, 500, 30);
        orderItemSubTotalLbl.setFont(new Font(null, Font.PLAIN, 20));
        orderItemSubTotalLbl.setForeground(Color.WHITE);
        addToOrderRightPanel.add(orderItemSubTotalLbl);

        addToOrderBtn = new JButton("Add to order");
        addToOrderBtn.setBounds(25, 490, 550, 51);
        addToOrderBtn.setFocusable(false);
        addToOrderBtn.setBorder(null);
        addToOrderBtn.setBackground(Color.BLACK);
        addToOrderBtn.setForeground(Color.WHITE);
        addToOrderBtn.setFont(new Font(null, Font.BOLD, 18));
        addToOrderRightPanel.add(addToOrderBtn);







        ////////cart////////////
        cartContainerPanel = new JPanel();
        cartContainerPanel.setBounds(932, 0, 554, 800);
        cartContainerPanel.setBackground(new Color(0x444444));
        cartContainerPanel.setLayout(null);
        cartContainerPanel.setVisible(false);
        layeredPane.add(cartContainerPanel, JLayeredPane.PALETTE_LAYER);

        cartContainerPanelCloseBtn = new JButton("X");
        cartContainerPanelCloseBtn.setBounds(10, 10, 20, 20);
        cartContainerPanelCloseBtn.setFocusable(false);
        cartContainerPanelCloseBtn.setBackground(Color.BLACK);
        cartContainerPanelCloseBtn.setForeground(Color.WHITE);
        cartContainerPanelCloseBtn.setBorder(null);
        cartContainerPanelCloseBtn.setFont(new Font(null, Font.BOLD, 18));
        cartContainerPanel.add(cartContainerPanelCloseBtn);

        JLabel cartSelectLabel = new JLabel("Carts");
        cartSelectLabel.setFont(new Font(null, Font.BOLD, 20));
        cartSelectLabel.setForeground(Color.WHITE);
        cartSelectLabel.setBounds(440, 10, 55, 30);
        cartContainerPanel.add(cartSelectLabel);

        try {
            File imageFile = new File("Images/cartselectArrow.png");
            BufferedImage cartSelectImage = ImageIO.read(imageFile);
            cartSelectBtnPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (cartSelectImage != null) {
                        g.drawImage(cartSelectImage, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            cartSelectBtnPanel.setBounds(495, 18, 20, 20);
            cartSelectBtnPanel.setBackground(new Color(0x444444));
            cartContainerPanel.add(cartSelectBtnPanel);

        }catch(IOException ex){
            ex.printStackTrace();
        }

        cartDeliveryBtn = new JButton("Delivery");
        cartDeliveryBtn.setFont(new Font(null, Font.PLAIN, 16));
        cartDeliveryBtn.setBackground(Color.BLACK);
        cartDeliveryBtn.setForeground(Color.WHITE);
        cartDeliveryBtn.setBounds(180, 60, 101, 38);
        cartDeliveryBtn.setFocusable(false);
        cartDeliveryBtn.setBorder(null);
        cartContainerPanel.add(cartDeliveryBtn);

        cartPickUpBtn = new JButton("Pick up");
        cartPickUpBtn.setFont(new Font(null, Font.PLAIN, 16));
        cartPickUpBtn.setBackground(new Color(0x828282));
        cartPickUpBtn.setForeground(Color.BLACK);
        cartPickUpBtn.setBounds(281, 60, 101, 38);
        cartPickUpBtn.setFocusable(false);
        cartPickUpBtn.setBorder(null);
        cartContainerPanel.add(cartPickUpBtn);

        cartRestNameLbl = new JLabel("KFC");
        cartRestNameLbl.setBounds(30, 120, 370, 50);
        cartRestNameLbl.setFont(new Font(null, Font.BOLD, 30));
        cartRestNameLbl.setForeground(Color.WHITE);
        cartContainerPanel.add(cartRestNameLbl);

        cartItemsCountLbl = new JLabel();
        cartItemsCountLbl.setFont(new Font(null, Font.PLAIN, 20));
        cartItemsCountLbl.setForeground(Color.WHITE);
        cartItemsCountLbl.setBounds(410, 120, 130, 50);
        cartContainerPanel.add(cartItemsCountLbl);

        ////
        cartItemsPanel = new JPanel();
        cartItemsPanel.setPreferredSize(new Dimension(518, 418));
        cartItemsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 5));

        JScrollPane cartItemsScrollPane = new JScrollPane(cartItemsPanel);
        cartItemsScrollPane.setBounds(18, 180, 518, 418);
        cartItemsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        cartContainerPanel.add(cartItemsScrollPane);

        cartOrderAddressTxt = new JTextField();
        cartOrderAddressTxt.setBounds(18, 610, 518, 47);
        cartOrderAddressTxt.setText("Enter your delivery address");
        cartOrderAddressTxt.setBackground(new Color(0xB8B8B8));
        cartOrderAddressTxt.setForeground(Color.GRAY);
        cartContainerPanel.add(cartOrderAddressTxt);

        JLabel cartSubTotalLabel = new JLabel("Subtotal:");
        cartSubTotalLabel.setFont(new Font(null, Font.BOLD, 22));
        cartSubTotalLabel.setForeground(Color.WHITE);
        cartSubTotalLabel.setBounds(30, 660, 100, 30);
        cartContainerPanel.add(cartSubTotalLabel);

        cartSubTotalPriceLabel = new JLabel();
        cartSubTotalPriceLabel.setFont(new Font(null, Font.BOLD, 22));
        cartSubTotalPriceLabel.setForeground(Color.WHITE);
        cartSubTotalPriceLabel.setBounds(350, 660, 200, 30);
        cartContainerPanel.add(cartSubTotalPriceLabel);

        cartPlaceOrderBtn = new JButton("Place the order");
        cartPlaceOrderBtn.setFont(new Font(null, Font.BOLD, 18));
        cartPlaceOrderBtn.setForeground(Color.WHITE);
        cartPlaceOrderBtn.setBackground(Color.BLACK);
        cartPlaceOrderBtn.setFocusable(false);
        cartPlaceOrderBtn.setBorder(null);
        cartPlaceOrderBtn.setBounds(18, 700, 518, 52);
        cartContainerPanel.add(cartPlaceOrderBtn);
        ////



        /////cart list///////
        cartsListPanel = new JPanel();
        cartsListPanel.setPreferredSize(new Dimension(333, 234));
        cartsListPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
        cartsListPanel.setBackground(new Color(0x3B3B3B));

        cartListScrollPane = new JScrollPane(cartsListPanel);
        cartListScrollPane.setBounds(1154, 95, 333, 234);
        cartListScrollPane.setBorder(null);
        cartListScrollPane.setVisible(false);
        cartListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        layeredPane.add(cartListScrollPane, JLayeredPane.PALETTE_LAYER);

        layeredPane.setLayer(cartListScrollPane, 2);
        layeredPane.setLayer(cartContainerPanel, 1);



        /////////search panel////////////
        restSearchPanel = new JPanel();
        restSearchPanel.setBounds(0, 130, 1500, 670);
        restSearchPanel.setVisible(false);
        restSearchPanel.setBackground(new Color(0x303030));
        restSearchPanel.setLayout(null);
        layeredPane.add(restSearchPanel, JLayeredPane.PALETTE_LAYER);


        try {
            File imageFile = new File("Images/arrowBack.png");
            BufferedImage backBtnIcon = ImageIO.read(imageFile);
            searchPanelBackBtnPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (backBtnIcon != null) {
                        g.drawImage(backBtnIcon, 5, 5, 40, 40, this);
                    }
                }
            };

            searchPanelBackBtnPanel.setBounds(70, 20, 50, 50);
            searchPanelBackBtnPanel.setBackground(new Color(0x3F3E3E));
            restSearchPanel.add(searchPanelBackBtnPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        try {
            File imageFile = new File("Images/search.png");
            BufferedImage searchIcon1 = ImageIO.read(imageFile);
            JPanel searchPanelSearchIconPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (searchIcon1 != null) {
                        g.drawImage(searchIcon1, 13, 7, 30, 30, this);
                    }
                }
            };

            searchPanelSearchIconPanel.setBounds(395, 20, 57, 44);
            searchPanelSearchIconPanel.setBackground(new Color(0x000000));
            restSearchPanel.add(searchPanelSearchIconPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        searchPanelSearchBar = new JTextField("Search Restaurants");
        searchPanelSearchBar.setForeground(new Color(0x4C4C4C));
        searchPanelSearchBar.setFont(new Font(null, Font.PLAIN, 20));
        searchPanelSearchBar.setHorizontalAlignment(SwingConstants.CENTER);
        searchPanelSearchBar.setBackground(new Color(0xAAAAAA));
        searchPanelSearchBar.setBounds(452, 20, 475, 44);
        searchPanelSearchBar.setBorder(null);
        restSearchPanel.add(searchPanelSearchBar);

        ////....
        restSearchResultsPanel = new JPanel();
        restSearchResultsPanel.setPreferredSize(new Dimension(1350, 528));
        restSearchResultsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        restSearchResultsPanel.setBackground(new Color(0x4E4E4E));

        JScrollPane restSearchResultsPanelScrollPane = new JScrollPane(restSearchResultsPanel);
        restSearchResultsPanelScrollPane.setBounds(70, 85, 1350, 528);
        restSearchResultsPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        restSearchResultsPanelScrollPane.setBorder(null);
        restSearchPanel.add(restSearchResultsPanelScrollPane);
        ////....
        ////////////


        ///////////favorites panel////////////
        myFavoritesPanel = new JPanel();
        myFavoritesPanel.setBounds(0, 130, 1500, 670);
        myFavoritesPanel.setBackground(new Color(0x3F3E3E));
        myFavoritesPanel.setVisible(false);
        myFavoritesPanel.setLayout(null);
        layeredPane.add(myFavoritesPanel, JLayeredPane.PALETTE_LAYER);

        try {
            File imageFile = new File("Images/arrowBack.png");
            BufferedImage backBtnIcon = ImageIO.read(imageFile);
            myFavoriteBackBtnPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (backBtnIcon != null) {
                        g.drawImage(backBtnIcon, 0, 0, 50, 50, this);
                    }
                }
            };

            myFavoriteBackBtnPanel.setBounds(70, 50, 50, 50);
            myFavoriteBackBtnPanel.setBackground(new Color(0x3F3E3E));
            myFavoritesPanel.add(myFavoriteBackBtnPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        JLabel myFavLabel = new JLabel("My Favorites");
        myFavLabel.setFont(new Font(null, Font.BOLD, 24));
        myFavLabel.setForeground(Color.WHITE);
        myFavLabel.setBounds(600, 20, 150, 30);
        myFavoritesPanel.add(myFavLabel);

        favRestsBtn = new JButton("Restaurants");
        favRestsBtn.setFont(new Font(null, Font.BOLD, 15));
        favRestsBtn.setBounds(550, 70, 130, 36);
        favRestsBtn.setForeground(Color.WHITE);
        favRestsBtn.setBackground(new Color(0x1035F4));
        favRestsBtn.setFocusable(false);
        myFavoritesPanel.add(favRestsBtn);

        favMealsBtn = new JButton("Meals");
        favMealsBtn.setFont(new Font(null, Font.BOLD, 15));
        favMealsBtn.setBounds(680, 70, 130, 36);
        favMealsBtn.setForeground(Color.BLACK);
        favMealsBtn.setBackground(new Color(0xC3C3C3));
        favMealsBtn.setFocusable(false);
        myFavoritesPanel.add(favMealsBtn);

        ////....
        favItemsPanel = new JPanel();
        favItemsPanel.setPreferredSize(new Dimension(1350, 480));
        favItemsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        favItemsPanel.setBackground(new Color(0x343434));

        JScrollPane favItemsPanelScrollPane = new JScrollPane(favItemsPanel);
        favItemsPanelScrollPane.setBounds(70, 130, 1350, 480);
        favItemsPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        favItemsPanelScrollPane.setBorder(null);
        myFavoritesPanel.add(favItemsPanelScrollPane);
        ////....
        ////////////////////////


        homeFrame.add(layeredPane);
    }






    public void addLogoutBtnSidebarListener(ActionListener listener){
        logoutBtnSidebar.addActionListener(listener);
    }

    public void setAfterLoginAndLogoutView(Boolean isLogged){
        homePanelUserProfilePanel.setVisible(isLogged);
        logoutBtnSidebar.setVisible(isLogged);
        restaurantPanelUserProfilePanel.setVisible(isLogged);
        restaurantPanelLoginBtn.setVisible(!isLogged);
        loginBtn.setVisible(!isLogged);
        loginBtnSidebar.setVisible(!isLogged);
        signupBtnSidebar.setVisible(!isLogged);
    }

    public void addLoginBtnSidebarListener(ActionListener listener){
        loginBtnSidebar.addActionListener(listener);
    }

    public void addSignupBtnSidebarListener(ActionListener listener){
        signupBtnSidebar.addActionListener(listener);
    }

    public void addSignupDeliverBtnListener(ActionListener listener){
        signupDeliverBtn.addActionListener(listener);
    }

    public void addCustomerLoginFormLoginBtnListener(ActionListener listener){
        loginFormLoginBtn.addActionListener(listener);
    }

    public String[] getCustomerLoginDetails(){
        String[] details = {loginFormEmailTxt.getText(), loginFormPwdTxt.getText()};
        return details;
    }

    public void addUserSignUpBtnLitener(ActionListener listener){
        registerFormSignUpBtn.addActionListener(listener);
    }

    public String[] getUserSignUpDetails(){
        String[] details = {usernameTxt.getText(), registerFormEmailTxt.getText(), registerFormPwdTxt.getText(), registerFormConfirmPwdTxt.getText()};
        return details;
    }

    public void displayUserRegistrationValidateMsge(Boolean isValid, String msge){
        if(isValid){
            JOptionPane.showMessageDialog(logSignUpFormPanel, msge, null, JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(logSignUpFormPanel, msge, null, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setCustomerEmailConfirmPanelVisibility(Boolean visible){
        customerEmailConfirmPanel.setVisible(visible);
        registerFormPanelLeft.setVisible(!visible);
    }

    public void setCustomerEmailConfirmBtnListener(ActionListener listener){
        customerEmailConfirmBtn.addActionListener(listener);
    }

    public String getCustomerEnteredEmailConfirmCode(){
        return customerEmailConfirmCodeTxt.getText();
    }

    public void clearCustomerRegistrationPanel(){
        usernameTxt.setText("");
        registerFormEmailTxt.setText("");
        registerFormPwdTxt.setText("");
        registerFormConfirmPwdTxt.setText("");
        customerEmailConfirmCodeTxt.setText("");
    }

    public void clearCustomerLoginPanel(){
        loginFormEmailTxt.setText("");
        loginFormPwdTxt.setText("");
    }

    public void addLoginFormSignUpLblListener(MouseListener listener){
        loginFormSignUpLbl.addMouseListener(listener);
    }

    public void addRegisterFormLoginLblListener(MouseListener listener){
        registerFormLoginLbl.addMouseListener(listener);
    }

    public void openRegisterFormPanelLeft(){
        registerFormPanelLeft.setVisible(true);
        loginFormPanelLeft.setVisible(false);
    }

    public void openLoginFormPanelLeft(){
        loginFormPanelLeft.setVisible(true);
        registerFormPanelLeft.setVisible(false);
    }

    public void addRegisterFormCloseBtnListener(MouseListener listener){
        registerFormCloseBtn.addMouseListener(listener);
    }

    public void addUserLoginBtnListener(ActionListener listener){
        loginBtn.addActionListener(listener);
    }

    public void addRestPanelUserLoginBtnListener(ActionListener listener){
        restaurantPanelLoginBtn.addActionListener(listener);
    }

    public void setHomePanelVisibility(Boolean visible){
        homePanel.setVisible(visible);
    }

    public void setUserLogSignupContainerVisibility(Boolean visible){
        userLogSignupContainer.setVisible(visible);
    }


    public void setRestaurants(List<List<Object>> restaurants, String type, String whereToLoad){
        if(Objects.equals(whereToLoad, "home")){
            restaurantsPanel.removeAll();
        }else if(Objects.equals(whereToLoad, "searchPanel")){
            restSearchResultsPanel.removeAll();
        }else{
            favItemsPanel.removeAll();
        }


        for (List<Object> restaurant : restaurants) {
            int restId = (int) restaurant.getFirst();
            String name = (String) restaurant.get(1);
            String isOpen = (String) restaurant.get(2);
            BufferedImage image = (BufferedImage) restaurant.get(3);
            String restAllowType = (String) restaurant.get(4);

            if(Objects.equals(restAllowType, type) || Objects.equals(type, "All")){
                JPanel restaurant1 = new JPanel();
                restaurant1.putClientProperty("ID", Integer.toString(restId));
                restaurant1.setPreferredSize(new Dimension(390, 272));
                restaurant1.setLayout(new BorderLayout());
                restaurant1.setBackground(new Color(0x0F0F0F));
                if(Objects.equals(whereToLoad, "home")){
                    restaurantsPanel.add(restaurant1);
                }else if(Objects.equals(whereToLoad, "searchPanel")){
                    restSearchResultsPanel.add(restaurant1);
                }else{
                    favItemsPanel.add(restaurant1);
                }

                restaurant1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(Objects.equals(whereToLoad, "searchPanel")){
                            restSearchPanel.setVisible(false);
                            CustomerModel.setIsRestSearchPanelOpen(false);
                            CustomerModel.setRestSearchBarText("");
                            clearSearchBar();
                            clearSearchedResultsPanel();
                        }else if(Objects.equals(whereToLoad, "favPanel")){
                            myFavoritesPanel.setVisible(false);
                            CustomerModel.setIsFavoritesPanelOpen(false);
                        }

                        CustomerModel.setIsHomePanelOpen(false);
                        homePanel.setVisible(false);
                        CustomerModel.setIsRestaurantPanelOpen(true);
                        restaurantPanel.setVisible(true);
                        CustomerModel.setOpenRestaurantId(Integer.parseInt((String) restaurant1.getClientProperty("ID")));
                        CustomerModel.setOpenRestaurantAllowType(restAllowType);

                        if(Objects.equals(isOpen, "yes")){
                            restOpenLabel.setText("OPEN");
                            restOpenStatusPanel.setBackground(new Color(0x2CA30E));
                        }else{
                            restOpenLabel.setText("CLOSE");
                            restOpenStatusPanel.setBackground(new Color(0xED0E0E));
                        }

                        restNameLabel.setText(name);

                    }
                });

                JPanel restaurantImgPanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        if (image != null) {
                            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                        }
                    }
                };

                restaurantImgPanel.setPreferredSize(new Dimension(390, 182));
                restaurantImgPanel.setBackground(Color.RED);
                restaurant1.add(restaurantImgPanel, BorderLayout.NORTH);

                JPanel restaurantBottomPanel = new JPanel();
                restaurantBottomPanel.setPreferredSize(new Dimension(390, 90));
                restaurantBottomPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
                restaurant1.add(restaurantBottomPanel, BorderLayout.SOUTH);

                JPanel restBottomDetailsPanel = new JPanel();
                restBottomDetailsPanel.setLayout(new BorderLayout());
                restBottomDetailsPanel.setPreferredSize(new Dimension(320, 90));
                restBottomDetailsPanel.setBackground(new Color(0x0F0F0F));
                restBottomDetailsPanel.setBorder(new EmptyBorder(15, 20, 15, 0));
                restaurantBottomPanel.add(restBottomDetailsPanel);

                JLabel restNameLabel = new JLabel(name);
                restNameLabel.setForeground(Color.white);
                restBottomDetailsPanel.add(restNameLabel, BorderLayout.NORTH);

                JLabel restOpenLabel = new JLabel();
                if(Objects.equals(isOpen, "no")){
                    restOpenLabel.setText("CLOSE");
                }else{
                    restOpenLabel.setText("OPEN");
                }
                restOpenLabel.setForeground(Color.white);
                restBottomDetailsPanel.add(restOpenLabel, BorderLayout.SOUTH);

                try {
                    final File[] imageFile = new File[1];

                    if(CustomerModel.getLoggedCustomerID() != 0){
                        if(CustomerModel.checkIfTheRestOrMealIsInFavList(restId, "rest", 0)){
                            imageFile[0] = new File("Images/favorites.png");
                        }else{
                            imageFile[0] = new File("Images/hart.png");
                        }
                    }else{
                        imageFile[0] = new File("Images/hart.png");
                    }

                    final BufferedImage[] hartIcon = {ImageIO.read(imageFile[0])};
                    JPanel restBottomFavPanel = new JPanel() {
                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            if (hartIcon[0] != null) {
                                g.drawImage(hartIcon[0], 20, 30, 30, 30, this);
                            }
                        }
                    };

                    restBottomFavPanel.setBackground(new Color(0x0F0F0F));
                    restBottomFavPanel.setLayout(new BorderLayout());
                    restBottomFavPanel.setPreferredSize(new Dimension(70, 90));
                    restaurantBottomPanel.add(restBottomFavPanel);

                    restBottomFavPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(CustomerModel.getLoggedCustomerID() != 0){
                                try{
                                    if(CustomerModel.checkIfTheRestOrMealIsInFavList(restId, "rest", 0)){
                                        hartIcon[0] = ImageIO.read(new File("Images/hart.png"));
                                        restBottomFavPanel.repaint();
                                        CustomerModel.updateFavorites(restId, "remove", "rest", 0);
                                    }else{
                                        hartIcon[0] = ImageIO.read(new File("Images/favorites.png"));
                                        restBottomFavPanel.repaint();
                                        CustomerModel.updateFavorites(restId, "add", "rest", 0);
                                    }
                                }catch (IOException ex){
                                    ex.printStackTrace();
                                }
                            }
                        }
                    });
                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        }

        int componentsPerRow = 3;
        int componentHeight = 312;
        int numRows;
        if(Objects.equals(whereToLoad, "home")){
            numRows = (int) Math.ceil((double) restaurantsPanel.getComponentCount() / componentsPerRow);
            int totalPreferredRowHeight = componentHeight * numRows;
            restaurantsPanel.setPreferredSize(new Dimension(1320, totalPreferredRowHeight));
            restaurantsPanel.revalidate();
            restaurantsPanel.repaint();
        }else if(Objects.equals(whereToLoad, "searchPanel")){
            numRows = (int) Math.ceil((double) restSearchResultsPanel.getComponentCount() / componentsPerRow);
            int totalPreferredRowHeight = componentHeight * numRows;
            restSearchResultsPanel.setPreferredSize(new Dimension(1350, totalPreferredRowHeight));
            restSearchResultsPanel.revalidate();
            restSearchResultsPanel.repaint();
        }else{
            numRows = (int) Math.ceil((double) favItemsPanel.getComponentCount() / componentsPerRow);
            int totalPreferredRowHeight = componentHeight * numRows;
            favItemsPanel.setPreferredSize(new Dimension(1350, totalPreferredRowHeight));
            favItemsPanel.revalidate();
            favItemsPanel.repaint();
        }
    }

    public void setHomeFrameVisible(){
        homeFrame.setVisible(true);
    }

    public void clearRestaurantsPanel(){
        restaurantsPanel.removeAll();
        restaurantsPanel.revalidate();
        restaurantsPanel.repaint();
    }

    public void addEmailConfirmBtnListner(ActionListener listener){
        emailConfirmBtn.addActionListener(listener);
    }

    public String getUserEnteredEmailConfirmCode(){
//        return Integer.parseInt(emailConfirmCodeTextField.getText());
        return emailConfirmCodeTextField.getText();
    }

    public void openEmailConfirmPanel(boolean open){
        emailConfirmPanel.setVisible(open);
        restSignupPanel.setVisible(!open);
        restSignUpContainerImagePanel.setVisible(!open);
    }

    public void setEmailConfirmMessage(String email){
        emailConfirmMsgLabel.setText("Enter the 4 digit code we sent to your email: " + email);
    }

    public void addMenuIconClickListner(MouseListener listener) {
        menuIconLabel.addMouseListener(listener);
    }

    public void setSideBarVisible(boolean visible) {
        sideBarHome.setVisible(visible);
    }

    public void addRestaurantBtnListner(ActionListener listener){
        addRestBtn.addActionListener(listener);
    }

    public void setRestSignAppVisible(boolean visible){
        restSignupPanelContainer.setVisible(visible);
    }

    public void addRestSignUpFormCloseBtnListener(ActionListener listener){
        restSignUpCloseBtn.addActionListener(listener);
    }

    //////// Restaurant register input validation ////////

    public void addRestSubmitBtnListner(ActionListener listener){
        restSignupSubmitBtn.addActionListener(listener);
    }

    public String[] getRestRegisterDetails(){
        String[] details = {
                storeNameTextField.getText(),
                storeAddressTextField.getText(),
                storeEmailTextField.getText(),
                storeMobileTextField.getText(),
                storeCityTextField.getText(),
                storePostalCodeTextField.getText(),
                storeBranchNameTextField.getText()
        };

        return details;
    }

    public void displayRestSubmitMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Invalid inputs", JOptionPane.ERROR_MESSAGE);
    }

    public void clearRestSignupForm(){
        storeNameTextField.setText("");
        storeAddressTextField.setText("");
        storeEmailTextField.setText("");
        storeMobileTextField.setText("");
        storeCityTextField.setText("");
        storePostalCodeTextField.setText("");
        storeBranchNameTextField.setText("");
    }

    public void displayDataInsertedMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Successfull", JOptionPane.INFORMATION_MESSAGE);
    }

    public void addFilterAllPanelListener(MouseListener listener){
        filterPanelOption1.addMouseListener(listener);
    }

    public void addFilterDeliveryPanelListener(MouseListener listener){
        filterPanelOption2.addMouseListener(listener);
    }

    public void addFilterPickUpPanelListener(MouseListener listener){
        filterPanelOption3.addMouseListener(listener);
    }

    public void applyFilterBtnsStyles(String type){
        if(Objects.equals(type, "all")){
            allLabel.setForeground(Color.WHITE);
            filterPanelOption1.setBackground(new Color(0x000000));
            deliveryLabel.setForeground(Color.BLACK);
            pickupLabel.setForeground(Color.BLACK);
            filterPanelOption2.setBackground(Color.WHITE);
            filterPanelOption3.setBackground(Color.WHITE);
        }else if(Objects.equals(type, "delivery")){
            deliveryLabel.setForeground(Color.WHITE);
            filterPanelOption2.setBackground(new Color(0x000000));
            allLabel.setForeground(Color.BLACK);
            filterPanelOption1.setBackground(Color.WHITE);
            pickupLabel.setForeground(Color.BLACK);
            filterPanelOption3.setBackground(Color.WHITE);
        }else{
            pickupLabel.setForeground(Color.WHITE);
            filterPanelOption3.setBackground(new Color(0x000000));
            deliveryLabel.setForeground(Color.BLACK);
            filterPanelOption2.setBackground(Color.WHITE);
            allLabel.setForeground(Color.BLACK);
            filterPanelOption1.setBackground(Color.WHITE);
        }
    }

    public void restMenuIconBtnPanelListener(MouseListener listener){
        restMenuIconBtn.addMouseListener(listener);
    }

    public void setRestaurantMyFeaturesPanelVisibility(boolean visible){
        restaurantMyFeaturesPanel.setVisible(visible);
    }

    public void loadCategoriesToRestMenuBar(List<String> restCategories){
        restaurantMenuPanel.removeAll();
        restaurantMenuPanel.setOpaque(false);

        for(String category : restCategories){
            JPanel menuCategoryPanel = new JPanel();
            menuCategoryPanel.setPreferredSize(new Dimension(240, 30));
            menuCategoryPanel.putClientProperty("ID", category);
            menuCategoryPanel.setBackground(new Color(0x262626));
            menuCategoryPanel.setLayout(null);
            restaurantMenuPanel.add(menuCategoryPanel);

            JLabel categoryLabel = new JLabel(category);
            categoryLabel.setForeground(Color.WHITE);
            categoryLabel.setBounds(0, 0, 240, 30);
            categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
            menuCategoryPanel.add(categoryLabel);

            menuCategoryPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String clickedCategoryId = (String) menuCategoryPanel.getClientProperty("ID");
                    int targetY = 0;

                    for(Component component : restMenuItemsPanel.getComponents()){
                        int componentY = targetY;
                        String targetId = (String) ((JPanel) component).getClientProperty("ID");
                        int componentHeight = component.getPreferredSize().height;

                        targetY += componentHeight;
                        targetY += 10;

                        if(Objects.equals(clickedCategoryId, targetId)){
                             JViewport viewport = RestMenuItemsScrollPane.getViewport();
                             viewport.setViewPosition(new Point(0, componentY));
                             break;
                        }
                    }
                }
            });
        }

        int totalPreferredHeight = 0;
        for (Component component : restaurantMenuPanel.getComponents()) {
            totalPreferredHeight += component.getPreferredSize().height;
        }

        restaurantMenuPanel.setPreferredSize(new Dimension(240, totalPreferredHeight));

        restaurantMenuPanel.revalidate();
        restaurantMenuPanel.repaint();
        restaurantMenuPanel.setOpaque(true);////newly added
    }

    public void clearCategoriesInRestMenuBar(){
        restaurantMenuPanel.removeAll();
        restaurantMenuPanel.revalidate();
        restaurantMenuPanel.repaint();
    }

    public void loadAllCategoryItems(List<List<List<Object>>> allCategoryFoodItems, List<String> restCategories){
        restMenuItemsPanel.removeAll();
        restMenuItemsPanel.setOpaque(false);

        for(int i = 0; i < allCategoryFoodItems.size(); i++) {
            List<List<Object>> categoryFoodItems = allCategoryFoodItems.get(i);
            String categoryName = restCategories.get(i);

            JPanel categoryItemsContainerPanel = new JPanel();
            categoryItemsContainerPanel.putClientProperty("ID", categoryName);
            categoryItemsContainerPanel.setBackground(new Color(0x373737));
            categoryItemsContainerPanel.setPreferredSize(new Dimension(1165, 400));
            categoryItemsContainerPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
            restMenuItemsPanel.add(categoryItemsContainerPanel);

            JPanel categoryItemsTopPanel = new JPanel();
            categoryItemsTopPanel.setPreferredSize(new Dimension(1165, 50));
            categoryItemsTopPanel.setLayout(null);
            categoryItemsTopPanel.setBackground(new Color(0x373737));
            categoryItemsContainerPanel.add(categoryItemsTopPanel);

            JLabel categoryNameLabel = new JLabel(categoryName);
            categoryNameLabel.setForeground(Color.WHITE);
            categoryNameLabel.setFont(new Font(null, Font.BOLD, 20));
            categoryNameLabel.setBounds(20, 0, 1000, 50);
            categoryItemsTopPanel.add(categoryNameLabel);

            JPanel categoryItemsBottomPanel = new JPanel();
            categoryItemsBottomPanel.setPreferredSize(new Dimension(1165, 400));
            categoryItemsBottomPanel.setBackground(new Color(0x373737));
            categoryItemsBottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
            categoryItemsContainerPanel.add(categoryItemsBottomPanel);

            for (List<Object> foodItem : categoryFoodItems) {
                String name = (String) foodItem.get(0);
                int price = (int) foodItem.get(1);
                String description = (String) foodItem.get(2);
                BufferedImage image = (BufferedImage) foodItem.get(3);
                String foodItemId = String.valueOf((int) foodItem.get(4));

                JPanel menuItemPanel = new JPanel();
                menuItemPanel.setPreferredSize(new Dimension(555, 194));
//                menuItemPanel.setBackground(new Color(0xFFD7F2));
                menuItemPanel.setBackground(new Color(0x82E0FE));
                menuItemPanel.putClientProperty("ID", foodItemId);
                menuItemPanel.setLayout(null);
                categoryItemsBottomPanel.add(menuItemPanel);

                menuItemPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(!CustomerModel.getIsRestaurantOpen()){
                            JOptionPane.showMessageDialog(restaurantPanel, "Restaurant is closed at this time", null, JOptionPane.INFORMATION_MESSAGE);
                        }else if(CustomerModel.getLoggedCustomerID() == 0){
                            userLogSignupContainer.setVisible(true);
                            restaurantPanel.setVisible(false);
                        }else{
                            addToOrderPanel.setVisible(true);
                            CustomerModel.setIsAddToOrderPanelOpen(true);
                            CustomerModel.setOpenFoodItemId(Integer.parseInt((String)menuItemPanel.getClientProperty("ID")));

                            if(Objects.equals(CustomerModel.getOpenRestaurantAllowType(), "All")){
                                group.clearSelection();

                                orderItemDeliveryRadioBtn.setVisible(true);
                                orderItemPickupRadioBtn.setVisible(true);

                                orderItemPickupLbl.setVisible(true);
                                orderItemDeliveryLbl.setVisible(true);

                            }else if(Objects.equals(CustomerModel.getOpenRestaurantAllowType(), "Delivery")){
                                group.clearSelection();

                                orderItemDeliveryRadioBtn.setVisible(true);
                                orderItemPickupRadioBtn.setVisible(false);

                                orderItemDeliveryRadioBtn.setSelected(true);

                                orderItemPickupLbl.setVisible(false);
                                orderItemDeliveryLbl.setVisible(true);
                            }else{
                                group.clearSelection();

                                orderItemDeliveryRadioBtn.setVisible(false);
                                orderItemPickupRadioBtn.setVisible(true);

                                orderItemPickupRadioBtn.setSelected(true);

                                orderItemPickupLbl.setVisible(true);
                                orderItemDeliveryLbl.setVisible(false);
                            }

                            orderItemQntTxt.setText("1");
                        }
                    }
                });

                JLabel menuItemNameLbl = new JLabel();
                menuItemNameLbl.setText(name);
                menuItemNameLbl.setBounds(20, 20, 300, 40);
                menuItemNameLbl.setFont(new Font(null, Font.PLAIN, 18));
                menuItemNameLbl.setVerticalAlignment(SwingConstants.TOP);
                menuItemPanel.add(menuItemNameLbl);

                JTextArea menuItemDescription = new JTextArea();
                menuItemDescription.setText(description);
                menuItemDescription.setBounds(20, 60, 260, 80);
                menuItemDescription.setFont(new Font(null, Font.PLAIN, 12));
                menuItemDescription.setLineWrap(true);
                menuItemDescription.setWrapStyleWord(true);
                menuItemDescription.setEditable(false);
                menuItemDescription.setFocusable(false);
                menuItemDescription.setBackground(null);
                menuItemPanel.add(menuItemDescription);

                JLabel menuItemPrice = new JLabel();
                menuItemPrice.setText("LKR " + price);
                menuItemPrice.setFont(new Font(null, Font.BOLD, 16));
                menuItemPrice.setBounds(20, 140, 300, 50);
                menuItemPanel.add(menuItemPrice);

                JPanel menuItemImagePanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        if (image != null) {
                            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                        }
                    }
                };
                menuItemImagePanel.setBounds(355, 5, 196, 184);
                menuItemImagePanel.setBackground(Color.GRAY);
                menuItemPanel.add(menuItemImagePanel);


                try{
                    final File[] imageFile = new File[1];
                    if(CustomerModel.getLoggedCustomerID() != 0){
                        if(CustomerModel.checkIfTheRestOrMealIsInFavList(0, "meal", Integer.parseInt(foodItemId))){
                            imageFile[0] = new File("Images/favorites.png");
                        }else{
                            imageFile[0] = new File("Images/hart.png");
                        }
                    }else{
                        imageFile[0] = new File("Images/hart.png");
                    }

                    final BufferedImage[] FavoriteImg = {ImageIO.read(imageFile[0])};
                    JPanel menuItemAddToFavoriteBtnPanel = new JPanel() {
                        @Override
                        protected void paintComponent(Graphics g) {
                            super.paintComponent(g);
                            if (FavoriteImg[0] != null) {
                                g.drawImage(FavoriteImg[0], 0, 0, getWidth(), getHeight(), this);
                            }
                        }
                    };
                    menuItemAddToFavoriteBtnPanel.setBounds(310, 20, 30, 30);
                    menuItemAddToFavoriteBtnPanel.setBackground(new Color(0x82E0FE));
                    menuItemPanel.add(menuItemAddToFavoriteBtnPanel);


                    menuItemAddToFavoriteBtnPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            try {
                                if(CustomerModel.getLoggedCustomerID() != 0){
                                    if(CustomerModel.checkIfTheRestOrMealIsInFavList(0, "meal", Integer.parseInt(foodItemId))){
                                        imageFile[0] = new File("Images/hart.png");
                                        FavoriteImg[0] = ImageIO.read(imageFile[0]);
                                        menuItemAddToFavoriteBtnPanel.repaint();
                                        CustomerModel.updateFavorites(0, "remove", "meal", Integer.parseInt(foodItemId));
                                    }else{
                                        imageFile[0] = new File("Images/favorites.png");
                                        FavoriteImg[0] = ImageIO.read(imageFile[0]);
                                        menuItemAddToFavoriteBtnPanel.repaint();
                                        CustomerModel.updateFavorites(0, "add", "meal", Integer.parseInt(foodItemId));
                                    }
                                }
                            }catch(IOException ex){
                                ex.printStackTrace();
                            }
                        }
                    });
                }catch(IOException ex){
                    ex.printStackTrace();
                }

                //.....

                int componentsPerRow = 2;
                int componentHeight = 194;
                int numRows = (int) Math.ceil((double) categoryItemsBottomPanel.getComponentCount() / componentsPerRow);
                int totalPreferredRowHeight = componentHeight * numRows;
                categoryItemsBottomPanel.setPreferredSize(new Dimension(1165, totalPreferredRowHeight + 50));
            }

            int totalPreferredHeight3 = 0;
            for (Component component : categoryItemsContainerPanel.getComponents()) {
                totalPreferredHeight3 += component.getPreferredSize().height;
            }
            categoryItemsContainerPanel.setPreferredSize(new Dimension(1170, totalPreferredHeight3));
        }

        int totalPreferredHeight2 = 0;
        for (Component component : restMenuItemsPanel.getComponents()) {
            totalPreferredHeight2 += component.getPreferredSize().height;
        }
        restMenuItemsPanel.setPreferredSize(new Dimension(1170, totalPreferredHeight2));

        restMenuItemsPanel.revalidate();
        restMenuItemsPanel.repaint();
        restMenuItemsPanel.setOpaque(true);
    }

    public void setRestaurantPanelVisibility(Boolean visible){
        restaurantPanel.setVisible(visible);
    }

    public void clearRestMenuItemsPanel(){
        restMenuItemsPanel.removeAll();
        restMenuItemsPanel.revalidate();
        restMenuItemsPanel.repaint();
    }

    public void addRestaurantPanelBackBtnPanelListener(MouseListener listener){
        restaurantBackBtnPanel.addMouseListener(listener);
    }








    //////////add to order panel////////////

    public void addToOrderPanelCloseBtnListener(ActionListener listener){
        addToOrderPanelCloseBtn.addActionListener(listener);
    }

    public void setAddToOrderPanelVisibility(Boolean visible){
        addToOrderPanel.setVisible(visible);
    }

    public void loadItemDetailsToAddToOrderPanel(List<Object> foodItemDetails){
        orderItemNameLbl.setText((String) foodItemDetails.get(0));
        orderItemPriceLbl.setText(String.valueOf((int) foodItemDetails.get(1)));
        orderItemDescriptionTextArea.setText((String) foodItemDetails.get(2));
        openFoodItemImage = (BufferedImage) foodItemDetails.get(3);
        addToOrderLeftPanel.repaint();

        orderItemSubTotalLbl.setText(String.valueOf(Integer.parseInt(orderItemPriceLbl.getText()) * Integer.parseInt(orderItemQntTxt.getText())));
    }

    public void addOrderItemAddQntPlusBtnListener(ActionListener listener){
        orderItemAddQntPlusBtn.addActionListener(listener);
    }

    public void addOrderItemAddQntMinusBtnListener(ActionListener listener){
        orderItemAddQntMinusBtn.addActionListener(listener);
    }

    public void setOrderItemQnt(String qnt){
        orderItemQntTxt.setText(qnt);
    }

    public int getOrderItemQnt(){
        return Integer.parseInt(orderItemQntTxt.getText());
    }

    public int getOpenFoodItemPrice(){
        return Integer.parseInt(orderItemPriceLbl.getText());
    }

    public void setSubTotal(String subtotal){
        orderItemSubTotalLbl.setText(subtotal);
    }

    public void addToOrderBtnListener(ActionListener listener){
        addToOrderBtn.addActionListener(listener);
    }

    public List<Object> getOrderItemDetails(){
        List<Object> orderItemDetails = new ArrayList<>();

        orderItemDetails.add(Integer.parseInt(orderItemQntTxt.getText()));

        if(orderItemPickupRadioBtn.isSelected()){
            orderItemDetails.add("pickup");
        }else if(orderItemDeliveryRadioBtn.isSelected()){
            orderItemDetails.add("delivery");
        }else{
            orderItemDetails.add("none");
        }

        orderItemDetails.add(Integer.parseInt(orderItemSubTotalLbl.getText()));

        return orderItemDetails;
    }

    public void displaySelectOrderTypeError(){
        JOptionPane.showMessageDialog(addToOrderPanel, "Enter an order Type!", null, JOptionPane.WARNING_MESSAGE);
    }




    //////// cart ////////
    public void addCartOrderAddressTxtFocusListener(FocusListener listener){
        cartOrderAddressTxt.addFocusListener(listener);
    }

    public String getCartOrderAddressTxtValue(){
        return cartOrderAddressTxt.getText();
    }

    public void setCartOrderAddressTxtValue(String value){
        cartOrderAddressTxt.setText(value);
    }

    public void setCartOrderAddressTxtForeground(String color){
        if(color.equals("b")){
            cartOrderAddressTxt.setForeground(Color.BLACK);
        }else{
            cartOrderAddressTxt.setForeground(Color.GRAY);
        }
    }

    public void cartBtnPanelListener(MouseListener listener){
        cartBtnPanel.addMouseListener(listener);
    }

    public void setCartContainerPanelVisibility(Boolean visible){
        cartContainerPanel.setVisible(visible);
    }

    public void addCartContainerPanelCloseBtnListener(ActionListener listener){
        cartContainerPanelCloseBtn.addActionListener(listener);
    }

    public void loadCartItems(List<List<Object>> cartItemsDetails){
        cartItemsPanel.removeAll();
        int subtotal = 0;
        int totalQnt = 0;

        for(List<Object> cartItemDetails : cartItemsDetails){
            int qnt = (int)cartItemDetails.get(0);
            String itemName = (String) cartItemDetails.get(1);
            int price = (int) cartItemDetails.get(2);
            BufferedImage cartItemImage = (BufferedImage) cartItemDetails.get(3);
            int itemId = (int)cartItemDetails.get(4);

            JPanel cartItemPanel = new JPanel();
            cartItemPanel.setPreferredSize(new Dimension(501, 132));
            cartItemPanel.setBackground(new Color(0x848484));
            cartItemPanel.setLayout(null);
            cartItemPanel.putClientProperty("ID", String.valueOf(itemId));
            cartItemsPanel.add(cartItemPanel);

            JTextField cartItemPriceHiddenField = new JTextField();
            cartItemPriceHiddenField.setVisible(false);
            cartItemPriceHiddenField.setText(""+price);
            cartItemPriceHiddenField.setBounds(160, 85, 50, 40);
            cartItemPriceHiddenField.setVisible(true);
            cartItemPriceHiddenField.setEditable(false);
            cartItemPanel.add(cartItemPriceHiddenField);

            JTextArea cartItemNameTextArea = new JTextArea();
            cartItemNameTextArea.setText(itemName);
            cartItemNameTextArea.setBounds(20, 10, 250, 50);
            cartItemNameTextArea.setFont(new Font(null, Font.PLAIN, 16));
            cartItemNameTextArea.setLineWrap(true);
            cartItemNameTextArea.setWrapStyleWord(true);
            cartItemNameTextArea.setEditable(false);
            cartItemNameTextArea.setFocusable(false);
            cartItemNameTextArea.setBackground(null);
            cartItemPanel.add(cartItemNameTextArea);

            JPanel cartItemImgPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (cartItemImage != null) {
                        g.drawImage(cartItemImage, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            cartItemImgPanel.setBounds(350, 5, 116, 92);
            cartItemPanel.add(cartItemImgPanel);

            JLabel cartItemTotalPriceLbl = new JLabel("" + price * qnt);
            cartItemTotalPriceLbl.setFont(new Font(null, Font.BOLD, 16));
            cartItemTotalPriceLbl.setBounds(350, 98, 120, 30);
            cartItemTotalPriceLbl.setForeground(Color.WHITE);
            cartItemPanel.add(cartItemTotalPriceLbl);

            JButton cartItemQntMinusBtn = new JButton("-");
            cartItemQntMinusBtn.setBounds(20, 85, 20, 20);
            cartItemQntMinusBtn.setFont(new Font(null, Font.BOLD, 22));
            cartItemQntMinusBtn.setBackground(Color.BLACK);
            cartItemQntMinusBtn.setForeground(Color.WHITE);
            cartItemQntMinusBtn.setBorder(null);
            cartItemQntMinusBtn.setFocusable(false);
            cartItemPanel.add(cartItemQntMinusBtn);

            JTextField cartItemQntTxt = new JTextField();
            cartItemQntTxt.setText(""+qnt);
            cartItemQntTxt.setBorder(null);
            cartItemQntTxt.setEditable(false);
            cartItemQntTxt.setBounds(45, 75, 80, 40);
            cartItemPanel.add(cartItemQntTxt);

            JButton cartItemQntPlusBtn = new JButton("+");
            cartItemQntPlusBtn.setBounds(130, 85, 20, 20);
            cartItemQntPlusBtn.setFont(new Font(null, Font.BOLD, 22));
            cartItemQntPlusBtn.setBackground(Color.BLACK);
            cartItemQntPlusBtn.setForeground(Color.WHITE);
            cartItemQntPlusBtn.setBorder(null);
            cartItemQntPlusBtn.setFocusable(false);
            cartItemPanel.add(cartItemQntPlusBtn);

            cartItemQntPlusBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cartItemQntTxt.setText(""+(Integer.parseInt(cartItemQntTxt.getText()) + 1));

                    int itemSubTotal = price * Integer.parseInt(cartItemQntTxt.getText());
                    cartItemTotalPriceLbl.setText(""+ itemSubTotal);

                    CustomerModel.updateCartItemQntAndTotal(Integer.parseInt(cartItemQntTxt.getText()), itemId, itemSubTotal);
                    cartSubTotalPriceLabel.setText("LKR " + CustomerModel.getCartOpenSectionSubTotal());
//                    CustomerModel.updateTotalAccordingToQnt();
                }
            });

            cartItemQntMinusBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!Objects.equals(cartItemQntTxt.getText(), "1")){
                        cartItemQntTxt.setText(""+(Integer.parseInt(cartItemQntTxt.getText()) - 1));

                        int itemSubTotal = price * Integer.parseInt(cartItemQntTxt.getText());
                        cartItemTotalPriceLbl.setText(""+ itemSubTotal);

                        int itemId = (int)cartItemDetails.get(4);
                        CustomerModel.updateCartItemQntAndTotal(Integer.parseInt(cartItemQntTxt.getText()), itemId, itemSubTotal);
                        cartSubTotalPriceLabel.setText("LKR " + CustomerModel.getCartOpenSectionSubTotal());
                    }
                }
            });

            JLabel cartItemRemoveLbl = new JLabel("X");
            cartItemRemoveLbl.setFont(new Font(null, Font.PLAIN, 16));
            cartItemRemoveLbl.setBounds(484, 0, 20, 20);
            cartItemRemoveLbl.setForeground(Color.WHITE);
            cartItemPanel.add(cartItemRemoveLbl);

            cartItemRemoveLbl.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(CustomerModel.deleteCartItem(itemId).equals("close")){
                        setCartContainerPanelVisibility(false);
                        CustomerModel.setIsCartContainerPanelOpen(false);
                        CustomerModel.setOpenCartId(0);
                    }else{
                        List<List<Object>> cartItemsDetails = CustomerModel.getCartItems(CustomerModel.getOpenCartId(), CustomerModel.getCartOpenOption());
                        loadCartItems(cartItemsDetails);
                    }
                }
            });

            subtotal += (price * qnt);
            totalQnt += qnt;
        }

        cartSubTotalPriceLabel.setText("LKR " + subtotal);

        if(totalQnt <= 100){
            cartItemsCountLbl.setText(totalQnt + " items");
        }else{
            cartItemsCountLbl.setText("100+ items");
        }

        int totalPreferredHeight = 0;
        for (Component component : cartItemsPanel.getComponents()) {
            totalPreferredHeight += component.getPreferredSize().height + 5;
        }
        cartItemsPanel.setPreferredSize(new Dimension(518, totalPreferredHeight));

        cartItemsPanel.revalidate();
        cartItemsPanel.repaint();
    }

    public void setCartPickUpOptionDisableAndEnable(String enablility){
        if(enablility.startsWith("d")){
            cartPickUpBtn.setEnabled(false);
            cartPickUpBtn.setBackground(new Color(0x525252));
            cartPickUpBtn.setForeground(new Color(0x676767));
        }else{
            cartPickUpBtn.setEnabled(true);
            cartPickUpBtn.setBackground(new Color(0xF1F1F1));
            cartPickUpBtn.setForeground(Color.BLACK);
        }
    }

    public void setCartDeliveryOptionDisableAndEnable(String enablility){
        if(enablility.startsWith("d")){
            cartDeliveryBtn.setEnabled(false);
            cartDeliveryBtn.setBackground(new Color(0x525252));
            cartDeliveryBtn.setForeground(new Color(0x676767));
        }else{
            cartDeliveryBtn.setEnabled(true);
            cartDeliveryBtn.setBackground(new Color(0xF1F1F1));
            cartDeliveryBtn.setForeground(Color.BLACK);
        }
    }

    public void setCartOpenOptionStyles(String openOption){
        if(openOption.startsWith("d")){
            cartDeliveryBtn.setBackground(new Color(0x191919));
            cartDeliveryBtn.setForeground(Color.WHITE);
            if(cartPickUpBtn.isEnabled()){
                cartPickUpBtn.setBackground(Color.WHITE);
                cartPickUpBtn.setForeground(Color.BLACK);
            }
        }else{
            cartPickUpBtn.setBackground(new Color(0x191919));
            cartPickUpBtn.setForeground(Color.WHITE);
            if(cartDeliveryBtn.isEnabled()){
                cartDeliveryBtn.setBackground(Color.WHITE);
                cartDeliveryBtn.setForeground(Color.BLACK);
            }
        }
    }

    public void addCartDeliveryBtnListener(ActionListener listener){
        cartDeliveryBtn.addActionListener(listener);
    }

    public void addCartPickUpBtnListener(ActionListener listener){
        cartPickUpBtn.addActionListener(listener);
    }

    public void setCartDeliverAddressBarVisibility(Boolean visibility){
        cartOrderAddressTxt.setVisible(visibility);
    }

    public void setOpenCartBranchName(){
        cartRestNameLbl.setText(CustomerModel.getOpenCartBranchName());
    }




    /////cart list///////
    JScrollPane cartListScrollPane;
    public void setCartsListPanelVisibility(Boolean visibility){
        cartListScrollPane.setVisible(visibility);
    }

    public void setCartsListPanelOpenPosition(int y){
        cartListScrollPane.setBounds(1154, y, 333, 234);
    }

    public void loadCartsToCartListPanel(List<List<Object>> carts){
        cartsListPanel.removeAll();

        for(List<Object> cart : carts){
            int cartId = (int)cart.get(0);
            int totalItems = (int)cart.get(1);
            String branchName = (String)cart.get(2);
            BufferedImage restImage = (BufferedImage)cart.get(3);

            JPanel cartsListCartPanel = new JPanel();
            cartsListCartPanel.setPreferredSize(new Dimension(302, 53));
            cartsListCartPanel.setBackground(new Color(0x6A6969));
            cartsListCartPanel.setLayout(null);
            cartsListCartPanel.putClientProperty("ID", String.valueOf(cartId));
            cartsListPanel.add(cartsListCartPanel);

            cartsListCartPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CustomerModel.setIsCartsListPanelOpen(false);
                    setCartsListPanelVisibility(false);

                    String cartId = (String) cartsListCartPanel.getClientProperty("ID");

                    /////
                    setCartContainerPanelVisibility(true);
                    CustomerModel.setIsCartContainerPanelOpen(true);

                    CustomerModel.setOpenCartId(Integer.parseInt(cartId));

                    CustomerModel.setOpenCartRestAllowTypeAndNameAndId(Integer.parseInt(cartId));
                    setOpenCartBranchName();

                    String allowType = CustomerModel.getOpenCartRestAllowType();

                    if(allowType.equals("Delivery")){
                        setCartPickUpOptionDisableAndEnable("disable");
                        setCartDeliveryOptionDisableAndEnable("enable");
                        setCartOrderAddressTxtVisibility(true);

                        CustomerModel.setCartOpenOption("Delivery");
                        setCartOpenOptionStyles("delivery");
                    }else if(allowType.equals("Pick Up")){
                        setCartDeliveryOptionDisableAndEnable("disable");
                        setCartPickUpOptionDisableAndEnable("enable");
                        setCartOrderAddressTxtVisibility(false);

                        CustomerModel.setCartOpenOption("Pickup");
                        setCartOpenOptionStyles("pickup");
                    }else{
                        if(CustomerModel.checkIfDeliveryOrdersInTheCart(Integer.parseInt(cartId))){
                            setCartDeliveryOptionDisableAndEnable("enable");
                            setCartPickUpOptionDisableAndEnable("enable");
                            setCartOrderAddressTxtVisibility(true);

                            CustomerModel.setCartOpenOption("Delivery");
                            setCartOpenOptionStyles("delivery");

                            allowType = "Delivery";
                        }else{
                            setCartDeliveryOptionDisableAndEnable("enable");
                            setCartPickUpOptionDisableAndEnable("enable");
                            setCartOrderAddressTxtVisibility(false);

                            CustomerModel.setCartOpenOption("Pickup");
                            setCartOpenOptionStyles("pickup");

                            allowType = "Pick Up";
                        }
                    }

                    List<List<Object>> cartItemsDetails = CustomerModel.getCartItems(Integer.parseInt(cartId), allowType);
                    loadCartItems(cartItemsDetails);
                    //////
                }
            });

            JPanel cartsListCartPanelImagePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (restImage != null) {
                        g.drawImage(restImage, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            cartsListCartPanelImagePanel.setBounds(3, 3, 53, 47);
            cartsListCartPanel.add(cartsListCartPanelImagePanel);

            JLabel cartsListCartPanelBranchNameLbl = new JLabel(branchName);
            cartsListCartPanelBranchNameLbl.setFont(new Font(null, Font.BOLD, 16));
            cartsListCartPanelBranchNameLbl.setForeground(Color.WHITE);
            cartsListCartPanelBranchNameLbl.setBounds(70, 2, 230, 25);
            cartsListCartPanel.add(cartsListCartPanelBranchNameLbl);

            JLabel cartsListCartPanelItemsCountLabel = new JLabel(totalItems + " items");
            cartsListCartPanelItemsCountLabel.setFont(new Font(null, Font.PLAIN, 12));
            cartsListCartPanelItemsCountLabel.setForeground(Color.WHITE);
            cartsListCartPanelItemsCountLabel.setBounds(70, 28, 200, 20);
            cartsListCartPanel.add(cartsListCartPanelItemsCountLabel);

            int totalPreferredHeight = 0;
            for (Component component : cartsListPanel.getComponents()) {
                totalPreferredHeight += component.getPreferredSize().height;
            }
            cartsListPanel.setPreferredSize(new Dimension(333, totalPreferredHeight));
        }

        cartsListPanel.revalidate();
        cartsListPanel.repaint();
    }

    public void addHomeCartBtnPanelListener(MouseListener listener){
        homePanelCartBtnPanel.addMouseListener(listener);
    }

    public void addInCartCartSelectBtnPanelListener(MouseListener listener){
        cartSelectBtnPanel.addMouseListener(listener);
    }

    public void addCartPlaceOrderBtnListener(ActionListener listener){
        cartPlaceOrderBtn.addActionListener(listener);
    }


    ///place the order//////
    public void clearOpenCart(){
        cartItemsPanel.removeAll();
        cartItemsPanel.revalidate();
        cartItemsPanel.repaint();
    }

    public String getOrderAddress(){
        return cartOrderAddressTxt.getText();
    }

    public void displayEnterAddressMessage(){
        JOptionPane.showMessageDialog(restaurantPanel, "Please enter your address the package needs to be delivered!", null, JOptionPane.ERROR_MESSAGE);
    }

    public void setCartOrderAddressTxtVisibility(Boolean visibility){
        cartOrderAddressTxt.setVisible(visibility);
    }

    public Boolean getCartOrderAddressTxtVisibility(){
        return cartOrderAddressTxt.isVisible();
    }


    //////delivery sign up //////
    public void setDeliverySignUpContainerVisibility(Boolean visibility){
        deliverySignUpContainer.setVisible(visibility);
    }

    public void addDeliverySignUpContainerCloseLblListener(MouseListener listener){
        deliverySignUpContainerCloseLbl.addMouseListener(listener);
    }

    public void addDeliverySignUpBtnListener(ActionListener listener){
        deliverySignUpBtn.addActionListener(listener);
    }

    public String[] getDeliveryRegistrationDetails(){
        String[] details = new String[7];
        details[0] =  deliveryUsernameTxt.getText();
        details[1] = deliveryEmailTxt.getText();
        details[2] = deliveryNicTxt.getText();
        details[3] = deliveryVehicleNumberTxt.getText();
        details[4] = (String) deliveryVehicleTypeComboBox.getSelectedItem();
        details[5] = deliveryFullNameTxt.getText();
        details[6] = deliveryTelTxt.getText();

        return  details;
    }
    public void displayDeliverySignUpValidationMsge(String msge){
        JOptionPane.showMessageDialog(deliverySignUpContainer, msge, null, JOptionPane.ERROR_MESSAGE);
    }

    public void setDeliverySignUpEmailConfirmPanelVisibility(Boolean visibility){
        deliverySignUpEmailConfirmPanel.setVisible(visibility);
    }

    public void setDeliverySignUpPanelVisibility(Boolean visibility){
        deliverySignUpPanel.setVisible(visibility);
    }

    public void addDeliverySignUpEmailConfirmPanelBackBtnListener(MouseListener listener){
        deliverySignUpEmailConfirmPanelBackBtn.addMouseListener(listener);
    }

    public void addDeliveryEmailConfirmBtnListener(ActionListener listener){
        deliveryEmailConfirmBtn.addActionListener(listener);
    }

    public String getDeliveryConfirmEmailTextFieldValue(){
        return deliveryConfirmEmailTxt.getText();
    }

    public void displayDeliverySignUpSuccessfullMsge(String email){
        JOptionPane.showMessageDialog(deliverySignUpContainer, "You are under review by our administrative team and we will send an email to " + email + " with your username and password once your registration is successfull", null, JOptionPane.INFORMATION_MESSAGE);
    }

    public void clearDeliverySignUpContainer(){
        deliveryUsernameTxt.setText("");
        deliveryEmailTxt.setText("");
        deliveryNicTxt.setText("");
        deliveryVehicleNumberTxt.setText("");
        deliveryVehicleTypeComboBox.setSelectedIndex(0);
        deliveryFullNameTxt.setText("");
        deliveryTelTxt.setText("");
    }

    ///////my orders//////
    public void addSideBarHomeMyOrdersPanelListener(MouseListener listener){
        sideBarHomeMyOrdersPanel.addMouseListener(listener);
    }

    public void setSideBarHomeMyOrdersListPanelVisibility(Boolean visibility){
        sideBarHomeMyOrdersListPanel.setVisible(visibility);
    }

    public void addMyOrdersListPanelBackBtnPanelListener(MouseListener listener){
        myOrdersListPanelBackBtnPanel.addMouseListener(listener);
    }

    public void loadLiveOrdersToSideBar(List<List<Object>> orders){
        sideBarHomeMyOrdersViewPanel.removeAll();

        for(List<Object> order : orders){
            JPanel sideBarHomeMyOrdersListOrderPanel = new JPanel();
            sideBarHomeMyOrdersListOrderPanel.setPreferredSize(new Dimension(300, 80));
            sideBarHomeMyOrdersListOrderPanel.setBackground(new Color(0x6C6C6C));
            sideBarHomeMyOrdersListOrderPanel.setLayout(null);
            sideBarHomeMyOrdersListOrderPanel.putClientProperty("ID", String.valueOf((int)order.get(3)));
            sideBarHomeMyOrdersViewPanel.add(sideBarHomeMyOrdersListOrderPanel);

            JLabel ordersListOrderPanelOrderIdLabel = new JLabel("ORDER ID:");
            ordersListOrderPanelOrderIdLabel.setFont(new Font(null, Font.PLAIN, 15));
            ordersListOrderPanelOrderIdLabel.setForeground(Color.WHITE);
            ordersListOrderPanelOrderIdLabel.setBounds(5, 4, 80, 20);
            sideBarHomeMyOrdersListOrderPanel.add(ordersListOrderPanelOrderIdLabel);

            JLabel ordersListOrderPanelOrderId = new JLabel("#" + order.getFirst());
            ordersListOrderPanelOrderId.setFont(new Font(null, Font.PLAIN, 15));
            ordersListOrderPanelOrderId.setForeground(Color.WHITE);
            ordersListOrderPanelOrderId.setBounds(90, 4, 80, 20);
            sideBarHomeMyOrdersListOrderPanel.add(ordersListOrderPanelOrderId);

            JLabel ordersListOrderPanelOrderTotal = new JLabel("LKR " + (int)order.get(1));
            ordersListOrderPanelOrderTotal.setFont(new Font(null, Font.PLAIN, 12));
            ordersListOrderPanelOrderTotal.setForeground(Color.WHITE);
            ordersListOrderPanelOrderTotal.setBounds(5, 35, 130, 15);
            sideBarHomeMyOrdersListOrderPanel.add(ordersListOrderPanelOrderTotal);

            JLabel ordersListOrderPanelOrderRestName = new JLabel((String) order.get(4));
            ordersListOrderPanelOrderRestName.setFont(new Font(null, Font.PLAIN, 12));
            ordersListOrderPanelOrderRestName.setForeground(Color.WHITE);
            ordersListOrderPanelOrderRestName.setBounds(5, 60, 150, 15);
            sideBarHomeMyOrdersListOrderPanel.add(ordersListOrderPanelOrderRestName);

            String status = (String)order.get(2);
            String orderType = ((String)order.get(7)).toLowerCase();
            String statusValue;
            if(Objects.equals(status, "new")){
                statusValue = "reviewing";
            }else if(Objects.equals(status, "accept") || Objects.equals(status, "preparing")){
                statusValue = "processing";
            }else if(Objects.equals(status,"delivering")){
                statusValue = "delivering";
            }else if(Objects.equals(status, "prepared") && Objects.equals(orderType, "delivery")){
                statusValue = "to be delivered";
            }else{
                statusValue = "to be picked-up";
            }

            JLabel ordersListOrderPanelOrderStatus = new JLabel(statusValue);
            ordersListOrderPanelOrderStatus.setFont(new Font(null, Font.BOLD, 15));
            ordersListOrderPanelOrderStatus.setForeground(Color.GREEN);
            ordersListOrderPanelOrderStatus.setBounds(145, 30, 150, 20);
            ordersListOrderPanelOrderStatus.setHorizontalAlignment(SwingConstants.RIGHT);
            sideBarHomeMyOrdersListOrderPanel.add(ordersListOrderPanelOrderStatus);

            JLabel ordersListOrderPanelOrderRestAddress = new JLabel((String) order.get(5));
            ordersListOrderPanelOrderRestAddress.setBounds(0, 0, 150, 20);
            ordersListOrderPanelOrderRestAddress.setVisible(false);
            sideBarHomeMyOrdersListOrderPanel.add(ordersListOrderPanelOrderRestAddress);

            JLabel ordersListOrderPanelOrderRestMobile = new JLabel((String)order.get(6));
            ordersListOrderPanelOrderRestMobile.setBounds(0, 0, 150, 20);
            ordersListOrderPanelOrderRestMobile.setVisible(false);
            sideBarHomeMyOrdersListOrderPanel.add(ordersListOrderPanelOrderRestMobile);

            sideBarHomeMyOrdersListOrderPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CustomerModel.setOpenLiveOrderInfoPanelOrderId((int)order.get(3));
                    List<List<Object>> orderItems = CustomerModel.getLiveOrderItems(Integer.parseInt((String)sideBarHomeMyOrdersListOrderPanel.getClientProperty("ID")));
                    loadLiveOrderItems(orderItems);
                    liveOrderInfoPanel.setVisible(true);
                    CustomerModel.setIsLiveOrderInfoPanelOpen(true);

                    liveOrderStatus.setText(ordersListOrderPanelOrderStatus.getText());
                    liveOrderOrderType.setText((String)order.get(7));
                    liveOrderRestName.setText(ordersListOrderPanelOrderRestName.getText());
                    liveOrderRestTel.setText(ordersListOrderPanelOrderRestMobile.getText());
                    liveOrderTotal.setText(ordersListOrderPanelOrderTotal.getText());
                    liveOrderRestAddress.setText(ordersListOrderPanelOrderRestAddress.getText());
                }
            });
        }

        int totalPreferredHeight = 0;
        for (Component component : sideBarHomeMyOrdersViewPanel.getComponents()) {
            totalPreferredHeight += component.getPreferredSize().height + 5;
        }
        sideBarHomeMyOrdersViewPanel.setPreferredSize(new Dimension(320, totalPreferredHeight));

        sideBarHomeMyOrdersViewPanel.revalidate();
        sideBarHomeMyOrdersViewPanel.repaint();
    }

    public void loadLiveOrderItems(List<List<Object>> orderItems){
        liveOrderInfoOrderItemsPanel.removeAll();
        liveOrderInfoOrderItemsPanel.revalidate();
        liveOrderInfoOrderItemsPanel.repaint();

        for(List<Object> orderItem : orderItems){
            JPanel liveOrderInfoOrderItemPanel = new JPanel();
            liveOrderInfoOrderItemPanel.setPreferredSize(new Dimension(842, 74));
            liveOrderInfoOrderItemPanel.setBackground(new Color(0x5B5B5B));
            liveOrderInfoOrderItemPanel.setLayout(null);
            liveOrderInfoOrderItemsPanel.add(liveOrderInfoOrderItemPanel);

            BufferedImage itemImage = (BufferedImage) orderItem.get(3);
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
            liveOrderInfoOrderItemPanel.add(orderItemImagePanel);

            JLabel orderItemNameLabel = new JLabel((String) orderItem.get(0));
            orderItemNameLabel.setForeground(Color.WHITE);
            orderItemNameLabel.setBounds(110, 20, 350, 30);
            orderItemNameLabel.setFont(new Font(null, Font.PLAIN, 16));
            liveOrderInfoOrderItemPanel.add(orderItemNameLabel);

            JLabel orderItemQntLabel = new JLabel("x" + (int)orderItem.get(1));
            orderItemQntLabel.setForeground(Color.WHITE);
            orderItemQntLabel.setFont(new Font(null, Font.PLAIN, 16));
            orderItemQntLabel.setBounds(470, 20, 120, 30);
            liveOrderInfoOrderItemPanel.add(orderItemQntLabel);

            JLabel orderItemTotalPrice = new JLabel("LKR " + (int)orderItem.get(2));
            orderItemTotalPrice.setBounds(650, 20, 190,30);
            orderItemTotalPrice.setForeground(Color.WHITE);
            orderItemTotalPrice.setFont(new Font(null, Font.BOLD, 18));
            liveOrderInfoOrderItemPanel.add(orderItemTotalPrice);
        }

        int totalPreferredHeight = 0;
        for (Component component : liveOrderInfoOrderItemsPanel.getComponents()) {
            totalPreferredHeight += component.getPreferredSize().height + 5;
        }
        liveOrderInfoOrderItemsPanel.setPreferredSize(new Dimension(850, totalPreferredHeight));
    }

    public void addLiveOrderInfoPanelCloseBtnListener(ActionListener listener){
        liveOrderInfoPanelCloseBtn.addActionListener(listener);
    }

    public void closeLiveOrderInfoPanel(){
        liveOrderInfoPanel.setVisible(false);
    }

    public void loadOpenLiveOrderStatus(String status){
        liveOrderStatus.setText(status);
    }

    public String getOpenLiveOrderType(){
        return liveOrderOrderType.getText();
    }



    ///////search panel/////////
    public void addHomeSearchBarPanelListener(MouseListener listener){
        searchBarPanel.addMouseListener(listener);
    }

    public void addSearchPanelSearchBarFocusableListener(FocusListener listener){
        searchPanelSearchBar.addFocusListener(listener);
    }

    public void removeSearchBarPlaceHolder(){
        searchPanelSearchBar.setText("");
        searchPanelSearchBar.setForeground(Color.BLACK);
    }

    public void addSearchBarPlaceHolder(){
        searchPanelSearchBar.setText("Search Restaurants");
        searchPanelSearchBar.setForeground(new Color(0x4C4C4C));
    }

    public String getSearchBarText(){
        return searchPanelSearchBar.getText();
    }

    public void addSearchPanelSearchBarTextChangeListener(DocumentListener listener){
        searchPanelSearchBar.getDocument().addDocumentListener(listener);
    }

    public void setRestSearchPanelVisibility(Boolean visibility){
        restSearchPanel.setVisible(visibility);
    }

    public void addSearchPanelBackBtnListener(MouseListener listener){
        searchPanelBackBtnPanel.addMouseListener(listener);
    }

    public void clearSearchBar(){
        searchPanelSearchBar.setText("");
    }

    public void clearSearchedResultsPanel(){
        restSearchResultsPanel.removeAll();
        restSearchResultsPanel.revalidate();
        restSearchResultsPanel.repaint();
    }




    ///////my favorites panel/////////
    public void addSideBarHomeFavoritesPanelListener(MouseListener listener){
        sideBarHomeFavoritesPanel.addMouseListener(listener);
    }

    public void setMyFavoritesPanelVisibility(Boolean visibility){
        myFavoritesPanel.setVisible(visibility);
    }

    public void addMyFavoriteBackBtnPanelListener(MouseListener listener){
        myFavoriteBackBtnPanel.addMouseListener(listener);
    }

    public void addFavRestsBtnListener(ActionListener listener){
        favRestsBtn.addActionListener(listener);
    }

    public void addFavMealsBtnListener(ActionListener listener){
        favMealsBtn.addActionListener(listener);
    }

    public void setClickedFavBtnStyles(String clickedBtn){
        if(Objects.equals(clickedBtn, "rests")){
            favRestsBtn.setBackground(new Color(0x1035F4));
            favRestsBtn.setForeground(Color.WHITE);
            favMealsBtn.setBackground(new Color(0xC3C3C3));
            favMealsBtn.setForeground(Color.BLACK);
        }else{
            favRestsBtn.setBackground(new Color(0xC3C3C3));
            favRestsBtn.setForeground(Color.BLACK);
            favMealsBtn.setBackground(new Color(0x1035F4));
            favMealsBtn.setForeground(Color.WHITE);
        }
    }

    public void loadFavMeals(List<List<Object>> favMeals){
        favItemsPanel.removeAll();

        for(List<Object> favMeal : favMeals){
            String name = (String) favMeal.getFirst();
            int price = (int) favMeal.get(1);
            String description = (String) favMeal.get(2);
            BufferedImage image = (BufferedImage) favMeal.get(3);
            String foodItemId = String.valueOf((int) favMeal.get(4));
            int restId = (int)favMeal.get(5);

            JPanel menuItemPanel = new JPanel();
            menuItemPanel.setPreferredSize(new Dimension(555, 194));
            menuItemPanel.setBackground(new Color(0xFFD7F2));
            menuItemPanel.putClientProperty("ID", foodItemId);
            menuItemPanel.setLayout(null);
            favItemsPanel.add(menuItemPanel);

            menuItemPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CustomerModel.checkIfOpenFavMealRestOpen(restId);

                    if(!CustomerModel.getIsRestaurantOpen()){
                        JOptionPane.showMessageDialog(myFavoritesPanel, "Restaurant is closed at this time", null, JOptionPane.INFORMATION_MESSAGE);
                    }else if(CustomerModel.getLoggedCustomerID() == 0){
                        userLogSignupContainer.setVisible(true);
                        restaurantPanel.setVisible(false);
                    }else{
                        CustomerModel.setOpenFavMealRestId(restId);
                        CustomerModel.setOpenRestaurantId(restId); ////////////////////////////////

                        addToOrderPanel.setVisible(true);
                        CustomerModel.setIsAddToOrderPanelOpen(true);
                        CustomerModel.setOpenFoodItemId(Integer.parseInt((String)menuItemPanel.getClientProperty("ID")));

                        if(Objects.equals(CustomerModel.getOpenFavMealRestAllowType(restId), "All")){
                            group.clearSelection();

                            orderItemDeliveryRadioBtn.setVisible(true);
                            orderItemPickupRadioBtn.setVisible(true);

                            orderItemPickupLbl.setVisible(true);
                            orderItemDeliveryLbl.setVisible(true);

                        }else if(Objects.equals(CustomerModel.getOpenFavMealRestAllowType(restId), "Delivery")){
                            group.clearSelection();

                            orderItemDeliveryRadioBtn.setVisible(true);
                            orderItemPickupRadioBtn.setVisible(false);

                            orderItemDeliveryRadioBtn.setSelected(true);

                            orderItemPickupLbl.setVisible(false);
                            orderItemDeliveryLbl.setVisible(true);
                        }else{
                            group.clearSelection();

                            orderItemDeliveryRadioBtn.setVisible(false);
                            orderItemPickupRadioBtn.setVisible(true);

                            orderItemPickupRadioBtn.setSelected(true);

                            orderItemPickupLbl.setVisible(true);
                            orderItemDeliveryLbl.setVisible(false);
                        }

                        orderItemQntTxt.setText("1");
                    }
                }
            });

            JLabel menuItemNameLbl = new JLabel();
            menuItemNameLbl.setText(name);
            menuItemNameLbl.setBounds(20, 20, 300, 40);
            menuItemNameLbl.setFont(new Font(null, Font.PLAIN, 18));
            menuItemNameLbl.setVerticalAlignment(SwingConstants.TOP);
            menuItemPanel.add(menuItemNameLbl);

            JTextArea menuItemDescription = new JTextArea();
            menuItemDescription.setText(description);
            menuItemDescription.setBounds(20, 60, 260, 80);
            menuItemDescription.setFont(new Font(null, Font.PLAIN, 12));
            menuItemDescription.setLineWrap(true);
            menuItemDescription.setWrapStyleWord(true);
            menuItemDescription.setEditable(false);
            menuItemDescription.setFocusable(false);
            menuItemDescription.setBackground(null);
            menuItemPanel.add(menuItemDescription);

            JLabel menuItemPrice = new JLabel();
            menuItemPrice.setText("LKR " + price);
            menuItemPrice.setFont(new Font(null, Font.BOLD, 16));
            menuItemPrice.setBounds(20, 140, 300, 50);
            menuItemPanel.add(menuItemPrice);

            JPanel menuItemImagePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (image != null) {
                        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            menuItemImagePanel.setBounds(355, 5, 196, 184);
            menuItemImagePanel.setBackground(Color.GRAY);
            menuItemPanel.add(menuItemImagePanel);


            try{
                final File[] imageFile = new File[1];
                if(CustomerModel.getLoggedCustomerID() != 0){
                    if(CustomerModel.checkIfTheRestOrMealIsInFavList(0, "meal", Integer.parseInt(foodItemId))){
                        imageFile[0] = new File("Images/favorites.png");
                    }else{
                        imageFile[0] = new File("Images/hart.png");
                    }
                }else{
                    imageFile[0] = new File("Images/hart.png");
                }

                final BufferedImage[] FavoriteImg = {ImageIO.read(imageFile[0])};
                JPanel menuItemAddToFavoriteBtnPanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        if (FavoriteImg[0] != null) {
                            g.drawImage(FavoriteImg[0], 0, 0, getWidth(), getHeight(), this);
                        }
                    }
                };
                menuItemAddToFavoriteBtnPanel.setBounds(310, 20, 30, 30);
                menuItemAddToFavoriteBtnPanel.setBackground(new Color(0xFFD7F2));
                menuItemPanel.add(menuItemAddToFavoriteBtnPanel);


                menuItemAddToFavoriteBtnPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            if(CustomerModel.getLoggedCustomerID() != 0){
                                if(CustomerModel.checkIfTheRestOrMealIsInFavList(0, "meal", Integer.parseInt(foodItemId))){
                                    imageFile[0] = new File("Images/hart.png");
                                    FavoriteImg[0] = ImageIO.read(imageFile[0]);
                                    menuItemAddToFavoriteBtnPanel.repaint();
                                    CustomerModel.updateFavorites(0, "remove", "meal", Integer.parseInt(foodItemId));
                                }else{
                                    imageFile[0] = new File("Images/favorites.png");
                                    FavoriteImg[0] = ImageIO.read(imageFile[0]);
                                    menuItemAddToFavoriteBtnPanel.repaint();
                                    CustomerModel.updateFavorites(0, "add", "meal", Integer.parseInt(foodItemId));
                                }
                            }
                        }catch(IOException ex){
                            ex.printStackTrace();
                        }
                    }
                });
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }

        int componentsPerRow = 2;
        int componentHeight = 194;
        int numRows = (int) Math.ceil((double) favItemsPanel.getComponentCount() / componentsPerRow);
        int totalPreferredRowHeight = componentHeight * numRows;
        favItemsPanel.setPreferredSize(new Dimension(1350, totalPreferredRowHeight + 50));

        favItemsPanel.revalidate();
        favItemsPanel.repaint();
    }
}
