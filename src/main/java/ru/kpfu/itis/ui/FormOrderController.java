package ru.kpfu.itis.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.entity.Order;
import ru.kpfu.itis.service.OrderService;

import static ru.kpfu.itis.utils.Utils.isEmpty;

/**
 * Created by katemrrr on 12.05.17.
 */
public class FormOrderController {

    private Stage formOrderStage;
    private Long orderId;

    public void setFormOrderStage(Stage formOrderStage) {
        this.formOrderStage = formOrderStage;
    }


    @Autowired
    private OrderService orderService;

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
            Order newOrder = new Order(surnameField.getText(), nameField.getText(), patronymicField.getText(), modelField.getText(), phoneField.getText(), gettingField.getText(), refundingField.getText());
            newOrder.setId(orderId);
            orderService.save(newOrder);
            for (Order order : data) {
                if(order.getId() == newOrder.getId()){
                    data.remove(order);
                    break;
                }
            }
            data.add(newOrder);
            formOrderStage.close();
        }
    }

    public boolean isValidInput(){
        boolean error = true;

        if (isEmpty(surnameField.getText()) || isEmpty(nameField.getText()) || isEmpty(patronymicField.getText()) || isEmpty(phoneField.getText()) ||
                isEmpty(modelField.getText()) || isEmpty(gettingField.getText()) || isEmpty(refundingField.getText())) {
            error = false;
        }

        if (error){
            return error;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(formOrderStage);
            alert.setHeaderText("Correct invalid fields!");
            alert.setTitle("Invalid Fields");
            alert.setContentText("Введите корректные данные!");

            alert.showAndWait();

            return false;
        }
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
        fillOrder(new Order("", "", "", "", "", "", ""));
    }
}
