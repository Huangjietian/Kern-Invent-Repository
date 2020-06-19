# context 内容的代码结构优化
## TabulationConfiguration成员结构
- ColumnDefinitions: List
- BannerDefinitions: List

## 分离构造和使用的必要性？
可以分离。

## 动态修改内容

Tabulation
- 修改Banners
- 修改Columns
- 修改有效行数
- 修改起始行
- 修改表头行高
- 修改表体行高
- 修改最大列宽
- 修改最小列宽

Column
- 修改列表头
- 修改列宽
- 修改单元格格式表达式
- 修改函数
- 修改表头风格
- 修改表体风格

Banner
- 修改风格
- 修改范围
- 修改内容
- 修改行高

# XML配置的可能性
怎么转换为Bean？只能写模板，意义不大