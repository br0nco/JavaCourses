package com;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class App 
{   
    
    public static double round(double d, int places){
        DecimalFormat df = new DecimalFormat("#.##"); 
        return Double.valueOf(df.format(d));
    }
    
    public static List generateCats(int catsCount){
        List<Cat> catsList = new ArrayList<>(catsCount);
        Random random = new Random();
        int cntF = 0, cntH = 0, cntY = 0, cntO = 0;        
        
        for(int i = 0; i < catsCount; i++){
            int rand = random.nextInt(4);
            double weight = round(random.nextDouble() + random.nextInt(4)+2,2);
            switch(rand){
                case 0: 
                    catsList.add(new FatCat(FatCat.class.getSimpleName() + cntF++, weight));
                    break;
                case 1: 
                    catsList.add(new HomelessCat(HomelessCat.class.getSimpleName() + cntH++, weight));
                    break;
                case 2:
                    catsList.add(new YammyCat(YammyCat.class.getSimpleName() + cntY++, weight));
                    break;
                case 3:
                    catsList.add(new OrdinaryCat(OrdinaryCat.class.getSimpleName() + cntO++, weight));
                    break;
                default: System.out.println("unknown cat format");
            }
        }
        return catsList;
    }
    
    public static void main( String[] args )
    {
        List<Cat> cats = generateCats(8);
        CatFarschFactory factory = new CatFarschFactory();
       
        try {
            factory.makeFarsch(cats);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        System.out.println("Total Farsch:" + factory.getFarsch() + " kilos");
    }
}
