package com.rlzz.wms.ui.login;


import com.rlzz.base.common.base.presenter.Presenter;

/**
 * @author monty
 * @package com.rlzz.wms.ui.login
 * @date 2018/3/23  下午3:51
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public interface LoginContract {
    interface View {

    }

    abstract class AbsPresenter extends Presenter<View> {
        abstract void login(String account, String password, boolean isRememberPwd);
    }

    interface Model {
//        Observable<ResultModel<String>> login(String account, String password);
    }
}
