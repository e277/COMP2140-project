
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

            
            versionName = getFileName() + "_v" + counter + "_" + getFileExtension();
            System.out.println("l: " +versionName);

            File submissionsFolder = new File("Submissions");

            // Print the contents of the "Submissions" folder
            File[] submissionFiles = submissionsFolder.listFiles();
            System.out.println("Files in Submissions folder:");
            for (File file : submissionFiles) {
                System.out.println(file.getName());
            }

            
            Path sourcePath = new File("Submissions/" + getFileName() + getFileExtension()).toPath();
            if (!Files.exists(sourcePath)) {
                System.out.println("Original files not found: " + filePath.toString());
                return;
            }
            Path sourcPath = Paths.get("Submissions", versionName);
            String sourceName = sourcePath.toString();
            //System.out.println("source:" + "\t" + sourceName + "\n");
            String sName = sourcPath.toString();
            //System.out.println("destination:" + "\t" +sName + "\n");
            File old = new File("Submissions\\" + getFileName() + getFileExtension());
             if (!Files.exists(old.toPath())) {
                System.out.println("Original files not found: " + filePath.toString());
                return;
            }
            System.out.println("old:" + "\t" + old + "\n");
            File newf = new File("Submissions\\"+ versionName);
            System.out.println("new:" + "\t" + newf + "\t" + versionName + "\n");

           boolean h = old.renameTo(newf);
            //sourcePath = old.toPath();
            if (h){
                sourcePath = newf.toPath();
            System.out.println("sourcename: " + old + "\n" + "newname:" + newf + "\n" + "newpath:" + sourcePath);}
            

            // Create a new folder for versions if it doesn't exist
            File VersionsFolder= new File("VersionsFolder");
            Path versionFilePath= new File(VersionsFolder,versionName).toPath();
            

            System.out.println(sourcePath + "\t" + versionFilePath);
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



    

}