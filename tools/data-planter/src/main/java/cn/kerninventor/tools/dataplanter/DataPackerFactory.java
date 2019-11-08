package cn.kerninventor.tools.dataplanter;

import cn.kerninventor.tools.dataplanter.impl.DataTreePackerServer;

/**
 * @Title: Planter
 * @ProjectName swms
 * @Version 1.0.SNAPSHOT
 * @Author Kern
 * @Date 2019/11/8 12:57
 */
public class DataPackerFactory {

    public static DataTreePacker dataTreePacker(){
        return new DataTreePackerServer();
    }
}
