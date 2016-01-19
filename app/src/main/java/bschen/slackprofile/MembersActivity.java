package bschen.slackprofile;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view,
                    final int position, final long id) {
                launchProfileActivity(mAdapter.getItem(position), view);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    private void launchProfileActivity(final Member member, final View listItemView) {
        final Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(ProfileActivity.EXTRA_MEMBER, member);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent);
        } else {
            final Object tag = listItemView.getTag();
            if (tag instanceof MembersAdapter.ViewHolder) {
                final MembersAdapter.ViewHolder viewHolder = (MembersAdapter.ViewHolder) tag;
                final View avatar = viewHolder.avatar;
                final View userName = viewHolder.userName;
                final View realName = viewHolder.realName;

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this,
                        Pair.create(avatar, avatar.getTransitionName()),
                        Pair.create(userName, userName.getTransitionName()),
                        Pair.create(realName, realName.getTransitionName()));

                startActivity(intent, options.toBundle());
            }
        }
    }
}
