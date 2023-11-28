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

public class RandomProblemGenerator {
    public static void main(String[] args) throws IOException {
        List<File> filesInFolder = Files.walk(Paths.get("src/main/java"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .filter(file -> !(file.getPath().contains("lld") ||
                        file.getPath().contains("java8") ||
                        file.getPath().contains("reflections") ||
                        file.getPath().contains("multithreading") ||
                        file.getPath().contains("internals")))
                .filter(file -> file.getName().contains(".java"))
                .collect(Collectors.toList());
        int rand=  (new Random().nextInt(filesInFolder.size()));
        System.out.println(rand+" "+filesInFolder.get(rand).getName());
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filesInFolder.get(rand)))){
            reader.lines().filter(e->e.contains("http") || e.contains("https")).forEach(System.out::println);
        }

        System.out.println(filesInFolder.size());
    }

}
