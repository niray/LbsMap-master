/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.shuyu.lbsmap;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.mapapi.clusterutil.clustering.ClusterManager;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapLoadedCallback;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;


public class MarkerActivity extends Activity implements OnMapLoadedCallback {

    MapView mMapView;
    BaiduMap mBaiduMap;
    MapStatus ms;
    private ClusterManager<ClusterBaiduItem> mClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapView = (MapView) findViewById(R.id.baidu_map);
        ms = new MapStatus.Builder().target(new LatLng(39.914935, 116.403119)).zoom(8).build();
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setOnMapLoadedCallback(this);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(ms));
        // 定义点聚合管理类ClusterManager
        mClusterManager = new ClusterManager<ClusterBaiduItem>(this, mBaiduMap);
        // 添加Marker点
        addMarkers();
        // 设置地图监听，当地图状态发生改变时，进行点聚合运算
        mBaiduMap.setOnMapStatusChangeListener(mClusterManager);

    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    /**
     * 向地图添加Marker点
     */
    public void addMarkers() {
        // 添加Marker点
        LatLng llA = new LatLng(39.963175, 116.400244);
        LatLng llB = new LatLng(39.942821, 116.369199);
        LatLng llC = new LatLng(39.939723, 116.425541);
        LatLng llD = new LatLng(39.906965, 116.401394);
        LatLng llE = new LatLng(39.956965, 116.331394);
        LatLng llF = new LatLng(39.886965, 116.441394);
        LatLng llG = new LatLng(39.996965, 116.411394);

        List<ClusterBaiduItem> items = new ArrayList<>();
        items.add(new ClusterBaiduItem(llA));
        items.add(new ClusterBaiduItem(llB));
        items.add(new ClusterBaiduItem(llC));
        items.add(new ClusterBaiduItem(llD));
        items.add(new ClusterBaiduItem(llE));
        items.add(new ClusterBaiduItem(llF));
        items.add(new ClusterBaiduItem(llG));

        mClusterManager.addItems(items);

    }

    @Override
    public void onMapLoaded() {
        ms = new MapStatus.Builder().zoom(9).build();
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(ms));
    }

}
