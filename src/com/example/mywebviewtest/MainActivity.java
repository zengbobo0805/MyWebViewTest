package com.example.mywebviewtest;

import java.io.UnsupportedEncodingException;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class MainActivity extends SherlockFragmentActivity {
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);
		tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
//				intent.setData(Uri
//						.parse("http://ma.taobao.com/r/ci-uH8izqW21rbYACs"));
				intent.setData(Uri
						.parse("http://10.3.1.140:8080/client/index.jsp"));
				startActivity(intent);
			}
		});
		String str = "";
		if (TextUtils.isEmpty(read())) {
			str = "hello world:" + "<img src=\"" + R.drawable.ic_launcher
					+ "\"/>";
			System.out.println("str 1:"+str);
		} else {
			str = read();
			System.out.println("str 2:"+str);

		}
//		tv.setText(Html.fromHtml(str, new ImageGetter() {
//
//			@Override
//			public Drawable getDrawable(String source) {
//				int id = Integer.parseInt(source);
//				Drawable d = getResources().getDrawable(id);
//				d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
//				return d;
//			}
//		}, null));
		
		String buf  = "1234567890";
		SpannableString spannableString = new SpannableString(buf);
		Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		 ImageSpan imageSpan = new ImageSpan(bp);    
		spannableString.setSpan(imageSpan, 3, buf.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		tv.setText(spannableString);
		String model_buf = AntTest.objectToString();
		BaseModel basemodel =AntTest.stringToObject(model_buf);
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(model_buf+"\n");
		stringBuffer.append(basemodel.getDateTime()+"\n");
		stringBuffer.append(basemodel.getName()+"\n");
		stringBuffer.append(basemodel.getNumber()+"\n");
		stringBuffer.append(basemodel.getPrice()+"\n");
		stringBuffer.append(basemodel+"\n");
		
		tv.setText(stringBuffer.toString());
	ActionBar actionBar =	getSupportActionBar();
	actionBar.setDisplayHomeAsUpEnabled(true);
	actionBar.setHomeButtonEnabled(true);
	actionBar.setDisplayUseLogoEnabled(true);
	String url = "http://pic.lvmama.com/pics/super/2014/02/94540.jpg";
	System.out.println("myWebViewTest url:"+generateFileName(url));
	
	}

	
	public static String generateFileName(String url) {
		try {
			return SpecBase64.encode(url.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			return SpecBase64.encode(url.getBytes());
		}
	}
	private void save() {
		SharedPreferences sh = getSharedPreferences("my", MODE_PRIVATE);
		Editor ed = sh.edit();
		ed.putString("tv", tv.getText().toString());
		ed.commit();
	}

	private String read() {
		SharedPreferences sh = getSharedPreferences("my", MODE_PRIVATE);
		return sh.getString("tv", "");
	}
}
