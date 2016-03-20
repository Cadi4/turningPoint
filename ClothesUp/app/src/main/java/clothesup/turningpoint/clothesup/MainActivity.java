package clothesup.turningpoint.clothesup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.StaticLayout;
import android.util.Log;
import android.widget.ImageView;
import android.view.View;
import android.widget.Toast;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginDefine;
import com.nhn.android.naverlogin.OAuthLoginHandler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static String LW;

    // UI
    private ImageView loginWithNaver;
    private ImageView loginWIthFb;
    private ImageView loginWithGoogle;
    private Intent intent;
    // for login with naver // information of client
    private static String OAUTH_CLIENT_ID = "zUKm7ls5Sf9He48fUwTZ";
    private static String OAUTH_CLIENT_SECRET = "nvqug0vxU8";
    private static String OAUTH_CLIENT_NAME = "ClothesUp";
    private static String accessToken = "";
    private static String refreshToken = "";
    private static String EmailAddress;
    private static OAuthLogin mOAuthLoginInstance;
    public static Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // for login with Naver
        OAuthLoginDefine.DEVELOPER_VERSION = true;
        mContext = this;
        initData();

        loginWithNaver= (ImageView)findViewById(R.id.login_with_naver_button);
        loginWIthFb = (ImageView)findViewById(R.id.login_with_fb_button);
        loginWithGoogle = (ImageView)findViewById(R.id.login_with_google_button);
        loginWithNaver.setOnClickListener(this);
        loginWIthFb.setOnClickListener(this);
        loginWithGoogle.setOnClickListener(this);

    }

    private void initData() {
        mOAuthLoginInstance = OAuthLogin.getInstance();
        mOAuthLoginInstance.init(mContext, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login_with_naver_button :
                Log.i("OAuthSampleActivity", "naver! button clicked");
                LW = "Naver";
                mOAuthLoginInstance.startOauthLoginActivity((Activity) mContext, mOAuthLoginHandler);
                break;
            case R.id.login_with_fb_button :
                break;
            case R.id.login_with_google_button :
                break;
        }
    }
    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                accessToken = mOAuthLoginInstance.getAccessToken(mContext);
                refreshToken = mOAuthLoginInstance.getRefreshToken(mContext);
                long expiresAt = mOAuthLoginInstance.getExpiresAt(mContext);
                String tokenType = mOAuthLoginInstance.getTokenType(mContext);
                //mOauthAT.setText(accessToken);
                //mOauthRT.setText(refreshToken);
                //mOauthExpires.setText(String.valueOf(expiresAt));
                //mOauthTokenType.setText(tokenType);
                //mOAuthState.setText(mOAuthLoginInstance.getState(mContext).toString());
                Intent intent = new Intent(mContext, AfterLoginActivity.class);
                startActivity(intent);
            } else {
                String errorCode = mOAuthLoginInstance.getLastErrorCode(mContext).getCode();
                String errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext);
                Toast.makeText(mContext, "errorCode:" + errorCode + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        };
    };

    public String getOpenAPI(){
        return mOAuthLoginInstance.requestApi(mContext, accessToken, "https://openapi.naver.com/v1/nid/me");
    }
    public void logout(){
        mOAuthLoginInstance.logout(this);
    }
    public void deleteToken(){
        new DeleteTokenTask().execute();
    }
    private class DeleteTokenTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            boolean isSuccessDeleteToken = mOAuthLoginInstance.logoutAndDeleteToken(mContext);

            if (!isSuccessDeleteToken) {
                // 서버에서 token 삭제에 실패했어도 클라이언트에 있는 token 은 삭제되어 로그아웃된 상태이다
                // 실패했어도 클라이언트 상에 token 정보가 없기 때문에 추가적으로 해줄 수 있는 것은 없음
                Log.d("MainActivity", "errorCode:" + mOAuthLoginInstance.getLastErrorCode(mContext));
                Log.d("MainActivity", "errorDesc:" + mOAuthLoginInstance.getLastErrorDesc(mContext));
            }

            return null;
        }
        protected void onPostExecute(Void v) {
            //updateView();
        }
    }

}
