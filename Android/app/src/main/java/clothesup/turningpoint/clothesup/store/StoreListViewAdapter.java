package clothesup.turningpoint.clothesup.store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import clothesup.turningpoint.clothesup.R;
import clothesup.turningpoint.clothesup.data.ContentDB;
import clothesup.turningpoint.clothesup.store.StoreFragment;

/**
 * Created by Hanbyeol on 2016-05-13
 */
public class StoreListViewAdapter extends BaseAdapter {
    private Context context = null;
    private List<ContentDB> contentList;
    //private ArrayList<ContentDB> storeList = new ArrayList<ContentDB>();

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

    // layout for one store item in listView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StoreFragment.ViewHolder holder;
        View row = convertView;

        if(row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.listview_store_item, parent, false);
            // using setTag to store instances of Store objects in View
            //convertView.setTag(holder);
        }
        setTextViews(position, row);
        setImage(position, row);

  /*      else {
            // calling getTag to return instances of Store objects
            holder = (StoreFragment.ViewHolder) convertView.getTag();
        }

        ContentDB contentDB = contentList.get(position);

        if(contentDB.getInfo().getImage() != null) {
            holder.storeImage.setVisibility(View.VISIBLE);
            holder.storeImage.setImageResource(R.drawable.store);
//            holder.storeImage.setImageBitmap(new ImageLoader().getBitmapImg("image/store.png"));
        }
        else {
            holder.storeImage.setVisibility(View.GONE);
        }

        //holder.storeRank.setText(contentDB.getInfo().getRank());
        String tmp = " ";
        for(int i=0; i<contentDB.getName().size(); i++) {
            tmp = tmp + contentDB.getName().get(i);
        }
        holder.storeName.setText(tmp);
        holder.storeNum.setText(contentDB.getId());
        //holder.storeHit.setText(contentDB.getInfo().getHit());
*/
        return row;
    }

    private void setTextViews(int position, View row) {
        TextView storeRank = (TextView) row.findViewById(R.id.textView_storeRank);
        TextView storeName = (TextView) row.findViewById(R.id.textView_storeName);
        TextView storeNum = (TextView) row.findViewById(R.id.textView_storeNum);
        TextView storeHit = (TextView) row.findViewById(R.id.textView_storeHit);

        storeRank.setText(position + 1 + "");
        storeName.setText(contentList.get(position).getName().get(0).toString());
        storeNum.setText(contentList.get(position).getId().toString());
        storeHit.setText("조회수 " + contentList.get(position).getInfo().getCountN());
    }

    private void setImage(int position, View row) {
        ImageView storeImage = (ImageView) row.findViewById(R.id.imageView_storeImage);
        // add image??? ImageView.setimg??
    }

    public void addItem(ContentDB newDB) {
        contentList.add(newDB);
    }

    public void removeItem(int position) {
        contentList.remove(position);
    }
}
