package org.gloryseekers.infra.preferences;

public class MacAppPreferences extends AppPreferences {

    private MacAppPreferences() {
        super();
    }

    public static AppPreferences getInstance() {
        if(instance!=null) {
            instance = new MacAppPreferences();
        }
        return instance;
    }

    @Override
    protected String getAppDataURL() {
        return null;
    }
    
}
