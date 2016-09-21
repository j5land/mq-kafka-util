/**
 * Copyright (c) 2015, Shanghai World Fund Co.,Ltd All Rights Reserved.
 *
 * 包路径:cn.com.spider.mq.kafka.serializer.kryo
 *
 * 当前类名称:Node.java
 *
 * @author   wanguohui
 *  
 *    2015~2016 上海万丰文化传播有限公司-版权所有
 *
 */
package com.spider.util;

/**
 * Node节点类
 *
 * @author wanguohui
 *
 * 2015年11月10日 上午9:24:50
 *
 */
public class Node {

	private String nodeName;  
    
    public Node(String name) {  
        this.nodeName = name;  
    }  
      
    public String getName() {  
        return this.nodeName;  
    }  
      
    public String toString() {  
        return this.nodeName;  
    }  
      
    public boolean equals(Object obj) {  
        if (obj instanceof Node) {  
            return ((Node) obj).nodeName.equals(this.nodeName);  
        }  
        return false;  
    }  
      
    public int hashCode() {  
        return this.nodeName.hashCode();  
    }  
}
