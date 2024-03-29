/* Ocean.java */

/**
 *  The Ocean class defines an object that models an ocean full of sharks and
 *  fish.  Descriptions of the methods you must implement appear below.  They
 *  include a constructor of the form
 *
 *      public Ocean(int i, int j, int starveTime);
 *
 *  that creates an empty ocean having width i and height j, in which sharks
 *  starve after starveTime timesteps.
 *
 *  See the README file accompanying this project for additional details.
 */

public class Ocean {

  /**
   *  Do not rename these constants.  WARNING:  if you change the numbers, you
   *  will need to recompile Test4.java.  Failure to do so will give you a very
   *  hard-to-find bug.
   */

  public final static int EMPTY = 0;
  public final static int SHARK = 1;
  public final static int FISH = 2;

  /**
   *  Define any variables associated with an Ocean object here.  These
   *  variables MUST be private.
   */
  private int width, height, starvetime;
  private int[][] thisocean, oceansharktime;
  

  /**
   *  The following methods are required for Part I.
   */

  /**
   *  Ocean() is a constructor that creates an empty ocean having width i and
   *  height j, in which sharks starve after starveTime timesteps.
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   */

  public Ocean(int i, int j, int starveTime) {
    // Your solution here.
      width = i;
      height = j;
      starvetime = starveTime;
      thisocean = new int[width][height];
      oceansharktime = new int[width][height];
  }

  /**
   *  width() returns the width of an Ocean object.
   *  @return the width of the ocean.
   */

  public int width() {
    // Replace the following line with your solution.
    return width;
  }

  /**
   *  height() returns the height of an Ocean object.
   *  @return the height of the ocean.
   */

  public int height() {
    // Replace the following line with your solution.
    return height;
  }

  /**
   *  starveTime() returns the number of timesteps sharks survive without food.
   *  @return the number of timesteps sharks survive without food.
   */

  public int starveTime() {
    // Replace the following line with your solution.
    return starvetime;
  }

  /**
   *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
   *  cell is already occupied, leave the cell as it is.
   *  @param x is the x-coordinate of the cell to place a fish in.
   *  @param y is the y-coordinate of the cell to place a fish in.
   */

  public void addFish(int x, int y) {
    // Your solution here.
      if(cellContents(x,y)==EMPTY)
      {
      int a = modulo(x,width);
      int b = modulo(y,height);
      thisocean[a][b] = FISH;
      }
  }

  /**
   *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
   *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
   *  just eaten.  If the cell is already occupied, leave the cell as it is.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   */

  public void addShark(int x, int y) {
    // Your solution here.
      if(cellContents(x,y)==EMPTY)
      {
      int a = modulo(x,width);
      int b = modulo(y,height);
      thisocean[a][b] = SHARK;
      oceansharktime[a][b]=starvetime;
      }
  }

  /**
   *  cellContents() returns EMPTY if cell (x, y) is empty, FISH if it contains
   *  a fish, and SHARK if it contains a shark.
   *  @param x is the x-coordinate of the cell whose contents are queried.
   *  @param y is the y-coordinate of the cell whose contents are queried.
   */

  public int cellContents(int x, int y) {
    // Replace the following line with your solution.
    int a = modulo(x,width);
      int b = modulo(y,height);
    return thisocean[a][b];
  }

  /**
   *  timeStep() performs a simulation timestep as described in README.
   *  @return an ocean representing the elapse of one timestep.
   */

  public Ocean timeStep() {
    // Replace the following line with your solution.
    Ocean newocean = new Ocean(width, height, starvetime);
    for(int i = 0; i<width; i++)
    {
        for(int j = 0; j<height; j++)
        {
            int content = this.cellContents(i,j);
            
            //FISH CONDITIONS 
            if(content==FISH)
            {
                newocean.thisocean[i][j]=fishoutcome(i,j);
                if(newocean.thisocean[i][j]==SHARK)
                {
                    newocean.oceansharktime[i][j]=starvetime;
                }
            }
            
            
            //SHARK CONDITIONS
            else if(content==SHARK)
            {
                if(anyfish(i,j)==true)
                {
                   newocean.oceansharktime[i][j] = starvetime; //they're full!
                   newocean.thisocean[i][j]=SHARK;
                    
                    
                }
                else{
                    int hunger = oceansharktime[i][j]-1;
                    newocean.oceansharktime[i][j]= hunger;
                   newocean.thisocean[i][j]=SHARK;
                    if(hunger < 0)
                    {
                        newocean.thisocean[i][j]=EMPTY;
                    }
                }
            }
            
            else if(content==EMPTY)
            {
                newocean.thisocean[i][j]=emptyoutcome(i,j);
                if(newocean.thisocean[i][j]==SHARK)
                {
                    newocean.oceansharktime[i][j]=starvetime;
                }
            }
        }
    }
    
    return newocean;
  }
  private int modulo(int num, int mod)
  {
      if(num<0)
      {
          return modulo(num+mod, mod);
      }
      return num % mod;
  }
  
