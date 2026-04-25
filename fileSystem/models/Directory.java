package fileSystem.models;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Directory extends FileSystemNode{
    private Map<String,FileSystemNode> children;

    public Directory(String name){
        super(name);
        this.children = new HashMap<>();
    }

    public void addChild(FileSystemNode node) throws Exception{
        if(children.containsKey(node.getName())){
            throw new Exception("File or directory already exists: "+ node.getName());
        }

        children.put(node.getName(),node);
        node.setParent(this);
    }

    public void removeChild(String name) throws Exception{
        if(!children.containsKey(name)){
            throw new Exception("File or directory not found: " + name);
        }
        children.remove(name);
    }

    public FileSystemNode getChild(String name){
        return children.get(name);
    }
    public Map<String,FileSystemNode> getChildren(){
        return children;
    }

    public boolean isDirectory(){
        return true;
    }

    public int getSize(){
        return children.values().stream().mapToInt(child -> child.getSize()).sum();
    }

}