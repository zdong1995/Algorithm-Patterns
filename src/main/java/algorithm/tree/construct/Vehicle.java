package algorithm.tree.construct;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class Vehicle {
  public int size;
  public boolean canPark;

  public Vehicle(int size, boolean canPark) {
    this.size = size;
    this.canPark = canPark;
  }


  public Vehicle() {
  }


  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public boolean isCanPark() {
    return canPark;
  }

  public void setCanPark(boolean canPark) {
    this.canPark = canPark;
  }
}
