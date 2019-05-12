# File

`public File(String pathname)` 设置完整路径

`File(File parent, String child) `从父抽象路径名和子路径名字符串创建新的 File实例

'atom-text-editor, webview.markdown-preview-plus':
  'ctrl-shift-M': 'markdown-preview-plus:toggle'
- 如果文件存在则删除否则创建
```java
public class Main {
	public static void main(String[] args) throws Exception {
		File file = new File("E:\\Tashi\\Desktop\\Code\\Java\\DailyCode\\10-文件\\Main.txt");
		if (file.exists()) {
			file.delete();
		} else {
			file.createNewFile();
		}
	}
}
```
- 创建多级目录
```java
public static void main(String[] args) throws Exception {
		/**
		 * 创建多级目录
		 * */
		File file = new File("C://1//2//3//5.txt");

		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		}
	}
```

```java
public static void main(String[] args) throws Exception {
		File file = new File("E:\\Photo\\1.jpg");

		if (file.exists()) {
			System.out.println("是否是文件" + "\t" + file.isFile());
			System.out.println("是否是目录" + "\t" + file.isDirectory());
			System.out.println("最近修改日期" + "\t" + file.lastModified());
			long len = file.length() / 1024 / 1024;

			System.out.println(
					new BigDecimal((double) len).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP) + "\tM");

		}
	}
```

- 目录
```java
public static void main(String[] args) throws Exception {
		File file = new File("c://");
		if (file.isDirectory()) {
			String res[] = file.list(); /*列出目录文件*/
			for (int i = 0; i < res.length; i++) {
				System.out.println(res[i]);
			}
		}
	}
```

**常用方法与字段**

`File.separator`系统文件分隔符

`.exists()`文件是否存在

`.isFile()`是否是文件

`.isDirectory()`是否是目录

`.mkdirs()`创建目录

`getParentFile()`找父路径


# 流

- 字节流
- 字符流

**Step**

1. 通过File类定一个操作文件的路径
2. 通过字节流子类对象为父类对象实例化
3. 进行输入输出操作
4. 关闭流


> ### 字节流

理解out与int。in代表把文件从存储器输出到屏幕上，out代表把信息写到存储器里

`InputStream`

`OutputStream`

- 字节输出
```java
public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\Tahi\\Desktop\\Main.txt");
		if (file.exists()) {
			FileOutputStream output = new FileOutputStream(file);
			String str = "Hello Friend";
			byte data[] = str.getBytes();
			output.write(data);
			//4 关闭管道
			output.close();
		}

	}
```
- 部分输出
```java
public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\Tahi\\Desktop\\Main.txt");
		if (file.exists()) {
			FileOutputStream output = new FileOutputStream(file);
			String str = "Hello Friend";
			byte data[] = str.getBytes();
			output.write(data, 2, 3);
			//4 关闭管道
			output.close();
		}

	}
```
- 追加
```java
public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\Tahi\\Desktop\\Main.txt");
		if (file.exists()) {
			FileOutputStream output = new FileOutputStream(file, true);
			String str = "Hello Friend";
			byte data[] = str.getBytes();
			output.write(data);
			//4 关闭管道
			output.close();
		}

	}
```
- 读取单个
- 将读取的字节存储字节数组里
- 将读取的字节部分存储字节数组里

```java
public static void main(String[] args) throws Exception {
	File file = new File("C:\\Users\\Tahi\\Desktop\\Main.txt");
	if (file.exists()) {
		FileInputStream in = new FileInputStream(file);

		byte data[] = new byte[1024];//准备杯子
		int read = in.read(data); //读取的字节长度
		in.close();
		System.out.println(new String(data));
	}
}
```

```java
public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\Tahi\\Desktop\\Main.txt");
		if (file.exists()) {
			FileInputStream in = new FileInputStream(file);

			byte data[] = new byte[1024];//准备杯子
			int read = in.read(data, 5, 12); //读取的字节长度

			int temp = 0;//每次接受的字节数据
			int foot = 0;//字符操作角标
			while ((temp = in.read()) != -1) {
				data[foot++] = (byte) temp;
			}

			in.close();

			System.out.println(new String(data));
		}
```

- writer
```java
public class Main {
	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\Tahi\\Desktop\\Main.txt");
		if (file.exists()) {
			FileWriter out = new FileWriter(file);
			String str = "好好学习";
			out.write(str);
			out.close();
		}
	}
}
```
- Reader
```java
public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\Tahi\\Desktop\\Main.txt");
		if (file.exists()) {
			FileReader in = new FileReader(file);
			char[] data = new char[1024];

			int read = in.read(data);
			System.out.println(new String(data));
		}
	}
```

> 字节流与字符流的区别

最大的区别是，字节流直接与终端进行交互，字符流需要将数据经过缓冲区输出

使用字符流没有关闭操作 缓冲区的内容不会输出到存储器里。可以强制使用`.flush()`强制清空缓冲区
