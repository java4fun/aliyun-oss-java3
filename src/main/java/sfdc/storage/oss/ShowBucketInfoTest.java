package sfdc.storage.oss;

public class ShowBucketInfoTest {
    public static void main(String[] args) {
        OssOperationDemo ossDemoClient = new OssOperationDemo();

        // read file now
        ossDemoClient.showBucketInfo(OssOperationDemo.bucketName);

        System.out.println();


    }
}
