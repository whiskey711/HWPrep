public class SegmentTree {
    class Node {
        Node left, right;
        int val;
        // lazy mark
        int add;
    }

    private void update(Node node, int start, int end, int left, int right, int val) {
        // [start, end] is subset to [left, end]
        if (left <= start && end <= right) {
            node.val += (end - start + 1) * val;
            node.add += val;
        } else {
            int mid = (start + end) / 2;
            // push lazy mark to children
            pushDown(node, mid-start+1, end-mid);
            if (left <= mid) update(node.left, start, mid, left, right, val);
            if (mid < right) update(node.right, mid+1, end, left, right, val);
            pushUp(node);
        }
    }

    private void pushDown(Node node, int leftNum, int rightNum) {
        // create child nodes
        if (node.left == null) node.left = new Node();
        if (node.right == null) node.right = new Node();
        // check lazy mark
        if (node.add == 0) return;
        node.left.val += node.add * leftNum;
        node.right.val += node.add * rightNum;
        // 把标记下推给孩子节点
        // 对区间进行「加减」的更新操作，下推懒惰标记时需要累加起来，不能直接覆盖
        node.left.add += node.add;
        node.right.add += node.add;
        // 取消当前节点标记
        node.add = 0;
    }

    public int query(Node node, int start, int end, int l, int r) {
        if (l <= start && end <= r) return node.val;
        int mid = (start + end) / 2, ans = 0;
        pushDown(node, mid - start + 1, end - mid);
        if (l <= mid) ans += query(node.left, start, mid, l, r);
        if (r > mid) ans += query(node.right, mid + 1, end, l, r);
        return ans;
    }
    private void pushUp(Node node) {
        node.val = node.left.val + node.right.val;
    }

}
