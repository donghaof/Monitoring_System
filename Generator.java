import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Generator {
    public static void main(String[] args) {
        String path = args[0];
        long stamp = 0;
        try {
            Date currentDate = new SimpleDateFormat("yyyyMMdd").parse("20141031");
            //get time by seconds
            stamp = currentDate.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Random rd = new Random();
        StringBuilder sb = new StringBuilder();
        int day = 24 * 60 * 60;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            //generate 1000 servers records for one day
            for (int i = 0; i < day; i = i + 60) {
                for (int i2 = 0; i2 < 250; i2++) {
                    sb.append(stamp + i).append(" 192.168.0.").append(i2).append(" 0 ").append(rd.nextInt(100)).append("\n");
                    sb.append(stamp + i).append(" 192.168.0.").append(i2).append(" 1 ").append(rd.nextInt(100)).append("\n");
                }
                for (int i2 = 0; i2 < 250; i2++) {
                    sb.append(stamp + i).append(" 192.168.1.").append(i2).append(" 0 ").append(rd.nextInt(100)).append("\n");
                    sb.append(stamp + i).append(" 192.168.1.").append(i2).append(" 1 ").append(rd.nextInt(100)).append("\n");
                }
                for (int i2 = 0; i2 < 250; i2++) {
                    sb.append(stamp + i).append(" 192.168.2.").append(i2).append(" 0 ").append(rd.nextInt(100)).append("\n");
                    sb.append(stamp + i).append(" 192.168.2.").append(i2).append(" 1 ").append(rd.nextInt(100)).append("\n");
                }
                for (int i2 = 0; i2 < 250; i2++) {
                    sb.append(stamp + i).append(" 192.168.3.").append(i2).append(" 0 ").append(rd.nextInt(100)).append("\n");
                    sb.append(stamp + i).append(" 192.168.3.").append(i2).append(" 1 ").append(rd.nextInt(100)).append("\n");
                }
                writer.write(sb.toString());
                writer.flush();
                sb.setLength(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
