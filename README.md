# aw06


[Amazon Review Data (2018)](https://nijianmo.github.io/amazon/index.html) has a huge products metadata set of multiple categories.

|category| reviews | metadata |
|--| -- | -- |
|Amazon Fashion|reviews (883,636 reviews)|metadata (186,637 products)|
|All Beauty|reviews (371,345 reviews)|metadata (32,992 products)|
|Appliances|reviews (602,777 reviews)|metadata (30,459 products)|
| ... |
|Tools and Home Improvement|reviews (9,015,203 reviews)|metadata (571,982 products)|
Toys and Games|reviews (8,201,231 reviews)|metadata (634,414 products)|
Video Games|reviews (2,565,349 reviews)|metadata (84,893 products)|

Please finish the following tasks:

- Download no less than two categories of these metadata.
- Referring the example code in this repo, convert each line in the downloaded files into a POJO of `Product` class and save the object in a database like MySQL. 
- Integrate the database containing Amazon products with your own AW04 project and build an Amazon WebPOS system.


And, of course, always try to make the system run as fast as possible.

## 说明
当前程序通过分块方式处理项目testdata目录下的json文件（由于这些文件都相当大，因此这些文件不会上传github）

基于当前机器性能，线程池设定为6线程。同时考虑到数据集特点：所有单个文件都相当大，所以分块部分仍采用默认设置下的 `SimpleAsyncTaskExecutor`类。

我们注意到在下载下来的数据集中，有一些信息是缺失的，但我们在这里不做处理，处理放在aw04之中

数据库查询：```select * from `amazon-products`.products```

现在 已经可以将json中的内容高效的写入mysql中了，下一步的任务将是在aw04上拓展原有的实现。

一些已知的问题，这里的数据集其实并不是很完美，有不少数据相事实上是缺失的，这里没有做太多的处理，问题将被留到aw04拓展中完成。

有时一部分内容会被重复写入mysql，可能的解决方案是将asin设为唯一值，但是这个操作过于费时了，基于效率考虑不采用这个方案。