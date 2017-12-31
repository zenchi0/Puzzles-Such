package gubenia;

public class EightPuzzle
   {

   private static final int N = 3;
   // size of the puzzle

   private static final int queueSize = 500000;
   // The size of the queue

   private Queue Q;
   // the Queue

   private static final byte[][] goal = { { 1, 2, 3 }, 
                                          { 4, 5, 6 },
                                          { 7, 8, 0 } };
  // the goal position

   // helper functions to get row and column of a blank at index "ndx"
   // and to get the blank's index from its row and col values.
   //----------------------------------------------------------------

   private int rowOf( byte ndx ) { return ndx / N; }

   private int colOf( byte ndx ) { return ndx % N; }

   private byte ndxOf( int r, int c ) { return (byte)(r*N + c); }
   
   private int nomoves;
   
   private int puzzlecnt;
   
   



   //+------------------------------+
   //| the top level solving method |
   //+------------------------------+

   public void solve( byte[][] init )
 {

      Q = new Queue( queueSize );

      byte blank = getBlank( init );
     // get the location of the initial blank
      

     // insert the initial config into the queue
     // ----------------------------------------

      Q.enqueue( new Config( init, -1, blank ) ); // enter new Config into queue
      nomoves = -1;
      puzzlecnt++;
      while(!Q.isEmpty()){
          Config currentConfig = Q.dequeue();
          
          // if current Config is the goal print the solution and return.
          if(!notEqual(currentConfig.getBoard(),goal)){
              printSolution(Q, init);
              break;
          }
          
          // while the currentConfig is not the goal perform moves
          // util the goal is reached
          
          while(notEqual(currentConfig.getBoard(), goal)){
              
                moveUp(currentConfig);
                currentConfig = Q.dequeue();                   
                moveDown(currentConfig);
                moveLeft(currentConfig);
                currentConfig = Q.peek();
                moveRight(currentConfig);
                   
          } // end while notEqual loop
          
      } // end while notEmpty loop
      
      System.out.println("No solution");
           
      } // end solve method


   //+--------------------------------------------------------+
   //| Search a position to find and return the blank's index |
   //+--------------------------------------------------------+

   private byte getBlank( byte[][] pos )
 {

      for (int i=0; i < N; i++)

         for (int j=0; j < N; j++)

            if (pos[i][j] == 0)

               return ndxOf( i, j );

      return -1;
   // should never happen.  Needed to make compiler happy.

      }



   //+---------------------+
   //| print out the board |
   //+---------------------+

   private void printBoard( byte[][] pos )
 {
     nomoves++;

      for (int i=0; i < N; i++)

         {

         for (int j=0; j < N; j++)

            System.out.print( pos[i][j] );

            System.out.println();
            

         }

      System.out.println();

      }

   //+---------------------------+
   //| Two-D array equality test |
   //+---------------------------+

   private boolean notEqual( byte[][] board1, byte[][] board2 )

      {

      for (int i=0; i < N; i++)

         for (int j=0; j < N; j++)

            if (board1[i][j] != board2[i][j]) return true;

      return false;

      }

   //+------------------------+
   //| Two-D array assignment |
   //+------------------------+

   private void arrayAssign( byte[][] to, byte[][] from )

      {

      for (int i=0; i < N; i++)

         for (int j=0; j < N; j++)

            to[i][j] = from[i][j];

      }
   
   // method to print Solution
   
   private void printSolution(Queue Q, byte[][] init){
       int i;
       for (i = 0; i <= Q.getCurrNdx(); i++)
       printBoard(Q.getBoard(i));
       System.out.println("puzzle" + puzzlecnt + ": solved in " + nomoves + " moves. \t" +
               Q.getTotalInsertions() + " positions generated.");
   }
   
   //+----------------------------------------------------------+
   // methods to move tiles                                     |
   //-----------------------------------------------------------+
   
   //check if move is on board
   
   // method to move the blank up
    
    private void moveUp(Config currentConfig){
        byte [][] newState = new byte [N][N];
        arrayAssign(newState, currentConfig.getBoard());
        int i1 = rowOf(getBlank(newState));
        int j1 = colOf(getBlank(newState));
        int i2 = rowOf(getBlank(newState))-1;
        int j2 = colOf(getBlank(newState));
        if (i1 > 0){
            byte temp = newState [i1][j1];
            newState [i1][j1] = newState [i2][j2];
            newState [i2][j2] = temp;
        }
        if (currentConfig.getPrev() == -1 || notEqual(newState,
                            Q.getBoard(currentConfig.getPrev())))
            Q.enqueue(new Config (newState,
                            Q.getCurrNdx(), getBlank(newState)));
    }
    
    //method to move the blank down
    
    private void moveDown(Config currentConfig){
        byte [][] newState = new byte [N][N];
        arrayAssign(newState, currentConfig.getBoard());
        int i1 = rowOf(getBlank(newState));
        int j1 = colOf(getBlank(newState));
        int i2 = rowOf(getBlank(newState))+1;
        int j2 = colOf(getBlank(newState));
        if (i1 < 2){
            byte temp = newState[i1][j1];
            newState [i1][j1] = newState [i2][j2];
            newState [i2][j2] = temp;
        }
        if (currentConfig.getPrev() == -1 || notEqual(newState,
                            Q.getBoard(currentConfig.getPrev())))
              Q.enqueue(new Config (newState,
                            Q.getCurrNdx(), getBlank(newState)));
    }
    
    // method to move the blank to the left
    
    private void moveLeft(Config currentConfig){
        byte [][] newState = new byte [N][N];
        arrayAssign(newState, currentConfig.getBoard());
        int i1 = rowOf(getBlank(newState));
        int j1 = colOf(getBlank(newState));
        int i2 = rowOf(getBlank(newState));
        int j2 = colOf(getBlank(newState))-1;
        if (j1 > 0){
            byte temp = newState [i1][j1];
            newState[i1][j1] = newState [i2][j2];
            newState[i2][j2] = temp;
        }
         if (currentConfig.getPrev() == -1 || notEqual(newState,
                            Q.getBoard(currentConfig.getPrev())))
             Q.enqueue(new Config (newState,
                            Q.getCurrNdx(), getBlank(newState)));
    }
    
    // method to move the blank to the right
    
    private void moveRight(Config currentConfig){
        byte [][] newState = new byte [N][N];
        arrayAssign(newState, currentConfig.getBoard());
        int i1 = rowOf(getBlank(newState));
        int j1 = colOf(getBlank(newState));
        int i2 = rowOf(getBlank(newState));
        int j2 = colOf(getBlank(newState))+1;
        if (j1 < 2){
            byte temp = newState[i1][j1];
            newState [i1][j1] = newState [i2][j2];
            newState [i2][j2] = temp;      
        }
        if (currentConfig.getPrev() == -1 || notEqual(newState,
                            Q.getBoard(currentConfig.getPrev())))
               Q.enqueue(new Config (newState,
                            Q.getCurrNdx(), getBlank(newState)));
    }
    

   //+------------------------------------------------------------------+
   //| Tester which defines some positions and calls the solver on them |
   //+------------------------------------------------------------------+

   public static void main( String[] args )

      {

      EightPuzzle puzzle = new EightPuzzle();


      byte[][] puzzle1 = { { 1, 2, 3 },
                           { 4, 6, 8 },
                           { 7, 5, 0 }};


      byte[][] puzzle2 = { { 1, 2, 3 },
                           { 4, 5, 6 },
                           { 7, 8, 0 } };


      byte[][] puzzle3 = { { 4, 8, 2 },
                           { 7, 1, 3 },
                           { 5, 0, 6 } };


     puzzle.solve( puzzle1 );
   // an easy puzzle with a 4 move solution


     puzzle.solve( puzzle2 );
   // a special case -- no move solution!

    puzzle.solve( puzzle3 );
   // a harder puzzle

      }

   }
