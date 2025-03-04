import java.util.*;

public class Main {
    Set<String> visited = new HashSet<>();

    public static void main(String[] args) {
        Main mm = new Main();
        mm.constructBRTree(3, new int[][]{{0, 1}}, new int[][]{{2, 1}});

    }

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] redFrom = new ArrayList[n];
        List<Integer>[] blueFrom = new ArrayList[n];
        for (int i=0; i<n; i++) {
            redFrom[i] = new ArrayList<>();
            blueFrom[i] = new ArrayList<>();
        }
        for (int[] edge : redEdges) {
            int from = edge[0];
            int to = edge[1];
            redFrom[from].add(to);
        }for (int[] edge : blueEdges) {
            int from = edge[0];
            int to = edge[1];
            blueFrom[from].add(to);
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()) {

        }
    }

    public void constructBRTree(int n, int[][] redEdges, int[][] blueEdges) {
        // 标明颜色，这是很好的习惯哦。
        final int RED = 0;
        final int BLUE = 1;

        // 构建双层邻接表
        List<Integer>[][] adj = new ArrayList[2][n];
        for (int i = 0; i < n; i++) {
            adj[RED][i] = new ArrayList<>();
            adj[BLUE][i] = new ArrayList<>();
        }
        for (int[] edge : redEdges) {
            adj[RED][edge[0]].add(edge[1]);
        }
        for (int[] edge : blueEdges) {
            adj[BLUE][edge[0]].add(edge[1]);
        }
    }

    public int closedIsland(int[][] grid) {
        int island = 0;
        for (int row=1; row<grid.length-1; row++) {
            for (int col=1; col<grid[row].length-1; col++) {
                if (grid[row][col] == 1) continue;
                if (visited.contains(row+"_"+col)) continue;
                if (dfs(grid, row, col)) {
                    island++;
                    System.out.println("start from "+row+"_"+col);
                }
            }
        }
        return island;
    }

    public boolean dfs(int[][] grid, int row, int col) {
        visited.add(row + "_" + col);
        // base case 1: 1 direction is boundary of grid
        if (row == 0 || row == grid.length-1 ||
                col == 0 || col == grid[row].length-1) return false;
        // if there are direction that is not visited, dfs
        boolean right, left, up, down;
        right = left = up = down = true;
        // right
        if (!visited.contains(row+"_"+(col+1)) && grid[row][col+1]!=1) {
            right = dfs(grid, row, col+1);
        }
        // left
        if (!visited.contains(row+"_"+(col-1)) && grid[row][col-1]!=1) {
            left = dfs(grid, row, col-1);
        }
        // down
        if (!visited.contains((row+1)+"_"+col) && grid[row+1][col]!=1) {
            down = dfs(grid, row+1, col);
        }
        // up
        if (!visited.contains((row-1)+"_"+col) && grid[row-1][col]!=1) {
            up = dfs(grid, row-1, col);
        }
        // base case 2: 4 directions are either visited or water

        System.out.print(row+"_"+col+", ");
        return right && left && up && down;
    }

    static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0, j = 0;
        while (i < popped.length) {
            while (stack.isEmpty() || stack.peek() != popped[i]) {
                if (j >= pushed.length) return false;
                stack.push(pushed[j]);
                j++;
            }
            stack.pop();
            i++;
        }
        return true;
    }

    static int[][] reconstructQueue(int[][] people) {
        // [a,b], sort by a desc then b asc
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            else return b[0] - a[0];
        });
        LinkedList<int[]> ls = new LinkedList<>();
        for (int[] person : people) {
            ls.add(person[1], person);
        }
        return ls.toArray(new int[ls.size()][2]);
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

    static int threeSumClosest(int[] nums, int target) {
        return 0;
    }

    static class MyCircularQueue {

        int[] que;
        int front;
        int rear;
        int capacity;

        public MyCircularQueue(int k) {
            que = new int[k+1];
            front = 0;
            rear = 0;
            capacity = k;
        }

        public boolean enQueue(int value) {
            if (isFull()) return false;
            que[rear] = value;
            rear = (rear+1) % que.length;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) return false;
            front = (front+1) % que.length;
            return true;
        }

        public int Front() {
            if (isEmpty()) return -1;
            return que[front];
        }

        public int Rear() {
            if (isEmpty()) return -1;
            int actualRear = (rear+capacity) % que.length;
            return que[actualRear];
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public boolean isFull() {
            return (rear+1) % que.length == front;
        }
    }

}