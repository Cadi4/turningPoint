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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StoreFragment extends Fragment {
    private ListView mListView = null;
    private ListViewAdapter mAdapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_store, container, false);
        String [] rank = {"별점수", "조회수", "후기수"};
        Spinner spinner_rank = (Spinner) view.findViewById(R.id.spinner_ranking);

        ArrayAdapter<String> RKadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, rank);
        RKadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_rank.setAdapter(RKadapter);
        setRetainInstance(true);    // Retain fragment instance across configuration changes

        mListView = (ListView) view.findViewById(R.id.listView_storeItemList);

        mAdapter = new ListViewAdapter(this.getContext());
        mListView.setAdapter(mAdapter);
/*
        mAdapter.addItem("1", ContextCompat.getDrawable(getContext(), R.drawable.store), "About Some", "B-6", "조회수 800");
        mAdapter.addItem("2", ContextCompat.getDrawable(getContext(), R.drawable.store), "강남스타일", "B-11", "조회수 700");
        mAdapter.addItem("3", ContextCompat.getDrawable(getContext(), R.drawable.store), "A-round101", "B-48", "조회수 600");
        mAdapter.addItem("4", ContextCompat.getDrawable(getContext(), R.drawable.store), "Mango Steen", "D-6", "조회수 500");
        mAdapter.addItem("5", ContextCompat.getDrawable(getContext(), R.drawable.store), "HANABEE", "E-1", "조회수 400");
        mAdapter.addItem("6", ContextCompat.getDrawable(getContext(), R.drawable.store), "KATE", "F-10", "조회수 300");
        mAdapter.addItem("7", ContextCompat.getDrawable(getContext(), R.drawable.store), "Monologue", "남-5", "조회수 200");
        mAdapter.addItem("8", ContextCompat.getDrawable(getContext(), R.drawable.store), "빨간고양이", "북-7", "조회수 100");
*/
        return view;
    }

    public static class ViewHolder {
        public TextView storeRank;
        public ImageView storeImage;
        public TextView storeName;
        public TextView storeNum;
        public TextView storeHit;
    }
}