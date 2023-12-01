import java.nio.file.Path;

public class FileInfo {
    private int ID;
    private Path path;

    FileInfo(int ID, Path path) {
        this.ID = ID;
        this.path = path;
    }

    public int getID() {
        return ID;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String toString() {
        return getID() + " " + getPath();
    }
}
