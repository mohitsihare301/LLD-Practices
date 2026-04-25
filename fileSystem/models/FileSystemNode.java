package fileSystem.models;

public abstract class FileSystemNode{
    private String name;
    private FileSystemNode parent;

    public FileSystemNode(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public FileSystemNode getParent(){
        return parent;
    }

    public void setParent(FileSystemNode parent){
        this.parent = parent;
    }

    public String getAbsolutePath(){
        if(parent==null){
            return "/";
        }
        String parentPath = parent.getAbsolutePath();
        return parentPath == "/" ? "/" + name : parentPath + "/" + name;
    }

    public abstract boolean isDirectory();

    public abstract int getSize();
}