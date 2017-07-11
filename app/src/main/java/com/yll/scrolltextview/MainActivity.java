package com.yll.scrolltextview;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyTextView textView = (MyTextView)findViewById(R.id.text);
        textView.startMarquee();
    }
}
