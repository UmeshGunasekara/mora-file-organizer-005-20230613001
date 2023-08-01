/*
 * Created by IntelliJ IDEA.
 * Language: Java
 * Property of Umesh Gunasekara
 * @Author: SLMORA
 * @DateTime: 6/26/2023 3:33 PM
 */
package com.slmora.learn.common.file.util;

import com.slmora.learn.common.logging.MoraLogger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  This Class created for File Read Access<br>
 *  Code<br>
 *  1 - {@link Stream}<br>
 *  2 - {@link Files#lines(Path, Charset)}<br>
 *  3 - {@link Files#readAllLines(Path, Charset)}<br>
 *  4 - {@link Files#newBufferedReader(Path, Charset)}<br>
 *  5 - {@link BufferedReader#lines()}<br>
 *  6 - {@link FileReader}<br>
 *  7 - {@link FileInputStream}<br>
 *  8 - {@link InputStream}<br>
 *  9 - {@link InputStreamReader}<br>
 *  0 - {@link File}<br>
 *  a - {@link Files#readAllBytes(Path)}<br>
 *  b - {@link ClassLoader}<br>
 *  c - {@link FileUtils#readFileToString(File, String)}<br>
 *  d - {@link IOUtils#toString(Reader)}<br>
 *  e - {@link Scanner}<br>
 *  f - {@link DataInputStream#read(byte[])}<br>
 *  g - {@link Files#readString(Path)}<br>
 *  h - {@link RandomAccessFile}<br>
 *  i - {@link FileChannel}<br>
 *  j - {@link ByteBuffer}<br>
 *  k - {@link URL}<br>
 *  l - {@link URLConnection}<br>
 *  m - {@link Base64}<br>
 *  n - {@link java.util.Base64.Encoder#encodeToString(byte[])}<br>
 *  o - {@link String#getBytes()}<br>
 *  p - {@link FileUtils#lineIterator(File, String)}<br>
 *  q - {@link LineIterator}<br>
 *  r - {@link BufferedInputStream}<br>
 *  s - {@link FileInputStream#getChannel()}<br>
 *  Methods
 *  <ul>
 *      <li>{@link #readByFilePathToList_21(String, Charset)} </li>
 *      <li>{@link #readByFilePathToList_21(String)}</li>
 *      <li>{@link #readByFilePathToList_3(String, Charset)}</li>
 *      <li>{@link #readByFilePathToList_451(String, Charset)}</li>
 *      <li>{@link #readByFilePathToList_451(String)}</li>
 *      <li>{@link #readByFilePathToList_65(String, Charset)}</li>
 *      <li>{@link #readByFilePathToList_65(String)}</li>
 *      <li>{@link #readByFilePathToList_7895(String, Charset)}</li>
 *      <li>{@link #readByFilePathToList_7895(String)}</li>
 *      <li>{@link #readByFilePathToList_0e(String, Charset)}</li>
 *      <li>{@link #readByFilePathToList_0e(String)}</li>
 *      <li>{@link #readByFilePathToListWithFiltersNotStartsWithAndNumberReplace_21(String, String, String)}</li>
 *      <li>{@link #readByFilePathToListWithFiltersNotStartsWithAndNumberReplace_65(String, String, String)}</li>
 *      <li>{@link #readByFilePathToListWithFiltersNotStartsWithAndNumberReplace_7895(String, String, String)}</li>
 *      <li>{@link #readByFilePathToBytes_a(String)}</li>
 *      <li>{@link #readByFilePathToString_a(String, Charset)}</li>
 *      <li>{@link #readByFilePathToString_a(String)}</li>
 *      <li>{@link #readByFilePathToStringOnClassLoader_b0c(String, String)}</li>
 *      <li>{@link #readByFilePathToStringOnClassLoader_b0c(String)}</li>
 *      <li>{@link #readByFilePathToStringOnClassLoader_78d(String, String)}</li>
 *      <li>{@link #readByFilePathToStringOnClassLoader_78d(String)}</li>
 *      <li>{@link #readByFilePathToStringBuilder_21(String, Charset)}</li>
 *      <li>{@link #readByFilePathToStringBuilder_21(String)}</li>
 *      <li>{@link #readByFilePathToStringBuilder_3(String, Charset)}</li>
 *      <li>{@link #readByFilePathToBytes_7f(String)}</li>
 *      <li>{@link #readByFilePathToString_7f(String, Charset)}</li>
 *      <li>{@link #readByFilePathToString_7f(String)}</li>
 *      <li>{@link #readByFilePathToString_21(String, Charset)}</li>
 *      <li>{@link #readByFilePathToString_21(String)}</li>
 *      <li>{@link #readByFilePathToString_g(String)}</li>
 *      <li>{@link #readByFilePathToString_hij(String)}</li>
 *      <li>{@link #readByFilePathToStringBuilderWithFiltersNotStartsWithAndNumberReplace_7895(String, String, String)}</li>
 *      <li>{@link #readByInputStreamToStringBuilder_95(InputStream)}</li>
 *      <li>{@link #readByInputStreamToStringBuilder_951(InputStream)}</li>
 *      <li>{@link #readByUrlPathToStringBuilder_kl8(String)}</li>
 *      <li>{@link #readByFilePathToEncodedString_amn(String)}</li>
 *      <li>{@link #readByFilePathToEncodedString_3mno(String, Charset)}</li>
 *      <li>{@link #readByFilePathToString_r7(String)}</li>
 *  </ul>
 *
 * @since   1.0
 *
 * <blockquote><pre>
 * <br>Version      Date            Editor              Note
 * <br>-------------------------------------------------------
 * <br>1.0          10/12/2022      SLMORA                Initial Code
 * </pre></blockquote>
 */
public class MoraFileReadAccessUtilities
{
    private final static MoraLogger LOGGER = MoraLogger.getLogger(MoraFileReadAccessUtilities.class);

    /**
     * Get string content of file as a List using {@link Files#lines(Path, Charset)}
     * and {@link Stream} on given {@code filePath} and {@code charset}.
     * Collect all lines in to list using {@link Collectors#toList()}.
     * In {@link Files#lines(Path, Charset)} internally using {@link FileChannel}.
     * In {@link Files#lines(Path)} internally using UTF-8 as default charset.
     * Read file with UTF-8 encoding if given {@code charset} is null.
     *
     * @param filePath      the path of the source file.
     * @param charset       the Charset encoding type.
     * @return              the List of Content encoded with given Charset.
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public Optional<List<String>> readByFilePathToList_21(String filePath, Charset charset) throws IOException {
        //read file into stream, try-with-resources
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with Charset {}", filePath, (null != charset)?charset.toString():null);
        try (Stream<String> fileStream = Files.lines(Paths.get(filePath),charset==null? StandardCharsets.UTF_8:charset)) {
            List<String> resultList = fileStream.collect(Collectors.toList());
            return Optional.of(resultList);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a List line by line using {@link #readByFilePathToList_21(String, Charset)}
     * on given {@code filePath} and {@code StandardCharsets.UTF_8}.
     *
     * @param filePath      the path of the source file.
     * @return              the List of Content
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since               1.0
     * @see                 #readByFilePathToList_21(String, Charset)
     */
    public Optional<List<String>> readByFilePathToList_21(String filePath) throws IOException {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {}", filePath);
        return readByFilePathToList_21(filePath,StandardCharsets.UTF_8);
    }

    /**
     * Get string content of file as a List using {@link Files#readAllLines(Path, Charset)} on
     * given {@code filePath} and {@code charset}.
     * In {@link Files#readAllLines(Path, Charset)} internally using {@link FileChannel},
     * {@link InputStreamReader} and {@link BufferedReader#lines()}.
     * Read file with UTF-8 encoding if given {@code charset} is null.
     * This will fetch full content in to memory and if the file is big the program may occur OutOfMemory error.
     *
     * @param filePath      the path of the source file.
     * @param charset       the Charset encoding type.
     * @return              the List of Content encoded with given Charset.
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since               1.0
     */
    public Optional<List<String>> readByFilePathToList_3(String filePath, Charset charset) throws IOException {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with Charset {}", filePath, (null != charset)?charset.toString():null);
        try{
            List<String> resultList = Files.readAllLines(Paths.get(filePath),charset==null?StandardCharsets.UTF_8:charset);
            return Optional.of(resultList);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a List using {@link Files#newBufferedReader(Path)},
     * {@link BufferedReader#lines()} and {@link Stream} on given {@code filePath} and {@code charset}.
     * Collect all lines in to list using {@link Collectors#toList()}.
     * In {@link Files#newBufferedReader(Path, Charset)} internally using {@link FileChannel}, {@link InputStreamReader}.
     * In {@link Files#newBufferedReader(Path)} internally using UTF-8 as default charset.
     *
     * @param filePath      the path of the source file.
     * @param charset       the Charset encoding type.
     * @return              the List of Content
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since               1.0
     */
    public Optional<List<String>> readByFilePathToList_451(String filePath, Charset charset) throws IOException {
        //read file into BufferedReader, try-with-resources
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with Charset {}", filePath, (null != charset)?charset.toString():null);
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath),charset==null?StandardCharsets.UTF_8:charset)) {
            List<String> resultList = br.lines().collect(Collectors.toList());
            return Optional.of(resultList);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a List using {@link #readByFilePathToList_451(String, Charset)}
     * on given {@code filePath} and {@code StandardCharsets.UTF_8}.
     *
     * @param filePath      the path of the source file.
     * @return              the List of Content
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since               1.0
     * @see                 #readByFilePathToList_451(String, Charset)
     */
    public Optional<List<String>> readByFilePathToList_451(String filePath) throws IOException {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with default Charset", filePath);
        return readByFilePathToList_451(filePath, StandardCharsets.UTF_8);
    }

    /**
     * Get string content of file as a List using {@link FileReader} and {@link BufferedReader#lines()}
     * on given {@code filePath} and {@code charset}.
     * Collect all lines in to list using while looping.
     * In {@link FileReader} internally using {@link File} and {@link FileInputStream}.
     *
     * @param filePath      the path of the source file.
     * @param charset       the Charset encoding type.
     * @return              the List of Content
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public Optional<List<String>> readByFilePathToList_65(String filePath, Charset charset) throws IOException {
        //read file into BufferedReader, try-with-resources
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with Charset {}", filePath, (null != charset)?charset.toString():null);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath, charset==null?StandardCharsets.UTF_8:charset))) {
            //with while loop
            List<String> resultList = new ArrayList<>();
            String line = null;
            while ((line = br.readLine()) != null){
                resultList.add(line);
            }
            return Optional.of(resultList);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a List using {@link #readByFilePathToList_65(String, Charset)}
     * on given {@code filePath} and {@code StandardCharsets.UTF_8}.
     *
     * @param filePath      the path of the source file.
     * @return              the List of Content
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since               1.0
     * @see                 #readByFilePathToList_65(String, Charset)
     */
    public Optional<List<String>> readByFilePathToList_65(String filePath) throws IOException {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with default Charset", filePath);
        return readByFilePathToList_65(filePath, StandardCharsets.UTF_8);
    }

    /**
     * Get string content of file as a List using {@link FileInputStream},
     * {@link InputStream}, {@link InputStreamReader} and {@link BufferedReader#lines()}
     * on given {@code filePath} and {@code charset}.
     * In {@link FileInputStream} internally using {@link File}.
     * Collect all lines in to list using while looping.
     *
     * @param filePath      the path of the source file.
     * @param charset       the Charset encoding type.
     * @return              the List of Content
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public Optional<List<String>> readByFilePathToList_7895(String filePath, Charset charset) throws
            IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with Charset {}", filePath, (null != charset)?charset.toString():null);
        try (
                InputStream iStream = new FileInputStream(filePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(iStream, charset==null?StandardCharsets.UTF_8:charset))
        ) {
            List<String> resultList = new ArrayList<>();
            String line = null;
            while ((line = br.readLine()) != null){
                resultList.add(line);
            }
            return Optional.of(resultList);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a List using {@link #readByFilePathToList_7895(String, Charset)},
     * on given {@code filePath} and {@code StandardCharsets.UTF_8}.
     *
     * @param filePath      the path of the source file.
     * @return              the List of Content
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since               1.0
     * @see                 #readByFilePathToList_7895(String, Charset)
     */
    public Optional<List<String>> readByFilePathToList_7895(String filePath) throws
            IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with default Charset", filePath);
        return readByFilePathToList_7895(filePath, StandardCharsets.UTF_8);
    }

    /**
     * Get string content of file as a List using {@link File} and {@link Scanner} on given {@code filePath} and {@code charset}.
     * In {@link Scanner} internally using {@link File} and {@link FileInputStream}.
     * Collect all lines in to list using while looping.
     *
     * @param filePath      the path of the source file.
     * @param charset       the Charset encoding type.
     * @return              the List of Content
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public Optional<List<String>> readByFilePathToList_0e(String filePath, Charset charset) throws IOException {
        //read file into Scanner, try-with-resources
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with Charset {}", filePath, (null != charset)?charset.toString():null);
        try (Scanner scanner = new Scanner(new File(filePath),charset==null?StandardCharsets.UTF_8:charset)) {
            List<String> resultList = new ArrayList<>();
            while (scanner.hasNext()) {
                resultList.add(scanner.nextLine());
            }
            return Optional.of(resultList);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a List using {@link #readByFilePathToList_0e(String, Charset)}
     * on given {@code filePath} and {@code StandardCharsets.UTF_8}.
     *
     * @param filePath      the path of the source file.
     * @return              the List of Content
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since               1.0
     * @see                 #readByFilePathToList_0e(String, Charset)
     */
    public Optional<List<String>> readByFilePathToList_0e(String filePath) throws IOException {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with default Charset", filePath);
        return readByFilePathToList_0e(filePath, StandardCharsets.UTF_8);
    }

    /**
     * Get string content of file as a List using {@link File}, {@link FileUtils#lineIterator(File, String)} and {@link LineIterator}
     * on given {@code filePath} and {@code charset}.
     * In {@link FileUtils#lineIterator(File, String)} internally using {@link File} and {@link FileInputStream} and
     * {@link IOUtils#lineIterator(InputStream, String)}.
     * The {@code charsetName} can be "US-ASCII", "ISO-8859-1", "UTF-8", "UTF-16BE", "UTF-16LE", "UTF-16".
     *
     * @param filePath      the path of the source file.
     * @param charsetName       the Name of Charset encoding type.
     * @return              the List of Content
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public Optional<List<String>> readByFilePathToList_0pq(String filePath, String charsetName) throws IOException {
        //read file into Scanner, try-with-resources
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with Charset {}", filePath, charsetName);
        try (LineIterator lineIterator = FileUtils.lineIterator(new File(filePath), charsetName==null?"UTF-8":charsetName)) {
            List<String> resultList = new ArrayList<>();
            while (lineIterator.hasNext()) {
                resultList.add(lineIterator.nextLine());
            }
            return Optional.of(resultList);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a List using {@link Files#lines(Path)} and {@link Stream}
     * on given {@code filePath} with filters of {@code notStartsWith} and {@code numberReplace}.
     * Filter lines not starts with given {@code notStartsWith} String.
     * From the filtered lines find value 10 and replace with given {@code numberReplace}
     * value with 4 leftpad with 0 (Ex: 33 converts to 0033).
     * Convert all out put in to Uppercase in Result.
     * In {@link Files#lines(Path)} internally using UTF-8 as default charset and {@link FileChannel}.
     * Collect all lines in to list using {@link Collectors#toList()}.
     *
     * @param filePath          the path of the source file.
     * @param notStartsWith     the string to filter not start with lines.
     * @param numberReplace     the String value of number for replace NUmber 10 in the line.
     * @return                  the Filtered List of Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public Optional<List<String>> readByFilePathToListWithFiltersNotStartsWithAndNumberReplace_21(String filePath, String notStartsWith, String numberReplace) throws IOException {
        //read file into stream, try-with-resources
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} lines not started with {} and replace value 10 replaced to 4 digit value with {}", filePath, notStartsWith, numberReplace);
        try (Stream<String> fileStream = Files.lines(Paths.get(filePath))) {
            List<String> resultList = fileStream
                    .filter(line -> !line.toUpperCase().startsWith(notStartsWith.toUpperCase()))
                    .map(line -> line.replaceAll("10", StringUtils.leftPad(numberReplace, 4, "0")))
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
            return Optional.of(resultList);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a List using {@link FileReader} and {@link BufferedReader#lines()}
     * on given {@code filePath} with filters of {@code notStartsWith} and {@code numberReplace}.
     * Filter lines not starts with given {@code notStartsWith} String.
     * From the filtered lines find value 10 and replace with given {@code numberReplace} value with 4 leftpad with 0 (Ex: 33 converts to 0033).
     * Convert all out put in to Uppercase in Result.
     * Collect all lines in to list using while looping.
     * In {@link FileReader} internally using {@link File} and {@link FileInputStream}.
     *
     * @param filePath          the path of the source file.
     * @param notStartsWith     the string to filter not start with lines.
     * @param numberReplace     the String value of number for replace NUmber 10 in the line.
     * @return                  the Filtered List of Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public Optional<List<String>> readByFilePathToListWithFiltersNotStartsWithAndNumberReplace_65(String filePath, String notStartsWith, String numberReplace) throws
            IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} lines not started with {} and replace value 10 replaced to 4 digit value with {}", filePath, notStartsWith, numberReplace);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8))) {
            String line = null;
            List<String> resultList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if(!line.toUpperCase().startsWith(notStartsWith.toUpperCase())){
                    if(line.contains("10")){
                        line = line.replaceAll("10", StringUtils.leftPad(numberReplace, 4, "0"));
                    }
                    line = line.toUpperCase();
                    resultList.add(line);
                }
            }
            return Optional.of(resultList);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a List using {@link FileInputStream}, {@link InputStream},
     * {@link InputStreamReader} and {@link BufferedReader#lines()} on given {@code filePath} with filters
     * of {@code notStartsWith} and {@code numberReplace}.
     * Filter lines not starts with given {@code notStartsWith} String. <br>
     * From the filtered lines find value 10 and replace with given {@code numberReplace} value with 4 leftpad with 0 (Ex: 33 converts to 0033). <br>
     * Convert all out put in to Uppercase in Result.
     * Collect all lines in to list using while looping.
     * In {@link FileInputStream} internally using {@link File}.
     *
     * @param filePath          the path of the source file.
     * @param notStartsWith     the string to filter not start with lines.
     * @param numberReplace     the String value of number for replace NUmber 10 in the line.
     * @return                  the Filtered List of Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public Optional<List<String>> readByFilePathToListWithFiltersNotStartsWithAndNumberReplace_7895(String filePath, String notStartsWith, String numberReplace) throws
            IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} lines not started with {} and replace value 10 replaced to 4 digit value with {}", filePath, notStartsWith, numberReplace);
        StringBuilder contentBuilder = new StringBuilder(1024);

        try (
                InputStream iStream = new FileInputStream(filePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(iStream,StandardCharsets.UTF_8))
        ){
            String line = null;
            List<String> resultList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if(!line.toUpperCase().startsWith(notStartsWith.toUpperCase())){
                    if(line.contains("10")){
                        line = line.replaceAll("10", StringUtils.leftPad(numberReplace, 4, "0"));
                    }
                    line = line.toUpperCase();
                    resultList.add(line);
                }
            }
            return Optional.of(resultList);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get content of file as byte Array using {@link Files#readAllBytes(Path)} on given {@code filePath}
     * In {@link Files#readAllBytes(Path)} internally using {@link java.nio.channels.Channels} and {@link InputStream}.
     * This will take full content of byte in to memory so it may cause on OutOfMemory error.
     * This allows upto Java 7
     *
     * @param filePath          the path of the source file.
     * @return                  the byte array of File Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public byte[] readByFilePathToBytes_a(String filePath) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {}", filePath);
        try {
            return Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a single String using {@link #readByFilePathToBytes_a(String)}
     * on given {@code filePath} and {@code charset}.
     * This allows upto Java 7
     *
     * @param filePath          the path of the source file.
     * @param charset           the Charset encoding type.
     * @return                  the Full String of File Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     * @see                     #readByFilePathToBytes_a(String)
     */
    public String readByFilePathToString_a(String filePath, Charset charset) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with Charset {}", filePath, (null != charset)?charset.toString():null);
        try {
            return new String (readByFilePathToBytes_a(filePath), charset==null?StandardCharsets.UTF_8:charset);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a single String using {@link #readByFilePathToString_a(String, Charset)}
     * on given {@code filePath} and {@code StandardCharsets.UTF_8}.
     * This allows upto Java 7
     *
     * @param filePath          the path of the source file.
     * @return                  the Full String of File Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     * @see                     #readByFilePathToBytes_a(String)
     * @see                     #readByFilePathToString_a(String, Charset)
     */
    public String readByFilePathToString_a(String filePath) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {}", filePath);
        try {
            return readByFilePathToString_a (filePath,StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as single String using {@link File} and {@link FileUtils#readFileToString(File, String)}.
     * on given {@code fileName} and {@code charsetName}.
     * Get the resources path using {@link ClassLoader} and located the file with given {@code fileName}.
     * In {@link FileUtils#readFileToString(File, String)} internally using {@link IOUtils#toString(Reader)} and {@link InputStream}.
     * The {@code charsetName} can be "US-ASCII", "ISO-8859-1", "UTF-8", "UTF-16BE", "UTF-16LE", "UTF-16".
     * This allows upto Java 7
     *
     * @param fileName          the name of the source file.
     * @param charsetName       the Name of Charset encoding type.
     * @return                  the Full String of File Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public String readByFilePathToStringOnClassLoader_b0c(String fileName, String charsetName) throws IOException
    {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            LOGGER.debug(Thread.currentThread().getStackTrace(), "Read class path File {} with Charset {}", file.getAbsolutePath(), charsetName);
            return FileUtils.readFileToString(file, charsetName==null?"UTF-8":charsetName);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a single String using {@link #readByFilePathToStringOnClassLoader_b0c(String, String)}
     * on given {@code filePath} and {@code "UTF-8"}.
     * This allows upto Java 7
     *
     * @param fileName          the name of the source file.
     * @return                  the Full String of File Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     * @see                     #readByFilePathToStringOnClassLoader_b0c(String, String)
     */
    public String readByFilePathToStringOnClassLoader_b0c(String fileName) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read class path File {} with Charset {}", fileName);
        return readByFilePathToStringOnClassLoader_b0c(fileName, "UTF-8");
    }

    /**
     * Get string content of file as a single String using {@link FileInputStream}, {@link InputStream} and {@link IOUtils#toString(Reader)}
     * on given {@code filePath} and {@code charsetName}.
     * The {@code charsetName} can be "US-ASCII", "ISO-8859-1", "UTF-8", "UTF-16BE", "UTF-16LE", "UTF-16".
     * This allows upto Java 7
     *
     * @param filePath          the path of the source file.
     * @param charsetName       the Name of Charset encoding type.
     * @return                  the Full String of File Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public String readByFilePathToStringOnClassLoader_78d(String filePath, String charsetName) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with Charset {}", filePath, charsetName);
        try (InputStream iStream = new FileInputStream(filePath)){
            return IOUtils.toString(iStream, charsetName==null?"UTF-8":charsetName);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as single String using {@link #readByFilePathToStringOnClassLoader_78d(String, String)}
     * on given {@code filePath} and {@code "UTF-8"}.
     *
     * @param filePath          the path of the source file.
     * @return                  the Full String of File Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     * @see                     #readByFilePathToStringOnClassLoader_78d(String, String)
     */
    public String readByFilePathToStringOnClassLoader_78d(String filePath) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {}", filePath);
        return readByFilePathToStringOnClassLoader_78d(filePath,  "UTF-8");
    }

    /**
     * Get string content of file as a StringBuilder using {@link Files#lines(Path, Charset)}
     * and {@link Stream} on given {@code filePath} and {@code charset}.
     * Collect all lines in to StringBuilder using {@link StringBuilder#append(String)}.
     * The last character of the StringBuilder is NewLine {@code "\n"}.
     * In {@link Files#lines(Path, Charset)} internally using {@link FileChannel}.
     * In {@link Files#lines(Path)} internally using UTF-8 as default charset.
     * Read file with UTF-8 encoding if given {@code charset} is null.
     * {@link Files#lines(Path, Charset)} method doesn't include line-termination character.
     * If we want to read all text from a file in to a String we can use this
     *
     * @param filePath          the path of the source file.
     * @param charset           the Charset encoding type.
     * @return                  the Full StringBuilder of File Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public StringBuilder readByFilePathToStringBuilder_21(String filePath, Charset charset) throws IOException
    {
        //read file into stream, try-with-resources
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with Charset {}", filePath, (null != charset)?charset.toString():null);
        try (Stream<String> fileStream = Files.lines(Paths.get(filePath),charset==null?StandardCharsets.UTF_8:charset)){
            StringBuilder contentBuilder = new StringBuilder();
            fileStream.forEach(line -> contentBuilder.append(line).append("\n"));
//            fileStream.reduce((a,b) -> String.valueOf(contentBuilder.append(a).append("\n").append(b)));
            return contentBuilder;
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a StringBuilder using {@link #readByFilePathToStringBuilder_21(String, Charset)}
     * on given {@code filePath} and {@code StandardCharsets.UTF_8}.
     *
     * @param filePath      the path of the source file.
     * @return              the Full StringBuilder of File Content
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since               1.0
     * @see                 #readByFilePathToStringBuilder_21(String, Charset)
     */
    public StringBuilder readByFilePathToStringBuilder_21(String filePath) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {}", filePath);
        return readByFilePathToStringBuilder_21(filePath, StandardCharsets.UTF_8);
    }

    /**
     * Get string content of file as a StringBuilder using {@link Files#readAllLines(Path, Charset)} on
     * given {@code filePath} and {@code charset}.
     * Collect all lines in to StringBuilder using {@link StringBuilder#append(String)}.
     * The last character of the StringBuilder is NewLine {@code "\n"}.
     * In {@link Files#readAllLines(Path, Charset)} internally using {@link FileChannel},
     * {@link InputStreamReader} and {@link BufferedReader#lines()}.
     * Read file with UTF-8 encoding if given {@code charset} is null.
     * {@link Files#readAllLines(Path, Charset)} method doesn't include line-termination character.
     * If we want to read all text from a file in to a String we can use this
     *
     * @param filePath      the path of the source file.
     * @param charset       the Charset encoding type.
     * @return              the Full StringBuilder of File Content encoded with given Charset.
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since               1.0
     */
    public StringBuilder readByFilePathToStringBuilder_3(String filePath, Charset charset) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with Charset {}", filePath, (null != charset)?charset.toString():null);
        try {
            StringBuilder contentBuilder = new StringBuilder(1024);
            List lines = Files.readAllLines(Paths.get(filePath),charset==null?StandardCharsets.UTF_8:charset);
            lines.forEach(line -> contentBuilder.append(line).append("\n"));
            return contentBuilder;
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get content of file as byte Array using {@link FileInputStream}, {@link DataInputStream#read(byte[])}
     * on given {@code filePath}.
     * This allows upto Java 7
     *
     * @param filePath          the path of the source file.
     * @return                  the byte array of File Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public byte[] readByFilePathToBytes_7f(String filePath) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {}", filePath);
        byte[] bytes = null;
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(filePath))){
            int nBytesToRead = dataInputStream.available();
            if(nBytesToRead > 0) {
                bytes = new byte[nBytesToRead];
                dataInputStream.read(bytes);
            }
            return bytes;
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a single String using {@link #readByFilePathToBytes_7f(String)}
     * on given {@code filePath} and {@code charset}.
     * This allows upto Java 7
     *
     * @param filePath          the path of the source file.
     * @param charset           the Charset encoding type.
     * @return                  the Full String of File Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     * @see                     #readByFilePathToBytes_7f(String)
     */
    public String readByFilePathToString_7f(String filePath, Charset charset) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with Charset {}", filePath, (null != charset)?charset.toString():null);
        try {
            return new String (readByFilePathToBytes_7f(filePath), charset==null?StandardCharsets.UTF_8:charset);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a Optional single String using {@link #readByFilePathToString_7f(String, Charset)}
     * on given {@code filePath} and {@code StandardCharsets.UTF_8}.
     * This allows upto Java 7
     *
     * @param filePath          the path of the source file.
     * @return                  the Optional Full String of File Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     * @see                     #readByFilePathToBytes_7f(String)
     * @see                     #readByFilePathToString_7f(String, Charset)
     */
    public Optional<String> readByFilePathToString_7f(String filePath) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {}", filePath);
        try {
            String content = readByFilePathToString_7f(filePath, StandardCharsets.UTF_8);
            return content==null?Optional.empty():Optional.of(content);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a single String using {@link Files#lines(Path, Charset)}
     * and {@link Stream} on given {@code filePath} and {@code charset}.
     * Collect all lines in to a String using {@link Collectors#joining(CharSequence)} with {@link System#lineSeparator()}.
     * The last character of the String is not NewLine {@link System#lineSeparator()}.
     * In {@link Files#lines(Path, Charset)} internally using {@link FileChannel}.
     * In {@link Files#lines(Path)} internally using UTF-8 as default charset.
     * Read file with UTF-8 encoding if given {@code charset} is null.
     *
     * @param filePath          the path of the source file.
     * @param charset           the Charset encoding type.
     * @return                  the Full String of File Content encoded with given Charset.
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public String readByFilePathToString_21(String filePath, Charset charset) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with Charset {}", filePath, (null != charset)?charset.toString():null);
        //read file into BufferedReader, try-with-resources
        try {
            String content = Files.lines(Paths.get(filePath),charset==null?StandardCharsets.UTF_8:charset).collect(Collectors.joining(System.lineSeparator()));
            return content;
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a single String using {@link #readByFilePathToString_21(String, Charset)}
     * on given {@code filePath} and {@code StandardCharsets.UTF_8}.
     *
     * @param filePath      the path of the source file.
     * @return              the List of Content
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since               1.0
     * @see                 #readByFilePathToString_21(String, Charset)
     */
    public String readByFilePathToString_21(String filePath) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {}", filePath);
        return readByFilePathToString_21(filePath, StandardCharsets.UTF_8);
    }

    /**
     * Get string content of file as a single String using {@link Files#readString(Path)} on given {@code filePath}.
     * In {@link Files#readString(Path)} internally using {@code jdk.internal.access.JavaLangAccess#newStringNoRepl(byte[], Charset)},
     * {@link Files#readAllBytes(Path)}.
     * In {@link Files#readString(Path)} internally using UTF-8 as default charset.
     *
     * @param filePath      the path of the source file.
     * @return              the Full String of File Content.
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since               1.0
     */
    public String readByFilePathToString_g(String filePath) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {}", filePath);
        //read file into BufferedReader, try-with-resources
        try {
            String content = Files.readString(Paths.get(filePath));
            return content;
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a single String using {@link RandomAccessFile}, {@link FileChannel},
     * {@link ByteBuffer} on given {@code filePath}.
     * In {@link RandomAccessFile} internally using {@link File}.
     * To get {@link FileChannel} uses {@link RandomAccessFile#getChannel()} and
     * setup {@link ByteBuffer} using of {@link FileChannel} then read for {@link ByteBuffer} using
     * {@link FileChannel#read(ByteBuffer)} finally to flip the {@link ByteBuffer} use {@link ByteBuffer#flip()}
     *
     * @param filePath          the path of the source file.
     * @return                  the Full String of File Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public String readByFilePathToString_hij(String filePath) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {}", filePath);
        try (RandomAccessFile accessFile = new RandomAccessFile(filePath, "r");
             FileChannel channel = accessFile.getChannel()){
            int bufferSize = 1024;
            if (bufferSize > channel.size()) {
                bufferSize = (int) channel.size();
            }
            ByteBuffer buff = ByteBuffer.allocate(bufferSize);
            channel.read(buff);
            buff.flip();
            String content = new String(buff.array(),StandardCharsets.UTF_8);
            return content;
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a StringBuilder using {@link FileInputStream}, {@link InputStream},
     * {@link InputStreamReader} and {@link BufferedReader#lines()} on given {@code filePath} with filters
     * of {@code notStartsWith} and {@code numberReplace}.
     * Filter lines not starts with given {@code notStartsWith} String. <br>
     * From the filtered lines find value 10 and replace with given {@code numberReplace} value with 4 leftpad with 0 (Ex: 33 converts to 0033). <br>
     * Convert all out put in to Uppercase in Result.
     * Collect all lines in to list using while looping.
     * Collect all lines in to StringBuilder using {@link StringBuilder#append(String)}.
     * The last character of the StringBuilder is NewLine {@code "\n"}.
     * In {@link FileInputStream} internally using {@link File}.
     *
     * @param filePath          the path of the source file.
     * @param notStartsWith     the string to filter not start with lines.
     * @param numberReplace     the String value of number for replace NUmber 10 in the line.
     * @return                  the Filtered StringBuilder of Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public StringBuilder readByFilePathToStringBuilderWithFiltersNotStartsWithAndNumberReplace_7895(String filePath, String notStartsWith, String numberReplace) throws
            IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} lines not started with {} and replace value 10 replaced to 4 digit value with {}", filePath, notStartsWith, numberReplace);
        try(
                InputStream iStream = new FileInputStream(filePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(iStream))
        ) {
            StringBuilder contentBuilder = new StringBuilder(1024);
            String line = null;
            while ((line = br.readLine()) != null) {
                if(!line.toUpperCase().startsWith(notStartsWith.toUpperCase())){
                    if(line.contains("10")){
                        line = line.replaceAll("10", StringUtils.leftPad(numberReplace, 4, "0"));
                    }
                    line = line.toUpperCase();
                    contentBuilder.append(line).append("\n");
                }
            }
            return contentBuilder;
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a StringBuilder using {@link InputStreamReader} and {@link BufferedReader#lines()}
     * on given {@link InputStream}.
     * Collect all lines in to StringBuilder using {@link StringBuilder#append(String)}.
     * The last character of the StringBuilder is NewLine {@code "\n"}.
     *
     * @param inputStream   the Input Stream of the source file.
     * @return              the Full StringBuilder of File Content
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public StringBuilder readByInputStreamToStringBuilder_95(InputStream inputStream) throws
            IOException
    {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder resultStringBuilder = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
            return resultStringBuilder;
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a StringBuilder using {@link InputStreamReader}, {@link BufferedReader#lines()}
     * and {@link Stream} on given {@link InputStream}.
     * Collect all lines in to StringBuilder using {@link StringBuilder#append(String)}.
     * The last character of the StringBuilder is NewLine {@code "\n"}.
     *
     * @param inputStream   the Input Stream of the source file.
     * @return              the Full StringBuilder of File Content
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public StringBuilder readByInputStreamToStringBuilder_951(InputStream inputStream) throws
            IOException
    {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))){
            StringBuilder contentBuilder = new StringBuilder(1024);
            br.lines().forEach(line -> contentBuilder.append(line).append("\n"));
            return contentBuilder;
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }

    }

    /**
     * Get string content of file as Full StringBuilder using {@link URL}, {@link URLConnection},
     * {@link InputStream} and {@link #readByInputStreamToStringBuilder_95(InputStream)} by given {@code urlPath}.
     *
     * @param urlPath           the URL Path of the source file.
     * @return                  the Full StringBuilder of File Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     * @see                     #readByInputStreamToStringBuilder_95(InputStream)
     */
    public StringBuilder readByUrlPathToStringBuilder_kl8(String urlPath) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read URL {}", urlPath);
        InputStream inputStream = null;
        StringBuilder resultStringBuilder = new StringBuilder();
        //read file into BufferedReader, try-with-resources
        try {
            URL urlObject = new URL(urlPath);
            URLConnection urlConnection = urlObject.openConnection();
            inputStream = urlConnection.getInputStream();
            resultStringBuilder = readByInputStreamToStringBuilder_95(inputStream);
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                LOGGER.error(Thread.currentThread().getStackTrace(), e);
                throw e;
            }
            return resultStringBuilder;
        }
    }

    /**
     * Get content of file as a single encoded String using {@link #readByFilePathToBytes_a(String)},
     * {@link Base64}, {@code java.util.Base64.Encoder#RFC4648} and {@link java.util.Base64.Encoder#encodeToString(byte[])}
     * on given {@code filePath}.
     *
     * @param filePath          the path of the source file.
     * @return                  the encoded String of File Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     * @see                     #readByFilePathToBytes_a(String)
     */
    public String readByFilePathToEncodedString_amn(String filePath) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {}", filePath);
        byte[] fileBytes = readByFilePathToBytes_a(filePath);
        return Base64.getEncoder().encodeToString(fileBytes);
    }

    /**
     * Get content of file as a single encoded String using {@link #readByFilePathToStringBuilder_3(String, Charset)},
     * {@link Base64}, {@code java.util.Base64.Encoder#RFC4648}, {@link java.util.Base64.Encoder#encodeToString(byte[])},
     * {@link String#getBytes()} on given {@code filePath} and {@code charset}.
     *
     * @param filePath          the path of the source file.
     * @param charset           the Charset encoding type.
     * @return                  the encoded String of File Content
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     * @see                     #readByFilePathToStringBuilder_3(String, Charset)
     */
    public String readByFilePathToEncodedString_3mno(String filePath, Charset charset) throws
            IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {} with Charset {}", filePath, (null != charset)?charset.toString():null);
        StringBuilder fileContent = readByFilePathToStringBuilder_3(filePath, charset);
        return Base64.getEncoder().encodeToString(fileContent.toString().getBytes());

    }

    /**
     * Get string content of file as a StringBuilder using {@link InputStreamReader} and {@link BufferedInputStream}
     * on given {@code filePath}.
     * Collect all lines in to StringBuilder using {@link StringBuilder#append(String)}.
     * Byte segment size is 4096 bytes.
     *
     * @param filePath          the path of the source file.
     * @return              the Full StringBuilder of File Content
     * @throws IOException  if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public StringBuilder readByFilePathToString_r7(String filePath) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {}", filePath);
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath))){
            StringBuilder resultStringBuilder = new StringBuilder();
            byte[] buff = new byte[4096];
            int len;
            while ((len = in.read(buff)) != -1) {
                // process data here: bbuf[0] thru bbuf[len - 1]
                resultStringBuilder.append(new String(buff,StandardCharsets.UTF_8));
            }
            return resultStringBuilder;
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

    /**
     * Get string content of file as a single String using {@link FileInputStream#getChannel()}, {@link FileChannel},
     * {@link ByteBuffer} on given {@code filePath}.
     * In {@link FileInputStream} internally using {@link File}.
     * To get {@link FileChannel} uses {@link FileInputStream#getChannel()} and
     * setup {@link ByteBuffer} using of {@link FileChannel} then read for {@link ByteBuffer} using
     * {@link FileChannel#read(ByteBuffer)} finally to flip the {@link ByteBuffer} use {@link ByteBuffer#flip()}.
     * Collect all lines in to StringBuilder using {@link StringBuilder#append(String)}.
     * Byte segment size is 4096 bytes.
     * To clean {@link ByteBuffer} use {@link ByteBuffer#clear()} to ready for read again.
     *
     * @param filePath          the path of the source file.
     * @return                  the Full StringBuilder of File Content.
     * @throws IOException      if an I/O error occurs opening the file.
     *
     * @since                   1.0
     */
    public StringBuilder readByFilePathToString_sij(String filePath) throws IOException
    {
        LOGGER.debug(Thread.currentThread().getStackTrace(), "Read File {}", filePath);
        try (FileChannel inputChannel = new FileInputStream(filePath).getChannel()){
            StringBuilder resultStringBuilder = new StringBuilder();
            int bufferSize = 1024*4;
            if (bufferSize > inputChannel.size()) {
                bufferSize = (int) inputChannel.size();
            }
            ByteBuffer buff = ByteBuffer.allocate(bufferSize);
//            inputChannel.read(buff);
//            buff.flip();
//            String content = new String(buff.array(),StandardCharsets.UTF_8);
//            return content;
            while (inputChannel.read(buff) != -1) {
                buff.flip();
                resultStringBuilder.append(new String(buff.array(),StandardCharsets.UTF_8));
                buff.clear();
            }
            return resultStringBuilder;
        } catch (IOException e) {
            LOGGER.error(Thread.currentThread().getStackTrace(), e);
            throw e;
        }
    }

}
