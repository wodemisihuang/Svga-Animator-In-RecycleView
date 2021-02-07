package com.dazhou.blind.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestSVGAAdapter extends RecyclerView.Adapter<TestSVGAAdapter.GiftViewHolder> {

    private Context context;
    private Map<String, SVGAVideoEntity> svgaMap = new HashMap<>();
    private List<String> testData = new ArrayList<>();

    public TestSVGAAdapter(Context context) {
        this.context = context;
        initMap();
    }

    public void initMap() {
        testData.clear();
        for (int i = 0; i < 28; i++) {
            testData.add(i + "");
        }
    }

    @NonNull
    @Override
    public GiftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false);
        return new GiftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GiftViewHolder holder, int position) {
        String testString = testData.get(position);
        holder.svgaImageView.setClearsAfterDetached(false);
        SVGAVideoEntity entity = svgaMap.get(testString);
        if (entity == null) {
            SVGAParser parser = new SVGAParser(holder.svgaImageView.getContext());
            try {
                parser.decodeFromAssets("weekly_wish.svga", new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(@NotNull SVGAVideoEntity svgaVideoEntity) {
                        svgaMap.put(testString, svgaVideoEntity);
                        holder.svgaImageView.setVideoItem(svgaVideoEntity);
                        holder.svgaImageView.startAnimation();
                    }

                    @Override
                    public void onError() {

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            holder.svgaImageView.setVideoItem(entity);
            holder.svgaImageView.startAnimation();
        }
    }

    @Override
    public int getItemCount() {
        return testData == null ? 0 : testData.size();
    }

//    public void clearSvgaCache() {
//        for (SVGAVideoEntity entity : giftMap.values()) {
//            entity.clear();
//        }
//        giftMap.clear();
//    }

    static class GiftViewHolder extends RecyclerView.ViewHolder {
        private final SVGAImageView svgaImageView;

        public GiftViewHolder(View itemView) {
            super(itemView);
            svgaImageView = itemView.findViewById(R.id.item_room_svga);
        }
    }
}