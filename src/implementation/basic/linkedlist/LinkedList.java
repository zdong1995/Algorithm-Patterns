package implementation.basic.linkedlist;

/** Implementation of singly linked list in Java.
 * @author Zhang Dong
 * @version 1.0
 */
class ListNode {
  int val;
  ListNode next;

  public ListNode(int val) {
    this.val = val;
    this.next = null;
  }
}

public class LinkedList {
  ListNode head;

  public LinkedList() {
    this.head = null;
  }

  public LinkedList(int val) {
    this.head = new ListNode(val);
  }

  public LinkedList(int[] array) {
    if (array == null || array.length == 0) {
      this.head = null;
    }
    this.head = new ListNode(array[0]);
    for (int i = 1; i < array.length; i++) {
      this.appendTail(array[i]);
    }
  }

  /**
   * Return the length of given LinkedList
   *
   * @return the length of LinkedList
   */
  public int length() {
    int length = 0;
    while (head != null) {
      length++;
      head = head.next;
    }
    return length;
  }

  /**
   * Retrieve an element at a specific index from the LinkedList.
   *
   * @param index the index of the element to be retrieved
   * @return list node at the input index
   */
  public ListNode get(int index) {
    while (index > 0 && head != null) {
      head = head.next;
      index--;
    }
    // if index is out of bound, head will be null, we also return null here
    return head;
  }

  /**
   * Append one element at the head position of LinkedList.
   *
   * @param val the value of list node needed to be appended
   * @return updated head of LinkedList
   */
  public void appendHead(int val) {
    ListNode newNode = new ListNode(val);
    // case 1: head == null
    if (this.head == null) {
      this.head = newNode;
    }
    // case 2: head != null
    newNode.next = this.head;
    this.head = newNode;
  }

  /**
   * Append one element at the tail position of LinkedList.
   *
   * @param val the value of list node needed to be appended
   */
  public void appendTail(int val) {
    ListNode dummy = new ListNode(0);
    dummy.next = this.head;
    ListNode cur = dummy;
    while (cur.next != null) {
      cur = cur.next;
    }
    cur.next = new ListNode(val);
    this.head = dummy.next;
  }

  /**
   * Assuming no duplicate values in the linked list, not circled-list,
   * remove one element with request value, do nothing if not found.
   *
   * @param target the value of list node needed to be removed
   * @return {@code true} if this list contained the specified element
   */
  public boolean remove(int target) {
    if (head == null) { // case 1: empty list
      return false;
    }
    ListNode cur = this.head;
    // case 2: remove head
    if (cur.val == target) {
      this.head = this.head.next;
      cur.next = null;
      return true;
    }
    // case 3: stop at the node before target node
    while (cur.next != null) {
      if (cur.next.val == target) {
        ListNode next = cur.next;
        cur.next = cur.next.next;
        next.next = null;
        return true;
      }
      cur = cur.next;
    }
    return false;
  }

  @Override
  public String toString() {
    if (head == null) {
      return "";
    }
    ListNode cur = head;
    StringBuilder res = new StringBuilder();
    while (cur.next != null) {
      res.append(cur.val).append(", ");
      cur = cur.next;
    }
    return res.append(cur.val).toString();
  }
}
