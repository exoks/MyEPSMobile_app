package com.example.eps;

import java.io.Serializable;

public class VideoClassModel  implements Serializable {
    private String imageTempnail;
    private String videoPath;
    private String videoTitle;

    public VideoClassModel() {
    }
    public VideoClassModel(String imageTempnail, String videoPath, String videoTitle) {
        this.imageTempnail = imageTempnail;
        this.videoPath = videoPath;
        this.videoTitle = videoTitle;
    }

    public String getImageTempnail() {
        return imageTempnail;
    }

    public void setImageTempnail(String imageTempnail) {
        this.imageTempnail = imageTempnail;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }
}
