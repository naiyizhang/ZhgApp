package com.zhg.api.samples.otto;

/**
 * Created by nyzhang on 2015/12/17.
 */
public class LocationChangeEvent {
    public float lat;
    public float lon;

    public LocationChangeEvent(float lat, float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "LocationChangeEvent{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
