package com.lock.lifesensexu.passwordkey;

import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class MainActivity extends AppCompatActivity {

    private EditText mOriginPassword_ed;
    private Button mGetPwd_bt;
    private EditText mNewPassword_tv;
    private Button mReset;
    private TextView mResetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        mOriginPassword_ed = (EditText) findViewById(R.id.originPassword);
        mGetPwd_bt = ((Button) findViewById(R.id.getPwd));
        mReset = ((Button) findViewById(R.id.reset));
        mNewPassword_tv = ((EditText) findViewById(R.id.NewPassword));
        mResetPassword = ((TextView) findViewById(R.id.resetPassword));
        mGetPwd_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mOriginPassword = mOriginPassword_ed.getText().toString();
                if (mOriginPassword.length() != 0) {
                    String encryptResultStr = ASCllUtils.stringToAscii(mOriginPassword);
                    mNewPassword_tv.setText(encryptResultStr);
                    copy(encryptResultStr,MainActivity.this);
                }
            }
        });
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mNewPassword = mNewPassword_tv.getText().toString();
                if (mNewPassword.length()!= 0) {
                    String decryptResult = ASCllUtils.asciiToString(mNewPassword);
                    if(decryptResult!=null){
                        mResetPassword.setText(new String(decryptResult));
                    }
                }
            }
        });
    }

    public static void copy(String content, Context context) {
// 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 实现粘贴功能
     * add by wangqianzhou
     *
     * @param context
     * @return
     */
    public static String paste(Context context) {
// 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.getText().toString().trim();
    }
}
