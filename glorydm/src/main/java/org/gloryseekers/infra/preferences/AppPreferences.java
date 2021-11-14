package org.gloryseekers.infra.preferences;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Properties;

import org.gloryseekers.domain.model.LogType;
import org.gloryseekers.infra.log.GSLogger;

public abstract class AppPreferences {

  protected final String PREFERENCES_FILE_NAME = "glorydm.properties";

  protected static AppPreferences instance;

  private Properties properties;

  private OutputStream outputStream;

  protected String appDataURL;

  protected String appName = "glorydm";



  protected AppPreferences() {
    appDataURL = getAppDataURL();
    initialize(this.appDataURL);
  }

  protected void initialize(String appDataURL) {
    GSLogger.log(AppPreferences.class, LogType.INFO, "INIT AppPreferences");
    properties = new Properties();
    Path path = Paths.get(appDataURL);
    File file;
    try {
      Files.createDirectories(path);
      file = new File(appDataURL + "/glorydm.properties");
      GSLogger.log(AppPreferences.class, LogType.INFO, appDataURL + "/glorydm.properties");
      System.out.println((file.createNewFile())?"- Created":"- Exist"); //This should be refactor to be logged properly.
      try {
          InputStream inputStream = new FileInputStream(file);
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream);//I guess this should be open
          properties.load(inputStreamReader);
          GSLogger.log(AppPreferences.class, LogType.INFO, "Properties loaded");
          this.outputStream = new FileOutputStream(file);
          GSLogger.log(AppPreferences.class, LogType.INFO, "Setted outputStream");
      } catch (IOException e) {
        GSLogger.log(AppPreferences.class, LogType.ERROR, e.getMessage());
      }
    } catch (IOException e1) {
      GSLogger.log(AppPreferences.class, LogType.ERROR, e1.getMessage());
    }
  }

  public static AppPreferences getSystemInstance() {
    String osName = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);

    if ((osName.indexOf("mac") >= 0) || (osName.indexOf("darwin") >= 0)) {
      return MacAppPreferences.getInstance();
    } else if (osName.indexOf("win") >= 0) {
      return WindowsAppPreferences.getInstance();
    } else if (osName.indexOf("nux") >= 0) {
      return LinuxAppPreferences.getInstance();
    } else {
      return ResourcesAppPreferences.getInstance();
    }

  }

  protected abstract String getAppDataURL();

  /**
   * Returns the value associated with the given key.
   * 
   * @param key the key associated with one value.
   * @return the value associated.
   */
  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public void setProperty(String key, String value) {
    properties.put(key,value);
  }

  public boolean store() {
    try {
      properties.store(this.outputStream, null);
    } catch (IOException e) {
      GSLogger.log(AppPreferences.class, LogType.ERROR, e.getMessage());
      return false;
    }
    return true;
  }

}
