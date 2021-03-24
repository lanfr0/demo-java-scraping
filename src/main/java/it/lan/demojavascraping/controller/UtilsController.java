package it.lan.demojavascraping.controller;

import it.lan.demojavascraping.service.Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/utils/v1")
public class UtilsController {
    private final Utils utils = new Utils();

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public void ping(@RequestParam(name="ip") String ip) throws IOException {
        utils.sendPingRequest();
    }

    @RequestMapping(value = "/getmyip", method = RequestMethod.GET)
    public String getMyIp(@RequestParam(name="hostname") String hostname, @RequestParam(name="port") String port) throws IOException {
        return utils.getMyIp(hostname, port);
    }

    @RequestMapping(value = "/get-my-ip-nuovo", method = RequestMethod.GET)
    public String getMyIpNuovo(@RequestParam(name="hostname") String hostname, @RequestParam(name="port") String port) throws IOException {
        return utils.getMyIpNuovo(hostname, port);
    }

    @RequestMapping(value = "/isreachable", method = RequestMethod.GET)
    public Boolean isReachable(@RequestParam(name="hostname") String hostname, @RequestParam(name="port") int port) throws IOException {
        return utils.isReachable(hostname, port);
    }

    @RequestMapping(value = "/read-ip-port", method = RequestMethod.GET)
    public void readIpPort() throws IOException {
        utils.readIpPort();
    }

    @RequestMapping(value = "/request", method = RequestMethod.GET)
    public String request(@RequestParam(name="url") String url) {
        return utils.request(url);
    }
}
