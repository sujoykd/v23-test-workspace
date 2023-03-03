package com.example.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@SpringBootApplication
@Theme(value = "v23-starter", variant = Lumo.LIGHT)
public class Application implements AppShellConfigurator {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
