package com.ahmetcet.searchablespinnerwithicons;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

                spinnerDialog.show();

            }
        });




    }

    private ArrayList<ListItem> getListData(){
        ArrayList<ListItem> result = new ArrayList<>();


        return result;
    }
}