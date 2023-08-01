/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/13/2023 9:17 PM
 */
package com.slmora.learn.common.ds.hibernate.util;

import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.common.uuid.util.MoraUuidUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
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
 * <br>1.0          6/13/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class MoraUUIDGenerator implements
        IdentifierGenerator
{
    private final static MoraLogger LOGGER = MoraLogger.getLogger(MoraUUIDGenerator.class);

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws
            HibernateException
    {
        MoraUuidUtilities uuidUtilities = new MoraUuidUtilities();
        UUID uuid = UUID.randomUUID();
        return uuidUtilities.getOrderedUUIDByteArrayFromUUIDWithApacheCommons(uuid);
    }
}
