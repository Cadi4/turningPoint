package clothesup.turningpoint.clothesup;

import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StoreFragment extends Fragment implements View.OnClickListener {
//    private ListView mListView;
//    private ListViewAdapter mAdapter;
    private TextView result;
    private Button btn_all;
    private NetworkService service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_store, container, false);
        String [] rank = {"별점수", "조회수", "후기수"};
        Spinner spinner_rank = (Spinner) view.findViewById(R.id.spinner_ranking);

        ArrayAdapter<String> RKadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, rank);
        RKadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_rank.setAdapter(RKadapter);
        setRetainInstance(true);    // Retain fragment instance across configuration changes

        result = (TextView) view.findViewById(R.id.txt_result);
        btn_all = (Button) view.findViewById(R.id.btn_all);

        ApplicationController application = new ApplicationController();
        application.buildNetworkService();
        service = application.getNetworkService();

        btn_all.setOnClickListener(this);

/*
        mListView = (ListView) view.findViewById(R.id.listView_storeItemList);

        mAdapter = new ListViewAdapter(this.getContext());
        mListView.setAdapter(mAdapter);
*/
        return view;
    }

    @Override
    public void onClick(View v) {
        Call<List<ContentDB>> thumbnailCall = service.getServerStores();
        thumbnailCall.enqueue(new Callback<List<ContentDB>>() {
            @Override
            public void onResponse(Call<List<ContentDB>> call, Response<List<ContentDB>> response) {
                if(response.isSuccessful()) {
                    List<ContentDB> content_temp = response.body();

                    String show_txt = "";
                    for(ContentDB contentDB : content_temp) {
                        show_txt += contentDB.getId();
                    }
                    result.setText(show_txt);
                } else {
                    int statusCode = response.code();
                    Log.i("MyTag", "응답코드:" + statusCode);
                }
            }

            @Override
            public void onFailure(Call<List<ContentDB>> call, Throwable t) {
                Log.i("MyTag", "서버 onFailure 에러내용:" + t.getMessage());
            }
        });
    }

    public static class ViewHolder {
        public TextView storeRank;
        public ImageView storeImage;
        public TextView storeName;
        public TextView storeNum;
        public TextView storeHit;
    }
}