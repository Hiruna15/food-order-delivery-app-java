import com.mysql.cj.xdevapi.ModifyStatement;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.util.List;

public class DriverController implements Runnable {
    private LoginView loginView;
    private DriverModel model;
    private DriverView driverView;

    Thread getNewOrdersthread = new Thread(this);

    public DriverController(LoginView loginView,  DriverModel model){
        this.loginView = loginView;
        this.model = model;

        loginView.addLoginButtonListner(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] loginDetails = loginView.getUsernamePassword();

                if(loginDetails[0].isEmpty()){
                    loginView.displayInvalidLogMessage("Enter username!");
                }else if(loginDetails[1].isEmpty()){
                    loginView.displayInvalidLogMessage("Enter password!");
                }else{
                    if(model.logTheUser(loginDetails[0], loginDetails[1])){
                        loginView.closeLoginFrame();
                        driverView = new DriverView();

                        getNewOrdersthread.start();

                        ////////
                        driverView.addSideBarBtnPanelListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if(model.getIsSideBarOpen()){
                                    model.setIsSideBarOpen(false);
                                    driverView.setSideBarPanelVisibility(false);
                                }else{
                                    model.setIsSideBarOpen(true);
                                    driverView.setSideBarPanelVisibility(true);
                                }
                            }
                        });

