package com.rlzz.wms.ui.login;

import com.rlzz.library.net.RetrofitHelper;
import com.rlzz.library.net.api.APIDemo;
import com.rlzz.library.net.bean.ResultModel;

import io.reactivex.Observable;

/**
 * @author monty
 * @package com.rlzz.wms.ui.login
 * @date 2018/3/23  下午3:58
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public class LoginModelImpl implements LoginContract.Model {
    private APIDemo service;
    private LoginModelImpl() {
        service = RetrofitHelper.getInstance().getService(APIDemo.class);
    }

    public static LoginContract.Model getInstance() {
        return LoginContentModelHolder.INSTANCE;
    }

    private final static class LoginContentModelHolder {
        private final static LoginContract.Model INSTANCE = new LoginModelImpl();
    }
    @Override
    public Observable<ResultModel<String>> login(String account, String password) {
        return service.getBlog(0);
    }
}
