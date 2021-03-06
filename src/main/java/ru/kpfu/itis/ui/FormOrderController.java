package ru.kpfu.itis.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.entity.Order;
import ru.kpfu.itis.service.OrderService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static ru.kpfu.itis.utils.Utils.isEmpty;

/**
 * Created by katemrrr on 12.05.17.
 */
public class FormOrderController {

    private Stage formOrderStage;
    private Long orderId;
    private boolean isAdmin;

    public void setFormOrderStage(Stage formOrderStage) {
        this.formOrderStage = formOrderStage;
    }


    @Autowired
    private OrderService orderService;

    @Autowired
    AdminController adminController;

    private ObservableList<Order> data;

    @FXML private TextField surnameField;
    @FXML private TextField nameField;
    @FXML private TextField patronymicField;
    @FXML private TextField phoneField;
    @FXML private TextField modelField;
    @FXML private TextField gettingField;
    @FXML private TextField refundingField;

    @FXML public void initialize() {}

    @FXML
    public void addOrder(){
        if (isValidInput()){
            Order newOrder = new Order(surnameField.getText(), nameField.getText(), patronymicField.getText(),
                    modelField.getText(), phoneField.getText(), gettingField.getText(), refundingField.getText());
            newOrder.setId(orderId);
            orderService.save(newOrder);
            if (isAdmin) {
                for (Order order : data) {
                    if (order.getId() == newOrder.getId()) {
                        data.remove(order);
                        break;
                    }
                }
                data.add(newOrder);
                adminController.refreshTable();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Спасибо Вам!");
                alert.setContentText("Спасибо вам за заказ. Наши операторы скоро вам перезвонят.");
                alert.showAndWait();
            }
            adminController.refreshTable();
            formOrderStage.close();
        }
    }

    private boolean validateData(){
        return !(isEmpty(surnameField.getText()) || isEmpty(nameField.getText()) || isEmpty(patronymicField.getText()) || isEmpty(phoneField.getText()) ||
                isEmpty(modelField.getText()) || isEmpty(gettingField.getText()) || isEmpty(refundingField.getText()));
    }

    private boolean validateDate(String s){
        try {
            String day = s.substring(0, 2);
            String month = s.substring(3, 5);
            String year = s.substring(6, 10);
            int d = Integer.parseInt(day);
            int m = Integer.parseInt(month);
            int y = Integer.parseInt(year);
            if (d > 31 || d < 0 || m > 12 || m < 0 || y < 0){
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean checkDate(){
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date gdate;
        Date rdate;
        Calendar curDate = Calendar.getInstance(Locale.getDefault());
        Date cdate;
        try {
            String today = new Date().toLocaleString();
            cdate = format.parse(today.substring(0, today.indexOf(" ")));
            gdate = format.parse(gettingField.getText());
            rdate = format.parse(refundingField.getText());
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        if(!gdate.before(cdate) && !rdate.before(gdate))
            return true;
        return false;
    }

    public boolean isValidInput(){
        boolean isValid = true;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(formOrderStage);

        if (!validateData()) {
            alert.setContentText("Введите корректные данные!");
            isValid = false;
        } else if (!validateDate(refundingField.getText())){
            alert.setContentText("Введите дату возврата в формате дд.мм.гггг.");
            isValid = false;
        } else if (!validateDate(gettingField.getText())){
            alert.setContentText("Введите дату выдачи в формате дд.мм.гггг.");
            isValid = false;
        } else if (!checkDate()){
            alert.setContentText("Введите корректную дату!");
            isValid = false;
        }

        if(!isValid){
            alert.showAndWait();
        }

        return isValid;
    }

    public void setData(ObservableList<Order> data) {
        this.data = data;
    }

    @FXML
    public void cancelForm(){
        formOrderStage.close();
    }

    public ObservableList<Order> getData() {
        return data;
    }

    public void fillOrder(Order order) {
        orderId = order.getId();

        surnameField.setText(order.getSurname());
        nameField.setText(order.getName());
        patronymicField.setText(order.getPatronymic());
        phoneField.setText(order.getPhone());
        modelField.setText(order.getModel());
        gettingField.setText(order.getGetting());
        refundingField.setText(order.getRefunding());
    }

    public void clearOrder() {
        if (isAdmin) {
            fillOrder(new Order("", "", "", "", "", "", ""));
        } else {
            fillOrder(new Order("", "", "", "", "", "", ""));
        }
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
