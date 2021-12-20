package dev.jsonone;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws IOException {
        LOG.info("Starting ...");
        new App().run();
    }

    public void run() throws IOException {
        var json = readFile("data.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        Item item = mapper.readValue(json, Item.class);
        LOG.info("Some date: {}", item.someDate);
    }

    public String readFile(String fileName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        LOG.debug("File Found : {}", file.exists());
        String content = new String(Files.readAllBytes(file.toPath()));
        LOG.debug(content);
        return content;
    }
}
