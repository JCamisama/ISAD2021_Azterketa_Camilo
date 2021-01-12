package ehu.isad.utils;

import java.util.Random;

public class MetodoRandomBatzuk {

    //Singleton patroia
    private static final MetodoRandomBatzuk instance = new MetodoRandomBatzuk();

    public static MetodoRandomBatzuk getInstance() {
        return instance;
    }

    private MetodoRandomBatzuk() {
    }


    //Metodoak
    public String izenRandomBatSortuAurrizkiBatekin(String pAurrizkia){

        Random rand = new Random();
        int upperbound = 214748364;
        int int_random = rand.nextInt(upperbound);


        return pAurrizkia + Integer.toString(int_random);
    }
}
