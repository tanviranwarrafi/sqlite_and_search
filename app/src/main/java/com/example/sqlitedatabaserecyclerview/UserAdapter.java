package com.example.sqlitedatabaserecyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> implements Filterable {

    private List<UserModel> users;
    Context context;

    private List<UserModel> filterModelClass;

    public UserAdapter(Context context, List<UserModel> users) {
        this.context = context;
        this.users = users;
        this.filterModelClass = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        String name = users.get(position).getName();
        String email = users.get(position).getEmail();
        viewHolder.setData(name, email);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView userImage;
        private TextView userName;
        private TextView userEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.user_item_image);
            userName = itemView.findViewById(R.id.user_item_name);
            userEmail = itemView.findViewById(R.id.user_item_email);

        }

        public void setData(final String name, final String email) {
            userName.setText(name);
            userEmail.setText(email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent itemDetailsIntent = new Intent(itemView.getContext(), UserDetailsActivity.class);
                    itemDetailsIntent.putExtra("name", name);
                    itemDetailsIntent.putExtra("email", email);
                    itemView.getContext().startActivity(itemDetailsIntent);
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String charString = constraint.toString();

                if (charString.isEmpty()) {
                    users = filterModelClass;
                } else {
                    List<UserModel> filterList = new ArrayList<>();
                    for (UserModel data : filterModelClass) {
                        if ((data.getName().toUpperCase()).contains(charString.toUpperCase())) {
                            filterList.add(data);
                        }
                    }
                    users = filterList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = users;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                users = (List<UserModel>) results.values;
                notifyDataSetChanged();
            }
        };

    }
}