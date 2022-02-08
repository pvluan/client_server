public class test {

    public static String string_check(String s){
        String s2 = null;
        if (s.substring(0,5).equals("echo\"")){
            s2 = s.substring(5, s.length()-1);
           // return "aaaa";
        }
        else if (s.substring(0,12).equals("standardize\"")){
            s2 = s.substring(12, s.length()-1).toUpperCase();
        }
        return s2;
    }

    public static void main(String[] args) {
        //string_check("echo\"luan\"");
        String msg = "echo\"luanrrrrr\"";
        String msg2 = "standardize\"luandeptraivcl\"";
        System.out.println(msg);
        System.out.println(string_check(msg2));
    }
}
