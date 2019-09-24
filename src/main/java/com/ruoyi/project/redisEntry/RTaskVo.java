package com.ruoyi.project.redisEntry;

import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.project.duties.tasks.domain.Tasks;

import java.math.BigDecimal;

public class RTaskVo extends Tasks {
    //已经出场的方量
    private BigDecimal curFangLiang = new BigDecimal(0);

    public BigDecimal getCurFangLiang() {
        return curFangLiang;
    }

    public void setCurFangLiang(BigDecimal curFangLiang) {
        this.curFangLiang = curFangLiang;
    }

    public static void main(String[] args){
        Tasks task = new Tasks();
        task.setPause("Y");
        RTaskVo vo = new RTaskVo();
        BeanUtils.copyProperties(task,vo);
        vo.setCurFangLiang(new BigDecimal(23));
        BeanUtils.copyProperties(task,vo);
        System.out.println(vo.getPause()+vo.getCurFangLiang().toString());
    }
}
