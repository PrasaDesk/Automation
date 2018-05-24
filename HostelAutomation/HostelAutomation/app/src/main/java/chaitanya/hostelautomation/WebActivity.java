package chaitanya.hostelautomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebActivity extends AppCompatActivity{

  WebView webView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_web);

    String ipAdd = getIntent().getStringExtra("ip");
    String portNum = getIntent().getStringExtra("port");
    String url = "http://" + ipAdd + ":" + portNum ;
   // String url= "https://www.google.co.in";

    webView = (WebView)findViewById(R.id.webview);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl(url);
  }
}
