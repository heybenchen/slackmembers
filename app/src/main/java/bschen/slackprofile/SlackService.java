package bschen.slackprofile;

import bschen.slackprofile.models.MembersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SlackService {

    String BASE_URL = "https://api.slack.com/methods/";
    String TOKEN = "xoxp-5048173296-5048346304-5180362684-7b3865";

    @GET("members.list")
    Call<MembersResponse> listMembers(@Query("token") String token);
}
