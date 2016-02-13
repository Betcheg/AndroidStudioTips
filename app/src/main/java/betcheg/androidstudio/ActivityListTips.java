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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listtips);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView liste_category = (ListView) findViewById(R.id.listtips);
        String[] titreCategory = new String[]{ "Tips 1", "Tips 2 ", "Tips 3" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ActivityListTips.this,
                android.R.layout.simple_list_item_1, titreCategory);
        liste_category.setAdapter(adapter);

       // String tab[][] = getListeTipsFromJson();
        getListeTipsFromJson();
        liste_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(ActivityListTips.this, ActivityTips.class);
                intent.putExtra("numero", position);
                startActivity(intent);
                Log.i("TEST TIPS",Integer.toString(position));
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

    public void  getListeTipsFromJson() {
        String fichier = "";
        String line;
        InputStream inputStream = getApplicationContext().getResources().openRawResource(R.raw.category1);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            while ((line = reader.readLine()) != null) {
                fichier = fichier.concat(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("fichier:", fichier);
        try {

            JSONObject json = new JSONObject(fichier);
            JSONObject subJson = json.getJSONObject("1");

            Log.i("json:", json.get("1").toString());
            Log.i("json2 :", subJson.get("titre").toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
