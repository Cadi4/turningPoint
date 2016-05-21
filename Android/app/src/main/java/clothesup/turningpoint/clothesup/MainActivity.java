package clothesup.turningpoint.clothesup;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.view.View;
import java.util.List;

//import com.facebook.FacebookSdk;
//import com.facebook.login.widget.LoginButton;

/**
 *  main page, login page
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static String LW;            // login with what?
    private ImageView loginWithNaver, loginWithFb, loginWithGoogle; // UI
    private static Context mContext;     //context
    OAuthNaver oAuthNaver;              //instance for login with Naver

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_main);
        mContext = this;                      //context of mainActivity
        oAuthNaver = new OAuthNaver();      //instance for login with Naver
        initView();
    }

    public static Context getmContext() {
        return mContext;
    }

    /**
     *  initialize views in mainActivity, and set onClickListener
     */
    private void initView() {
        loginWithNaver= (ImageView)findViewById(R.id.login_with_naver_button);
        //loginWIthFb = (LoginButton)findViewById(R.id.login_with_fb_button);
        loginWithGoogle = (ImageView)findViewById(R.id.login_with_google_button);
        loginWithNaver.setOnClickListener(this);
        //loginWIthFb.setOnClickListener(this);
        loginWithGoogle.setOnClickListener(this);
    }

    /**
     *  control the situation when login button is clicked
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login_with_naver_button :
                LW = "Naver";
                oAuthNaver.startLogin();
                break;
            //case R.id.login_with_fb_button :
            //    break;
            case R.id.login_with_google_button :
                break;
        }
    }
}
