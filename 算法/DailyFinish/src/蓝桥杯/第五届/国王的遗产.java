package 蓝桥杯.第五届;

public class 国王的遗产 {

    public static void main(String[] args){
        /**
         *
         标题：国王的遗产

         X国是个小国。国王K有6个儿子。在临终前，K国王立下遗嘱：国王的一批牛作为遗产要分给他的6个儿子。
         其中，大儿子分1/4，二儿子1/5，三儿子1/6，....
         直到小儿子分1/9。
         牛是活的，不能把一头牛切开分。

         最后还剩下11头牛，分给管家。

         请计算国王这批遗产中一共有多少头牛。

         这是一个整数，请通过浏览器提交答案，不要填写任何多余的内容（比如说明性的文字）

         *
         * */

        int down=4*5*6*7*8*9;
        int up = down/4 + down/5 + down/6 + down/7 + down/8 + down/9;
        for(double i=2500; i<5000; i++){
            if(i-(i*up)/down == 11){
                System.out.println(i);
            }
        }
    }
}
