package com.geeeeeeeek.grouprecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by geeeeeeeek
 * Date: 13/12/2017
 * Time: 9:19 PM
 */

public class MainActivity extends Activity {
    private RecyclerView mRecyclerView;
    private ContactsAdapter contactsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // mock data
        final List<Contact> data = new ArrayList<>();
        data.add(new Contact(1, "hello"));
        data.add(new Contact(1, "hello"));
        data.add(new Contact(2, "kkkk"));
        data.add(new Contact(2, "kkkk"));
        data.add(new Contact(2, "kkkk"));
        data.add(new Contact(2, "kkkk"));
        data.add(new Contact(2, "kkkk"));
        data.add(new Contact(2, "kkkk"));
        data.add(new Contact(2, "kkkk"));
        data.add(new Contact(3, "ffffdddd"));
        data.add(new Contact(3, "ffffdddd"));
        data.add(new Contact(3, "ffffdddd"));
        data.add(new Contact(3, "ffffdddd"));
        data.add(new Contact(3, "ffffdddd"));
        data.add(new Contact(3, "ffffdddd"));
        data.add(new Contact(3, "ffffdddd"));
        data.add(new Contact(3, "ffffdddd"));
        data.add(new Contact(3, "ffffdddd"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));
        data.add(new Contact(4, "aaaaa"));


        contactsAdapter = new ContactsAdapter(this, data);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(contactsAdapter);
        mRecyclerView.addItemDecoration(new GroupItemDecoration(this, new GroupItemDecoration.DecorationListener() {
            @Override
            public int getType(int position) {
                return data.get(position).type;
            }

            @Override
            public String getTypeText(int position) {
                return data.get(position).initial;
            }
        }));

    }
}
