/* 
Use a buffer and not an array or arraylist
- Buffer allow for synchronization of the producer and consumer threads 
- Buffer - thread safe 
- Array and arraylist are also not threadsafe 
(look back at lecture for this)
*/

/*
String stream - treat string as a file content
stream is a continuous collection of entities
Don't necessarily need to use Scanner on a string
but want to use Scanner on a file 
*/


import java.util.*;
import java.io.*;

public class CS3913Spring2020Files{
    static void printFiles(File cwd){
        File[] dirlist = cwd.listFiles();
        for (File f: dirList){
            if (f.isDirectory()){
                printFiles(f) // recursion is a beautifull thin
            } else{
                // System.out.println(f.getName());
                System.out.println(f.getAbsolutePath());
            }
        }
    }

    public static void main(String[] args){
        // designed to allow you to print out bytes -- dataoutput
        try{
            int x = 512; // nothing
            x = 97; // writes out a ?? --> writes a byte and it corresponds to ascii a
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File("DOSOut.txt"))); // for binary files for example
            PrintStream ps = new PrintStream(new File ("PSOut.txt"));
            ps.println(x);
            dos.write(x);
        }
        catch(FileNotFoundException ex){

        }
        catch(IOException ex){

        }
        // try{
        //     URL url = new URL("http://finance.yahoocom/quote/AMZN");
        //     URLConnection conn = url.openConnection();
        //     Scanner in = new Scanner(conn.getInputStream());
        //     PrintStream outFile = new PrintStream("webpage.txt");
        //     while (in.hasNext()){
        //         outFile.println(in.nextLine());
        //     }
        //     outFile.close()
        // }
        // catch(MalformedURLException ex){
        //     System.out.println("ERR: MALFORMEDURL");
        // }
        // catch(IOException ex){
        //     System.out.println("ERR: IOEXCEPTION");
        // }
        
        // File dot = new File(".");
        // // File dot = new File("..\\..\\..\\..\\..\\"); // security is an issue  when you allow somebody to .. to a folder they shouldnt be accessing
        // printFiles(dot);

        // SECOND PART OF CLASS
        // try{
        //     HashMap<Integer, String> h = new HashMap<Integer, String>();
        //     Scanner inFile = new Scanner(new File("idinfo.txt"));
        //     while (inFile.hasNext()){
        //         h.put(inFile.nextInt(), inFile.next())
                // next will give back a complete string
        //     }
        //     Set<Integer> s = h.keySet();
        //     // will overwrite, doesn't append
        //     PrintStream outFile = new PrintStream("output.txt");
        //     for (Integer i: s){
        //         // System.out.println(i + ": " + h.get(i));
        //         outFile.println(i + ", " + h.get(i));
        //     }
        // }
        // catch (FileNotFoundException ex){}

        // FIRST PART OF CLASS 
        // text file may have ints and strings but ... we don't want to split to get the individual parts
        // File f = new File("myfile.txt");
        // file exists in the directory of the root of the whole project
        // if (f.exists()){
        //     System.out.println("Yep, found the file!");
        //     System.out.println("it's contents are: ")
        //     try{
        //         Scanner inFile = new Scanner(f);
        //         while(inFile.hasNext()){
        //             System.out.println(inFile.nextLine());
        //         }
        //     } catch (FileNotFoundException ex) {}
        // }
        // else{
        //     System.out.println("404: FILE NOT FOUND!");
        // }
    }
}
