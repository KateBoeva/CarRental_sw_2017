package ru.kpfu.itis.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class UserController {

    private Logger logger = LoggerFactory.getLogger(SignUpController.class);

    @FXML private Label modelLabel;
    @FXML private Label yearLabel;
    @FXML private Label runLabel;
    @FXML private Label powerLabel;
    @FXML private Label priceLabel;

    @FXML private TableView<Car> table;

    private ObservableList<Car> dataCar;
    private ObservableList<Order> dataOrder;

    @Autowired
    private CarService carService;

    @Autowired
    private OrderService orderService;

    @Autowired
    FormOrderController formOrderController;

    @Qualifier("formOrderView")
    @Autowired
    private ConfigControllers.View formOrderView;

    private Stage userStage;

    public void setUserStage(Stage userStage) {
        this.userStage = userStage;
    }

    @FXML
    public void initialize() {}

    public UserController() {}

    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {

        List<Car> cars = carService.findAll();
        dataCar = FXCollections.observableArrayList(cars);

        TableColumn<Car, String> nameColumn = new TableColumn<>("Модель");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Car, String> priceColumn = new TableColumn<>("Цена в час (руб.)");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().setAll(nameColumn, priceColumn);
        table.setItems(dataCar);

        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCarDetails(newValue)
        );
    }

    private void showCarDetails(Car car){
        car = table.getSelectionModel().getSelectedItem();
        if (car != null){
            modelLabel.setText(car.getName());
            priceLabel.setText(String.valueOf(car.getPrice()));
            runLabel.setText(String.valueOf(car.getRun()));
            powerLabel.setText(String.valueOf(car.getPower()));
            yearLabel.setText(String.valueOf(car.getYear()));
            int k = 0;
        }
    }

    @FXML
    public void reserveCar() throws IOException{
        Stage formOrderStage = new Stage();
        createFrame("Добавить бронь", formOrderView, formOrderStage);
        formOrderController.setFormOrderStage(formOrderStage);
        formOrderController.setData(dataOrder);
        formOrderController.clearOrder();
        formOrderStage.show();
        int k = 0;
    }

    public ObservableList<Car> getDataCar() {
        return dataCar;
    }
}
