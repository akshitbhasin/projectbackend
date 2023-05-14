package com.insutek.server.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class HostMachineUtils implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    Environment environment;
    private String port;
    private String hostname;

    private static String UNKNOWN_HOST_STRING = "local-host";


    public HostMachineUtils(){
        port = "8080";
        hostname = "localhost";
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getHostname() throws UnknownHostException {
        return hostname == null ?  InetAddress.getLocalHost().getHostAddress() : hostname;
    }

    public HostMachineUtils getHostMachineUtils(){
        return this;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

    }
}
