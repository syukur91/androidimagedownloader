package com.downloader.app.downloader;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Syukur on 7/19/2017.
 */

public class DownloadJobService  extends JobService {

    static final String TAG = DownloadJobService.class.getSimpleName();

    private UpdateAppsAsyncTask updateTask = new UpdateAppsAsyncTask();

    @Override
    public boolean onStartJob(JobParameters params) {
        // Note: this is preformed on the main thread.
        Log.d(TAG, "onStartJob id=" + params.getJobId());
        updateTask.execute(params);
        Toast.makeText(this, R.string.job_started, Toast.LENGTH_LONG).show();
        //TO DO: Get list of image url loop to download image
        final String url = "http://cdn3.nflximg.net/images/3093/2043093.jpg";
        DownloaderHelper.downloadImage(url,this);
        //TO DO: Send downloaded image
        return true;
    }

    // Stopping jobs if our job requires change.

    @Override
    public boolean onStopJob(JobParameters params) {
        // Note: return true to reschedule this job.
        Log.d(TAG, "onStopJob id=" + params.getJobId());
        boolean shouldReschedule = updateTask.stopJob(params);
        return shouldReschedule;
    }

    private class UpdateAppsAsyncTask extends AsyncTask<JobParameters, Void, JobParameters[]> {


        @Override
        protected JobParameters[] doInBackground(JobParameters... params) {

            // Do updating and stopping logical here.
            Log.d(TAG, "Updating apps in the background");
            return params;
        }

        @Override
        protected void onPostExecute(JobParameters[] result) {

            for (JobParameters params : result) {
                if (!hasJobBeenStopped(params)) {
                    Log.d(TAG, "finishing job with id=" + params.getJobId());
                    jobFinished(params, false);
                }
            }
        }

        private boolean hasJobBeenStopped(JobParameters params) {
            // Logic for checking stop.
            return false;
        }

        public boolean stopJob(JobParameters params) {
            Log.d(TAG, "stopJob id=" + params.getJobId());
            // Logic for stopping a job. return true if job should be rescheduled.
            return false;
        }

    }
}