  private int emptyoutcome(int i, int j)
  {
      int fishcount=0, sharkcount = 0;
      for(int a = i-1; a<i+2; a++) //checking 1 row out
          {
              for(int b = j-1; b<j+2; b++)
              {
                 if(thisocean[modulo(a,width)][modulo(b,height)]==FISH)
                 {
                    fishcount++;
                 }
                 if(thisocean[modulo(a,width)][modulo(b,height)]==SHARK)
                 {
                     sharkcount++;
                 }
              }
          }
      if(fishcount < 2)
      {
          return EMPTY;
      }
      if(fishcount >= 2 && sharkcount<=1)
      {
          return FISH;
      }
      if(fishcount >= 2 && sharkcount >=2)
      {
          return SHARK;
      }
      System.out.println("ERRRRRORRRR");
      return 3;
  }
  
  private boolean anyfish(int i, int j)
  {
      int fishcount=0;
      for(int a = i-1; a<i+2; a++) //checking 1 row out
      {
              for(int b = j-1; b<j+2; b++)
              {
                 if(thisocean[modulo(a,width)][modulo(b,height)]==FISH)
                 {
                    fishcount++;
                 }
              }
      }
      if(fishcount >= 1)
      {
          return true;
      }
      else{
          return false;
      }
  }
  
  
  private int fishoutcome(int i, int j)
  {
      int fishcount=-1, sharkcount = 0; //this method will always count itself, the fish once
      
     
      for(int a = i-1; a<i+2; a++) //checking 1 row out
          {
              for(int b = j-1; b<j+2; b++)
              {
                 if(thisocean[modulo(a,width)][modulo(b,height)]==FISH)
                 {
                    fishcount++;
                 }
                 if(thisocean[modulo(a,width)][modulo(b,height)]==SHARK)
                 {
                     sharkcount++;
                 }
              }
          }
      
      if(sharkcount==0)
      {
          return FISH;
      }
      if(sharkcount>=2)
      {
          return SHARK;
      }
      return EMPTY; //if sharkcount ==1
  }

  /**
   *  The following method is required for Part II.
   */

  /**
   *  addShark() (with three parameters) places a shark in cell (x, y) if the
   *  cell is empty.  The shark's hunger is represented by the third parameter.
   *  If the cell is already occupied, leave the cell as it is.  You will need
   *  this method to help convert run-length encodings to Oceans.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   *  @param feeding is an integer that indicates the shark's hunger.  You may
   *         encode it any way you want; for instance, "feeding" may be the
   *         last timestep the shark was fed, or the amount of time that has
   *         passed since the shark was last fed, or the amount of time left
   *         before the shark will starve.  It's up to you, but be consistent.
   */

  public void addShark(int x, int y, int feeding) {
    // Your solution here.
      this.thisocean[modulo(x,width)][modulo(y,height)]=SHARK;
      this.oceansharktime[modulo(x,width)][modulo(y,height)]=feeding;
  }

  /**
   *  The following method is required for Part III.
   */

  /**
   *  sharkFeeding() returns an integer that indicates the hunger of the shark
   *  in cell (x, y), using the same "feeding" representation as the parameter
   *  to addShark() described above.  If cell (x, y) does not contain a shark,
   *  then its return value is undefined--that is, anything you want.
   *  Normally, this method should not be called if cell (x, y) does not
   *  contain a shark.  You will need this method to help convert Oceans to
   *  run-length encodings.
   *  @param x is the x-coordinate of the cell whose contents are queried.
   *  @param y is the y-coordinate of the cell whose contents are queried.
   */

  public int sharkFeeding(int x, int y) {
    // Replace the following line with your solution.
     if(this.thisocean[modulo(x,width)][modulo(y,height)]!=SHARK)
     {
         return 0;
     }
     else{
         return oceansharktime[modulo(x,width)][modulo(y,height)];
     }
    //return 0;
  }

}
