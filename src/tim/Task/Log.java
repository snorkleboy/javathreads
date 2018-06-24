package tim.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Log {
    static Hashtable<String, ArrayList<String>> threadToLogsMap = new Hashtable<String, ArrayList<String>>();
    public static void log(String threadName, String message){
        if (!threadToLogsMap.contains(threadName)){
            threadToLogsMap.put(threadName,new ArrayList<String>());
        }
        threadToLogsMap.get(threadName).add(message);
    }
    public static void print(){
        ArrayList<String> keys = new ArrayList<String>(threadToLogsMap.keySet());
        ArrayList<String[]> messageList = new ArrayList<String[]>();
        int longestLength = 0;

        for (int j = 0; j<keys.size(); j++){
            String threadName = keys.get(j);
            ArrayList<String> messages = threadToLogsMap.get(threadName);
            int i =0;
            while(!messages.isEmpty()){
                if (messageList.size()< i+1){
                    messageList.add(i,new String[keys.size()]);
                }
                String[] line = messageList.get(i);
                i++;
                line[j] = messages.remove(0);
            }
        }
        System.out.format(keys.toString());
        for(String[] line : messageList){
            System.out.format(Arrays.toString(line));
        }

    }



}
