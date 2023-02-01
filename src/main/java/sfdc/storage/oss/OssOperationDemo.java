package sfdc.storage.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.OSSObject;

import java.io.*;

public class OssOperationDemo {


    private static final String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    private static final String accessKeyId = "<yourAccessKeyId>";
    private static final String accessKeySecret = "<yourAccessKeySecret>";
    private static final int CHUNK_SIZE = 1024;
    private static final String CONTENT_SUFFIX = "txt";
    protected static final String bucketName = "<yourBucketName>";




    public void showBucketInfo(String bucketName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            BucketInfo info = ossClient.getBucketInfo(bucketName);
            System.out.println("Bucket " + bucketName + " Info：");
            System.out.println("\tRegion：" + info.getBucket().getLocation());
            System.out.println("\tCreated On：" + info.getBucket().getCreationDate());
            System.out.println("\tOwner：" + info.getBucket().getOwner());
        } finally {
            ossClient.shutdown();
        }
    }


    public void writeObject(String bucketName, String objectName, InputStream is) throws IOException {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            throw new RuntimeException("Failed to write object to OSS, bucket " + bucketName + " does not exist");
        }
        try {
            ossClient.putObject(bucketName, objectName, is);
        } finally {
            ossClient.shutdown();
        }
    }


    public File readObject(String bucketName, String objectName) throws IOException {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            throw new RuntimeException("Failed to read object from OSS, bucket " + bucketName + " does not exist");
        }

        try {
            // 1 get object
            OSSObject objMetaData =  ossClient.getObject(bucketName, objectName);
            if(objMetaData == null) {
                return null;
            }
            // 2. write the object to a file
            File file = readContentIntoFile(objMetaData.getObjectContent(), objectName);

            objMetaData.getObjectContent().close();
            return file;
        } finally {
            ossClient.shutdown();
        }
    }


    public boolean objectExists(String bucketName, String objectName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            ossClient.doesObjectExist(bucketName, objectName);
            return true;
        } finally {
            ossClient.shutdown();
        }
    }


    public boolean deleteObject(String bucketName, String objectName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            throw new RuntimeException("Failed to delete object from OSS, bucket " + bucketName + " does not exist");
        }
        try {
            ossClient.deleteObject(bucketName, objectName);
            return true;
        } finally {
            ossClient.shutdown();
        }
    }

    static File readContentIntoFile(InputStream is, String objectName) throws IOException {

        byte[] contentBuffer = new byte[CHUNK_SIZE];
        File file = File.createTempFile(objectName, CONTENT_SUFFIX);

        try (OutputStream out = new FileOutputStream(file)) {
            int bytesRead;
            while ((bytesRead = is.read(contentBuffer)) != -1) {
                out.write(contentBuffer, 0, bytesRead);
            }
        } catch (Exception e) {
            // If there is an exception caught, delete the file and propagate exception
            if(file != null) file.delete();
            throw e;
        }
        return file;
    }
}
