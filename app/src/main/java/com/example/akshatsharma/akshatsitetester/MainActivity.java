package com.example.akshatsharma.akshatsitetester;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends ActionBarActivity {

    Button test,clear;
    EditText url,search;
    TextView show;
    String t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = (Button) findViewById(R.id.button);
        clear = (Button) findViewById(R.id.button2);
        url = (EditText) findViewById(R.id.myText);
        //show = (TextView) findViewById(R.id.showView);
        search = (EditText) findViewById(R.id.editText);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((url.getText() + "") == "")||((search.getText()+"")==""))
                    Toast.makeText(getApplicationContext(), "Please Enter the values", Toast.LENGTH_LONG).show();
                else {
                    MyAppThread obj=new MyAppThread();
                    t1= url.getText().toString();
                    t1="https://www."+t1;
                    t2=search.getText()+"";
                    obj.execute(t1,t2);

                }
            }

        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url.setText("");
                search.setText("");
                t1 = "";
                t2 = "";
            }


        });
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

    public class MyAppThread extends AsyncTask<String, Void, String> {

        String myURL="";
        String str="";
        Pattern r;
        @Override
        protected String doInBackground(String... params) {
            myURL = params[0];
            str=params[1];
            r=Pattern.compile(str);
            String response = "";

                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(myURL);
                try {
                    HttpResponse execute = client.execute(httpGet);
                    InputStream content = execute.getEntity().getContent();

                    BufferedReader buffer = new BufferedReader(
                            new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


                return response;
            }

            @Override
            protected void onPostExecute(String result) {

            //show.setText(result+"");

                Matcher m=r.matcher(result);
              if(m.find()) {

                   // show.setText("true");
                  LayoutInflater inflater=getLayoutInflater();
                  View layout= inflater.inflate(R.layout.correct_toast,(ViewGroup)findViewById(R.id.correct_toast_layout));
                  ImageView imageView=(ImageView)layout.findViewById(R.id.image);
                  imageView.setImageResource(R.drawable.correct);
                  TextView textView=(TextView)layout.findViewById(R.id.correct_text);
                  textView.setText("Congratulations Correct Match Found. Please Bookmark the Site, Now Redirecting you to the site");
                  Toast toast=new Toast(getApplicationContext());
                  toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                  toast.setDuration(Toast.LENGTH_LONG);
                  toast.setView(layout);
                  toast.show();
                  openMyWeb();

                }
                    else
                    {
                        //show.setText("false");
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.incorrect_toast, (ViewGroup) findViewById(R.id.incorrect_toast_layout));
                        ImageView imageView = (ImageView) layout.findViewById(R.id.image2);
                        imageView.setImageResource(R.drawable.incorrect);
                        TextView textView = (TextView) layout.findViewById(R.id.incorrect_text);
                        textView.setText("Site is Uninteresting, Please Ignore the site");
                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.show();
                }



            }

            @Override
            protected void onPreExecute() {
            }

            @Override
            protected void onProgressUpdate(Void... values) {
            }
            private void openMyWeb(){

                Uri site1 = Uri.parse(String.valueOf(myURL));
                Intent intent = new Intent( Intent.ACTION_VIEW, site1 );
                startActivity (intent);
            }

        }
    }
