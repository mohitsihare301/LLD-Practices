package fileSystem;
import java.util.List;
import java.util.Scanner;

import fileSystem.services.FileSystem;
public class FileSystemMain {
    public static void main(String[] args){
        FileSystem fs = FileSystem.getInstance();

        Scanner sc = new Scanner(System.in);
       
        while(true){
            System.out.println(fs.pwd() + " $ ");
            
            String input = sc.nextLine().trim();

            if(input.isEmpty()){
                continue;
            }

            String[] parts = input.split("\\s+", 3);
            String command = parts[0];

            try{
                switch(command){
                    case "ls": 
                        List<String> items = fs.ls();
                        System.out.println("List items: ");
                        System.out.println(String.join(" ", items));
                        break;

                    case "pwd":
                        System.out.println(fs.pwd());
                        break;
                    
                    case "mkdir":
                        fs.mkdir(parts[1]);
                        System.out.println("Created dir: " + parts[1]);
                        break;
                    
                    case "touch": 
                        fs.createFile(parts[1]);
                        System.out.println("Created file: "+ parts[1]);
                        break;

                    case "cat":
                        System.out.println(fs.readFile(parts[1]));
                        break;

                    case "nano": 
                        fs.writeFile(parts[1],parts[2]);
                        break;
                    
                    case "rm": 
                        fs.rm(parts[1]);
                        System.out.println("Removed: " + parts[1]);
                        break;
                    
                    case "cd":
                        fs.cd(parts[1]);
                        break;
                    
                    case "exit":
                        return;
                    
                    default:
                        System.err.println("Command not found!");
                    
                    
                    
                }
            } catch( Exception e){
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
