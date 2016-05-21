package clothesup.turningpoint.clothesup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Hanbyeol on 2016-03-26
 */
public class GuideActivity extends Activity implements View.OnClickListener {
    private WebView guideWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        guideWebView = (WebView) findViewById(R.id.blogGuideWebView);
        guideWebView.getSettings().setJavaScriptEnabled(true);
        guideWebView.loadUrl("http://m.naver.com");
        guideWebView.setWebViewClient(new HelloWebViewClient());

        ImageView backToGuide = (ImageView) findViewById(R.id.imageView_guide_backButton);
        backToGuide.setOnClickListener(this);
    }

    private class HelloWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imageView_guide_backButton) {
            this.finish();
        }
    }
}