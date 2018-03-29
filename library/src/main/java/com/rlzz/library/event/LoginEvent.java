package com.rlzz.library.event;

/**
 * @author monty
 * @package com.rlzz.library.event
 * @date 2018/3/30  上午12:54
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public class LoginEvent {
    public static int INIT_EVENT = 0;
    public static int RESTART_EVENT = 1;
    private int event;

    public LoginEvent(int event) {
        this.event = event;
    }
}
