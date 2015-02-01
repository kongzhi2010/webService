package husd.wsi.service.report;

import husd.wsi.exception.ShortYException;

/**
 * Created by shengdong on 2015/1/17.
 */
public class SimpleTest {

    public static void main(String[] args){

    }

    public static void main(int [] x,int [] y){
        if(x.length > y.length)
            throw new ShortYException("y 轴的值要比x轴的值多");

    }



}
