package ehu.isad.controllers.db;


import ehu.isad.models.WebOrria;
import ehu.isad.utils.DenboraEmailea;
import ehu.isad.utils.MetodoRandomBatzuk;
import ehu.isad.utils.Sarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class WebKudeatzaile {

    private static final WebKudeatzaile instance = new WebKudeatzaile();

    public static WebKudeatzaile getInstance() {
        return instance;
    }

    private WebKudeatzaile() {
    }




    public boolean webDatubaseanDago(String pMd5Checksum) throws SQLException {

        boolean dago = false;

        String bilaketaquery = this.bilaketaQueryPrestatu(pMd5Checksum);
        DBKudeatzaileSQLite dbKudeatzaile = DBKudeatzaileSQLite.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(bilaketaquery);

        return rs.next();
    }

    private String bilaketaQueryPrestatu(String pMd5Checksum) {

        String query = "SELECT * " +
                "FROM checksums "+
                "WHERE md5='"+pMd5Checksum+"';";

        return query;
    }
/*
    private Captcha ezaugarriakLortu(ResultSet pEmaitzak) throws SQLException {

        Captcha captchaHau = null;

        String      filename    = pEmaitzak.getString("filename");
        String      value       = pEmaitzak.getString("value");
        int         id          = pEmaitzak.getInt("id");
        Long        dataLong    = pEmaitzak.getLong("date");
        Date        data        = new Date(dataLong);
        String      dataOna     = DenboraEmailea.getInstantzia().lortuDataLongetikDataBakarrik(dataLong);
        captchaHau = new Captcha(id, filename, dataOna, value);

        return captchaHau;
    }


    private ResultSet catchapakEskatuDatuBaseari(){

        String query = "select id, value, filename, " +
                "date from captchas;";
        DBKudeatzaileSQLite dbKudeatzaile = DBKudeatzaileSQLite.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        return rs;
    }

    public Captcha captchaBerriaLortu(int pAurrekoarenId) {

        Captcha captchaBerria = null;

        int id = pAurrekoarenId + 1;

        //Irudia lortzen
        String filename = MetodoRandomBatzuk.getInstance().
                izenRandomBatSortuAurrizkiBatekin("captcha");
        Sarea.irudiaLortu("http://45.32.169.98/captcha.php", filename);

        String data = DenboraEmailea.getInstantzia().lortuOraingoData();
        String value = "";

        captchaBerria = new Captcha(id, filename+".png", data, value);

        this.captachaDatuBaseanSartu(captchaBerria);

        return captchaBerria;
    }

    private void captachaDatuBaseanSartu(Captcha pCaptchaBerria) {

        String insertQuery   = this.SartzekoQueryPrestatu(pCaptchaBerria);
        DBKudeatzaileSQLite.getInstantzia().execSQL(insertQuery);

    }


    private String SartzekoQueryPrestatu(Captcha pCaptchaBerria) {

        String      filename    = pCaptchaBerria.getFilename();
        String      value       = pCaptchaBerria.getValue();
        Long        dataLong        = DenboraEmailea.getInstantzia().lortuOraingoDataMilisegundutan();


        String query = "INSERT INTO captchas(`filename`, `value`, `date`) "+
                       "VALUES('"+filename+"','"+value+"',"
                                 +dataLong+");";

         return query;
    }


    public void captchenBalioakEguneratu(ObservableList<Captcha> captchaZerrenda) {

        for(Captcha cHau: captchaZerrenda){

            String updateQuery = this.eguneratzeQueryLortu(cHau);
            DBKudeatzaileSQLite.getInstantzia().execSQL(updateQuery);
        }
    }

    private String eguneratzeQueryLortu(Captcha pCaptcha) {

        String emaitza = "UPDATE captchas "+
                            "SET value='"+pCaptcha.getValue()+"' "+
                            "WHERE filename='"+pCaptcha.getFilename()+"';";

        return emaitza;
    } return query;
    }


    public void captchenBalioakEguneratu(ObservableList<Captcha> captchaZerrenda) {

        for(Captcha cHau: captchaZerrenda){

            String updateQuery = this.eguneratzeQueryLortu(cHau);
            DBKudeatzaileSQLite.getInstantzia().execSQL(updateQuery);
        }
    }

    private String eguneratzeQueryLortu(Captcha pCaptcha) {

        String emaitza = "UPDATE captchas "+
                            "SET value='"+pCaptcha.getValue()+"' "+
                            "WHERE filename='"+pCaptcha.getFilename()+"';";

        return emaitza;
    }
    */

}
