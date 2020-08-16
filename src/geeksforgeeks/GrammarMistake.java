package geeksforgeeks;

/**
 * https://www.geeksforgeeks.org/check-given-sentence-given-set-simple-grammer-rules/
 * A simple sentence if syntactically correct if it fulfills given rules. The following are given rules.
 *
 * 1. Sentence must start with a Uppercase character (e.g. Noun/ I/ We/ He etc.)
 * 2. Then lowercase character follows.
 * 3. There must be spaces between words.
 * 4. Then the sentence must end with a full stop(.) after a word.
 * 5. Two continuous spaces are not allowed.
 * 6. Two continuous upper case characters are not allowed.
 * 7. However, the sentence can end after an upper case character.
 *
 *
 */
import java.util.Arrays;
import java.util.List;

class Main
{
    public static boolean validateSentence(char[] chars)
    {
        int index = 0;
        if (Character.isLowerCase(chars[index])) { // 1st condition
            return false;
        }

        while (index < chars.length)
        {
            if (Character.isUpperCase(chars[index]))
            {
                if (Character.isUpperCase(chars[index + 1])) { // 5th condition
                    return false;
                }

                if (index - 1 >= 0 && chars[index - 1] != ' ') { // 2nd condition
                    return false;
                }
            }

            if (chars[index] == ' ' && chars[index + 1] == ' ') { // 4th condition
                return false;
            }

            index++;
        }

        if (chars[index - 2] == ' ' || chars[index - 1] != '.') { // 3th condition
            return false;
        }

        return true;
    }

    public static void main(String[] args)
    {
        List<String> list = Arrays.asList(
                "This sentence is syntactically correct.",

                "This sentence is syntactically  incorrect as two " +
                        "continuous spaces are not allowed.",

                "This sentence is syntactically correct Y.",

                "This sentence is syntactically incorRect as uppercase " +
                        "character is not allowed midway of the String.",

                "THis sentence is syntactically incorrect as lowercase " +
                        "character don't follow the first uppercase character.",

                "This sentence is syntactically incorrect as it doesn't " +
                        "end with a full stop"
        );

        System.out.println("Valid sentences are -");
        for (String sentence: list) {
            if (validateSentence(sentence.toCharArray())) {
                System.out.println(sentence);
            }
        }
    }
}