                        driverView.addSideBarLogOutBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                model.setLoggedUserId(0);
                                model.setLoggedUsername("");
                                driverView.closeDriverInterface();
                                new LoginView();
                            }
                        });

                        driverView.addDeliveryOrderPanelListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                int orderId = driverView.getDeliveryOrderPanelId();
                                driverView.setMainContainerPanelVisibility(false);
                                model.setIsOrdersPanelOpen(false);
                                driverView.serDeliveryOrderCheckOutPanelVisibility(true);

                                List<Object> orderDetails = model.getCheckOutPanelDetails(orderId);
                                List<List<Object>> orderItems = model.getCheckOutPanelOrderItems(orderId);

                                driverView.loadCheckOutPanelDetails(orderDetails);
                                driverView.loadCheckOutPanelOrderItems(orderItems);

                            }
                        });

                        driverView.addOrderCheckOutPanelBackBtnPanelListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                driverView.serDeliveryOrderCheckOutPanelVisibility(false);
                                driverView.setMainContainerPanelVisibility(true);
                                model.setIsOrdersPanelOpen(true);
                            }
                        });

                        driverView.addOrderCheckOutBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                driverView.setDeliveryOrderPaymentPanelVisibility(true);
                                int orderTotal = model.getDeliveryOrderSubTotal();
                                driverView.setPaymentPanelSubTotal(orderTotal);
                                driverView.setPaymentPanelTotal(orderTotal);

                                String  orderId = driverView.getOrderCheckOutBtnId();
                                driverView.setPaymentDoneBtnId(orderId);
                            }
                        });

                        driverView.addDeliveryChargeTextFieldListener(new DocumentListener() {
                            @Override
                            public void insertUpdate(DocumentEvent e) {
                                if(!driverView.getDeliveryChargeTextValue().contains(" ")){
                                    int deliverycharge = Integer.parseInt(driverView.getDeliveryChargeTextValue());
                                    int subtotal =  model.getDeliveryOrderSubTotal();
                                    driverView.setPaymentPanelTotal(deliverycharge + subtotal);
                                    model.setDeliveryOrderTotal(deliverycharge + subtotal);
                                }

                            }

                            @Override
                            public void removeUpdate(DocumentEvent e) {
                                int subtotal =  model.getDeliveryOrderSubTotal();
                                if(!driverView.getDeliveryChargeTextValue().isEmpty()){
                                    if(!driverView.getDeliveryChargeTextValue().contains(" ")){
                                        int deliverycharge = Integer.parseInt(driverView.getDeliveryChargeTextValue());
                                        driverView.setPaymentPanelTotal(deliverycharge + subtotal);
                                        model.setDeliveryOrderTotal(deliverycharge + subtotal);
                                    }
                                }else{
                                    driverView.setPaymentPanelTotal(subtotal);
                                }

                            }

                            @Override
                            public void changedUpdate(DocumentEvent e) {

                            }
                        });

                        driverView.addPaidAmountTextFieldListener(new DocumentListener() {
                            @Override
                            public void insertUpdate(DocumentEvent e) {
                                if(!driverView.getPaidAmountTextFieldValue().contains(" ")){
                                    int paidAmount = Integer.parseInt(driverView.getPaidAmountTextFieldValue());
                                    if(paidAmount >= model.getDeliveryOrderTotal()){
                                        int balance = paidAmount - model.getDeliveryOrderTotal();
                                        driverView.setBalance(balance);
                                    }else{
                                        driverView.setBalance(-1);
                                    }
                                }
                            }

                            @Override
                            public void removeUpdate(DocumentEvent e) {
                                if(!driverView.getPaidAmountTextFieldValue().isEmpty()){
                                    if(!driverView.getPaidAmountTextFieldValue().contains(" ")){
                                        int paidAmount = Integer.parseInt(driverView.getPaidAmountTextFieldValue());
                                        if(paidAmount >= model.getDeliveryOrderTotal()){
                                            int balance = paidAmount - model.getDeliveryOrderTotal();
                                            driverView.setBalance(balance);
                                        }else{
                                            driverView.setBalance(-1);
                                        }
                                    }
                                }
                            }

                            @Override
                            public void changedUpdate(DocumentEvent e) {

                            }
                        });

                        driverView.addPaymentDoneBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(driverView.getDeliveryChargeTextValue().isEmpty()){
                                    driverView.displayPaymentErrorMessage("Enter your delivery charge!");
                                }else if(driverView.getBalance().isEmpty()){
                                    driverView.displayPaymentErrorMessage("Paid amount is lower than the total amount!");
                                }else{
                                    int orderId = driverView.getPaymentDoneBtnId();
                                    int paidAmount = Integer.parseInt(driverView.getPaidAmountTextFieldValue());
                                    model.updateTheOrderStatusAsCompleted(orderId, "completed", Integer.parseInt(driverView.getDeliveryChargeTextValue()));
                                    driverView.clearPaymentPanel();
                                    driverView.setDeliveryOrderPaymentPanelVisibility(false);
                                    driverView.displayOrderCompleteMessage(model.getDeliveryOrderCode());

                                    driverView.serDeliveryOrderCheckOutPanelVisibility(false);
                                    driverView.setMainContainerPanelVisibility(true);
                                    driverView.setDeliveryOrderPanelVisibility(false);
                                    model.setIsOrdersPanelOpen(true);
                                    DriverModel.setPickedOrderId(0);

//                                    new EmailSender("hirunadilmith15@gmail.com", model.getCustomerEmail(), "Order #" + model.getDeliveryOrderCode() + " delivered",
//                                            "Your order #" + model.getDeliveryOrderCode() + " successfully delivered.\n\nTotal amount: LKR " + model.getDeliveryOrderTotal()
//                                     + "\nDelivery charge: LKR " + driverView.getDeliveryChargeTextValue() + "\nPaid Amount: LKR " + paidAmount + "\nBalance: " +
//                                                    driverView.getBalance());

                                    SwingUtilities.invokeLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            new EmailSender("hirunadilmith15@gmail.com", model.getCustomerEmail(), "Order #" + model.getDeliveryOrderCode() + " delivered",
                                                    "Your order #" + model.getDeliveryOrderCode() + " successfully delivered.\n\nTotal amount: LKR " + model.getDeliveryOrderTotal()
                                                            + "\nDelivery charge: LKR " + driverView.getDeliveryChargeTextValue() + "\nPaid Amount: LKR " + paidAmount + "\nBalance: " +
                                                            driverView.getBalance());
                                        }
                                    });
                                }
                            }
                        });
                        ////////
                    }else{
                        loginView.displayInvalidLogMessage("Invalid login details!");
                    }
                }
            }
        });
    }

    public void run(){
        while (true){
            if(model.getIsOrdersPanelOpen()){
                List<List<Object>> orders = model.getOrdersToBeDelivered();
                driverView.loadOrders(orders);
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
