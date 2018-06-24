package threader.dataClasss;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Location {
    public String name;
    public String city;
    public String type;
    public Location(){}
    public void setName(String namein){
        name = namein;
    }
    public void setCity(String cityin){
        city = cityin;
    }

    public void setType(String typein){
        type = typein;
    }

    public static Location[] ParseLocations(String json) throws JsonMappingException{
        Location[] locations = null;
        try{
            ObjectMapper mapper = new ObjectMapper();
            locations = mapper.readValue(json, Location[].class);
        }
        catch(JsonMappingException e){
            System.out.println("json read problems " + json + "   " + Thread.currentThread().getName());
            throw e;
        }catch(IOException e){
            e.printStackTrace();
        }
        return locations;
    }
    public static Location ParseLocation(String json) throws JsonMappingException{
        Location location = null;
        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(json);
            if (actualObj == null){
                throw new JsonMappingException("couldnt get object out " + json);
            }

            location = new Location(actualObj);
            if (location.name == null || location.city == null || location.type == null){
                throw new JsonMappingException(
                        "city, name, or type is null:" + location.city +" " + location.name + " " +"  "+ location.type + ".. Original json:"+json
                );
            }
        }catch (JsonMappingException e){
            System.out.println("json read problems " + json + "   " + Thread.currentThread().getName());
            System.out.println(e.getMessage());
            System.out.println(e.getLocation());
            throw e;
        }catch (IOException e){
            e.printStackTrace();
        }
        return location;
    };
    public Location(JsonNode json){
        name = json.get("name").textValue();
        city = json.get("name").textValue();;
        type = json.get("name").textValue();;
    }
    public Location(String namein, String cityin, String typein){
        name = namein;
        city = cityin;
        type = typein;
    }
    public static Data[] extractData(Location[] locations){
        HashMap<String,Data> dataCollection = new HashMap<>();
        for (Location location : locations){
            dataCollection.putIfAbsent(location.city,new Data(location.city,new HashMap<String, TypeAmount>()));
            Data cityData = dataCollection.get(location.city);
            cityData.typeAmounts.putIfAbsent(location.type,new TypeAmount(location.type,0));
            TypeAmount cityTypeAmount = cityData.typeAmounts.get(location.type);
            cityTypeAmount.amount = cityTypeAmount.amount + 1;
        }
        Set keys = dataCollection.keySet();
        Iterator iterator = keys.iterator();
        Data[] returnData = new Data[keys.size()];
        int i =0;
        while (iterator.hasNext()){
            String key = (String)iterator.next();
            returnData[i] = dataCollection.get(key);
            i++;
        }
        return returnData;
    }

    public String toString(){
        return toJsonString();
    }
    public String toJsonString(){
        return "{name:"+name+",city:"+city +",type:"+type+"}";
    }
    public String toCSV(){
        return "null";
    }
}
