# 3. 巧妙高效的双指针

严格意义来说，双指针并不是一种算法，但是它反而是算法面试题目中最常出现的一种思路。双指针可以轻松处理很多数组和字符串的题目。数组是我们大多数人刚开始学习编程时所接触的第一种线性数据结构，而且也是数据结构与算法知识的基础。字符串和数组一样，只不过一个是 `int[]` 存数字的数组，一个是 `char[]` 存字符的数组。对于字符串的题目我们往往可以通过 `toCharArray()` API 将字符串转变成数组来进行计算，避免调用昂贵的 String API。

### 总结

双指针一般有三种形式：
- 同向双指针：同向双指针用于解决链表查环，数组和字符串修改操作，去重操作等。
- 相向双指针：相向双指针用于解决 2 Sum 类型题目和翻转类题目。
- 背向双指针：背向双指针用于从某一个位置出发进行双向扩展寻找最后结果。

除此之外，有另外两种高级的双指针用法：
- 滑动窗口：一种高级的同向双指针方法，可以解决很多困难的字符串子串类题目。
- Partition：一种高级的相向双指针方法，通过维持挡板区域进行 Partition 操作。

双指针可以进一步泛化推广到 K 个指针的用法，可以处理一系列多个 input 的问题，如 K 路归并等。

### [1. 从 2 sum 开始带你解决 K sum 所有变种](3_1_two_sum.md)

* 2 Sum - Unsorted/Sorted
* 2 Sum - Greater/Smaller/Closest/Difference
* 2 Sum - All Pairs/All Unique Pairs/Data Structure Design
* 3 Sum - Greater/Closest/Smaller/All Unique Pairs
* 4 Sum
* K Sum

### TODO:
* [ ] Slow-Fast Pointers
* [ ] K-way Merge
* [ ] Deduplication
* [ ] Sliding Window
* [ ] Partition
* [ ] Backwards Pointers





