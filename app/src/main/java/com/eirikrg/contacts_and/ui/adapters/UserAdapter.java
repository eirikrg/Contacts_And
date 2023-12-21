package com.eirikrg.contacts_and.ui.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.eirikrg.domain.entities.user.User;
import com.eirikrg.contacts_and.R;
import com.eirikrg.contacts_and.ui.activities.users_activity.UsersActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private final List<User> localDataSet;

    public UserAdapter(List<User> userList) {
        localDataSet = userList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_recycler_view_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        User user = this.localDataSet.get(position);

        viewHolder.getUserName().setText(user.getName().getTitledName());
        Picasso.get().load(user.getPicture().getLarge()).into(viewHolder.userImage);
        viewHolder.getUserPhone().setText(user.getPhone().trim());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Context context;
        private final TextView userName;
        private final ImageView userImage;
        private final TextView userPhone;

        public ViewHolder(View view) {
            super(view);

            this.context = view.getContext();

            view.setOnClickListener(this);

            this.userName = view.findViewById(R.id.tv_userName);
            this.userImage = view.findViewById(R.id.iv_user);
            this.userPhone = view.findViewById(R.id.tv_userPhone);
        }

        public TextView getUserName() {
            return this.userName;
        }

        public ImageView getUserImage() {
            return this.userImage;
        }

        public TextView getUserPhone() {
            return this.userPhone;
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                User user = localDataSet.get(position);
                ((UsersActivity) context).startUserDetailActivity(user);
            }
        }
    }
}

