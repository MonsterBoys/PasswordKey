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

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {

    private EditText mOriginPassword_ed;
    private Button mGetPwd_bt;
    private TextView mNewPassword_tv;
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
        mNewPassword_tv = ((TextView) findViewById(R.id.NewPassword));
        mResetPassword = ((TextView) findViewById(R.id.resetPassword));
        mGetPwd_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mOriginPassword = mOriginPassword_ed.getText().toString();
                if (mOriginPassword.length() != 0) {
                    toMD5(mOriginPassword);
                }
            }
        });
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String mOriginPassword = mOriginPassword_ed.getText().toString();
                if (mOriginPassword.length()!= 0) {
                    returnPwd(KL(mOriginPassword));
                }
            }
        });
    }

    public void returnPwd(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String k = new String(a);
        mResetPassword.setText(k);
    }
    public static String KL(String inStr) {
        // String s = new String(inStr);
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }
    public void toMD5(String plainText) {
        try {
            //生成实现指定摘要算法的 MessageDigest 对象。
            MessageDigest md = MessageDigest.getInstance("MD5");
            //使用指定的字节数组更新摘要。
            md.update(plainText.getBytes());
            //通过执行诸如填充之类的最终操作完成哈希计算。
            byte b[] = md.digest();
            //生成具体的md5密码到buf数组
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            if (buf != null) {
                copy(buf.toString(),MainActivity.this);
                mNewPassword_tv.setText(buf.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
