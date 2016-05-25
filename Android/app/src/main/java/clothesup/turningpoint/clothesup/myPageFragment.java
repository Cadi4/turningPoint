package clothesup.turningpoint.clothesup;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by bro.Jo on 2016-03-21
 */
public class myPageFragment extends Fragment implements View.OnTouchListener {
    View view;
    ImageView scrollHelper;
    StickyListHeadersListView stickyList;
    boolean isScrollHelper = false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        view = inflater.inflate(R.layout.fragment_my_page, container, false);
        initStickyList();
        initScrollHelper();
        return view;
    }

    private void initStickyList() {
        stickyList= (StickyListHeadersListView) view.findViewById(R.id.sticky_mypage_list_view);
        stickyList.setFastScrollEnabled(true);
        MyStickyListAdapter adapter = new MyStickyListAdapter(getActivity());
        stickyList.requestFocusFromTouch();
        stickyList.setAdapter(adapter);
    }
    private void initScrollHelper() {
        scrollHelper = (ImageView)view.findViewById(R.id.scroll_helper);
        scrollHelper.setOnTouchListener(this);
        scrollHelper.setAlpha(10);
    }

    /**
     * onTouchListener is used when scrollHelper is touched
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if(action == MotionEvent.ACTION_MOVE && -50 < event.getX() && event.getX() < 100){
            //Log.v("onTouch scroll_helper", " X : " + event.getX() + " ,  Y : " + event.getY());
            switch ((int)(event.getY() - 35) / 100){
                case 0: stickyList.setSelection(3); break;
                case 1: stickyList.setSelection(5); break;
                case 2: stickyList.setSelection(10); break;
                case 3: stickyList.setSelection(12); break;
                case 4: stickyList.setSelection(13); break;
            }
        }
        controlScrollHelper();
        return true;
    }

    /**
     * MyStickyListAdapter is adapter for list about my information
     */
    public class MyStickyListAdapter extends BaseAdapter implements StickyListHeadersAdapter{
        private String[] countries;
        private LayoutInflater inflater;

        public MyStickyListAdapter(Context context) {
            inflater = LayoutInflater.from(context);
            countries = context.getResources().getStringArray(R.array.countries);
        }

        @Override
        public int getCount() {
            //Log.v("getCount", String.valueOf(countries.length));
            return countries.length;
        }

        @Override
        public Object getItem(int position) {
            Log.v("getItem", countries[position] + "position : " + position);
            return countries[position];
        }

        @Override
        public long getItemId(int position) {
            Log.v("getItemId", String.valueOf(position));
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            controlScrollHelper();
            ViewHolder holder = new ViewHolder();
            if (position < 5 || 9 <= position && position < 12) {
                convertView = inflater.inflate(R.layout.listview_store_item, parent, false);
                holder.text = (TextView) convertView.findViewById(R.id.textView_storeName);
                holder.rank = (TextView) convertView.findViewById(R.id.textView_storeRank);
                holder.text.setText(countries[position]);
                holder.rank.setText(String.valueOf(position));
            }
            else {
                convertView = inflater.inflate(R.layout.my_page_list_item, parent, false);
                holder.text = (TextView) convertView.findViewById(R.id.textview_my_page_list);
                holder.text.setText(countries[position]);
            }
            //Log.v("getView", String.valueOf(position) + "  " + countries[position]);
            return convertView;
        }

        @Override
        public View getHeaderView(int position, View convertView, ViewGroup parent) {
            HeaderViewHolder holder;
            if (convertView == null) {
                holder = new HeaderViewHolder();
                convertView = inflater.inflate(R.layout.my_page_header, parent, false);
                holder.text = (TextView) convertView.findViewById(R.id.textview_header_list);
                convertView.setTag(holder);
            } else {
                holder = (HeaderViewHolder) convertView.getTag();
            }
            //set my_page_header text as first char in name
            String headerText = "" + countries[position].subSequence(0, 1).charAt(0);
            //Log.i("my_page_header text", headerText + " ::: " + countries[position] + " ::: " + position);
            holder.text.setText(headerText);
            //Log.v("getHeaderView", convertView.toString());
            return convertView;
        }

        @Override
        public long getHeaderId(int position) {
            //return the first character of the country as ID because this is what headers are based upon
            /*
            if(position<4){
                return 0;
            }else if(position < 6){
                return 4;
            }else {
                return 6;
            }*/
            //Log.v("getHeaderId", String.valueOf(countries[position].subSequence(0,1).charAt(0)));
            return countries[position].subSequence(0, 1).charAt(0);
        }

        class HeaderViewHolder {
            TextView text;
        }

        class ViewHolder {
            TextView text, rank;
        }
    }

    /**
     * controlScrollHelper and VanishScrollHelper are for making disappear or appear ScrollHelper
     */
    private void controlScrollHelper() {
        if(!isScrollHelper) {
            // show scroll helper
            isScrollHelper = true;
            scrollHelper.setAlpha(100);
            // dim scroll helper
            Handler handler = new Handler();
            handler.postDelayed(new VanishScrollHelper(), 1200);
        }
    }
    private class VanishScrollHelper implements Runnable{
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        synchronized (this) {
                            scrollHelper.setAlpha(10);
                            //Log.e("thread run?", "yes" + i + " / " + (int) i);
                        }
                        isScrollHelper = false;
                    } catch (Exception e) {
                        Log.e("thread run?", "error");
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
