package com.vb_note01.favoriteplace;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinner = findViewById(R.id.whitch_place);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            private String[] Array;
            private String array_list;

            @Override
            public void onCheckedChanged(RadioGroup group, int id) {
                if (-1 == id) {
                    //　Spinnerを空にする処
                    setSpinner(spinner, Array);
                } else {
                    // 選択されたラジオボタンに対応したリストをSpinnerにいれる
                    RadioButton radiobutton = (RadioButton) findViewById(id);
                    String rSelect = radiobutton.getText().toString();
                    switch (rSelect){
                        case "ゴルフ": array_list = "golf_list";break;
                        case "駅": array_list = "station_list";break;
                        default:break;
                    }
                    int rID = getResources().getIdentifier(array_list, "array", getPackageName());
                    Array = getResources().getStringArray(rID);
                    setSpinner(spinner, Array);
                }
            }
        });

        this.findViewById(R.id.map_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //              選択されたSpinnerで地図検索
                String address = spinner.getSelectedItem().toString();
                Uri uri = Uri.parse("geo:0.0?q=" + address);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    private void setSpinner(Spinner spinner,String[] arr){
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
