/* DList.java */
//GRADER edit size/head
package list;

/**
 *  A DList is a mutable doubly-linked list ADT.  Its implementation is
 *  circularly-linked and employs a sentinel (dummy) node at the head
 *  of the list.
 *
 *  DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

public class DList {

  /**
   *  head references the sentinel node.
   *  size is the number of items in the list.  (The sentinel node does not
   *       store an item.)
   *
   *  DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
   */

  protected DListNode head;
  protected int size;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode x in a DList, x.next != null.
   *  3)  For any DListNode x in a DList, x.prev != null.
   *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
   *      that can be accessed from the sentinel (head) by a sequence of
   *      "next" references.
   */

  /**
   *  newNode() calls the DListNode constructor.  Use this class to allocate
   *  new DListNodes rather than calling the DListNode constructor directly.
   *  That way, only this method needs to be overridden if a subclass of DList
   *  wants to use a different kind of node.
   *  @param item the item to store in the node.
   *  @param prev the node previous to this node.
   *  @param next the node following this node.
   */
  protected DListNode newNode(Object item, DListNode prev, DListNode next) {
    return new DListNode(item, prev, next);
  }

  /**
   *  DList() constructor for an empty DList.
   */
  public DList() {
    head = newNode(null, head, head);
    size = 0;
  }

  /**
   *  isEmpty() returns true if this DList is empty, false otherwise.
   *  @return true if this DList is empty, false otherwise. 
   *  Performance:  runs in O(1) time.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /** 
   *  length() returns the length of this DList. 
   *  @return the length of this DList.
   *  Performance:  runs in O(1) time.
   */
  public int length() {
    return size;
  }

  /**
   *  insertFront() inserts an item at the front of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertFront(Object item) {
    if(size==0)
    {
        DListNode temp = newNode(item, head, head);
        head.next = temp;
        head.prev = temp;
    }
    else{
        DListNode temp = newNode(item, head, head.next);
        head.next = temp;
        head.next.next.prev = head.next;
    }
    size++;
  }

  /**
   *  insertBack() inserts an item at the back of this DList.
   *  @param item is the item to be inserted.
   *  Performance:  runs in O(1) time.
   */
  public void insertBack(Object item) {
    if(size==0)
    {//if empty list
        DListNode temp = newNode(item, head, head);
        head.next = temp;
        head.prev = temp;
    }
    else{ 
        DListNode temp = newNode(item, head.prev, head);
        head.prev = temp;
        head.prev.prev.next = head.prev;
    }
    size++;
  }

  /**
   *  front() returns the node at the front of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the front of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode front() {
    if(isEmpty())
    {
        return null;
    }
    return head.next;
  }

  /**
   *  back() returns the node at the back of this DList.  If the DList is
   *  empty, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @return the node at the back of this DList.
   *  Performance:  runs in O(1) time.
   */
  public DListNode back() {
      if(isEmpty())
      {
          return null;
      }
      return head.prev;
  }

  /**
   *  next() returns the node following "node" in this DList.  If "node" is
   *  null, or "node" is the last node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose successor is sought.
   *  @return the node following "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode next(DListNode node) {
    if(node==null || node.next == head ){
        return null;
    }
    return node.next;
  }

  /**
   *  prev() returns the node prior to "node" in this DList.  If "node" is
   *  null, or "node" is the first node in this DList, return null.
   *
   *  Do NOT return the sentinel under any circumstances!
   *
   *  @param node the node whose predecessor is sought.
   *  @return the node prior to "node".
   *  Performance:  runs in O(1) time.
   */
  public DListNode prev(DListNode node) {
    if(node==null || node.prev == head ){
        return null;
    }
    return node.prev;
  }

  /**
   *  insertAfter() inserts an item in this DList immediately following "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item after.
   *  Performance:  runs in O(1) time.
   */
  public void insertAfter(Object item, DListNode node) {
      if(node!=null){
        DListNode temp = newNode(item, node, node.next);
        node.next = temp;
        node.next.next.prev = temp;
        size++;
      }
  }

  /**
   *  insertBefore() inserts an item in this DList immediately before "node".
   *  If "node" is null, do nothing.
   *  @param item the item to be inserted.
   *  @param node the node to insert the item before.
   *  Performance:  runs in O(1) time.
   */
  public void insertBefore(Object item, DListNode node) {
     if(node!=null){
        DListNode temp = newNode(item, node.prev, node);
        node.prev = temp;
        node.prev.prev.next = temp;
        size++;
     }
  }

  /**
   *  remove() removes "node" from this DList.  If "node" is null, do nothing.
   *  Performance:  runs in O(1) time.
   */
  public void remove(DListNode node) {
    if(node!=null && node != head){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   *  DO NOT CHANGE THIS METHOD.
   *
   *  @return a String representation of this DList.
   *  Performance:  runs in O(n) time, where n is the length of the list.
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head.next;
    while (current != head) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }
  
  
  public static void main(String args[]){
      DList test = new DList();
      
      test.insertFront(6);
      System.out.println(test.toString());
      test.insertBack(5);
      System.out.println(test);
      test.insertFront(7);
      System.out.println(test);
      test.insertFront(8);
      System.out.println(test);
      test.insertBack(4);
      System.out.println(test);
      test.insertAfter(3, test.head.prev);
      System.out.println(test);
      test.insertBefore(9, test.head.next);
      test.insertBack(1);
      test.insertBefore(2, test.head.prev);
      System.out.println(test);
      test.insertBefore(142823, test.head.next.next.next);
      System.out.println("length iiiiiis" + test.length());
      test.remove(test.head.next.next.next);
      System.out.println(test);
      System.out.println(test.front().item);
      System.out.println(test.back().item);
      System.out.println(test.prev(test.head.next.next).item);
      
      System.out.println("NOW FOR LOCKED");
      System.out.println("NOW FOR LOCKED");
      System.out.println("NOW FOR LOCKED");
      
      LockDList test2 = new LockDList();
      test2.insertFront(6);
      System.out.println(test2.toString());
      test2.insertBack(5);
      System.out.println(test2);
      test2.insertFront(7);
      System.out.println(test2);
      test2.insertFront(8);
      System.out.println(test2);
      test2.insertBack(4);
      System.out.println(test2);
      test2.insertAfter(3, test2.head.prev);
      test2.remove(test2.head.next);
      System.out.println(test2);
      test2.lockNode(test2.head.next);
      test2.remove(test2.head.next);
      System.out.println(test2);
      
      
  }
  
  
  
  
}
