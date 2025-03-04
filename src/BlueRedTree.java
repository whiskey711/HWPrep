public class BlueRedTree {
    int val;
    BlueRedTree blue;
    BlueRedTree red;

    BlueRedTree() {}

    BlueRedTree(int val) { this.val = val; }

    BlueRedTree(int val, BlueRedTree blue, BlueRedTree red) {
        this.val = val;
        this.blue = blue;
        this.red = red;
    }
}
