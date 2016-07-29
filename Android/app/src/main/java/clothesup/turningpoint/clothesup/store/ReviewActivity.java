package clothesup.turningpoint.clothesup.store;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

import clothesup.turningpoint.clothesup.R;

/**
 * Created by Hanbyeol on 2016-07-20.
 */
public class ReviewActivity extends Activity implements View.OnClickListener {
    private Calendar calendar;
    private int visit_day;
    private int visit_month;
    private int visit_year;
    private EditText visitDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_register);

        initView();
        setVisitDate();
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

    public void setVisitDate() {
        calendar = Calendar.getInstance();
        visit_year = calendar.get(Calendar.YEAR);
        visit_month = calendar.get(Calendar.MONTH);
        visit_day = calendar.get(Calendar.DAY_OF_MONTH);
        visitDate = (EditText) findViewById(R.id.editText_visit_date);

        visitDate.setOnClickListener(this);
    }

    public void registerReview() {
        ViewGroup registerPhoto = (ViewGroup) findViewById(R.id.layout_review_photo);
        View reviewText = (View) findViewById(R.id.editText_review_text);

        registerPhoto.setOnClickListener(this);
    }

    public static class LinedEditText extends EditText{
        private Paint paint;

        public LinedEditText(Context context, AttributeSet attrs) {
            super(context, attrs);
            paint = new Paint();

            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(getResources().getColor(R.color.line));
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int left = getLeft();
            int right = getRight();
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int height = getHeight();
            int lineHeight = getLineHeight();
            int count = (height-paddingTop-paddingBottom) / lineHeight;
            int baseLine = paddingTop;

            if(getLineCount() > count)
                count = getLineCount();

            for(int i=0; i<count; i++) {
                baseLine += lineHeight;
                canvas.drawLine(left+paddingLeft, baseLine, right-paddingRight, baseLine, paint);
                super.onDraw(canvas);
            }
        }
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
                showDialog(0);
                break;
            case R.id.layout_review_photo: {
              dialogView();
            }
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, listener, visit_year, visit_month, visit_day);
    }

    private DatePickerDialog.OnDateSetListener listener
            = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
            visitDate.setText(year + "년 " + (month+1) + "월 " + day + "일");
        }
    };

    public void dialogView() {
        PhotoDialog dialog = new PhotoDialog(this);

        dialog.show();
    }
}
