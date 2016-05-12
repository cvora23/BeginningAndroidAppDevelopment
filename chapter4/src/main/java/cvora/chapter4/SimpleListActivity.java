package cvora.chapter4;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SimpleListActivity extends ListActivity {

    String[] presidents = {
            "Dwight D. Eisenhower",
            "John F. Kennedy",
            "Lyndon B. Johnson",
            "Richard Nixon",
            "Gerald Ford",
            "Jimmy Carter",
            "Ronald Reagan",
            "George H. W. Bush",
            "Bill Clinton",
            "George W. Bush",
            "Barack Obama"
    };

    String[] newPresidents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list);
        ListView listView = getListView();
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setTextFilterEnabled(true);

        newPresidents = getResources().getStringArray(R.array.presidents_array);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,newPresidents));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this,"You have selected "+presidents[position],Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view){

        ListView listView = getListView();
        String itemsSelected = "Selected items: \n";
        for(int i=0;i<listView.getCount();i++){
            if(listView.isItemChecked(i)){
                itemsSelected += listView.getItemAtPosition(i) + "\n";
            }
        }
        Toast.makeText(this,itemsSelected,Toast.LENGTH_SHORT).show();
    }
}
