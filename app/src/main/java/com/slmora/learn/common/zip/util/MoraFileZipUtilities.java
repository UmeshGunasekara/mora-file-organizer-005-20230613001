/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/30/2023 6:23 PM
 */
package com.slmora.learn.common.zip.util;

import com.slmora.learn.common.file.util.MoraFileWriteAccessUtilities;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

/**
 *  This Class created for
 *  <ul>
 *      <li>....</li>
 *  </ul>
 *
 * @since   1.0
 *
 * <blockquote><pre>
 * <br>Version      Date            Editor              Note
 * <br>-------------------------------------------------------
 * <br>1.0          6/30/2023      SLMORA                Initial Code
 * </pre></blockquote>
 *
 * @see <a href="https://www.baeldung.com/java-compress-and-uncompress">Zipping and Unzipping in Java</a>
 * @see <a href="https://zetcode.com/java/zipinputstream/">Java ZipInputStream</a>
 * @see <a href="https://www.geeksforgeeks.org/java-program-to-read-and-print-all-files-from-a-zip-file/">Java Program to Read and Print All Files From a Zip File</a>
 * @see <a href="https://jenkov.com/tutorials/java-zip/zipfile.html">Java ZipFile Tutorial</a>
 * @see <a href="https://www.digitalocean.com/community/tutorials/java-unzip-file-example">Java Unzip File Example</a>
 */
public class MoraFileZipUtilities {
    final static Logger LOGGER = LogManager.getLogger(MoraFileZipUtilities.class);

    public Optional<Path> unzipAndGetPath(Path sourceZipPath, String targetFolderPath) throws IOException
    {
//        `String fileZip = "D:\\MORA\\Video\\[ FreeCourseWeb.com ] Udemy - Gradle for java developers.zip";`
        MoraFileWriteAccessUtilities writeAccessUtilities = new MoraFileWriteAccessUtilities();
        File destinationDirectory = new File(targetFolderPath);
        String destinationPathString=targetFolderPath;
        String zipFileName = writeAccessUtilities.removeExtension(sourceZipPath.getFileName().toString());

        byte[] buffer = new byte[1024];
        try(ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(sourceZipPath.toAbsolutePath().toString()))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            int loopCount = 1;
            while (zipEntry != null) {
                File extractedDestination = getValidDestinationPath(destinationDirectory, zipEntry);
                if (zipEntry.isDirectory()) {
                    if (!extractedDestination.isDirectory() && !extractedDestination.mkdirs()) {
                        LOGGER.error("Failed to create directory " + extractedDestination);
//                        throw new IOException("Failed to create directory " + extractedDestination);
                    }else {
                        if(extractedDestination.getAbsolutePath().endsWith(zipFileName)){
                            destinationPathString = extractedDestination.getAbsolutePath();
                        }else if(!extractedDestination.getParent().contains(zipFileName)){
                            destinationPathString = targetFolderPath;
                        }
                    }
                } else {
                    // fix for Windows-created archives
                    File parent = extractedDestination.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        LOGGER.error("Failed to create directory " + parent);
//                        throw new IOException("Failed to create directory " + parent);
                    }else {
                        if (!parent.getAbsolutePath().contains(zipFileName)) {
                            destinationPathString = targetFolderPath;
                        }
                    }

                    // write file content
                    try(FileOutputStream fos = new FileOutputStream(extractedDestination)) {
                        int len;
                        while ((len = zipInputStream.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zipEntry = zipInputStream.getNextEntry();
                loopCount++;
            }

            zipInputStream.closeEntry();
        }
//        catch (ZipException ze){
//            LOGGER.error(ExceptionUtils.getStackTrace(ze));
//        }catch (IOException e) {
//            LOGGER.error(ExceptionUtils.getStackTrace(e));
////            throw new RuntimeException(e);
//        }
        Path destinationPath = Path.of(destinationPathString);
        return writeAccessUtilities.isEmpty(destinationPath)?Optional.empty():Optional.of(destinationPath);
    }

    private File getValidDestinationPath(File destinationDirectory, ZipEntry zipEntry) throws IOException
    {
        File destFile = new File(destinationDirectory, zipEntry.getName());

        String destinationDirectoryPathString = destinationDirectory.getCanonicalPath();
        String destinationFilePathString = destFile.getCanonicalPath();

        if (!destinationFilePathString.startsWith(destinationDirectoryPathString + File.separator)) {
            LOGGER.error("Entry is outside of the target dir: " + zipEntry.getName());
//            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
}
