package 蓝桥杯.第九届;

public class Pass4_测试次数 {
    /**
     *  x 星球的居民脾气不太好，但好在他们生气的时候唯一的异常举动是：摔手机。
     * 各大厂商也就纷纷推出各种耐摔型手机。x 星球的质监局规定了手机必须经过耐摔测试，并且评定出一个耐摔指数来，之后才允许上市流通。
     *
     * x 星球有很多高耸入云的高塔，刚好可以用来做耐摔测试。塔的每一层高度都是一样的，与地球上稍有不同的是，他们的第一层不是地面，而是相当于我们的 2 楼。
     *
     * 如果手机从第 7 层扔下去没摔坏，但第 8 层摔坏了，则手机耐摔指数 = 7。
     * 特别地，如果手机从第 1 层扔下去就坏了，则耐摔指数 = 0。
     * 如果到了塔的最高层第 n 层扔没摔坏，则耐摔指数 = n
     *
     * 为了减少测试次数，从每个厂家抽样 3 部手机参加测试。
     *
     * 某次测试的塔高为 1000 层，如果我们总是采用最佳策略，在最坏的运气下最多需要测试多少次才能确定手机的耐摔指数呢？
     *
     * 请填写这个 return最多测试次数
     *
     * >>>解
     *  变量：共M楼层 N个手机 X层
     *  return 最多测试次数
     *  方式自底向上
     * */
    static int getMinSteps(int num, int floorNum) {
        if (num < 1 || floorNum < 1)
            return 0;

        // 备忘录，存储num个手机，floorNum层楼条件下的最优化尝试次数
        int[][] cache = new int[num + 1][floorNum + 1];
        // 把备忘录每个元素初始化成最大的尝试次数
        for (int i = 1; i <= num; i++)
            for (int j = 1; j <= floorNum; j++)
                cache[i][j] = j;

        for (int n = 2; n <= num; n++)
            for (int m = 1; m <= floorNum; m++)
                for (int k = 1; k < m; k++)
                    // 扔手机的楼层从1到m枚举一遍，如果当前算出的尝试次数小于上一次算出的尝试次数，则取代上一次的尝试次数。
                    cache[n][m] = Math.min(cache[n][m], 1 + Math.max(cache[n - 1][k - 1], cache[n][m - k]));

        return cache[num][floorNum];
    }
    public static void main(String[] args){
        System.out.println(getMinSteps(3,1000));
    }
}
