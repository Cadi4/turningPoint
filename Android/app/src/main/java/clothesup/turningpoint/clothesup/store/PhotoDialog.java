package clothesup.turningpoint.clothesup.store;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import clothesup.turningpoint.clothesup.R;

/**
 * Created by Hanbyeol on 2016-07-28.
 */
public class PhotoDialog extends Dialog implements View.OnClickListener {
    public Activity activity;

    public PhotoDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_photo);

        Button yesBtn = (Button) findViewById(R.id.button_dialog_yes);
        Button noBtn = (Button) findViewById(R.id.button_dialog_no);

        yesBtn.setOnClickListener(this);
        noBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_dialog_yes:
                // 사진 등록할 수 있는 method
                break;
            case R.id.button_dialog_no:
                dismiss();
        }
    }
}
