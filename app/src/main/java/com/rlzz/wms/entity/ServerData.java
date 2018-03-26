package com.rlzz.wms.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author monty
 * @package com.rlzz.wms.entity
 * @date 2018/3/26  下午4:45
 * @description TODO
 * @org www.szrlzz.com 深圳市瑞联智造科技有限公司
 * @email mwu@szrlzz.com
 */

public class ServerData implements Parcelable {
    /**
     * 协议 http-0 https-1
     */
    public int protocol;
    /**
     * ip地址
     */
    public String ipAddress;
    /**
     * 端口
     */
    public String port;
    /**
     * 目录
     */
    public String catalog;
    /**
     * 帐套
     */
    public String accountBook;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.protocol);
        dest.writeString(this.ipAddress);
        dest.writeString(this.port);
        dest.writeString(this.catalog);
        dest.writeString(this.accountBook);
    }

    public ServerData() {
    }

    protected ServerData(Parcel in) {
        this.protocol = in.readInt();
        this.ipAddress = in.readString();
        this.port = in.readString();
        this.catalog = in.readString();
        this.accountBook = in.readString();
    }

    public static final Parcelable.Creator<ServerData> CREATOR = new Parcelable.Creator<ServerData>() {
        @Override
        public ServerData createFromParcel(Parcel source) {
            return new ServerData(source);
        }

        @Override
        public ServerData[] newArray(int size) {
            return new ServerData[size];
        }
    };
}
