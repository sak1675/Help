package com.example.help;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class TipsFragment extends Fragment {
    WebView webContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tips, container, false);

        webContent = view.findViewById(R.id.webContent);
        webContent.loadUrl("https://www.villageofshorewood.org/281/Preventative-Measures-Against-Rape");
        if(webContent.canGoBack()) {
            webContent.goBack();

            if (webContent.canGoForward()) {
                webContent.goForward();
            }
        }
        return view;
    }


}