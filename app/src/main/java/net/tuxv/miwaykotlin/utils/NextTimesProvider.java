package net.tuxv.miwaykotlin.utils;

import android.util.Log;

import net.tuxv.miwaykotlin.models.Time;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NextTimesProvider {

    private static final String TAG = "NextTimesProvider";
    private static final String baseUrl = "http://m.miway.ca/";

    public static ArrayList<Time> getNextPassingTimes(String route, String dir, String stop) {
        ArrayList<Time> timeSet = new ArrayList<>();
        try {
            Log.d(TAG, "Scraping " + route + " " + dir + " at " + stop);


            String trigger = "The next bus is scheduled to depart at:";
            String endTrigger = "see full schedule";
            boolean triggerStatus = false;

            String urlSuffix = "nextPassingTimes.jsp?sId=" + stop + "&id=" + route + "_" + dir;
            String url = baseUrl + urlSuffix;
            Log.d(TAG, "url: " + url);

            // TODO(yasith): timeout shouldn't be 0
            Document doc = Jsoup.connect(url).timeout(0).get();
            Elements times = doc.getElementsByTag("td");

            // When we find the trigger, all following <td> contains bus times. Until we find the
            // endTrigger, then we exit the loop, and return the bus times we found.
            for (Element td : times) {
                String text = td.text();

                if (text.contains(trigger)) {
                    triggerStatus = true;
                } else if (triggerStatus) {
                    if (text.contains(endTrigger)) {
                        break;
                    }

                    // If we arrive here we can extract bus times.
                    timeSet.addAll(extractTimes(text));
                }
            }
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());

        }

        return timeSet;
    }

    private static TreeSet<Time> extractTimes(String text) {
        TreeSet<Time> timeSet = new TreeSet<Time>();
        if (text.contains("AM") || text.contains("PM")) {

            Log.d(TAG, text);
            String slots[] = text.split(" ");

            // Go through the split text, find times, and convert to Time
            // objects. Add them to a TreeSet
            for(int i = 0; i < slots.length; i++) {
                String s = slots[i];
                Log.d(TAG + "SLOT", s);
                if (s.contains("AM") || s.contains("PM")) {
                    int colonPos = s.indexOf(':');
                    int apmPos = s.indexOf('M') - 1;

                    String hourStr = s.substring(0, colonPos);
                    String minuteStr = s.substring(colonPos + 1, apmPos);
                    String apmStr = s.substring(apmPos, apmPos + 2);

                    int hour = Integer.parseInt(hourStr);
                    int minute = Integer.parseInt(minuteStr);

                    Time t = new Time(hour, minute, apmStr);

                    if(i+1 < slots.length) {
                        String next_token = slots[i+1];
                        Pattern p = Pattern.compile("\\(.?\\)");
                        Matcher m = p.matcher(next_token);
                        if(m.matches()) {
                            // TODO(yasith) : Complete tags separately
                            //t.setSpecialRoute(true);
                            // t.setSpecialRouteStr(next_token);
                        }
                    }

                    timeSet.add(t);
                }
            }
        }

        return timeSet;
    }
}