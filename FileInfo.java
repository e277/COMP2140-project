import java.nio.file.Path;

public class FileInfo {
    private int ID;
    private Path path;

    FileInfo(int ID, Path path) {
        this.ID = ID;
        this.path = path;
    }

    
    /** 
     * @return int
     */
    public int getID() {
        return ID;
    }

    
    /** 
     * @return Path
     */
    public Path getPath() {
        return path;
    }

    
    /** 
     * @param path
     */
    public void setPath(Path path) {
        this.path = path;
    }

    public String toString() {
        return getID() + " " + getPath();
    }
}
