/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 7/1/2023 3:24 PM
 */
package com.slmora.learn.controller;

import com.slmora.learn.common.file.util.MoraFileWriteAccessUtilities;
import com.slmora.learn.dao.impl.MFODirectoryDaoImpl;
import com.slmora.learn.dao.impl.MFOFileDaoImpl;
import com.slmora.learn.dto.DirectoryDto;
import com.slmora.learn.dto.FileDto;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.jpa.entity.EMFOFile;
import com.slmora.learn.model.SearchPathModel;
import com.slmora.learn.service.IMFODirectoryService;
import com.slmora.learn.service.IMFOFileService;
import com.slmora.learn.service.impl.MFODirectoryServiceImpl;
import com.slmora.learn.service.impl.MFOFileServiceImpl;
import com.slmora.learn.system.property.SingleSystemProperty;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

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
public class MoraFileOrganizerWalkingController {
    final static Logger LOGGER = LogManager.getLogger(MoraFileOrganizerWalkingController.class);


    public void sourcePathWalk(Path source, Integer zipFileLevel, Path zipParent, EMFOFile eFile, Integer driveCode, boolean isSkipEnable){

        if(zipParent!=null && eFile!=null){
            LOGGER.info("sourcePathWalk source : "+source.toAbsolutePath().toString()+", zipFileLevel"+zipFileLevel+", zipParent"+zipParent.toAbsolutePath().toString()+", eFile : "+eFile.getFileFullPath()+", driveCode : "+driveCode);
        }else {
            LOGGER.info("sourcePathWalk source : "+source.toAbsolutePath().toString()+", zipFileLevel"+zipFileLevel+", driveCode : "+driveCode);
        }

        final String[] basePathString = {null};
        final Integer[] basePathLevel = {null};

        try{
            Files.walkFileTree(source,
                    new HashSet<FileVisitOption>(Arrays.asList(FileVisitOption.FOLLOW_LINKS)),
                    Integer.MAX_VALUE,
                    new SimpleFileVisitor<>()
                    {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        {
//                            System.out.printf("-------- Visiting file %s\n", file+", Level: "+zipFileLevel);
                            LOGGER.info("-------- Visiting file :"+ file.toAbsolutePath()+", Level: "+zipFileLevel+", driveCode : "+driveCode);
//                            LOGGER.info("Visiting file "+file+"\n");
                            MFOFileController fileController = new MFOFileController();
                            MoraFileWriteAccessUtilities writeAccessUtilities = new MoraFileWriteAccessUtilities();
                            if(zipFileLevel<1) {
//                                if(isSkipEnable) {
//                                    if (basePathString[0] != null && basePathLevel[0] != null) {
//                                        Integer dirLevel = writeAccessUtilities.getDirectoryLevel(file) + 1;
//                                        if (file.toAbsolutePath().toString().startsWith(basePathString[0]) && dirLevel.intValue() >= basePathLevel[0]) {
//                                            return FileVisitResult.CONTINUE;
//                                        }
//                                    }
//                                }
                                Optional<FileDto> opFileDto = isFileAvailable(file, zipFileLevel, driveCode);
                                if (opFileDto.isPresent()) {
                                    FileDto fileDto = opFileDto.get();
                                    if (fileDto.getFileSearchStatus() == 1) {
                                        System.gc();
                                        return FileVisitResult.CONTINUE;
                                    } else {
                                        fileController.addFile(fileDto, 0, driveCode, isSkipEnable);
                                        System.gc();
                                        return FileVisitResult.CONTINUE;
                                    }
                                } else {
                                    fileController.addFile(file, 0, driveCode, isSkipEnable);
                                    System.gc();
                                    return FileVisitResult.CONTINUE;
                                }
                            }else {
                                String zipExtractionDestination = SingleSystemProperty.PROP_MFO_ZIP_EXTRACT_DESTINATION+"_"+zipFileLevel;
                                String dbFileString = file.toAbsolutePath().toString().replace(zipExtractionDestination, eFile.getFileFullPath());
                                Path dbFile = Paths.get(dbFileString);
//                                if(isSkipEnable) {
//                                    if (basePathString[0] != null && basePathLevel[0] != null) {
//                                        Integer dirLevel = writeAccessUtilities.getDirectoryLevel(dbFile) + 1;
//                                        if (dbFile.toAbsolutePath().toString().startsWith(basePathString[0]) && dirLevel.intValue() >= basePathLevel[0]) {
//                                            return FileVisitResult.CONTINUE;
//                                        }
//                                    }
//                                }
                                Optional<FileDto> opFileDto = isFileAvailable(dbFile, zipFileLevel, eFile, driveCode);
                                if(opFileDto.isPresent()){
                                    FileDto fileDto = opFileDto.get();
                                    if(fileDto.getFileSearchStatus()==1){
                                        System.gc();
                                        return FileVisitResult.CONTINUE;
                                    }else {
                                        fileController.addFile(file, fileDto, zipFileLevel, eFile, zipParent, driveCode,isSkipEnable);
                                        System.gc();
                                        return FileVisitResult.CONTINUE;
                                    }
                                }else {
                                    fileController.addFile(file, zipFileLevel, eFile, zipParent, driveCode,isSkipEnable);
                                    System.gc();
                                    return FileVisitResult.CONTINUE;
                                }
                            }






//                            if(basePathString[0]!=null&&basePathLevel[0]!=null){
//                                Integer dirLevel = writeAccessUtilities.getDirectoryLevel(file)+1;
//                                if(file.toAbsolutePath().toString().startsWith(basePathString[0])&&dirLevel.intValue()>=basePathLevel[0]){
//                                    return FileVisitResult.CONTINUE;
//                                }else {
//                                    if(zipFileLevel<1) {
//                                        Optional<FileDto> opFileDto = isFileAvailable(file, zipFileLevel, driveCode);
//                                        if(opFileDto.isPresent()){
//                                            FileDto fileDto = opFileDto.get();
//                                            if(fileDto.getFileSearchStatus()==1){
//                                                System.gc();
//                                                return FileVisitResult.CONTINUE;
//                                            }else {
//                                                fileController.addFile(fileDto, 0, driveCode);
//                                                System.gc();
//                                                return FileVisitResult.CONTINUE;
//                                            }
//                                        }else {
//                                            fileController.addFile(file, 0, driveCode);
//                                            System.gc();
//                                            return FileVisitResult.CONTINUE;
//                                        }
//                                    }else {
//                                        Optional<FileDto> opFileDto = isFileAvailable(file, zipFileLevel, eFile, driveCode);
//                                        if(opFileDto.isPresent()){
//                                            FileDto fileDto = opFileDto.get();
//                                            if(fileDto.getFileSearchStatus()==1){
//                                                System.gc();
//                                                return FileVisitResult.CONTINUE;
//                                            }
//                                        }else {
//                                            fileController.addFile(file, zipFileLevel, eFile, zipParent, driveCode);
//                                            System.gc();
//                                            return FileVisitResult.CONTINUE;
//                                        }
//                                    }
//                                }
//                            }else {
//                                if(zipFileLevel<1) {
//                                    Optional<FileDto> opFileDto = isFileAvailable(file, zipFileLevel, driveCode);
//                                    if(opFileDto.isPresent()){
//                                        FileDto fileDto = opFileDto.get();
//                                        if(fileDto.getFileSearchStatus()==1){
//                                            System.gc();
//                                            return FileVisitResult.CONTINUE;
//                                        }
//
//                                    }else {
//                                        fileController.addFile(file, 0, driveCode);
//                                        System.gc();
//                                        return FileVisitResult.CONTINUE;
//                                    }
//                                }else {
//                                    Optional<FileDto> opFileDto = isFileAvailable(file, zipFileLevel, eFile, driveCode);
//                                    if(opFileDto.isPresent()){
//                                        FileDto fileDto = opFileDto.get();
//                                        if(fileDto.getFileSearchStatus()==1){
//                                            System.gc();
//                                            return FileVisitResult.CONTINUE;
//                                        }
//                                    }else {
//                                        fileController.addFile(file, zipFileLevel, eFile, zipParent, driveCode);
//                                        System.gc();
//                                        return FileVisitResult.CONTINUE;
//                                    }
//                                }
//                            }
//                            System.gc();
//                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFileFailed(Path file, IOException e)
                        {
//                            System.err.printf("Visiting failed for %s\n", file+", Level: "+zipFileLevel);
//                            LOGGER.info("Visiting failed for "+file+"\n");
                            return FileVisitResult.SKIP_SUBTREE;
                        }

                        @Override
                        public FileVisitResult preVisitDirectory(Path dir,
                                                                 BasicFileAttributes attrs)
                        {
//                            System.out.printf("---------- About to visit directory %s\n", dir);
                            LOGGER.info("---------- Visiting directory :"+ dir.toAbsolutePath()+", Level: "+zipFileLevel+", driveCode : "+driveCode);
//                            LOGGER.info("About to visit directory "+dir+"\n");
                            MFODirectoryController directoryController = new MFODirectoryController();
                            MoraFileWriteAccessUtilities writeAccessUtilities = new MoraFileWriteAccessUtilities();

                            if(zipFileLevel<1) {
//                                if(isSkipEnable) {
//                                    if (basePathString[0] != null && basePathLevel[0] != null) {
//                                        Integer dirLevel = writeAccessUtilities.getDirectoryLevel(dir) + 1;
//                                        if (dir.toAbsolutePath().toString().startsWith(basePathString[0]) && dirLevel.intValue() > basePathLevel[0]) {
//                                            return FileVisitResult.SKIP_SUBTREE;
//                                        }
//                                    }
//                                }
                                Optional<DirectoryDto> opDirDto = isDirectoryAvailable(dir, zipFileLevel, driveCode);
                                if(opDirDto.isPresent()){
                                    DirectoryDto dirDto = opDirDto.get();
                                    if(dirDto.getDirectorySearchStatus()==1){
                                        if(isSkipEnable) {
                                            System.gc();
                                            return FileVisitResult.SKIP_SUBTREE;
                                        }else {
//                                            basePathString[0] = dirDto.getDirectoryFullPath();
////                                            basePathLevel[0] = writeAccessUtilities.getDirectoryLevel(dir)+1;
//                                            basePathLevel[0] = dirDto.getDirectoryLevel();
                                            System.gc();
                                            return FileVisitResult.CONTINUE;
                                        }
                                    }else {
                                        SingleSystemProperty.SEARCH_DIR_STACK.push(SearchPathModel.of(dir,zipFileLevel,null,null,null));
                                        System.gc();
                                        return FileVisitResult.CONTINUE;
                                    }

                                }else {
                                    directoryController.addDirectory(dir, 0, driveCode);
                                    System.gc();
                                    return FileVisitResult.CONTINUE;
                                }
                            }else {
                                String zipExtractionDestination = SingleSystemProperty.PROP_MFO_ZIP_EXTRACT_DESTINATION+"_"+zipFileLevel;
                                String dbDirString = dir.toAbsolutePath().toString().replace(zipExtractionDestination, eFile.getFileFullPath());
                                Path dbDir = Paths.get(dbDirString);
//                                if(isSkipEnable) {
//                                    if (basePathString[0] != null && basePathLevel[0] != null) {
//                                        Integer dirLevel = writeAccessUtilities.getDirectoryLevel(dbDir) + 1;
//                                        if (dbDir.toAbsolutePath().toString().startsWith(basePathString[0]) && dirLevel.intValue() > basePathLevel[0]) {
//                                            return FileVisitResult.SKIP_SUBTREE;
//                                        }
//                                    }
//                                }
                                Optional<DirectoryDto> opDirDto = isDirectoryAvailable(dbDir, zipFileLevel, eFile, driveCode);
                                if(opDirDto.isPresent()){
                                    DirectoryDto dirDto = opDirDto.get();
                                    if(dirDto.getDirectorySearchStatus()==1){
                                        if(isSkipEnable) {
                                            System.gc();
                                            return FileVisitResult.SKIP_SUBTREE;
                                        }else {
//                                            basePathString[0] = dirDto.getDirectoryFullPath();
//                                            basePathLevel[0] = dirDto.getDirectoryLevel();
                                            System.gc();
                                            return FileVisitResult.CONTINUE;
                                        }
                                    }else {
                                        SingleSystemProperty.SEARCH_DIR_STACK.push(SearchPathModel.of(dbDir,zipFileLevel,Paths.get(eFile.getFileFullPath()),eFile.getFileIsZip(),eFile.getId()));
                                        System.gc();
                                        return FileVisitResult.CONTINUE;
                                    }
                                }else {
                                    directoryController.addDirectory(dir,
                                            zipFileLevel,
                                            eFile,
                                            zipParent,
                                            driveCode);
                                    System.gc();
                                    return FileVisitResult.CONTINUE;
                                }
                            }





//                            SingleSystemProperty.SEARCH_DIR_STACK.push(SearchPathModel.of(dir.toAbsolutePath().toString(),zipFileLevel));
//                            if(basePathString[0]!=null&&basePathLevel[0]!=null){
//                                Integer dirLevel = writeAccessUtilities.getDirectoryLevel(dir)+1;
//                                if(dir.toAbsolutePath().toString().startsWith(basePathString[0])&&dirLevel.intValue()>basePathLevel[0]){
//                                    return FileVisitResult.CONTINUE;
//                                }else {
//                                    if(zipFileLevel<1) {
//                                        Optional<DirectoryDto> opDirDto = isDirectoryAvailable(dir, zipFileLevel, driveCode);
//                                        if(opDirDto.isPresent()){
//                                            DirectoryDto dirDto = opDirDto.get();
//                                            if(dirDto.getDirectorySearchStatus()==1){
//                                                basePathString[0] = dirDto.getDirectoryFullPath();
////                                            basePathLevel[0] = writeAccessUtilities.getDirectoryLevel(dir)+1;
//                                                basePathLevel[0] = dirDto.getDirectoryLevel();
//                                                System.gc();
//                                                return FileVisitResult.CONTINUE;
//                                            }else {
//                                                SingleSystemProperty.SEARCH_DIR_STACK.push(SearchPathModel.of(dir,zipFileLevel,null,null,null));
//                                                System.gc();
//                                                return FileVisitResult.CONTINUE;
//                                            }
//
//                                        }else {
//                                            directoryController.addDirectory(dir, 0, driveCode);
//                                            System.gc();
//                                            return FileVisitResult.CONTINUE;
//                                        }
//                                    }else {
//                                        Optional<DirectoryDto> opDirDto = isDirectoryAvailable(dir, zipFileLevel, eFile, driveCode);
//                                        if(opDirDto.isPresent()){
//                                            DirectoryDto dirDto = opDirDto.get();
//                                            if(dirDto.getDirectorySearchStatus()==1){
//                                                basePathString[0] = dirDto.getDirectoryFullPath();
//                                                basePathLevel[0] = dirDto.getDirectoryLevel();
//                                                System.gc();
//                                                return FileVisitResult.CONTINUE;
//                                            }else {
//                                                SingleSystemProperty.SEARCH_DIR_STACK.push(SearchPathModel.of(dir,zipFileLevel,Paths.get(eFile.getFileFullPath()),eFile.getFileIsZip(),eFile.getId()));
//                                                System.gc();
//                                                return FileVisitResult.CONTINUE;
//                                            }
//                                        }else {
//                                            directoryController.addDirectory(dir,
//                                                    zipFileLevel,
//                                                    eFile,
//                                                    zipParent,
//                                                    driveCode);
//                                            System.gc();
//                                            return FileVisitResult.CONTINUE;
//                                        }
//                                    }
//                                }
//                            }else {
//                                if(zipFileLevel<1) {
//                                    Optional<DirectoryDto> opDirDto = isDirectoryAvailable(dir, zipFileLevel, driveCode);
//                                    if(opDirDto.isPresent()){
//                                        DirectoryDto dirDto = opDirDto.get();
//                                        if(dirDto.getDirectorySearchStatus()==1){
//                                            basePathString[0] = dirDto.getDirectoryFullPath();
////                                            basePathLevel[0] = writeAccessUtilities.getDirectoryLevel(dir)+1;
//                                            basePathLevel[0] = dirDto.getDirectoryLevel();
//                                            System.gc();
//                                            return FileVisitResult.CONTINUE;
//                                        }else {
//                                            SingleSystemProperty.SEARCH_DIR_STACK.push(SearchPathModel.of(dir,zipFileLevel,null,null,null));
//                                            System.gc();
//                                            return FileVisitResult.CONTINUE;
//                                        }
//
//                                    }else {
//                                        directoryController.addDirectory(dir, 0, driveCode);
//                                        System.gc();
//                                        return FileVisitResult.CONTINUE;
//                                    }
//                                }else {
//                                    Optional<DirectoryDto> opDirDto = isDirectoryAvailable(dir, zipFileLevel, eFile, driveCode);
//                                    if(opDirDto.isPresent()){
//                                        DirectoryDto dirDto = opDirDto.get();
//                                        if(dirDto.getDirectorySearchStatus()==1){
//                                            basePathString[0] = dirDto.getDirectoryFullPath();
//                                            basePathLevel[0] = dirDto.getDirectoryLevel();
//                                            System.gc();
//                                            return FileVisitResult.CONTINUE;
//                                        }else {
//                                            SingleSystemProperty.SEARCH_DIR_STACK.push(SearchPathModel.of(dir,zipFileLevel,Paths.get(eFile.getFileFullPath()),eFile.getFileIsZip(),eFile.getId()));
//                                            System.gc();
//                                            return FileVisitResult.CONTINUE;
//                                        }
//                                    }else {
//                                        directoryController.addDirectory(dir,
//                                                zipFileLevel,
//                                                eFile,
//                                                zipParent,
//                                                driveCode);
//                                        System.gc();
//                                        return FileVisitResult.CONTINUE;
//                                    }
//                                }
//                            }
//                            System.gc();
//                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                        {
//                            System.out.println("Post Visit Directory: " + dir);
                            return FileVisitResult.CONTINUE;
                        }
                    });
        } catch (IOException e) {
//            LOGGER.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }

    private Optional<DirectoryDto> isDirectoryAvailable(Path source, Integer zipFileLevel, EMFOFile eZipFile, Integer driveCode){
        IMFODirectoryService dirService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
        SearchPathModel searchModel = SearchPathModel.of(source, zipFileLevel, Paths.get(eZipFile.getFileFullPath()), eZipFile.getFileIsZip(), eZipFile.getId());
        try{
            Optional<EMFODirectory> opSearchDirectory = dirService.getMFODirectoryBySearchPathModelDrive(searchModel, driveCode);
            if(opSearchDirectory.isPresent()){
                return Optional.of(new DirectoryDto(opSearchDirectory.get()));
            }else {
                return Optional.empty();
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }finally {
            dirService.close();
        }
    }

    private Optional<DirectoryDto> isDirectoryAvailable(Path source, Integer zipFileLevel, Integer driveCode){
        IMFODirectoryService dirService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
        SearchPathModel searchModel = SearchPathModel.of(source, zipFileLevel, null, null, null);
        try{
            Optional<EMFODirectory> opSearchDirectory = dirService.getMFODirectoryBySearchPathModelDrive(searchModel, driveCode);
            if(opSearchDirectory.isPresent()){
                return Optional.of(new DirectoryDto(opSearchDirectory.get()));
            }else {
                return Optional.empty();
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }finally {
            dirService.close();
        }
    }

    private Optional<FileDto> isFileAvailable(Path source, Integer zipFileLevel, EMFOFile eZipFile, Integer driveCode){
        IMFOFileService fileService = new MFOFileServiceImpl(new MFOFileDaoImpl());
        SearchPathModel searchModel = SearchPathModel.of(source, zipFileLevel, Paths.get(eZipFile.getFileFullPath()), eZipFile.getFileIsZip(), eZipFile.getId());
        try{
            Optional<EMFOFile> opSearchFile = fileService.getMFOFileBySearchPathModelDrive(searchModel, driveCode);
            if(opSearchFile.isPresent()){
                return Optional.of(new FileDto(opSearchFile.get()));
            }else {
                return Optional.empty();
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }finally {
            fileService.close();
        }
    }

    private Optional<FileDto> isFileAvailable(Path source, Integer zipFileLevel, Integer driveCode){
        IMFOFileService fileService = new MFOFileServiceImpl(new MFOFileDaoImpl());
        SearchPathModel searchModel = SearchPathModel.of(source, zipFileLevel, null, null, null);
        try{
            Optional<EMFOFile> opSearchFile = fileService.getMFOFileBySearchPathModelDrive(searchModel, driveCode);
            if(opSearchFile.isPresent()){
                return Optional.of(new FileDto(opSearchFile.get()));
            }else {
                return Optional.empty();
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }finally {
            fileService.close();
        }
    }

    public void sourcePathWalk(Path source, Integer zipFileLevel, Integer driveCode, boolean isSkipEnable){
        LOGGER.info("sourcePathWalk source : "+source.toAbsolutePath().toString()+", zipFileLevel : "+zipFileLevel+", driveCode : "+driveCode);
        sourcePathWalk(source, zipFileLevel, null, null, driveCode, isSkipEnable);
        IMFODirectoryService dirService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
        try {
            if(SingleSystemProperty.SEARCH_DIR_STACK.size()>0) {
                SearchPathModel lastPathModel = SingleSystemProperty.SEARCH_DIR_STACK.peek();
                while (SingleSystemProperty.SEARCH_DIR_STACK.size()>0) {
                    Optional<EMFODirectory> opSearchDir = dirService.getMFODirectoryBySearchPathModelDrive(lastPathModel, driveCode);
                    if (opSearchDir.isPresent()) {
                        EMFODirectory searchDir = opSearchDir.get();
                        searchDir.setDirectorySearchStatus(1);
                        dirService.persistMFODirectory(searchDir);
                    }
                    LOGGER.info("Poped from the search stack : " + SingleSystemProperty.SEARCH_DIR_STACK.pop());
                    if(SingleSystemProperty.SEARCH_DIR_STACK.size()>0){
                        lastPathModel = SingleSystemProperty.SEARCH_DIR_STACK.peek();
                    }else {
                        break;
                    }
                }
            }
//
//            if (SingleSystemProperty.SEARCH_DIR_STACK.size() == 1) {
//                SearchPathModel lastPathModel = SingleSystemProperty.SEARCH_DIR_STACK.pop();
//                Optional<EMFODirectory> opSearchDir = dirService.getMFODirectoryBySearchPathModelDrive(lastPathModel, driveCode);
//                if (opSearchDir.isPresent()) {
//                    EMFODirectory searchDir = opSearchDir.get();
//                    searchDir.setDirectorySearchStatus(1);
//                    dirService.persistMFODirectory(searchDir);
//                }
//            }
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
//            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            LOGGER.error(ExceptionUtils.getStackTrace(e));
//            throw new RuntimeException(e);
        }finally {
            dirService.close();
            System.gc();
        }
    }
}
