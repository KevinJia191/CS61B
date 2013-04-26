/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/


public class Set {
  /* Fill in the data fields here. */
    
    
 protected List myList;
  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    // Your solution here.
      myList = new DList();
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return myList.length();
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) {
    // Your solution here.
      ListNode pos = myList.front();
      int cardinality = cardinality();
     
      while(cardinality>0)
      {
          cardinality--;
          try{
              if(c.compareTo(pos.item())==-1){
                  pos.insertBefore(c);
                  return;
              }
              if(c.compareTo(pos.item())==0){
                  return;
              }
              pos = pos.next();
          }
          catch(InvalidNodeException e1){
              
          }
          
      }
      //System.out.println(c);
      myList.insertBack(c); //in case its the largest  
  }

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
    public void union(Set s) {
    // Your solution here.
      
      int length = s.myList.length() + this.myList.length();
      
      ListNode thispos = this.myList.front(), spos = s.myList.front();
      Comparable o1, o2;
     
      
      
      while(length>0){
          try{
              o1 = (Comparable) thispos.item();
              o2 = (Comparable) spos.item();
              
              if(o1.compareTo(o2)==-1){
                  
                  //thispos.insertAfter(o2);
                  //thispos = thispos.next();
                  
                  if(thispos.next().isValidNode()){
                    thispos = thispos.next();
                  }
                  else{
                      while(spos.next().isValidNode()){
                          thispos.insertAfter(o2);
                          thispos = thispos.next();
                          spos = spos.next();
                          o2 = (Comparable) spos.item();
                      }
                      o2 = (Comparable) spos.item(); //last item
                      thispos.insertAfter(o2); //last item
                      length = 0; //break
                  }
                  //System.out.println("first");
              }
              
              
              else if(o1.compareTo(o2)==1){
                  thispos.insertBefore(o2);
                  if(spos.next().isValidNode()){
                    spos = spos.next();
                  }
                  //System.out.println("third");
              }
              
              
              else if(o1.compareTo(o2)==0){

                  if(thispos.next().isValidNode()){
                    thispos = thispos.next();
                  }
                  if(spos.next().isValidNode()){
                    spos = spos.next();
                  }
                  length--; //cause we take off 2 elements
              }
              length--;
              
          }
          catch(InvalidNodeException e1){
              
          }
      }
      
     
  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
    // Your solution here.
      Comparable o1, o2;
      ListNode thispos = this.myList.front(), spos = s.myList.front();
      int length = s.myList.length() + this.myList.length();
      
      while(length > 0){
          
          try{
            o1 = (Comparable)thispos.item();
            o2 = (Comparable)spos.item();

            if(o2.compareTo(o1)==1){ 
                
                if(thispos.next().isValidNode()){
                    thispos = thispos.next();
                    thispos.prev().remove();
                   
                 }
                else{
                    thispos.remove();
                }
                
                
            }
            else if(o2.compareTo(o1)==-1){
                if(spos.next().isValidNode()){
                    spos = spos.next();
                }
                else{//were done, final value of s is less than rest of elements in this
                    while(thispos.next().isValidNode()){
                        thispos = thispos.next();
                        thispos.prev().remove();
                    }
                    thispos.remove(); //final element
                }
            }
            else if(o2.compareTo(o1)==0){
                //this.insert(o2); //not necessary
                if(thispos.next().isValidNode()){
                    thispos = thispos.next();
                  }
                if(spos.next().isValidNode()){
                    spos = spos.next();
                  }
                length--; //because we incremented twice
                
            }
            length--;
          } 
          catch(InvalidNodeException e1){
              length = 0;
          }
      }
      
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    // Replace the following line with your solution.
     String setstring = "{ ";
     int cardin = cardinality();
     ListNode pos = myList.front();
     while(cardin > 0)
     {
        try{
            setstring += pos.item() + " ";
            pos = pos.next();
            cardin--;
        }
        catch(InvalidNodeException e1){
            
        }
        
     }
    return setstring + "}";
  }

  public static void main(String[] argv) {
    Set s = new Set();
     //mytest
    s.insert(new Integer(5));
    System.out.println("Set s = " + s);
    s.insert(new Integer(9));
    System.out.println("Set s = " + s);
    s.insert(new Integer(10));
    System.out.println("Set s = " + s);
    
    s.insert(new Integer(3));
    System.out.println("Set s = " + s);
    s.insert(new Integer(4));
    System.out.println("Set s = " + s);
    s.insert(new Integer(3));
    System.out.println("Set s = " + s);

    Set s2 = new Set();
    s2.insert(new Integer(4));
    s2.insert(new Integer(5));
    s2.insert(new Integer(5));
    s2.insert(new Integer(2));
    s2.insert(new Integer(9));
    s2.insert(new Integer(1));
    s2.insert(new Integer(25));
    s2.insert(new Integer(26));
    s2.insert(new Integer(27));
    s2.insert(new Integer(28));
    s2.insert(new Integer(29));

    System.out.println("Set s2 = " + s2);

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    s3.insert(new Integer(25));
    System.out.println("Set s3 = " + s3);
    
    
   
    
    s.union(s2);
    System.out.println("After s.union(s2), s = " + s);

    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);

    System.out.println("s.cardinality() = " + s.cardinality());
    // You may want to add more (ungraded) test code here.
    
    
  }
}
