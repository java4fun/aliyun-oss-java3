package sfdc.storage.oss;

public class DeleteObjectTest {
    public static void main(String[] args) {
        OssOperationDemo ossDemoClient = new OssOperationDemo();
        String objectName = "HelloOSS";
        System.out.println("File " + objectName + " exists in bucket " + OssOperationDemo.bucketName + ": "
                + ossDemoClient.objectExists(OssOperationDemo.bucketName, objectName));

        // delete file now
        ossDemoClient.deleteObject(OssOperationDemo.bucketName, objectName);

        System.out.println("File " + objectName + " exists in bucket " + OssOperationDemo.bucketName + ": "
                + ossDemoClient.objectExists(OssOperationDemo.bucketName, objectName));

        System.out.println();


    }
}
