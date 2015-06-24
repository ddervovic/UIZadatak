package ddervovic.edukacija.com.uizadatak;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class ViewPagerActivity extends FragmentActivity {

    private static final int mBrojStranica = 3;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        mViewPager = (ViewPager) findViewById( R.id.activity_view_pager_layout );
        mPagerAdapter = new MojScreenSlideAdapter( getSupportFragmentManager() );
        mViewPager.setAdapter( mPagerAdapter );
        mViewPager.setOnPageChangeListener( new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                invalidateOptionsMenu();
            }
        });
    }


    //adapter
    private class MojScreenSlideAdapter extends FragmentStatePagerAdapter {

        public MojScreenSlideAdapter( FragmentManager fm ){
            super( fm );

        }

        @Override
        public Fragment getItem(int position) {
            return MojSlideFragment.createFragment( position );
        }

        @Override
        public int getCount() {
            return mBrojStranica;
        }
    }

    //fragment koji se mijenja
    public static class MojSlideFragment extends android.support.v4.app.Fragment{

        public static final String POLJE_POZICIJA = "Pozicija";

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.screen_slide_page, container, false);

            TextView tv = (TextView) rootview.findViewById(R.id.screen_slide_page_text_view);

            int pozicija = getArguments().getInt(POLJE_POZICIJA);
            String[] slideovi = getResources().getStringArray( R.array.lista_izbora_nav_drawer );

            //promijeni text
            tv.setText( "Slide ->" + slideovi[pozicija] );

            return rootview;

        }

        public static MojSlideFragment createFragment( int position ){
            MojSlideFragment slideFragment = new MojSlideFragment();
            Bundle bundle = new Bundle();
            bundle.putInt( POLJE_POZICIJA, position );
            slideFragment.setArguments( bundle );


            return slideFragment;
        }
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_pager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
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

    //end menu
}
