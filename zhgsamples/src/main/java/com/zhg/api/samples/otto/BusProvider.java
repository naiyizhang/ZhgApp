package com.zhg.api.samples.otto;

import com.squareup.otto.Bus;

/**
 * Created by nyzhang on 2015/12/17.
 */
public class BusProvider {
    private static final Bus BUS=new Bus();
    public static Bus getInstance(){
        return BUS;
    }
}
