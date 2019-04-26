package com.example.chapter05_project_mvvm.contract;

import com.example.chapter05_project_mvvm.model.GitHubService;

public interface RepositoryListViewContract {

    void showRepositories(GitHubService.Repositories repositories);

    void showError();

    void startDetailActivity(String fullRepositoryName);

}
