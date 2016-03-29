package clothesup.turningpoint.clothesup;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import uk.co.senab.photoview.PhotoViewAttacher;


public class MapFragment extends Fragment implements View.OnClickListener{
    ImageView marker;
    ImageView pinchZoom;
    int positionX = 0;
    int positionY = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        pinchZoom = (ImageView) view.findViewById(R.id.imageview_map_pinch);
        marker = (ImageView) view.findViewById(R.id.imageview_marker);
        marker.setOnClickListener(this);
        Drawable bitmap = getResources().getDrawable(R.drawable.wallpaper);
        pinchZoom.setImageDrawable(bitmap);
        PhotoViewAttacher mAttacher = new PhotoViewAttacher(pinchZoom);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.imageview_marker :
                ViewGroup.MarginLayoutParams params=(ViewGroup.MarginLayoutParams) marker.getLayoutParams();
                positionX += 10;
                positionY += 10;
                params.leftMargin = positionX;
                params.topMargin = positionY;
                marker.setLayoutParams(params);
                /*
                Matrix m = this.getMatrix();
                marker.setScaleType(ImageView.ScaleType.MATRIX);
                marker.setImageMatrix(m);
                */
                break;
        }
    }

    private Matrix getMatrix(){
        Matrix m = new Matrix();
        positionY += 10;
        if(positionY > 70)   positionY = -30;
        m.postTranslate(0, positionY);
        return m;
    }

}
