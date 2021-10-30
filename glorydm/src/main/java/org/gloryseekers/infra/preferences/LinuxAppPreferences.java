package org.gloryseekers.infra.preferences;

public class LinuxAppPreferences extends AppPreferences {

    private LinuxAppPreferences() {
        super();
    }

    public static AppPreferences getInstance() {
        if(instance==null) {
            instance = new LinuxAppPreferences();
        }
        return instance;
    }

    @Override
    protected String getAppDataURL() {
        return System.getProperty("user.home") + "/." + this.appName;
    }
    
}
