package io.github.gabrieldev.passwordgenerator;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class InfoActivity extends AppCompatActivity {

    private String[] mName = new String[] {
            "Applications Name",
            "Applications Package",
            "Applications Version",
            "GitHub Link",
            "Developer and Grafic"
    };
    private String[] mValues = new String[] {
            "Password Generator",
            "io.github.gabrieldev.passwordgenerator",
            "1.0 (Stable)",
            "https://goo.gl/3Jljl8",
            "-->GabrieLDev<--"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar mToolbarInfo = (Toolbar) findViewById(R.id.mToolbarInfo);
        setSupportActionBar(mToolbarInfo);
        mToolbarInfo.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMain = new Intent(InfoActivity.this,MainActivity.class);
                startActivity(backToMain);
                finish();
            }
        });
        ListView mListViewInfo = (ListView) findViewById(R.id.mListViewInfo);

        CustomListView mCustomAdapter;
        mCustomAdapter = new CustomListView(this, mName, mValues);
        mListViewInfo.setAdapter(mCustomAdapter);

        mListViewInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 3) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/GabrieLDev23/PasswordGenerator")));
                }
            }
        });
    }

    public void onBackPressed() {
        Intent backToSetting = new Intent(InfoActivity.this,SettingsActivity.class);
        startActivity(backToSetting);
        finish();
    }
}
