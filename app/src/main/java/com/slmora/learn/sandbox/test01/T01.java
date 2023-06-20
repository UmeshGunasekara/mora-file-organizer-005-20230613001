/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/19/2023 7:16 PM
 */
package com.slmora.learn.sandbox.test01;

import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import com.slmora.learn.dao.impl.MFODirectoryDaoImpl;
import com.slmora.learn.jpa.entity.EMFODirectory;
import com.slmora.learn.service.impl.MFODirectoryServiceImpl;

import java.util.List;
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
 * <br>1.0          6/19/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class T01 {
    public static void main(String[] args)
    {
        System.out.println("Hello 1");

        MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();

        EMFODirectory dir01 = new EMFODirectory();
//        dir01.setDirectoryCode(1);
        dir01.setDirectoryName("TEST11");

        long startTime = System.nanoTime();

        MFODirectoryServiceImpl service = new MFODirectoryServiceImpl(new MFODirectoryDaoImpl());
        Optional<byte[]> id = service.addMFODirectory(dir01);

        if(id.isPresent()) {
            UUID uuid = uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(id.get());
            System.out.println(uuid.toString());
        }

        List<EMFODirectory> entries = service.getAllMFODirectories();
        if(entries.size()>0){
//            entries.stream().forEach(i->System.out.println(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(i.getDirectoryId()).toString()));
            entries.stream().forEach(i->System.out.println(uuidUtilities.getUUIDFromOrderedUUIDByteArrayWithApacheCommons(i.getId()).toString()));
        }

        service.close();
        // Test
//        Transaction transaction = null;
//        try (Session session = HibernateDBCPAnoUtil.getHibernateSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(dir01);
//            transaction.commit();
//            System.out.println("Added Directory 01 : " + dir01.getDirectoryName());
//        } catch (Throwable throwable) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            throwable.printStackTrace();
//        }
        System.out.println();

        System.out.println("Hello 2");
    }
}
