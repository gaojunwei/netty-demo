package com.jd.jr.innovation.epl.demo.command.ap;

import com.jd.jr.innovation.epl.demo.command.CommandData;
import com.jd.jr.innovation.epl.demo.command.enums.ApCmdEnum;
import com.jd.jr.innovation.epl.demo.socket.server.common.SystemCode;
import com.jd.jr.innovation.epl.demo.socket.server.common.exception.AppException;
import com.jd.jr.innovation.epl.demo.socket.server.common.utils.BinaryUtil;
import com.jd.jr.innovation.epl.demo.socket.server.common.utils.MacUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: gaojunwei
 * @Date: 2018/11/30 17:26
 * @Description: AP注册数据
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ApRegisterData extends CommandData {

    private String apCode;

    private ApRegisterData(){}

    public ApRegisterData(String apCode){
        if(!MacUtils.isMac(apCode)){
            throw new AppException(SystemCode.PARAM_ERROR.getCode(),SystemCode.PARAM_ERROR.getMessage());
        }
        this.apCode = apCode.toUpperCase();
    }

    @Override
    public byte[] getBytes() {
        if(super.getBytes()==null || super.getBytes().length==0){
            byte[] data = new byte[7];
            data[0] = ApCmdEnum.CMD_REG.getCmd();
            byte[] apCodeBts = BinaryUtil.toBytes(this.apCode);
            System.arraycopy(apCodeBts, 0, data, 1, apCodeBts.length);
            super.setBytes(data);
        }
        return super.getBytes();
    }
}