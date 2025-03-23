# React Spring Boot AWS S3 image upload

## Create an S3 bucket

-   Navigate to S3 in your AWS console
-   Select `Create bucket`
-   Give your bucket a name

[What is AW S3?](https://docs.aws.amazon.com/AmazonS3/latest/userguide/Welcome.html)

## Create a new user in your AWS console:

-   Navigate to the IAM and create a new user.
-   Select the `Attach policies directly` option and then `Create policy`
-   Select the `JSON` option next to visual and paste the following code

```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": ["s3:*", "s3-object-lambda:*"],
            "Resource": [
                "arn:aws:s3:::<replace-with-your-bucket-name>",
                "arn:aws:s3:::<replace-with-your-bucket-name>/*"
            ]
        }
    ]
}
```

-   Attach that policy to your newly created user

## Create a key value pair for the user

-   Go back to IAM and select the user you just created
-   Go to `Security credentials` -> `Access keys`
-   Click `Create access key` -> `Other` -> `Create access key`
-   This should take you to a screen that shows your key-value pair, make sure to download and copy it, you will need it for this application

## Configure AWS in your Spring project

-   Once you have the access key make sure to add the required dependencies (you can find them in the `pom.xml` file of this project)

-   Create a config file with the following code:

```java

@Configuration
public class AWSConfig {

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure().load();
    }

    @Bean
    public S3Presigner s3Presigner(AwsCredentialsProvider awsCredentialsProvider, Dotenv dotenv) {
        return S3Presigner.builder()
                .credentialsProvider(awsCredentialsProvider).region(Region.of(dotenv.get("AWS_REGION")))
                .build();
    }

    @Bean
    public S3Client s3Client(AwsCredentialsProvider awsCredentialsProvider, Dotenv dotenv) {
        return S3Client.builder()
                .credentialsProvider(awsCredentialsProvider).region(Region.of(dotenv.get("AWS_REGION")))
                .build();
    }

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider(Dotenv dotenv) {
        return StaticCredentialsProvider.create(
                AwsBasicCredentials.create(dotenv.get("AWS_ACCESS_KEY_ID"), dotenv.get("AWS_SECRET_ACCESS_KEY")));
    }
}
```

-   Make sure you have the `AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY` environment variables set up in a `.env` file

-   Once you have the above set up your app should be running assuming you provided correct credentials.

## Disclaimers

-   The code was written using the official [AWS examples repo](https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2/example_code/s3/src/main/java/com/example/s3) as a guide
-   It is **NOT** clean code that follows best practices or have proper error handling, so please **DO NOT** copy and paste, use it as a general guide and make your own design decisions
-   Make sure you read about S3 and [presigned urls](https://docs.aws.amazon.com/prescriptive-guidance/latest/presigned-url-best-practices/overview.html) and can answer questions about what they are
