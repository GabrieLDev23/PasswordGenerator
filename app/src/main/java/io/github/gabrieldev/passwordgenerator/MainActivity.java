package io.github.gabrieldev.passwordgenerator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mButtonGenerate;
    private TextView mTextViewMoreChoose;
    private AutoCompleteTextView mEditTextPassword;
    private FloatingActionButton mFabHome;
    private BottomSheetDialog mBottomSheet;
    private Animation mRotateBackward;
    private String mPasswordGenerate, mTextOfEditText;
    private LinearLayout mGenerateBottomSheet, mCopyBottomSheet, mDeleteBottomSheet;
    private ClipboardManager mClipboardManager;
    private ClipData mClipData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        mButtonGenerate = (Button) findViewById(R.id.mButtonGenerate);
        mTextViewMoreChoose = (TextView) findViewById(R.id.mTextViewMoreChoose);
        mEditTextPassword = (AutoCompleteTextView) findViewById(R.id.mEditTextPassword);
        mFabHome = (FloatingActionButton) findViewById(R.id.mFabHome);

        mRotateBackward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);

        mBottomSheet = new BottomSheetDialog(this);
        View mBottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet, null);
        mBottomSheet.setContentView(mBottomSheetView);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new String[] {mPasswordGenerate});
        mEditTextPassword.setAdapter(mAdapter);

        mGenerateBottomSheet = (LinearLayout) findViewById(R.id.mGenerateButtonBottomSheet);
        mGenerateBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheet.dismiss();
            }
        });
        mCopyBottomSheet = (LinearLayout) findViewById(R.id.mCopyButtonBottomSheet);
        mCopyBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextOfEditText = mEditTextPassword.getText().toString().trim();
                if (TextUtils.isEmpty(mTextOfEditText)) {
                    Toast.makeText(MainActivity.this, R.string.mStringPasswordEmpty, Toast.LENGTH_SHORT).show();
                } else {
                    mClipData = ClipData.newPlainText("",mTextOfEditText);
                    mClipboardManager.setPrimaryClip(mClipData);
                }
                mBottomSheet.dismiss();
            }
        });
        mDeleteBottomSheet = (LinearLayout) findViewById(R.id.mDeleteButtonBottomSheet);
        mDeleteBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditTextPassword.setText("");
                mBottomSheet.dismiss();
            }
        });
        mFabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFabHome.startAnimation(mRotateBackward);
                //Intent passToSetting = new Intent(MainActivity.this,SettingActivity.class);
                //startActivity(passToSetting);
                //finish();
            }
        });
        mTextViewMoreChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheet.show();
            }
        });
    }
}
