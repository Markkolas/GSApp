package org.gloryseekers.infra.preferences;

import java.net.URISyntaxException;

public class ResourcesAppPreferences extends AppPreferences {

    public ResourcesAppPreferences() {
        super();
    }

    public static AppPreferences getInstance() {
        if(instance!=null) {
            instance = new ResourcesAppPreferences();
        }
        return instance;
    }

    @Override
    protected String getAppDataURL() {
        try {
            return ResourcesAppPreferences.class.getResource("").toURI().toASCIIString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
