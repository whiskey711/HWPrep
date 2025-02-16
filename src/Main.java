import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        fiveKeys("1 1 5 1 5 2 4 4");
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

    // unsolved
    static void buildMaxnum(String s) {
        String[] strArr = s.split(",");
        for (int i=0; i<strArr.length-1; i++) {
            for (int j=i+1; j<strArr.length; j++) {

                if (strArr[i].compareTo(strArr[j]) < 0 && !strArr[j].startsWith(strArr[i])) {
                    String temp = strArr[i];
                    strArr[i] = strArr[j];
                    strArr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(strArr));
    }

    static void shootingScores(int n, int[] ids, int[] scores) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i=0; i<n; i++) {
            if (map.containsKey(ids[i])) {
                List<Integer> temp = map.get(ids[i]);
                temp.add(scores[i]);
                map.put(ids[i], temp);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.add(scores[i]);
                map.put(ids[i], new ArrayList<>());
                map.put(ids[i], temp);
            }

        }
        map.entrySet().removeIf(entry -> entry.getValue().size() < 3);
        System.out.println(map);
        Map<Integer, Integer> summap = new HashMap<>();
        for (var entry: map.entrySet()) {
            int sum = entry.getValue().stream().reduce(0, Integer::sum);
            summap.put(entry.getKey(), sum);
        }
        System.out.println(summap);
        summap.entrySet().stream().sorted(Map.Entry.comparingByValue(
                (a, b) -> { return b - a; }
        )).forEach(
                s -> System.out.println(s.getKey())
        );
    }

    static void fiveKeys(String input) {
        StringBuilder screen = new StringBuilder();
        StringBuilder clipboard = new StringBuilder();
        boolean selection = false;
        String[] inputArray = input.split(" ");
        for (String in : inputArray) {
            switch (in) {
                case "1": // type a
                    if (selection){
                        screen = new StringBuilder("a");
                        selection = false;
                    }
                    else screen.append("a");
                    break;
                case "2": // ctrl c
                    if (selection) clipboard = screen;
                    break;
                case "3": // ctrl x
                    if (selection){
                        clipboard = screen;
                        screen = new StringBuilder();
                        selection = false;
                    }
                    break;
                case "4": // ctrl v
                    if (selection) {
                        screen = clipboard;
                        selection = false;
                    } else screen.append(clipboard);
                    break;
                case "5": // ctrl a
                    selection = true;
                    break;
            }

        }
        System.out.println("screen");
        System.out.println(screen);
        System.out.println("clipboard");
        System.out.println(clipboard);
    }
}