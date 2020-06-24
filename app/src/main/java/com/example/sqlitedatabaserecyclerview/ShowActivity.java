package com.example.sqlitedatabaserecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView showrecyclerView;
    private List<UserModel> users;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        showrecyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        showrecyclerView.setLayoutManager(linearLayoutManager);

        users = new ArrayList<>();
        helper = new DatabaseHelper(this);

        final UserAdapter adapter = new UserAdapter(ShowActivity.this, users);
        showrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        getData();

        searchView = findViewById(R.id.searchView);
        searchView.setQueryHint("Search here....");
        searchView.setActivated(true);
        searchView.setIconified(true);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (adapter != null){
                    adapter.getFilter().filter(newText);
                }
                return true;
            }
        });
    }

    private void getData() {
        Cursor cursor = helper.showData();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(helper.COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(helper.COL_NAME));
            String email = cursor.getString(cursor.getColumnIndex(helper.COL_EMAIL));

            users.add(new UserModel(id, name, email));
            UserAdapter adapter = new UserAdapter(ShowActivity.this, users);
            adapter.notifyDataSetChanged();
        }

    }
}
