package implementation.basic.hashmap;

import implementation.basic.linkedlist.ListNode;

/**
 * Implementation of HashMap, generic type is provided.
 * @author Zhang Dong
 * @version 1.0
 */
public class HashMap {
  private ListNode[] map;
  private int size;
  private float loadFactor;
  private static final float DEFAULT_LOAD_FACTOR = 0.75f;

  public HashMap() {
  }

  public HashMap(int capacity, float loadFactor) {
    map = new ListNode[capacity];
    this.loadFactor = loadFactor;
  }

  public int size() {
    return this.size;
  }
}
