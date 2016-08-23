package com.adotdamo.android.recipeapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


/**
 * Created by karloandrada on 6/16/16.
 */
public class RecipePageFragment extends Fragment {
    private static final String ARG_URI = "recipe_page_url";


    private Uri mUri;
    private WebView mWebView;
    private ProgressBar mProgressBar;

    public static RecipePageFragment newInstance(Uri uri)
    {
        Bundle args = new Bundle();
        args.putParcelable(ARG_URI,uri);

        RecipePageFragment fragment = new RecipePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUri = getArguments().getParcelable(ARG_URI);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recipe_page,container,false);

        mProgressBar = (ProgressBar)v.findViewById(R.id.fragment_recipe_page_progress_bar);
        mProgressBar.setMax(100);

        mWebView = (WebView) v.findViewById(R.id.fragment_recipe_web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient()
        {
            public void onProgressChanged(WebView webView, int newProgress)
            {
                if(newProgress == 100)
                {
                    mProgressBar.setVisibility(View.GONE);
                }else
                {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }

            public void onReceivedTitle(WebView webView,String title)
            {
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                activity.getSupportActionBar().setSubtitle(title);
            }
        });


        mWebView.setWebViewClient(new WebViewClient()
        {
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                return false;
            }
        });
        mWebView.loadUrl(mUri.toString());

        return v;
    }


}
