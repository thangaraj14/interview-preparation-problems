package geeksforgeeks;

class UrlEncode {
    char[] replaceSpaces(char[] str, int truelength) {
        int spaceCount = 0, index, i = 0;
        for (i = 0; i < truelength; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }

        index = truelength + spaceCount * 2;
        if (truelength < str.length)
            str[truelength] = ' ';
        for (i = truelength - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                index = index - 3;
            } else {
                str[index - 1] = str[i];
                index--;
            }
        }

        return str;
    }

    public static void main(String[] args) {
        System.out.println(new UrlEncode().replaceSpaces("Mr John Smith  ".toCharArray(), 13));
    }
}