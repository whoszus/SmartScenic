package com.scenic.model;

import com.scenic.repo.pojo.City;
import com.scenic.repo.pojo.ScenicSpot;

/**
 * Created by Lr on 2016/5/9/009.
 */
public class MScenicSpot {

    private Integer scenicSpotNo;
    private String scenicSpotName;
    private String scenicPhoto;
    private City city;
    private Integer cityId;
    private String cityName;
    private String scenicSpotShortInfo;
    private String scenicInfo;
    private Double scenicSpoMinLongitude;
    private Double scenicSpotMinLatitude;
    private Double scenicSpotMaxLongitude;
    private Double scenicSpotMaxLatitude;

    public MScenicSpot(){

    }

    public MScenicSpot(ScenicSpot scenicSpot,City city){
        if (scenicSpot.getScenicSpotNo() != null)
            this.scenicSpotNo = scenicSpot.getScenicSpotNo();
        if (scenicSpot.getScenicSpotName() != null)
            this.scenicSpotName =scenicSpot.getScenicSpotName();

        if (city != null){
            if( city.getCityId()!=null)
            this.cityId  = city.getCityId();

            if(city.getCityName()!=null)
                this.cityName = city.getCityName();
        }

        if (scenicSpot.getScenicPhoto() != null)
            this.scenicPhoto =scenicSpot.getScenicPhoto();

        if (scenicSpot.getScenicSpotShortInfo() != null)
            this.scenicSpotShortInfo = scenicSpot.getScenicSpotShortInfo();

        if (scenicSpot.getScenicInfo() != null)
            this.scenicInfo = scenicSpot.getScenicInfo();
        if (scenicSpot.getScenicSpoMinLongitude() != null)
            this.scenicSpoMinLongitude = scenicSpot.getScenicSpoMinLongitude();

        if (scenicSpot.getScenicSpotMinLatitude() != null)
            this.scenicSpotMinLatitude = scenicSpot.getScenicSpotMinLatitude();

        if (scenicSpot.getScenicSpotMaxLongitude() != null)
            this.scenicSpotMaxLongitude =scenicSpot.getScenicSpotMaxLongitude();

        if (scenicSpot.getScenicSpotMaxLatitude() != null)
            this.scenicSpotMaxLatitude =scenicSpot.getScenicSpotMaxLatitude();
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getScenicInfo() {
        return scenicInfo;
    }

    public void setScenicInfo(String scenicInfo) {
        this.scenicInfo = scenicInfo;
    }

    public String getScenicPhoto() {
        return scenicPhoto;
    }

    public void setScenicPhoto(String scenicPhoto) {
        this.scenicPhoto = scenicPhoto;
    }

    public Double getScenicSpoMinLongitude() {
        return scenicSpoMinLongitude;
    }

    public void setScenicSpoMinLongitude(Double scenicSpoMinLongitude) {
        this.scenicSpoMinLongitude = scenicSpoMinLongitude;
    }

    public Double getScenicSpotMaxLatitude() {
        return scenicSpotMaxLatitude;
    }

    public void setScenicSpotMaxLatitude(Double scenicSpotMaxLatitude) {
        this.scenicSpotMaxLatitude = scenicSpotMaxLatitude;
    }

    public Double getScenicSpotMaxLongitude() {
        return scenicSpotMaxLongitude;
    }

    public void setScenicSpotMaxLongitude(Double scenicSpotMaxLongitude) {
        this.scenicSpotMaxLongitude = scenicSpotMaxLongitude;
    }

    public Double getScenicSpotMinLatitude() {
        return scenicSpotMinLatitude;
    }

    public void setScenicSpotMinLatitude(Double scenicSpotMinLatitude) {
        this.scenicSpotMinLatitude = scenicSpotMinLatitude;
    }

    public String getScenicSpotName() {
        return scenicSpotName;
    }

    public void setScenicSpotName(String scenicSpotName) {
        this.scenicSpotName = scenicSpotName;
    }

    public Integer getScenicSpotNo() {
        return scenicSpotNo;
    }

    public void setScenicSpotNo(Integer scenicSpotNo) {
        this.scenicSpotNo = scenicSpotNo;
    }

    public String getScenicSpotShortInfo() {
        return scenicSpotShortInfo;
    }

    public void setScenicSpotShortInfo(String scenicSpotShortInfo) {
        this.scenicSpotShortInfo = scenicSpotShortInfo;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
