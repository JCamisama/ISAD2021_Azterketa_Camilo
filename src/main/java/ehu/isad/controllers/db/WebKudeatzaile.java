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

    public WebOrria webDatubasetikAtera(String pMd5Sum, String pUrl) throws SQLException {

        WebOrria webHau = new WebOrria();
        String bilaketaquery = this.bilaketaQueryPrestatu(pMd5Sum);
        DBKudeatzaileSQLite dbKudeatzaile = DBKudeatzaileSQLite.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(bilaketaquery);

        if(rs.next()){
            String md5 = rs.getString("md5");
            String bertsio = rs.getString("version");

            webHau.setMd5String(md5);
            webHau.setBertsioa(bertsio);
            webHau.setUrl(pUrl);
        }

        return webHau;
    }

    public void bertsioAldaketaKudeatu(WebOrria pWebHau) {

        String query = this.insertEskaeraPrestatu(pWebHau);
        DBKudeatzaileSQLite dbKudeatzaile = DBKudeatzaileSQLite.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
    }


    private String insertEskaeraPrestatu(WebOrria pWebHau){

        //liburu taulako zutabeak: isbn, izenburu, argitaletxe, orriKop, irudia

        String  md5       = pWebHau.getMd5String();
        String  version   = pWebHau.getBertsioa();
        int     idCMS     = 1;
        String  readme    = "README";

        String query = "INSERT INTO checksums(idCMS, version, md5, path) " +
                "VALUES("+idCMS+",'"+version+"','"+md5+"','"+readme+"');";

        return query;
    }



}
