package com.liu.retrofitdemo2.wrapper;

import com.liu.retrofitdemo2.bean.AccessToken;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 初始化
 * 
 * @ClassName: ServiceGenerator
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 刘楠
 * @date 2016年10月31日 下午1:32:21
 *
 */
public class ServiceGenerator {
	public static final String BASE_URL = "https://twitter.com/";

	private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

	private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
			.addConverterFactory(ScalarsConverterFactory.create())
			.addConverterFactory(GsonConverterFactory.create());

	/**
	 * 普通不需要认证的API
	 * @Title: createService 
	 * @Description: TODO(这里用一句话描述这个方法的作用)       
	 * @param @param serviceClass
	 * @param @return    设定文件
	 * @return S    返回类型    
	 * @throws
	 */
	public static <S> S createService(Class<S> serviceClass) {
		return createService(serviceClass, null);
	}
	
	/**
	 * 需要用户名与密码认证的API调这个
	 * @Title: createService 
	 * @Description: TODO(这里用一句话描述这个方法的作用)       
	 * @param @param serviceClass
	 * @param @param username
	 * @param @param password
	 * @param @return    设定文件
	 * @return S    返回类型    
	 * @throws
	 */
	public static <S> S createService(Class<S> serviceClass, String username, String password) {
		
		
		
		return null;
        
    }
	/**
	 * 需要authToken认证的API调这个
	 * @Title: createService 
	 * @Description: TODO(这里用一句话描述这个方法的作用)       
	 * @param @param serviceClass
	 * @param @param authToken
	 * @param @return    设定文件
	 * @return S    返回类型    
	 * @throws
	 */
	public static <S> S createService(Class<S> serviceClass, final AccessToken token) {
       
		
		if(token!=null){
			
			
			httpClient.addInterceptor(new Interceptor() {
				
				public okhttp3.Response intercept(Chain chain) throws IOException {
					
					
					Request origin = chain.request();
					
					Request.Builder requestBuilder = origin.newBuilder()
							.header("Accept", "application/json")
							.header("Authorization", token.getAccessToken())
							.method(origin.method(), origin.body());
	
					Request request  = requestBuilder.build();
			
					return chain.proceed(request);
				}
			});
			
			
		}
		
	
       
		OkHttpClient client = httpClient.build();
		Retrofit retrofit = builder.client(client).build();
		return retrofit.create(serviceClass);
	}
}
