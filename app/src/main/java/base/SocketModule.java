package base;

/**
 * Created by zhongwang on 2018/1/22.
 */

public class SocketModule {
    /**
     * 操作类型 可以定义一个公共类来存放操作类型数据
     */
    private String operateType;
    /**
     * 需要的数据 （所有的数据包括实体类 字符 布尔 等 封装成json字符串传递）
     */
    private String baseModule;

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getBaseModule() {
        return baseModule;
    }

    public void setBaseModule(String baseModule) {
        this.baseModule = baseModule;
    }
}
