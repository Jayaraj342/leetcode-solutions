import java.util.*;

// Subject
interface Internet {
    void connectTo(String serverHost) throws Exception;
}

// Real subject
class RealInternet implements Internet {
    @Override
    public void connectTo(String serverHost) {
        System.out.println("Connected to : " + serverHost);
    }
}

// Proxy
class ProxyInternet implements Internet {
    private final Internet internet;
    private final List<String> bannedSites;

    ProxyInternet() {
        this.internet = new RealInternet();

        bannedSites = new ArrayList<>();
        bannedSites.add("abc.com");
        bannedSites.add("def.com");
        bannedSites.add("ijk.com");
        bannedSites.add("lnm.com");
    }

    @Override
    public void connectTo(String serverHost) throws Exception {
        if (bannedSites.contains(serverHost.toLowerCase())) {
            throw new Exception("Access denied to : " + serverHost);
        }

        internet.connectTo(serverHost);
    }

}

class Client {
    public static void main(String[] args) {
        Internet internet = new ProxyInternet();
        try {
            internet.connectTo("geeksforgeeks.com");
            internet.connectTo("abc.com");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
