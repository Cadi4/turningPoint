package clothesup.turningpoint.clothesup;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.view.View;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.InputSource;
import java.io.StringReader;

public class AfterLoginActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView loginWIthWhat;
    private TextView emailAddress;
    private TextView deleteToken;
    private TextView logout;
    public static Context contextOfAfterLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);
        contextOfAfterLoginActivity = this;

        loginWIthWhat = (TextView) findViewById(R.id.textview_login_with_what);
        emailAddress = (TextView) findViewById(R.id.textview_email_address);
        deleteToken = (TextView) findViewById(R.id.button_delete_token);
        logout = (TextView) findViewById(R.id.button_logout);

        loginWIthWhat.setText("Login with " + MainActivity.LW);
        String a = ((MainActivity)MainActivity.mContext).getOpenAPI();
        if(a==null || a=="")        Log.i("AfterLoginActivity", "nullll");
        else               Log.i("AfterLoginActivity", "nullllnononono");
        String.format("%s", a);
        xmlParshingByXpath(a);
        Log.w("a????????????????", "왜안될까");
        Log.d("a????????????????", a);
        Log.d("a????????????????", String.valueOf(a));
        Log.i("a????????????????", a);
        Log.i("a????????????????", String.valueOf(a));
        emailAddress.setText(a + "@naver.com");

        deleteToken.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    private void xmlParshingByXpath(String InputXML) {
        try {
            Log.d("asdf!!!!!XMLXML", "들왔냥");
            InputSource inputSource = new InputSource(new StringReader((InputXML)));
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputSource);
            XPath xpath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = (NodeList)xpath.evaluate("//data/response/email/", document, XPathConstants.NODESET);
            for(int idx=0; idx<nodeList.getLength(); idx++){
                Log.d("logdlogd", nodeList.item(idx).getTextContent());
            }
            Node node = nodeList.item(0);
            Node textNode = nodeList.item(0).getChildNodes().item(0);
            Log.d("asdf!!!!!XMLXML", textNode.getNodeValue());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        //MainActivity mainActivity;
        //mainActivity = new MainActivity();
        //mainActivity = (MainActivity)getApplicationContext();
        switch(v.getId()){
            case R.id.button_delete_token :
                ((MainActivity)MainActivity.mContext).deleteToken();
                this.finish();
                break;
            case R.id.button_logout :
                ((MainActivity)MainActivity.mContext).logout();
                this.finish();
                break;
        }
    }
}