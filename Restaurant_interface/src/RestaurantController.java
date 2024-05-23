import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.Struct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public class RestaurantController implements Runnable {
    private RestaurantModel model;
    private LoginView loginView;
    private RestaurantView restaurantView;

    public RestaurantController(RestaurantModel model, LoginView loginView){
        this.model = model;
        this.loginView = loginView;

        loginView.addLoginButtonListner(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] userLog = loginView.getUsernamePassword();
                if(userLog[0].isEmpty() || userLog[1].isEmpty()){
                    loginView.displayInvalidLogMessage("enter username and password!");
                }
                else{
                    if(model.checkUserLogin(userLog[0], userLog[1])){
                        model.setRestaurantUsrname(userLog[0]);
                        model.setRestaurantPassword(userLog[1]);
                        loginView.closeLoginFrame();
                        restaurantView = new RestaurantView();

                        restaurantView.setLoggedRestName(model.getRestBranchName());

                        model.setHomePanelOpen(true);

                        ////

                        if(model.chekRestOpenOrClose()){
                            restaurantView.setRestOpenCloseBtnStyles("open");
                        }else{
                            restaurantView.setRestOpenCloseBtnStyles("close");
                        }
                        restaurantView.addHomeBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                restaurantView.closeEditSelectCategoryScrollPane();
                                model.setHomePanelOpen(true);
                                model.setOrdersPanelOpen(false);
                                model.setMenuPanelOpen(false);
                                model.setCheckOutPanelOpen(false);
                                model.setEditPanelOpen(false);

                                restaurantView.openMenuAddContainerPanel(false);
                                restaurantView.openHomePanel(true);

                                restaurantView.closecheckOutOrderViewPanel();
                                restaurantView.setCheckOutOrderPaymentPanelVisibility(false);
                            }
                        });

                        restaurantView.addOrderBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                restaurantView.closeEditSelectCategoryScrollPane();
                                model.setHomePanelOpen(false);
                                model.setOrdersPanelOpen(true);
                                model.setMenuPanelOpen(false);
                                model.setCheckOutPanelOpen(false);
                                model.setEditPanelOpen(false);

                                restaurantView.openMenuAddContainerPanel(false);
                                restaurantView.openOrdersPanel(true);

                                restaurantView.setDashBoardSearchOrderContainerPanelVisibility(false);
                                RestaurantModel.setIsDashBoardSearchOrderViewPanelOpen(false);
                                restaurantView.setDashBoardSearchOrderViewPanelVisibility(false);

                                restaurantView.closecheckOutOrderViewPanel();
                                restaurantView.setCheckOutOrderPaymentPanelVisibility(false);
                            }
                        });

                        restaurantView.addMenuBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                restaurantView.closeEditSelectCategoryScrollPane();
                                model.setHomePanelOpen(false);
                                model.setOrdersPanelOpen(false);
                                model.setMenuPanelOpen(true);
                                model.setCheckOutPanelOpen(false);
                                model.setEditPanelOpen(false);

