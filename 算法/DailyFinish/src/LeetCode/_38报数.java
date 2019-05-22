package LeetCode;


public class _38报数 {
    public static String countAndSay(int n) {
        if(n==1)
            return "1";
        String s = "1"; //刚开始
        int i=1;
        while(1<n){
            s = getStr(s);
            i++;
        }
        return s;
    }
    /*111221 -> 312211 */
    public static String getStr(String str){
        char prec = 0;
        int count = 0;
        StringBuilder bs = new StringBuilder();
        for(int i=0; i<str.length(); i++){
            char nowc = str.charAt(i);
            if(prec==0){
                count++;
                prec = nowc;
                if(i==str.length() - 1)//如果是最后一个数
                    bs.append(count+""+nowc);
            }else{
                if(prec==nowc){
                    count++;
                    if(i==str.length()-1)
                        bs.append(count+""+nowc);
                }else{
                    prec=0;
                    --i;//返回
                    bs.append(count+""+str.charAt(i));
                    count=0; //归0
                }
            }
        }
        return bs.toString();
    }
    public static void main(String[] args){
        /**
         *  报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
         * 1.     1
         * 2.     11
         * 3.     21
         * 4.     1211
         * 5.     111221
         * */
        String s1 = "12345";
        String s2 = "34";
        int i = s1.indexOf(s2);
        System.out.println(i);
    }
}
