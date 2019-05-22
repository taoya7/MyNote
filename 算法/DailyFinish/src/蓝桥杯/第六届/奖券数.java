package 蓝桥杯.第六届;

public class 奖券数 {
    public static void main(String[] args){
        /**
         *  有些人很迷信数字，比如带“4”的数字，认为和“死”谐音，就觉得不吉利。
         *
         * 虽然这些说法纯属无稽之谈，但有时还要迎合大众的需求。某抽奖活动的奖券号码是5位数（10000-99999），
         *
         * 要求其中不要出现带“4”的号码，主办单位请你计算一下，如果任何两张奖券不重号，最多可发出奖券多少张。
         * */

        int count = 0;

        for(int i=10000; i<=99999; i++){
            String str = new Integer(i).toString();
            boolean flag = true;
            for(int j=0; j<str.length(); j++){
                char ch = str.charAt(j);
                if(ch == '4'){
                    flag = false;
                }
            }
            if(flag){
                count++;
            }
        }
        System.out.println(count);
    }
}
