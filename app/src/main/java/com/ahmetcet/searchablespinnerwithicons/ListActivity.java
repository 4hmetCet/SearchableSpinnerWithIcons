package com.ahmetcet.searchablespinnerwithicons;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.ahmetcet.searchablespinnerwithicons.Adapter.CustomListAdapter;
import com.ahmetcet.searchablespinnerwithicons.Model.ListItem;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final CustomListAdapter listAdapter=new CustomListAdapter(getApplicationContext(),getListData());

        EditText editText_search = (EditText) findViewById(R.id.editText_spinnerSearch);
        ListView listView_spinnerList = (ListView) findViewById(R.id.listView_spinnerList);

        listView_spinnerList.setAdapter(listAdapter);

        listView_spinnerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ListItem item = (ListItem) parentView.getItemAtPosition(position);
                // ((TextView) parentView.getChildAt(0)).setTextColor(Color.parseColor("#0c8299"));

            }
        });


        editText_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
    private ArrayList<ListItem> getListData(){
        ArrayList<ListItem> result = new ArrayList<>();
        result.add(new ListItem("1","one_1"));
        result.add(new ListItem("2","two_2"));

        return result;
    }

}