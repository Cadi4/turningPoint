package clothesup.turningpoint.clothesup;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.graphics.RectF;
import android.widget.TextView;

import uk.co.senab.photoview.PhotoViewAttacher;
import uk.co.senab.photoview.PhotoViewAttacher.OnMatrixChangedListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnScaleChangeListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnSingleFlingListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnViewTapListener;

/**
 * Created by bro.Jo on 2016-03-21.
 */
public class MapFragment extends Fragment implements View.OnClickListener{
    View view;                                // for fragment_map
    private PhotoViewAttacher mAttacher;    // photoView attacher
    private ImageView pinchZoom;            // photoView for map
    private TextView mCurrMatrixTv;        // temp Tv for matrix
    private TextView mCurrScaleTv;         // temp Tv for Scale
    private ImageView marker;               // marker to represent start point or destination point
    private float imgSizeX=0, imgSizeY=0;   // map size
    private float scale=1;                    // map scale
    private float curL=0, curT=0, curR=0, curB=0;   // map display rect
    private float positionX=370, positionY=350;     // position of marker

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        view = inflater.inflate(R.layout.fragment_map, container, false);
        photoViewPinchZoomInit();   // initialize photoView
        getImgSize();               // get size of map
        return view;
    }

    /**
     * get size of map
     */
    private void getImgSize(){
        imgSizeX = mAttacher.getDisplayRect().right - mAttacher.getDisplayRect().left;
        imgSizeY = mAttacher.getDisplayRect().bottom - mAttacher.getDisplayRect().top;
    }

    /**
     * updating the position of marker
     * this method operated by MatrixChangeListener
     * @param r is mAttacher.getDisplayRect()
     */
    private void updateMarker(RectF r) {
        scale = mAttacher.getScale();
        curL = r.left;  curT = r.top;   curR = r.right; curB = r.bottom;
        marker = (ImageView) view.findViewById(R.id.imageview_marker);

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) marker.getLayoutParams();
        params.leftMargin = (int) (scale * positionX + curL);
        params.topMargin = (int) (scale * positionY + curT);
        marker.setLayoutParams(params);

        mCurrScaleTv.setText(scale + ", " + positionX + ", " + curL);
    }

    /**
     * initialize photoView
     */
    private void photoViewPinchZoomInit() {
        pinchZoom = (ImageView) view.findViewById(R.id.imageview_map_pinch);
        Drawable bitmap = getResources().getDrawable(R.drawable.wallpaper);
        pinchZoom.setImageDrawable(bitmap);
        mAttacher = new PhotoViewAttacher(pinchZoom);
        //Matrix
        mCurrMatrixTv = (TextView) view.findViewById(R.id.text_rectf);
        mAttacher.setOnMatrixChangeListener(new MatrixChangeListener());
        //Scale
        mCurrScaleTv =  (TextView) view.findViewById(R.id.text_scale);
        mAttacher.setOnScaleChangeListener(new ScaleChangeListener());
    }

    /**
     * on click listener
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imageview_marker :
                Log.i("DisplayMatrix()", mAttacher.getDisplayMatrix().toShortString());
                Log.i("MaximumScale()", mAttacher.getMaximumScale() + "");
                Log.i("Scale()", mAttacher.getScale() + "");
                Log.i("DisplayRect()", mAttacher.getDisplayRect() + "");
                break;
        }
    }

    /**
     * listener for re-positioning of map
     */
    private class MatrixChangeListener implements OnMatrixChangedListener {
        @Override
        public void onMatrixChanged(RectF rect) {
            mCurrMatrixTv.setText(rect.toString());
            updateMarker(rect);
        }
    }

    /**
     * listener for re-scaling of map
     */
    private class ScaleChangeListener implements OnScaleChangeListener {
        @Override
        public void onScaleChange(float scaleFactor, float focusX, float focusY) {
            //mCurrScaleTv.setText(Math.round(mAttacher.getScale()*1000)/1000.0 + ", "); // + scaleFactor + ", " + focusX + ", " + focusY);
        }
    }
}
