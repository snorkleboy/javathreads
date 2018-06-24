package threader.dataClasss;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Data{
    public String city;
    public HashMap<String, TypeAmount> typeAmounts;
    Data(String c, HashMap<String, TypeAmount> typeams){
        city = c;
        typeAmounts =typeams;
    }
    public String toString(){
        try{


        StringBuilder builder = new StringBuilder();
        String string = "CITY: "+city + "-TYPEAMOUNTS:";
        builder.append(string);
        Set keys = typeAmounts.keySet();
        Iterator iterator = keys.iterator();
        int i =0;
        while (iterator.hasNext()){
            String key = (String)iterator.next();
            TypeAmount typeAmount = typeAmounts.get(key);
            builder.append("\n type: " + typeAmount.type + "  -amount: "+typeAmount.amount);
        }
        return builder.toString();
        }catch( NullPointerException e){
            System.out.println("NULL POINTER ON GETTING DATA->STRING");
            System.out.println(e);
        }
        return "ERROR GETTING THIS DATA";

    }
}
class TypeAmount{
    public String type;
    public int amount;
    public TypeAmount(String t, int num){
        type = t;
        amount = num;
    }
}