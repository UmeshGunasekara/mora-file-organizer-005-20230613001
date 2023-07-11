/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 7/10/2023 3:42 PM
 */
package com.slmora.learn.sandbox.test01;

import com.slmora.learn.common.cryptography.hmac.util.EHmacAlgorithm;
import com.slmora.learn.common.cryptography.hmac.util.MoraHMACUtilities;
import com.slmora.learn.dao.impl.MFODirectoryDaoImpl;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.model.SearchPathModel;
import com.slmora.learn.service.IMFODirectoryService;
import com.slmora.learn.service.impl.MFODirectoryServiceImpl;

import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
 * <br>1.0          7/10/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class T24 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException
    {
        IMFODirectoryService directoryService = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
        SearchPathModel model = new SearchPathModel();

        model.setPath(Paths.get("D:\\MORA\\Video\\TM\\ABC\\AA\\ABC\\AA"));
        model.setZipParentFile(Paths.get("D:\\MORA\\Video\\TM\\ABC\\AA\\ABC.zip"));
        model.setZipLevel(1);
        model.setZipParentFileLevel(0);

        MoraHMACUtilities hmacUtilities = new MoraHMACUtilities();

        System.out.println(hmacUtilities.hmacStringByMacUsingAlgorithmKey_156(
                EHmacAlgorithm.SHA256.getHmacAlgorithmNameString(),
                model.getZipParentFile().toAbsolutePath().toString(), model.getZipParentFile().getFileName().toString()));

        Optional<EMFODirectory> eDir = directoryService.getMFODirectoryBySearchPathModelDrive(model, 1);
        System.out.println(eDir.get().getDirectoryFullPath());
    }
}
