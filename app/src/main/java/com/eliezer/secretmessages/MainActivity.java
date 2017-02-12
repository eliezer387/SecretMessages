package com.eliezer.secretmessages;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //declarar variables para GUI
    EditText txtIn;
    EditText txtKey;
    EditText txtOut;
    SeekBar sb;
    Button btnEncode;

    public String encode(String message, int k){
        String out ="";
        char key = (char)k;

        for (int x = 0; x < message.length(); x++) {
            char in = message.charAt(x);

            if (in >= 'A' && in <= 'Z') {
                in += key;

                if (in > 'Z')
                    in -= 26;

                if (in < 'A')
                    in += 26;
            }

            if (in >= 'a' && in <= 'z') {
                in += key;

                if (in > 'z')
                    in -= 26;

                if (in < 'a')
                    in += 26;
            }

            if (in >= '0' && in <='9')
            {
                in += (k % 10 );
                if(in > '9')
                    in-=10;
                if(in < '0')
                    in+=10;
            }

            out += in;
        }

        return out;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtIn = (EditText)findViewById(R.id.txtIn);
        txtKey = (EditText)findViewById(R.id.txtKey);
        txtOut = (EditText)findViewById(R.id.txtOut);
        sb = (SeekBar)findViewById(R.id.seekBar);
        btnEncode = (Button)findViewById(R.id.btnEncode);

        btnEncode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int key = Integer.parseInt(txtKey.getText().toString());
                String message = txtIn.getText().toString();
                String out = encode(message, key);
                txtOut.setText(out);
                txtKey.setText(""+key);
            }
        });

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int key = i-13;
                String message = txtIn.getText().toString();
                String out = encode(message, key);
                txtOut.setText(out);
                txtKey.setText(""+key);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        txtKey.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                int progress = Integer.parseInt(txtKey.getText().toString());
                sb.setProgress(progress+13);
                return true;
            }
        });
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
