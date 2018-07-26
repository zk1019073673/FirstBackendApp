package com.advantech.homework.model;

import javax.persistence.*;

@Entity
@Table(name="device_info")
public class DeviceInfoPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "devicename")
    private String deviceName;

    @Column(name = "devicetype")
    private Integer deviceType;

    @Column(name = "location")
    private String location;

    @Column(name = "status")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DeviceInfoPO{" +
                "id=" + id +
                ", deviceName='" + deviceName + '\'' +
                ", deviceType=" + deviceType +
                ", location='" + location + '\'' +
                ", status=" + status +
                '}';
    }
}
