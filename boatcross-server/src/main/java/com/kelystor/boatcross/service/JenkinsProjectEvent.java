package com.kelystor.boatcross.service;

import com.kelystor.boatcross.entity.JenkinsProject;

import java.util.List;

public interface JenkinsProjectEvent {
    default void onSorted(List<JenkinsProject> projects) {}

    default void onTriggerBuild(JenkinsProject project) {}

    default void onBuilding(JenkinsProject project) {}

    default void onFinishBuild(JenkinsProject project) {}

    default void onBuildSuccess(JenkinsProject project, String newVersion) {}

    default void onBuildFailed(JenkinsProject project, String message) {}

    default void onBuildError(JenkinsProject project, String message) {}

    default void onComplete() {}
}