//                                restaurantView.openMenuAddContainerPanel(false); ///////
                                restaurantView.openMenuPanel(true);

                                restaurantView.clearMenuBottomPanel();
                                List<List<Object>> menuItems = RestaurantModel.getMenu();

                                if(!menuItems.isEmpty()){
                                    for (List<Object> menuItem : menuItems) {
                                        restaurantView.addMenuItemsToEditPanel(menuItem);
                                    }
                                }

                                restaurantView.setDashBoardSearchOrderContainerPanelVisibility(false);
                                RestaurantModel.setIsDashBoardSearchOrderViewPanelOpen(false);
                                restaurantView.setDashBoardSearchOrderViewPanelVisibility(false);
                                restaurantView.setCheckOutOrderPaymentPanelVisibility(false);

                                restaurantView.closecheckOutOrderViewPanel();
                            }
                        });

                        restaurantView.addEditBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                restaurantView.closeEditSelectCategoryScrollPane();
                                model.setHomePanelOpen(false);
                                model.setOrdersPanelOpen(false);
                                model.setMenuPanelOpen(false);
                                model.setCheckOutPanelOpen(false);
                                model.setEditPanelOpen(true);

                                restaurantView.openMenuAddContainerPanel(false);
                                restaurantView.openEditPanel(true);

                                List<Object> restaurantDetails = model.getRestaurantDetails();
                                restaurantView.setEditPanelData(restaurantDetails);

                                restaurantView.setDashBoardSearchOrderContainerPanelVisibility(false);
                                RestaurantModel.setIsDashBoardSearchOrderViewPanelOpen(false);
                                restaurantView.setDashBoardSearchOrderViewPanelVisibility(false);

                                restaurantView.closecheckOutOrderViewPanel();
                                restaurantView.setCheckOutOrderPaymentPanelVisibility(false);
                            }
                        });

                        restaurantView.addCheckOutBtnSideBarListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                restaurantView.closeEditSelectCategoryScrollPane();
                                model.setHomePanelOpen(false);
                                model.setOrdersPanelOpen(false);
                                model.setMenuPanelOpen(false);
                                model.setEditPanelOpen(false);
                                model.setCheckOutPanelOpen(true);

                                restaurantView.openMenuAddContainerPanel(false);
                                restaurantView.openCheckOutPanel(true);

                                restaurantView.setDashBoardSearchOrderContainerPanelVisibility(false);
                                RestaurantModel.setIsDashBoardSearchOrderViewPanelOpen(false);
                                restaurantView.setDashBoardSearchOrderViewPanelVisibility(false);

                                LocalDate currentDate = LocalDate.now();
                                List<List<Object>> orders = model.getOrdersOfTheSelectedDate(currentDate, "toPickUp", "");
                                restaurantView.loadCheckOutOrdersListPanel(orders);

                            }
                        });

                        restaurantView.addCheckOutOrderSearchBarTxtListener(new DocumentListener() {
                            @Override
                            public void insertUpdate(DocumentEvent e) {
                                restaurantView.closecheckOutOrderViewPanel();
                                String orderCode = restaurantView.getCheckOutOrderSearchBarCode() + "%";
                                LocalDate currentDate = LocalDate.now();
                                List<List<Object>> orders = model.getOrdersOfTheSelectedDate(currentDate, "search", orderCode);
                                restaurantView.loadCheckOutOrdersListPanel(orders);
                            }

                            @Override
                            public void removeUpdate(DocumentEvent e) {
                                restaurantView.closecheckOutOrderViewPanel();
                                String orderCode = restaurantView.getCheckOutOrderSearchBarCode() + "%";
                                LocalDate currentDate = LocalDate.now();
                                List<List<Object>> orders = model.getOrdersOfTheSelectedDate(currentDate, "search", orderCode);
                                restaurantView.loadCheckOutOrdersListPanel(orders);
                            }

                            @Override
                            public void changedUpdate(DocumentEvent e) {

                            }
                        });

                        restaurantView.addOrderCheckOutBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                restaurantView.setCheckOutOrderPaymentPanelVisibility(true);
                                int orderTotal = model.getCheckOutOrderTotal();
                                restaurantView.setPaymentPanelOrderTotal(orderTotal);
                            }
                        });

                        restaurantView.addPaidAmountTextFieldListener(new DocumentListener() {
                            @Override
                            public void insertUpdate(DocumentEvent e) {
                                String paidAmount = restaurantView.getPaidAmount();
                                if(!paidAmount.contains(" ") && paidAmount.matches("\\d+")){
                                    if(Integer.parseInt(paidAmount) >= model.getCheckOutOrderTotal()){
                                        restaurantView.setCheckOutOrderbalance(String.valueOf(Integer.parseInt(paidAmount) - model.getCheckOutOrderTotal()));
                                    }else{
                                        restaurantView.setCheckOutOrderbalance("");
                                    }
                                }else{
                                    restaurantView.setCheckOutOrderbalance("");
                                }
                            }

                            @Override
                            public void removeUpdate(DocumentEvent e) {
                                String paidAmount = restaurantView.getPaidAmount();
                                if(!paidAmount.contains(" ") && paidAmount.matches("\\d+")){
                                    if(Integer.parseInt(paidAmount) >= model.getCheckOutOrderTotal()){
                                        restaurantView.setCheckOutOrderbalance(String.valueOf(Integer.parseInt(paidAmount) - model.getCheckOutOrderTotal()));
                                    }else{
                                        restaurantView.setCheckOutOrderbalance("");
                                    }
                                }else{
                                    restaurantView.setCheckOutOrderbalance("");
                                }
                            }

                            @Override
                            public void changedUpdate(DocumentEvent e) {

                            }
                        });

                        restaurantView.addPaymentDoneBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(!restaurantView.getCheckOutOrderBalance().isEmpty()){
                                    restaurantView.clearCheckOutPanelSearchBar();
                                    int orderId = model.getCheckOutOrderId();
                                    model.changeOrderStatus("completed", orderId);
                                    restaurantView.displayPaymentSuccessMsge();

                                    restaurantView.setCheckOutOrderPaymentPanelVisibility(false);
                                    restaurantView.clearPaymentPanel();
                                    restaurantView.closeCheckOutOrderViewPanel();

                                    LocalDate currentDate = LocalDate.now();
                                    List<List<Object>> orders = model.getOrdersOfTheSelectedDate(currentDate, "toPickUp", "");
                                    restaurantView.loadCheckOutOrdersListPanel(orders);


                                    new EmailSender("hirunadilmith15@gmail.com", model.getCheckOutOrderCustEmail(), "Order #" + model.getCheckOutOrderCode() + " Completed",
                                            "Order code: #" + "\n\nTotal amount: LKR " + model.getCheckOutOrderTotal() + "\nPaid Amount: LKR " + restaurantView.getPaidAmount() + "\nBalance: LKR" +
                                                    restaurantView.getCheckOutOrderBalance());
                                }else{
                                    restaurantView.displayPaymentFailedMsge();
                                }
                            }
                        });

                        restaurantView.addPaymentPanelCloseBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                restaurantView.setCheckOutOrderPaymentPanelVisibility(false);
                                restaurantView.clearPaymentPanel();
                            }
                        });

                        restaurantView.addMenuItemAddBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String[] details = restaurantView.getAddMenuItemDetails();
                                BufferedImage image = restaurantView.getSelectedMenuItemImage();

                                if(image == null){
                                    restaurantView.displayMenuItemAddValidateDialog("Please add an image!");
                                }
                                else if(details[0].isEmpty()){
                                    restaurantView.displayMenuItemAddValidateDialog("Item name is required!");
                                }
                                else if(details[1].isEmpty()){
                                    restaurantView.displayMenuItemAddValidateDialog("Item price is required!");
                                }
                                else if(details[2].isEmpty()){
                                    restaurantView.displayMenuItemAddValidateDialog("Add the description!");
                                }
                                else if(details[3].isEmpty()){
                                    restaurantView.displayMenuItemAddValidateDialog("Select a category!");
                                }
                                else{
                                    if(model.addMenuItem(details, image)){
                                        restaurantView.displayMenuItemAddValidateDialog("Menu Item added successfully");
                                        restaurantView.clearMenuAddPanel();

                                        restaurantView.clearMenuBottomPanel();
                                        List<List<Object>> menuItems = RestaurantModel.getMenu();

                                        for (List<Object> menuItem : menuItems) {
                                            restaurantView.addMenuItemsToEditPanel(menuItem);
                                        }
                                    }
                                }
                            }
                        });

                        restaurantView.addSaveChangesBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                boolean isNewDataValid = false;
                                List<List<Object>> menuItems = new ArrayList<>();
                                Component[] components = restaurantView.getMenuBottomPanel().getComponents();

                                if (components != null && components.length > 0){

                                    for (Component editMenuItemPanel : components) {
                                        List<Object> menuItem = new ArrayList<>();

                                        int itemId = Integer.parseInt(((JTextField) editMenuItemPanel.getComponentAt(10, 30)).getText());
                                        String name = ((JTextField) editMenuItemPanel.getComponentAt(90, 30)).getText();
                                        String priceString = ((JTextField) editMenuItemPanel.getComponentAt(330, 30)).getText();

                                        Pattern pattern = Pattern.compile("\\D");
                                        int price;
                                        if(priceString.isEmpty()){
                                            price = -1;
                                        }
                                        else if(pattern.matcher(priceString).find()){
                                            price = -1;
                                        }
                                        else{
                                            price = Integer.parseInt(priceString);
                                        }

                                        String description = "";
                                        for(Component c : ((JScrollPane) editMenuItemPanel.getComponentAt(435, 10)).getViewport().getComponents()){
                                            description = ((JTextArea) c).getText();
                                        }

                                        String category = ((JTextField) editMenuItemPanel.getComponentAt(745, 10)).getText();

                                        BufferedImage image = restaurantView.getItemImages().get(String.valueOf(itemId));



                                        if(name.isEmpty() || priceString.isEmpty() || description.isEmpty() || Objects.equals(category, "not selected")){
                                            isNewDataValid = false;
                                            restaurantView.displayMenuItemsSaveChangesErrorMsge("Cannot save changes because some fields are empty!");
                                            break;
                                        }else if(price < 0){
                                            isNewDataValid = false;
                                            restaurantView.displayMenuItemsSaveChangesErrorMsge("Invalid price has entered!");
                                            break;
                                        }else{
                                            menuItem.add(itemId);
                                            menuItem.add(name);
                                            menuItem.add(price);
                                            menuItem.add(description);
                                            menuItem.add(category);
                                            menuItem.add(image);

                                            menuItems.add(menuItem);
                                            isNewDataValid = true;
                                        }
                                    }

                                    if(isNewDataValid){
                                        if(model.updateMenuItems(menuItems)){
                                            restaurantView.displayMenuItemsSaveChangesMsge("Changes appliyed");
                                        }else{
                                            restaurantView.displayMenuItemsSaveChangesErrorMsge("Changes didn't apply, check againg!");
                                        }
                                    }
                                }
                            }
                        });

                        restaurantView.addRestEditedDataSaveBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                List<Object> editPanelMiddleData = restaurantView.getEditPanelMiddleData();

                                if(((BufferedImage) editPanelMiddleData.get(0)) == null){
                                    restaurantView.displayEditPanelDataValidateMsge("Select a banner image!");
                                }
                                else if(!(((String) editPanelMiddleData.get(1)).matches("[0-9]{10}"))){
                                    restaurantView.displayEditPanelDataValidateMsge("enter a valid phone number!");
                                }
                                else if(((int) editPanelMiddleData.get(2)) == 0){
                                    restaurantView.displayEditPanelDataValidateMsge("Select a allow order type");
                                }
                                else{
                                    if(model.updateRestChangedData(editPanelMiddleData)){
                                        restaurantView.displayUpdateDataMsge();
                                    }else{
                                        restaurantView.displayEditPanelDataValidateMsge("Image is too large or not a valid type!");
                                        restaurantView.repainRestBannerPanel();
                                    }
                                }
                            }
                        });

                        restaurantView.addRestEnableBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(Objects.equals(restaurantView.getIsLive(), "no")){
                                    List<Object> restaurantDetails = model.getRestaurantDetails();

                                    if(((BufferedImage) restaurantDetails.get(5)) == null){
                                        restaurantView.displayEditPanelDataValidateMsge("can't enable the restaurant without a banner image!");
                                    }else if(((String) restaurantDetails.get(6)) == null){
                                        restaurantView.displayEditPanelDataValidateMsge("can't enable the restaurant without setting up a allow orders type!");
                                    }else if(!RestaurantModel.checkIfTheRestHasFoodItems()){
                                        restaurantView.displayEditPanelDataValidateMsge("can't enable the restaurant without at least one menuItem!");
                                    }else{
                                        restaurantView.setIsLive("yes");
                                        RestaurantModel.updateIsLive("yes");
                                        restaurantView.changeRestEnableBtnStyle("yes");
                                        restaurantView.setRestEnableDisableMsge("Restaurant Enabled");
                                    }
                                }
                                else{
                                    restaurantView.setIsLive("no");
                                    RestaurantModel.updateIsLive("no");
                                    restaurantView.changeRestEnableBtnStyle("no");
                                    restaurantView.setRestEnableDisableMsge("Restaurant Disabled");
                                }
                            }
                        });

                        restaurantView.addEditItemsBtnPanelListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                restaurantView.closeEditSelectCategoryScrollPane();
                                restaurantView.applyNavigationBtnsStyles("editItem");

                                restaurantView.openAddCategoryContainerPanel(false);
                                restaurantView.openMenuAddContainerPanel(false);
                                restaurantView.openEditCategoryContainerPanel(false);
                                restaurantView.openMenuEditPanel(true);
                                restaurantView.setVisibilityOfMenuSaveChangesBtn(true);

                                restaurantView.clearMenuBottomPanel();

                                List<List<Object>> menuItems = RestaurantModel.getMenu();

                                for (List<Object> menuItem : menuItems) {
                                    restaurantView.addMenuItemsToEditPanel(menuItem);
                                }
                            }
                        });

                        restaurantView.addEditCategoriesBtnPanelListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                restaurantView.closeEditSelectCategoryScrollPane();

                                List<String> categories = RestaurantModel.getCategories();

                                if(categories.isEmpty()){
                                    restaurantView.displayEditCategoryPanelCantOpenMsge("No categories has been added  yet!");
                                }else{
                                    restaurantView.applyNavigationBtnsStyles("editCategory");
                                    restaurantView.clearCategoriesInCategoryEditPanel();
                                    restaurantView.openMenuEditPanel(false);
                                    restaurantView.openAddCategoryContainerPanel(false);
                                    restaurantView.openMenuAddContainerPanel(false);
                                    restaurantView.openEditCategoryContainerPanel(true);
                                    restaurantView.setVisibilityOfMenuSaveChangesBtn(false);

                                    for(String category : categories){
                                        int count = RestaurantModel.getCategoryItemsCount(category);
                                        restaurantView.loadCategoriesToCategoryEditPanel(category, count);
                                    }
                                }
                            }
                        });

                        restaurantView.addItemsBtnPanelListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                restaurantView.closeEditSelectCategoryScrollPane();
                                List<String> categories = RestaurantModel.getCategories();
                                if(!categories.isEmpty()){
                                    restaurantView.clearSelectCategoryPanel();

                                    restaurantView.applyNavigationBtnsStyles("addItem");

                                    restaurantView.openEditCategoryContainerPanel(false);
                                    restaurantView.openMenuEditPanel(false);
                                    restaurantView.openAddCategoryContainerPanel(false);
                                    restaurantView.openMenuAddContainerPanel(true);
                                    restaurantView.setVisibilityOfMenuSaveChangesBtn(false);

                                    for(String category : categories ){
                                        restaurantView.loadCategoriesAddItemPanel(category);
                                    }
                                }else{
                                    restaurantView.displayInputValidateErorMsge("To add food Items there should be at least one category!");
                                }
                            }
                        });

                        restaurantView.addCategoriesBtnPanelListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                restaurantView.closeEditSelectCategoryScrollPane();
                                restaurantView.applyNavigationBtnsStyles("addCategory");

