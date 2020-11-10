package com.example.learningwebviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUrl;
    private Button buttonGo;
    private Button buttonLoadHTML;
    private Button buttonLoadHTMLFile;
    private WebView webView;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()){
            this.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mapping
        editTextUrl = findViewById(R.id.edit_text_url);
        buttonGo = findViewById(R.id.button_go);
        buttonLoadHTML = findViewById(R.id.button_load_html);
        buttonLoadHTMLFile = findViewById(R.id.button_load_html_file);
        webView = findViewById(R.id.web_view);

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new MyWebViewClient(this));

        // Load page from url to WebView
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editTextUrl.getText().toString();
                if(url.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please input Url", Toast.LENGTH_SHORT).show();
                }
                else {
                    webView.loadUrl(url);
                }
            }
        });

        // Load page from html file to WebView
        buttonLoadHTMLFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                webView.loadUrl("file:///android_asset/htmlfile.html");
            }
        });

        // Load page from html string to WebView
        buttonLoadHTML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String htmlString = "<html>\n" +
                        "<body>\n" +
                        "\n" +
                        "<h1>This content is loaded from html string</h1>\n" +
                        "\n" +
                        "<h3>Go to Google Developers page: </h3>\n" +
                        "<p>\n" +
                        "  <a href=\"https://developers.google.com/\">https://developers.google.com</a>\n" +
                        "</p>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>";

                webView.loadData(htmlString, "text/html", "UTF-8");
            }
        });



    }
}