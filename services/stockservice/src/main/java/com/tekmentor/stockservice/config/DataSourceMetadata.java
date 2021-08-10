package com.tekmentor.stockservice.config;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DataSourceMetadata {
    private String username;
    private String password;
    private String host;
    private String engine;
    private String port;
    private String dbInstanceIdentifier;

}
