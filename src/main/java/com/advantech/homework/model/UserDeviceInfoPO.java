package com.advantech.homework.model;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_device")
@Embeddable
public class UserDeviceInfoPO implements Serializable {

    @Id
    @Column(name = "user_id")
    private Integer userId;
    @Id
    @Column(name = "device_id")
    private Integer deviceId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "UserDeviceInfoPO{" +
                "userId=" + userId +
                ", deviceId=" + deviceId +
                '}';
    }
}
