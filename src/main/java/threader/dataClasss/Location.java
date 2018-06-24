package threader.dataClasss;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Location {
    public String name;
    public String city;
    public String type;

    public static Location[] ParseLocations(String json) throws JsonProcessingException{
        Location[] locations = null;
        try{
            ObjectMapper mapper = new ObjectMapper();
            locations = mapper.readValue(json, Location[].class);
        }catch (JsonProcessingException e){
            System.out.println("json read problems " + json + "   " + Thread.currentThread().getName());
            System.out.println(e.getMessage());
            System.out.println(e.getLocation());
            throw e;
        }catch(IOException e){
            System.out.println(e.getStackTrace());
        }
        return locations;
    }
    public static Location ParseLocation(String json) throws JsonProcessingException{
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
        }catch (JsonProcessingException e){
            System.out.println("json read problems " + json + "   " + Thread.currentThread().getName());
            System.out.println(e.getMessage());
            System.out.println(e.getLocation());
            throw e;
        }catch (IOException e){
            System.out.println(e.getStackTrace());
        }
        return location;
    };
    public Location(JsonNode json){
        name = json.get("name").textValue();
        city = json.get("name").textValue();;
        type = json.get("name").textValue();;
    }
    public Location(String namein, String cityin, String typin){
        name = namein;
        city = cityin;
        type = typin;
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