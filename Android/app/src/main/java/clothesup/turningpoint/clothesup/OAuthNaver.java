package clothesup.turningpoint.clothesup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginDefine;
import com.nhn.android.naverlogin.OAuthLoginHandler;

/**
 * this class is for login with naver
 */
public class OAuthNaver {
    // information of client
    private static String OAUTH_CLIENT_ID = "zUKm7ls5Sf9He48fUwTZ";
    private static String OAUTH_CLIENT_SECRET = "nvqug0vxU8";
    private static String OAUTH_CLIENT_NAME = "ClothesUp";
    private static String accessToken;          //token for access, this token last 4 hours
    private static String refreshToken;         //token for refreshing accessToken, refreshToken last more time than accessToken
    private static OAuthLogin mOAuthLoginInstance;
    private Context mContext = MainActivity.getmContext();

    OAuthNaver(){
        OAuthNaverInitData(mContext);
    }
    private void OAuthNaverInitData(Context mContext) {
        mOAuthLoginInstance = OAuthLogin.getInstance();
        mOAuthLoginInstance.init(mContext, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME);
        OAuthLoginDefine.DEVELOPER_VERSION = true;
    }

    /**
     *  To start login
     */
    protected void startLogin(){
        mOAuthLoginInstance.startOauthLoginActivity((Activity) mContext, mOAuthLoginHandler);
    }

    /**
     *  To control the situation after login completed
     */
    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                accessToken = mOAuthLoginInstance.getAccessToken(mContext);
                refreshToken = mOAuthLoginInstance.getRefreshToken(mContext);
                long expiresAt = mOAuthLoginInstance.getExpiresAt(mContext);
                String tokenType = mOAuthLoginInstance.getTokenType(mContext);
                //mOauthAT.setText(accessToken);    //mOauthRT.setText(refreshToken);   //mOauthExpires.setText(String.valueOf(expiresAt));
                //mOauthTokenType.setText(tokenType);  //mOAuthState.setText(mOAuthLoginInstance.getState(mContext).toString());
                Intent intent = new Intent(MainActivity.getmContext(), MainTabLayout.class);        //after login completed
                MainActivity.getmContext().startActivity(intent);
            } else {
                String errorCode = mOAuthLoginInstance.getLastErrorCode(mContext).getCode();
                String errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext);
                Toast.makeText(mContext, "errorCode:" + errorCode + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();
            }
        }
    };

    /**
     *  To logout and To delete the access token
     */
    public void deleteToken(){
        new DeleteTokenTask().execute();
    }
    private class DeleteTokenTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            boolean isSuccessDeleteToken = mOAuthLoginInstance.logoutAndDeleteToken(mContext);

            if (!isSuccessDeleteToken) {
                Log.d("MainActivity", "errorCode:" + mOAuthLoginInstance.getLastErrorCode(mContext));
                Log.d("MainActivity", "errorDesc:" + mOAuthLoginInstance.getLastErrorDesc(mContext));
            }
            return null;
        }
        protected void onPostExecute(Void v) {
            //updateView();
        }
    }

    /**
     *  To logout
     */
    public void logout(){
        mOAuthLoginInstance.logout(mContext);
    }

    /**
     *  To get information of user such as email address, birthday, age, etc...
     */
    public String getOpenAPI(){
        String userInfo = "";
        try {
            userInfo = new RequestApiTask().execute().get();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return userInfo;
    }
    private class RequestApiTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected String doInBackground(Void... params) {
            String url = "https://openapi.naver.com/v1/nid/getUserProfile.xml";
            String at = mOAuthLoginInstance.getAccessToken(mContext);
            return mOAuthLoginInstance.requestApi(mContext, at, url);
        }
        protected void onPostExecute(String userInfo) {
            Log.d("userData", userInfo);
        }
    }
}


