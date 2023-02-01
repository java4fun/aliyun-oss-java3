package sfdc.storage.oss;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PutObjectTest {
    public static void main(String[] args) throws IOException {

        OssOperationDemo ossDemoClient = new OssOperationDemo();
        String objectName = "HelloOSS";

        System.out.println("File " + objectName + " exists in bucket " + OssOperationDemo.bucketName + ": "
                + ossDemoClient.objectExists(OssOperationDemo.bucketName, objectName));

        InputStream is = new ByteArrayInputStream("Hello OSS".getBytes());

        // write object to bucket
        ossDemoClient.writeObject(OssOperationDemo.bucketName, objectName, is);

        System.out.println("File " + objectName + " was created in bucket " + OssOperationDemo.bucketName );

        System.out.println();

    }
}
