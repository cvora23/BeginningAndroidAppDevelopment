package com.cricmybrain.chapter3;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ActionItemsActionBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_items_action_bar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.show();
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        CreateMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return MenuChoice(item);
    }

    private void CreateMenu(Menu menu){

        MenuItem mnu1 = menu.add(0,0,0,"Item 1");
        {
            mnu1.setIcon(R.drawable.ic_launcher);
            mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        }
        MenuItem mnu2 = menu.add(0,1,1,"Item 2");
        {
            mnu2.setIcon(R.drawable.ic_launcher);
            mnu2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        }
        MenuItem mnu3 = menu.add(0,2,2,"Item 3");
        {
            mnu3.setIcon(R.drawable.ic_launcher);
            mnu3.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        }
        MenuItem mnu4 = menu.add(0,3,3,"Item 4");
        {
            mnu4.setIcon(R.drawable.ic_launcher);
            mnu4.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        }
        MenuItem mnu5 = menu.add(0,4,4,"Item 5");
        {
            mnu5.setIcon(R.drawable.ic_launcher);
            mnu5.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        }
    }

    private boolean MenuChoice(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                Toast.makeText(this,"You clicked on the Application Icon",Toast.LENGTH_SHORT).show();
                return true;
            case 0:
                Toast.makeText(this,"You Clicked on item 1",Toast.LENGTH_SHORT).show();
                return true;
            case 1:
                Toast.makeText(this,"You Clicked on item 2",Toast.LENGTH_SHORT).show();
                return true;
            case 2:
                Toast.makeText(this,"You Clicked on item 3",Toast.LENGTH_SHORT).show();
                return true;
            case 3:
                Toast.makeText(this,"You Clicked on item 4",Toast.LENGTH_SHORT).show();
                return true;
            case 4:
                Toast.makeText(this,"You Clicked on item 5",Toast.LENGTH_SHORT).show();
                return true;

        }
        return false;
    }
}
