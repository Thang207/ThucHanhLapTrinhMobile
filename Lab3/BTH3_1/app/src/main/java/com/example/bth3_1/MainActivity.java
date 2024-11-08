package com.example.bth3_1;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHandler databaseHandler;
    private Contact selectedContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(this);
        databaseHandler.deleteAllContacts();

        databaseHandler.addContact(new Contact("Ravi", "9100000000"));
        databaseHandler.addContact(new Contact("Srinivas", "9199999999"));
        databaseHandler.addContact(new Contact("Tommy", "9522222222"));
        databaseHandler.addContact(new Contact("Karthik", "9533333333"));
        databaseHandler.addContact(new Contact("Sakai", "98888888888"));

        showData();
        ListView lvContacts = findViewById(R.id.lv_contact);
        lvContacts.setOnItemLongClickListener((parent, view, position, id) -> {
            selectedContact = (Contact) parent.getItemAtPosition(position);
            if (selectedContact != null) {
                databaseHandler.deleteContact(selectedContact.getId());
                showData();
                Toast.makeText(MainActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }
    private List<Contact> getData() {
        return databaseHandler.getAllContacts();
    }
    private void showData() {
        ListView lvContacts = findViewById(R.id.lv_contact);
        List<Contact> contactsData = getData();
        ContactAdapter contactAdapter = new ContactAdapter(MainActivity.this, contactsData);
        lvContacts.setAdapter(contactAdapter);
        contactAdapter.notifyDataSetChanged();
    }
}
