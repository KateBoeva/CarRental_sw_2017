package ru.kpfu.itis.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.kpfu.itis.ConfigControllers;
import ru.kpfu.itis.entity.Car;
import ru.kpfu.itis.entity.Order;
import ru.kpfu.itis.service.CarService;
import ru.kpfu.itis.service.OrderService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

import static ru.kpfu.itis.utils.Utils.createFrame;


/**
 * Created by katemrrr on 12.05.17.
 */

@SuppressWarnings("SpringJavaAutowiringInspection")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    private Stage adminStage;

    public Stage getAdminStage() {
        return adminStage;
    }

    public void setAdminStage(Stage adminStage) {
        this.adminStage = adminStage;
    }

    @Autowired
    private OrderService orderService;

    @Autowired
    private CarService carService;

    @FXML private TabPane tabPane;
    @FXML private Tab ordersTab;
    @FXML private Tab carsTab;

    @FXML private TableView<Order> ordersTable;

    @FXML private TableView<Car> carsTable;
    @FXML private TableColumn<Car, String> nameCar;
    @FXML private TableColumn<Car, String> priceCar;

    @FXML private Label surnameLabel;
    @FXML private Label nameLabel;
    @FXML private Label patronymicLabel;
    @FXML private Label phoneLabel;
    @FXML private Label modelLabel;
    @FXML private Label gettingLabel;
    @FXML private Label refundingLabel;

    @FXML private Label nameCarLabel;
    @FXML private Label yearLabel;
    @FXML private Label runLabel;
    @FXML private Label powerLabel;
    @FXML private Label priceLabel;

    @Autowired
    FormOrderController formOrderController;

    @Autowired
    FormCarController formCarController;

    private ObservableList<Car> carData;

    public void setOrderData(ObservableList<Order> orderData) {
        this.orderData = orderData;
    }

    private ObservableList<Order> orderData;

    @FXML
    public void initialize() {}

    public AdminController() {}

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {

        List<Order> orders = orderService.findAll();
        orderData = FXCollections.observableArrayList(orders);

        TableColumn<Order, String> nameColumn = new TableColumn<>("Клиент");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Order, String> surnameColumn = new TableColumn<>("Surname");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));

        TableColumn<Order, String> modelColumn = new TableColumn<>("Модель");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        ordersTable.getColumns().setAll(nameColumn, surnameColumn, modelColumn);

        ordersTable.setItems(orderData);

        ordersTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showOrderDetails(newValue));
    }

    private void showOrderDetails(Order order){
        order = ordersTable.getSelectionModel().getSelectedItem();
        if (order != null){
            surnameLabel.setText(order.getSurname());
            nameLabel.setText(order.getName());
            patronymicLabel.setText(order.getPatronymic());
            phoneLabel.setText(order.getPhone());
            modelLabel.setText(order.getModel());
            gettingLabel.setText(order.getGetting());
            refundingLabel.setText(order.getRefunding());
        }
    }

    @Qualifier("formOrderView")
    @Autowired
    private ConfigControllers.View formOrderView;

    @Qualifier("formCarView")
    @Autowired
    private ConfigControllers.View formCarView;

    @FXML
    public void addOrder() throws IOException {
        Stage formOrderStage = new Stage();
        createFrame("Добавить бронь", formOrderView, formOrderStage);
        formOrderController.setFormOrderStage(formOrderStage);
        formOrderController.setData(orderData);
        formOrderStage.show();
    }

    @FXML
    public void addCar() throws IOException {
        Stage formCarStage = new Stage();
        createFrame("Добавить автомобиль", formCarView, formCarStage);
        formCarController.setFormCarStage(formCarStage);
        formCarController.setData(carData);
        formCarStage.show();
    }

    @FXML
    public void deleteCar(){

        Car car = carsTable.getSelectionModel().getSelectedItem();
        if (car != null){
            carService.delete(car);
        }

        int index = carsTable.getSelectionModel().getSelectedIndex();

        if (index >= 0) {
            carsTable.getItems().remove(index);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ошибка");
            alert.show();
        }

    }

    @FXML
    public void deleteOrder(){

        Order order = ordersTable.getSelectionModel().getSelectedItem();
        if (order != null){
            orderService.delete(order);
        }

        int index = ordersTable.getSelectionModel().getSelectedIndex();

        if (index >= 0) {
            ordersTable.getItems().remove(index);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ошибка");
            alert.show();
        }

    }

    @FXML
    public void updateOrder(){
        Stage formOrderStage = new Stage();
        createFrame("Изменить бронь", formOrderView, formOrderStage);
        formOrderController.setFormOrderStage(formOrderStage);
        formOrderController.setData(orderData);
        formOrderStage.show();
    }

    @FXML
    public void updateCar(){
        Stage formCarStage = new Stage();
        createFrame("Изменить автомобиль", formCarView, formCarStage);
        formCarController.setFormCarStage(formCarStage);
        formCarController.setData(carData);
        formCarStage.show();
    }
}
