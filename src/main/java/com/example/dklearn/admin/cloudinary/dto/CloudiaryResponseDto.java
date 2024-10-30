package com.example.dklearn.admin.cloudinary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class CloudiaryResponseDto {
    private String signature;
    private String format;
    @JsonProperty("resource_type")
    private String resourceType;
    @JsonProperty("secure_url")
    private String secureUrl;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("asset_id")
    private String assetId;
    @JsonProperty("version_id")
    private String versionId;
    private String type;
    private String version;
    private String url;
    @JsonProperty("private_id")
    private String privateId;
    @JsonProperty("public_id")
    private String publicId;
    private ArrayList<Object> tags;
    private String folder;
    @JsonProperty("original_filename")
    private String originalFilename;
    @JsonProperty("api_key")
    private String apiKey;
    private String bytes;
    private String width;
    private String etag;
    private boolean placeholder;
    private int height;
}
