package betcheg.androidstudio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ActivityListTips extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Graphical
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listtips);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView liste_category = (ListView) findViewById(R.id.listtips);

        // Get Intent args
        Intent intent = getIntent();
        int catNumber = -1;
        switch (intent.getIntExtra("category", -1))
        {
            case 0:
                catNumber = R.raw.category1;
                break;
            case 1:
                catNumber = R.raw.category2;
                break;
            case 2:
                catNumber = R.raw.category3;
                break;
            default:
                Log.i("Error", "Error reading cat number");
        }
        // Get Informations
        final FileToJson fileToJson = new FileToJson(catNumber);
        fileToJson.getJsonFromFile(getApplicationContext());


        // Set adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ActivityListTips.this,
                android.R.layout.simple_list_item_1, fileToJson.getTabAttribute("titre"));
        liste_category.setAdapter(adapter);


        // Listen to clics
        liste_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(ActivityListTips.this, ActivityTips.class);
                intent.putExtra("json", fileToJson.getJsonWhereIdIs(position));
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
