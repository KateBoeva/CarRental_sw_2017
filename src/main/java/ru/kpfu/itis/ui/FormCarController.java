package ru.kpfu.itis.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kpfu.itis.entity.Car;
import ru.kpfu.itis.service.CarService;

import static ru.kpfu.itis.utils.Utils.isEmpty;

/**
 * Created by katemrrr on 12.05.17.
 */


public class FormCarController {

    private Stage formCarStage;
    private Long carId;

    public void setFormCarStage(Stage formCarStage) {
        this.formCarStage = formCarStage;
    }

    @Autowired
    private CarService carService;

    public void setData(ObservableList<Car> data) {
        this.data = data;
    }

    private ObservableList<Car> data;

    public FormCarController() {}

    @FXML private TextField nameField;
    @FXML private TextField priceField;
    @FXML private TextField runField;
    @FXML private TextField powerField;
    @FXML private TextField yearField;

    @FXML public void initialize() {}

    @FXML
    public void addCar(){
        if (isValidInput()){
            Car newCar = new Car(nameField.getText(), Integer.parseInt(priceField.getText()), Integer.parseInt(runField.getText()), Integer.parseInt(powerField.getText()), Integer.parseInt(yearField.getText()));
            newCar.setId(carId);
            carService.save(newCar);
            for (Car car : data) {
                if(car.getId() == newCar.getId()){
                    data.remove(car);
                    break;
                }
            }
            data.add(newCar);
            formCarStage.close();
        }
    }

    public boolean isValidInput(){
        boolean isError = false;

        if (isEmpty(nameField.getText()) || isEmpty(priceField.getText()) || isEmpty(runField.getText()) ||
                isEmpty(powerField.getText()) || isEmpty(yearField.getText())) {
            isError = true;
        }

        if (isError){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(formCarStage);
            alert.setHeaderText("Correct fields!");
            alert.setTitle("Invalid Fields!");
            alert.setContentText("Введите корректные данные!");

            alert.showAndWait();

            return false;
        }
        return true;
    }

    @FXML
    public void cancelForm(){
        formCarStage.close();
    }

    public ObservableList<Car> getData() {
        return data;
    }

    public void fillCar(Car car) {
        carId = car.getId();

        nameField.setText(car.getName());
        priceField.setText(car.getPrice() == 0 ? "" : String.valueOf(car.getPrice()));
        runField.setText(car.getRun() == 0 ? "" : String.valueOf(car.getRun()));
        powerField.setText(car.getPower() == 0 ? "" : String.valueOf(car.getPower()));
        yearField.setText(car.getYear() == 0 ? "" : String.valueOf(car.getYear()));
    }

    public void clearCar() {
        fillCar(new Car("", 0, 0, 0, 0));
    }

}
