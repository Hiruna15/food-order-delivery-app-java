import com.sun.source.tree.ForLoopTree;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.BorderUIResource;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DriverView {
    private JPanel sideBarBtnPanel, sideBarPanel;
    private JButton sideBarLogOutBtn;
    private JFrame homeFrame;
    private JPanel ordersPanel;
    private JPanel deliveryOrderPanel;
    private JLabel deliveryOrderPanelOrderId, deliveryOrderPanelBranchName;
    private JPanel deliveryOrderCheckOutPanel;
    private JPanel mainContainerPanel;
    private JPanel orderCheckOutPanelBackBtnPanel;
    private JLabel deliveryOrderTotalLabel, checkOutPanelCustomerNameLbl, checkOutPanelRestNameLbl, deliverOrderCode;
    private JButton orderCheckOutBtn;
    private JTextArea checkOutPanelDeliverAddress, checkOutPanelPickUpAddress;
    private JPanel deliverOrderItemsPanel;
    private JButton paymentDoneBtn;
    private JPanel deliveryOrderPaymentPanel;
    private JLabel total, subTotal;
    private JTextField deliveryChargeText;
    private JTextField paidAmountText;
    private JTextField balanceText;

    public DriverView(){
        homeFrame = new JFrame();
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(1500, 800);
        homeFrame.setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1500, 800));
        homeFrame.add(layeredPane);

        JPanel topBarPanel = new JPanel();
        topBarPanel.setLayout(null);
        topBarPanel.setBounds(0, 0, 1500, 82);
        topBarPanel.setBackground(new Color(0x323232));
        layeredPane.add(topBarPanel, JLayeredPane.DEFAULT_LAYER);

        try {
            File imageFile = new File("images/hamburger-menu.png");
            BufferedImage menuBarImg = ImageIO.read(imageFile);
            sideBarBtnPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (menuBarImg != null) {
                        g.drawImage(menuBarImg, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };

            sideBarBtnPanel.setBounds(20, 15, 50, 50);
            sideBarBtnPanel.setBackground(new Color(0x323232));
            topBarPanel.add(sideBarBtnPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        JLabel logoLabel = new JLabel("LOGO");
        logoLabel.setFont(new Font(null, Font.BOLD, 35));
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setBounds(600, 15, 200, 50);
        topBarPanel.add(logoLabel);

        JPanel profileIconPanel = new JPanel();
        profileIconPanel.setBounds(1400, 15, 50, 50);
        topBarPanel.add(profileIconPanel);

        mainContainerPanel = new JPanel();
        mainContainerPanel.setBounds(0, 82, 1500, 718);
        mainContainerPanel.setBackground(new Color(0x4F4F4F));
        mainContainerPanel.setLayout(null);
        mainContainerPanel.setVisible(true);
        layeredPane.add(mainContainerPanel, JLayeredPane.DEFAULT_LAYER);

        JLabel allOrdersLabel = new JLabel("All Orders");
        allOrdersLabel.setFont(new Font(null, Font.BOLD, 35));
        allOrdersLabel.setBounds(70, 40, 200, 40);
        allOrdersLabel.setForeground(Color.WHITE);
        mainContainerPanel.add(allOrdersLabel);

        ////////////////
        ordersPanel = new JPanel();
        ordersPanel.setPreferredSize(new Dimension(1388, 560));
        ordersPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 80, 20));

        JScrollPane ordersPanelScrollPane = new JScrollPane(ordersPanel);
        ordersPanelScrollPane.setBounds(50, 95, 1388, 567);
        ordersPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainContainerPanel.add(ordersPanelScrollPane);

        /////////////////

        sideBarPanel = new JPanel();
        sideBarPanel.setBounds(0, 82, 311, 718);
        sideBarPanel.setBackground(new Color(0x2C2C2C));
        sideBarPanel.setVisible(false);
        sideBarPanel.setLayout(null);
        layeredPane.add(sideBarPanel, JLayeredPane.PALETTE_LAYER);

        try {
            File imageFile = new File("images/completed.png");
            BufferedImage completedOrdersIcon = ImageIO.read(imageFile);
            JPanel completedOrdersIconPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (completedOrdersIcon != null) {
                        g.drawImage(completedOrdersIcon, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            completedOrdersIconPanel.setBounds(40, 40, 30, 30);
            completedOrdersIconPanel.setBackground(new Color(0x2C2C2C));
            sideBarPanel.add(completedOrdersIconPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        JLabel sideBarLabel1 = new JLabel("Completed orders");
        sideBarLabel1.setForeground(Color.WHITE);
        sideBarLabel1.setBounds(90, 40, 250, 30);
        sideBarLabel1.setFont(new Font(null, Font.PLAIN, 17));
        sideBarPanel.add(sideBarLabel1);

        try {
            File imageFile = new File("images/earn.png");
            BufferedImage earningsIcon = ImageIO.read(imageFile);
            JPanel earningsIconPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (earningsIcon != null) {
                        g.drawImage(earningsIcon, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            earningsIconPanel.setBounds(40, 100, 30, 30);
            earningsIconPanel.setBackground(new Color(0x2C2C2C));
            sideBarPanel.add(earningsIconPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }

        JLabel sideBarLabel2 = new JLabel("Earnings");
        sideBarLabel2.setForeground(Color.WHITE);
        sideBarLabel2.setBounds(90, 100, 250, 30);
        sideBarLabel2.setFont(new Font(null, Font.PLAIN, 17));
        sideBarPanel.add(sideBarLabel2);

        sideBarLogOutBtn = new JButton("LOG OUT");
        sideBarLogOutBtn.setBounds(15, 620, 280, 45);
        sideBarLogOutBtn.setFocusable(false);
        sideBarLogOutBtn.setBackground(Color.BLACK);
        sideBarLogOutBtn.setForeground(Color.WHITE);
        sideBarPanel.add(sideBarLogOutBtn);

        ///////////////
        deliveryOrderPanel = new JPanel();
        deliveryOrderPanel.setVisible(false);
        deliveryOrderPanel.setBounds(1000, 10, 406, 80);
        deliveryOrderPanel.setLayout(null);
        deliveryOrderPanel.setBackground(new Color(0x02B160));
        mainContainerPanel.add(deliveryOrderPanel);

        JLabel onADeliveryLabel = new JLabel("On a deliivery");
        onADeliveryLabel.setForeground(Color.WHITE);
        onADeliveryLabel.setFont(new Font(null, Font.BOLD, 18));
        onADeliveryLabel.setBounds(20, 30, 150, 20);
        deliveryOrderPanel.add(onADeliveryLabel);

        JLabel deliveryOrderPanelOIDLbl = new JLabel("ORDER ID:");
        deliveryOrderPanelOIDLbl.setFont(new Font(null, Font.PLAIN, 15));
        deliveryOrderPanelOIDLbl.setForeground(Color.WHITE);
        deliveryOrderPanelOIDLbl.setBounds(220, 10, 90, 20);
        deliveryOrderPanel.add(deliveryOrderPanelOIDLbl);

        deliveryOrderPanelOrderId = new JLabel();
        deliveryOrderPanelOrderId.setFont(new Font(null, Font.PLAIN, 15));
        deliveryOrderPanelOrderId.setForeground(Color.WHITE);
        deliveryOrderPanelOrderId.setBounds(320, 10, 100, 20);
        deliveryOrderPanel.add(deliveryOrderPanelOrderId);

        deliveryOrderPanelBranchName = new JLabel("KFC-MATARA");
        deliveryOrderPanelBranchName.setFont(new Font(null, Font.PLAIN, 20));
        deliveryOrderPanelBranchName.setForeground(Color.WHITE);
        deliveryOrderPanelBranchName.setBounds(180, 40, 220, 30);
        deliveryOrderPanelBranchName.setHorizontalAlignment(SwingConstants.CENTER);
        deliveryOrderPanel.add(deliveryOrderPanelBranchName);

        List<Object> deliveryOrderPanelDetails = DriverModel.checkIfDriverHasAOngoingOrder();
        if(!deliveryOrderPanelDetails.isEmpty()){
            deliveryOrderPanel.putClientProperty("ID", String.valueOf((int)deliveryOrderPanelDetails.getFirst()));
            deliveryOrderPanel.setVisible(true);
            DriverModel.setPickedOrderId((int)deliveryOrderPanelDetails.getFirst());
            deliveryOrderPanelOrderId.setText("#" + deliveryOrderPanelDetails.get(1));
            deliveryOrderPanelBranchName.setText((String) deliveryOrderPanelDetails.get(2));
        }
        //////////////

        //////////////////////
        deliveryOrderCheckOutPanel = new JPanel();
        deliveryOrderCheckOutPanel.setBounds(0, 82, 1500, 718);
        deliveryOrderCheckOutPanel.setLayout(null);
        deliveryOrderCheckOutPanel.setVisible(false);
        deliveryOrderCheckOutPanel.setBackground(new Color(0x4F4F4F));
        layeredPane.add(deliveryOrderCheckOutPanel, JLayeredPane.DEFAULT_LAYER);

        JLabel checkOutPanelTopLbl = new JLabel("Delivering Order");
        checkOutPanelTopLbl.setFont(new Font(null, Font.BOLD, 35));
        checkOutPanelTopLbl.setForeground(Color.WHITE);
        checkOutPanelTopLbl.setBounds(60, 30, 300, 50);
        deliveryOrderCheckOutPanel.add(checkOutPanelTopLbl);

        JLabel orderCodeLabel = new JLabel("Order Code: ");
        orderCodeLabel.setBounds(60, 82, 120, 30);
        orderCodeLabel.setForeground(Color.WHITE);
        orderCodeLabel.setFont(new Font(null, Font.PLAIN, 18));
        deliveryOrderCheckOutPanel.add(orderCodeLabel);

        deliverOrderCode = new JLabel("#12345");
        deliverOrderCode.setForeground(Color.WHITE);
        deliverOrderCode.setFont(new Font(null, Font.PLAIN, 16));
        deliverOrderCode.setBounds(180, 82, 200, 30);
        deliveryOrderCheckOutPanel.add(deliverOrderCode);

        JLabel checkOutPanelRestLbl = new JLabel("RESTAURANT:");
        checkOutPanelRestLbl.setFont(new Font(null, Font.PLAIN, 18));
        checkOutPanelRestLbl.setForeground(Color.WHITE);
        checkOutPanelRestLbl.setBounds(60, 120, 150, 25);
        deliveryOrderCheckOutPanel.add(checkOutPanelRestLbl);

        checkOutPanelRestNameLbl = new JLabel("KFC-Matara");
        checkOutPanelRestNameLbl.setFont(new Font(null, Font.PLAIN, 16));
        checkOutPanelRestNameLbl.setBounds(90, 150, 300, 20);
        checkOutPanelRestNameLbl.setForeground(Color.WHITE);
        deliveryOrderCheckOutPanel.add(checkOutPanelRestNameLbl);

        JLabel checkOutPanelCustomerLbl = new JLabel("CUSTOMER:");
        checkOutPanelCustomerLbl.setFont(new Font(null, Font.PLAIN, 18));
        checkOutPanelCustomerLbl.setForeground(Color.WHITE);
        checkOutPanelCustomerLbl.setBounds(60, 215, 150, 25);
        deliveryOrderCheckOutPanel.add(checkOutPanelCustomerLbl);

        checkOutPanelCustomerNameLbl = new JLabel("Hiruna Dilmith");
        checkOutPanelCustomerNameLbl.setFont(new Font(null, Font.PLAIN, 16));
        checkOutPanelCustomerNameLbl.setBounds(90, 245, 300, 20);
        checkOutPanelCustomerNameLbl.setForeground(Color.WHITE);
        deliveryOrderCheckOutPanel.add(checkOutPanelCustomerNameLbl);

        JLabel checkOutPanelPickUpLbl = new JLabel("PICK-UP ADDRESS:");
        checkOutPanelPickUpLbl.setFont(new Font(null, Font.PLAIN, 18));
        checkOutPanelPickUpLbl.setForeground(Color.WHITE);
        checkOutPanelPickUpLbl.setBounds(60, 310, 250, 25);
        deliveryOrderCheckOutPanel.add(checkOutPanelPickUpLbl);

        checkOutPanelPickUpAddress = new JTextArea();
        checkOutPanelPickUpAddress.setText("no 15 henewaththa thihagoda");
        checkOutPanelPickUpAddress.setBounds(90, 340, 300, 80);
        checkOutPanelPickUpAddress.setFont(new Font(null, Font.PLAIN, 16));
        checkOutPanelPickUpAddress.setLineWrap(true);
        checkOutPanelPickUpAddress.setWrapStyleWord(true);
        checkOutPanelPickUpAddress.setEditable(false);
        checkOutPanelPickUpAddress.setFocusable(false);
        checkOutPanelPickUpAddress.setBackground(null);
        checkOutPanelPickUpAddress.setForeground(Color.WHITE);
        deliveryOrderCheckOutPanel.add(checkOutPanelPickUpAddress);

        JLabel checkOutPanelDeliveryLbl = new JLabel("DELIVER ADDRESS:");
        checkOutPanelDeliveryLbl.setFont(new Font(null, Font.PLAIN, 18));
        checkOutPanelDeliveryLbl.setForeground(Color.WHITE);
        checkOutPanelDeliveryLbl.setBounds(60, 440, 250, 25);
        deliveryOrderCheckOutPanel.add(checkOutPanelDeliveryLbl);

        checkOutPanelDeliverAddress = new JTextArea();
        checkOutPanelDeliverAddress.setText("no 15 henewaththa thihagoda");
        checkOutPanelDeliverAddress.setBounds(90, 470, 300, 80);
        checkOutPanelDeliverAddress.setFont(new Font(null, Font.PLAIN, 16));
        checkOutPanelDeliverAddress.setLineWrap(true);
        checkOutPanelDeliverAddress.setWrapStyleWord(true);
        checkOutPanelDeliverAddress.setEditable(false);
        checkOutPanelDeliverAddress.setFocusable(false);
        checkOutPanelDeliverAddress.setBackground(null);
        checkOutPanelDeliverAddress.setForeground(Color.WHITE);
        deliveryOrderCheckOutPanel.add(checkOutPanelDeliverAddress);

        orderCheckOutBtn = new JButton("Check Out");
        orderCheckOutBtn.setBounds(60, 590, 373, 48);
        orderCheckOutBtn.setBackground(new Color(0x02B160));
        orderCheckOutBtn.setForeground(Color.WHITE);
        orderCheckOutBtn.setFont(new Font(null, Font.BOLD, 22));
        orderCheckOutBtn.setFocusable(false);
        deliveryOrderCheckOutPanel.add(orderCheckOutBtn);

        //....
        deliverOrderItemsPanel = new JPanel();
        deliverOrderItemsPanel.setPreferredSize(new Dimension(872, 560));
        deliverOrderItemsPanel.setBackground(new Color(0x2B2B2B));
        deliverOrderItemsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));

        JScrollPane deliverOrderItemsPanelScrollPane = new JScrollPane(deliverOrderItemsPanel);
        deliverOrderItemsPanelScrollPane.setBounds(550, 40, 872, 564);
        deliverOrderItemsPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        deliverOrderItemsPanelScrollPane.setBorder(null);
        deliveryOrderCheckOutPanel.add(deliverOrderItemsPanelScrollPane);



        deliveryOrderTotalLabel = new JLabel("LKR 1500");
        deliveryOrderTotalLabel.setBounds(600, 620, 800, 40);
        deliveryOrderTotalLabel.setFont(new Font(null, Font.BOLD, 30));
        deliveryOrderTotalLabel.setForeground(Color.WHITE);
        deliveryOrderTotalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        deliveryOrderCheckOutPanel.add(deliveryOrderTotalLabel);


        try {
            File imageFile = new File("images/arrowBack.png");
            BufferedImage backBtnImage = ImageIO.read(imageFile);
            orderCheckOutPanelBackBtnPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (backBtnImage != null) {
                        g.drawImage(backBtnImage, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };
            orderCheckOutPanelBackBtnPanel.setBounds(5, 5, 39, 32);
            orderCheckOutPanelBackBtnPanel.setBackground(Color.BLACK);
            deliveryOrderCheckOutPanel.add(orderCheckOutPanelBackBtnPanel);
        }catch(IOException ex){
            ex.printStackTrace();
        }
        //.....

        //////////////////////

        deliveryOrderPaymentPanel = new JPanel();
        deliveryOrderPaymentPanel.setBounds(520, 100, 475, 598);
        deliveryOrderPaymentPanel.setBackground(new Color(0x222222));
        deliveryOrderPaymentPanel.setLayout(null);
        deliveryOrderPaymentPanel.setVisible(false);
        layeredPane.add(deliveryOrderPaymentPanel, JLayeredPane.PALETTE_LAYER);

        JLabel deliveryChargeLabel = new JLabel("Delivery Charge");
        deliveryChargeLabel.setFont(new Font(null, Font.PLAIN, 20));
        deliveryChargeLabel.setForeground(Color.WHITE);
        deliveryChargeLabel.setBounds(80, 50, 200, 30);
        deliveryOrderPaymentPanel.add(deliveryChargeLabel);

        deliveryChargeText = new JTextField();
        deliveryChargeText.setBounds(80, 85, 305, 37);
        deliveryChargeText.setBorder(null);
        deliveryOrderPaymentPanel.add(deliveryChargeText);

        JLabel paidAmountLabel = new JLabel("Paid Amount");
        paidAmountLabel.setFont(new Font(null, Font.PLAIN, 20));
        paidAmountLabel.setForeground(Color.WHITE);
        paidAmountLabel.setBounds(80, 150, 200, 30);
        deliveryOrderPaymentPanel.add(paidAmountLabel);

        paidAmountText = new JTextField();
        paidAmountText.setBounds(80, 185, 305, 37);
        paidAmountText.setBorder(null);
        deliveryOrderPaymentPanel.add(paidAmountText);

        JLabel balanceLabel = new JLabel("Balance");
        balanceLabel.setFont(new Font(null, Font.PLAIN, 20));
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setBounds(80, 250, 200, 30);
        deliveryOrderPaymentPanel.add(balanceLabel);

        balanceText = new JTextField();
        balanceText.setBounds(80, 285, 305, 37);
        balanceText.setBorder(null);
        deliveryOrderPaymentPanel.add(balanceText);

        JLabel subTotalLabel = new JLabel("SUBTOTAL: ");
        subTotalLabel.setFont(new Font(null, Font.BOLD, 20));
        subTotalLabel.setForeground(Color.WHITE);
        subTotalLabel.setBounds(100, 370, 150, 25);
        deliveryOrderPaymentPanel.add(subTotalLabel);

        subTotal = new JLabel();
        subTotal.setFont(new Font(null, Font.BOLD, 20));
        subTotal.setForeground(Color.WHITE);
        subTotal.setBounds(260, 370, 300, 25);
        deliveryOrderPaymentPanel.add(subTotal);

        JLabel totalLabel = new JLabel("TOTAL: ");
        totalLabel.setFont(new Font(null, Font.BOLD, 20));
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setBounds(100, 420, 150, 25);
        deliveryOrderPaymentPanel.add(totalLabel);

        total = new JLabel();
        total.setFont(new Font(null, Font.BOLD, 20));
        total.setForeground(Color.WHITE);
        total.setBounds(260, 420, 300, 25);
        deliveryOrderPaymentPanel.add(total);

        paymentDoneBtn = new JButton("Payment Done");
        paymentDoneBtn.setBounds(80, 500, 305, 37);
        paymentDoneBtn.setFocusable(false);
        paymentDoneBtn.setFont(new Font(null, Font.PLAIN, 20));
        paymentDoneBtn.setForeground(Color.WHITE);
        paymentDoneBtn.setBackground(new Color(0x02B160));
        deliveryOrderPaymentPanel.add(paymentDoneBtn);


        homeFrame.setVisible(true);
    }

    public void addSideBarBtnPanelListener(MouseListener listener){
        sideBarBtnPanel.addMouseListener(listener);
    }

    public void setSideBarPanelVisibility(Boolean visibility){
        sideBarPanel.setVisible(visibility);
    }

    public void addSideBarLogOutBtnListener(ActionListener listener){
        sideBarLogOutBtn.addActionListener(listener);
    }

    public void closeDriverInterface(){
        homeFrame.dispose();
    }

    public void loadOrders(List<List<Object>> orders){
        ordersPanel.setDoubleBuffered(true);
        ordersPanel.removeAll();

        for(List<Object> order : orders){
            JPanel orderPanel = new JPanel();
            orderPanel.setPreferredSize(new Dimension(572, 321));
            orderPanel.setLayout(null);
            orderPanel.setBackground(new Color(0x323232));
            ordersPanel.add(orderPanel);

            JLabel orderIdLabel = new JLabel("ORDER ID:");
            orderIdLabel.setFont(new Font(null, Font.PLAIN, 15));
            orderIdLabel.setBounds(20, 20, 100, 25);
            orderIdLabel.setForeground(Color.WHITE);
            orderPanel.add(orderIdLabel);

            JLabel orderId = new JLabel("#" + order.get(2));
            orderId.setFont(new Font(null, Font.PLAIN, 15));
            orderId.setBounds(130, 20, 100, 25);
            orderId.setForeground(Color.WHITE);
            orderPanel.add(orderId);

            JLabel orderRestName = new JLabel((String) order.get(4));
            orderRestName.setBounds(250, 10, 300, 40);
            orderRestName.setHorizontalAlignment(SwingConstants.RIGHT);
            orderRestName.setFont(new Font(null, Font.BOLD, 20));
            orderRestName.setForeground(Color.WHITE);
            orderPanel.add(orderRestName);


            try {
                File imageFile = new File("images/pickup.png");
                BufferedImage pickupIcon = ImageIO.read(imageFile);
                JPanel pickUpIconPanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        if (pickupIcon != null) {
                            g.drawImage(pickupIcon, 0, 0, getWidth(), getHeight(), this);
                        }
                    }
                };
                pickUpIconPanel.setBounds(20, 65, 30, 30);
                pickUpIconPanel.setBackground(new Color(0x323232));
                orderPanel.add(pickUpIconPanel);
            }catch(IOException ex){
                ex.printStackTrace();
            }

            JLabel pickUpAddressTitleLbl = new JLabel("PICK-UP ADDRESS");
            pickUpAddressTitleLbl.setFont(new Font(null, Font.PLAIN, 14));
            pickUpAddressTitleLbl.setForeground(Color.WHITE);
            pickUpAddressTitleLbl.setBounds(60, 70, 200, 20);
            orderPanel.add(pickUpAddressTitleLbl);

            JTextArea pickupAddressTxtArea = new JTextArea();
            pickupAddressTxtArea.setText((String) order.get(5));
            pickupAddressTxtArea.setBounds(20, 100, 230, 80);
            pickupAddressTxtArea.setFont(new Font(null, Font.PLAIN, 15));
            pickupAddressTxtArea.setLineWrap(true);
            pickupAddressTxtArea.setWrapStyleWord(true);
            pickupAddressTxtArea.setEditable(false);
            pickupAddressTxtArea.setFocusable(false);
            pickupAddressTxtArea.setBackground(null);
            pickupAddressTxtArea.setForeground(Color.WHITE);
            orderPanel.add(pickupAddressTxtArea);

            try {
                File imageFile = new File("images/delivery.png");
                BufferedImage deliveryIcon = ImageIO.read(imageFile);
                JPanel deliveryIconPanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        if (deliveryIcon != null) {
                            g.drawImage(deliveryIcon, 0, 0, getWidth(), getHeight(), this);
                        }
                    }
                };
                deliveryIconPanel.setBounds(320, 65, 30, 30);
                deliveryIconPanel.setBackground(new Color(0x323232));
                orderPanel.add(deliveryIconPanel);
            }catch(IOException ex){
                ex.printStackTrace();
            }

            JLabel deliveryAddressTitleLbl = new JLabel("DELIVER ADDRESS");
            deliveryAddressTitleLbl.setFont(new Font(null, Font.PLAIN, 14));
            deliveryAddressTitleLbl.setForeground(Color.WHITE);
            deliveryAddressTitleLbl.setBounds(355, 70, 200, 20);
            orderPanel.add(deliveryAddressTitleLbl);

            JTextArea deliverAddressTxtArea = new JTextArea();
            deliverAddressTxtArea.setText((String) order.get(3));
            deliverAddressTxtArea.setBounds(320, 100, 230, 80);
            deliverAddressTxtArea.setFont(new Font(null, Font.PLAIN, 15));
            deliverAddressTxtArea.setLineWrap(true);
            deliverAddressTxtArea.setWrapStyleWord(true);
            deliverAddressTxtArea.setEditable(false);
            deliverAddressTxtArea.setFocusable(false);
            deliverAddressTxtArea.setBackground(null);
            deliverAddressTxtArea.setForeground(Color.WHITE);
            orderPanel.add(deliverAddressTxtArea);

            JLabel orderTotalLabel = new JLabel("LKR " + (int)order.get(1));
            orderTotalLabel.setForeground(Color.WHITE);
            orderTotalLabel.setFont(new Font(null, Font.BOLD, 18));
            orderTotalLabel.setBounds(20, 200, 500, 20);
            orderPanel.add(orderTotalLabel);

            JButton orderInfoBtn = new JButton("INFO");
            orderInfoBtn.putClientProperty("ID", String.valueOf((int)order.getFirst()));
            orderInfoBtn.setBounds(150, 270, 107, 32);
            orderInfoBtn.setBackground(new Color(0x474747));
            orderInfoBtn.setForeground(Color.WHITE);
            orderInfoBtn.setFocusable(false);
            orderPanel.add(orderInfoBtn);

            JButton pickOrderBtn = new JButton("PICK ORDER");
            pickOrderBtn.putClientProperty("ID", String.valueOf((int)order.getFirst()));
            pickOrderBtn.setBounds(270, 255, 280, 46);
            pickOrderBtn.setForeground(Color.WHITE);
            pickOrderBtn.setBackground(new Color(0x02B160));
            pickOrderBtn.setFocusable(false);
            orderPanel.add(pickOrderBtn);

            if(DriverModel.getPickedOrderId() != 0){
                orderInfoBtn.setEnabled(false);
                pickOrderBtn.setEnabled(false);
            }else{
                orderInfoBtn.setEnabled(true);
                pickOrderBtn.setEnabled(true);
            }

            pickOrderBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deliveryOrderPanel.putClientProperty("ID", String.valueOf((int)order.getFirst()));
                    deliveryOrderPanel.setVisible(true);
                    DriverModel.setPickedOrderId((int)order.getFirst());
                    deliveryOrderPanelOrderId.setText("#" + order.get(2));
                    deliveryOrderPanelBranchName.setText((String) order.get(4));

                    DriverModel.addPickedUpOrderToDriverOrdersTable(DriverModel.getLoggedUserId(), (int)order.getFirst(), 0, "delivering");
                    DriverModel.updateOrderStatusAsDeliveringInOrdersTable((int)order.getFirst());
                }
            });
        }

        ordersPanel.revalidate();
        ordersPanel.repaint();

        int componentsPerRow = 2;
        int componentHeight = 321 + 20;
        int numRows = (int) Math.ceil((double) ordersPanel.getComponentCount() / componentsPerRow);
        int totalPreferredRowHeight = componentHeight * numRows;

        ordersPanel.setPreferredSize(new Dimension(1388, totalPreferredRowHeight));

    }

    public void addDeliveryOrderPanelListener(MouseListener listener){
        deliveryOrderPanel.addMouseListener(listener);
    }

    public int getDeliveryOrderPanelId(){
        return Integer.parseInt((String)(deliveryOrderPanel.getClientProperty("ID")));
    }

    public void setDeliveryOrderPanelVisibility(Boolean visibility){
        deliveryOrderPanel.setVisible(visibility);
    }

    public void setMainContainerPanelVisibility(Boolean visibility){
        mainContainerPanel.setVisible(visibility);
    }

    public void serDeliveryOrderCheckOutPanelVisibility(Boolean visibility){
        deliveryOrderCheckOutPanel.setVisible(visibility);
    }

    public void addOrderCheckOutPanelBackBtnPanelListener(MouseListener listener){
        orderCheckOutPanelBackBtnPanel.addMouseListener(listener);
    }

    public void loadCheckOutPanelDetails(List<Object> orderDetails){
        deliverOrderCode.setText("#" + orderDetails.getFirst());
        checkOutPanelRestNameLbl.setText((String) orderDetails.get(3));
        checkOutPanelCustomerNameLbl.setText((String) orderDetails.get(5));
        deliveryOrderTotalLabel.setText("LKR " + (int)orderDetails.get(1));
        checkOutPanelDeliverAddress.setText((String) orderDetails.get(2));
        checkOutPanelPickUpAddress.setText((String) orderDetails.get(4));
        orderCheckOutBtn.putClientProperty("ID", String.valueOf((int)orderDetails.get(6)));
    }

    public void loadCheckOutPanelOrderItems(List<List<Object>> orderItems){
        deliverOrderItemsPanel.removeAll();
        deliverOrderItemsPanel.revalidate();
        deliverOrderItemsPanel.repaint();

        for(List<Object> item : orderItems){
            JPanel deliverOrderItemPanel = new JPanel();
            deliverOrderItemPanel.setPreferredSize(new Dimension(842, 74));
            deliverOrderItemPanel.setBackground(new Color(0x5B5B5B));
            deliverOrderItemPanel.setLayout(null);
            deliverOrderItemsPanel.add(deliverOrderItemPanel);

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
            deliverOrderItemPanel.add(orderItemImagePanel);

            JLabel orderItemNameLabel = new JLabel((String) item.getFirst());
            orderItemNameLabel.setForeground(Color.WHITE);
            orderItemNameLabel.setBounds(110, 20, 350, 30);
            orderItemNameLabel.setFont(new Font(null, Font.PLAIN, 16));
            deliverOrderItemPanel.add(orderItemNameLabel);

            JLabel orderItemQntLabel = new JLabel("x" + (int)item.get(1));
            orderItemQntLabel.setForeground(Color.WHITE);
            orderItemQntLabel.setFont(new Font(null, Font.PLAIN, 16));
            orderItemQntLabel.setBounds(470, 20, 120, 30);
            deliverOrderItemPanel.add(orderItemQntLabel);

            JLabel orderItemTotalPrice = new JLabel("LKR " + (int)item.get(2));
            orderItemTotalPrice.setBounds(650, 20, 190,30);
            orderItemTotalPrice.setForeground(Color.WHITE);
            orderItemTotalPrice.setFont(new Font(null, Font.BOLD, 18));
            deliverOrderItemPanel.add(orderItemTotalPrice);
        }

        int totalPreferredHeight = 0;
        for (Component component : deliverOrderItemsPanel.getComponents()) {
            totalPreferredHeight += component.getPreferredSize().height + 5;
        }

        deliverOrderItemsPanel.setPreferredSize(new Dimension(872, totalPreferredHeight));
    }

    public void addOrderCheckOutBtnListener(ActionListener listener){
        orderCheckOutBtn.addActionListener(listener);
    }

    public void addPaymentDoneBtnListener(ActionListener listener){
        paymentDoneBtn.addActionListener(listener);
    }

    public void setDeliveryOrderPaymentPanelVisibility(Boolean visibility){
        deliveryOrderPaymentPanel.setVisible(visibility);
    }

    public void setPaymentPanelSubTotal(int subtotal){
        subTotal.setText("LKR " + subtotal);
    }

    public void setPaymentPanelTotal(int tot){
        total.setText("LKR " + tot);
    }

    public void addDeliveryChargeTextFieldListener(DocumentListener listener){
        deliveryChargeText.getDocument().addDocumentListener(listener);
    }

    public String getDeliveryChargeTextValue(){
        return deliveryChargeText.getText();
    }

    public void addPaidAmountTextFieldListener(DocumentListener listener){
        paidAmountText.getDocument().addDocumentListener(listener);
    }

    public String getPaidAmountTextFieldValue(){
        return paidAmountText.getText();
    }

    public void setBalance(int balance){
        if(balance != -1){
            balanceText.setText("LKR " + balance);
        }else{
            balanceText.setText("");
        }
    }

    public String getBalance(){
        return balanceText.getText();
    }

    public String getOrderCheckOutBtnId(){
        return (String) orderCheckOutBtn.getClientProperty("ID");
    }

    public void setPaymentDoneBtnId(String orderId){
        paymentDoneBtn.putClientProperty("ID", orderId);
    }

    public int getPaymentDoneBtnId(){
        return Integer.parseInt((String)paymentDoneBtn.getClientProperty("ID"));
    }

    public void clearPaymentPanel(){
        balanceText.setText("");
        paidAmountText.setText("");
        deliveryChargeText.setText("");
    }

    public void displayOrderCompleteMessage(String orderCode){
        JOptionPane.showMessageDialog(deliveryOrderCheckOutPanel, "Order #" + orderCode + "completed", null,  JOptionPane.PLAIN_MESSAGE);
    }

    public void displayPaymentErrorMessage(String  msge){
        JOptionPane.showMessageDialog(deliveryOrderCheckOutPanel, msge, null,  JOptionPane.ERROR_MESSAGE);
    }
}
