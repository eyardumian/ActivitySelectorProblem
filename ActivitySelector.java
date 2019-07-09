import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;


/**
 * Programs to solve the Activity Selector problem.
 * 
 * @author Erika Yardumian 
 * @version 6/26/19
 */
 
public class ActivitySelector {

   public static void main(String[] args)
   {
      StudyOverhead(2000, 1000);
   }
    
   public static int[] RecursiveActivitySelector(int[] s, int[] f, int k, int n, int[] A) {
      int m = k + 1;
      while (m <= n && s[m] <= f[k]) {
         m++;
      }
      if (m <= n) {
         A[m] = 1;
         RecursiveActivitySelector(s, f, m, n, A);
         return A;
      }
      else {
         return null;
      } 
   }
   
   public static int[] GreedyActivitySelector(int[] s,int[] f, int n, int[] A) {
      A[0] = 1;
      int k = 1;
      for (int m = 2; m < n; m++) {
         if (s[m] >= f[k]) {
            A[m] = 1;
            k = m;
         }
      }
      return A;
      //System.out.println(A);
   }  
   
   public static void StudyOverhead(int NumberPoints, int NumberRuns) {
      File F = new File("Times2.txt");
      try {
         PrintWriter printWriter = new PrintWriter(F);
      
         int[] s = new int[NumberPoints];
         int[] f = new int[NumberPoints];
         InitializeArrays(NumberPoints, s, f);
                  
         for (int i = 100; i < NumberPoints; i++) {
            long TimeRecursive = 0;
            long TimeIterative = 0;
            for (int j = 1; j < NumberRuns; j++) {
               int[] A = new int[i];
               long recursiveStartTime;
               long recursiveEndTime;
            
               recursiveStartTime = System.nanoTime();
               RecursiveActivitySelector(s, f, 0, i - 1, A);
               recursiveEndTime = System.nanoTime();
               TimeRecursive += recursiveEndTime - recursiveStartTime;
               System.out.println(TimeRecursive);
               
               long iterativeStartTime;
               long iterativeEndTime;
               
               iterativeStartTime = System.nanoTime();
               GreedyActivitySelector(s, f, i - 1, A);
               iterativeEndTime = System.nanoTime();
               TimeIterative += iterativeEndTime - iterativeStartTime;
               System.out.println(TimeIterative);
            }
            printWriter.println(i + ", " + (float)TimeRecursive/ (float)TimeIterative);
         }
         printWriter.close();
      }
      catch (FileNotFoundException ex) {
         System.out.println("File not found.");
      }    
   }
      
   public static void InitializeArrays(int n, int[] s, int[] f) {
      s[0] = 0;
      f[0] = 0;
      for (int i = 1; i < n; i++) {
         if (i % 2 == 0) {
            s[i] = f[i - 2];
            f[i] = s[i] + 2;
         }
         else {
            s[i] = f[i - 1] -1;
            f[i] = f[i - 1] + 1;
         }
      }
   }
}
