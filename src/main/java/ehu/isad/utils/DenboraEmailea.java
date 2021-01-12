package ehu.isad.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

public class DenboraEmailea {

    private static DenboraEmailea instantzia;

    private DenboraEmailea(){}

    public static DenboraEmailea getInstantzia(){

        if (DenboraEmailea.instantzia == null){
            DenboraEmailea.instantzia = new DenboraEmailea();
        }

        return DenboraEmailea.instantzia;
    }

    public Timestamp unekoDataOrduaEman() {

        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        return timestamp;
    }


    public String lortuDataLongetikDataBakarrik(Long pMilisegunduak){

        Date        data        = new Date(pMilisegunduak);
        LocalDate   dataOna     = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        String dataString = dataOna.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));

        return dataString;
    }

    public String lortuOraingoData(){

        LocalDate dataOrain = LocalDate.now();
        String dataString = dataOrain.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));

        return dataString;
    }

    public long lortuOraingoDataMilisegundutan() {

        Date date = new Date(); //Une honetako data eta ordua
        long timeMilli = date.getTime();

        return timeMilli;
    }
}
