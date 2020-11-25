package datastrcture.linkedlist;

public class Test {
  public static void main(String[] args) {
    int[] array = {1, 3, 5, 2, 4, 6};
    LinkedList list = new LinkedList(array);
    System.out.println(list);
    list.remove(1);
    System.out.println(list);
    list.appendHead(8);
    System.out.println(list);
    list.appendTail(10);
    System.out.println(list);
  }
}
