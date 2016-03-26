package clothesup.turningpoint.clothesup;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Hanbyeol on 2016-03-26.
 */
public class NoticeTextActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        TextView noticeTxt = (TextView) findViewById(R.id.edit_notice);
        noticeTxt.setText(readTxt());
    }

    private String readTxt() {
        String data = null;
        InputStream inputStream = getResources().openRawResource(R.raw.textfile_notice);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream();

        int i;
        try {
            i = inputStream.read();
            while(i != -1) {
                byteArrayInputStream.write(i);
                i = inputStream.read();
            }

            data = new String(byteArrayInputStream.toByteArray(), "MS949");
            inputStream.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
