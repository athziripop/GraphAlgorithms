import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BFS {
    private Queue<String> queue;
    private List<String> discoveredWebSiteList;

    public BFS() {
        this.queue = new LinkedList<>();
        this.discoveredWebSiteList = new ArrayList<>();
    }

    public void discoverWeb(String root){
        this.queue.add(root);
        this.discoveredWebSiteList.add(root);

        while (!this.queue.isEmpty()){
            String actual = queue.remove();
            String rawHtml = readUrl(actual);

            String regex = "((http|https)://)(www.)?" + "[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]" + "{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(rawHtml);

            while (matcher.find()){
                String w = matcher.group();
                if(!discoveredWebSiteList.contains(w)){
                    discoveredWebSiteList.add(w);
                    System.out.println("Website found: " + w);
                    queue.add(w);
                }
            }
        }
    }

    public String readUrl(String site){
        StringBuilder rawHtml = new StringBuilder("");

        try{
            URL url = new URL(site);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";

            while((line = reader.readLine()) != null){
                rawHtml.append(line);
            }

            reader.close();
        }catch (Exception e){
            System.out.println("Problem with the site...");
        }
        return rawHtml.toString();
    }
}
