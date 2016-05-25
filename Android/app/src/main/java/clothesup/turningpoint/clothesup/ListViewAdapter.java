package clothesup.turningpoint.clothesup;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Hanbyeol on 2016-05-13
 */
public class ListViewAdapter extends BaseAdapter {
    private Context context = null;
    private ArrayList<ContentDB> storeList = new ArrayList<ContentDB>();

    public ListViewAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getCount() {
        return storeList.size();
    }

    @Override
    public Object getItem(int position) {
        return storeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // layout for one store item in listView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        StoreFragment.ViewHolder holder;

        if(convertView == null) {
            holder = new StoreFragment.ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_store_item, parent, false);

            holder.storeRank = (TextView) convertView.findViewById(R.id.textView_storeRank);
            holder.storeImage = (ImageView) convertView.findViewById(R.id.imageView_storeImage);
            holder.storeName = (TextView) convertView.findViewById(R.id.textView_storeName);
            holder.storeNum = (TextView) convertView.findViewById(R.id.textView_storeNum);
            holder.storeHit = (TextView) convertView.findViewById(R.id.textView_storeHit);

            // using setTag to store instances of Store objects in View
            convertView.setTag(holder);
        }
        else {
            // calling getTag to return instances of Store objects
            holder = (StoreFragment.ViewHolder) convertView.getTag();
        }

        ContentDB contentDB = storeList.get(position);

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

        return convertView;
    }

    public void addItem(ContentDB newDB) {
        storeList.add(newDB);
    }

    public void removeItem(int position) {
        storeList.remove(position);
    }
}
