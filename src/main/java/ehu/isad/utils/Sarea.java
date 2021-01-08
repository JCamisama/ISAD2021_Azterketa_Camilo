package ehu.isad.utils;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class Sarea {

    //Atributuak
    private static String url_lehenengo_zatia   = "https://api.gdax.com/products/";
    private static String url_bigarren_zatia    = "-eur/ticker";

    //Metodoak
   /*
    public static Float[] bilatuTxanpona(String pTxanpon){


        //URL-tik Json formatuko testua lortuko da orain
        String urlOsoa = url_lehenengo_zatia + pTxanpon + url_bigarren_zatia;
        String jsonFormatukoLerroa = urltikJsonFormatuanTextuaLortu(urlOsoa);

        Float[] emaitzakZenbatBolumen = detaileakEmanLiburukoInfotik(jsonFormatukoLerroa);

        return emaitzakZenbatBolumen;
    }
*/

    private static String urltikJsonFormatuanTextuaLortu(String pUrlOsoa) {

        String jsonTestua = "";

        try {
            URL openLib = new URL(pUrlOsoa);
            URLConnection yc = openLib.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            jsonTestua = in.readLine();
            in.close();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonTestua;
    }


    /*
    private static Float[] detaileakEmanLiburukoInfotik(String pJasonString) {

        Gson gson = new Gson();
        TxanponContainer kontainer = gson.fromJson(pJasonString, TxanponContainer.class);

        return new Float[]{kontainer.price, kontainer.volume};
    }
     */


}
