package com.example.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@Component
@PropertySource(value="classpath:pushMessageByGrade.properties",encoding="utf-8")
@ConfigurationProperties(prefix = "abc.edc")
public class PropertiesFromClassPath {
    private String language ;
    private String os ;
    private String version;
    private String messageZH ;
    private String messageEN ;
    private String titleZH ;
    private String titleEN ;
    private String upgradeFromXAtoXBZH ;
    private String upgradeFromXAtoXBEN ;
    private String upgradeFromXAtoXHZH ;
    private String upgradeFromXAtoXHEN ;
    private String upgradeFromXBtoXHZH ;
    private String upgradeFromXBtoXHEN ;


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMessageEN() {
        return messageEN;
    }

    public void setMessageEN(String messageEN) {
        this.messageEN = messageEN;
    }

    public String getTitleZH() {
        return titleZH;
    }

    public void setTitleZH(String titleZH) {
        this.titleZH = titleZH;
    }

    public String getTitleEN() {
        return titleEN;
    }

    public void setTitleEN(String titleEN) {
        this.titleEN = titleEN;
    }

    public PropertiesFromClassPath() {
    }

    public void setMessageZH(String messageZH){
        this.messageZH = messageZH ;
    }
    public String getMessageZH(){
        return this.messageZH ;
    }

    public void setUpgradeFromXAtoXBZH(String upgradeFromXAtoXBZH){
        this.upgradeFromXAtoXBZH = upgradeFromXAtoXBZH;
    }
    public String getUpgradeFromXAtoXBZH(){
        return this.upgradeFromXAtoXBZH ;
    }
    public void setUpgradeFromXAtoXBEN(String upgradeFromXAtoXBEN){
        this.upgradeFromXAtoXBEN = upgradeFromXAtoXBEN;
    }
    public String getUpgradeFromXAtoXBEN(){
        return this.upgradeFromXAtoXBEN ;
    }

    public void setUpgradeFromXAtoXHZH(String upgradeFromXAtoXHZH){
        this.upgradeFromXAtoXHZH = upgradeFromXAtoXHZH;
    }
    public String getUpgradeFromXAtoXHZH(){ return this.upgradeFromXAtoXHZH ; }
    public void setUpgradeFromXAtoXHEN(String upgradeFromXAtoXHEN){
        this.upgradeFromXAtoXHEN = upgradeFromXAtoXHEN;
    }
    public String getUpgradeFromXAtoXHEN(){
        return this.upgradeFromXAtoXHEN ;
    }

    public void setUpgradeFromXBtoXHZH(String upgradeFromXBtoXHZH){
        this.upgradeFromXBtoXHZH = upgradeFromXBtoXHZH;
    }
    public String getUpgradeFromXBtoXHZH(){ return this.upgradeFromXBtoXHZH ; }
    public void setUpgradeFromXBtoXHEN(String upgradeFromXBtoXHEN){
        this.upgradeFromXBtoXHEN = upgradeFromXBtoXHEN;
    }
    public String getUpgradeFromXBtoXHEN(){
        return this.upgradeFromXBtoXHEN ;
    }
}
