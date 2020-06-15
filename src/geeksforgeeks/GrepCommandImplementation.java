package geeksforgeeks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://www.geeksforgeeks.org/grep-command-in-unixlinux/
 */
public class GrepCommandImplementation {

    public static void main(String[] argv) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string to match from GrepCommandImplementation.java file: ");
        Pattern pattern = Pattern.compile(sc.next());
        Matcher matcher = pattern.matcher("");
        String file = "src/com/sanfoundry/setandstring/GrepCommandImplementation.java";
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new FileReader(file));

        } catch (IOException e) {
            System.err.println("Cannot read '" + file + "': " + e.getMessage());
        }
        while ((line = br.readLine()) != null) {
            matcher.reset(line);
            if (matcher.find()) {

            }
            {
                System.out.println(file + ": " + line);

            }
        }
        br.close();
        sc.close();
    }
}