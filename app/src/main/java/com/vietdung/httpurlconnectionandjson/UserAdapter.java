package com.vietdung.httpurlconnectionandjson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context mContext;
    private List<User> mUsers;
    private LayoutInflater mInflater;

    public UserAdapter(Context context, List<User> users) {
        mContext = context;
        mUsers = users;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers == null ? 0 : mUsers.size();
    }

    public void setUsers(List<User> users){
        mUsers = users;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextName;
        private TextView mTextFullName;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextName = itemView.findViewById(R.id.text_name);
            mTextFullName = itemView.findViewById(R.id.text_full_name);
        }

        private void bindView(User user) {
            mTextName.setText(user.getName());
            mTextFullName.setText(user.getFullName());
        }
    }
}
