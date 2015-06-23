package ddervovic.edukacija.com.uizadatak;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class FirstScreen extends ActionBarActivity {
    private List<Osobe> mojeOsobe = new ArrayList<Osobe>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        generirajOsobe();
        populateListView();
        registerClickCallback();
    }




    private void generirajOsobe() {
        mojeOsobe.add( new Osobe("Kristina Grguricin", "Kako si?", "0915555555", R.drawable.heart) );
        mojeOsobe.add( new Osobe("Leonardo Gospodnetic", "Ae", "0915555555", R.drawable.fish) );
        mojeOsobe.add( new Osobe("Matija Percec", "Kako sam jak!", "0915555555", R.drawable.down) );
        mojeOsobe.add( new Osobe("Sanja Saric", "Palacinke", "0915555555", R.drawable.star) );
        mojeOsobe.add( new Osobe("Matija Percec", "Kako sam jak!", "0915555555", R.drawable.down) );
        mojeOsobe.add( new Osobe("Sanja Saric", "Palacinke", "0915555555", R.drawable.star) );
        mojeOsobe.add( new Osobe("Matija Percec", "Kako sam jak!", "0915555555", R.drawable.down) );
        mojeOsobe.add( new Osobe("Sanja Saric", "Palacinke", "0915555555", R.drawable.star) );
        mojeOsobe.add( new Osobe("Mate Bogovic", "Kme...", "0915555555", R.drawable.bug) );
        mojeOsobe.add(new Osobe("Goran Jaksic", "Gdje su mi tabletice?", "0915555555", R.drawable.help));
        mojeOsobe.add(new Osobe("Goran Jaksic", "Gdje su mi tabletice?", "0915555555", R.drawable.help));
        mojeOsobe.add(new Osobe("Goran Jaksic", "Gdje su mi tabletice?", "0915555555", R.drawable.help));
        mojeOsobe.add(new Osobe("Goran Jaksic", "Gdje su mi tabletice?", "0915555555", R.drawable.help));
        mojeOsobe.add(new Osobe("Neven Susa", "Ufffff...", "0915555555", R.drawable.up));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_screen, menu);
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

    private void populateListView() {
        ArrayAdapter<Osobe> adapter = new MyListAdapter();
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);


    }

    private class MyListAdapter extends ArrayAdapter<Osobe>{
        public MyListAdapter(){
            super(FirstScreen.this, R.layout.item_view, mojeOsobe);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if( itemView == null ){
                itemView = getLayoutInflater().inflate( R.layout.item_view, parent, false );

            }

            //dohvat osoba
            Osobe osoba = mojeOsobe.get( position );

            //popunjavanje view-a
            ImageView imageView = (ImageView) itemView.findViewById( R.id.imgSlikaOsobe );
            imageView.setImageResource( osoba.getSlika() );

            TextView txtIme = (TextView) itemView.findViewById( R.id.txtIme );
            String ime = osoba.getIme() + " (" + osoba.getBroj() + ")";
            txtIme.setText( ime );

            TextView txtSMS = (TextView) itemView.findViewById( R.id.txtSMS );
            txtSMS.setText( osoba.getSms() );

            return itemView;
        }
    }


    private void registerClickCallback() {
        ListView listView = ( ListView ) findViewById( R.id.listView );
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Osobe osoba = mojeOsobe.get( position );
                String poruka = "Kliknulu ste poruku " + osoba.getIme() + "-" + osoba.getSms();
                Toast.makeText(FirstScreen.this, poruka, Toast.LENGTH_LONG).show();
            }
        });
    }
}
