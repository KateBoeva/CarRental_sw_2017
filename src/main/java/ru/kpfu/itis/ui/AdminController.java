package ru.kpfu.itis.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.kpfu.itis.entity.Car;
import ru.kpfu.itis.entity.Order;
import ru.kpfu.itis.service.OrderService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by katemrrr on 12.05.17.
 */

//@Component
@SuppressWarnings("SpringJavaAutowiringInspection")
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(SignUpController.class);

    private Stage adminStage;

    @Autowired
    OrderService orderService;

    @FXML private TabPane tabPane;
    @FXML private TableView<Order> ordersTable;
    @FXML private Tab ordersTab;
    @FXML private TableView<Car> carTable;
    @FXML private Tab carsTab;

//    @FXML private Label surnameLabel;
//    @FXML private Label nameLabel;
//    @FXML private Label patronymicLabel;
//    @FXML private Label phoneLabel;
//    @FXML private Label modelLabel;
//    @FXML private Label gettingLabel;
//    @FXML private Label refundingLabel;

    private ObservableList<Car> carData;
    private ObservableList<Order> orderData;

    @FXML
    public void initialize() {}

    public AdminController() {}

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {

//        tabPane.setSide(Side.LEFT);
//        tabPane.getTabs();

        List<Order> orders = orderService.findAll();
        orderData = FXCollections.observableArrayList(orders);

        TableColumn<Order, String> nameColumn = new TableColumn<>("Имя");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Order, String> modelColumn = new TableColumn<>("Модель/марка");
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        ordersTable.getColumns().setAll(nameColumn, modelColumn);

        //Добавление в таблицу данных из наблюдаемого списка
        ordersTable.setItems(orderData);
    }


    public void setAdminStage(Stage adminStage) {
        this.adminStage = adminStage;
    }
}
