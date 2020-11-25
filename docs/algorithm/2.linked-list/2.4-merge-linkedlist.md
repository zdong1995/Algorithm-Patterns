# 2.4 Merge LinkedList

## I. Summary

#### ⌨ 3 problems in this section. Source code: [Merge LinkedList](https://github.com/zdong1995/coding-interview/tree/master/src/main/java/algorithm/linkedlist/merge).

For merge LinkedList problem, the technique we will use is to use dummy node. Since at the begining we don't know which `head` will be smaller, thus we will need some boilerplate code to determine which node will be used as the start of result. In this situation, the dummy node will be very useful.

We can create one dummy node and initialize one `tail` pointer at dummy node. Then we iteratively append new node after the `tail` and update `tail`. Finally after we merged the lists, the `head` of result list will be `dummy.next`. The main idea is as following:

```java
ListNode dummy = new ListNode(0);
ListNode tail = dummy;
ListNode current = head;

while (head != null) {
  tail.next = current;
  tail = tail.next;
  current = current.next;
}

return dummy.next; // the real head
```

### 2. Merge Two Sorted List

```java
public ListNode mergeTwoSortedList(ListNode head1, ListNode head2) {
  ListNode dummy = new ListNode(0);
  ListNode tail = dummy;

  while (head1 != null && head2 != null) {
    if (head1.val < head2.val) {
      tail.next = head1;
      head1 = head1.next;
    } else {
      tail.next = head2;
      head2 = head2.next;
    }
    tail = tail.next;
  }
  // post-processing
  if (head1 != null) {
    tail.next = head1;
  }
  if (head2 != null) {
    tail.next = head2;
  }
  return dummy.next;
}
```

