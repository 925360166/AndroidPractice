// IBookManager.aidl
//第二类AIDL文件
//作用是定义方法接口
package com.example.songwei.androidpractice.AIDL;


// Declare any non-default types here with import statements
//导入所需要使用的非默认支持的数据类型的包
import com.example.songwei.androidpractice.AIDL.Book;

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */


     //所有的返回值前都不需要加任何东西，不管是什么数据类型
     List<Book> getBooks();

     //传参时除了java基本类型以及String、CharSequence之外的类型
     //都需要在前面加上定向Tag，具体加什么量需而定
    void addBook(in Book book);
}
