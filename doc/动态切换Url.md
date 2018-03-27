Step 1
```java
public interface ApiService {
     @Headers({"Domain-Name: douban"}) // Add the Domain-Name header
     @GET("/v2/book/{id}")
     Observable<ResponseBody> getBook(@Path("id") int id);
}
```
 
Step 2
```java
 // You can change BaseUrl at any time while App is running (The interface that declared the Domain-Name header)
 RetrofitUrlManager.getInstance().putDomain("douban", "https://api.douban.com");
```

改变全局Url:
```java
 // BaseUrl configured in the Domain-Name header will override BaseUrl in the global setting
 RetrofitUrlManager.getInstance().setGlobalDomain("your BaseUrl");
```