//                                restaurantView.clearMenuBottomPanel();
                                restaurantView.openEditCategoryContainerPanel(false);
                                restaurantView.openMenuEditPanel(false);
                                restaurantView.openMenuAddContainerPanel(false);
                                restaurantView.openAddCategoryContainerPanel(true);
                                restaurantView.setVisibilityOfMenuSaveChangesBtn(false);
                            }
                        });

                        restaurantView.addCategoryBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(restaurantView.getCategory().isEmpty()){
                                    restaurantView.displayInputValidateErorMsge("Enter the category that you want to add!");
                                }else if(model.checkIfCategoryExist(restaurantView.getCategory())){
                                    restaurantView.displayInputValidateErorMsge("The category is already exist in the menu");
                                    restaurantView.clearAddCategoryField();
                                }else{
                                    if(model.addCategory(restaurantView.getCategory())){
                                        restaurantView.displayCategoryAddedMsge("The category '" + restaurantView.getCategory() + "' added");
                                        restaurantView.clearAddCategoryField();
                                    }else{
                                        restaurantView.displayInputValidateErorMsge("Category adding failed!");
                                    }
                                }
                            }
                        });

                        restaurantView.addEditCategorySaveChangesBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                List<List<String>> newCategoryNames = new ArrayList<>();
                                Component[] components = restaurantView.getEditCategoryPanel().getComponents();
                                String msge = "";
                                String isNewDataValid = "";


                                for(Component categoryPanel : components){
                                    List<String> newCategoryName = new ArrayList<>();
                                    String newCatName = ((JTextField) categoryPanel.getComponentAt(10, 7)).getText();
                                    String id = (String) ((JTextField) categoryPanel.getComponentAt(10, 7)).getClientProperty("ID");

                                    if(!Objects.equals(newCatName, id)){
                                        if(newCatName.isEmpty()){
                                            isNewDataValid = "no";
                                            msge = "Can't add empty category names!!";
                                            break;
                                        }else if(model.checkIfCategoryExist(newCatName) && !newCatName.equalsIgnoreCase(id)){
                                            isNewDataValid = "no";
                                            msge = "can't add duplicate category names!";
                                            break;
                                        }
                                        else{
                                            newCategoryName.add(newCatName);
                                            newCategoryName.add(id);
                                            newCategoryNames.add(newCategoryName);
                                            isNewDataValid = "yes";
                                        }
                                    }
                                }

                                if(isNewDataValid.equals("yes")){
                                    if(model.updateCategoryName(newCategoryNames)){
                                        restaurantView.displayMenuItemsSaveChangesMsge("Changes applied");
                                    }else{
                                        restaurantView.displayMenuItemsSaveChangesErrorMsge("Update failed!");
                                    }
                                }else if(isNewDataValid.equals("no")){
                                    restaurantView.displayMenuItemsSaveChangesErrorMsge(msge);
                                }
                            }
                        });

                        restaurantView.addNewOrdersBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                model.setIsNewOrdersPanelOpen(true);
                                model.setIsAcceptedOrdersPanelOpen(false);
                                model.setIsPreparingPanelOpen(false);

                                restaurantView.setOrderViewPanelVisibility(false);
                                restaurantView.setClickedOrderBtnStyles("new");
                            }
                        });

                        restaurantView.addAcceptedOrdersBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                model.setIsNewOrdersPanelOpen(false);
                                model.setIsAcceptedOrdersPanelOpen(true);
                                model.setIsPreparingPanelOpen(false);

                                restaurantView.setOrderViewPanelVisibility(false);
                                restaurantView.setClickedOrderBtnStyles("accepted");
                            }
                        });

                        restaurantView.addPreparingOrdersBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                model.setIsNewOrdersPanelOpen(false);
                                model.setIsAcceptedOrdersPanelOpen(false);
                                model.setIsPreparingPanelOpen(true);

                                restaurantView.setOrderViewPanelVisibility(false);
                                restaurantView.setClickedOrderBtnStyles("preparing");
                            }
                        });

                        restaurantView.addDeliveryOrdersBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                model.setIsDeliveryOrdersOptionSelected(true);
                                model.setIsPickUpOrdersOptionSelected(false);
                                restaurantView.setClickedOrderTypeOptionBtnStyles("delivery");

                                restaurantView.setOrderViewPanelVisibility(false);
                            }
                        });

                        restaurantView.addPickupOrdersBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                model.setIsDeliveryOrdersOptionSelected(false);
                                model.setIsPickUpOrdersOptionSelected(true);
                                restaurantView.setClickedOrderTypeOptionBtnStyles("pickup");

                                restaurantView.setOrderViewPanelVisibility(false);
                            }
                        });

                        restaurantView.addOrderAcceptBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int orderId = Integer.parseInt(restaurantView.getAcceptBtnId());
                                model.changeOrderStatus("accept", orderId);
                                String orderCode = model.getOrdersPanelOpenOrderCode();
                                restaurantView.displayOrderAcceptOrDeclineMsge("Order #" + orderCode + " accepted");
                                restaurantView.setOrderViewPanelVisibility(false);

                                String restEmail = model.getRestaurantUsrname();
                                String userEmail = model.getUserEmailOfTheOpenOrder(orderId);
                                String subject = "Order #" + orderCode + " accepted: " + model.getRestBranchName();
                                String msge = "Order code: #" + orderCode +"\nYour order placed with " + model.getRestBranchName() + " has been accepted. we" +
                                        " will notify you once the order is complete" + "\nContact: " + model.getRestMobile() + "\nAddress: " +
                                        model.getRestAddress() + "\n\nThank you\nUber Eats";
                                new EmailSender(restEmail, userEmail, subject, msge);
                            }
                        });

                        restaurantView.addOrderDeclineBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int orderId = Integer.parseInt(restaurantView.getDeclineBtnId());
                                model.changeOrderStatus("decline", orderId);
                                String orderCode = model.getOrdersPanelOpenOrderCode();
                                restaurantView.displayOrderAcceptOrDeclineMsge("Order #" + orderCode + " declined!");
                                restaurantView.setOrderViewPanelVisibility(false);

                                String restEmail = model.getRestaurantUsrname();
                                String userEmail = model.getUserEmailOfTheOpenOrder(orderId);
                                String subject = "Order #" + orderCode + " declined: " + model.getRestBranchName();
                                String msge = "Order code: #" + orderCode +"\nYour order placed with " + model.getRestBranchName() + " has been declined.\nContact: " + model.getRestMobile() + "\nAddress: " +
                                        model.getRestAddress() + "\n\nThank you\nUber Eats";
                                new EmailSender(restEmail, userEmail, subject, msge);
                            }
                        });

                        restaurantView.addOrderStartToPrepareBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int orderId = Integer.parseInt(restaurantView.getStartToPrepareBtnId());
                                model.changeOrderStatus("preparing", orderId);
                                String orderCode = model.getOrdersPanelOpenOrderCode();
                                restaurantView.displayOrderAcceptOrDeclineMsge("Order #" + orderCode + " started to prepare");
                                restaurantView.setOrderViewPanelVisibility(false);

