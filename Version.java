import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Version{
    // private String studentName;
    // private String librarian;
    ArrayList <String>  versions = new ArrayList<>();
    private int uploaderId;
    private Path filePath;
    private  int counter= 0;
    
    
    public Version(int uploaderId, Path file_path){
        this.uploaderId= uploaderId;
        this.filePath = file_path;
        

    }

    private int getUploaderId(){
        return uploaderId;
    }


    private String getFileName(){
       
        String fileNameWithExtension = filePath.getFileName().toString();

        int index = fileNameWithExtension.lastIndexOf('.');
        if (index == -1) {
            return fileNameWithExtension;
        }
        String fileName= fileNameWithExtension.substring(0, index);
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

            //boolean guess = Files.exists(filePath)? true : false;
            //System.out.println("path: " + filePath + " guess: " + guess);

            // Generate the version name
            LocalDateTime timeUploaded = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH-mm-ss");
            versionName = getFileName() + "_v" + counter + "_" + timeUploaded.format(formatter) + getFileExtension();

            File submissionsFolder = new File("Submissions");
            System.out.println("Submissions folder exists: " + submissionsFolder.exists());

            // Print the contents of the "Submissions" folder
            File[] submissionFiles = submissionsFolder.listFiles();
            System.out.println("Files in Submissions folder:");
            for (File file : submissionFiles) {
                System.out.println(file.getName());
            }


            Path sourcePath = Paths.get("Submissions", getFileName() + getFileExtension());

            // Create a new folder for versions if it doesn't exist
            File VersionsFolder= new File("VersionsFolder");
            Path versionFilePath= new File(VersionsFolder,versionName).toPath();


            System.out.println("SourceFile: " + sourcePath);
            System.out.println("FileExtension: " + getFileExtension());
            System.out.println("SourcePath: " + sourcePath);
            System.out.println("VersionFilePath: " + versionFilePath);


            Files.copy(sourcePath, versionFilePath,StandardCopyOption.REPLACE_EXISTING);


        } catch (IOException e) {
            if (e instanceof NoSuchFileException) {
                System.out.println("File not found: " + filePath.toString());
        } else {
            e.printStackTrace();
        }
    }

    //Add the version to the list
    versions.add(versionName);
    System.out.println(versionName);
    counter++;
    }



    public void getVersion(){
        for (String docVersion:versions){
            System.out.println(docVersion);
        }
    }




    public static void main(String[] args) {
        
        Path path = Paths.get("Submissions\\ar.c");
        System.out.println(path);
        Version document = new Version(123456789, path);

        document.addVersion();
        document.getVersion();

    }

}