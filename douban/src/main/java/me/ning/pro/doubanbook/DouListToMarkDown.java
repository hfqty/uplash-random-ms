package me.ning.pro.doubanbook;

import me.ning.pro.doubanbook.entity.DouList;
import me.ning.pro.doubanbook.parser.DouListParser;

public class DouListToMarkDown {

    public static void main(String[] args) {
     /*   DouListToMarkDown douListToMarkDown = new DouListToMarkDown();
        douListToMarkDown
                .setid(1264675)//9分
                .setid(1246984)//入门
                .setid(1757387)//9-9.7
                .setid(723364)//二十世纪
                //.setid(14090587)//开智
               // .setid(43430373)//2500
                //.setid(31441)//编程
        ;*/
        DouList douList = new DouList();
        douList
                .id(1264675)//9分
                .id(1246984)//入门
                .id(1757387)//9-9.7
                .id(723364)//二十世纪
                .id(14090587)//开智
                .id(31441)//编程
                .id(43430373)//2500
        ;

        DouListParser douListParser = new DouListParser(douList);
        douListParser.parse();
    }


}
