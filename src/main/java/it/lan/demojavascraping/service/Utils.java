package it.lan.demojavascraping.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Utils {
    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    public void readIpPort() throws IOException{
        try {
            File myObj = new File("/Users/marcolanfranchi/Downloads/http_proxies.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(":");

                String res = getMyIp(parts[0], parts[1]);
                logger.info(res);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void sendPingRequest() throws IOException {
        String ipAddress = "";
        try {
            File myObj = new File("/Users/marcolanfranchi/Downloads/http_proxies.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(":");
                //System.out.println(data);
                //String result;
                InetAddress geek = InetAddress.getByName(parts[0]);

                System.out.println("Sending Ping Request to " + parts[0]);
                if (geek.isReachable(5000)) {
                    parts[0] = "Host is reachable";
                    System.out.println(parts[0]);
                }
                else {
                    parts[0] = "Sorry ! We can't reach to this host";
                    System.out.println(parts[0]);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String getMyIp(String hostname, String port) throws IOException {

        URL weburl = new URL("https://www.myip.com/");
        Proxy webProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostname, Integer.parseInt(port)));
        // HttpURLConnection webProxyConnection = (HttpURLConnection) weburl.openConnection(webProxy);

        try{
            Document webPage = Jsoup
                    .connect(weburl.toString())
                    .proxy(webProxy)
                    .timeout(10000)
                    .get();

            Element ip = webPage.getElementById("ip");
            // logger.info(ip.text());

            return ip.text();
        } catch (IOException e) {
            String res = "Eccezione: "+hostname+" "+port;
            return res;
        }
    }

    public String getMyIpNuovo(String hostname, String port) throws IOException {

        URL weburl = new URL("http://httpbin.org/ip");
        Proxy webProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostname, Integer.parseInt(port)));
        // HttpURLConnection webProxyConnection = (HttpURLConnection) weburl.openConnection(webProxy);

        try{
            Document webPage = Jsoup
                    .connect(weburl.toString())
                    .proxy(webProxy)
                    .get();

            Element ip = webPage.getElementsByTag("pre").get(0);
            // logger.info(ip.text());

            return ip.text();
        } catch (IOException e) {
            String res = "Eccezione: "+hostname+" "+port;
            return res;
        }
    }

    public boolean isReachable(String addr, int openPort) {
        try {
            Socket soc = new Socket();
            soc.connect(new InetSocketAddress(addr, openPort), 10000);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public String request(String url) {
        try{
            Document webPage = Jsoup
                    .connect(url)
                    .get();
            Element ip = webPage.getElementsByTag("pre").get(0);
            return ip.text();

        } catch (IOException e) {
            return "Eccezione";
        }
    }
}
