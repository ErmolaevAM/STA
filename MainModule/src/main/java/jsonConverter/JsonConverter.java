package jsonConverter;

import com.google.gson.Gson;
import entities.Apps;
import entities.StackTrace;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 06.12.2016.
 */
public class JsonConverter {
        Gson GSON = new Gson();

    public void appToJson(List<Apps> list){
        for (Apps item : list) {
            String json = GSON.toJson(item);
            try (FileWriter writer = new FileWriter("D:\\Моя папка\\IdeaProjects\\STA(4.12.16)\\MainModule\\src\\main\\resources\\appJsonFile.json", true)) {
                GSON.toJson(item, writer);
                writer.write('\n');
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    public List<Apps> appFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("D:\\Моя папка\\IdeaProjects\\STA(4.12.16)\\MainModule\\src\\main\\resources\\appJsonFile.json"));
        String line;
        List<Apps> list = new ArrayList<>();

        while (reader.readLine()!=null){
            line = reader.readLine();
            Apps apps = GSON.fromJson(line, Apps.class);
            list.add(apps);
        }
        return list;
    }

    public void stackTraceToJson(List<StackTrace> list){
        for (StackTrace item : list) {
            String json = GSON.toJson(item);
            try (FileWriter writer = new FileWriter("D:\\Моя папка\\IdeaProjects\\STA(4.12.16)\\MainModule\\src\\main\\resources\\stackTraceJsonFile.json", true)){
                GSON.toJson(item, writer);
                writer.write('\n');
            } catch (IOException ex){
                ex.getMessage();
            }
        }
    }

    public List<StackTrace> stackTraceFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("D:\\Моя папка\\IdeaProjects\\STA(4.12.16)\\MainModule\\src\\main\\resources\\stackTraceJsonFile.json"));
        String line;
        List<StackTrace> list = new ArrayList<>();

        while (reader.readLine()!=null){
            line = reader.readLine();
            StackTrace stackTrace = GSON.fromJson(line, StackTrace.class);
            list.add(stackTrace);
        }
        return list;
    }

    public String stringToJson(String string){
        return GSON.toJson(string, String.class);
    }

}
