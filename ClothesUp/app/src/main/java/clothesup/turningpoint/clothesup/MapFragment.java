package clothesup.turningpoint.clothesup;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import uk.co.senab.photoview.PhotoViewAttacher;


public class MapFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ImageView pinchZoom = (ImageView) view.findViewById(R.id.imageview_map_pinch);
        Drawable bitmap = getResources().getDrawable(R.drawable.wallpaper);
        pinchZoom.setImageDrawable(bitmap);
        PhotoViewAttacher mAttacher = new PhotoViewAttacher(pinchZoom);

        return view;
    }
}
