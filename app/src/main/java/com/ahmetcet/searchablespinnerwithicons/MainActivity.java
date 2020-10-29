package com.ahmetcet.searchablespinnerwithicons;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ahmetcet.searchablespinnerwithicons.Adapter.CustomListAdapter;
import com.ahmetcet.searchablespinnerwithicons.Model.ListItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tv_spinner ;
    Dialog spinnerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerDialog = new Dialog(this);
        tv_spinner = (TextView) findViewById(R.id.tv_spinner);

        tv_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinnerDialog.setContentView(R.layout.dialog_searchable_spinner);
                spinnerDialog.setCancelable(true);
                spinnerDialog.getWindow().setLayout(650,800);
                //spinnerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                spinnerDialog.show();

                final CustomListAdapter listAdapter=new CustomListAdapter(getApplicationContext(),getListData());

                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_searchable_spinner, null);
                EditText editText_search = (EditText) dialogView.findViewById(R.id.editText_spinnerSearch);
                ListView listView_spinnerList = (ListView) dialogView.findViewById(R.id.listView_spinnerList);

                listView_spinnerList.setAdapter((ListAdapter)listAdapter);

                listView_spinnerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        ListItem item = (ListItem) parentView.getItemAtPosition(position);
                        // ((TextView) parentView.getChildAt(0)).setTextColor(Color.parseColor("#0c8299"));

                        tv_spinner.setText(item.getValue());
                        tv_spinner.setTextColor(Color.BLACK);
                        if(spinnerDialog.isShowing())
                            spinnerDialog.dismiss();

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
        });




    }

    private ArrayList<ListItem> getListData(){
        ArrayList<ListItem> result = new ArrayList<>();
        result.add(new ListItem("1","test_1"));
        result.add(new ListItem("2","test_2"));

        return result;
    }
}