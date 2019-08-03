package in.tecpool.chatbot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by uvclient2 on 03/08/2019.
 */

public class ChatBot extends WebView implements View.OnTouchListener{

    Activity activity;
    String botKey,userKey;
    String info[];

    WebView webView;

    String pre="<html><body><img align=\"middle\" style=\"width:90%;height:90%;\" src=\"";
    String post="\"></body></html>";

    private static final int MAX_CLICK_DURATION = 200;
    private long startClickTime;


    public ChatBot(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(Activity activity, String botKey, String userKey)
    {
        this.activity=activity;
        this.botKey=botKey;
        this.userKey=userKey;

        this.getSettings().setJavaScriptEnabled(true);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setBackgroundResource(R.drawable.shadow);
        this.setHorizontalScrollBarEnabled(false);
        this.setVerticalScrollBarEnabled(false);
        this.setOnTouchListener(this);
        webView=this;
        new AsyncValidateBot().execute();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                startClickTime = Calendar.getInstance().getTimeInMillis();
                break;
            }
            case MotionEvent.ACTION_UP: {
                long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if(clickDuration < MAX_CLICK_DURATION) {
                    activity.startActivity(new Intent(activity,ChatBotActivity.class).putExtra("botKey",botKey).putExtra("userKey",userKey));
                }else
                {
                    Toast.makeText(activity,"Hi! Im Bot", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return true;
    }

    public class AsyncValidateBot extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);

        }

        @Override
        protected String doInBackground(String... params) {
            WebserviceCall com = new WebserviceCall(activity.getBaseContext(), activity);
            try {

                String param13[][] = {{"uniqueId", botKey}};
                String aResponse13 = com.getStringWithoutActivity("getBotMaster", param13);

                return aResponse13;
            } catch (Exception ds) {
                return ds.toString();
            }

        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result.startsWith("OK"))
            {
                info=result.substring(3).split(",");

                webView.loadDataWithBaseURL(null, pre+info[5]+post, "text/html", "utf-8", null);

            }
            else
            {
                Toast toast = Toast.makeText(activity.getBaseContext(), result.toString(), Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 100);
                toast.show();
            }
        }

    }
}
