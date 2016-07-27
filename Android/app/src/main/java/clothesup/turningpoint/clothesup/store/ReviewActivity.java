package clothesup.turningpoint.clothesup.store;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

import clothesup.turningpoint.clothesup.R;

/**
 * Created by Hanbyeol on 2016-07-20.
 */
public class ReviewActivity extends Activity implements View.OnClickListener {
    EditText visitDate;
    private int visit_year, visit_month, visit_day;
    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_register);

        initView();
        setVisitingDate();
        registerReview();
  }

    public void initView() {
        ImageView backToDetail = (ImageView) findViewById(R.id.imageView_review_backToDetail);
        TextView register = (TextView) findViewById(R.id.textView_review_register);
        ImageView storeImage = (ImageView) findViewById(R.id.imageView_review_storeImage);
        TextView storeId = (TextView) findViewById(R.id.textView_review_storeId);
        TextView storeName = (TextView) findViewById(R.id.textView_review_storeName);

        backToDetail.setOnClickListener(this);
        register.setOnClickListener(this);
        Picasso.with(this).load("http://52.196.54.163:1337/" + getIntent().getStringExtra("store_image"))
                .resize(50,50).into(storeImage);
        storeId.setText(getIntent().getStringExtra("store_id"));
        storeName.setText(getIntent().getStringExtra("store_name"));
    }

    public void setVisitingDate() {
        visitDate = (EditText) findViewById(R.id.editText_visit_date);

        final Calendar objTime = Calendar.getInstance();
        visit_year = objTime.get(Calendar.YEAR);
        visit_month = objTime.get(Calendar.MONTH);
        visit_day = objTime.get(Calendar.DAY_OF_MONTH);

        visitDate.setOnClickListener(this);
    }

    public void registerReview() {
        ViewGroup registerPhoto = (ViewGroup) findViewById(R.id.layout_review_photo);

        registerPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.imageView_review_backToDetail:
                finish();
                break;
            case R.id.textView_review_register:
                // 서버에 리뷰 데이터 전송하는 method
                break;
            case R.id.editText_visit_date:
                showDialog(DATE_DIALOG_ID);
                break;
            case R.id.layout_review_photo: {
              dialogView();
            }
        }
    }

    public void dialogView() {
        PhotoDialog dialog = new PhotoDialog(this);

        dialog.show();
    }

    protected Dialog onCreateDialog(int id) {
        switch(id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener,
                        visit_year, visit_month, visit_day);
        }
        return null;
    }

    private void updateDisplay(EditText editText) {
        editText.setText(new StringBuffer().append(visit_year).append("년 ")
                .append(visit_month).append("월 ").append(visit_day).append("일"));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            visit_year = year;
            visit_month = monthOfYear;
            visit_day = dayOfMonth;

            updateDisplay(visitDate);
        }
    };
}
