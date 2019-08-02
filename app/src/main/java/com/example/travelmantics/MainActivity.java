package com.example.travelmantics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    EditText text_title;
    EditText text_price;
    EditText text_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseUtil.openFbReference("traveldeals");
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;
        text_title = (EditText) findViewById(R.id.text_title);
        text_price = (EditText) findViewById(R.id.text_price);
        text_description = (EditText) findViewById(R.id.text_description);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_menu:
                SaveDeal();
                Toast.makeText(this, "Deal Saved", Toast.LENGTH_LONG).show();
                clean();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void clean() {
        text_title.setText("");
        text_price.setText("");
        text_description.setText("");
        text_title.requestFocus();
    }

    private void SaveDeal() {
        String title = text_title.getText().toString();
        String price = text_price.getText().toString();
        String description = text_description.getText().toString();
        TravelDeal deal = new TravelDeal(title, price, description, "");
        mDatabaseReference.push().setValue(deal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }
}
