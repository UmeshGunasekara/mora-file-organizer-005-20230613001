/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/20/2023 8:37 PM
 */
package com.slmora.learn.dao;

import com.slmora.learn.common.dao.IGenericDao;
import com.slmora.learn.jpa.entity.EMFOSystemProperty;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This Interface created for
 * <ul>
 *     <li>....</li>
 * </ul>
 *
 * @since 1.0
 *
 * <blockquote><pre>
 * <br>Version      Date            Editor              Note
 * <br>-------------------------------------------------------
 * <br>1.0          6/20/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
public interface IMFOSystemPropertyDao extends IGenericDao<byte[], EMFOSystemProperty>
{
    public Optional<byte[]> addMFOSystemProperty(EMFOSystemProperty systemProperty);

    public Optional<EMFOSystemProperty> getMFOSystemPropertyById(byte[] id);

    public Optional<EMFOSystemProperty> getMFOSystemPropertyByUUID(UUID uuidKey);

    public void deleteMFOSystemProperty(EMFOSystemProperty systemProperty);

    public List<EMFOSystemProperty> getAllMFOSystemProperties();

    public Optional<EMFOSystemProperty> getMFOSystemPropertyByCode(Integer code);

    public Optional<byte[]> persistReturnIdMFOSystemProperty(EMFOSystemProperty systemProperty);

    public EMFOSystemProperty persistMFOSystemProperty(EMFOSystemProperty systemProperty);
}
