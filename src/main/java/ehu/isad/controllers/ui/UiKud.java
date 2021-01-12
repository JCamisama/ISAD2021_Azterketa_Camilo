package ehu.isad.controllers.ui;

import ehu.isad.Nagusia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class UiKud implements Initializable {

    //Atributuak
    private Nagusia mainApp;

    @FXML private TextField urlText;
    @FXML private Button checkBtn;

    @FXML private TableView<?> taula;
    @FXML private TableColumn<?, ?> urlZut;
    @FXML private TableColumn<?, ?> md5Zut;
    @FXML private TableColumn<?, ?> versionZut;

    @FXML private Text testuAdierazlea;

    @FXML
    void onClickBtn(ActionEvent event) {

    }



    //Metodoak
    public void setMainApp(Nagusia pMainApp) {
        this.mainApp = pMainApp;
    }

    public void hasieratu() {

        //TODO
        /*
        this.pertsonakKargatu();
        this.taulaEzkerra.setEditable(true);
        this.taulaEskuina.setEditable(true);
        this.modeloarenDatuakTaulanTxertatu();
        this.taulaEzkerra.getSelectionModel().selectLast();

         */
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
