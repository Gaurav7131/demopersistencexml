package com.example.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.Entity.ProtoTypeBean;
import com.example.Service.Plugin;
import com.example.springcorelab.NotificationService;

@Component
public class NotificationManager {
    private final NotificationService notificationService;
    private final List<Plugin> plugins;
    private final Map<String, Plugin> pluginMap;

    // Xml configuration:explicitly state "properties" from app.prop injavaclass
    @Value("${'app.name:DefaultSpringApp'}")
    private String appName;

    // constructor injection with @Qualifier to reduce ambiguity
    public NotificationManager(@Qualifier("emailService") NotificationService notificationService, List<Plugin> plugins,
            Map<String, Plugin> plugMap, Map<String, Plugin> pluginMap) {
        this.notificationService = notificationService;
        this.pluginMap = pluginMap;
        this.plugins = plugins;
    }

    // method
    public void processAll() {
        System.out.println("App name from Properties:" + appName);
        notificationService.sendNotification("Hello constructor injection via Qualifier:");

        // Ordered list (Plugin.java)
        System.out.println("Executing Ordered pluginList");
        for (Plugin plugin : plugins)
            plugin.execute();
    }

    // LookUp method:abstract magical getter method
    // allows to used prototypescope in singletonscope give fresh,instance of bean
    @org.springframework.beans.factory.annotation.Lookup
    public ProtoTypeBean getProtoTypeBean() {
        // Spring overrides this method at runtime to return a fresh prototype bean
        // every time!
        return null;

    }
}
