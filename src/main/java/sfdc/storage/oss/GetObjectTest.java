package sfdc.storage.oss;

import java.io.File;
import java.io.IOException;

public class GetObjectTest {
    public static void main(String[] args) throws IOException {

        OssOperationDemo ossDemoClient = new OssOperationDemo();
        String objectName = "HelloOSS";

        System.out.println("File " + objectName + " exists in bucket " + OssOperationDemo.bucketName + ": "
                + ossDemoClient.objectExists(OssOperationDemo.bucketName, objectName));

        // read file now
        ossDemoClient.readObject(OssOperationDemo.bucketName, objectName);

        System.out.println("File " + objectName + " was downloaded successfully!");

        System.out.println();

    }
}
