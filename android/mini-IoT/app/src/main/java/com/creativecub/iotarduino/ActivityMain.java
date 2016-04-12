package com.creativecub.iotarduino;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ActivityMain extends AppCompatActivity {

    private List<Data> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DataAdapter mAdapter;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new DataAdapter(dataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                handleClick(view, position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        prepareMovieData();
    }

    private void prepareMovieData() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.light_off);

        Data data = new Data("Light", "", "2015", bitmap, false);
        dataList.add(data);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fan_off);

        data = new Data("Fan", "", "2015", bitmap, false);
        dataList.add(data);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sound_off);

        data = new Data("Sound", "", "2015", bitmap, false);
        dataList.add(data);

        mAdapter.notifyDataSetChanged();
    }

    public void handleClick(View v, int position) {


        Data data = dataList.get(position);

        String snackText = "";


        if (data.gettoggle()) {
//            Toast.makeText(getApplicationContext(), data.getTitle() + " off!", Toast.LENGTH_SHORT).show();
            snackText = data.getTitle() + " off!";

        } else {

//            Toast.makeText(getApplicationContext(), data.getTitle() + " on!", Toast.LENGTH_SHORT).show();
            snackText = data.getTitle() + " on!";

        }
//        Toast.makeText(getApplicationContext(), data.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();

        switch (data.getTitle()) {

            case "Light":

                new DownloadFilesTask(this).execute("A");

                if (!data.gettoggle()) {

                    data.setToggle(true);
                    data.setIvIcon(BitmapFactory.decodeResource(getResources(), R.drawable.light_on));

                    mAdapter.notifyDataSetChanged();
                } else {

                    data.setToggle(false);
                    data.setIvIcon(BitmapFactory.decodeResource(getResources(), R.drawable.light_off));

                    mAdapter.notifyDataSetChanged();

                }

                break;

            case "Fan":

                new DownloadFilesTask(this).execute("B");

                if (!data.gettoggle()) {

                    data.setToggle(true);
                    data.setIvIcon(BitmapFactory.decodeResource(getResources(), R.drawable.fan_on));

                    mAdapter.notifyDataSetChanged();
                } else {

                    data.setToggle(false);
                    data.setIvIcon(BitmapFactory.decodeResource(getResources(), R.drawable.fan_off));

                    mAdapter.notifyDataSetChanged();

                }

                break;


            case "Sound":

                new DownloadFilesTask(this).execute("c");

                if (!data.gettoggle()) {

                    data.setToggle(true);
                    data.setIvIcon(BitmapFactory.decodeResource(getResources(), R.drawable.sound_on));

                    mAdapter.notifyDataSetChanged();
                } else {

                    data.setToggle(false);
                    data.setIvIcon(BitmapFactory.decodeResource(getResources(), R.drawable.sound_off));

                    mAdapter.notifyDataSetChanged();

                }

                break;


        }

        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, snackText, Snackbar.LENGTH_SHORT);

        snackbar.show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_tips, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);

        return super.onOptionsItemSelected(item);
    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ActivityMain.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ActivityMain.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    private class DownloadFilesTask extends AsyncTask<String, Void, String> {

        Context context;

        public DownloadFilesTask(Context context) {

            this.context = context;
        }

        protected String doInBackground(String... urls) {


            HttpURLConnection connection;
            OutputStreamWriter request = null;

            URL url = null;
            String response = null;
            String parameters = urls[0];

            try {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

                url = new URL("http://" + prefs.getString("ip", "192.168.0.105") + "/arduino/test/device.php?name=" + parameters);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestMethod("POST");

                request = new OutputStreamWriter(connection.getOutputStream());
                request.write(parameters);
                request.flush();
                request.close();
                String line = "";
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                // Response from server after login process will be stored in response variable.
                response = sb.toString();
                // You can perform UI operations here
//                Toast.makeText(ActivityMain.this,"Message from Server: \n"+ response, Toast.LENGTH_SHORT).show();

                isr.close();
                reader.close();

            } catch (Exception e) {
                // Error
                e.printStackTrace();
            }

            return response;

        }


        protected void onPostExecute(String s) {

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

            Log.d("iot","Message from Server: \n" + prefs.getString("ip", "192.168.0.105") + " " + s);
//            Toast.makeText(context, "Message from Server: \n" + prefs.getString("ip", "192.168.0.105") + " " + s, Toast.LENGTH_SHORT).show();

        }
    }

}
