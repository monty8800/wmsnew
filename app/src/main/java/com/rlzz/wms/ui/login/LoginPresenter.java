package com.rlzz.wms.ui.login;


/**
 * @author monty
 * @package com.rlzz.wms.ui.login
 * @date 2018/3/23  下午3:50
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public class LoginPresenter extends LoginContract.AbsPresenter {

    private LoginContract.Model model;

    public LoginPresenter() {
//        model = LoginModelImpl.getInstance();
    }

    @Override
    void login(String account, String password,boolean isRememberPwd) {
        /*addSubscribe(model.login(account,password).compose(RxSchedulers.compose()).subscribeWith(new BaseObserver<String>() {
            @Override
            protected void onSuccess(String s) {

            }
        }));*/
    }
}
