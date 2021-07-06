import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Parser {

    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        //The key of map is the combination of IP and CPU ID, the value of map is a list of usage
        try (BufferedReader br = Files.newBufferedReader(Paths.get(args[0]), StandardCharsets.UTF_8)) {
            //Loop all the lines in the DATA_PATH file
            for (String line; (line = br.readLine()) != null; ) {
                String[] dd = line.split(" ");
                String k = dd[1] + " " + dd[2];
                if (!map.containsKey(k)) {
                    map.put(k, new ArrayList<>());
                }
                map.get(k).add(dd[3]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            String[] a = sc.nextLine().split(" ");
            switch (a[0].toLowerCase(Locale.ROOT)) {
                case "query":
                    //>QUERY 192.168.1.10 1 2014-10-31 00:00 2014-10-31 00:05
                    String key = a[1] + " " + a[2];
                    long stamp = 0;
                    try {
                        Date currentDate = new SimpleDateFormat("yyyyMMdd").parse("20141031");
                        stamp = currentDate.getTime() / 1000;
                        SimpleDateFormat formatter = null;
                        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
                        Date date1 = formatter.parse(a[3] + " " + a[4]);
                        Date date2 = formatter.parse(a[5] + " " + a[6]);
                        //Record the begin time
                        long stamp1 = (date1.getTime() / 1000);
                        //Record the end time
                        long stamp2 = (date2.getTime() / 1000);
                        System.out.println("CPU" + a[2] + " usage on " + a[1] + ":");
                        //Print the usage of begin time, we use the (stamp1 - stamp) / 60, which is minitues,
                        // to represent the index of list
                        System.out.print("(" + a[3] + " " + a[4] + ", " + map.get(key).get((int) (stamp1 - stamp) / 60) + "%)");
                        //Loop every time with the same ip and CPU ID between stamp1 and stamp2
                        for (long i = stamp1 + 60; i <= stamp2; i = i + 60) {
                            Date time = new Date(i * 1000);
                            String dt = formatter.format(time);
                            int yh = (int) ((i - stamp) / 60);
                            System.out.print(", (" + dt + ", " + map.get(key).get(yh) + "%)");
                        }
                        System.out.println();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "exit":
                    flag = false;
                    break;
                default:
                    System.out.println("Undefined command");
                    break;
            }
        }
        System.exit(0);
    }
}
