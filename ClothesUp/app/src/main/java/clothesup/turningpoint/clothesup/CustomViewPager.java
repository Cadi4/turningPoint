package clothesup.turningpoint.clothesup;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by bro.jo on 2016-03-22.
 */
public class CustomViewPager extends ViewPager {
    public CustomViewPager(Context context)
    {
        super(context);
    }
    public CustomViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        try
        {
            return super.onInterceptTouchEvent(ev);
        }
        catch (IllegalArgumentException e)          {}
        catch (ArrayIndexOutOfBoundsException e)    {}
        return false;
    }
}
