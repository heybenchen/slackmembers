package bschen.slackprofile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import bschen.slackprofile.models.Member;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    public static final String EXTRA_MEMBER = "EXTRA_MEMBER";

    private Member mMember;

    @Bind(R.id.profile_avatar) ImageView mAvatar;
    @Bind(R.id.profile_user_name) TextView mUserName;
    @Bind(R.id.profile_real_name) TextView mRealName;
    @Bind(R.id.profile_title) TextView mTitle;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        loadIntent(getIntent());
        initViews();
    }

    private void loadIntent(final Intent intent) {
        mMember = (Member) intent.getSerializableExtra(EXTRA_MEMBER);

    }

    private void initViews() {
        Picasso.with(this).load(mMember.getProfile().getImage192()).into(mAvatar);
        mUserName.setText(mMember.getName());
        mRealName.setText(mMember.getRealName());
        mTitle.setText(mMember.getProfile().getTitle());
    }

}
