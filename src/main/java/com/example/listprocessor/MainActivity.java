package com.example.listprocessor;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Inject
    ItemList the_list;   // singleton list

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        tv = findViewById(R.id.text_main);

        // Initialize sample strings if empty
        if (the_list.isEmpty()) {
            the_list.add("pizza");
            the_list.add("crackers");
            the_list.add("peanut butter");
            the_list.add("jelly");
            the_list.add("bread");
            the_list.add("spaghetti");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_option1) {
            onOption1(item);
            return true;
        } else if (id == R.id.action_option2) {
            onOption2(item);
            return true;
        } else if (id == R.id.action_option3) {
            onOption3(item);
            return true;
        } else if (id == R.id.action_option4) {
            onOption4(item);
            return true;
        } else if (id == R.id.action_option5) {
            onOption5(item);
            return true;
        } else if (id == R.id.action_option6) {
            onOption6(item);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // Option 1: Show list in order
    public void onOption1(MenuItem i) {
        tv.setText("");
        for (String item : the_list) {
            tv.append(item + "\n");
        }
    }

    // Option 2: Show list in reverse order
    public void onOption2(MenuItem i) {
        tv.setText("");
        for (int j = the_list.size() - 1; j >= 0; j--) {
            tv.append(the_list.get(j) + "\n");
        }
    }

    // Option 3: Show size of list
    public void onOption3(MenuItem i) {
        tv.setText("The list currently contains " + the_list.size() + " items.");
    }

    // Option 4: Add item activity
    public void onOption4(MenuItem i) {
        startActivity(new Intent(this, AddItemActivity.class));
    }

    // Option 5: Remove item activity
    public void onOption5(MenuItem i) {
        startActivity(new Intent(this, RemoveItemActivity.class));
    }

    // Option 6: Remove all items
    public void onOption6(MenuItem i) {
        the_list.clear();
        tv.setText("All items have been removed from the list.");
    }
}
