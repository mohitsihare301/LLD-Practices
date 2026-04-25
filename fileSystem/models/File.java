package fileSystem.models;

public class File extends FileSystemNode{
    private StringBuilder content;

    public File(String name){
        super(name);
        this.content = new StringBuilder();
    }

    public File(String name, String content){
        super(name);
        this.content = new StringBuilder(content);
    }

    public String getContent(){
        return content.toString();
    }

    public void setContent(String content){
        this.content = new StringBuilder(content);
    }

    public void appendContent(String extraContent){
        this.content.append(extraContent);
    }

    public boolean isDirectory(){
        return false;
    }

    public int getSize(){
        return content.length();
    }
}