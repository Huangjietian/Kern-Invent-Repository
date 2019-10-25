# 文件格式校验
通过文件头检验文件格式。

# 一个简单的示例

FileInputStream stream = new FileInputStream(filepath);

该方法将返回一个简单的校验结果，包括 pass:Boolean , targetFormat:FileFormatEnum , correctFormat:FileFormatEnum
FormatChecker checker = FileFormatCensor.check(FileFormatEnum.PNG, stream);

该方法在校验通过时将什么也不发生，校验不通过时将抛出一个默认的异常
FileFormatCensor.checkE(FileFormatEnum.JPEG, stream);

### 需要注意的一点是，输入流将在一次校验后关闭，这样做主要是为了防止异常发生时流没有得到正确的关闭。 需要注意再次获取输入流。
