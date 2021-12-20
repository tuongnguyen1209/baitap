package com.vtca.color.reader.common.version;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class VersionController {

    @Value("${git.tags}")
    private String gitTags;
    @Value("${git.branch}")
    private String gitBranch;
    @Value("${git.dirty}")
    private String gitDirty;
    @Value("${git.remote.origin.url}")
    private String gitRemoteOriginUrl;
    @Value("${git.commit.id}")
    private String gitCommitId;
    @Value("${git.commit.id.abbrev}")
    private String gitCommitIdAbbrev;
    @Value("${git.commit.id.describe}")
    private String gitCommitIdDescribe;
    @Value("${git.commit.id.describe-short}")
    private String gitCommitIdDescribeShort;
    @Value("${git.commit.user.name}")
    private String gitCommitUserName;
    @Value("${git.commit.user.email}")
    private String gitCommitUserEmail;
    @Value("${git.commit.message.full}")
    private String gitCommitMessageFull;
    @Value("${git.commit.message.short}")
    private String gitCommitMessageShort;
    @Value("${git.commit.time}")
    private String gitCommitTime;
    @Value("${git.closest.tag.name}")
    private String gitClosestTagName;
    @Value("${git.closest.tag.commit.count}")
    private String gitClosestTagCommitCount;
    @Value("${git.build.user.name}")
    private String gitBuildUserName;
    @Value("${git.build.user.email}")
    private String gitBuildUserEmail;
    @Value("${git.build.time}")
    private String gitBuildTime;
    @Value("${git.build.host}")
    private String gitBuildHost;
    @Value("${git.build.version}")
    private String gitBuildVersion;

    @GetMapping("/v.html")
    public Map<String, String> getCommitId() {
        Map<String, String> result = new HashMap<>();
        result.put("Git Tags", gitTags);
        result.put("Git Branch", gitBranch);
        result.put("Git Dirty", gitDirty);
        result.put("Git Remote Origin Url", gitRemoteOriginUrl);
        result.put("Git Commit Id", gitCommitId);
        result.put("Git Commit Id Abbrev", gitCommitIdAbbrev);
        result.put("Git Commit Id Describe", gitCommitIdDescribe);
        result.put("Git Commit Id Describe Short", gitCommitIdDescribeShort);
        result.put("Git Commit User Name", gitCommitUserName);
        result.put("Git Commit User Email", gitCommitUserEmail);
        result.put("Git Commit Message Full", gitCommitMessageFull);
        result.put("Git Commit Message Short", gitCommitMessageShort);
        result.put("Git Commit Time", gitCommitTime);
        result.put("Git Closest Tag Name", gitClosestTagName);
        result.put("Git Closest Tag Commit Count", gitClosestTagCommitCount);
        result.put("Git Build User Name", gitBuildUserName);
        result.put("Git Build User Email", gitBuildUserEmail);
        result.put("Git Build Time", gitBuildTime);
        result.put("Git Build Host", gitBuildHost);
        result.put("Git Build Version", gitBuildVersion);

        return result;
    }
}
