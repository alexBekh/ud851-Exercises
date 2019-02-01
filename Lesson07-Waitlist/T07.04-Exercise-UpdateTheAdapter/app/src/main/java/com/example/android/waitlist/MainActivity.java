package com.example.android.waitlist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.android.waitlist.data.TestUtil;
import com.example.android.waitlist.data.WaitlistContract.WaitlistEntry;
import com.example.android.waitlist.data.WaitlistDbHelper;


public class MainActivity extends AppCompatActivity {
    
    private SQLiteDatabase mDb;
    
    private EditText mGuestNameEditText;
    private EditText mPartySizeEditText;
    private RecyclerView mWaitlistRecyclerView;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        mGuestNameEditText = (EditText) findViewById(R.id.person_name_edit_text);
        mPartySizeEditText = (EditText) findViewById(R.id.party_count_edit_text);
        
        // Set local attributes to corresponding views
        mWaitlistRecyclerView = (RecyclerView) this.findViewById(R.id.all_guests_list_view);

        // Set layout for the RecyclerView, because it's a list we are using the linear layout
        mWaitlistRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Create a DB helper (this will create the DB if run for the first time)
        WaitlistDbHelper dbHelper = new WaitlistDbHelper(this);

        // Keep a reference to the mDb until paused or killed. Get a writable database
        // because you will be adding restaurant customers
        mDb = dbHelper.getWritableDatabase();

        //Fill the database with fake data
        TestUtil.insertFakeData(mDb);
        
        showAllGuests();
    
    }
    
    private void showAllGuests()
    {
        // Get all guest info from the database and save in a cursor
        Cursor cursor = getAllGuests();
        
        // TODO (10) Pass the entire cursor to the adapter rather than just the count
        // Create an adapter for that cursor to display the data
        GuestListAdapter adapter = new GuestListAdapter(this, cursor);
        
        // Link the adapter to the RecyclerView
        mWaitlistRecyclerView.setAdapter(adapter);
    }
    
    /**
     * This method is called when user clicks on the Add to waitlist button
     *
     * @param view The calling view (button)
     */
    public void addToWaitlist(View view)
    {
        addDataFromUiToDatabase();
    
        clearUiFields();
    
        showAllGuests();
    }
    
    private void clearUiFields()
    {
        mGuestNameEditText.setText("");
        mGuestNameEditText.clearFocus();
        mPartySizeEditText.setText("");
        mPartySizeEditText.clearFocus();
    }
    
    private void addDataFromUiToDatabase()
    {
        String guestName = mGuestNameEditText.getText().toString();
        int partySize = Integer.parseInt(mPartySizeEditText.getText().toString());
        
        ContentValues contentValues = new ContentValues();
        contentValues.put(WaitlistEntry.COLUMN_GUEST_NAME, guestName);
        contentValues.put(WaitlistEntry.COLUMN_PARTY_SIZE, partySize);
        mDb.insert(WaitlistEntry.TABLE_NAME, null, contentValues);
    }
    
    
    /**
     * Query the mDb and get all guests from the waitlist table
     *
     * @return Cursor containing the list of guests
     */
    private Cursor getAllGuests() {
        return mDb.query(
                WaitlistEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                WaitlistEntry.COLUMN_TIMESTAMP
        );
    }


}