package gubenia;

/* Class for items to be inserted and extracted from the Queue
 */
public class Config
   {

   private static final int N = 3;
   // size of the puzzle

   private byte[][] board;
   // array of piece locations

   private int prev;
   // index of previous

   private byte blank;
   // index of blank

   // constructor sets the fields according to the passed in arguments

   public Config( byte[][] position, int p, byte space )
 {

      board = new byte[N][N];

      for (int i=0; i < N; i++)

         for (int j=0; j < N; j++)

            board[i][j] = position[i][j];

            prev = p;

            blank = space;

      }

   // getters for all the class fields

   public int getPrev() { return prev; }

   public byte[][] getBoard() { return board; }

   public byte getBlank() { return blank; }

   }

