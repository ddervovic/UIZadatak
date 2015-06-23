package ddervovic.edukacija.com.uizadatak;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MojActivitySaNavigationDrawerom extends ActionBarActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    public static String[] mIzbori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moj_activity_sa_navigation_drawerom);

        mIzbori = getResources().getStringArray( R.array.lista_izbora_nav_drawer );

        mDrawerLayout = (DrawerLayout) findViewById( R.id.drawer_layout );
        mActivityTitle = getTitle().toString();

        mDrawerList = (ListView) findViewById(R.id.nav_drawer_list_view);
        mAdapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, mIzbori );

        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem( position );
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setupDrawer();

    }

    private void setupDrawer(){
        mDrawerToggle = new ActionBarDrawerToggle( this, mDrawerLayout,R.string.navigation_drawer_open, R.string.navigation_drawer_close ){
            public void onDrawerOpened( View drawerView ){
                super.onDrawerOpened( drawerView );
                getSupportActionBar().setTitle("Navigacija!");
                invalidateOptionsMenu();

            }

            public void onDrawerClosed( View view ){
                super.onDrawerOpened( view );
                getSupportActionBar().setTitle( mActivityTitle );
                invalidateOptionsMenu();
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_moj_activity_sa_navigation_drawerom, menu);
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
        else if( mDrawerToggle.onOptionsItemSelected( item ) ){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectItem( int position ){
        //zamijeni moj_content_frame sa novim fragmentom
        Fragment fragment = new MojFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(MojFragment.IME_POLJA, position);
        fragment.setArguments(bundle);

        FragmentManager fm = getFragmentManager();

        fm.beginTransaction().replace( R.id.moj_content_frame, fragment ).commit();

        //promijeni u draweru koji je element označen i zatvori ga
        mDrawerList.setItemChecked( position, true );
        mDrawerLayout.closeDrawer( mDrawerList );

    }

    public static class MojFragment extends Fragment{

        public static final String IME_POLJA = "Izbor";

        public MojFragment(){

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootview = inflater.inflate( R.layout.moj_frame, container, false );

            int i = getArguments().getInt(IME_POLJA);

            TextView txtView = (TextView) rootview.findViewById( R.id.moj_frame_text_view );
            txtView.setText( mIzbori[i] );

            return rootview;

        }
    }
}
