/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes an
 *  Ocean object.  Descriptions of the methods you must implement appear below.
 *  They include constructors of the form
 *
 *      public RunLengthEncoding(int i, int j, int starveTime);
 *      public RunLengthEncoding(int i, int j, int starveTime,
 *                               int[] runTypes, int[] runLengths) {
 *      public RunLengthEncoding(Ocean ocean) {
 *
 *  that create a run-length encoding of an Ocean having width i and height j,
 *  in which sharks starve after starveTime timesteps.
 *
 *  The first constructor creates a run-length encoding of an Ocean in which
 *  every cell is empty.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts an Ocean object into a run-length encoding of that object.
 *
 *  See the README file accompanying this project for additional details.
 */

public class RunLengthEncoding {

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */


private int arraylen, runpointer = 0;


private DList1 rle;
  /**
   *  The following methods are required for Part II.
   */
  /**
   *  RunLengthEncoding() (with three parameters) is a constructor that creates
   *  a run-length encoding of an empty ocean having width i and height j,
   *  in which sharks starve after starveTime timesteps.
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   */

  public RunLengthEncoding(int i, int j, int starveTime) {
    // Your solution here.
      rle = new DList1(i, j, starveTime);
      rle.insertFront(Ocean.EMPTY, i*j, starveTime);
      arraylen = 1;
      
      
  }
  
  /**
   *  RunLengthEncoding() (with five parameters) is a constructor that creates
   *  a run-length encoding of an ocean having width i and height j, in which
   *  sharks starve after starveTime timesteps.  The runs of the run-length
   *  encoding are taken from two input arrays.  Run i has length runLengths[i]
   *  and species runTypes[i].
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   *  @param runTypes is an array that represents the species represented by
   *         each run.  Each element of runTypes is Ocean.EMPTY, Ocean.FISH,
   *         or Ocean.SHARK.  Any run of sharks is treated as a run of newborn
   *         sharks (which are equivalent to sharks that have just eaten).
   *  @param runLengths is an array that represents the length of each run.
   *         The sum of all elements of the runLengths array should be i * j.
   */

  public RunLengthEncoding(int i, int j, int starveTime,
                           int[] runTypes, int[] runLengths) {
    rle = new DList1(i, j, starveTime);
    
    for(int a = 0; a<runTypes.length; a++)
    {
        rle.insertEND(runTypes[a], runLengths[a], starveTime);
       
    }
    arraylen = runTypes.length;
    
    
  }

  /**
   *  restartRuns() and nextRun() are two methods that work together to return
   *  all the runs in the run-length encoding, one by one.  Each time
   *  nextRun() is invoked, it returns a different run (represented as an
   *  array of two ints), until every run has been returned.  The first time
   *  nextRun() is invoked, it returns the first run in the encoding, which
   *  contains cell (0, 0).  After every run has been returned, nextRun()
   *  returns null, which lets the calling program know that there are no more
   *  runs in the encoding.
   *
   *  The restartRuns() method resets the enumeration, so that nextRun() will
   *  once again enumerate all the runs as if nextRun() were being invoked for
   *  the first time.
   *
   *  (Note:  Don't worry about what might happen if nextRun() is interleaved
   *  with addFish() or addShark(); it won't happen.)
   */

  /**
   *  restartRuns() resets the enumeration as described above, so that
   *  nextRun() will enumerate all the runs from the beginning.
   */

  public void restartRuns() {
    // Your solution here.
      runpointer = 0;
  }

  /**
   *  nextRun() returns the next run in the enumeration, as described above.
   *  If the runs have been exhausted, it returns null.  The return value is
   *  an array of two ints (constructed here), representing the type and the
   *  size of the run, in that order.
   *  @return the next run in the enumeration, represented by an array of
   *          two ints.  The int at index zero indicates the run type
   *          (Ocean.EMPTY, Ocean.SHARK, or Ocean.FISH).  The int at index one
   *          indicates the run length (which must be at least 1).
   */

  public int[] nextRun() {
    // Replace the following line with your solution.
      DListNode1 pos = rle.head;
      if(runpointer >= arraylen)
      {
          
          return null;
      }
      
      for(int i = 0; i<runpointer; i++)
      {
          pos = pos.next;
      }
      
      runpointer++;
      
      int[] run = {pos.item[0], pos.item[1]};
      
    return run;
  }

  /**
   *  toOcean() converts a run-length encoding of an ocean into an Ocean
   *  object.  You will need to implement the three-parameter addShark method
   *  in the Ocean class for this method's use.
   *  @return the Ocean represented by a run-length encoding.
   */

  public Ocean toOcean() {
    // Replace the following line with your solution.
      Ocean translate = new Ocean(rle.width, rle.height, rle.starveTimer);
      int translatei =0, translatej=0, occurences, obj, starve;
      DListNode1 pos = rle.head;
      
      for(int i = 0; i<rle.size; i++)
      {
          occurences = pos.item[1]; //length
          obj = pos.item[0];        //obj
          starve = pos.item[2];     //starve
          
          for(int j = occurences; j>0; j--)
          {
              if(obj == Ocean.FISH)
              {
                  translate.addFish(translatei, translatej);
              }
              
              if(obj == Ocean.SHARK)
              {
                  translate.addShark(translatei, translatej, starve);
              }
              //what if obj is empty?
              translatei++;
              if(translatei%rle.width==0)
              {
                  translatej++;
              }
          }
          pos = pos.next;
      }
      
    return translate;
  }

  /**
   *  The following method is required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of an input Ocean.  You will need to implement
   *  the sharkFeeding method in the Ocean class for this constructor's use.
   *  @param sea is the ocean to encode.
   */

  public RunLengthEncoding(Ocean sea) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
      int oldobj, newobj, occurences = 0, oldstarveduration=sea.sharkFeeding(0,0),newstarveduration, h=-1;
      oldobj = sea.cellContents(0, 0);
      int[] RunTypes = new int[sea.height() * sea.width()], RunLengths= new int[sea.height() * sea.width()];
      int RLEposition=0;
      int[] sharktimers = new int[sea.height() * sea.width()];
      
      int runs = 0;
      for(int i = 0; i<sea.height() * sea.width(); i++)
      {
          if(i%sea.width()==0)
          {
              h++;
          }
          
          newobj = sea.cellContents(i, h);
          newstarveduration = sea.sharkFeeding(i,h);
          
          if(newobj==oldobj && newstarveduration==oldstarveduration)
          {
              occurences++;
          }
          else{
              
              runs++;
              RunTypes[RLEposition]=oldobj;
              RunLengths[RLEposition]=occurences;
              sharktimers[RLEposition]=oldstarveduration;
              //System.out.println(occurences);
              oldobj = newobj;
              occurences = 1;
              RLEposition++;
              oldstarveduration = newstarveduration;
          }
              
      }
      //HANDLING LAST RUN
      RunTypes[RLEposition]=oldobj;
      RunLengths[RLEposition]=occurences;
      sharktimers[RLEposition]=oldstarveduration;
      runs++;
      
      rle = new DList1(sea.width(), sea.height(), sea.starveTime());
      for(int j = 0; j<runs; j++)
      {
          rle.insertEND(RunTypes[j], RunLengths[j], sharktimers[j]);
      }
      arraylen = runs;
    check();
    
  }

  /**
   *  The following methods are required for Part IV.
   */

  /**
   *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
   *  cell is already occupied, leave the cell as it is.  The final run-length
   *  encoding should be compressed as much as possible; there should not be
   *  two consecutive runs of sharks with the same degree of hunger.
   *  @param x is the x-coordinate of the cell to place a fish in.
   *  @param y is the y-coordinate of the cell to place a fish in.
   */

  public void addFish(int x, int y) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
    DListNode1 pos = rle.head;
    int position = x + rle.width * y;
    int positionchecking = 0;
    for(int i = 0; i < rle.size; i++)
    {
        if(positionchecking==position && pos.item[0]==Ocean.EMPTY) //START OF RUN OF EMPTY (OR EMPTYlength=1)
        {
            //CHECK OF FIRST ELEMENT (HEAD) IS DONE IN (IF POSITIONCHECKINGN == POSITION + 1)
            if(i>0 && pos.prev.item[0]==Ocean.FISH) //IF ITEM BEFORE IS RUN OF FISH FFFEEEEE
            {
                if(pos.item[1]==1) //IF EMPTY RUN WILL DISAPPEAR
                {
                    
                    
                    
                    if(pos == rle.tail)
                    {
                        //CUT OUT THE LAST ELEMENT
                        pos.prev.item[1] = pos.prev.item[1]+1; //increase run of fish by 1
                        rle.tail = pos.prev;
                        rle.tail.next = null;
                        arraylen = arraylen - 1;
                        break;
                    }
                    
                    //BUT WHAT IF ITEM BEFORE ANNND AFTER ARE FISH
                    if(pos.prev.item[0]==Ocean.FISH && pos.next.item[0]==Ocean.FISH)
                    {
                        DListNode1 interm = new DListNode1(Ocean.FISH, 1+pos.prev.item[1]+pos.next.item[1], 0);
                        

                        pos.prev.prev.next = interm;
                        interm.prev = pos.prev.prev;
                        interm.next = pos.next.next;
                        arraylen = arraylen - 2;
                        
                        
                    }
                    
                    else
                    {
                        //CUT OUT THE LENGTH 1 EMPTY RUN
                        pos.prev.item[1] = pos.prev.item[1]+1; //increase run of fish by 1
                        pos.next.prev = pos.prev;
                        pos.prev.next = pos.next;
                        arraylen = arraylen -1;
                        break;
                    }
                    
                    
                }
                
                else{
                    pos.prev.item[1] = pos.prev.item[1]+1; //increase run of fish by 1
                    pos.item[1] = pos.item[1]-1;            //decrease run of empty by 1
                    break;
                }
                
                
            }
            else{//IF ITEM BEFORE IS SHARK (OR IS FIRST ITEM)

                if(pos.item[1]==1) //IF EMPTY RUN WILL DISAPPEAR
                {
                    if(pos == rle.tail) //IF EMPTY RUN IS TAIL
                    {
                        //CUT OUT THE LAST ELEMENT, REPLACE IT WITH A 1 LENGTH FISH
                        rle.tail = new DListNode1(Ocean.FISH, 1, 0); //ADD A FISH TO THE END
                        rle.tail.next = null;                       //ITS THE END
                        rle.tail.prev = pos.prev;                   //MAKE SURE THE PREV POINTS RIGHT
                        
                        pos.prev.next = rle.tail;
                        break;
                    }
                    else if(pos.next.item[0]!=Ocean.FISH)
                    {
                        //CUT OUT THE LENGTH 1 EMPTY RUN, REPLACE WITH LENGTH 1 FISH
                        pos.item[0] = Ocean.FISH;
                        pos.item[1] = 1;
                        pos.item[2] = 0;
                        break;
                    }
                    else{//LENGTH 1 EMPTY FOLLOWED BY A FISH
                        pos.next.item[1]=pos.next.item[1]+1; //EXTEND BY 1
                        pos.prev.next = pos.next;
                        pos.next.prev = pos.prev;
                        break;
                    }
                    
                }
                else{  //EMPTY RUN WONT DISSAPEAR
                    DListNode1 interm = new DListNode1(Ocean.FISH, 1, 0);
                    pos.prev.next = interm;
                    interm.next = pos;
                    interm.prev = pos.prev;
                    pos.item[1] = pos.item[1]-1;
                    arraylen = arraylen +1;
                    break;
                }

            }
        }
        
        if(positionchecking==position+1) //IF POSITION IS END OF RUN OF EMPTY
        {                               //dont need to check if empty ==1, cause that would be above
            
            if(pos.item[0]==Ocean.FISH)  //IF INSERT RIGHT BEFORE RUN OF FISH
            {
                pos.prev.item[1]=pos.prev.item[1]-1; //EMPTY RUN LOSES 1 LENGTH
                pos.item[1] = pos.item[1]+1;     //FISH RUN GAINS 1 LENGTH
                //DONT NEED TO CHANGE arraylen
                break;
            }
            else{ //IF INSERT RIGHT BEFORE RUN OF SHARK
                pos.prev.item[1] -= 1; //reduce length of empty by 1
                DListNode1 interm = new DListNode1(Ocean.FISH, 1, 0);   //insert fish
                pos.prev.next = interm; //before point to fish
                interm.next = pos;      //set fish prev/next
                interm.prev = pos.prev;
                pos.prev = interm;
                arraylen = arraylen +1;
               break;
            }
        }
        
        
        if(positionchecking > position) //IF POSITION IS In the MIDDLE OF A RUN OF EMPTY
        {
            int start = positionchecking - pos.prev.item[1];
            int end = positionchecking - 1;
            int partonelength = position - start;
            int parttwolength = end - position;
            
            DListNode1 fishy = new DListNode1(Ocean.FISH, 1, 0);
            
            DListNode1 partone = new DListNode1(pos.prev.item[0], partonelength, 0);
            pos.prev.prev.next = partone;
            partone.prev = pos.prev.prev;
            partone.next = fishy;
            
            DListNode1 parttwo = new DListNode1(pos.prev.item[0], parttwolength, 0);
            parttwo.prev = fishy;
            parttwo.next = pos;
            pos.prev = parttwo;
            
            fishy.prev = partone;
            fishy.next = partone;
            arraylen = arraylen +2;
            
            break;
        }
        
        positionchecking += pos.item[1];
        pos = pos.next;
    }
      
    check();
  }

  /**
   *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
   *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
   *  just eaten.  If the cell is already occupied, leave the cell as it is.
   *  The final run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs of sharks with the same degree
   *  of hunger.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   */

  public void addShark(int x, int y) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
   
    DListNode1 pos = rle.head;
    int position = x + rle.width * y;
    int positionchecking = 0;
    for(int i = 0; i < rle.size; i++)
    {
        if(positionchecking==position && pos.item[0]==Ocean.EMPTY) //START OF RUN OF EMPTY (OR EMPTYlength=1)
        {
            //CHECK OF FIRST ELEMENT (HEAD) IS DONE IN (IF POSITIONCHECKINGN == POSITION + 1)
            if(i>0 && pos.prev.item[0]==Ocean.SHARK && pos.prev.item[2]==rle.starveTimer) //IF ITEM BEFORE IS RUN OF SHARKS w/ same hunger FFFEEEEE
            {                       
                if(pos.item[1]==1) //IF EMPTY RUN WILL DISAPPEAR
                {

                    if(pos == rle.tail)
                    {
                        //CUT OUT THE LAST ELEMENT
                        pos.prev.item[1] = pos.prev.item[1]+1; //increase run of sharks by 1
                        rle.tail = pos.prev;
                        rle.tail.next = null;
                        arraylen = arraylen -1;
                        break;
                    }
                    
                    //BUT WHAT IF ITEM BEFORE ANNND AFTER ARE SHARKS OF SAME HUNGER
                    if(pos.prev.item[0]==Ocean.SHARK && pos.next.item[0]==Ocean.SHARK && pos.prev.item[2]==rle.starveTimer && pos.next.item[2]==rle.starveTimer)
                    {
                        DListNode1 interm = new DListNode1(Ocean.SHARK, 1+pos.prev.item[1]+pos.next.item[1], 0);
                        

                        pos.prev.prev.next = interm;
                        interm.prev = pos.prev.prev;
                        interm.next = pos.next.next;
                        arraylen = arraylen - 2;
                        
                        
                    }
                    
                    
                    else
                    {
                        //CUT OUT THE LENGTH 1 EMPTY RUN
                        pos.prev.item[1] = pos.prev.item[1]+1; //increase run of sharks by 1
                        pos.next.prev = pos.prev;
                        pos.prev.next = pos.next;
                        arraylen = arraylen -1;
                        break;
                    }
                    
                }
                
                else{
                    pos.prev.item[1] = pos.prev.item[1]+1; //increase run of sharks w/ same hunger by 1
                    pos.item[1] = pos.item[1]-1;            //decrease run of empty by 1
                    break;
                }
                
                
            }
            else{//IF ITEM BEFORE IS FISH OR SHARK of DIFF Hunger (OR IS FIRST ITEM)

                if(pos.item[1]==1) //IF EMPTY RUN WILL DISAPPEAR
                {
                    if(pos == rle.tail) //IF EMPTY RUN IS TAIL
                    {
                        //CUT OUT THE LAST ELEMENT, REPLACE IT WITH A 1 LENGTH SHARK
                        rle.tail = new DListNode1(Ocean.SHARK, 1, rle.starveTimer); //ADD A SHARK TO THE END
                        rle.tail.next = null;                       //ITS THE END
                        rle.tail.prev = pos.prev;                   //MAKE SURE THE PREV POINTS RIGHT
                        
                        pos.prev.next = rle.tail;
                        arraylen = arraylen -1;
                        break;
                    }
                    else if(!( pos.next.item[0]==Ocean.SHARK && pos.next.item[2] == rle.starveTimer)) //if next element isnt shark of same hunger 
                    {
                        //CUT OUT THE LENGTH 1 EMPTY RUN, REPLACE WITH LENGTH 1 SHARK
                        pos.item[0] = Ocean.SHARK;
                        pos.item[1] = 1;
                        pos.item[2] = rle.starveTimer;
                        //arraylen = arraylen
                        
                        break;
                    }
                    else{//LENGTH 1 EMPTY FOLLOWED BY A ShARK OF SAME HUNGER
                        pos.next.item[1]=pos.next.item[1]+1; //EXTEND BY 1
                        pos.prev.next = pos.next;
                        pos.next.prev = pos.prev;
                        arraylen = arraylen - 1;
                        break;
                    }
                    
                }
                else{  //EMPTY RUN WONT DISSAPEAR
                    DListNode1 interm = new DListNode1(Ocean.SHARK, 1, rle.starveTimer);
                    pos.prev.next = interm;
                    interm.next = pos;
                    interm.prev = pos.prev;
                    pos.item[1] = pos.item[1]-1;
                    arraylen = arraylen +1;
                    break;
                }

            }
        }
        
        if(positionchecking==position+1) //IF POSITION IS END OF RUN OF EMPTY
        {
            if(pos.item[0]==Ocean.SHARK && pos.item[2]==rle.starveTimer)  //IF INSERT RIGHT BEFORE RUN OF SHARK OF SAME HUNGER
            {
                pos.prev.item[1]=pos.prev.item[1]-1; //EMPTY RUN LOSES 1 LENGTH
                pos.item[1] = pos.item[1]+1;     //SHARK RUN GAINS 1 LENGTH
                break;
            }
            else{ //IF INSERT RIGHT BEFORE RUN OF FISH
                pos.prev.item[1] -= 1; //reduce length of empty by 1
                DListNode1 interm = new DListNode1(Ocean.SHARK, 1, rle.starveTimer);   //insert shark
                pos.prev.next = interm; //before point to shark
                interm.next = pos;      //set fish prev/next
                interm.prev = pos.prev;
                pos.prev = interm;
                arraylen = arraylen +1;
               break;
            }
        }
        
        
        if(positionchecking > position) //IF POSITION IS In the MIDDLE OF A RUN OF EMPTY
        {
            int start = positionchecking - pos.prev.item[1];
            int end = positionchecking - 1;
            int partonelength = position - start;
            int parttwolength = end - position;
            
            DListNode1 fishy = new DListNode1(Ocean.SHARK, 1, rle.starveTimer);
            
            DListNode1 partone = new DListNode1(pos.prev.item[0], partonelength, 0); //0 b/c empty
            partone.prev = pos.prev.prev;
            partone.next = fishy;
            pos.prev.prev.next = partone;
            
            DListNode1 parttwo = new DListNode1(pos.prev.item[0], parttwolength, 0); //0 b/c empty
            pos.prev = parttwo;
            parttwo.prev = fishy;
            parttwo.next = pos;
            
            fishy.prev = partone;
            fishy.next = parttwo;
            arraylen = arraylen +2;
            
            break;
        }
        
        positionchecking += pos.item[1];
        pos = pos.next;
    }
      
    check();
  }

  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same contents, or if the sum of all run
   *  lengths does not equal the number of cells in the ocean.
   */

  private void check() {
      DListNode1 pos = rle.head;
      
      int sum = rle.head.item[1];
      
      if(arraylen < 1)
      {
          System.out.println("arraylen less than 1 man");
          return;
      }
      while(pos.next!=null)
      {
          pos = pos.next;
          sum+=pos.item[1];
          if(pos != rle.head)
          {
              if(pos.item[0] == pos.prev.item[0] && pos.item[2] == pos.prev.item[2])
              {
                  System.out.println("repeats");
              }
          }
          
      }
      
      if(sum!=rle.height*rle.width)
      {
          System.out.println("runlengths not same as size");
      }
      
      
  }

}
