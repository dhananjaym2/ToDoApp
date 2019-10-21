package codepath.com.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import codepath.com.todoapp.map.MapScreen;

public class TodoActivity extends AppCompatActivity {

    ArrayAdapter<String> arrayAdapter;
    ListView lvItems;
    ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        lvItems = (ListView) findViewById(R.id.listView);
//        items = new ArrayList<String>();
//        items.add("Item 1");
//        items.add("Item 2");

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

                items.remove(position);
                arrayAdapter.notifyDataSetChanged();
                saveItems();
                return true;
            }
        });
        items = readItems();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.todo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_map_screen) {
            Intent intentToMapScreen = new Intent(this, MapScreen.class);
            startActivity(intentToMapScreen);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addButton(View view) {
        EditText etItemToAdd = (EditText) findViewById(R.id.etItem);
        String itemToAdd = etItemToAdd.getText().toString();
        items.add(itemToAdd);
//        arrayAdapter.add(itemToAdd);
        arrayAdapter.notifyDataSetChanged();
        etItemToAdd.setText("");
        saveItems(/*itemToAdd*/);
    }

    private ArrayList<String> readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            return new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<String>();
        }
    }

    private void saveItems(/*String itemToAdd*/) {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
//        ArrayList<String> itemArrayToAdd = new ArrayList<String>();
//        itemArrayToAdd.add(itemToAdd);
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}