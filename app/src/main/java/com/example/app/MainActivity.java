package com.example.app;

import android.annotation.SuppressLint;
import android.webkit.WebSettings;


import android.os.Bundle; //existing
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity; // implement library

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private EditText searchBar;
    private Button homeButton;
    private TextView tabCount;
    private ImageView menuButton;
    //private WebView mWebView;

    @Override
    @SuppressLint("SetJavaScriptEnabled")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        searchBar = findViewById(R.id.searchBar);
        homeButton = findViewById(R.id.homeButton);
        tabCount = findViewById(R.id.tabCount);
        menuButton = findViewById(R.id.menuButton);

        // Set up WebView
        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        loadHomePage(); // Load the initial home page

        // Handle search button click
        searchBar.setOnKeyListener((view, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                performSearch();
                return true;
            }
            return false;
        });

        // Handle home button click
        homeButton.setOnClickListener(v -> loadHomePage());

        // Handle menu button click
        menuButton.setOnClickListener(v -> openMenu());
        

        // REMOTE RESOURCE
        // mWebView.loadUrl("https://example.com");

        // LOCAL RESOURCE
        // mWebView.loadUrl("file:///android_asset/index.html");
    }

    private void performSearch() {
        String searchTerm = searchBar.getText().toString();
        if (isValidUrl(searchTerm)) {
            webView.loadUrl(searchTerm);
        } else {
            String searchUrl = "https://www.google.com/search?q=" + searchTerm;
            webView.loadUrl(searchUrl);
        }
    }

    private boolean isValidUrl(String url) {
        // Implement your own logic to validate the URL format
        return url.startsWith("http://") || url.startsWith("https://") || url.startsWith("www") || url.contains(".com");
    }

    private void loadHomePage() {
        // Load your desired home page URL
        String homeUrl = "https://www.example.com";
        webView.loadUrl(homeUrl);
    }

    private void openMenu() {
        // Implement your logic to open the menu (e.g., show a menu dialog)
        // ...
    }
    
    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
