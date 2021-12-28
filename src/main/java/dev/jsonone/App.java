package dev.jsonone;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        LOG.info("Starting ...");
        new App().run();
    }

    public void run() {
        var json = readFile("data.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        try {
            Item item = mapper.readValue(json, Item.class);
            LOG.info("Title: {}", item.title);
            LOG.info("Some date: {}", item.someDate);
            LOG.info("Some other date: {}", item.someOtherDate);
        } catch (Exception e) {
            LOG.error("Error parsing json: {}", e.getMessage());
        }

    }

    public String readFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        LOG.debug("File Found : {}", file.exists());
        String content = "";
        try {
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException ioe) {
            LOG.error("Error reading file: {}", ioe.getMessage());
        }
        LOG.debug(content);
        return content;
    }
}
