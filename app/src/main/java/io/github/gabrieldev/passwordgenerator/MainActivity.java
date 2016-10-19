package io.github.gabrieldev.passwordgenerator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

@SuppressWarnings("all")
public class MainActivity extends AppCompatActivity {

    private EditText mEditTextPassword;
    private BottomSheetDialog mBottomSheetDialog;
    private String mStringPasswordGenerate = "";
    private ClipboardManager mClipboardManager;
    private ClipData mClipData;
    private int mIntPosition = 0;
    private int mIntUnspecifiched = 0;
    public static boolean mBoolError = false;
    private String mStringUppercase[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "W", "Z"
    }; //26
    private String mStringLowercase[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "x", "y", "w", "z"
    }; //26
    private String mStringNumbers[] = {"0","1","2","3","4","5","6","7","8","9"
    }; //10
    private String mStringSpecials[] = {"!", "£", "$", "%", "&", "/", "=", "?", "^", "*", "@", "#", "§", "<", ">", "-"
    }; //16

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
        mEditTextPassword = (EditText) findViewById(R.id.mEditTextPassword);
        FloatingActionButton mFabHome = (FloatingActionButton) findViewById(R.id.mFabHome);

        LinearLayout mSettingBottomSheet = (LinearLayout) mBottomSheetDialog.findViewById(R.id.mSettingButtonBottomSheet);
        LinearLayout mCopyBottomSheet = (LinearLayout) mBottomSheetDialog.findViewById(R.id.mCopyButtonBottomSheet);
        LinearLayout mDeleteBottomSheet = (LinearLayout) mBottomSheetDialog.findViewById(R.id.mDeleteButtonBottomSheet);

        mButtonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVoidGeneratePassword();
            }
        });
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
                mStringPasswordGenerate = mEditTextPassword.getText().toString();
                if (TextUtils.isEmpty(mStringPasswordGenerate)) {
                    Toast.makeText(MainActivity.this, R.string.mStringPasswordEmpty, Toast.LENGTH_SHORT).show();
                } else {
                    mClipData = ClipData.newPlainText("",mStringPasswordGenerate);
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

    private void mVoidGeneratePassword() {
        mEditTextPassword.setText("");
        if (SettingsActivity.mIntLength==0 && SettingsActivity.mIntLength < 99) {
            mBoolError = true;
            Intent passToSettingForError = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(passToSettingForError);
            finish();
        } else {
            while (mIntUnspecifiched != SettingsActivity.mIntLength)
            {
                mIntPosition = (int)(Math.random()*5);
                if (SettingsActivity.mBoolUppercase==true && mIntPosition==1) {
                    if (mIntUnspecifiched==SettingsActivity.mIntLength)
                    {
                        break;
                    }
                    mIntUnspecifiched++;
                    mIntPosition = (int)(Math.random()*26);
                    mEditTextPassword.append(mStringUppercase[mIntPosition]);
                    mIntPosition = 0;
                }
                if (SettingsActivity.mBoolLowercase==true && mIntPosition==2) {
                    if (mIntUnspecifiched==SettingsActivity.mIntLength)
                    {
                        break;
                    }
                    mIntUnspecifiched++;
                    mIntPosition = (int)(Math.random()*26);
                    mEditTextPassword.append(mStringLowercase[mIntPosition]);
                    mIntPosition = 0;
                }
                if (SettingsActivity.mBoolNumbers==true && mIntPosition==3) {
                    if (mIntUnspecifiched==SettingsActivity.mIntLength)
                    {
                        break;
                    }
                    mIntUnspecifiched++;
                    mIntPosition = (int)(Math.random()*10);
                    mEditTextPassword.append(mStringNumbers[mIntPosition]);
                    mIntPosition = 0;
                }
                if (SettingsActivity.mBoolSpecials==true && mIntPosition==4) {
                    if (mIntUnspecifiched==SettingsActivity.mIntLength)
                    {
                        break;
                    }
                    mIntUnspecifiched++;
                    mIntPosition = (int)(Math.random()*16);
                    mEditTextPassword.append(mStringSpecials[mIntPosition]);
                    mIntPosition = 0;
                }
            }
            mBoolError = false;
            mStringPasswordGenerate = mEditTextPassword.getText().toString();
            mIntUnspecifiched = 0;
        }
    }
}
