package rainey.id;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_item);
        ListAdapter adapter = new ListAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
    }


    private class ListAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

        Context context;
        String[] title;
        LayoutInflater inflater;

        public ListAdapter(Context context) {
            this.context = context;
            title = context.getResources().getStringArray(R.array.list);
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public String getItem(int position) {
            return title[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            title.setText(getItem(position));
            return convertView;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent();
            switch (position) {
                case 0:
                    intent.setClass(context, ObserverActivity.class);
            }
            context.startActivity(intent);
        }
    }
}
