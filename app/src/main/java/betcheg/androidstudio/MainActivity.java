package betcheg.androidstudio;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // On appelle les méthodes de départ de la création d'une nouvelle activitée
        super.onCreate(savedInstanceState);
        // On définit l'interface graphique à afficher.
        setContentView(R.layout.activity_main);

        // Pour afficher le titre en haut de l'activité
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // On associe l'objet graphique "listcategory" du XML à un objet JAVA afin de pouvoir effectuer
        // des traitements dessus
        ListView liste_category = (ListView) findViewById(R.id.listcategory);

        // Nom des catégorie codé en dur
        String[] titreCategory = new String[]{ "Categorie 1", "Categorie 2 ", "Categorie 3" };
        // On met un adapter, afin de faire comprendre à Android comment afficher ces informations
        // Ici, les informations contenues dans "titreCategory" sont affiché dans l'objet graphique
        // inclue de base dans Android, nommé simple_list_item_1 (il existe déjà dans la librairie android
        // je l'ai pas créé, je l'utilise car il correspond à mes besoins  : afficher une simple liste)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, titreCategory);

        // J'associe mon objet graphique à l'adaptateur
        liste_category.setAdapter(adapter);

        // J'ecoute lorsque l'un des objets de la liste est cliqué
        liste_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // A chaque clic sur un des objets de la liste j'effectue une action
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Ici, je prévois de changer de "fenetre" (  une Activity en langage Android)
                Intent intent = new Intent(MainActivity.this, ActivityListTips.class);
                // Exemple de code pour transmettre des informations d'une Activité à une autre
                // -> String message = "abc";
                // -> intent.putExtra("nom de la chaine", message);

                // On démarre la nouvelle activitée
                startActivity(intent);

                // Log.i permet d'afficher dans la console des informations utile pour le développeur
                // Ici j'affiche la position de l'objet cliqué dans la liste.
                Log.i("TEST",Integer.toString(position));
            }
        });
    }


    // Methode de base à definir obligatoirement, fait automatiquement par Eclipse/Android Studio
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Methode de base à definir obligatoirement, fait automatiquement par Eclipse/Android Studio
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
