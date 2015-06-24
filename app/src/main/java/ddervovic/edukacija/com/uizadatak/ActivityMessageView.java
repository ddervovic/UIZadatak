package ddervovic.edukacija.com.uizadatak;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ActivityMessageView extends ActionBarActivity {

    Osobe mOsoba;
    private List<String> poruke = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_message_view);

        Bundle bundle = getIntent().getBundleExtra( FirstScreen.PASSED_PARAMS );

        if( bundle != null ){
            int position = bundle.getInt( FirstScreen.POSITION );
            mOsoba = FirstScreen.mojeOsobe.get( position );
        }

        setTitle( mOsoba.getIme() + " " + mOsoba.getBroj() );
        poruke.add( mOsoba.getSms() );

        ListView listViewMessages = (ListView)  findViewById( R.id.listViewMessages );
        MyMessageAdapter adapter = new MyMessageAdapter();
        listViewMessages.setAdapter( adapter );

    }


    //adapter za ListView
    private class MyMessageAdapter extends ArrayAdapter{

        public MyMessageAdapter() {
            super(ActivityMessageView.this, R.layout.list_item_message_view, poruke);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if( convertView == null ){
                convertView = getLayoutInflater().inflate( R.layout.list_item_message_view, parent, false );
            }

            TextView tv = (TextView) convertView.findViewById( R.id.message_text );
            if ( mOsoba != null ) tv.setText( mOsoba.getSms() );

            return convertView;

        }
    }

    private class Message{
        private String message;
        private boolean isMoja;

        public String getMessage() {
            return message;
        }

        public boolean isMoja() {
            return isMoja;
        }

        public Message(String message, boolean isMoja) {
            this.message = message;
            this.isMoja = isMoja;
        }
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_message_view, menu);
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
