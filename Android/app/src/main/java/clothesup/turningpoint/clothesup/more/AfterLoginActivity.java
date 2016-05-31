package clothesup.turningpoint.clothesup.more;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.view.View;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.StringReader;

import clothesup.turningpoint.clothesup.login.MainLoginActivity;
import clothesup.turningpoint.clothesup.login.OAuthNaver;
import clothesup.turningpoint.clothesup.R;

/**
 * Created by bro. Jo
 */
public class AfterLoginActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView loginWIthWhat, userInfoTextView, deleteToken, logout;
    OAuthNaver oAuthNaver = new OAuthNaver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        initViews();
        fillTextViews();
        deleteToken.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    private void initViews() {
        loginWIthWhat = (TextView) findViewById(R.id.textview_login_with_what);
        userInfoTextView = (TextView) findViewById(R.id.textview_user_info);
        deleteToken = (TextView) findViewById(R.id.button_delete_token);
        logout = (TextView) findViewById(R.id.button_logout);
    }
    private void fillTextViews() {
        loginWIthWhat.setText("Login with " + MainLoginActivity.LW);
        String userInfo = oAuthNaver.getOpenAPI();
        String userEmail = xmlParsingByXpath(userInfo, "email");
        String userBirthday = xmlParsingByXpath(userInfo, "birthday");
        String userAge = xmlParsingByXpath(userInfo, "age");
        String userGender = xmlParsingByXpath(userInfo, "gender");
        String userName = xmlParsingByXpath(userInfo, "name");
        userInfoTextView.setText("email : " + userEmail +
                "\nbirthday : " + userBirthday +
                "\nage : " + userAge+
                "\ngender : " + userGender+
                "\nname : " + userName +
                "\n");
    }
    public String xmlParsingByXpath(String InputXML, String target) {
        try {
            InputSource inputSource = new InputSource(new StringReader((InputXML)));
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
            XPath xpath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = (NodeList)xpath.evaluate("//data/response/" + target, document, XPathConstants.NODESET);
            for(int idx=0; idx<nodeList.getLength(); idx++){
                Log.d("xml_log", nodeList.item(idx).getTextContent());
                return nodeList.item(idx).getTextContent();
            }
        }
        catch(Exception e){
            Log.e("XML parsing error", "XML error");
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_delete_token :
                oAuthNaver.deleteToken();
                this.finish();
                break;
            case R.id.button_logout:
                oAuthNaver.logout();
                this.finish();
                break;
        }
    }
}