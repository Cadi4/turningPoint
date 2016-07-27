package clothesup.turningpoint.clothesup.store;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import clothesup.turningpoint.clothesup.R;
import clothesup.turningpoint.clothesup.data.ContentDB;
import clothesup.turningpoint.clothesup.data.ReviewDB;
import clothesup.turningpoint.clothesup.network.ApplicationController;
import clothesup.turningpoint.clothesup.network.MappingService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hanbyeol on 2016-06-19.
 */
public class DetailStoreActivity extends Activity implements RatingBar.OnRatingBarChangeListener {
    private MappingService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_store);

        ApplicationController application = new ApplicationController();
        service = application.buildNetworkService();

        setDetailNetwork();
    }

    public void setDetailNetwork() {
        Call<List<ContentDB>> thumbnailCall_content = service.getServerStores();
        thumbnailCall_content.enqueue(new Callback<List<ContentDB>>() {

            @Override
            public void onResponse(Call<List<ContentDB>> call, Response<List<ContentDB>> response) {
                if (response.isSuccessful()) {
                    List<ContentDB> contentDB = response.body();
                    setDetailView(contentDB);
                } else {
                    Log.i("DetailStoreActivity", "응답코드: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<ContentDB>> call, Throwable t) {
                Log.e("DetailStoreActivity", "서버 onFailure: " + t.getMessage());
            }
        });

        Call<List<ReviewDB>> thumbnailCall_review = service.getServerReviews();
        thumbnailCall_review.enqueue(new Callback<List<ReviewDB>>() {

            @Override
            public void onResponse(Call<List<ReviewDB>> call, Response<List<ReviewDB>> response) {
                if (response.isSuccessful()) {
                    List<ReviewDB> reviewDB = response.body();
                    setStarChart(reviewDB);
                } else {
                    int statusCode = response.code();
                    Log.i("DetailStoreActivity", "응답코드: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<List<ReviewDB>> call, Throwable t) {
                Log.e("DetailStoreActivity", "서버 onFailure: " + t.getMessage());
            }
        });
    }

    public void setDetailView(final List<ContentDB> contentDB) {
        ImageView backToStoreList = (ImageView) findViewById(R.id.imageView_detail_backButton);
        ImageView storeImage = (ImageView) findViewById(R.id.imageView_detail_storeImage);
        TextView storeId = (TextView) findViewById(R.id.textView_detail_storeId);
        TextView storeName = (TextView) findViewById(R.id.textView_detail_storeName);
        RatingBar storeRatingBar = (RatingBar) findViewById(R.id.ratingBar_detail);
        ImageView reviewButton = (ImageView) findViewById(R.id.imageView_detail_review);

        backToStoreList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Intent detailIntent = getIntent();

        Picasso.with(this).load("http://52.196.54.163:1337/" + detailIntent.getStringExtra("image"))
                .resize(50, 50).into(storeImage);
        storeId.setText(detailIntent.getStringExtra("id"));
        storeName.setText(detailIntent.getStringExtra("name"));
        storeRatingBar.setOnRatingBarChangeListener(this);

        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ReviewActivity.class);

                intent.putExtra("store_image", detailIntent.getStringExtra("image"));
                intent.putExtra("store_id", detailIntent.getStringExtra("id"));
                intent.putExtra("store_name", detailIntent.getStringExtra("name"));

                startActivity(intent);
            }
        });
    }

    public void setStarChart(List<ReviewDB> reviewDB) {
        HorizontalBarChart starChart = (HorizontalBarChart) findViewById(R.id.detail_starChart);
        TextView storeGrade = (TextView) findViewById(R.id.textView_detail_storeGrade);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(5, 0));
        entries.add(new BarEntry(15, 1));
        entries.add(new BarEntry(30, 2));
        entries.add(new BarEntry(20, 3));
        entries.add(new BarEntry(35, 4));
        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setColors(new int[]{R.color.starChart, R.color.starChart, R.color.starChart
                , R.color.starChart, R.color.starAccent}, this);
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("★");
        labels.add("★★");
        labels.add("★★★");
        labels.add("★★★★");
        labels.add("★★★★★");

        BarData data = new BarData(labels, dataSet);
        starChart.setData(data);

        starChart.setDescription("");
        XAxis xAxis = starChart.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(getResources().getColor(R.color.starAccent));
        YAxis left = starChart.getAxisLeft();
        left.setDrawAxisLine(false);
        left.setDrawGridLines(false);
        left.setDrawLabels(false);
        YAxis right = starChart.getAxisRight();
        right.setDrawAxisLine(false);
        right.setDrawGridLines(false);
        right.setDrawLabels(false);
        starChart.getLegend().setEnabled(false);

        storeGrade.setText(String.valueOf(calculate(entries)));
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        float numStars = ratingBar.getRating();
        Log.i("onRatingChanged: ", "success!!");
        Toast.makeText(this, String.valueOf(numStars), Toast.LENGTH_SHORT).show();
    }

    public float calculate(ArrayList<BarEntry> entries) {
        float total_people = (float) 0.0;
        float sum = (float) 0.0;

        for(int i=0; i<entries.size(); i++) {
            total_people += entries.get(i).getVal();
            sum += entries.get(i).getVal()*(i+1.0);
        }

        return Math.round((sum/total_people) * 10f) / 10f;
    }
}