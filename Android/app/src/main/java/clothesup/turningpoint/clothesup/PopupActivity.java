package clothesup.turningpoint.clothesup;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class PopupActivity extends Activity {
    private List<ContentDB> content;
    private float[] clickedXY;
    private TextView storeId, storeName, score, kind;
    private ImageView favorite, history, card, cash, exchange, refund, fitting;
    private LinearLayout controlStar;
    private TextView[] style;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popupSetting();
        init();
        gettingIntentData();
        (new getPopupData()).execute();
        Log.i("in popup activity", "clickedX : " + String.valueOf(clickedXY[0]));
        Log.i("in popup activity", "clickedY : " + String.valueOf(clickedXY[1]));
    }

    private void popupSetting() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);  // no title bar
        WindowManager.LayoutParams layoutParams= new WindowManager.LayoutParams();
        layoutParams.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount= 0.7f;       // set dim
        getWindow().setAttributes(layoutParams);
        setContentView(R.layout.activity_popup);
    }
    private void init() {
        storeId = (TextView)findViewById(R.id.textview_popup_store_id);
        storeName = (TextView)findViewById(R.id.textview_popup_store_name);
        score = (TextView)findViewById(R.id.textview_popup_score);
        kind = (TextView) findViewById(R.id.image_popup_kind);
        favorite = (ImageView)findViewById(R.id.image_popup_favor);
        history = (ImageView)findViewById(R.id.image_popup_history);
        card = (ImageView)findViewById(R.id.image_popup_card);
        cash = (ImageView)findViewById(R.id.image_popup_cash);
        exchange = (ImageView)findViewById(R.id.image_popup_exchange);
        refund = (ImageView)findViewById(R.id.image_popup_refund);
        fitting = (ImageView)findViewById(R.id.image_popup_fitting);
        controlStar = (LinearLayout)findViewById(R.id.image_popup_score);
        style = new TextView[3];
        style[0] = (TextView)findViewById(R.id.image_popup_style1);
        style[1] = (TextView)findViewById(R.id.image_popup_style2);
        style[2] = (TextView)findViewById(R.id.image_popup_style3);
    }
    private void gettingIntentData() {
        Intent intent = getIntent();
        clickedXY = new float[2];
        clickedXY = (float[]) intent.getSerializableExtra("data for popup");
    }
    private class getPopupData extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {
            ;
        }
        protected Void doInBackground(Void... params) {
            Proxy proxy = new Proxy();
            content = proxy.findServerStoreByLocation(clickedXY);
            if(content == null){
                Log.e("server connection fail", "Proxy fail e");
            }
            else if(content.isEmpty()) {
                Log.i("Async Proxy cannot find", String.valueOf(content));
                finish();
            }
            return null;
        }
        protected void onPostExecute(Void avoid) {
            if(content != null &&!content.isEmpty() && content.size() == 1) {
                Log.e("Async Content : ", String.valueOf(content));
                storeId.setText(content.get(0).getId());
                storeName.setText(content.get(0).getName().get(0));
                score.setText(String.valueOf((float)content.get(0).getInfo().getScore()));
                if(content.get(0).getInfo().getKind().size() == 1)
                    kind.setText(content.get(0).getInfo().getKind().get(0));
                for(int i = 0; i < content.get(0).getInfo().getStyle().size(); i++){
                    style[i].setText(content.get(0).getInfo().getStyle().get(i));
                }
                setFavoriteAndHistory(content.get(0).getId());
                setCardAndCashEtc();
                //history.setAlpha(isHistory());
                //card;
                //cash
                //exchange
                //refund
                //fitting
                ViewGroup.LayoutParams params = controlStar.getLayoutParams();
                //float width = (float)content.get(0).getInfo().getScore();
                float width = 3.0f;
                width = calWidth(width);
                params.width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, getResources().getDisplayMetrics());
                params.height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, getResources().getDisplayMetrics());
                controlStar.setLayoutParams(params);

            }
            else{
                Log.e("Async", "two element!! or empty");
            }
            Log.i("Async", "end");
        }

        private void setFavoriteAndHistory(final String id) {
            OAuthNaver oAuthNaver = new OAuthNaver();
            AfterLoginActivity AFL = new AfterLoginActivity();
            String userInfo = oAuthNaver.getOpenAPI();
            final String userEmail = AFL.xmlParsingByXpath(userInfo, "email");
            Log.i("userEmail : ", userEmail);
            favorite.setAlpha(15);
            history.setAlpha(15);
            new Thread(){
                public void run(){
                    Proxy proxy = new Proxy();
                    List<UserDB> userdb = proxy.findUserByEmail("bro.jo@gmail.com");//userEmail);
                    if(userdb == null){
                        Log.e("server connection fail", "Proxy fail e");
                    }
                    else if(userdb.isEmpty()) {
                        Log.i("Async Proxy cannot find", String.valueOf(content));
                    }
                    if(userdb != null &&!userdb.isEmpty() && userdb.size() == 1) {
                        Log.e("userdb ::", String.valueOf(userdb.get(0)));
                        Log.e("userdb ::", String.valueOf(userdb.get(0).getVisit_store()));
                        Log.e("userdb ::", String.valueOf(userdb.get(0).getFav_store()));
                        if(userdb.get(0).getFav_store().contains(id)) {
                            runOnUiThread(new Runnable(){ public void run(){favorite.setAlpha(255);}});
                        }
                        if(userdb.get(0).getVisit_store().contains(id)) {
                            runOnUiThread(new Runnable(){ public void run(){history.setAlpha(255);}});
                        }
                    }
                }
            }.start();
        }
    }

    private float calWidth(float width) {
        if(width < 0.1f)    return 0;
        else if(width < 0.4f)   return 5;
        else if(width < 0.6f)   return 7.3f;
        else if(width < 0.9f)   return 9.7f;
        else if(width < 1.1f)   return 12;
        else if(width < 1.4f)   return 23.4f;
        else if(width < 1.6f)   return 25.32f;
        else if(width < 1.9f)   return 27.4f;
        else if(width < 2.1f)   return 29;
        else if(width < 2.4f)   return 40.5f;
        else if(width < 2.6f)   return 42.9f;
        else if(width < 2.9f)   return 44.6f;
        else if(width < 3.1f)   return 48;
        else if(width < 3.4f)   return 0;
        else if(width < 3.6f)   return 0;
        else if(width < 3.9f)   return 0;
        else if(width < 4.1f)   return 0;
        else if(width < 4.4f)   return 0;
        else if(width < 4.6f)   return 0;
        else if(width < 4.9f)   return 0;
        else if(width < 5.1f)   return 0;
        return 0;
    }

    private void setCardAndCashEtc() {
        Log.e("is ::", String.valueOf(content.get(0).getInfo().isCard()));
        Log.e("is ::", String.valueOf(content.get(0).getInfo().isCash()));
        Log.e("is ::", String.valueOf(content.get(0).getInfo().isExchange()));
        Log.e("is ::", String.valueOf(content.get(0).getInfo().isFitting()));
        Log.e("is ::", String.valueOf(content.get(0).getInfo().isRefund()));
    }
}
