package ehu.isad.utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class IrudiKud {

    //Atributuak
    private String irudienDirektorioa;
    private String formatua           = "png";


    //Singleton patroia
    private static IrudiKud instantzia = new IrudiKud();
    private IrudiKud(){

        Properties ezarpenak =  Utils.lortuEzarpenak();
        this.irudienDirektorioa = ezarpenak.getProperty("irudienDirektorioa");
    }
    public static IrudiKud getInstantzia() {
        return IrudiKud.instantzia;
    }


    //Gainontzeko metodoak
    public String irudiaGorde(Image pIrudia, String pIrudiIzena){

        //Irudia sortuko du eta horren formatua itzuli egingo du.
        String kokapenOsoa = this.irudienDirektorioa+pIrudiIzena+"."+this.formatua;

        File irteerakofitx = new File(kokapenOsoa);
        BufferedImage bImage = SwingFXUtils.fromFXImage(pIrudia, null);
        try {
            ImageIO.write(bImage, formatua, irteerakofitx);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this.formatua;
    }


    public String irudiaGordeFormatuZehaztu(Image pIrudia, String pIrudiIzenaFormaturekin){

        //Irudia sortuko du eta horren formatua itzuli egingo du.
        String kokapenOsoa = this.irudienDirektorioa+pIrudiIzenaFormaturekin;

        File irteerakofitx = new File(kokapenOsoa);
        BufferedImage bImage = SwingFXUtils.fromFXImage(pIrudia, null);
        try {
            ImageIO.write(bImage, formatua, irteerakofitx);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this.formatua;
    }


    public Image irudiaKargatu(String pIrudiaIzena) {

        BufferedImage   irudiaBuff  = null;
        Image           irudia      = null;
        try{
            File irudiFitx = new File(this.irudienDirektorioa+pIrudiaIzena);
            irudiaBuff = ImageIO.read(irudiFitx);
            irudia = SwingFXUtils.toFXImage(irudiaBuff, null);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return irudia;
    }
}
