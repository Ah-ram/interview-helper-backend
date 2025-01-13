package road_to_employment.interview_helper.amazon_s3.service;

import com.amazonaws.services.s3.AmazonS3Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AmazonS3ServiceImpl implements AmazonS3Service {

    @Value("${aws.bucket-name}")
    private String bucketName;

    private final AmazonS3Client amazonS3Client;

    @Override
    public String deleteFile(String fileName) {

        String result = "success";

        try {
            boolean isObjectExist = amazonS3Client.doesObjectExist(bucketName, fileName);
            if (isObjectExist) {
                amazonS3Client.deleteObject(bucketName, fileName);
            } else {
                result = "file not found";
            }
        } catch (Exception e) {
            log.debug("Delete File failed", e);
        }

        return result;
    }
}
