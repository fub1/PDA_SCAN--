# 每一VDA标签扫码业务逻辑

- step1 VDA MAT
- step2 CMS MAT
- step3 VDA PKG
- function1 提交
- function2 回退一步
- function3 重新扫码

# 每一VDA标签扫码提交
- 提交数据保存到数据层：VDA MAT、CMS MAT、VDA PKG、提交时间日期、任务号
- Room实现数据源，完成对应的Data Layer代码，包括Model、Repository、Dao、Database等
- 数据层保存的扫码数据显示LazyColumn中，完成对应的UI代码，包括ViewModel、View等
- LazyColumn每一条数据均需要提供删除按钮
- 删除按钮点击后，需提示用户是否删除，确认后删除对应的数据