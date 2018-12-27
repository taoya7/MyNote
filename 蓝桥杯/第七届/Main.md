#### 1. 有奖猜谜

小明很喜欢猜谜语。
最近，他被邀请参加了X星球的猜谜活动。

每位选手开始的时候都被发给777个电子币。
规则是：猜对了，手里的电子币数目翻倍，
猜错了，扣除555个电子币, 扣完为止。

小明一共猜了15条谜语。
战果为：vxvxvxvxvxvxvvx
其中v表示猜对了，x表示猜错了。

请你计算一下，小明最后手里的电子币数目是多少。

请填写表示最后电子币数目的数字。
注意：你提交的应该是一个整数，不要填写任何多余的内容或说明性文字。

```java
public class Test1 {
	public static void main(String[] args) {
		

		String str = "vxvxvxvxvxvxvvx";
		
		int score = 0; //存储成绩
		/*
			步骤：
				1. 循环 -》看每一次是对还是错
				2. 判断 -》 字符串比较
		*/
		for(int i=0; i<str.length(); i++) {
			String a = String.valueOf(str.charAt(i));
			if(a.equals("v")) {
				score += 777;
			}else {
				score -= 555;
			}
		}
		
		System.out.println(score);
	}
}
```

**总结**

1. `char charAt(int index)`  返回 char指定索引处的值
2. char类形转换string `String.valueOf()`
3. 字符串比较`str.equals()`

#### 2. 煤球数目


有一堆煤球，堆成三角棱锥形。具体：
第一层放1个，
第二层3个（排列成三角形），
第三层6个（排列成三角形），
第四层10个（排列成三角形），
....
如果一共有100层，共有多少个煤球？

请填表示煤球总数目的数字。
注意：你提交的应该是一个整数，不要填写任何多余的内容或说明性文字。

```java
public class Test2 {
  public static void main(String[] args) {
	  int x = 0;
	  int sum = 0;
	  
	  for(int i=1; i<100; i++) {
		  sum += math(i);
	  }
	  System.out.println(sum);
	  
  }
  
  public static int math(int x) {
	  int y = x*(x+1)/2;
	  return y;
  }
}
```

**总结**

- 公式 `y = x*(x+1)/2`

- 等差数列求和 `n(a1+an)/2`
	- a1 第一个数
	- an 最后一个属
	- n 数列长度

- 等比数列求和



#### 3. 平方怪圈

如果把一个正整数的每一位都平方后再求和，得到一个新的正整数。
对新产生的正整数再做同样的处理。

如此一来，你会发现，不管开始取的是什么数字，
最终如果不是落入1，就是落入同一个循环圈。

请写出这个循环圈中最大的那个数字。

请填写该最大数字。
注意：你提交的应该是一个整数，不要填写任何多余的内容或说明性文字。

```java
public class Test3 {
	public static void main(String[] args) {
		f1(3);
	}
	public static void f1(int num) {
		int temp = 0;
		int sum = 0;
		while(num>0) {
			temp = num%10;
			sum += temp*temp;
			num /= 10;
		}
		System.out.println(sum);
		f1(sum);
	}
```


#### 4. 骰子游戏


我们来玩一个游戏。
同时掷出3个普通骰子（6个面上的数字分别是1~6）。
如果其中一个骰子上的数字等于另外两个的和，你就赢了。
下面的程序计算出你能获胜的精确概率（以既约分数表示）
```java
public class Test4{
	public static int gcd(int a, int b)
	{
		if(b==0) return a;
		return gcd(b,a%b);
	}
	
	public static void main(String[] args)
	{	
		int n = 0;
		for(int i=0; i<6; i++)
		for(int j=0; j<6; j++)
		for(int k=0; k<6; k++){
			if(i==j+k|| j==i+k || k==i+j) n++;   //填空位置
		}
		
		int m = gcd(n,6*6*6);
		System.out.println(n/m + "/" + 6*6*6/m);
	}
}
```

