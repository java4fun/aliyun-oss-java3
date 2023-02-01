package org.demo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;

public class StorageServiceImpl {
    private final OSS ossClient;

    public StorageServiceImpl() {
        this.ossClient = new OSSClientBuilder().build(
            System.getenv("endpoint"), 
            System.getenv("accessKeyId"), 
            System.getenv("accessKeySecret"));
    }


    //is bucket exist?
    public boolean doesBucketExist(String bucketName) {
        return ossClient.doesBucketExist(bucketName);
    }

    //create a bucket
    public Bucket createBucket(String bucketName) {
        return ossClient.createBucket(bucketName);
    }

    //list all buckets
    public List<Bucket> listBuckets() {
        return ossClient.listBuckets();
    }

    //delete a bucket
    public void deleteBucket(String bucketName) {
        ossClient.deleteBucket(bucketName);
    }

    //uploading object
    public PutObjectResult putObject(String bucketName, String key, File file) {
        return ossClient.putObject(bucketName, key, file);
    }

    //listing objects
    public ObjectListing listObjects(String bucketName) {
        return ossClient.listObjects(bucketName);
    }

    //get an object
    public OSSObject getObject(String bucketName, String objectKey) {
        return ossClient.getObject(bucketName, objectKey);
    }

    //copying an object
    public CopyObjectResult copyObject(
            String sourceBucketName,
            String sourceKey,
            String destinationBucketName,
            String destinationKey
    ) {
        return ossClient.copyObject(
                sourceBucketName,
                sourceKey,
                destinationBucketName,
                destinationKey
        );
    }

    //deleting an object
    public void deleteObject(String bucketName, String objectKey) {
        ossClient.deleteObject(bucketName, objectKey);
    }

    //deleting multiple Objects
    public DeleteObjectsResult deleteObjects(DeleteObjectsRequest delObjReq) {
        return ossClient.deleteObjects(delObjReq);
    }
}