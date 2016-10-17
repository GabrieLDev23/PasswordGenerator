package io.github.gabrieldev.passwordgenerator;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private CheckedTextView mCheckedTextViewUppercase, mCheckedTextViewLowercase, mCheckedTextViewNumbers, mCheckedTextViewSpacials;
    public static boolean mBoolUppercase = false, mBoolLowercase = false, mBoolNumbers = false, mBoolSpecials = false;
    public static int mIntLength;
    private String mStringExtractTextLength;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMain = new Intent(SettingsActivity.this,MainActivity.class);
                startActivity(backToMain);
                finish();
            }
        });
        Button mButtonInsertLength = (Button) findViewById(R.id.mButtonInsertLength);
        mCheckedTextViewUppercase = (CheckedTextView) findViewById(R.id.mCheckedTextViewUppercase);
        mCheckedTextViewLowercase = (CheckedTextView) findViewById(R.id.mCheckedTextViewLowercase);
        mCheckedTextViewNumbers = (CheckedTextView) findViewById(R.id.mCheckedTextViewNumbers);
        mCheckedTextViewSpacials = (CheckedTextView) findViewById(R.id.mCheckedTextViewSpecials);
        FloatingActionButton mFabSetting = (FloatingActionButton) findViewById(R.id.mFabSettings);

        loadingPreferences();

        mCheckedTextViewUppercase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBoolUppercase) {
                    mBoolUppercase = false;
                    mCheckedTextViewUppercase.setChecked(false);
                } else {
                    mBoolUppercase = true;
                    mCheckedTextViewUppercase.setChecked(true);
                }
            }
        });
        mCheckedTextViewLowercase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBoolLowercase) {
                    mBoolLowercase = false;
                    mCheckedTextViewLowercase.setChecked(false);
                } else {
                    mBoolLowercase = true;
                    mCheckedTextViewLowercase.setChecked(true);
                }
            }
        });
        mCheckedTextViewNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBoolNumbers) {
                    mBoolNumbers = false;
                    mCheckedTextViewNumbers.setChecked(false);
                } else {
                    mBoolNumbers = true;
                    mCheckedTextViewNumbers.setChecked(true);
                }
            }
        });
        mCheckedTextViewSpacials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBoolSpecials) {
                    mBoolSpecials = false;
                    mCheckedTextViewSpacials.setChecked(false);
                } else {
                    mBoolSpecials = true;
                    mCheckedTextViewSpacials.setChecked(true);
                }
            }
        });
        mButtonInsertLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVoidShowInsertLength();
            }
        });
        mFabSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToInfo = new Intent(SettingsActivity.this,InfoActivity.class);
                startActivity(switchToInfo);
                finish();
            }
        });
    }

    public void loadingPreferences() {
        if (MainActivity.mBoolError) {
            Toast.makeText(this, R.string.mStringErrorLengthNotSetted, Toast.LENGTH_SHORT).show();
        }
        if (mBoolUppercase) {
            mCheckedTextViewUppercase.setChecked(true);
        } else {
            mCheckedTextViewUppercase.setChecked(false);
        }
        if (mBoolLowercase) {
            mCheckedTextViewLowercase.setChecked(true);
        } else {
            mCheckedTextViewLowercase.setChecked(false);
        }
        if (mBoolNumbers) {
            mCheckedTextViewNumbers.setChecked(true);
        } else {
            mCheckedTextViewNumbers.setChecked(false);
        }
        if (mBoolSpecials) {
            mCheckedTextViewSpacials.setChecked(true);
        } else {
            mCheckedTextViewSpacials.setChecked(false);
        }
    }

    private void mVoidShowInsertLength() {
        AlertDialog.Builder mBuilderInsertLength = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View mBuilderView = layoutInflater.inflate(R.layout.custom_builder, null);
        mBuilderInsertLength.setView(mBuilderView);

        editText = (EditText) mBuilderView.findViewById(R.id.mEditTextLength);

        mBuilderInsertLength.setTitle("");
        mBuilderInsertLength.setMessage(R.string.mStringMessageBuilder);
        mBuilderInsertLength.setPositiveButton(R.string.mStringOK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mStringExtractTextLength = editText.getText().toString();
                mIntLength = Integer.parseInt(mStringExtractTextLength);
                Toast.makeText(SettingsActivity.this, R.string.mStringSaveSuccessful, Toast.LENGTH_SHORT).show();
            }
        });
        mBuilderInsertLength.setNegativeButton(R.string.mStringNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        mBuilderInsertLength.show();
    }
}
