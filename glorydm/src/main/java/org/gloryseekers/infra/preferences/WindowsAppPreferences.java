package org.gloryseekers.infra.preferences;

public class WindowsAppPreferences extends AppPreferences {

    private WindowsAppPreferences() {
        super();
    }

    public static AppPreferences getInstance() {
        if(instance!=null) {
            instance = new WindowsAppPreferences();
        }
        return instance;
    }

    @Override
    protected String getAppDataURL() {
        return System.getenv("APPDATA") + "\\" + this.appName + "\\";
    }

    
    
}
