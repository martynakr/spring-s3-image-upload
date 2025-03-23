package com.example.demo.config;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

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
