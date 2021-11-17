package com.psttrmtc.threading;



import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Handler mHandler;
    private ProgressBar mProgressBar;
    private EditText time;
    private Button mButton;
    private TextView percentText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler();
        mProgressBar = findViewById(R.id.mProgressBar);
        percentText = findViewById(R.id.progressPer);
        time = findViewById(R.id.progressBarSpeed);
        mButton = findViewById(R.id.startButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButton.setEnabled(false);
                mButton.setAlpha(0f);
                mButton.animate().alpha(0f).setDuration(1000).start();
                final int speed = Integer.parseInt(time.getText().toString());
//                new LoadingTask().execute(speed);



                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setProgress(0);
                        percentText.setText("0%");
                        mProgressBar.setProgress(0);
                            for (int i = 0; i <= speed + (50 - speed % 50) ; i=i+50) {
                                try {
                                    Thread.sleep(50);
                                    mProgressBar.setProgress((int) ((float)(i)/speed*100));
                                    percentText.setText( String.valueOf((int) ((float)(i)/(speed+ (50 - speed % 50))*100))+"%");
//                                    percentText.setText( String.valueOf(i)+"%");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        mButton.setEnabled(true);
                                        mButton.animate().alpha(1f).setDuration(500).start();
                                        mButton.setEnabled(true);
                                    }
                                }, 0);

                        }

                }).start();

            }
        });
    }
//    private class LoadingTask extends AsyncTask<Integer, Integer, Void>{
//
//
//        @Override
//        protected Void doInBackground(final Integer... speed) {
//            for (int i = 0; i <= speed[0] + (50 - speed[0] % 50) ; i=i+50) {
//                try {
//                    Thread.sleep(50);
////                    mProgressBar.setProgress((int) ((float)(i)/speed[0]*100));
////                    percentText.setText( String.valueOf((int) ((float)(i)/(speed[0]+ (50 - speed[0] % 50))*100))+"%");
//////                            percentText.setText( String.valueOf(i)+"%");
//                    publishProgress((int) ((float)(i)/speed[0]*100));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected  void onPreExecute(){
//            mButton.setEnabled(false);
//            mButton.setAlpha(0f);
//            mButton.animate().alpha(0f).setDuration(1000).start();
//            mProgressBar.setProgress(0);
//            percentText.setText("0%");
//            mProgressBar.setProgress(0);
//        }
//        @Override
//        protected void onProgressUpdate(Integer... progress){
//            mProgressBar.setProgress(progress[0]);
//            percentText.setText(progress[0].toString() + '%');
//        }
//        @Override
//        protected  void onPostExecute(final Void unused){
//            mButton.setEnabled(true);
//            mButton.animate().alpha(1f).setDuration(500).start();
//            mButton.setEnabled(true);
//        }
//    }
}