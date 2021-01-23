package com.LockedMe;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
class FileManager {
    /**This method receives a path  and delete the file from the directory
     * before deleting the file it asks user to reconfirm delete operation
     * it returns 1 if delete successfully and returns -1 if not.
     *
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
     * it returns 1 if find the file and returns -1 if not.
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
        String userOption;
        String pathDirectory=null;
        String fileName=null;
        String directory ="/Users/Pedram/Documents/Cloud-Programming/Course-Simplilearn" +
                "/ASSESSMENT1/TestFiles"; // directory in which files are located
        String file=null;

        welcomeScreen(applicationName,developerName);


        // Here we check the option selected by user and calls related method
        // Delete , Add, Sort or Search

        Scanner scanner = new Scanner(System.in);
        userOption=scanner.nextLine();

        switch (userOption) {
            case "B":{  // print directory before and after sorting
                List<Path> list =new ArrayList<Path>();
                list=FileManager.listingDirectory(directory);
                FileManager.sortFile(list);
            }
            break ;
            case "A":
                FileManager.addFile(directory);

                break;
            case "D":
                FileManager.deleteFile(directory);

                break;
            case "S":
                FileManager.searchFile(directory);

                break;
            case "R":
                System.out.println("we are in Return");

                break;
            case "E":
                System.exit(0);

                break;
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
        System.out.println("       ************ WELCOME TO LockedMe.com ***********");
        System.out.println("                 Application name :" + appName);
        System.out.println("                  Developer name :" + devName);
        System.out.println("****************************************************************");
        System.out.println("****************************************************************");

        System.out.println("Press (B) to Sort file names in ascending order :");
        System.out.println("Press (A) to Add a file to the existing directory list :");
        System.out.println("Press (D) to Delete a specified file :");
        System.out.println("Press (S) to Search a specified file :");
        System.out.println("Press (R) to Return to the main menu :");
        System.out.println("Press (E) to exit :");

    }
    }

