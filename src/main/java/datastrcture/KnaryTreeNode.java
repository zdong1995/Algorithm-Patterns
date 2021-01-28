package datastrcture;

import java.util.*;

public class KnaryTreeNode {
    public int key;
    public List<KnaryTreeNode> children;

    public KnaryTreeNode(int key) {
        this.key = key;
        this.children = new ArrayList<>();
    }
}
