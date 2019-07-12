package com.example.myapplication;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import android.location.LocationManager;
import android.location.Location;
import android.content.Context;
import android.location.LocationListener;
import android.util.Log;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.Manifest;
import android.support.v4.app.ActivityCompat;

import android.widget.Button;
import android.view.View;
public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    setTitle(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    setTitle(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    setTitle(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };
    private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//保存当前状态
        setContentView(R.layout.activity_main);//引入layout
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }
    //用Activity实现OnClickListener接口

    public void click(View v) {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, 1);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        TextView t1=(TextView) findViewById(R.id.t1);
        TextView t2=(TextView) findViewById(R.id.t2);
        TextView t3=(TextView) findViewById(R.id.t3);
        if (location != null) {
            double latitude = location.getLatitude();     //经度
            double longitude = location.getLongitude(); //纬度
            double altitude =  location.getAltitude();     //海拔


            t1.setText(Double.toString(latitude));
            t2.setText(Double.toString(longitude));
            t3.setText(Double.toString(altitude));
        }
        else{
            t1.setText("未获得");
            t2.setText("未获得");
            t3.setText("未获得");
        }
    }
    //通过R.来调用res中的资源




    /*
    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) { //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
            // log it when the location changes
            if (location != null) {
                Log.i("SuperMap", "Location changed : Lat: "
                        + location.getLatitude() + " Lng: "
                        + location.getLongitude());
            }
        }

        public void onProviderDisabled(String provider) {
            // Provider被disable时触发此函数，比如GPS被关闭
        }

        public void onProviderEnabled(String provider) {
            //  Provider被enable时触发此函数，比如GPS被打开
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Provider的转态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        }
    };
    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	*/
	//locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
    //       1000, 0, locationListener);


}
