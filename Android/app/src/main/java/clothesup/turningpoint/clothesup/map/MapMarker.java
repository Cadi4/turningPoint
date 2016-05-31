package clothesup.turningpoint.clothesup.map;

import android.graphics.RectF;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.View;
import android.widget.TextView;

import clothesup.turningpoint.clothesup.R;

/**
 * Created by bro.Jo on 2016-03-30
 */
public class MapMarker {
    View view;   float x, y;
    private float scale;                            // map scale
    private float curL=0, curT=0, curR=0, curB=0;   // map display rect
    private ImageView marker;                       // marker ImageView
    private float imgSizeX=0, imgSizeY=0;           // map size

    /**
     * constructor
     * @param view is view of MapFragment
     * @param x is -100, in order to hide it.
     * @param y is -100 too.
     */
    public MapMarker(View view, float x, float y) {
        this.view = view;
        this.x = x;
        this.y = y;
        getImgSize();
    }

    /**
     * updating the position of marker
     * this method operated by MatrixChangeListener
     * @param r is mAttacher.getDisplayRect()
     * @param scale is scale of the photoView
     * @param markerId is ID of marker you want to change
     */
    public void updateMarker(RectF r, float scale, ImageView markerId) {
        curL = r.left;  curT = r.top;   curR = r.right; curB = r.bottom;
        Log.e("curB", String.valueOf(r));
        marker = (ImageView) markerId;
        ViewGroup.MarginLayoutParams paramsS = (ViewGroup.MarginLayoutParams) marker.getLayoutParams();
        paramsS.leftMargin = (int) (scale * x*imgSizeX + curL);
        paramsS.topMargin = (int) (scale * y*imgSizeY + curT);
        marker.setLayoutParams(paramsS);
        TextView mCurrScaleTv =  (TextView) view.findViewById(R.id.text_scale);
        mCurrScaleTv.setText(Math.round(scale * 1000) / 1000.0 + ", " + Math.round((curR - curL) * 1000) / 1000.0 + ", " + Math.round((curB - curT) * 1000) / 1000.0); //for test
    }

    /**
     * get size of map
     */
    private void getImgSize(){
        imgSizeX = 1080;        //mAttacher.getDisplayRect().right - mAttacher.getDisplayRect().left;
        imgSizeY = 888.783f;    //mAttacher.getDisplayRect().bottom - mAttacher.getDisplayRect().top;
    }
}
