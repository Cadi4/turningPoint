package clothesup.turningpoint.clothesup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by bro.Jo on 2016-03-21.
 */
public class moreFragment extends Fragment implements View.OnClickListener{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        TextView infoLogin = (TextView) view.findViewById(R.id.myId_info_button);
        infoLogin.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.myId_info_button :
                Log.d("onClick!!", "hi");
                Intent intent = new Intent(this.getContext(), AfterLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.notice_button :

        }
    }
}
