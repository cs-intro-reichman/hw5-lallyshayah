/**
 * A library of string functions.
 */
public class MyString {
    public static void main(String args[]) {
        String hello = "hello";
        System.out.println(countChar(hello, 'h'));
        System.out.println(countChar(hello, 'l'));
        System.out.println(countChar(hello, 'z'));
        System.out.println(spacedString(hello));
        //// Put your other tests here.

        // subsetOf tests:
        System.out.println(subsetOf("sap", "space")); // returns true
        System.out.println(subsetOf("spa", "space")); // returns true
        System.out.println(subsetOf("pass", "space")); // returns false
        System.out.println(subsetOf("c", "space")); // returns true

        // randomStringOfLetters tests:
        System.out.println(randomStringOfLetters(4));
        System.out.println(randomStringOfLetters(3));
        System.out.println(randomStringOfLetters(0));
        System.out.println(randomStringOfLetters(20));

        // remove tests:
        System.out.println(remove("committee", "meet")); // returns "comit"

        // insertRandomly tests:
        System.out.println(insertRandomly('s',"cat"));

    }

    /**
     * Returns the number of times the given character appears in the given string.
     * Example: countChar("Center",'e') returns 2 and countChar("Center",'c')
     * returns 0.
     * 
     * @param str - a string
     * @param c   - a character
     * @return the number of times c appears in str
     */
    public static int countChar(String str, char ch) {
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch)
                count++;
        }

        return count;
    }

    /**
     * Returns true if str1 is a subset string str2, false otherwise
     * Examples:
     * subsetOf("sap","space") returns true
     * subsetOf("spa","space") returns true
     * subsetOf("pass","space") returns false
     * subsetOf("c","space") returns true
     *
     * @param str1 - a string
     * @param str2 - a string
     * @return true is str1 is a subset of str2, false otherwise
     */
    public static boolean subsetOf(String str1, String str2) {

        // edge cases

        if (str1.length() > str2.length()) {
            return false;
        }

        if (str1.length() == 0) {
            return true;
        }

        // arrays that each index of them represents number of occurrences of all
        // letters in str1 and str2.

        int[] countCharStr1Arr = new int[52];
        int[] countCharStr2Arr = new int[52];

        for (int i = 0; i < str1.length(); i++) {
            int asciiValue = (int) str1.charAt(i);

            if (asciiValue <= 90)
                countCharStr1Arr[asciiValue - 65]++;

            if (asciiValue >= 97)
                countCharStr1Arr[asciiValue - 71]++;
        }

        for (int i = 0; i < str2.length(); i++) {
            int asciiValue = (int) str2.charAt(i);

            if (asciiValue <= 90)
                countCharStr2Arr[asciiValue - 65]++;

            if (asciiValue >= 97)
                countCharStr2Arr[asciiValue - 71]++;
        }

        // this loop checks if Number of occurrences of all letters in str1 is less or
        // equal to number of occurrences in str2.
        // it's important for understanding if str1 is a subsetOf str2.

        boolean IsNumOfOccurrencesLessOrEqual = true;

        for (int i = 0; i < 52; i++) {
            if (countCharStr1Arr[i] > countCharStr2Arr[i])
                IsNumOfOccurrencesLessOrEqual = false;
        }

        // final solution

        int count = 0;

        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (str2.charAt(j) == str1.charAt(i))
                    count++;
            }
        }

        if (count == str1.length() && IsNumOfOccurrencesLessOrEqual == true)
            return true;

        else
            return false;
    }

    /**
     * Returns a string which is the same as the given string, with a space
     * character inserted after each character in the given string, except
     * for the last character.
     * Example: spacedString("silent") returns "s i l e n t"
     * 
     * @param str - a string
     * @return a string consisting of the characters of str, separated by spaces.
     */
    public static String spacedString(String str) {
        String spacedString = "";
        if (str == "") {
            return str;
        }
        for (int i = 0; i < str.length() - 1; i++) {
            char c = str.charAt(i);

            spacedString = spacedString + c + " ";
        }

        // last letter in str so I don't want to add another space!
        spacedString = spacedString + str.charAt(str.length() - 1);

        return spacedString;
    }

    /**
     * Returns a string of n lowercase letters, selected randomly from
     * the English alphabet 'a', 'b', 'c', ..., 'z'. Note that the same
     * letter can be selected more than once.
     * 
     * Example: randomStringOfLetters(3) can return "zoo"
     * 
     * @param n - the number of letter to select
     * @return a randomly generated string, consisting of 'n' lowercase letters
     */
    public static String randomStringOfLetters(int n) {
        String rndString = "";

        int count = 0;

        while (count < n) {
            int randomIndex = (int) (Math.random() * 26);
            char randomChar = (char) ('a' + randomIndex);
            rndString = rndString + randomChar;
            count++;
        }

        return rndString;
    }

    /**
     * Returns a string consisting of the string str1, minus all the characters in
     * the
     * string str2. Assumes (without checking) that str2 is a subset of str1.
     * Example: remove("committee","meet") returns "comit"
     * 
     * @param str1 - a string
     * @param str2 - a string
     * @return a string consisting of str1 minus all the characters of str2
     */
    public static String remove(String str1, String str2) {

        String answerString = str1;

        for (int i = 0; i < str2.length(); i++) {
            char c = str2.charAt(i);
            int index = answerString.indexOf(c);

            if (index != -1) {
                if (index < answerString.length() - 1) {
                    answerString = answerString.substring(0, index) + answerString.substring(index + 1);
                }

                else {
                    answerString = answerString.substring(0, index);
                }
            }
        }

        return answerString;

        // נעבור אות אות מהמילה שרוצים לחסר
        // נבדוק באמצעות פונקציה אינדקס של אם האות מופיעה בסטרינג שמחסרים ממנו
        // אם לא נעבור אות
        // אחרת נמחק את האות מהסטרינג הגדול על ידי פיצול שלו בעזרת פונקצית סאב סטרינג

    }

    /**
     * Returns a string consisting of the given string, with the given
     * character inserted randomly somewhere in the string.
     * For example, insertRandomly("s","cat") can return "scat", or "csat", or
     * "cast", or "cats".
     * 
     * @param ch  - a character
     * @param str - a string
     * @return a string consisting of str with ch inserted somewhere
     */
    public static String insertRandomly(char ch, String str) {
        // Generate a random index between 0 and str.length()
        int randomIndex = (int) (Math.random() * (str.length() + 1));
        // Insert the character at the random index
        String result = str.substring(0, randomIndex) + ch + str.substring(randomIndex);
        return result;
    }
}
