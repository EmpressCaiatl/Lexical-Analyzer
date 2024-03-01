import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static String theString = ""; //for reading from file
    public static char[] charArray = new char[0]; //for storing the array of chars for lexing

    public static void main(String[] args) throws IOException {
        Scanner scanThis = new Scanner(System.in); //for reading user console input

        //takes user folder name and finds the path (should be in the src folder)
        String pathStart = args[0];
        File folder = new File(pathStart);
        String canonicalPath = folder.getCanonicalPath();

        System.out.println("Path given: " + canonicalPath);
        System.out.println("Is this the correct test folder path? y/n ");

        String userInput = scanThis.nextLine();

        //manual check for folder issues
        while (userInput.equals("n") || userInput.equals("N")){

            System.out.println("Paste full test folder path here: ");
            canonicalPath = scanThis.nextLine();

            System.out.println("Path given: " + canonicalPath);
            System.out.println("Is this the correct test folder path? y/n ");
            userInput = scanThis.nextLine();

        }

        File source = new File(canonicalPath);

        //grab all files from folder
        //and output check
        //to ensure file reading
        try{
            File sourceFolder = new File (String.valueOf(source));
            String fileExt;

            for(File sourceFile : sourceFolder.listFiles()){        //reads files within folder one by one

                String fileName = sourceFile.getName();             //grabs file name
                fileExt = fileName.substring(fileName.lastIndexOf(".")+1); //checks file type

                if(fileExt.equalsIgnoreCase("txt")) {
                    System.out.println("\n"+"We have read " + fileName + " successfully. Now lexing." + "\n");

                    String fullPath = canonicalPath + "\\" + fileName;
                    File readThis = new File(fullPath);             //sets the full path for reading

                    Scanner scanFile = new Scanner(readThis);       //read the file
                    theString = scanFile.nextLine();           //to a string

                    while(scanFile.hasNextLine()){
                        theString = theString + "\n" + scanFile.nextLine();
                    }

                    charArray = theString.toCharArray();  //converts group of strings to chars
                    scanFile.close();

                    //call the lexer to begin lexing the file
                    Lexer lexer = new Lexer(theString);
                    lexer.scanTokens();
                    Lexer.x = 0; //reset lexer category numbering

                }else {//unsupported
                    System.out.println("\n"+"Filename extension \"" + fileName + "\" not supported");
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}








