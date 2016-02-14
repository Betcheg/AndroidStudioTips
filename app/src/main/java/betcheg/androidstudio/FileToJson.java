package betcheg.androidstudio;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by bastien on 14/02/16.
 */
public class FileToJson {

    // Generate getters and setters

    private JSONObject json;
    private String fileText;
    private int fileId;

    public FileToJson(){
        this.json = null;
        this.fileText ="";
        this.fileId = 0;
    }

    public FileToJson(int i){
        this.fileText = "";
        this.json = null;
        this.fileId = i;
    }

    public JSONObject getJsonFromFile(Context c) {
        this.fileText = "";
        this.json = null;

        String line; // Une ligne du fichier
        InputStream inputStream = c.getResources().openRawResource(this.fileId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            while ((line = reader.readLine()) != null) {
                fileText = fileText.concat(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.json = new JSONObject(fileText);
            return json;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] getTabAttribute(String attribute) {

            if(this.json != null) {
                String tab[] = new String[json.length()];

                for (int i = 0; i < tab.length; i++) {
                    try {
                        tab[i] = json.getJSONObject(Integer.toString(i)).getString(attribute);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                return tab;
            }
        else
            {
                Log.i("Error", "Json Object cannot be read from, use getJsonFromFile first");
                return null;
            }

    }

    public String getJsonWhereIdIs(int id){

        if(this.json != null) {
            try {
                return json.get(Integer.toString(id)).toString();
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        else
        {
            Log.i("Error", "Json Object cannot be read from, use getJsonFromFile first");
            return null;
        }
    }


}
