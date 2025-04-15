package display;

import java.io.*;
public class ReadNumberData {

   static FileInputStream fin;
   static DataInputStream dis;
   public static void main(String args[]) {
      boolean bdata;
      double ddata;
      int number;
      try {
         fin = new FileInputStream("example_11.txt");
         dis = new DataInputStream(fin);
         bdata = dis.readBoolean();
         System.out.println(bdata);
         ddata = dis.readDouble();
         System.out.println(ddata);
         while(true) {
            number = dis.readInt();
            System.out.print(number+" ");
         }
      }catch(IOException e) {
         System.err.println(e);
      }
   }
}