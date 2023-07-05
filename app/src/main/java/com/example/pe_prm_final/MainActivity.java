package com.example.pe_prm_final;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    List<ItemModel> itemModel;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_floating_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewItemActivity.class);
                startActivity(intent);
            }
        });


        itemModel = getData();

        customAdapter = new CustomAdapter(itemModel, new IClickItem() {
            @Override
            public void onClickItemGoDetail(ItemModel itemModel) {
                Intent intent = new Intent(MainActivity.this, DeleteUpdateItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("itemModel", itemModel);
                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(MainActivity.this, itemModel.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    public List<ItemModel> getData() {
        List<ItemModel> myList = new ArrayList<>();
        String[] cols = {ItemProvider.ID, ItemProvider.TITLE, ItemProvider.AUTHOR, ItemProvider.PAGES};

        Cursor cursor = getContentResolver().query(ItemProvider.CONTENT_URI, cols, null, null, null);

        if (cursor == null) {
            Toast.makeText(this, "cursor Empty", Toast.LENGTH_SHORT).show();
            return myList = null;
        }
        Log.d("ContentResolver", getContentResolver().toString());
        if (cursor.getCount() == 0) {
            myList = null;
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                String _id = cursor.getString(0);
                String _title = cursor.getString(1);
                String _author = cursor.getString(2);
                String _page = cursor.getString(3);

                myList.add(new ItemModel(_id, _title, _author, _page));
            }
        }
        return myList;
    }
}