package bschen.slackprofile;

import bschen.slackprofile.models.MembersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SlackService {

    String BASE_URL = "https://slack.com/api/";
    String TOKEN = "xoxp-5048173296-5048487710-18650790535-1cc8644082";

    @GET("users.list")
    Call<MembersResponse> listMembers(@Query("token") String token);
}
