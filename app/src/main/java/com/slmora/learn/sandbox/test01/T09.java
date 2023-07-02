/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/23/2023 12:09 AM
 */
package com.slmora.learn.sandbox.test01;

import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dao.impl.MFODirectoryDaoImpl;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.service.impl.MFODirectoryServiceImpl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

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
 * <br>1.0          6/23/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class T09 {
    public static void main(String[] args)
    {
        Path dir = Paths.get("D:\\MORA\\Video");
        MFODirectoryServiceImpl dirService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
        MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
        Optional<EMFODirectory> opEntityDir = null;
        try {
            opEntityDir = dirService.getMFODirectoryByDirectoryFullPathAndZipLevel(dir.toAbsolutePath().toString(),0);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        UUID uuid = uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(opEntityDir.get().getId());
        System.out.println(uuid.toString());
        dirService.close();
    }
}
