/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author yuril
 */
public class Principal {

    private static final String webSiteURL = "https://www.google.com/search?sxsrf=ACYBGNTIwa2-B0LxQV5oMwa0VAYA5bEARg%3A1578430027198&ei=S-4UXtrkC7W65OUPm-e-8Ac&q=tempo&oq=tempo&gs_l=psy-ab.3..0i71l8.3266.3266..3498...0.2..0.0.0.......0....1..gws-wiz.CElWLSNQ_Vw&ved=0ahUKEwiatsuIrvLmAhU1HbkGHZuzD34Q4dUDCAs&uact=5";
    //private static final String webSiteURL = "//ssl.gstatic.com/onebox/weather/64";
    //The path of the folder that you want to save the images to. Ex: "C:\\Program Files\\"
    private static final String folderPath = "Path_Here";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            //Connect to the website and get the html
            Document doc = Jsoup.connect(webSiteURL).get();

            //Get all elements with img tag ,
            Elements img = doc.getElementsByTag("img");

            for (Element el : img) {

                //for each element get the srs url
                String src = el.absUrl("src");
                //System.out.println(src);
                if (src.isEmpty()) {

                } else if (src.startsWith("https://ssl.gstatic.com/onebox/weather/64/")) {
                    System.out.println("Image Found!");
                    System.out.println("src attribute is : " + src);

                    getImages(src);
                } else {

                }

            }

        } catch (IOException ex) {
            System.err.println("There was an error");
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void getImages(String src) throws IOException {

        //String folder = null;
        //Exctract the name of the image from the src attribute
        int indexname = src.lastIndexOf("/");

        if (indexname == src.length()) {
            src = src.substring(1, indexname);
        }

        indexname = src.lastIndexOf("/");
        String name = src.substring(indexname, src.length());

        System.out.println(name);

        //Open a URL Stream
        URL url = new URL(src);
        try (InputStream in = url.openStream(); OutputStream out = new BufferedOutputStream(new FileOutputStream(folderPath + name))) {

            for (int b; (b = in.read()) != -1;) {
                out.write(b);
            }
        }

    }

    /*try {
            Document doc = Jsoup.connect("https://www.google.com/search?sxsrf=ACYBGNTIwa2-B0LxQV5oMwa0VAYA5bEARg%3A1578430027198&ei=S-4UXtrkC7W65OUPm-e-8Ac&q=tempo&oq=tempo&gs_l=psy-ab.3..0i71l8.3266.3266..3498...0.2..0.0.0.......0....1..gws-wiz.CElWLSNQ_Vw&ved=0ahUKEwiatsuIrvLmAhU1HbkGHZuzD34Q4dUDCAs&uact=5").get();

            Elements imgs = doc.getElementsByTag("img");
            int i = 0;
            for (Element src : imgs) {
                URL url = new URL(src.attr("abs:src"));

                try (InputStream in = url.openStream(); OutputStream out = new BufferedOutputStream(new FileOutputStream(i + ".png"))) {

                    for (int g; (g = in.read()) != -1;) {
                        out.write(g);
                    }

                }
                i++;
                System.out.println("src." + src.attr("abs:src"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/
}
