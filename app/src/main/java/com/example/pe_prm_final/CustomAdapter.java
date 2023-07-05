package com.example.pe_prm_final;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private final List<ItemModel> itemModelList;
    private final IClickItem iClickItem;

    public CustomAdapter(List<ItemModel> itemModelList, IClickItem iClickItem) {
        this.itemModelList = itemModelList;
        this.iClickItem = iClickItem;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemModel itemModel = itemModelList.get(position);
        if (itemModel == null) {
            return;
        }
        holder.book_id_txt.setText(itemModel.getId().toString());
        holder.book_title_txt.setText(itemModel.getTitle().toString());
        holder.book_author_txt.setText(itemModel.getAuthor().toString());
        holder.book_pages_txt.setText(itemModel.getPages().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItem.onClickItemGoDetail(itemModel);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (itemModelList != null) {
            return itemModelList.size();
        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView book_id_txt, book_title_txt, book_author_txt, book_pages_txt;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.book_id_txt);
            book_title_txt = itemView.findViewById(R.id.book_title_txt);
            book_author_txt = itemView.findViewById(R.id.book_author_txt);
            book_pages_txt = itemView.findViewById(R.id.book_pages_txt);
        }

    }

}