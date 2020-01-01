package com.alkhalildevelopers.mytestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.IUnityBannerListener;
import com.unity3d.services.banners.UnityBanners;

public class MainActivity extends AppCompatActivity {
    private String GameID = "3416089";
    private boolean testMode = true;
    private String bannerAdPlacement = "banner";
    private String interstitialAdPlacement = "interstitial";

    private Button showInterstitialBtn,showBannerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UnityAds.initialize(MainActivity.this,GameID,testMode);
        IUnityAdsListener unityAdsListener = new IUnityAdsListener() {
            @Override
            public void onUnityAdsReady(String s) {
                Toast.makeText(MainActivity.this, "Interstitial Ad ready" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUnityAdsStart(String s) {
                Toast.makeText(MainActivity.this, "Interstititl is playing", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
                Toast.makeText(MainActivity.this, "Interstitial is Finished" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
                Toast.makeText(MainActivity.this, unityAdsError.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        UnityAds.setListener(unityAdsListener);

        IUnityBannerListener iUnityBannerListener = new IUnityBannerListener() {
            @Override
            public void onUnityBannerLoaded(String s, View view) {
                ((ViewGroup)findViewById(R.id.bannerAdLayout)).removeView(view);
                ((ViewGroup)findViewById(R.id.bannerAdLayout)).addView(view);

            }

            @Override
            public void onUnityBannerUnloaded(String s) {

            }

            @Override
            public void onUnityBannerShow(String s) {

            }

            @Override
            public void onUnityBannerClick(String s) {

            }

            @Override
            public void onUnityBannerHide(String s) {

            }

            @Override
            public void onUnityBannerError(String s) {

            }
        };

        UnityBanners.setBannerListener(iUnityBannerListener);


        showInterstitialBtn = findViewById(R.id.showInterstitialAdBtn);
        showBannerBtn = findViewById(R.id.showBannerAdBtn);

        showInterstitialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnityAds.load(interstitialAdPlacement);
                DisplayInterstitialAd();
                UnityBanners.loadBanner(MainActivity.this,bannerAdPlacement);
            }
        });

        showBannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnityBanners.loadBanner(MainActivity.this,bannerAdPlacement);
            }
        });

    }

    private  void DisplayInterstitialAd (){
        if (UnityAds.isReady(interstitialAdPlacement)){
            UnityAds.show(MainActivity.this,interstitialAdPlacement);
        }
    }
}
