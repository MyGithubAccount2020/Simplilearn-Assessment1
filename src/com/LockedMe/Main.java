package com.LockedMe;

import java.io.IOException;
import java.nio.file.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
class FileManager {
    /**This method receives a path  and delete the file from the directory
     * before deleting the file it check if file exists or not
     * after deleting the file it checks availability of file and prints successful message if it doesn't exists
     *anymore and prints unsuccessful message if it still exists.
     * It throws IOException.
     * @param dir
     * @return
     */
    static  void deleteFile(String dir){
        String fileName = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your file to be deleted and press Enter: ");
        Path filePath = Paths.get(dir+scanner.nextLine());
        // check if the file exists or not
        if(Files.notExists(filePath)){
            System.out.println(" The file doesn't exist");
            scanner.close();
            return ;
        }else {

            try {
                Files.delete(filePath);
            } catch (NoSuchFileException x) {
                System.err.format("%s: no such" + " file or directory%n", filePath);
            } catch (DirectoryNotEmptyException x) {
                System.err.format("%s not empty%n", filePath);
            } catch (IOException x) {
                // File permission problems are caught here.
                System.err.println(x);
            }
        }
        scanner.close();
        if ( Files.notExists(filePath)){
            System.out.println("Successfully Deleted");
        }else {
            System.out.println("Not Deleted");
        }
    }

    /**This method receives a path and a file name and search the file from the directory and prints relevant message.
     * @param pathDirectory
     * @return
     */
    static  void searchFile(String pathDirectory){
        System.out.println("Directory content:");
        List<Path> list = new ArrayList<Path>();
        list=listingDirectory(pathDirectory);
        // print the list
        for (Path p: list
        ) {
            System.out.println(p.getFileName());
        }
        //ask user for file name.
        System.out.println("Enter file name and press Enter:");
        Scanner scanner = new Scanner(System.in);
        String f = scanner.nextLine();
        String pathString=pathDirectory+f;
        Path file1 = Paths.get(pathString);


        for (Path p:list
        ) {
            if(p.getFileName().equals(file1.getFileName())){
                System.out.println("Found successfully !!!");
                scanner.close();
                return;
            }
        }
        System.out.println("file not found");
    }



    /**This method receives a path and a file name and Add the file to the directory
     * Before creating the file, it checks if the file already exits or not. If the file already exists it
     * print a message otherwise adds the files to the directory. At the end it checks availability of added file.
     * If it finds file in directory, it prints successful message otherwise prints unsuccessful message.
     * It throws IOException.
     * @param dir
     * @return
     */
    static void addFile(String dir){
        String fileName = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your file to be deleted and press Enter: ");
        Path filePath = Paths.get(dir+scanner.nextLine());
        // check if the file exists or not
        if(Files.exists(filePath)){
            System.out.println(" The file exist");
            scanner.close();
            return ;
        }else {

            try {
                Files.createFile(filePath);
            } catch (NoSuchFileException x) {
                System.err.format("%s: no such" + " file or directory%n", filePath);
            }catch (IOException x) {
                // File permission problems are caught here.
                System.err.println(x);
            }
        }
        scanner.close();
        if (Files.exists(filePath)){
            System.out.println("Successfully Added !");
        } else {
            System.out.println("Not Added");
        }

    }

    /**This method receives a directory content as a ArrayList path
     * It sorts and print the list
     * @return
     */
    static  void sortFile(List<Path> list){
        System.out.println("After sorting: ");
        Collections.sort(list);
        for (Path f:list)
            System.out.println(f.getFileName());
    }

    /** This method receives the directory as string and returns the list of  directory contents as a ArrayList object
     *This method also prints the list.
     * It throws IOException.
     */
    static List<Path> listingDirectory(String dir){
        List<Path> listDir = new ArrayList<Path>();
        Path path = Paths.get(dir);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path file: stream) {
                listDir.add(file);
            }
        } catch (IOException | DirectoryIteratorException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            System.err.println(x);
        }
        return listDir;

    }

}
public class Main {

    public static void main(String[] args) throws Exception{

        final String applicationName = "LockedMe.com";
        final String developerName = "Ali Samiei";
        String userOption=null;
        String pathDirectory=null;
        String fileName=null;
        String directory ="/Users/Pedram/Documents/Cloud-Programming/Course-Simplilearn" +
                "/ASSESSMENT1/TestFiles/"; // directory in which files are located
        String file=null;

        welcomeScreen(applicationName,developerName);
        mainMenu();
        Scanner scanner = new Scanner((System.in));
        while(scanner.hasNextLine()){
            userOption=scanner.nextLine();

            switch(userOption){
                case "1" : {List<Path> list =new ArrayList<Path>();
                    list=FileManager.listingDirectory(directory);
                    FileManager.sortFile(list); }

                    break;

                case "2" : subMenu();

                     break;

                case "A" : FileManager.addFile(directory);

                    break;

                case "D" : FileManager.deleteFile(directory);

                    break;

                case "S" : FileManager.searchFile(directory);

                    break;

                case "R" : mainMenu();

                    break;

                case "3"  : System.exit(0);


                default:
                    System.out.println("Press a valid key");

            }

        }
        scanner.close();

    }

    /**
     * This method executes welcome screen and menu
     * It displays Welcome Message following by application name and developer details
     */
    static void welcomeScreen(String appName, String devName) {
        System.out.println("****************************************************************");
        System.out.println("****************************************************************");
        System.out.println("                   WELCOME TO LockedMe.com                      ");
        System.out.println("                 Application name :" + appName);
        System.out.println("                  Developer name :" + devName);
        System.out.println("****************************************************************");
        System.out.println("****************************************************************");


    }

    /**
     * this method prints main options , reads user input and returns pressed key as integer
     * Option1 : Sorting
     * Option2: Sub_Menu
     * Option3: Exit
     */
    static void mainMenu(){
        System.out.println("");
        System.out.println("Press 1 , 2 or 3 :");
        System.out.println("Option 1 : Sorting Directory    "+"Option 2 : Sub_Menu     "+   "Option 3 : Exit" );

    }

    /** This method prints sub_menu
     * read input key and returns pressed key
     */
    static void subMenu(){
        String userKey=null;
        System.out.println("Press (B) to Sort file names in ascending order :");
        System.out.println("Press (A) to Add a file to the existing directory list :");
        System.out.println("Press (D) to Delete a specified file :");
        System.out.println("Press (S) to Search a specified file :");
        System.out.println("Press (R) to Return to the main menu :");
        System.out.println("Press (E) to exit :");

    }

    }

