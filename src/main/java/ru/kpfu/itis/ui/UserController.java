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
import ru.kpfu.itis.entity.Car;
import ru.kpfu.itis.service.CarService;

import javax.annotation.PostConstruct;
import java.util.List;

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

    @FXML private TableColumn<Car, String> nameColumn;
    @FXML private TableColumn<Car, String> priceColumn;

    @FXML private TableView<Car> table;

    private ObservableList<Car> data;

    @Autowired
    private CarService carService;

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
        data = FXCollections.observableArrayList(cars);

        nameColumn = new TableColumn<>();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("model"));

        priceColumn = new TableColumn<>();
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

//        table.getColumns().setAll(nameColumn, priceColumn);
//        table.setItems(data);
    }

    @FXML
    public void reserveCar(){

    }

}
