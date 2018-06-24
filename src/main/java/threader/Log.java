package threader;

import threader.dataClasss.Data;
import threader.task.TaskQueue;
import threader.util.FileHelper;

import java.util.*;

public class Log {
    static private Hashtable<String, ArrayList<String>> threadToLogsMap = new Hashtable<String, ArrayList<String>>();
    static private Hashtable<String, Data[]> threadToResults = new Hashtable<String, Data[]>();
    static public int numFilesDone = 0;
    public static void log(String threadName, String message){
        if (!threadToLogsMap.containsKey(threadName)){
            threadToLogsMap.put(threadName, new ArrayList<String>());
        }
        ArrayList<String> messages = threadToLogsMap.get(threadName);
        messages.add(message);
        threadToLogsMap.put(threadName,messages);
    }
    public static void print(){
        ArrayList<String[]> lines = getLines();
        ArrayList<String> keys = new ArrayList<String>(threadToLogsMap.keySet());
        System.out.println("------------------------");
        System.out.println("------------------------");
        System.out.println(String.format("%20s","threadNames"));
        for (String s : keys) {
            System.out.print(String.format("%50s", s));
        }
        System.out.println();
        System.out.println(String.format("%20s ","Tasks"));
        System.out.println();
        for(String[] line : lines){
            for (String s : line) {
                System.out.print(String.format("%50s ", s));
            }
            System.out.println();
        }
        System.out.println();

        TaskQueue.print();
        System.out.println("brought to you by " + Thread.currentThread().getName());
        System.out.println("------------------------");
        System.out.println("------------------------");
    }
    public static void logResults(String file, Data[] data){
        if (threadToResults.putIfAbsent(file,data) == null){
            numFilesDone++;

             if(numFilesDone %5 == 0){
                 printResutls();
             }
        }else{
            System.out.println("FILE RESULTS BEING LOGGED TWICE!!!, SHOULD NOT HAPPENING YET");
        }

    }
    public static void printResutls(){
        Hashtable<String, Data[]> theseResults = threadToResults;
        threadToResults = new Hashtable<String, Data[]>();
        Set keys = theseResults.keySet();
        Iterator iterator = keys.iterator();
        StringBuffer builder = new StringBuffer();

        while(iterator.hasNext()){
            String filename = (String) iterator.next();
            Data[] dataArr = theseResults.get(filename);
            builder.append(filename + "\n");
            for (Data data : dataArr ){
                builder.append(data.toString());
            }
            builder.append("\n\n\n\n");
        }
        FileHelper.WriteFile("./results/results.all"+numFilesDone,builder.toString());

    }

    private static ArrayList<String[]> getLines(){
        ArrayList<String> keys = new ArrayList<String>(threadToLogsMap.keySet());
        ArrayList<String[]> lines = new ArrayList<String[]>();
        lines.add(keys.toArray(new String[keys.size()]));
        for (int j = 0; j<keys.size(); j++){
            String threadName = keys.get(j);
            ArrayList<String> messages = new ArrayList<String>(threadToLogsMap.get(threadName));
            threadToLogsMap.put(threadName,new ArrayList<String>());
            int i =0;
            while(!messages.isEmpty()){
                String message = messages.remove(0);
                if (lines.size()< i+1){
                    lines.add(new String[keys.size()]);
                }
                String[] line = lines.get(i);
                i++;
                line[j] = message;
            }
        }
        return lines;
    }

}
