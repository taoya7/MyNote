## TweenMax

### 动画结构

- `TweenMax( target:Object, duration:Number, vars:Object) `

  添加一个动画对象

  动画持续时间

  动画参数

- `TweenMax.to( target:Object, duration:Number, vars:Object )`

  也可以对多个目标进行动画

- `TweenMax.from( target:Object, duration:Number, vars:Object )`

  设置起始的位置

- `TweenMax.fromTo( target:Object, duration:Number, fromVars:Object, toVars:Object )`

  设置动画起始点与结束点

- `TweenMax.staggerTo( targets:Array, duration:Number, vars:Object, stagger:Number, onCompleteAll:Function, onCompleteAllParams:Array, onCompleteAllScope:* ) `

  | 参数名              | 类型     | 是否必填 | 描述                                               |
  | :------------------ | :------- | :------- | :------------------------------------------------- |
  | targets             | Array    | 是       | 要进行动画的对象，可以有多个，以数组形式传入       |
  | duration            | number   | 是       | 动画持续的秒数（或帧数，如果设置了useFrames:true） |
  | vars                | object   | 是       | 设置动画的一些属性及其他参数                       |
  | stagger             | Number   | 否       | 每个动画的起始间隔，默认是0                        |
  | onCompleteAll       | Function | 否       | 当所有显示对象都完成动画后要调用的函数             |
  | onCompleteAllParams | Array    | 否       | onCompleteAll函数的参数，以数组形式传入            |
  | onCompleteAllScope  |          | 否       | onCompleteAll函数的作用域，this                    |

- `TweenMax.delayedCall( delay:Number, callback:Function, params:Array, scope:*, useFrames:Boolean )`

- `TweenMax.set( target:Object, vars:Object )`

  立即设置目标的属性值而不产生过渡动画，相当于0的动画时间

### 





