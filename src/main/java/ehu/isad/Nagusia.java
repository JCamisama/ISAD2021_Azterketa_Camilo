package ehu.isad;

import ehu.isad.controllers.ui.UiKud;
import ehu.isad.controllers.ui.UiKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Nagusia extends Application {

    //Atributuak
    private Parent pertsonakUI;

    private Stage stage;
    private UiKud kudeatzaile;
    private Scene eszena;

    private String leihoa       = "/azterketa2.fxml";


    @Override
    public void start(Stage primaryStage) throws Exception {


        this.stage = primaryStage;
        FXMLLoader loaderPertsonak = new FXMLLoader(getClass().getResource(this.leihoa));
        this.pertsonakUI = (Parent) loaderPertsonak.load();
        this.kudeatzaile = loaderPertsonak.getController();
        this.kudeatzaile.setMainApp(this);
        this.kudeatzaile.hasieratu();

        this.stage.setTitle("Azterketa DATA");
        this.eszena = new Scene(this.pertsonakUI);

        this.eszenaErakutsi(this.eszena);

    }

    private void eszenaErakutsi(Scene pEszena){
        this.stage.setScene(pEszena);
        this.stage.show();
    }
}
