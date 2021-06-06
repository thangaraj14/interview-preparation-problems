package sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * https://www.geeksforgeeks.org/java-8-predicate-with-examples/
 */
public class PredicateTest {

    public static void main(String[] args) {
        String str = "thisistest";
        Predicate<String> predicate = s -> s.length() > 3;
        Predicate<String> predicateDu = s -> s.length() > 100;
        System.out.println(predicate.test(str));
        System.out.println(predicateDu.test(str));
        test();
    }

    public static void test() {
        List<User> users = new ArrayList<User>();
        users.add(new User("John", "admin"));
        users.add(new User("Peter", "member"));

        Predicate<User> predicate = user -> user.getRole().equals("admin");
        List admins = users.stream().filter(predicate).collect(Collectors.toList());

        System.out.println(admins);
    }
}

class User {
    String name, role;

    User(String a, String b) {
        name = a;
        role = b;
    }

    String getRole() {
        return role;
    }

    String getName() {
        return name;
    }

    public String toString() {
        return "User Name : " + name + ", Role:" + role;
    }

}
