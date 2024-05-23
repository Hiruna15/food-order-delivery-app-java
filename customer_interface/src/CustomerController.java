import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerController implements Runnable {
    private CustomerModel model;
    private CustomerView view;

    public CustomerController(CustomerModel model, CustomerView view) {
        this.model = model;
        this.view = view;

        view.addMenuIconClickListner(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(model.getSideBarHomeMyOrdersListPanelOpen()){
                    model.setSideBarHomeMyOrdersListPanelOpen(false);
                    view.setSideBarHomeMyOrdersListPanelVisibility(false);
                }

                if(model.isHomeSideBarPanelOpen()) {
                    model.setIsHomeSideBarPanelOpen(false);
                    view.setSideBarVisible(false);
                }
                else {
                    model.setIsHomeSideBarPanelOpen(true);
                    view.setSideBarVisible(true);
                }
            }
        });

        view.addRestaurantBtnListner(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(!model.isRestSignupFormOpen()){
                    model.setRestSignupFormOpen(true);
                    model.setIsHomeSideBarPanelOpen(false);
                    view.setSideBarVisible(false);
                    view.setRestSignAppVisible(true);
                    view.setHomePanelVisibility(false);
                }
            }
        });

        view.addRestSignUpFormCloseBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.openEmailConfirmPanel(false);
                model.setRestSignupFormOpen(false);
                view.setRestSignAppVisible(false);
                view.setHomePanelVisibility(true);
                view.clearRestSignupForm();
            }
        });

        view.addRestSubmitBtnListner(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] details = view.getRestRegisterDetails();

                String name = details[0];
                String address = details[1];
                String email = details[2];
                String mobile = details[3];
                String city = details[4];
                String postalCode = details[5];
                String branchName = details[6];