//                                String restEmail = model.getRestaurantUsrname();
//                                String userEmail = model.getUserEmailOfTheOpenOrder(orderId);
//                                String subject = "Order #" + orderCode + " is being prepared: " + model.getRestBranchName();
//
//                                String msge;
//                                if(model.getIsDeliveryOrdersOptionSelected()){
//                                    msge = "Order code: #" + orderCode +"\nYour order placed with " + model.getRestBranchName() + " is being prepared. you will be notified once it's ready to deliver.\nContact: " + model.getRestMobile() +  "\n\nThank you\nUber Eats";
//                                }else{
//                                    msge = "Order code: #" + orderCode +"\nYour order placed with " + model.getRestBranchName() + " is being prepared. you will be notified once it's ready to pick up\nContact: " + model.getRestMobile() + "\nAddress: " +
//                                            model.getRestAddress() + "\n\nThank you\nUber Eats";
//                                }
//                                new EmailSender(restEmail, userEmail, subject, msge);
                            }
                        });

                        restaurantView.addOrderPreparationCompleteBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int orderId = Integer.parseInt(restaurantView.getOrderPreparationCompleteBtnId());
                                model.changeOrderStatus("prepared", orderId);
                                String orderCode = model.getOrdersPanelOpenOrderCode();
                                if(model.getIsDeliveryOrdersOptionSelected()){
                                    restaurantView.displayOrderAcceptOrDeclineMsge("Order #" + orderCode + " is ready to deliver");
                                }else{
                                    restaurantView.displayOrderAcceptOrDeclineMsge("Order #" + orderCode + " is ready to pickup");
                                }
                                restaurantView.setOrderViewPanelVisibility(false);

                                String restEmail = model.getRestaurantUsrname();
                                String userEmail = model.getUserEmailOfTheOpenOrder(orderId);

                                String subject;
                                if(model.getIsDeliveryOrdersOptionSelected()){
                                    subject = "Order #" + orderCode + " is ready to deliver: " + model.getRestBranchName();
                                }else{
                                    subject = "Order #" + orderCode + " is ready to pickup: " + model.getRestBranchName();
                                }

                                String msge;
                                int orderTotal = model.getOpenOrderTotalPrice(orderId);
                                if(model.getIsDeliveryOrdersOptionSelected()){
                                    msge = "Order code: #" + orderCode + "\nOrder amount: LKR" + orderTotal + "\n\nYour order placed with " + model.getRestBranchName() + " is ready to deliver. you will be notified once a delivery driver picks your order.\nContact: " + model.getRestMobile() +  "\n\nThank you\nUber Eats";
                                }else{
                                    msge = "Order code: #" + orderCode + "Order amount: LKR" + orderTotal + "\n\nYour order placed with " + model.getRestBranchName() + " is ready to pickup. you can pick up the order now.\nContact: " + model.getRestMobile() + "\nAddress: " +
                                            model.getRestAddress() + "\n\nThank you\nUber Eats";
                                }
                                new EmailSender(restEmail, userEmail, subject, msge);
                            }
                        });



                        ///////////dashboard/////////////
                        restaurantView.addTodayBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                restaurantView.setTimePeriodBtnStyles("today");
                                model.setIsTodayBtnSelected(true);
                                model.setIsThisWeekBtnSelected(false);
                                model.setIsThisMonthBtnSelected(false);
                                model.setIsThisYearBtnSelected(false);
                            }
                        });

                        restaurantView.addThisWeekBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                restaurantView.setTimePeriodBtnStyles("thisweek");
                                model.setIsTodayBtnSelected(false);
                                model.setIsThisWeekBtnSelected(true);
                                model.setIsThisMonthBtnSelected(false);
                                model.setIsThisYearBtnSelected(false);
                            }
                        });

                        restaurantView.addThisMonthBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                restaurantView.setTimePeriodBtnStyles("thismonth");
                                model.setIsTodayBtnSelected(false);
                                model.setIsThisWeekBtnSelected(false);
                                model.setIsThisMonthBtnSelected(true);
                                model.setIsThisYearBtnSelected(false);
                            }
                        });

                        restaurantView.addThisYearBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                restaurantView.setTimePeriodBtnStyles("thisyear");
                                model.setIsTodayBtnSelected(false);
                                model.setIsThisWeekBtnSelected(false);
                                model.setIsThisMonthBtnSelected(false);
                                model.setIsThisYearBtnSelected(true);
                            }
                        });

                        restaurantView.addFindOrdersPanelSearchByDateBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                RestaurantModel.setIsDashBoardSearchOrderViewPanelOpen(false);
                                restaurantView.setDashBoardSearchOrderViewPanelVisibility(false);

                                LocalDate selectedDate = restaurantView.getSelectedDate();
                                if(selectedDate != null){
                                    List<List<Object>> orders = model.getOrdersOfTheSelectedDate(selectedDate, "all", "");
                                    if(orders.isEmpty()){
                                        restaurantView.setDashBoardSearchOrderContainerPanelVisibility(false);
                                        restaurantView.displayNoSearchResultMsge("No orders on " + selectedDate.toString());
                                    }else{
                                        restaurantView.setDashBoardSearchOrderContainerPanelVisibility(true);
                                        restaurantView.loadSearchOrdersSelectPanel(orders);
                                    }
                                }
                            }
                        });

                        restaurantView.addFindOrdersPanelSearchByIdBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String regex = "\\d+";
                                String orderCode = restaurantView.getSearchOrderCode();
                                if(orderCode.matches(regex)){
                                    List<List<Object>> orders = model.getTheOrderOfTheSearchedId(orderCode);
                                    if(orders.isEmpty()){
                                        restaurantView.setDashBoardSearchOrderContainerPanelVisibility(false);
                                        restaurantView.displayNoSearchResultMsge("No Results");
                                    }else{
                                        restaurantView.setDashBoardSearchOrderContainerPanelVisibility(true);
                                        restaurantView.loadSearchOrdersSelectPanel(orders);
                                    }
                                }
                            }
                        });

                        restaurantView.addDashboardSearchPanelCloseBtn1Listener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                restaurantView.setDashBoardSearchOrderContainerPanelVisibility(false);
                                RestaurantModel.setIsDashBoardSearchOrderViewPanelOpen(false);
                                restaurantView.setDashBoardSearchOrderViewPanelVisibility(false);
                            }
                        });

                        restaurantView.addDashboardSearchPanelCloseBtn2Listener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                restaurantView.setDashBoardSearchOrderContainerPanelVisibility(false);
                                RestaurantModel.setIsDashBoardSearchOrderViewPanelOpen(false);
                                restaurantView.setDashBoardSearchOrderViewPanelVisibility(false);
                            }
                        });

                        restaurantView.addRestOpenCloseBtnListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if(!Objects.equals(restaurantView.getIsLive(), "no")){
                                    if(model.chekRestOpenOrClose()){
                                        model.setRestOpenOrClose("no");
                                        restaurantView.displayRestOpenCloseMsge("Rstaurant closed");
                                        restaurantView.setRestOpenCloseBtnStyles("close");
                                    }else{
                                        model.setRestOpenOrClose("yes");
                                        restaurantView.displayRestOpenCloseMsge("Rstaurant Opened");
                                        restaurantView.setRestOpenCloseBtnStyles("open");
                                    }
                                }
                            }
                        });
                        ////
                    }
                    else{
                        loginView.displayInvalidLogMessage("Invalid login details!");
                    }
                }
            }
        });
    }

    public void run(){
        while(true){
            if(model.isOrdersPanelOpen()){
                if(RestaurantModel.getIsNewOrdersPanelOpen()){
                    List<List<Object>> newOrders;
                    if(model.getIsDeliveryOrdersOptionSelected()){
                        newOrders = model.getNewOrders("new", "Delivery");
                    }else{
                        newOrders = model.getNewOrders("new", "Pickup");
                    }

                    restaurantView.loadNewOrdersPanel(newOrders);
                }

                if(RestaurantModel.getIsAcceptedOrdersPanelOpen()){
                    List<List<Object>> newOrders;
                    if(model.getIsDeliveryOrdersOptionSelected()){
                        newOrders = model.getNewOrders("accept", "Delivery");
                    }else{
                        newOrders = model.getNewOrders("accept", "Pickup");
                    }

                    restaurantView.loadNewOrdersPanel(newOrders);
                }

                if(RestaurantModel.getIsPreparingPanelOpen()){
                    List<List<Object>> newOrders;
                    if(model.getIsDeliveryOrdersOptionSelected()){
                        newOrders = model.getNewOrders("preparing", "Delivery");
                    }else{
                        newOrders = model.getNewOrders("preparing", "Pickup");
                    }

                    restaurantView.loadNewOrdersPanel(newOrders);
                }
            }else if(model.isHomePanelOpen()){
                int[] ordersStatics;
                if(model.getIsTodayBtnSelected()){
                    ordersStatics = model.getDashBoardOrderStatics("today");
                }else if(model.getIsThisMonthBtnSelected()){
                    ordersStatics = model.getDashBoardOrderStatics("thisMonth");
                }else if(model.getIsThisYearBtnSelected()){
                    ordersStatics = model.getDashBoardOrderStatics("thisYear");
                }else{
                    ordersStatics = model.getDashBoardOrderStatics("thisWeek");
                }

                restaurantView.loadDashBoardOrderStatics(ordersStatics);

                if(model.getIsDashBoardSearchOrderViewPanelOpen()){
                    String status = model.getDashBoardSearchSelectedOrderStatus();
                    restaurantView.setDashBoardSearchSelectedOrderStatus(status);
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
