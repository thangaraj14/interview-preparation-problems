package geeksforgeeks;

/**
 * https://leetcode.com/problems/compare-version-numbers/
 */
public class CompareVersions {

    public static void main(String[] args) {
        String str1 = "1.3.4";
        String str2 = "1.3.1.7";
        System.out.println(compareVersion(str1, str2));
    }

    public static int compareVersion(String version1, String version2) {
        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");

        int length = Math.max(levels1.length, levels2.length);
        for (int i = 0; i < length; i++) {
            Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
            Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
            int compare = v1.compareTo(v2);
            if (compare != 0) {
                return compare;
            }
        }

        return 0;
    }

}
