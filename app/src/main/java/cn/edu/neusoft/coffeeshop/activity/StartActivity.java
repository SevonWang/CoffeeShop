package cn.edu.neusoft.coffeeshop.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		SharedPreferences sharedPreferences = getSharedPreferences("Config",
				MODE_PRIVATE);

		String time = sharedPreferences.getString("time", "");
		if (time.equals("secondTime")) {
			Intent secondTime = new Intent(StartActivity.this,
					MainActivity.class);
			startActivity(secondTime);
			finish();
		} else {
			SharedPreferences sp = getSharedPreferences("Config", MODE_PRIVATE);
			Editor editor = sp.edit();
			editor.putString("time", "secondTime");
			editor.commit();
			Intent firstTime = new Intent(StartActivity.this, Welcome.class);
			startActivity(firstTime);
			finish();
		}
	}

}
