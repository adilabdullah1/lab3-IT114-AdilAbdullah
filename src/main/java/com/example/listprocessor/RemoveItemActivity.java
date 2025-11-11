package com.example.listprocessor;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RemoveItemActivity extends AppCompatActivity {

    @Inject
    ItemList the_list;

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);

        // Reference to coordinator layout for Snackbar
        coordinatorLayout = findViewById(R.id.myCoordinatorLayoutRemove);
    }

    public void removeItem(View view) {
        EditText et = findViewById(R.id.edit_position_remove);

        try {
            int pos = Integer.parseInt(et.getText().toString());
            String removedItem = the_list.remove(pos);

            hideKeyboard();
            Snackbar.make(coordinatorLayout,
                    "Removed item: " + removedItem,
                    Snackbar.LENGTH_SHORT).show();

        } catch (IndexOutOfBoundsException e) {
            hideKeyboard();
            Snackbar.make(coordinatorLayout,
                    "Invalid position! Try again.",
                    Snackbar.LENGTH_SHORT).show();

        } catch (NumberFormatException e) {
            hideKeyboard();
            Snackbar.make(coordinatorLayout,
                    "Please enter a valid number.",
                    Snackbar.LENGTH_SHORT).show();
        }
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
