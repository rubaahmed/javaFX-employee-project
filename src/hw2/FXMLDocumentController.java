package hw2;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import com.jfoenix.controls.JFXSnackbar;
public class FXMLDocumentController implements Initializable {

    List<data> arrayOfdata;
    @FXML
    private TextField name;
    @FXML
    private JFXSnackbar Msg;
    @FXML
    private TextField email;

    @FXML
    private TextField mobileNm;
     
    @FXML
    private ComboBox<String> number;
    @FXML
    private ComboBox<String> jobT;
    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;
    @FXML
    private ToggleGroup gender;
    @FXML
    private TableView<data> dataTableView;
    private Alert alert;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        jobT.getItems().addAll("Teacher", "Doctor", "Engineer", "Manager");
        number.getItems().addAll("059", "056");
    }

    @FXML
    void addAction(ActionEvent event) {
        try {
             String job = jobT.getSelectionModel().getSelectedItem();
        String Gender = "";
        String text = mobileNm.getText();
        String g = number.getSelectionModel().getSelectedItem() + text;

       
        if (male.isSelected()) {
            Gender = "male";
        } else if (female.isSelected()) {
            Gender = "female";
        } else {
            showAlert("Error",
                    "all feild are required , you must select it all",
                    "", Alert.AlertType.ERROR);
        }
        if (name.getText().isEmpty()
                || email.getText().isEmpty()
                || mobileNm.getText().isEmpty()
                || job == null) {
            showAlert("Error",
                    "all feild are required , you must select it all",
                    "", Alert.AlertType.ERROR);
        } else {
            
            
               data r = new data(name.getText(),
                        email.getText(),
                        Integer.parseInt(g),
                        jobT.getSelectionModel().getSelectedItem(),
                        Gender, number.getSelectionModel().getSelectedItem());
                dataTableView.getItems().add(r);
               Msg.show("Employee added successfuly ^^", 2500); 
            
                
            
        }
        } catch (Exception e) {
            System.out.println(e);
        }
       

    }

    @FXML
    void clearAction(ActionEvent event) {
        name.setText("");
        email.setText("");
        mobileNm.setText("");
        jobT.getSelectionModel().clearSelection();
        male.setSelected(false);
        female.setSelected(false);
    }

    @FXML
    void deleteAction(ActionEvent event) {
        try {
            if (dataTableView.getItems().isEmpty()) {
            showAlert("NO Data to delete ",
                    "The Table View is empty ",
                     "", Alert.AlertType.ERROR);
        } else {
            showAlert("Delete Student",
                    "Do you want to delete the student record ",
                    "Prees the ok button to delete", Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> delete = alert.showAndWait();
            if (delete.get() == ButtonType.OK) {
                data dd = dataTableView.getSelectionModel().getSelectedItem();
                dataTableView.getItems().remove(dd);
            } else {
                alert.close();
            }
        }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    @FXML
    void deleteAllRowsAction(ActionEvent event) {
        try {
            if (dataTableView.getItems().isEmpty()) {
            showAlert("NO Data to delete ", "The Table View is empty ", "", Alert.AlertType.ERROR);
        } else {
            showAlert("Delete Student",
                    "Do you want to delete the student record ",
                    "Prees the ok button to delete", Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> delete = alert.showAndWait();
            if (delete.get() == ButtonType.OK) {
                dataTableView.getItems().clear();
            } else {
                alert.close();
            }
        }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    @FXML
    void saveToExcelAction(ActionEvent event) {
        try {
             data dd;
        arrayOfdata = new ArrayList<>();
        if (dataTableView.getItems().isEmpty()) {
            showAlert("NO Data ", "NO Data ", "NO Data ", Alert.AlertType.ERROR);
        } else {
            for (int i = 0; i < dataTableView.getItems().size(); i++) {
                dd = dataTableView.getItems().get(i);
                arrayOfdata.add(i, dd);
                System.out.println(arrayOfdata.get(i).getName());
            }

           
        TextInputDialog textinput = new TextInputDialog();
        textinput.setTitle("Save Data");
        textinput.setHeaderText("Enter where you want to save the Excel File ");
        textinput.getDialogPane().setContentText("You must also specify the file extension ");
        Optional<String> result = textinput.showAndWait();
        TextField input=textinput.getEditor();
                WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 15, WritableFont.BOLD);
                WritableCellFormat wcf = new WritableCellFormat(titleFont);
                WritableWorkbook ww = Workbook.createWorkbook(new File(input.getText()));
                
                WritableSheet wss = ww.createSheet("Data", 0);
                wss.addCell(new jxl.write.Label(0, 0, "Name", wcf));
                wss.addCell(new jxl.write.Label(1, 0, "Email", wcf));
                wss.addCell(new jxl.write.Label(2, 0, "Mobile Number", wcf));
                wss.addCell(new jxl.write.Label(3, 0, "Job", wcf));
                wss.addCell(new jxl.write.Label(4, 0, "Gender", wcf));

                for (int i = 0; i < arrayOfdata.size(); i++) {
                    wss.addCell(new Label(0, i + 1, arrayOfdata.get(i).getName()));
                    wss.addCell(new Label(1, i + 1, arrayOfdata.get(i).getEmail()));
                    wss.addCell(new Label(2, i + 1, String.valueOf(arrayOfdata.get(i).getMobileNumber())));
                    wss.addCell(new Label(3, i + 1, arrayOfdata.get(i).getJobTitle()));
                    wss.addCell(new Label(4, i + 1, arrayOfdata.get(i).getGender()));
                }

                ww.write();
                ww.close();
                showAlert("Data Saved", "Data Saved to :"+input.getText(), "Data Saved", Alert.AlertType.INFORMATION);
            
        }
        } catch (Exception e) {
            System.out.println(e);
        }
       
    }

    void showAlert(String alertTitle, String alertHeader, String alertContext, Alert.AlertType type) {
        try {
            alert = new Alert(type);
        alert.setTitle(alertTitle);
        alert.setHeaderText(alertHeader);
        alert.setContentText(alertContext);
        if (type == Alert.AlertType.ERROR || type == Alert.AlertType.INFORMATION) {
            alert.show();
        }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }


}
