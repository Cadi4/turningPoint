package clothesup.turningpoint.clothesup.store;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import clothesup.turningpoint.clothesup.R;
import clothesup.turningpoint.clothesup.data.ContentDB;

/**
 * Created by Hanbyeol on 2016-05-13
 */
public class StoreListViewAdapter extends BaseAdapter {
    private Context context = null;
    private List<ContentDB> contentList;

    public StoreListViewAdapter(Context context, List<ContentDB> contentList) {
        super();
        this.context = context;
        this.contentList = contentList;
    }

    @Override
    public int getCount() {
        return contentList.size();
    }

    @Override
    public Object getItem(int position) {
        return contentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_store_item, parent, false);
        }
        setTextViews(position, convertView);
        setImage(position, convertView);

        return convertView;
    }

    private void setTextViews(int position, View row) {
        TextView storeRank = (TextView) row.findViewById(R.id.textView_storeRank);
        TextView storeName = (TextView) row.findViewById(R.id.textView_storeName);
        TextView storeId = (TextView) row.findViewById(R.id.textView_storeId);
        TextView storeCountN = (TextView) row.findViewById(R.id.textView_storeCountN);

        storeRank.setText(position + 1 + "");
        storeName.setText(contentList.get(position).getName().get(0).toString());
        storeId.setText(contentList.get(position).getId().toString());
        storeCountN.setText("조회수 " + contentList.get(position).getInfo().getCountN());
    }

    private void setImage(int position, View row) {
        ImageView storeImage = (ImageView) row.findViewById(R.id.imageView_storeImage);

        Picasso.with(context).load("http://52.196.54.163:1337/" + contentList.get(position).getInfo().getImage().toString())
                .resize(50, 50).into(storeImage);
        Log.i("Picasso", " completed: " + contentList.get(position).getInfo().getImage().toString());
    }
}
