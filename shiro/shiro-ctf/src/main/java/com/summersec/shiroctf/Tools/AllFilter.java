package com.summersec.shiroctf.Tools;

/**
 * @ClassName: AllFilter
 * @Description: TODO
 * @Author: Summer
 * @Date: 2020/12/23 14:54
 * @Version: v1.0.0
 * @Description:
 **/
public class AllFilter implements IAllFilter{

    public AllFilter() {
    }

    @Override
    public String filter(String param) {
        String[] keyWord = new String[]{"'", "\"", "select", "union", "/;", "/%3b"};
        String[] var3 = keyWord;
        int var4 = keyWord.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String i = var3[var5];
            param = param.replaceAll(i, "");
        }

        return param;
    }
}
