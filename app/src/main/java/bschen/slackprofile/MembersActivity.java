package bschen.slackprofile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import bschen.slackprofile.models.Member;
import bschen.slackprofile.models.MembersResponse;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MembersActivity extends AppCompatActivity {

    SlackService mSlackService;
    MembersAdapter mAdapter;

    @Bind(R.id.members_list) ListView mListView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        ButterKnife.bind(this);

        mSlackService = initSlackService();
        mAdapter = new MembersAdapter(this, new ArrayList<Member>());
        mListView.setAdapter(mAdapter);

        loadData();
    }

    private SlackService initSlackService() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SlackService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(SlackService.class);
    }

    private void loadData() {
        final Call<MembersResponse> call = mSlackService.listMembers(SlackService.TOKEN);
        call.enqueue(new Callback<MembersResponse>() {
            @Override
            public void onResponse(final Response<MembersResponse> response) {
                final MembersResponse memberResponse = response.body();
                if (response.body() != null && memberResponse.isOk()) {
                    mAdapter.replaceMembers(memberResponse.getMembers());
                }
            }

            @Override
            public void onFailure(final Throwable t) {
                Toast.makeText(MembersActivity.this, R.string.error_network, Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }
}
