package cn.edu.neusoft.coffeeshop.viewflow;

import cn.edu.neusoft.coffeeshop.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class DetailActivity extends Activity {

	private ImageView imgView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.img_view_flow);

		imgView = (ImageView) findViewById(R.id.img_viewflow);
		Bundle bundle = getIntent().getExtras();
		imgView.setImageResource(bundle.getInt("img_id"));
	}
}
