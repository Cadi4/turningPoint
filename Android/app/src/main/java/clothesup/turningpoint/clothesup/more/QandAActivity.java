package clothesup.turningpoint.clothesup.more;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import clothesup.turningpoint.clothesup.R;

/**
 * Created by Hanbyeol on 2016-03-30.
 */
public class QandAActivity extends Activity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_and_a);

        ImageView backToMore_q = (ImageView) findViewById(R.id.imageView_q_and_a_backButton);
        backToMore_q.setOnClickListener(this);

        TextView txt = (TextView) findViewById(R.id.textView);
        txt.setText("클로즈업에게 바라는 점을 말씀해주세요.\n" +
                "클로즈업은 여러분과 함께 만들어가는 서비스입니다 :)\n" +
                "(이메일로 답변드려요)");

        Spinner spinner = (Spinner) findViewById(R.id.spinner_qanda);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imageView_q_and_a_backButton) {
            this.finish();
        }
    }
}
