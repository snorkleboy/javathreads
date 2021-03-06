package threader.task.tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import threader.dataClasss.Location;
import threader.task.Task;
import threader.util.FileHelper;

import java.io.File;
import java.io.IOException;

public class MakeJsonTask extends Task {
    String path;
    int tomake;
    public MakeJsonTask(String fileName, int num){
        tomake = num;
        path = fileName;

        queue.add(this);
    }
    public static void MakeTestFiles(){
        new MakeJsonTask("./created/locations1a.json",1);
        new MakeJsonTask("./created/locations2a.json",10);
        new MakeJsonTask("./created/locations3a.json",100);
        new MakeJsonTask("./created/locations4a.json",1000);
        new MakeJsonTask("./created/locations5a.json",10000);

        new MakeJsonTask("./created/locations1b.json",1);
        new MakeJsonTask("./created/locations2b.json",10);
        new MakeJsonTask("./created/locations3b.json",100);
        new MakeJsonTask("./created/locations4b.json",1000);
        new MakeJsonTask("./created/locations5b.json",10000);

        new MakeJsonTask("./created/locations1c.json",1);
        new MakeJsonTask("./created/locations2c.json",10);
        new MakeJsonTask("./created/locations3c.json",100);
        new MakeJsonTask("./created/locations4c.json",1000);
        new MakeJsonTask("./created/locations5c.json",10000);
    }
    public void run(){
        Location[] locations = makeLocations(tomake);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try{
            File file = FileHelper.getFile(path);
            writer.writeValue(file,locations);
            System.out.print(file.toString());
        }catch (JsonProcessingException e){
            System.out.println("CANT SERIALIZE MY OWN OBJECTS");
            System.out.println(e);
        }catch (IOException e){
            System.out.println(e);
        }
        log.log(getThreadName(),"done making JSON");
    }

    public static Location[] makeLocations(int tomake){
        Location[] locations = new Location[tomake];
        String[] names = makeNames();
        String[] cities = makeCities();
        String[] types = {"Store", "store", "Holy Ghosty", "unHoly Ghost", "other Ghost"};
        for(int i =0; i<tomake;i++){
            String name = getRandom(names);
            String city = getRandom(cities);
            String type = getRandom(types);
            locations[i] = new Location(name,city,type);
        }
        return locations;
    }
    public static String[] makeNames(){
       return "obeisant,invite,battle,eye,illustrious,fool,vagabond,sore,fuzzy,hanging,gold,axiomatic,breathe,upbeat,balance,consider,craven,cultured,sharp,bucket,pleasant,attraction,hammer,cruel,carry,walk,hungry,smell,cheese,pigs,pan,abject,sloppy,stage,uppity,tease,quilt,discover,receive,shiny,bury,tiger,long-term,snails,develop,material,nine,kettle,rampant,apparatus,handsome,answer,treat,tax,fill,raspy,quizzical,intend,political,shaky,shiver,improve,smoke,delirious,dramatic,profit,blush,yielding,unique,late,station,need,tasty,hospitable,toy,shape,smash,hand,pedal,plastic,car,disagreeable,coal,range,stream,quarter,nest,lush,glue,hateful,erratic,position,godly,wakeful,part,friends,fit,literate,breakable,colorful"
            .split(",");
    }
    public static String[] makeCities(){
        return "Braedon,Blencogo,Romsey,Kirkwall,Wavemeet ,Scrabster,Woodhaerst,Cappadocia,Halivaara,Carningsby,Hornsey,Travercraig,Jongvale ,Ascot,Fool's March ,Emall,Garthram,Goulcrest ,Blencathra,Kilmarnock,Ballingsmallard,Pantmawr,Farnworth,Falkirk,Colchester,Leeside ,Balerno,Findochty,Braedon,Wimborne,Polperro,Caerdydd,Penzance,Threlkeld,Kincardine,Ferncombe,Grimsby,Deathfall ,Bamborourgh,Rochdale"
            .split(",");
    }
    public static String getRandom(String[] arr){
        return arr[(int)Math.floor(Math.random()*arr.length)];
    }
}


