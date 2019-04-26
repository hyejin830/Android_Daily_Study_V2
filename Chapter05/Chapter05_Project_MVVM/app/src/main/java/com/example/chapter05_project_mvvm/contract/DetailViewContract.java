package com.example.chapter05_project_mvvm.contract;


import android.util.Log;

/**
 * 상세 화면의 뷰가
 */

public interface DetailViewContract {

    String getFullRepositoryName();

    void startBrowser(String url);

    void showError(String message);
}


