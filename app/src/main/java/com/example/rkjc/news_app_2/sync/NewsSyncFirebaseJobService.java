package com.example.rkjc.news_app_2.sync;

import com.example.rkjc.news_app_2.data.NewsItemRepository;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;


public class NewsSyncFirebaseJobService extends JobService{

    @Override
    public boolean onStartJob(JobParameters job) {
        NewsItemRepository repository = NewsItemRepository.getInstance(getApplication());
        repository.syncWithAPI();
        NotificationUtils.notifySync(this);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return true;
    }
}
