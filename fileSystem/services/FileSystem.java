package fileSystem.services;

import fileSystem.models.FileSystemNode;

import java.util.ArrayList;

import fileSystem.models.Directory;
import fileSystem.models.File;
import java.util.List;

public class FileSystem {
    private static FileSystem instance;
    private Directory root;
    private Directory currentDir;

    private FileSystem(){
        root = new Directory("root");
        currentDir = root;
    };

    public static synchronized FileSystem getInstance(){
        if(instance==null){
            instance = new FileSystem();
        }
        return instance;
    }

    public Directory getCurrentDirectory(){
        return currentDir;
    }

    public String pwd(){
        return currentDir.getAbsolutePath();
    }

    public void createFile(String name) throws Exception{
        File file = new File(name);
        currentDir.addChild(file);
    }

    public String readFile(String name) throws Exception{
        FileSystemNode node = currentDir.getChild(name);
        if(node == null){
            throw new Exception("File not found: "+ name);
        }

        if(node.isDirectory()){
            throw new Exception(name + " is a dir.");
        }

        return ((File) node).getContent();
    }

    public void writeFile(String name, String content) throws Exception{
        FileSystemNode node = currentDir.getChild(name);
        if(name == null){
            throw new Exception("File not found: " + name);
        }

        if(node.isDirectory()){
            throw new Exception(name + " is a dir.");
        }

        ((File) node).setContent(content);
    }

    public void mkdir(String name) throws Exception{
        Directory newDir = new Directory(name);
        currentDir.addChild(newDir);
    }

    public List<String> ls(){
        List<String> items = new ArrayList<>();
        for(FileSystemNode node: currentDir.getChildren().values()){
            items.add(node.getName() + (node.isDirectory() ? "/" : "") );
        }
        return items;
    }

    public void rm(String name) throws Exception{
        currentDir.removeChild(name);
    }

    public void cd(String pathString) throws Exception{
        if(pathString == "/"){
            currentDir = root;
            return;
        }

        String[] paths = pathString.split("/");
        Directory temp = pathString.startsWith("/") ? root : currentDir;
        for(String path: paths){
            if(path.equals("/") || path.equals(".")){
                continue;
            }
            if(path.equals("..")){
                if(temp.getParent()!=null){
                    temp=(Directory) temp.getParent();
                }
                continue;
            }
            FileSystemNode node = temp.getChild(path);
            if(node == null || !node.isDirectory()){
                throw new Exception("Invalid dir: " + path);
            }
            temp = (Directory) node;
        }

        currentDir = temp;
    }


}