//                model.setStoreEmail(email);

                String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                Pattern pattern = Pattern.compile(emailPattern);
                Matcher matcher = pattern.matcher(email);
                boolean isValidEmail = matcher.matches();

                if(name.isEmpty() || address.isEmpty() || email.isEmpty() || mobile.isEmpty() || city.isEmpty() || postalCode.isEmpty() || branchName.isEmpty()){
                    view.displayRestSubmitMessage("Please fill all the fields!");
                }
                else if(!isValidEmail){
                    view.displayRestSubmitMessage("Please enter a valid email address!");
                }
                else if(!model.getRegisteredRestEmails(email)){
                    view.displayRestSubmitMessage("It already has an account with the email you entered!");
                }
                else if(!mobile.matches("^\\d{10}$")){
                    view.displayRestSubmitMessage("Invalid phone number!");
                }
                else if(!postalCode.matches("^\\d{5}$")){
                    view.displayRestSubmitMessage("Please enter a valid postal code!");
                }
                else{
                    model.setStoreName(name);
                    model.setStoreAddress(address);
                    model.setStoreEmail(email);
                    model.setStoreMobile(mobile);
                    model.setStoreCity(city);
                    model.setStorePostalCode(postalCode);
                    model.setStoreBranchName(branchName);
                    view.clearRestSignupForm();

                    view.openEmailConfirmPanel(true);
                    view.setEmailConfirmMessage(model.getStoreEmail());

                    model.generateEmailConfirmCode();
                    System.out.println(model.getEmailConfirmCode());

                    new EmailSender("hirunadilmith15@gmail.com", model.getStoreEmail(), "Email confirmation code", "Confirm your email with the code given below" + "" +
                            "\n\n" + "code: " + model.getEmailConfirmCode());
                }
            }
        });

        view.addEmailConfirmBtnListner(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(view.getUserEnteredEmailConfirmCode().isEmpty()){
                    view.displayRestSubmitMessage("Please enter the confirmation code we sent to your email!");
                }
                else if(Objects.equals(view.getUserEnteredEmailConfirmCode(), String.valueOf(model.getEmailConfirmCode()))){
                    model.addRestaurantToReviewList();
                    if(model.isDataInsertedSuccessful()){
                        view.displayDataInsertedMessage("Your restaurant is under review. we will send an email once the review process is done");
                        view.openEmailConfirmPanel(false);
                        model.setRestSignupFormOpen(false);
                        view.setHomePanelVisibility(true);
                        view.setRestSignAppVisible(false);
                        model.setIsDataInsertedSuccessful(false);
                    }
                }
                else{
                    view.displayRestSubmitMessage("Invalid confirmation code!");
                }
            }
        });

        view.addFilterAllPanelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
                model.setFilterRestaurantType("All");
                view.applyFilterBtnsStyles("all");
            }
        });

        view.addFilterDeliveryPanelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
                model.setFilterRestaurantType("Delivery");
                view.applyFilterBtnsStyles("delivery");
            }
        });

        view.addFilterPickUpPanelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
                model.setFilterRestaurantType("Pick Up");
                view.applyFilterBtnsStyles("pickup");
            }
        });

        view.restMenuIconBtnPanelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(model.getRestaurantMyFeaturesPanelOpen()){
                    model.setRestaurantMyFeaturesPanelOpen(false);
                    view.setRestaurantMyFeaturesPanelVisibility(false);
                }else{
                    model.setRestaurantMyFeaturesPanelOpen(true);
                    view.setRestaurantMyFeaturesPanelVisibility(true);
                }
            }
        });

        view.addUserLoginBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setHomePanelVisibility(false);
                view.setUserLogSignupContainerVisibility(true);
            }
        });

        view.addRestPanelUserLoginBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setRestaurantPanelVisibility(false);
                view.setUserLogSignupContainerVisibility(true);
            }
        });

        view.addRegisterFormCloseBtnListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.setCustomerEmailConfirmPanelVisibility(false);
                view.openLoginFormPanelLeft();
                view.clearCustomerRegistrationPanel();
                view.clearCustomerLoginPanel();
                view.setUserLogSignupContainerVisibility(false);

                if(model.getIsRestaurantPanelOpen()){
                    view.setRestaurantPanelVisibility(true);
                }else if(model.getIsHomePanelOpen()){
                    view.setHomePanelVisibility(true);
                }
            }
        });

        view.addLoginFormSignUpLblListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.openRegisterFormPanelLeft();
            }
        });

        view.addRegisterFormLoginLblListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.openLoginFormPanelLeft();
            }
        });

        view.addUserSignUpBtnLitener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] details = view.getUserSignUpDetails();
                if(Objects.equals(details[0], "")){
                    view.displayUserRegistrationValidateMsge(false, "Enter an username!");
                }else if(Objects.equals(details[1], "")){
                    view.displayUserRegistrationValidateMsge(false, "Enter your email address!");
                }else if(details[2].isEmpty()){
                    view.displayUserRegistrationValidateMsge(false, "Enter a password!");
                }else if(details[3].isEmpty()){
                    view.displayUserRegistrationValidateMsge(false, "Confirm the password!");
                }else if(!details[2].equals(details[3])){
                    view.displayUserRegistrationValidateMsge(false, "Confirm password was failed!");
                }else if(!model.isValidEmail(details[1])){
                    view.displayUserRegistrationValidateMsge(false, "Invalid Email address!");
                }else if(!model.getRegisteredRestEmails(details[1])){
                    view.displayUserRegistrationValidateMsge(false, "It's already have an account with the email you entered!");
                }else{
                    view.setCustomerEmailConfirmPanelVisibility(true);
                    view.displayUserRegistrationValidateMsge(true, "enter the 4 digit code we sent to " + details[1]);
                    model.generateEmailConfirmCode();
                    System.out.println(model.getEmailConfirmCode());
                }
            }
        });

        view.setCustomerEmailConfirmBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(view.getCustomerEnteredEmailConfirmCode().isEmpty()){
                    view.displayUserRegistrationValidateMsge(false, "Enter the email confirmation code!");
                }else if(!view.getCustomerEnteredEmailConfirmCode().matches("[0-9]+")){
                    view.displayUserRegistrationValidateMsge(false, "Invalid code!");
                }else if(!Objects.equals(view.getCustomerEnteredEmailConfirmCode(), String.valueOf(model.getEmailConfirmCode()))){
                    view.displayUserRegistrationValidateMsge(false, "Invalid code!");
                }else{
                    model.registerCustomer(view.getUserSignUpDetails());
                    view.displayUserRegistrationValidateMsge(true, "Registered successfully");
                    view.clearCustomerRegistrationPanel();
                    view.clearCustomerLoginPanel();
                    view.setCustomerEmailConfirmPanelVisibility(false);
                    view.openLoginFormPanelLeft();
                }
            }
        });

        view.addCustomerLoginFormLoginBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] details = view.getCustomerLoginDetails();
                if(details[0].isEmpty()){
                    view.displayUserRegistrationValidateMsge(false, "Enter your email address!");
                }else if(details[1].isEmpty()){
                    view.displayUserRegistrationValidateMsge(false, "Enter the password!");
                }else{
                    if(model.loginTheCustomer(details)){
                        view.clearCustomerLoginPanel();
                        view.clearCustomerRegistrationPanel();
                        view.setUserLogSignupContainerVisibility(false);
                        view.setAfterLoginAndLogoutView(true);

                        if(model.getIsRestaurantPanelOpen()){
                            view.setRestaurantPanelVisibility(true);
                        }else if(model.getIsHomePanelOpen()){
                            view.setHomePanelVisibility(true);
                        }

                    }else{
                        view.displayUserRegistrationValidateMsge(false, "Invalid login details!");
                        view.clearCustomerLoginPanel();
                    }
                }
            }
        });

        view.addLoginBtnSidebarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setUserLogSignupContainerVisibility(true);
                view.setHomePanelVisibility(false);
                model.setIsHomeSideBarPanelOpen(false);
                view.setSideBarVisible(false);
            }
        });

        view.addSignupBtnSidebarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setUserLogSignupContainerVisibility(true);
                view.openRegisterFormPanelLeft();
                view.setHomePanelVisibility(false);
                model.setIsHomeSideBarPanelOpen(false);
                view.setSideBarVisible(false);
            }
        });

        view.addSignupDeliverBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setDeliverySignUpContainerVisibility(true);
                view.setHomePanelVisibility(false);
                model.setIsHomeSideBarPanelOpen(false);
                view.setSideBarVisible(false);
            }
        });

        view.addDeliverySignUpContainerCloseLblListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.setDeliverySignUpContainerVisibility(false);
                view.setHomePanelVisibility(true);
                view.clearDeliverySignUpContainer();
            }
        });

        view.addDeliverySignUpBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] details = view.getDeliveryRegistrationDetails();

                if(details[5].isEmpty()){
                    view.displayDeliverySignUpValidationMsge("Enter your name");
                }else if(details[6].isEmpty()){
                    view.displayDeliverySignUpValidationMsge("Enter your mobile number");
                } else if(details[0].isEmpty()){
                    view.displayDeliverySignUpValidationMsge("Enter a username");
                }else if (details[1].isEmpty()){
                    view.displayDeliverySignUpValidationMsge("Enter your email address");
                }else if(details[2].isEmpty()){
                    view.displayDeliverySignUpValidationMsge("Enter your NIC number");
                }else if(details[3].isEmpty()) {
                    view.displayDeliverySignUpValidationMsge("Enter your vehicle number");
                }else if(details[4].startsWith("S")){
                    view.displayDeliverySignUpValidationMsge("Please select the type of your vehicle");
                }else{
                    if(model.checkDeliverySignUpUserNameExist(details[0])){
                        view.displayDeliverySignUpValidationMsge("Username '" + details[0] + "' is already taken!");
                    }else if(!model.isValidEmail(details[1])){
                        view.displayDeliverySignUpValidationMsge("The email you entered is invalid");
                    }else if(model.checkIfDeliverySignUpEmailExist(details[1])){
                        view.displayDeliverySignUpValidationMsge("there is already an account with the email you provided");
                    }else if(!model.isValidNICNumber(details[2])){
                        view.displayDeliverySignUpValidationMsge("NIC number you entered is not a valid one");
                    }else if(!details[6].matches("^\\d{10}$")){
                        view.displayDeliverySignUpValidationMsge("Phone number should have only 10 digits");
                    }else{
                        view.setDeliverySignUpPanelVisibility(false);
                        view.setDeliverySignUpEmailConfirmPanelVisibility(true);

                        model.generateEmailConfirmCode();
                        int code = model.getEmailConfirmCode();
                        System.out.println(code);

                        new EmailSender("hirunadilmith15@gmail.com", details[1], "Email confirmation code", "Enter this code given below to confirm your email" + "\n" + code);
                    }
                }
            }
        });

        view.addDeliveryEmailConfirmBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userEnteredCode = view.getDeliveryConfirmEmailTextFieldValue();
                if(userEnteredCode.isEmpty()){
                    view.displayDeliverySignUpValidationMsge("Enter the Email confirmation code we sent to " + view.getDeliveryRegistrationDetails()[1]);
                }else if(!userEnteredCode.equals(String.valueOf(model.getEmailConfirmCode()))){
                    view.displayDeliverySignUpValidationMsge("Confirmation failed!. The code you entered is incorrect");
                }else{
                    model.addANewDeliveryDriverToReview(view.getDeliveryRegistrationDetails());
                    view.displayDeliverySignUpSuccessfullMsge(view.getDeliveryRegistrationDetails()[1]);
                    view.setDeliverySignUpContainerVisibility(false);
                    view.setHomePanelVisibility(true);
                    view.setDeliverySignUpPanelVisibility(true);
                    view.setDeliverySignUpEmailConfirmPanelVisibility(false);
                    view.clearDeliverySignUpContainer();
                }
            }
        });

        view.addDeliverySignUpEmailConfirmPanelBackBtnListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.setDeliverySignUpEmailConfirmPanelVisibility(false);
                view.setDeliverySignUpPanelVisibility(true);
            }
        });

        view.addLogoutBtnSidebarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setLoggedCustomerID(0);
                model.setLoggedCustomerUserName(null);
                model.setIsHomeSideBarPanelOpen(false);
                view.setSideBarVisible(false);
                view.setAfterLoginAndLogoutView(false);
            }
        });

        view.addRestaurantPanelBackBtnPanelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.setRestaurantPanelVisibility(false);
                CustomerModel.setIsRestaurantPanelOpen(false);
                view.setHomePanelVisibility(true);
                CustomerModel.setIsHomePanelOpen(true);
                CustomerModel.setOpenRestaurantId(0);
            }
        });





        //////add to order panel ///////////
        view.addToOrderPanelCloseBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerModel.setIsAddToOrderPanelOpen(false);
                view.setAddToOrderPanelVisibility(false);
                CustomerModel.setOpenFoodItemId(0);
            }
        });

        view.addOrderItemAddQntPlusBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentQnt = view.getOrderItemQnt();
                currentQnt+=1;
                view.setOrderItemQnt(String.valueOf(currentQnt));

                int price = view.getOpenFoodItemPrice();
                view.setSubTotal(String.valueOf(price * currentQnt));
            }
        });

        view.addOrderItemAddQntMinusBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int currentQnt = view.getOrderItemQnt();
                if(currentQnt != 1){
                    currentQnt-=1;
                    view.setOrderItemQnt(String.valueOf(currentQnt));

                    int price = view.getOpenFoodItemPrice();
                    view.setSubTotal(String.valueOf(price * currentQnt));
                }
            }
        });

        view.addToOrderBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Object> orderItemDetails = view.getOrderItemDetails();
                if(orderItemDetails.get(1) != "none"){
                    model.addItemToCart(orderItemDetails);
                }else{
                    view.displaySelectOrderTypeError();
                }
            }
        });





        ///// cart ///////
        view.addCartOrderAddressTxtFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (view.getCartOrderAddressTxtValue().equals("Enter your delivery address")) {
                    view.setCartOrderAddressTxtValue("");
                    view.setCartOrderAddressTxtForeground("b");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(view.getCartOrderAddressTxtValue().isEmpty()){
                    view.setCartOrderAddressTxtValue("Enter your delivery address");
                    view.setCartOrderAddressTxtForeground("g");
                }
            }
        });

        view.cartBtnPanelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(CustomerModel.getLoggedCustomerID() != 0){
                    if(model.getIsRestaurantPanelOpen()){
                        int cartId = model.checkIfTheUserHasACartFromThatRest();
                        if(cartId != 0){
                            view.setCartContainerPanelVisibility(true);
                            CustomerModel.setIsCartContainerPanelOpen(true);

                            CustomerModel.setOpenCartId(cartId);

                            CustomerModel.setOpenCartRestAllowTypeAndNameAndId(cartId);
                            view.setOpenCartBranchName();



                            String allowType = CustomerModel.getOpenCartRestAllowType();

                            if(allowType.equals("Delivery")){
                                view.setCartPickUpOptionDisableAndEnable("disable");
                                view.setCartDeliveryOptionDisableAndEnable("enable");
                                view.setCartOrderAddressTxtVisibility(true);

                                CustomerModel.setCartOpenOption("Delivery");
                                view.setCartOpenOptionStyles("delivery");
                            }else if(allowType.equals("Pick Up")){
                                view.setCartDeliveryOptionDisableAndEnable("disable");
                                view.setCartPickUpOptionDisableAndEnable("enable");
                                view.setCartOrderAddressTxtVisibility(false);

                                CustomerModel.setCartOpenOption("Pickup");
                                view.setCartOpenOptionStyles("pickup");
                            }else{
                                if(CustomerModel.checkIfDeliveryOrdersInTheCart(cartId)){
                                    view.setCartDeliveryOptionDisableAndEnable("enable");
                                    view.setCartPickUpOptionDisableAndEnable("enable");
                                    view.setCartOrderAddressTxtVisibility(true);

                                    CustomerModel.setCartOpenOption("Delivery");
                                    view.setCartOpenOptionStyles("delivery");

                                    allowType = "Delivery";
                                }else{
                                    view.setCartDeliveryOptionDisableAndEnable("enable");
                                    view.setCartPickUpOptionDisableAndEnable("enable");
                                    view.setCartOrderAddressTxtVisibility(false);

                                    CustomerModel.setCartOpenOption("Pickup");
                                    view.setCartOpenOptionStyles("pickup");

                                    allowType = "Pick Up";
                                }
                            }

                            List<List<Object>> cartItemsDetails = CustomerModel.getCartItems(cartId, allowType);
                            view.loadCartItems(cartItemsDetails);
                        }else{
                            if(model.getCartsListPanelOpen()){
                                CustomerModel.setIsCartsListPanelOpen(false);
                                view.setCartsListPanelVisibility(false);
                            }else{
                                List<List<Object>> carts = model.getAllCarts();
                                if(!carts.isEmpty()){
                                    CustomerModel.setIsCartsListPanelOpen(true);
                                    view.setCartsListPanelVisibility(true);
                                    view.setCartsListPanelOpenPosition(95);

                                    view.loadCartsToCartListPanel(carts);
                                }
                            }
                        }
                    }
                }else{
                    if(model.getIsHomePanelOpen()){
                        view.setHomePanelVisibility(false);
                        view.setUserLogSignupContainerVisibility(true);
                    }else if(model.getIsRestaurantPanelOpen()){
                        view.setRestaurantPanelVisibility(false);
                        view.setUserLogSignupContainerVisibility(true);
                    }
                }
            }
        });

        view.addCartDeliveryBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerModel.setCartOpenOption("Delivery");
                view.setCartOpenOptionStyles("delivery");
                view.setCartOrderAddressTxtVisibility(true);

                List<List<Object>> cartItemsDetails = CustomerModel.getCartItems(CustomerModel.getOpenCartId(), CustomerModel.getCartOpenOption());
                view.loadCartItems(cartItemsDetails);
            }
        });

        view.addCartPickUpBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerModel.setCartOpenOption("Pickup");
                view.setCartOpenOptionStyles("pickup");
                view.setCartOrderAddressTxtVisibility(false);

                List<List<Object>> cartItemsDetails = CustomerModel.getCartItems(CustomerModel.getOpenCartId(), CustomerModel.getCartOpenOption());
                view.loadCartItems(cartItemsDetails);
            }
        });

        view.addCartContainerPanelCloseBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setCartContainerPanelVisibility(false);
                CustomerModel.setIsCartContainerPanelOpen(false);
                CustomerModel.setOpenCartId(0);
            }
        });

        view.addHomeCartBtnPanelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(CustomerModel.getLoggedCustomerID() != 0){
                    if(model.getCartsListPanelOpen()){
                        CustomerModel.setIsCartsListPanelOpen(false);
                        view.setCartsListPanelVisibility(false);
                    }else{
                        List<List<Object>> carts = model.getAllCarts();
                        if(!carts.isEmpty()){
                            CustomerModel.setIsCartsListPanelOpen(true);
                            view.setCartsListPanelVisibility(true);
                            view.setCartsListPanelOpenPosition(130);

                            view.loadCartsToCartListPanel(carts);
                        }
                    }
                }else{
                    view.setHomePanelVisibility(false);
                    view.setUserLogSignupContainerVisibility(true);
                }
            }
        });

        view.addInCartCartSelectBtnPanelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(model.getCartsListPanelOpen()){
                    CustomerModel.setIsCartsListPanelOpen(false);
                    view.setCartsListPanelVisibility(false);
                }else{
                    List<List<Object>> carts = model.getAllCarts();
                    if(!carts.isEmpty()){
                        CustomerModel.setIsCartsListPanelOpen(true);
                        view.setCartsListPanelVisibility(true);
                        view.setCartsListPanelOpenPosition(45);

                        view.loadCartsToCartListPanel(carts);
                    }
                }
            }
        });

        view.addCartPlaceOrderBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<List<Object>> placeOrderItemsDetails = model.getPlaceOrderDetails();

                if(!placeOrderItemsDetails.isEmpty()){

                    if(view.getCartOrderAddressTxtVisibility()){
                        String address = view.getOrderAddress();
                        if(Objects.equals(address, "Enter your delivery address") | address.isEmpty()){
                            view.displayEnterAddressMessage();
                        }else{
                            if(model.placeTheOrder(placeOrderItemsDetails, address).equals("close")){
                                view.setCartContainerPanelVisibility(false);
                                CustomerModel.setIsCartContainerPanelOpen(false);
                                CustomerModel.setOpenCartId(0);
                            }else{
                                List<List<Object>> cartItems = CustomerModel.getCartItems(CustomerModel.getOpenCartId(), CustomerModel.getCartOpenOption());
                                view.loadCartItems(cartItems);
                            }
                        }
                    }else{
                        if(model.placeTheOrder(placeOrderItemsDetails, "").equals("close")){
                            view.setCartContainerPanelVisibility(false);
                            CustomerModel.setIsCartContainerPanelOpen(false);
                            CustomerModel.setOpenCartId(0);
                        }else{
                            List<List<Object>> cartItems = CustomerModel.getCartItems(CustomerModel.getOpenCartId(), CustomerModel.getCartOpenOption());
                            view.loadCartItems(cartItems);
                        }
                    }
                }
            }
        });

        view.addSideBarHomeMyOrdersPanelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model.setIsHomeSideBarPanelOpen(true);
                view.setSideBarVisible(false);
                model.setSideBarHomeMyOrdersListPanelOpen(true);
                view.setSideBarHomeMyOrdersListPanelVisibility(true);
            }
        });

        view.addMyOrdersListPanelBackBtnPanelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model.setIsHomeSideBarPanelOpen(true);
                view.setSideBarVisible(true);
                model.setSideBarHomeMyOrdersListPanelOpen(false);
                view.setSideBarHomeMyOrdersListPanelVisibility(false);
            }
        });

        view.addLiveOrderInfoPanelCloseBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.closeLiveOrderInfoPanel();
                CustomerModel.setIsLiveOrderInfoPanelOpen(false);
            }
        });




        ////// search panel ////////
        view.addHomeSearchBarPanelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.setRestSearchPanelVisibility(true);
                CustomerModel.setIsRestSearchPanelOpen(true);
            }
        });

        view.addSearchPanelBackBtnListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.setRestSearchPanelVisibility(false);
                CustomerModel.setIsRestSearchPanelOpen(false);
                CustomerModel.setRestSearchBarText("");
                view.clearSearchBar();
                view.clearSearchedResultsPanel();
            }
        });

        view.addSearchPanelSearchBarFocusableListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(view.getSearchBarText().equals("Search Restaurants")){
                    view.removeSearchBarPlaceHolder();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(view.getSearchBarText().isEmpty()){
                    view.addSearchBarPlaceHolder();
                }
            }
        });

        view.addSearchPanelSearchBarTextChangeListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String searchText = "%" + view.getSearchBarText() + "%";
                CustomerModel.setRestSearchBarText(searchText);

                List<List<Object>> enableRestaurants = model.getEnableRestaurants("search", searchText);
                view.setRestaurants(enableRestaurants, "All", "searchPanel");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String searchText;
                if(!view.getSearchBarText().isEmpty()){
                    searchText = "%" + view.getSearchBarText() + "%";
                }else{
                    searchText = "";
                }

                CustomerModel.setRestSearchBarText(searchText);

                List<List<Object>> enableRestaurants = model.getEnableRestaurants("search", searchText);
                view.setRestaurants(enableRestaurants, "All", "searchPanel");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });




        /////////my favorites//////////
        view.addSideBarHomeFavoritesPanelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(CustomerModel.getLoggedCustomerID() != 0){
                    view.setMyFavoritesPanelVisibility(true);
                    CustomerModel.setIsFavoritesPanelOpen(true);

                    model.setIsFavRestsPanelOpen(true);
                    model.setIsFavMealsPanelOpen(false);
                    view.setClickedFavBtnStyles("rests");

                    view.setSideBarVisible(false);
                    model.setIsHomeSideBarPanelOpen(false);

                    List<List<Object>> favRestaurants = model.getFavoriteRestaurants();
                    view.setRestaurants(favRestaurants, "All", "favPanel");
                }
            }
        });

        view.addMyFavoriteBackBtnPanelListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.setMyFavoritesPanelVisibility(false);
                CustomerModel.setIsFavoritesPanelOpen(false);
            }
        });

        view.addFavRestsBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setIsFavRestsPanelOpen(true);
                model.setIsFavMealsPanelOpen(false);
                view.setClickedFavBtnStyles("rests");
            }
        });

        view.addFavMealsBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setIsFavMealsPanelOpen(true);
                model.setIsFavRestsPanelOpen(false);
                view.setClickedFavBtnStyles("meals");
            }
        });
    }



    public void run(){
        while (true){
            if(model.getIsHomePanelOpen()){
                if(model.getIsRestSearchPanelOpen()){
                    List<List<Object>> enableRestaurants = model.getEnableRestaurants("search", model.getRestSearchBarText());
                    view.setRestaurants(enableRestaurants, "All", "searchPanel");
                }else{
                    List<List<Object>> enableRestaurants = model.getEnableRestaurants("home", "");

                    if(!enableRestaurants.isEmpty()){
                        view.setRestaurants(enableRestaurants, model.getFilterRestaurantType(), "home");
                    }else {
                        view.clearRestaurantsPanel();
                    }
                }
                view.setHomeFrameVisible();
            }else if(model.getIsRestaurantPanelOpen()){
                model.setIsRestaurantOpen();
                List<String> restCategories = model.getRestCategories();
                if(!restCategories.isEmpty()){
                    view.loadCategoriesToRestMenuBar(restCategories);

                    List<List<List<Object>>> allCategoryFoodItems = new ArrayList<>();
                    for(String category : restCategories){
                        List<List<Object>> categoryFoodItems = model.getRestFoodItems(category);
                        allCategoryFoodItems.add(categoryFoodItems);
                    }

                    view.loadAllCategoryItems(allCategoryFoodItems, restCategories);
                }else{
                    view.clearCategoriesInRestMenuBar();
                    view.clearRestMenuItemsPanel();
                }


                if(CustomerModel.getIsAddToOrderPanelOpen()){
                    List<Object> foodItemDetails;
                    foodItemDetails = model.getOpenFoodItemDetails("rest");

                    view.loadItemDetailsToAddToOrderPanel(foodItemDetails);
                }

            }

            if(model.getSideBarHomeMyOrdersListPanelOpen()){
                List<List<Object>> orders = model.getCustomerLiveOrders();
                view.loadLiveOrdersToSideBar(orders);
            }

            if(model.getIsLiveOrderInfoPanelOpen()){
                String status = model.getOpenLiveOrderStatus();
                String statusValue;
                String orderType = view.getOpenLiveOrderType().toLowerCase();

                if(Objects.equals(status, "completed")){
                    CustomerModel.setIsLiveOrderInfoPanelOpen(false);
                    view.closeLiveOrderInfoPanel();
                }else{
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

                    view.loadOpenLiveOrderStatus(statusValue);
                }
            }

            //////////////
//            if(model.getIsCartContainerPanelOpen()){
//                List<List<Object>> cartItemsDetails = CustomerModel.getCartItems(CustomerModel.getOpenCartId(), CustomerModel.getCartOpenOption());
//                view.loadCartItemsUpdateDetails(cartItemsDetails);
//            }
            ////////////


            if(model.getIsFavoritesPanelOpen()){
                if(model.getIsFavRestsPanelOpen()){
                    List<List<Object>> favRestaurants = model.getFavoriteRestaurants();
                    view.setRestaurants(favRestaurants, "All", "favPanel");
                }else{
                    List<List<Object>> favMeals = model.getFavoriteMeals();
                    view.loadFavMeals(favMeals);

                    if(CustomerModel.getIsAddToOrderPanelOpen()){
                        List<Object> foodItemDetails;
                        if(model.getIsRestaurantPanelOpen()){
                            foodItemDetails = model.getOpenFoodItemDetails("rest");
                        }else{
                            foodItemDetails = model.getOpenFoodItemDetails("fav");
                        }

                        view.loadItemDetailsToAddToOrderPanel(foodItemDetails);
                    }
                }
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
