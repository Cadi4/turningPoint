package clothesup.turningpoint.clothesup;

import android.graphics.drawable.Drawable;

/**
 * Created by Hanbyeol on 2016-05-13.
 */
public class Store {
    // Object of storeData in ListView
    private String storeRank;   // rank
    private Drawable storeIcon; // icon
    private String storeName;   // name
    private String storeNum;    // id
    private String storeHit;    // hit

    public Store(String rank, Drawable icon, String name, String num, String hit) {
        this.storeRank = rank;
        this.storeIcon = icon;
        this.storeName = name;
        this.storeNum = num;
        this.storeHit = hit;
    }
}
