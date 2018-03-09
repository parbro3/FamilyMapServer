package Data;

/**
 * Created by Parker on 3/7/18.
 */

public class Location {
    String country;
    String city;
    String latitude;
    String longitude;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}


/*

    {
        "country": "Denmark",
        "city": "Nord",
        "latitude": "81.6000",
        "longitude": "-15.3333"
    },
 */