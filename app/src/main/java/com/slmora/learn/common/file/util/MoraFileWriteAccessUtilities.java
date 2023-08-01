/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 7/1/2023 9:39 AM
 */
package com.slmora.learn.common.file.util;

import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.system.property.SingleSystemProperty;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
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
    private final static MoraLogger LOGGER = MoraLogger.getLogger(MoraFileWriteAccessUtilities.class);

    public Optional<Set<String>> createDirectories(Path path) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Create directories for {}", (null!=path)?path.toAbsolutePath():null);
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
            LOGGER.error(Thread.currentThread().getStackTrace(), "Invalid File Path : "+path.toString());
            LOGGER.error(Thread.currentThread().getStackTrace(), "Your File System roots are");
            FileSystems.getDefault().getRootDirectories().forEach(i->LOGGER.debug(Thread.currentThread().getStackTrace(), "Root Dir for {}",  (null!=i)?i.toAbsolutePath():null));
            return Optional.empty();
        }else if (Files.isWritable(temPath)){
            Files.createDirectories(path);
            return Optional.of(dirSet);
        }else {
            LOGGER.error(Thread.currentThread().getStackTrace(), "User don't have permission to Write Dir in File System {} ", (null!=path)?path.toAbsolutePath():null);
            return Optional.empty();
        }
    }

    public void removeAll(Path targetPath, boolean isRemoveTargetParent) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Remove All from {} and remove target Path also : {}", (null!=targetPath)?targetPath.toAbsolutePath():null, isRemoveTargetParent);
        try (Stream<Path> walk = Files.walk(targetPath)) {
            walk.sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            if(isRemoveTargetParent){
                                Files.delete(path);
                                LOGGER.debug(Thread.currentThread().getStackTrace(), "Path removed {}", (null!=path)?path.toAbsolutePath():null);
                            }else{
                                if(!targetPath.toAbsolutePath().toString().endsWith(path.toAbsolutePath().toString())){
                                    Files.delete(path);
                                    LOGGER.debug(Thread.currentThread().getStackTrace(), "Path removed {}", (null!=path)?path.toAbsolutePath():null);
                                }
                            }
                        } catch (IOException e) {
                            LOGGER.error(Thread.currentThread().getStackTrace(), e);
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
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Accessible file count from {}", (null!=path)?path.toAbsolutePath():null);
        try (Stream<Path> walk = Files.walk(path)) {
            return walk
                    .filter(p -> Files.isReadable(p))
                    .filter(Files::isRegularFile)
                    .count();
        }
    }

    public String removeExtension(String filename) {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Remove file extension from {}", filename);
        final int NOT_FOUND = -1;
        final int index = FilenameUtils.indexOfExtension(filename); //used the String.lastIndexOf() method
        String result = null;
        if (index == NOT_FOUND) {
            LOGGER.debug(Thread.currentThread().getStackTrace(), "File doesn't have an extension");
            result = filename;
        } else {
            result = filename.substring(0, index);
        }
        LOGGER.debug(Thread.currentThread().getStackTrace(), "File extension removed {}", result);
        return result;
    }

    public boolean isEmpty(Path path) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Is this path empty {}", (null!=path)?path.toAbsolutePath():null);
        if (Files.isDirectory(path)) {
            try (Stream<Path> entries = Files.list(path)) {
                return !entries.findFirst().isPresent();
            }
        }

        return false;
    }

    public String getNonWindowsPathPatternByPath(Path path){
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Non windows pattern path for {}", (null!=path)?path.toAbsolutePath():null);
        String result = null;
        if(!path.getFileSystem().getSeparator().equals("/")&&path.toAbsolutePath().toString().contains("\\")){
            result = path.toAbsolutePath().toString().replace(path.getRoot().toAbsolutePath().toString(),"/").replace("\\","/");
        }else {
            result = path.toAbsolutePath().toString();
        }
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Result path {}", result);
        return result;
    }

    public Integer getDirectoryLevel(Path path){
        String sPath = path.toAbsolutePath().toString();
        Integer result = StringUtils.countMatches(sPath,path.getFileSystem().getSeparator());
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Directory Level for {} is {}", (null!=path)?path.toAbsolutePath():null, result);
        return result;
    }

    public List<Path> getImmediateFilesAndDirectoryList(Path sourcePath){
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Immediate Files list for {}", (null!=sourcePath)?sourcePath.toAbsolutePath():null);
        int baseDirectoryLevel = getDirectoryLevel(sourcePath);
        List<Path> resultList = new ArrayList<>();
        try{
            Files.walkFileTree(sourcePath,
                    new HashSet<FileVisitOption>(Arrays.asList(FileVisitOption.FOLLOW_LINKS)),
                    Integer.MAX_VALUE,
                    new SimpleFileVisitor<Path>()
                    {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        {
                            int dirLevel = getDirectoryLevel(file);
                            if(dirLevel>baseDirectoryLevel){
                                return FileVisitResult.SKIP_SIBLINGS;
                            }
                            resultList.add(file);
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFileFailed(Path file, IOException e)
                        {
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult preVisitDirectory(Path dir,
                                                                 BasicFileAttributes attrs)
                        {
                            int dirLevel = getDirectoryLevel(dir);
                            if(dirLevel>baseDirectoryLevel){
                                return FileVisitResult.SKIP_SUBTREE;
                            }else {
                                resultList.add(dir);
                            }
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult postVisitDirectory(Path dir, IOException exc){
                            return FileVisitResult.CONTINUE;
                        }
                    });
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
//            e.printStackTrace();
        }
        return resultList;
    }
}
