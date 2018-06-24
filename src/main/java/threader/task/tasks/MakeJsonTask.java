package threader.task.tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import threader.dataClasss.Location;
import threader.task.Task;

import java.io.File;
import java.io.IOException;

public class MakeJsonTask extends Task {
    public MakeJsonTask(){
        queue.add(this);
    }
    public void run(){

        int tomake = 1000;
        Location[] locations = new Location[tomake];
        for(int i =0; i<tomake;i++){
            locations[i] = new Location("name","city","type");
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        try{
            System.out.println(locations.length + "   LENGTH");

            File file = new File("./created/locations.json");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.createNewFile()){
                System.out.println("CREATED FILE  "+ file);
            }
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
    public static String[] makeNames(){
       return "obeisant,invite,battle,eye,illustrious,fool,vagabond,sore,fuzzy,hanging,gold,axiomatic,breathe,upbeat,balance,consider,craven,cultured,sharp,bucket,pleasant,attraction,hammer,cruel,carry,walk,hungry,smell,cheese,pigs,pan,abject,sloppy,stage,uppity,tease,quilt,discover,receive,shiny,bury,tiger,long-term,snails,develop,material,nine,kettle,rampant,apparatus,handsome,answer,treat,tax,fill,raspy,quizzical,intend,political,shaky,shiver,improve,smoke,delirious,dramatic,profit,blush,yielding,unique,late,station,need,tasty,hospitable,toy,shape,smash,hand,pedal,plastic,car,disagreeable,coal,range,stream,quarter,nest,lush,glue,hateful,erratic,position,godly,wakeful,part,friends,fit,literate,breakable,colorful"
            .split(",");
    }
    public static String[] getCities(){
        return "Braedon,Blencogo,Romsey,Kirkwall,Wavemeet ,Scrabster,Woodhaerst,Cappadocia,Halivaara,Carningsby,Hornsey,Travercraig,Jongvale ,Ascot,Fool's March ,Emall,Garthram,Goulcrest ,Blencathra,Kilmarnock,Ballingsmallard,Pantmawr,Farnworth,Falkirk,Colchester,Leeside ,Balerno,Findochty,Braedon,Wimborne,Polperro,Caerdydd,Penzance,Threlkeld,Kincardine,Ferncombe,Grimsby,Deathfall ,Bamborourgh,Rochdale"
            .split(",");
    }
}


