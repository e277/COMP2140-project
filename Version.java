
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Version {
    // private String studentName;
    // private String librarian;
    ArrayList<String> versions = new ArrayList<>();
    private int uploaderId;
    private Path filePath;
    private int counter = 1;
    private FileInfo info;
    private ArrayList<FileInfo> files = new ArrayList<FileInfo>();


    public Version(int uploaderId, Path file_path) {
        this.uploaderId = uploaderId;
        this.filePath = file_path;
        info = new FileInfo(uploaderId, file_path);
        files = loadFileInfo("FileInfo.txt");
    }

    private int getUploaderId() {
        return uploaderId;
    }

    private String getFileName() {

        String fileNameWithExtension = filePath.getFileName().toString();

        int index = fileNameWithExtension.lastIndexOf('.');
        if (index == -1) {
            return fileNameWithExtension;
        }
        String fileName = fileNameWithExtension.substring(0, index);
        return fileName;
    }

    private String getFileExtension() {
        String fileNameWithExtension = filePath.getFileName().toString();
        int lastDotIndex = fileNameWithExtension.lastIndexOf('.');
        if (lastDotIndex != -1) {
            return fileNameWithExtension.substring(lastDotIndex);
        } else {
            return "";
        }
    }

    void addVersion() {

        String versionName = "";

        try {
            // Check if the original file exists
            if (!Files.exists(filePath)) {
                System.out.println("Original file not found: " + filePath.toString());
                return;
            }

            versionName = getFileName() + "_v" + counter + "_" + getFileExtension();

            Path sourcePath = new File("Submissions/" + getFileName() + getFileExtension()).toPath();
            if (!Files.exists(sourcePath)) {
                System.out.println("Original file not found: " + filePath.toString());
                return;
            }

            File old = new File("Submissions\\" + getFileName() + getFileExtension());
            File newf = new File("Submissions\\" + versionName);

            boolean rename = old.renameTo(newf);
            if(rename) {
                sourcePath = newf.toPath();}

            File Folder = new File("Submissions");
            Path versionFilePath = new File(Folder, versionName).toPath();

        

            for(FileInfo file: files) {
                if (getUploaderId() == file.getID()){
                    file.setPath(versionFilePath);
                    break;
                }}

            AddFileInfo(files);
            Files.copy(sourcePath, versionFilePath, StandardCopyOption.REPLACE_EXISTING);


        } catch (Exception e) {
            if (e instanceof NoSuchFileException) {
                System.out.println("File not found: " + filePath.toString());
            } else {
                e.printStackTrace();
            }
        }
    }

    void updateVersion() {
        try {
            String name = getFileName().split("_")[1];
            int num = Integer.parseInt(name.substring(1));
            num++;
            String newname = getFileName().split("_")[0] + "_v" + num + "_" + getFileExtension();
            File Folder = new File("Submissions");
            File oldFile = filePath.toFile();
            File newFile = new File(Folder, newname);
            oldFile.renameTo(newFile);
            Path sPath = newFile.toPath();
            Path dPath = newFile.toPath();
            Files.copy(sPath, dPath, StandardCopyOption.REPLACE_EXISTING);


             for(FileInfo file: files) {
                if (getUploaderId() == file.getID()){
                    file.setPath(dPath);
                    break;
                }}

            AddFileInfo(files);
        } catch (Exception e) {
            if (e instanceof NoSuchFileException) {
                System.out.println("File not found: " + filePath.toString());
            } else {
                e.printStackTrace();
            }

        }
    }

    public void AddFileInfo(ArrayList<FileInfo> fileList) {
        try {
            PrintStream out = new PrintStream(new FileOutputStream("FileInfo.txt"));
            for (FileInfo r : fileList) {
                out.println(r);

            }
            out.close();
        } catch (FileNotFoundException fe) {
            System.out.print(fe.getMessage());
        }
    }

    private ArrayList<FileInfo> loadFileInfo(String idFile) {
        Scanner scan;
        try {
            scan = new Scanner(new File(idFile));
            while (scan.hasNext()) {
                String line = scan.nextLine();
                int id = Integer.parseInt(line.split(" ")[0]);
                Path path = new File(line.split(" ")[1]).toPath();
                FileInfo fInfo = new FileInfo(id, path);
                files.add(fInfo);
            }
            return files;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return files;
    }
}