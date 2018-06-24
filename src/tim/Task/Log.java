package tim.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Log {
    static private ConcurrentHashMap<String, ArrayList<String>> threadToLogsMap = new ConcurrentHashMap<String, ArrayList<String>>();
    public static void log(String threadName, String message){
            if (!threadToLogsMap.containsKey(threadName)){
                threadToLogsMap.put(threadName, new ArrayList<String>());
            }
            ArrayList<String> messages = new ArrayList<String>(threadToLogsMap.get(threadName));


            messages.add(message);
//        System.out.println("<ANTEST1" + "  " + messages.toString());

        threadToLogsMap.put(threadName,messages);
    }
    public static void print(){
        ArrayList<String> keys = new ArrayList<String>(threadToLogsMap.keySet());
        ArrayList<String[]> lines = new ArrayList<String[]>();
        int longestLength = 0;

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
        System.out.println();

        for (String s : keys) {
            System.out.print(String.format("%50s ", s));
        }
        System.out.println();
        for(String[] line : lines){
            for (String s : line) {
                System.out.print(String.format("%50s ", s));
            }
            System.out.println();
        }


    }



}
