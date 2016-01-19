package bschen.slackprofile.models;

import java.io.Serializable;

public class Profile implements Serializable {

    private String title;
    private String image_48;
    private String image_192;

    public String getTitle() {
        return title;
    }

    public String getImage48() {
        return image_48;
    }

    public String getImage192() {
        return image_192;
    }
}
