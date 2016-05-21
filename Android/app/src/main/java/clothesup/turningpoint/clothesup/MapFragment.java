package clothesup.turningpoint.clothesup;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.inputmethod.*;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.graphics.RectF;
import android.widget.TextView;

import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;
import uk.co.senab.photoview.PhotoViewAttacher.OnMatrixChangedListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnScaleChangeListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnSingleFlingListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnViewTapListener;

/**
 * Created by bro.Jo on 2016-03-21.
 */
public class MapFragment extends Fragment implements View.OnClickListener {
    View view;                                // for fragment_map
    private PhotoViewAttacher mAttacher;    // photoView attacher
    private ImageView pinchZoom;            // photoView for map
    private EditText searchBar;             // what is wanted to search
    private ImageView search, back;    // search button, back button
    private float imgSizeX = 0, imgSizeY = 0;   // map size
    private MapMarker startPoint;   private MapMarker destPoint;
    private ImageView startMarker;  private ImageView destMarker;
    boolean startOrDest = false;            // when false, start marker. when true, destination marker

    private TextView mCurrMatrixTv;        // test  // temp Tv for matrix
    private TextView mCurrScaleTv;         // test  // temp Tv for Scale

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        view = inflater.inflate(R.layout.fragment_map, container, false);
        initPhotoViewPinchZoom();   // initialize photoView
        getImgSize();               // get size of map
        initSearch();               // initialize views related with search
        initMarker();               // hide markers which indicate starting point and destination point
        return view;
    }

    /**
     * getImgSize() : when application is started, get size of map
     * initPhotoViewPinchZoom() : initialize photoView
     * initSearch() : initialize views related with search,
     *                and set onClickListener + onKeyListener
     * initMarker() : hide markers which indicate starting point and destination point
     */
    private void getImgSize() {
        imgSizeX = 1080; //mAttacher.getDisplayRect().right - mAttacher.getDisplayRect().left;
        imgSizeY = 888.783f;// - 161.09998f); //mAttacher.getDisplayRect().bottom - mAttacher.getDisplayRect().top;
    }
    private void initPhotoViewPinchZoom() {
        pinchZoom = (ImageView) view.findViewById(R.id.imageview_map_pinch);
        Drawable bitmap = getResources().getDrawable(R.drawable.wallpaper);
        pinchZoom.setImageDrawable(bitmap);
        mAttacher = new PhotoViewAttacher(pinchZoom);
        mAttacher.setMaximumScale((float) 4.2);
        mAttacher.setMediumScale((float) 2.6);
        //Matrix //for test
        mCurrMatrixTv = (TextView) view.findViewById(R.id.text_rectf);
        mAttacher.setOnMatrixChangeListener(new MatrixChangeListener());
        //Scale //for test
        mCurrScaleTv = (TextView) view.findViewById(R.id.text_scale);
        mAttacher.setOnScaleChangeListener(new ScaleChangeListener());
        //to get position of stores //for test
        mAttacher.setOnPhotoTapListener(new PhotoTapListener());
    }
    private void initSearch() {
        search = (ImageView) view.findViewById(R.id.imageview_search_button);
        search.setOnClickListener(this);
        back = (ImageView) view.findViewById(R.id.imageview_back_button);
        back.setOnClickListener(this);
        searchBar = (EditText) view.findViewById(R.id.editText_map_search);
        searchBar.setOnKeyListener(new EditMessageOnKeyListener());
    }
    private void initMarker() {
        startMarker = (ImageView) view.findViewById(R.id.imageview_starting_marker);
        destMarker = (ImageView) view.findViewById(R.id.imageview_destination_marker);
        startPoint = new MapMarker(view, -100.0f, -100.0f);
        destPoint = new MapMarker(view, -100.0f, -100.0f);
        startPoint.updateMarker(mAttacher.getDisplayRect(), mAttacher.getScale(), startMarker);
        destPoint.updateMarker(mAttacher.getDisplayRect(), mAttacher.getScale(), destMarker);
    }

    /**
     * onClick() : on click listener
     * EditMessageOnKeyListener : listener for keyboard
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageview_search_button:  // if search button clicked
                Log.i("setStorePosition start", "onclick");
                (new setStorePosition()).execute();
                break;
            case R.id.imageview_back_button:    // if back button clicked
                Log.i("back button", "clicked");
                startOrDest = false;
                mAttacher.setScale(1.0f, true);
                initMarker();
                searchBar.setHint("내위치 ex) C901 or 토니모리");
                break;
        }
    }
    private class EditMessageOnKeyListener implements View.OnKeyListener {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                Log.i("setStorePosition start", "enter key");
                (new setStorePosition()).execute();
            }
            return false;
        }
    }

    /**
     * popup() : when map is clicked, show popup
     * getPopupData : get data from server,
     *                and determine whether start popup intent or not using that data.
     */
    private void popup(float clickedX, float clickedY) {
        Log.i("popup", "pop up method in MapFragment!!");
        float[] clickedXY = new float[2];
        clickedXY[0] = clickedX;
        clickedXY[1] = clickedY;
        Intent popup_intent = new Intent(this.getContext(), PopupActivity.class);
        popup_intent.putExtra("data for popup", clickedXY);
        (new getPopupData(clickedXY, popup_intent)).execute();  // start intent, but if there is no data, then don't start intent
    }
    private class getPopupData extends AsyncTask<Void, Void, Void> {
        private Intent popup_intent;
        private List<ContentDB> content;
        private float[] clickedXY = new float[2];

        public getPopupData(float[] clickedXY, Intent popup_intent) {
            this.clickedXY = clickedXY;
            this.popup_intent = popup_intent;
        }
        protected void onPreExecute() {
            ;
        }
        protected Void doInBackground(Void... params) {
            Proxy proxy = new Proxy();
            content = proxy.findServerStoreByLocation(clickedXY);
            if(content == null){
                Log.e("MapFragment : server connection fail", "Proxy fail e");
            }
            else if(content.isEmpty()) {
                Log.i("MapFragment : Async Proxy cannot find by location", String.valueOf(content));
            }
            return null;
        }
        protected void onPostExecute(Void avoid) {
            if(content != null &&!content.isEmpty() && content.size() == 1) {
                Log.e("MapFragment : Async Content : ", String.valueOf(content));
                Log.e("MapFragment : Async Content : ", String.valueOf(content.get(0)));
                startActivity(popup_intent);
            }
            else{
                Log.e("MapFragment : Async", "two element!! or empty");
            }
            Log.i("Async", "end");
        }
    }

    /**
     * setStorePosition : when search some store, this async class will operate.
     *                    this class is for searing something from server.
     *                    the input is text of searchBar,
     *                    after this class is operated, this class will call update method
     * update() : update marker
     *            then the map will zoom the position and the marker will move the position.
     */
    private class setStorePosition extends AsyncTask<Void, Void, Void> {
        String searchText;
        List<ContentDB> content;

        protected void onPreExecute() {
            searchText = String.valueOf(searchBar.getText());
        }
        protected Void doInBackground(Void... params) {
            Proxy proxy = new Proxy();
            content = proxy.findServerStoreByName(searchText.toLowerCase().replaceAll("\\s", ""));
            if(content == null){
                Log.e("server connection fail", "Proxy fail e");
            }
            else if(content.isEmpty()) {
                Log.i("Async Proxy cannot find by name", String.valueOf(content));
                content = proxy.findServerStoreById(searchText.toUpperCase().replaceAll("\\s", ""));
                if(content.isEmpty()) {
                    Log.i("Async Proxy cannot find by id", String.valueOf(content));
                }
            }
            return null;
        }
        protected void onPostExecute(Void avoid) {
            if(content != null &&!content.isEmpty() && content.size() == 1) {
                Log.e("Async Content : ", String.valueOf(content));
                float x1, x2, y1, y2;
                x1 = content.get(0).location.getX1() / 1000f;   x2 = content.get(0).location.getX2() / 1000f;
                y1 = content.get(0).location.getY1() / 1000f;   y2 = content.get(0).location.getY2() / 1000f;
                update((x2-x1) / 2 + x1, (y2-y1) / 2 + y1);
                searchBar.setText(null);
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(view.getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(searchBar.getWindowToken(), 0); // hide soft keyboard
            }
            else{
                Log.e("Async", "two element!! or empty");
            }
            Log.i("Async", "end");
        }
    }
    private void update(float x, float y) {
        Log.i("startordest1", String.valueOf(startOrDest));
        if(!startOrDest){       // start marker
            startOrDest = true;
            startPoint.x = x;  startPoint.y = y;
            startPoint.updateMarker(mAttacher.getDisplayRect(), mAttacher.getScale(), startMarker);
            mAttacher.setScale(1.0f, false);
            mAttacher.setScale(4.0f, startPoint.x*imgSizeX, startPoint.y*(imgSizeY+800), true);
            Log.i("startordest2", String.valueOf(startOrDest));
            searchBar.setHint("목표위치 ex) C901 or 토니모리");
        }
        else{                   // dest marker
            destPoint.x = x;  destPoint.y = y;
            destPoint.updateMarker(mAttacher.getDisplayRect(), mAttacher.getScale(), destMarker);
            mAttacher.setScale(1.0f, false);
            mAttacher.setScale(4.0f, destPoint.x*imgSizeX, destPoint.y*(imgSizeY+800), true);
            Log.i("startordest3", String.valueOf(startOrDest));
        }
    }


    /**
     * listener for re-positioning of map
     */
    private class MatrixChangeListener implements OnMatrixChangedListener {
        @Override
        public void onMatrixChanged(RectF rect) {
            //mCurrMatrixTv.setText(rect.toString());
            startPoint.updateMarker(mAttacher.getDisplayRect(), mAttacher.getScale(), startMarker);
            if(startOrDest)
                destPoint.updateMarker(mAttacher.getDisplayRect(), mAttacher.getScale(), destMarker);
        }
    }

    /**
     * listener for re-scaling of map
     */
    private class ScaleChangeListener implements OnScaleChangeListener {
        @Override
        public void onScaleChange(float scaleFactor, float focusX, float focusY) {
            //mCurrScaleTv.setText(Math.round(mAttacher.getScale()*1000)/1000.0 + ", ");
        }
    }

    //for test
    private class PhotoTapListener implements OnPhotoTapListener {
        @Override
        public void onPhotoTap(View view, float x, float y) {
            mCurrMatrixTv.setText(Math.round(x*1000)/1000.0 + ", " + Math.round(y*1000)/1000.0);
            popup(x, y);
        }

        @Override
        public void onOutsidePhotoTap() {
            mCurrMatrixTv.setText("outside");
        }
    }
}
