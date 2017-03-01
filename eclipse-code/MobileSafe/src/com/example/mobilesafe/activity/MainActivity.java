package com.example.mobilesafe.activity;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.mobilesafe.R;
import com.example.mobilesafe.utils.streamUtils;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv_version;
	private String mVersionName;
	private int mVersionCode;
	private String mDescription;
	private String mURL; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_version=(TextView) findViewById(R.id.tv_version);
		tv_version.setText("版本号："+getPackageName());
		checkVersion();
	}
	public String getPackageName(){
		PackageManager packageManager= getPackageManager();
		try{
			PackageInfo packageInfo= packageManager.getPackageInfo("com.example.mobilesafe", 0);
			int code=packageInfo.versionCode;
			String versionName=packageInfo.versionName;
			return versionName;
		}catch(Exception e){
			System.err.println("未获得包名");
		}
		return "";
	}
	private void checkVersion(){
		new Thread(){
			public void run() {
				Log.i("main", "checkVersion");
				try {
					//本机用localhost，如果用模拟器加载电脑可以用IP(10.0.2.2)
					URL url=new URL("http://10.0.2.2:8080/update.json");
					HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
					httpURLConnection.setRequestMethod("GET");
					httpURLConnection.setConnectTimeout(5000);//连接超时
					httpURLConnection.setReadTimeout(5000);//响应超时
					Log.i("main", "code");
					httpURLConnection.connect();//连接
					int code=httpURLConnection.getResponseCode();
					Log.i("main", "code="+code);
					if(code==200){
						InputStream inputStream=httpURLConnection.getInputStream();
						String msg=streamUtils.readFromStream(inputStream);
						System.out.println("网络返回："+msg);
						
						JSONObject jsonObject=new JSONObject(msg);
						mVersionName=jsonObject.getString("versionName");
						mVersionCode=jsonObject.getInt("versionCode");
						mDescription=jsonObject.getString("description");
						mURL=jsonObject.getString("downloadUrl");
						Log.i("main", msg);
					}else{
						System.err.println("连接服务端出错");
					}
				} catch (MalformedURLException e) {
					//网址错误
					e.printStackTrace();
				} catch (IOException e) {
					//连接错误
					e.printStackTrace();
				} catch (JSONException e) {
					// Json解析失败
					e.printStackTrace();
				}
			};
		}.start();

	}
}
