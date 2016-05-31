package clothesup.turningpoint.clothesup.more;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import clothesup.turningpoint.clothesup.R;

/**
 * Created by bro.Jo on 2016-03-21
 */
public class moreFragment extends Fragment implements View.OnClickListener{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        initView(view);     // get Views ID and set onClickListener
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.myinfo_button :
            case R.id.myinfo_layout :
            case R.id.myinfo_arrow_imageView :
            case R.id.myinfo_imageView :
                Log.d("onClick!!", "start myInfo Intent");
                Intent intentMyinfo = new Intent(this.getContext(), AfterLoginActivity.class);
                startActivity(intentMyinfo);
                break;

            case R.id.notice_button :
            case R.id.notice_layout :
            case R.id.notice_arrow_imageView :
            case R.id.notice_imageView :
                Log.d("onClick!!", "start notice Intent");
                Intent intentNotice = new Intent(this.getContext(), NoticeActivity.class);
                startActivity(intentNotice);
                break;

            case R.id.guide_button :
            case R.id.guide_layout :
            case R.id.guide_arrow_imageView :
            case R.id.guide_imageView :
                Log.d("onClick!!", "start guide Intent");
                Intent intentGuide = new Intent(this.getContext(), GuideActivity.class);
                startActivity(intentGuide);
                break;

            case R.id.q_and_a_button :
            case R.id.q_and_a_layout :
            case R.id.q_and_a_arrow_imageView :
            case R.id.q_and_a_imageView :
                Log.d("onClick!!", "start Q&A Intent");
                Intent intentQandA = new Intent(this.getContext(), QandAActivity.class);
                startActivity(intentQandA);
                break;
        }
    }

    /**
     * get Views ID and set on click listener
     * @param view is inflated from R.layout.fragment_more
     */
    private void initView(View view){
        TextView infoLogin = (TextView) view.findViewById(R.id.myinfo_button);
        infoLogin.setOnClickListener(this);
        RelativeLayout infoLayout = (RelativeLayout) view.findViewById(R.id.myinfo_layout);
        infoLayout.setOnClickListener(this);
        ImageView infoArrow = (ImageView) view.findViewById(R.id.myinfo_arrow_imageView);
        infoArrow.setOnClickListener(this);
        ImageView infoImage = (ImageView) view.findViewById(R.id.myinfo_imageView);
        infoImage.setOnClickListener(this);

        TextView notice = (TextView) view.findViewById(R.id.notice_button);
        notice.setOnClickListener(this);
        RelativeLayout noticeLayout = (RelativeLayout) view.findViewById(R.id.notice_layout);
        noticeLayout.setOnClickListener(this);
        ImageView noticeArrow = (ImageView) view.findViewById(R.id.notice_arrow_imageView);
        noticeArrow.setOnClickListener(this);
        ImageView noticeImage = (ImageView) view.findViewById(R.id.notice_imageView);
        noticeImage.setOnClickListener(this);

        TextView guide = (TextView) view.findViewById(R.id.guide_button);
        guide.setOnClickListener(this);
        RelativeLayout guideLayout = (RelativeLayout) view.findViewById(R.id.guide_layout);
        guideLayout.setOnClickListener(this);
        ImageView guideArrow = (ImageView) view.findViewById(R.id.guide_arrow_imageView);
        guideArrow.setOnClickListener(this);
        ImageView guideImage = (ImageView) view.findViewById(R.id.guide_imageView);
        guideImage.setOnClickListener(this);

        TextView qAndA = (TextView) view.findViewById(R.id.q_and_a_button);
        qAndA.setOnClickListener(this);
        RelativeLayout qAndALayout = (RelativeLayout) view.findViewById(R.id.q_and_a_layout);
        qAndALayout.setOnClickListener(this);
        ImageView qAndAArrow = (ImageView) view.findViewById(R.id.q_and_a_arrow_imageView);
        qAndAArrow.setOnClickListener(this);
        ImageView qAndAImage = (ImageView) view.findViewById(R.id.q_and_a_imageView);
        infoImage.setOnClickListener(this);
    }
}
