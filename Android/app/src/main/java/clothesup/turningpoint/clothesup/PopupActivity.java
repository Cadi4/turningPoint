package clothesup.turningpoint.clothesup;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

public class PopupActivity extends Activity {
    private List<ContentDB> content;
    private float[] clickedXY;
    private TextView storeId, storeName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        popupSetting();
        init();
        gettingIntentData();
        (new getPopupData()).execute();
        Log.i("in popup activity, clickedX", String.valueOf(clickedXY[0]));
        Log.i("in popup activity, clickedY", String.valueOf(clickedXY[0]));
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
            }
            else{
                Log.e("Async", "two element!! or empty");
            }
            Log.i("Async", "end");
        }
    }
}
