import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(splitStrings(12, "12abc-abCABc-4aB@"));
    }

    static String splitStrings(int k, String s) {
        String[] strArr = s.split("-");
        String strWithoutHead = "";
        for (int i=1; i<strArr.length; i++) {
            strWithoutHead += strArr[i];
        }
        List<String> strLsWithoutHead = new ArrayList<>();
        strLsWithoutHead.add(strArr[0]);
        int lastJ = 0;
        for (int j=0; j<strWithoutHead.length(); j++) {
            if (j%k == 0 && j != 0) {
                String str = strWithoutHead.substring(j-k, j);
                lastJ = j;
                strLsWithoutHead.add(strConversion(str));
            }
        }

        strLsWithoutHead.add(strConversion(strWithoutHead.substring(lastJ)));
        return String.join("-", strLsWithoutHead);
    }

    static String strConversion(String str) {
        int lowLetterLen = str.replaceAll("[A-Z]", "").length();
        int upLetterLen = str.replaceAll("[a-z]", "").length();
        if (upLetterLen > lowLetterLen) {
            str = str.toUpperCase();
        }else if (upLetterLen < lowLetterLen) {
            str = str.toLowerCase();
        }
        return str;
    }
}