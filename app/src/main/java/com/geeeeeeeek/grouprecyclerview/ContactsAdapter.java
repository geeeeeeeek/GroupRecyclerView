package com.geeeeeeeek.grouprecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.util.List;

/**
 * Created by geeeeeeeek
 * Date: 13/12/2017
 * Time: 9:38 PM
 */

public class ContactsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context context;
    private List<Contact> data;

    public ContactsAdapter(Context context, List<Contact> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false), context);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        Contact contact = data.get(position);
        holder.setText(R.id.tv_name, contact.name);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
