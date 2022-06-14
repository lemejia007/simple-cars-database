
// SimpleCarsDatabaseController.java
package simplecarsdatabase;

import java.net.URL;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class SimpleCarsDatabaseController implements Initializable {
    
  @FXML private TableView tableView;
  @FXML private Button    addButton;
  @FXML private TextField carVIN, carMake, carModel, carColor, carYear, carMileage;
    
  ObservableList items;
    
  @FXML
  private void handleAddButton(ActionEvent e) {        
    new CarsCarTable().addCar(carVIN.getText(), 
                              carMake.getText(), 
                              carModel.getText(), 
                              carColor.getText(), 
                              Integer.parseInt(carYear.getText()), 
                              Integer.parseInt(carMileage.getText()));
    fillTable();
    carVIN.clear();
    carMake.clear();
    carModel.clear();
    carColor.clear();
    carYear.clear();
    carMileage.clear();
    tableView.requestFocus();
  }
    
  @FXML
  private void handleUpdateButton(ActionEvent e) {        
    new CarsCarTable().updateCar(carVIN.getText(), 
                                 carMake.getText(), 
                                 carModel.getText(), 
                                 carColor.getText(), 
                                 Integer.parseInt(carYear.getText()), 
                                 Integer.parseInt(carMileage.getText()));
    fillTable();
    carVIN.clear();
    carMake.clear();
    carModel.clear();
    carColor.clear();
    carYear.clear();
    carMileage.clear();
    tableView.requestFocus();
  }
    
  @FXML
  private void handleDeleteButton(ActionEvent e) {        
    new CarsCarTable().deleteCar(carVIN.getText());
    fillTable();
    carVIN.clear();
    carMake.clear();
    carModel.clear();
    carColor.clear();
    carYear.clear();
    carMileage.clear();
    tableView.requestFocus();
  }
    
  private void fillTable() {
    tableView.setItems(null);
    tableView.getColumns().clear();
    try {
      ResultSet rSet = new CarsCarTable().getResults();
      
      for(int index = 0; index < rSet.getMetaData().getColumnCount(); index++) {
        
        TableColumn tableColumn = new TableColumn(rSet.getMetaData().getColumnName(index + 1));        
        final int j = index;
        tableColumn.setCellValueFactory(new Callback <CellDataFeatures <ObservableList,String>, ObservableValue<String>>() {                    
          public ObservableValue<String> call(CellDataFeatures<ObservableList,  String> param) {                                                                                              
            return new SimpleStringProperty(param.getValue().get(j).toString());                        
          }                    
        });
                
        tableView.getColumns().addAll(tableColumn);
      }
            
      items = FXCollections.observableArrayList();
      while(rSet.next()) {
        ObservableList<String> row = FXCollections.observableArrayList();
        for(int i = 0; i < rSet.getMetaData().getColumnCount(); i++) {
          row.add(rSet.getString(i + 1));
        }
        items.add(row);                                
      }   
      tableView.setItems(items);
    }
    catch (SQLException sqlException) {
      sqlException.printStackTrace();
      System.exit(1);
    }
  }    
    
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    fillTable();  
    tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
      @Override
      public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        if(tableView.getSelectionModel().getSelectedItem() != null) {  
          
          TableViewSelectionModel selectionModel = tableView.getSelectionModel();
          ObservableList selectedCells  = selectionModel.getSelectedCells();
          TablePosition  tablePosition  = (TablePosition) selectedCells.get(0); 
          
          int val = tablePosition.getRow();
          Object row = items.get(val);
          String[] a = row.toString().split("[,]|[\\]]");
          
          carVIN.setText(a[0].substring(1).trim());
          carMake.setText(a[1].trim());
          carModel.setText(a[2].trim());
          carColor.setText(a[3].trim());
          carYear.setText(a[4].trim());
          carMileage.setText(a[5].trim());
        }
      }
    });
  }
}
