public class mt {
    public static String standardize(String s) {
        s = s.trim();
        s = s.replaceAll(" \\s+ ", " ");
        //String sub_string = s.substring(1, s.length() - 1).toLowerCase();
        s = s.toLowerCase();
        s = s.substring(0,1).toUpperCase() + s.substring(1, s.length());

        return s;


    }
    public static void main(String[] args) {
        // 1st: trim string

        System.out.println(standardize("  heLKDlo     world  "));

    }
}
