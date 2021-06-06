package dsa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class QuestionGenerator {

    public static void main(String[] args) throws IOException {

        List<File> filesInFolder = Files.walk(
                Paths.get("/Users/i312458/OneDrive - SAP SE/iStreet_WS/AmazonProblems/src/dsa"))
                                        .filter(Files::isRegularFile)
                                        .map(Path::toFile)
                                        .filter(file -> file.getName().contains(".java"))
                                        .collect(Collectors.toList());
        int rand = (new Random().nextInt(filesInFolder.size()));
        System.out.println(rand + " " + filesInFolder.get(rand).getName());
        try (BufferedReader reader = new BufferedReader(new FileReader(filesInFolder.get(rand)))) {
            reader.lines().filter(e -> e.contains("http") || e.contains("https")).forEach(System.out::println);
        }
    }
}