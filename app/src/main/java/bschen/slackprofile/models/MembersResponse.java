package bschen.slackprofile.models;

import java.util.List;

public class MembersResponse {

    private boolean ok;
    private List<Member> members;

    public boolean isOk() {
        return ok;
    }

    public List<Member> getMembers() {
        return members;
    }
}
