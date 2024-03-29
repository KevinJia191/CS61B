

/* DListNode1.java */

/**
 *  A DListNode1 is a node in a DList1 (doubly-linked list).
 */

public class DListNode1 {

  /**
   *  item references the item stored in the current node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  public int[] item = new int[3];
  public DListNode1 prev;
  public DListNode1 next;

  /**
   *  DListNode1() constructor.
   */
  DListNode1() {
    item[0] = 0;
    item[1] = 0;
    item[2] = 0;
    prev = null;
    next = null;
    System.out.println("CARECARECARE WRONG CONSTRUCTOR");
  }

  DListNode1(int obj, int length, int starveTimer) {
    item[0] = obj;
    item[1] = length;
    item[2] = starveTimer;
    prev = null;
    next = null;
  }
}
