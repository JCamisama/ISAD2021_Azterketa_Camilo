package ehu.isad.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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





}
