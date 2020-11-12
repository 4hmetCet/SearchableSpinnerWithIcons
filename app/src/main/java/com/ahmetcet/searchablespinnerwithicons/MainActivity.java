package com.ahmetcet.searchablespinnerwithicons;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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

    }

    private ArrayList<ListItem> getListData(){
        ArrayList<ListItem> result = new ArrayList<>();
        result.add(new ListItem("1","one_1"));
        result.add(new ListItem("2","two_2"));

        return result;
    }

    public void OpenSpinnerDialog(View view) {

        //startActivity(new Intent(MainActivity.this, SearchableListActivity.class));
        spinnerDialog = new Dialog(MainActivity.this);

        final CustomListAdapter listAdapter=new CustomListAdapter(getApplicationContext(),getListData());

        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_searchable_spinner, null);
        EditText editText_search = (EditText) dialogView.findViewById(R.id.editText_spinnerSearch);
        editText_search.setText("21312312");
        ListView listView_spinnerList = (ListView) dialogView.findViewById(R.id.listView_spinnerList);

        listView_spinnerList.setAdapter(listAdapter);

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
        spinnerDialog.setContentView(dialogView);
        spinnerDialog.setCancelable(true);
        spinnerDialog.getWindow().setLayout(650,800);
        //spinnerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        spinnerDialog.show();



    }
}