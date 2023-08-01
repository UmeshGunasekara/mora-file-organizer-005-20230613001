/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 7/19/2023 9:11 PM
 */
package com.slmora.learn.model;

import com.slmora.learn.common.logging.MoraLogger;
import com.slmora.learn.jpa.entity.EMFOFileFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
 * <br>1.0          7/19/2023      SLMORA                Initial Code
 * </pre></blockquote>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class SourceJsonModel {
    private final static MoraLogger LOGGER = MoraLogger.getLogger(SourceJsonModel.class);

    String source;
    Integer drive;
    Integer isskip; // 0-noskip/ 1-skip/ 2-zipnoskip

    public static Optional<List<SourceJsonModel>> getSourceJsonModelListFromJsonFile(String sourceJsonPath){
        try (FileReader reader = new FileReader(sourceJsonPath)){
            JSONParser jsonParser = new JSONParser();
            List<SourceJsonModel> resultList = new ArrayList<>();

            Object obj = jsonParser.parse(reader);
            JSONArray sourceList = (JSONArray) obj;
            LOGGER.getLogger().info(sourceList);

            for(Object source: sourceList){
                JSONObject sourceObject = (JSONObject) source;
                JSONObject selectedSource = (JSONObject) sourceObject.get("data");
//                SourceJsonModel model = SourceJsonModel.of((String)selectedSource.get("source"), ((Long)selectedSource.get("drive")).intValue(), (Boolean)selectedSource.get("isskip"));
                SourceJsonModel model = SourceJsonModel.of((String)selectedSource.get("source"), ((Long)selectedSource.get("drive")).intValue(), ((Long)selectedSource.get("isskip")).intValue());
                resultList.add(model);
            }
            return Optional.of(resultList);
        } catch (FileNotFoundException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
        } catch (ParseException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
        }
        return Optional.empty();
    }
}
