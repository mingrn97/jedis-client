package com.mingrn.common.redis;

import com.mingrn.common.redis.client.RedisGeoClient;
import com.mingrn.common.redis.client.RedisStringClient;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoUnit;

import java.util.Set;

@RestController
@RequestMapping
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(WebApplicationType.SERVLET).run(args);
    }

    @GetMapping("/geoGet")
    public String geoGet() {
        GeoCoordinate geoCoordinate = RedisGeoClient.getInstance().geoPos("citys:location", "shanghai");
        return geoCoordinate.toString();
    }

    @GetMapping("/geoSet")
    public Long geoSet() {
        return RedisGeoClient.getInstance().geoAdd("citys:location", 116.4071700000, 39.9046900000, "beijing");
    }

    @GetMapping("/geoMembers")
    public Set<String> geoMembers() {
        return RedisGeoClient.getInstance().geoMembers("citys:location", 0, -1);
    }

    @GetMapping("/geoDist")
    public Double geoDist() {
        Double d = RedisGeoClient.getInstance().geoDist("citys:location", "beijing", "shanghai", GeoUnit.M);
        System.out.println(d);
        return d;
    }

    @GetMapping("/set")
    public Boolean set() {
        return RedisStringClient.getInstance().set("key", "valvalvalval", false);
    }

    @GetMapping("/get")
    public String get() {
        return RedisStringClient.getInstance().get("key");
    }


    @GetMapping("/getAndSetNewVal")
    public String getAndSetNewVal() {
        return RedisStringClient.getInstance().getAndSetNewVal("key", "newVal");
    }

    @GetMapping("/setExistOrNot")
    public boolean setExistOrNot(@RequestParam String key,
                                 @RequestParam String val,
                                 @RequestParam Boolean existOrNot) {
        return RedisStringClient.getInstance().setExistOrNot(key, val, existOrNot, false);
    }

    @GetMapping("/setAndNotExist")
    public boolean setAndNotExist(@RequestParam String key,
                                  @RequestParam String val) {
        return RedisStringClient.getInstance().setAndNotExist(key, val, false);
    }

    @GetMapping("/setExpireAtSeconds")
    public boolean setExpireAtSeconds(@RequestParam String key,
                                      @RequestParam String val,
                                      @RequestParam Integer seconds) {
        return RedisStringClient.getInstance().setExpireAtSeconds(key, val, seconds, false);
    }

    @GetMapping("/setExpireAtSecondsExistOrNot")
    public boolean setExpireAtSecondsExistOrNot(@RequestParam String key,
                                                @RequestParam String val,
                                                @RequestParam Integer seconds,
                                                @RequestParam Boolean existOrNot) {
        return RedisStringClient.getInstance().setExpireAtSeconds(key, val, seconds, false, existOrNot);
    }

    @GetMapping("/setExpireAtMillis")
    public boolean setExpireAtMillis(@RequestParam String key,
                                     @RequestParam String val,
                                     @RequestParam Long seconds,
                                     @RequestParam Boolean existOrNot) {
        return RedisStringClient.getInstance().setExpireAtMillis(key, val, seconds, false, existOrNot);
    }

    @GetMapping("/getRange")
    public String getRange(@RequestParam String key,
                            @RequestParam Long startOffset,
                            @RequestParam Long endOffset) {
        return RedisStringClient.getInstance().getRange(key, startOffset, endOffset);
    }

}