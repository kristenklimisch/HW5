package kklimisch_hw5;

public class MainTesting {
    public static void main (String[] args) {
        VideoObj a = new VideoObj("Avator", 2009, "Cameron");
        VideoObj b = new VideoObj("Board", 2020, "Avery");
        String c = "Avator";
        String d = "Board";
        System.out.println(c.compareTo(d));
    }

}
