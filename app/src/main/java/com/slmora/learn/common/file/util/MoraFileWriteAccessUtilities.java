/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 7/1/2023 9:39 AM
 */
package com.slmora.learn.common.file.util;

import com.slmora.learn.system.property.SingleSystemProperty;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

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
 * <br>1.0          7/1/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class MoraFileWriteAccessUtilities {
    final static Logger LOGGER = LogManager.getLogger(MoraFileWriteAccessUtilities.class);

    public Optional<Set<String>> createDirectories(Path path) throws IOException
    {
        Set<String> dirSet = new TreeSet<>();
        Path temPath = Paths.get(path.toString());
        while (!temPath.toFile().exists()){
            dirSet.add(temPath.toString());
            if(temPath.getParent()!=null){
                temPath = temPath.getParent();
            }else {
                temPath = null;
                break;
            }
        }
        if(temPath == null){
            LOGGER.info("Invalid File Path : "+path.toString());
            LOGGER.info("Your File System roots are");
            FileSystems.getDefault().getRootDirectories().forEach(System.out::println);
            return Optional.empty();
        }else if (Files.isWritable(temPath)){
            Files.createDirectories(path);
            return Optional.of(dirSet);
        }else {
            LOGGER.info("User don't have permission to Write Dir in File System : "+path.toString());
            return Optional.empty();
        }
    }

    public void removeAll(Path targetPath, boolean isRemoveTargetParent) throws IOException
    {
        try (Stream<Path> walk = Files.walk(targetPath)) {
            walk.sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            if(isRemoveTargetParent){
                                Files.delete(path);
                            }else{
                                if(!targetPath.toAbsolutePath().toString().endsWith(path.toAbsolutePath().toString())){
                                    Files.delete(path);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
//            walk.filter(Files::isRegularFile)
//                    .filter(Files::isWritable)
//                    .sorted(Comparator.reverseOrder())
//                    .forEach(path -> {
//                        try {
//                            Files.delete(path);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    });
//            walk.filter(Files::isRegularFile)
//                    .filter(Files::isWritable)
//                    .sorted(Comparator.reverseOrder())
//                    .map(Path::toFile)
//                    .forEach(File::delete);
        }
    }

    public Long getAccessibleFileCount(Path path) throws IOException {
        try (Stream<Path> walk = Files.walk(path)) {
            return walk
                    .filter(p -> Files.isReadable(p))
                    .filter(Files::isRegularFile)
                    .count();
        }
    }

    public String removeExtension(String filename) {
        final int NOT_FOUND = -1;
        final int index = FilenameUtils.indexOfExtension(filename); //used the String.lastIndexOf() method
        if (index == NOT_FOUND) {
            return filename;
        } else {
            return filename.substring(0, index);
        }
    }

    public boolean isEmpty(Path path) throws IOException
    {
        if (Files.isDirectory(path)) {
            try (Stream<Path> entries = Files.list(path)) {
                return !entries.findFirst().isPresent();
            }
        }

        return false;
    }
}
