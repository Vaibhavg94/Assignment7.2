package com.vaibhav.assignment72;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity {
    private SqliteItemSearch sqllitebb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        final AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autocompleteitem);
        sqllitebb = new SqliteItemSearch(MainActivity.this);
        sqllitebb.openDB();

        final String[] deal = sqllitebb.getAllItemFilter();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, deal);
        actv.setAdapter(adapter);
        actv.setThreshold(1);
        actv.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                arg0.getItemAtPosition(arg2);
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        sqllitebb.close();
    }
}
