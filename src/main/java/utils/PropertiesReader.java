/**
 * Copyright (c) 2021 Fundacion Jala.
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    public static String readFileProperty(String fileName, String key) throws IOException {
        FileInputStream fileStream = null;
        Properties properties = null;
        try {
            fileStream = new FileInputStream(fileName);
            properties = new Properties();
            properties.load(fileStream);
        } catch(FileNotFoundException notfoundE) {
            notfoundE.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fileStream.close();
        }
        return properties.getProperty(key);
    }
}
