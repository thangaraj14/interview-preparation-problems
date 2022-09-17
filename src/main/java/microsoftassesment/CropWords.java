package microsoftassesment;

public class CropWords {

    public static String cropWords(String message, int K){
        if (message == null || message.length() == 0){
            return "";
        }
        // k greater than string, we return as it is;
        if (message.length() <= K){
            return trim(message);
        }
        // we split at some point and move back wards till we meet a space, so that we can confirm that result has whole words only, then trim removes the space
        StringBuilder sb = new StringBuilder(message.substring(0, K+1));
        while(sb.length() > 0 && sb.charAt(sb.length() - 1) != ' '){
            sb.deleteCharAt(sb.length() - 1);
        }

        return trim(sb.toString());
    }

    private static String trim(String message){
        int i = message.length() - 1;
        if (message.charAt(i) == ' '){
            while(i >= 0 && message.charAt(i) == ' '){
                i--;
            }
            if (i < 0){
                return "";
            }
            return message.substring(0, i+1);
        }


        return message;
    }

    public static void main(String[] args){
        System.out.println(cropWords("codility We test coders", 14).equals("codility We"));// === "codility We"
        System.out.println(cropWords(" co de my", 5).equals(" co"));// === " co"
        System.out.println(cropWords(" co de my", 7).equals(" co de"));// === " co de"
        System.out.println(cropWords("   ", 2).equals(""));// === ""
        System.out.println(cropWords("   re", 2).equals(""));// === "") //3 spaces before
        System.out.println(cropWords(" c ", 3).equals(" c"));// === " c"
        System.out.println(cropWords(" c d  ", 5).equals(" c d"));// === " c d"
        System.out.println(cropWords("co de my", 5).equals("co de"));// === "co de"
        System.out.println(cropWords("Word", 4).equals("Word"));// === "Word"
        System.out.println(cropWords("codility We test coders", 23).equals("codility We test coders"));// === "codility We test coders"
        System.out.println(cropWords("withOutSpaces", 14).equals("withOutSpaces"));// === "withOutSpaces"
        System.out.println(cropWords("", 14).equals(""));// === ""
        System.out.println(cropWords("Separatedby hyphens", 14).equals("Separatedby"));// === "Separatedby"
        System.out.println(cropWords("      Codility We test coders     ", 14).equals("      Codility"));// === "      Codility") //6 leading spaces
        System.out.println(cropWords("      Codility We test coders     ", 10).equals(""));// === "") //6 leading spaces
    }
}
