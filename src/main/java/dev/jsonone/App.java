package dev.jsonone;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class App {

    public static void main(String[] args) throws IOException {

        new App().run();
    }

    public void run() throws IOException {
        var json = readFile("data.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        Item item = mapper.readValue(json, Item.class);
        System.out.println("Some date: " + item.someDate);
    }

    public String readFile(String fileName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        System.out.println("File Found : " + file.exists());
        String content = new String(Files.readAllBytes(file.toPath()));
        System.out.println(content);
        return content;
    }
}
