package bschen.slackprofile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bschen.slackprofile.models.Member;
import butterknife.ButterKnife;

public class MembersAdapter extends ArrayAdapter<Member> {

    private static class ViewHolder {
        TextView userName;
        TextView realName;
        ImageView avatar;
    }

    public MembersAdapter(final Context context, final List<Member> objects) {
        super(context, 0, objects);
    }

    public void replaceMembers(final List<Member> members) {
        clear();
        addAll(members);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final Member member = getItem(position);
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.cell_member, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.userName = ButterKnife.findById(convertView, R.id.cell_member_user_name);
            viewHolder.realName = ButterKnife.findById(convertView, R.id.cell_member_real_name);
            viewHolder.avatar = ButterKnife.findById(convertView, R.id.cell_member_avatar);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.userName.setText(member.getName());
        viewHolder.realName.setText(member.getRealName());
        Picasso.with(getContext()).load(member.getProfile().getImage192()).into(viewHolder.avatar);

        return convertView;
    }
}
