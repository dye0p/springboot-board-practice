package com.springboot.board.springboot_board.global.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

@Getter
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperties {

    private String host;
    private int port;
    private String username;
    private String password;
    private boolean auth;
    private boolean starttlsEnable;
    private boolean starttlsRequired;
    private int connectionTimeout;
    private int timeout;
    private int writeTimeout;
    private Properties properties;

    public MailProperties(String host, int port, String username, String password, boolean auth, boolean starttlsEnable, boolean starttlsRequired, int connectionTimeout, int timeout, int writeTimeout, Properties properties) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.auth = auth;
        this.starttlsEnable = starttlsEnable;
        this.starttlsRequired = starttlsRequired;
        this.connectionTimeout = connectionTimeout;
        this.timeout = timeout;
        this.writeTimeout = writeTimeout;
        this.properties = properties;
    }
}
