package io.github.gabrieldev.passwordgenerator;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListView  extends ArrayAdapter<String> {

    private String[] mName;
    private String[] mValues;
    private Activity mContext;

    public CustomListView(Activity mContext, String[] mName, String[] mValues) {
        super(mContext, R.layout.custom_listview, mName);
        this.mContext = mContext;
        this.mName = mName;
        this.mValues = mValues;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.custom_listview, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewValue = (TextView) listViewItem.findViewById(R.id.textViewValues);

        textViewName.setText(mName[position]);
        textViewValue.setText(mValues[position]);
        return  listViewItem;
    }
}
