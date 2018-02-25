package cn.wodesh.controller;

import cn.wodesh.bean.UserBean;

import cn.wodesh.util.ParamValidateUtil;
import cn.wodesh.util.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TS on 2018/2/23.
 */
@RestController
public class UserController {

    @PostMapping("/rest/add")
    public Object add(@RequestBody UserBean userBean) throws Exception{
        ParamValidateUtil.notNull(userBean.getHeadurl() , "头像不能为空");
        return ResultUtil.success(userBean);
    }

}
