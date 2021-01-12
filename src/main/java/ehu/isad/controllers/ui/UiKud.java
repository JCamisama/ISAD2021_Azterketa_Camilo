package ehu.isad.controllers.ui;


import ehu.isad.controllers.db.WebKudeatzaile;
import org.apache.commons.codec.binary.Hex;

import java.io.InputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import ehu.isad.Nagusia;
import ehu.isad.models.WebOrria;
import ehu.isad.utils.Sarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UiKud implements Initializable {

    //Atributuak
    private Nagusia mainApp;

    @FXML private TextField urlText;
    @FXML private Button checkBtn;

    @FXML private TableView<WebOrria> taula;
    @FXML private TableColumn<WebOrria, String> urlZut;
    @FXML private TableColumn<WebOrria, String> md5Zut;
    @FXML private TableColumn<WebOrria, String> versionZut;

    @FXML private Text testuAdierazlea;

    private ObservableList<WebOrria> webZerrenda = FXCollections.observableArrayList();


    @FXML
    void onClickBtn(ActionEvent event) throws IOException, SQLException {

        String urlHau = this.urlText.getText();
        String md5Sum = this.getMd5Hash(urlHau+"/README");

        this.bilaketaKudeatu(md5Sum, urlHau);

        //this.testuAdierazlea.setText(md5Sum);
    }

    private void bilaketaKudeatu(String pMd5Sum, String pUrl) throws SQLException {

        Boolean badago = WebKudeatzaile.getInstance().webDatubaseanDago(pMd5Sum);

        if(badago){
            this.testuAdierazlea.setText("Datubasean Zegoen.");
            WebOrria bilatutakoa = WebKudeatzaile.getInstance().webDatubasetikAtera(pMd5Sum, pUrl);
            this.webZerrenda.add(bilatutakoa);

        }
        else{
            this.testuAdierazlea.setText("Ez da datubasean aurkirtu.");
            WebOrria webBerria = new WebOrria();
            webBerria.setUrl(pUrl);
            webBerria.setMd5String(pMd5Sum);
            this.webZerrenda.add(webBerria);
        }
        this.modeloarenDatuakTaulanTxertatu();
    }

    private String getMd5Hash(String pEdukia) {

        URL url = null;
        try {
            url = new URL(pEdukia);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream is = null;
        try {
            is = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String digest = null;
        try {
            digest = getDigest(is, md, 2048);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return digest;
    }

    public static String getDigest(InputStream is, MessageDigest md, int byteArraySize)
            throws NoSuchAlgorithmException, IOException {

        md.reset();
        byte[] bytes = new byte[byteArraySize];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
            md.update(bytes, 0, numBytes);
        }
        byte[] digest = md.digest();
        String result = new String(Hex.encodeHex(digest));
        return result;
    }


    //Metodoak
    public void setMainApp(Nagusia pMainApp) {
        this.mainApp = pMainApp;
    }

    public void hasieratu() {


        this.taula.setEditable(true);
        this.modeloarenDatuakTaulanTxertatu();

    }

    private void modeloarenDatuakTaulanTxertatu(){

        this.taula.setItems(this.webZerrenda);
        this.urlZut.setCellValueFactory(new PropertyValueFactory<>("Url"));
        this.md5Zut.setCellValueFactory(new PropertyValueFactory<>("Md5String"));
        this.versionZut.setCellValueFactory(new PropertyValueFactory<>("Bertsioa"));


        this.zutabeEditagarriakPrestatu();
    }

    private void zutabeEditagarriakPrestatu() {

        this.versionZut.setCellFactory(TextFieldTableCell.<WebOrria> forTableColumn());

        this.versionZut.setOnEditCommit((TableColumn.CellEditEvent<WebOrria, String> event) -> {

            TablePosition<WebOrria, String> pos = event.getTablePosition();
            int row = pos.getRow();
            WebOrria webHau = event.getTableView().getItems().get(row);
            String balioa = event.getNewValue();

            webHau.setBertsioa(balioa);

            WebKudeatzaile.getInstance().bertsioAldaketaKudeatu(webHau);

            this.testuAdierazlea.setText("md5 eta bertsio berria datubasean sartu dira");
        });
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
