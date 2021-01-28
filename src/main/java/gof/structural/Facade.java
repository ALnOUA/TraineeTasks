package gof.structural;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Facade {
    public static void main(String[] args) {
        String path = "some pathe";
        ReadFilePathFascade readFilePathFascade = new ReadFilePathFascade();
        readFilePathFascade.readFilesPath(path);

        /* its a part of code from my pet project (FileSystemApp)
        * path means a path of root directory
        * readFilesPath method gives us all pathes of subfolders and subfiles
        * we just hide this logic into additional class ReadFilePathFascade */
    }
}

class ReadFilePathFascade {
    public List<String> readFilesPath(String path) {
        List<String> pathes = Collections.emptyList();

        try (Stream<Path> walk = Files.walk(Paths.get(path))) {

            pathes = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".txt")).collect(Collectors.toList());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathes;
    }
}
