package bschen.slackprofile.models;

import java.io.Serializable;

public class Member implements Serializable {

    private String name;
    private String real_name;
    private Profile profile;

    public String getName() {
        return name;
    }

    public String getRealName() {
        return real_name;
    }

    public Profile getProfile() {
        return profile;
    }
}
