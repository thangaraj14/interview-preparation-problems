package strings.parentheses;

/**
 * Your friend John uses a lot of emoticons when you talk to him on Messenger.
 * In addition to being a person who likes to express himself through emoticons,
 * he hates unbalanced parenthesis so much that it makes him go :(
 * <p>
 * Sometimes he puts emoticons within parentheses,
 * and you find it hard to tell if a parenthesis really is a parenthesis or part of an emoticon.

 * A message has balanced parentheses if it consists of one of the following:
 * - An empty string ""
 * - One or more of the following characters: 'a' to 'z', ' ' (a space) or ':' (a colon)
 * - An open parenthesis '(', followed by a message with balanced parentheses, followed by a close parenthesis ')'.
 * - A message with balanced parentheses followed by another message with balanced parentheses.
 * - A smiley face ":)" or a frowny face ":("
 *
 * (:)  : YES
 * i am sick today (:() : YES
 * )(: NO
 * hacker cup: started :):): YES
 * :((: NO
 */
public class BalancedSmiley {

    /**
     * Idea :
     *
     *     Idea is similar to other Balance Parentheses related problems. i.e we check the balance of ( and ) brackets.
     *
     *     And whenever number of ) exceeds number of ( , we can say that it is unbalanced from start.
     *
     *     And whenever number of ( exceeds number of ) , we can say that it is unbalanced from end ( this one is trivial).
     *
     *     This can be done by incrementing when we see opening ( bracket and decrementing vice versa.
     *
     *
     * if maxOpen is negative or minOpen isn't zero, it's not possible for the message to have balanced parentheses
     *
     * @param s
     */
    public boolean isBalanced(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                maxOpen++;
                if (i == 0 || s.charAt(i - 1) == ':') {
                    minOpen++;
                }
            } else if (s.charAt(i) == ')') {
                minOpen = Math.max(0, minOpen - 1);
                if (i == 0 || s.charAt(i - 1) == ':') {
                    maxOpen--;
                    if (maxOpen < 0) break;
                }
            }
        }
        return maxOpen >= 0 && minOpen == 0;
    }
}
