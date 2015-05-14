package com.android.rockpaperspear;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.widget.TextView;
import android.view.Window; // center ad title

public class FSUCSPopup extends Activity {
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.fsu_cs_popup);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.ad_title);
		final SpannableString s = new SpannableString("http://www.cs.fsu.edu/");
	    Linkify.addLinks(s, Linkify.ALL);
	    TextView link = (TextView)findViewById(R.id.url);
	    link.setText(s);
	    link.setMovementMethod(LinkMovementMethod.getInstance());

}
	public void popupClose(View view) {
		finish();
	}
}
