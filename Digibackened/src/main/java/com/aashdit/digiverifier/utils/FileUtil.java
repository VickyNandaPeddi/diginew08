package com.aashdit.digiverifier.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Slf4j
@Component
public class FileUtil {

    private static Random random = new Random();

    public static File createUniqueTempFile(String prefix, String suffix) {
        try {
            prefix.concat("_" + random.nextInt());
            return File.createTempFile(prefix, suffix);
        } catch (IOException e) {
            log.error("unable to create file", e);
            throw new RuntimeException("unable to create file");
        }
    }

    public String unzip(byte[] data, String destDir) {
        boolean slash = false;
        String slashDataName = "";
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if (dir.exists() == false) {
            dir.mkdirs();
        }

        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(data));
            ZipEntry ze = zis.getNextEntry();
            boolean contains = ze.toString().contains("/");
            if (contains == true) {
                slash = true;
                int slashIndex = ze.toString().indexOf("/");
                String dataBeforeSlash = ze.toString().substring(0, slashIndex);
                slashDataName = dataBeforeSlash;
                System.out.println("Data before slash: " + dataBeforeSlash);
            } else {
                slash = false;
            }
            while (ze != null) {
                String entryName = ze.getName();
                String entryPath = destDir + File.separator + entryName;
                if (ze.isDirectory()) {
                    // Create the directory if it doesn't exist
                    File directory = new File(entryPath);
                    if (directory.exists()==false) {
                        directory.mkdirs();
                    }
                }
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);

                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return slashDataName;
    }
//    public void unzip(byte[] data, String destDir) {
//        File dir = new File(destDir);
//        // create output directory if it doesn't exist
//        if (!dir.exists()) dir.mkdirs();
//        //buffer for read and write data to file
//        byte[] buffer = new byte[1024];
//        try {
//            ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(data));
//            ZipEntry ze = zis.getNextEntry();
//            while (ze != null) {
//                String fileName = ze.getName();
//                String entryFileName = Paths.get(fileName).getFileName().toString();
//
//                System.out.println(fileName);
//                String fileExtension = "";
//                int extensionIndex = fileName.lastIndexOf(".");
//                if (extensionIndex > -1) {
//                    fileExtension = fileName.substring(extensionIndex);
//                }
////                File newFile = new File(destDir + File.separator + fileName);
//                File newFile = new File(destDir + File.separator + entryFileName + fileExtension);
//                System.out.println("Unzipping to " + newFile.getAbsolutePath());
//                //create directories for sub directories in zip
//                new File(newFile.getParent()).mkdirs();
//                FileOutputStream fos = new FileOutputStream(newFile);
//                int len;
//                while ((len = zis.read(buffer)) > 0) {
//                    fos.write(buffer, 0, len);
//                }
//                fos.close();
//                //close this ZipEntry
//                zis.closeEntry();
//                ze = zis.getNextEntry();
//            }
//            //close last ZipEntry
//            zis.closeEntry();
//            zis.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    public void createZipFileFromByteArray(byte[] data, String destPath) throws IOException {
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(data));
        byte[] buffer = new byte[1024];
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            String fileName = entry.getName();
            File newFile = new File(destPath + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
        }
        zis.close();
    }


    public void extractZip(byte[] zipData, String targetDir) throws IOException {
        // Create a new ZipInputStream from the byte array
        try (ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(zipData))) {
            // Loop through each entry in the zip file
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                // Get the file name of the entry
                String fileName = zipEntry.getName();
//                System.out.println(zipEntry.getExtra());
                // Create a new file in the target directory with the same name
                File outputFile = new File(targetDir);
                // If the entry is a directory, create the directory
                if (zipEntry.isDirectory()) {
                    outputFile.mkdirs();
                } else {
                    // If the entry is a file, write its contents to the output file
                    try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = zipInputStream.read(buffer)) > 0) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }
                }
            }
        }
    }

    public byte[] unzip(byte[] zipData) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(zipData);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ZipInputStream zis = new ZipInputStream(bais)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    baos.write(buffer, 0, len);
                }
            }
        }
        System.out.println("done");
        return baos.toByteArray();
    }

    public static InputStream convertToInputStream(File file) {
        try {
            return new DataInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            log.error("unable to convert file to input stream", e);
            throw new RuntimeException("unable to convert file to input stream");
        }
    }

    public File convertByteArrayToFile(byte[] bytes, String filename) throws IOException {
        File file = new File(filename);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();
        return file;
    }

}
