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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView mEditTextPassword;
    private BottomSheetDialog mBottomSheetDialog;
    private String mTextOfEditText;
    private ClipboardManager mClipboardManager;
    private ClipData mClipData;

    @SuppressWarnings("all")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mBottomSheetDialog = new BottomSheetDialog(this);
        View mBottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet, null);
        mBottomSheetDialog.setContentView(mBottomSheetView);

        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        Button mButtonGenerate = (Button) findViewById(R.id.mButtonGenerate);
        TextView mTextViewMoreChoose = (TextView) findViewById(R.id.mTextViewMoreChoose);
        mEditTextPassword = (AutoCompleteTextView) findViewById(R.id.mEditTextPassword);
        FloatingActionButton mFabHome = (FloatingActionButton) findViewById(R.id.mFabHome);

        LinearLayout mSettingBottomSheet = (LinearLayout) mBottomSheetDialog.findViewById(R.id.mSettingButtonBottomSheet);
        LinearLayout mCopyBottomSheet = (LinearLayout) mBottomSheetDialog.findViewById(R.id.mCopyButtonBottomSheet);
        LinearLayout mDeleteBottomSheet = (LinearLayout) mBottomSheetDialog.findViewById(R.id.mDeleteButtonBottomSheet);

        mSettingBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passToSetting = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(passToSetting);
                finish();
            }
        });
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
                mBottomSheetDialog.dismiss();
            }
        });
        mDeleteBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditTextPassword.setText("");
                mBottomSheetDialog.dismiss();
            }
        });
        mFabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.show();
            }
        });
    }
}
