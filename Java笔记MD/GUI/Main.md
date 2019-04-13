### 布局

- FlowLayout
- BorderLaayout
- GridLayout
- CardLayout
- GridBagLayout



**流程**

1. 创建frame窗体
2. 对窗体进行简单设置（大小 位置 布局）
3. 定义组件
4. 将组建通过窗体add方法添加到窗体中
5. 让窗体显示`setVisible(true)`

### 事件监听机制

- 事件源（组件）
- 事件（Event）
- 监听器（Listener）
- 事件处理（引发事件后处理方式）

按钮 点击 按钮监听 点击处理

```java

package Unit1;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
	public static void main(String[] args) {
		Frame fr = new Frame();
		fr.setSize(400, 400); //设置大小
		fr.setLocation(400, 200);//设置距离
		fr.setLayout(new FlowLayout()); //设置布局
		fr.add(new Button("Click!"));

		fr.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		/*事件处理*/
		fr.setVisible(true);
	}
}

```





