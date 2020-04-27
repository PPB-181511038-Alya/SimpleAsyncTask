package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask <Void, Integer, String> {
    private WeakReference<TextView> mTextView;

    SimpleAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }
    @Override
    protected String doInBackground(Void... voids) {

        // Generate a random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        int s = n*200;

        // Sleep for the random amount of time
        for (int i = 1; i <= s/200; i++){
            try {
                Thread.sleep(200);
                publishProgress(i*200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Return a String result
        return "Awake at last after sleeping for " + s + " milliseconds!";
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        mTextView.get().setText("Sleeping for..."+ values[0] + "milliseconds!");
    }
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

}
