package com.example.lucas.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Contact> contacts = new ArrayList<>();
    ListView contactListView;
    DatabaseHandler dbHandler;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactListView = (ListView) findViewById(R.id.listView);
        dbHandler = new DatabaseHandler(getApplicationContext());


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, CreatorContactActivity.class);
                startActivity(it);
            }
        });

        List<Contact> addableContacts = dbHandler.getAllContacts();
        int contactCount = dbHandler.getContactsCount();

        for (int i = 0; i < contactCount; i++) {
            contacts.add(addableContacts.get(i));
        }

        if (!addableContacts.isEmpty()) {
            populateList();
        }
    }

    private void populateList() {
        ArrayAdapter<Contact> adapter = new MainActivity.ContactListAdapter();
        contactListView.setAdapter(adapter);
    }

    private class ContactListAdapter extends ArrayAdapter<Contact> {
        public ContactListAdapter() {
            super(MainActivity.this, R.layout.listview_item, contacts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = getLayoutInflater().inflate(R.layout.listview_item, parent, false);
            Contact currentContact = contacts.get(position);

            TextView name = (TextView) convertView.findViewById(R.id.contactName);
            name.setText(currentContact.getName());

            TextView phone = (TextView) convertView.findViewById(R.id.phoneNumber);
            phone.setText(currentContact.getPhone());

            TextView email = (TextView) convertView.findViewById(R.id.emailAddress);
            email.setText(currentContact.getEmail());

            TextView address = (TextView) convertView.findViewById(R.id.cAddress);
            address.setText(currentContact.getAddress());

            ImageView ivContactImage = (ImageView) convertView.findViewById(R.id.ivContactImage);
            ivContactImage.setImageURI(currentContact.getImageURI());

            return convertView;
        }
    }

}
