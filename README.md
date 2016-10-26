##Retrofit学习(一)集成-简单get请求


### 集成

github<https://github.com/square/retrofit>

在studio在添加依赖

```
 //添加retrofit-会自动下载okhttp
compile 'com.squareup.retrofit2:retrofit:2.1.0'
    //添加retrofit gson转换会自动下载gson
compile 'com.squareup.retrofit2:converter-gson:2.1.0'
    //添加返回的字符串支付
compile 'com.squareup.retrofit2:converter-scalars:2.1.0'

```
可以用其它的retrofit2支付的替代见连接
转换可以在API处找到
<http://square.github.io/retrofit/#api-declaration>
右侧点击Retrofit Configuration版本与retrofit2保持一直即可
```
Gson: com.squareup.retrofit2:converter-gson
Jackson: com.squareup.retrofit2:converter-jackson
Moshi: com.squareup.retrofit2:converter-moshi
Protobuf: com.squareup.retrofit2:converter-protobuf
Wire: com.squareup.retrofit2:converter-wire
Simple XML: com.squareup.retrofit2:converter-simplexml
Scalars (primitives, boxed, and String): com.squareup.retrofit2:converter-scalars
```


### 第一个get请求

添加网络权限
```
<uses-permission android:name="android.permission.INTERNET"/>
```

建立接口
```
public interface GitHubService {


    @GET("users/{user}/repos")
    Call<String> listRepos(@Path("user") String user);
}

```

简单说明一下
@GET表示为get请求，还会有@POST
@PATH 表示后面的参数要添加到@GETR后面对应的{user}中，{user}相当于一个占位符
学习spring MVC都知道这种做学法把请求参数添加到请求路径中去，

@Query就是我们的请求的键值对的设置
@QueryMap 和@Query相似 就是个传个map集合,也是键值对


### 开始请求

界面就一个按键点击可以请求


> 结果
```
OK  [{"id":18221276,"name":"git-consortium","full_name":"octocat/git-consortium","owner":{"login":"octocat","id":583231,"avatar_url":"https://avatars.githubusercontent.com/u/583231?v=3","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false},"private":false,"html_url":"https://github.com/octocat/git-consortium","description":"This repo is for demonstration purposes only.","fork":false,"url":"https://api.github.com/repos/octocat/git-consortium","forks_url":"https://api.github.com/repos/octocat/git-consortium/forks","keys_url":"https://api.github.com/repos/octocat/git-consortium/keys{/key_id}","collaborators_url":"https://api.github.com/repos/octocat/git-consortium/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/octocat/git-consortium/teams","hooks_url":"https://api.github.com/repos/octocat/git-consortium/hooks","issue_events_url":"https://api.github.com/repos/octocat/git-consortium/issues/events{/number}","events_url":"https://api.github.com/repos/octocat/git-consortium/events","assignees_url":"https://api.github.com/repos/octocat/git-consortium/assignees{/user}","branches_url":"https://api.github.com/repos/octocat/git-consortium/branches{/branch}","tags_url":"https://api.github.com/repos/octocat/git-consortium/tags","blobs_url":"https://api.github.com/repos/octocat/git-consortium/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/octocat/git-consortium/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/octocat/git-consortium/git/refs{/sha}","trees_url":"https://api.github.com/repos/octocat/git-consortium/git/trees{/sha}","statuses_url":"https://api.github.com/repos/octocat/git-consortium/statuses/{sha}","languages_url":"https://api.github.com/repos/octocat/git-consortium/languages","stargazers_url":"https://api.github.com/repos/octocat/git-consortium/stargazers","contributors_url":"https://api.github.com/repos/octocat/git-consortium/contributors","subscribers_url":"https://api.github.com/repos/octocat/git-consortium/subscribers","subscription_url":"https://api.github.com/repos/octocat/git-consortium/subscription","commits_url":"https://api.github.com/repos/octocat/git-consortium/commits{/sha}","git_commits_url":"https://api.github.com/repos/octocat/git-consortium/git/commits{/sha}","comments_url":"https://api.github.com/repos/octocat/git-consortium/comments{/number}","issue_comment_url":"https://api.github.com/repos/octocat/git-consortium/issues/comments{/number}","contents_url":"https://api.github.com/repos/octocat/git-consortium/contents/{+path}","compare_url":"https://api.github.com/repos/octocat/git-consortium/compare/{base}...{head}","merges_url":"https://api.github.com/repos/octocat/git-consortium/merges","archive_url":"https://api.github.com/repos/octocat/git-consortium/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/octocat/git-consortium/downloads","issues_url":"https://api.github.com/repos/octocat/git-consortium/issues{/number}","pulls_url":"https://api.github.com/repos/octocat/git-consortium/pulls{/number}","milestones_url":"https://api.github.com/repos/octocat/git-consortium/milestones{/number}","notifications_url":"https://api.github.com/repos/octocat/git-consortium/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/octocat/git-consortium/labels{/name}","releases_url":"https://api.github.com
```

这样一个网络请求就完成了

把返回字符串返回实体类使用Gson
```
package com.liu.retrofitdemo2.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @Description: 描述
 * @AUTHOR 刘楠  Create By 2016/10/26 0026 10:54
 */
public class Repo {


    public int id;
    public String name;
    public String full_name;
    public OwnerEntity owner;
    @SerializedName("private")
    public boolean privateX;
    public String  html_url;
    public Object  description;
    public boolean fork;
    public String  url;
    public String  forks_url;
    public String  keys_url;
    public String  collaborators_url;
    public String  teams_url;
    public String  hooks_url;
    public String  issue_events_url;
    public String  events_url;
    public String  assignees_url;
    public String  branches_url;
    public String  tags_url;
    public String  blobs_url;
    public String  git_tags_url;
    public String  git_refs_url;
    public String  trees_url;
    public String  statuses_url;
    public String  languages_url;
    public String  stargazers_url;
    public String  contributors_url;
    public String  subscribers_url;
    public String  subscription_url;
    public String  commits_url;
    public String  git_commits_url;
    public String  comments_url;
    public String  issue_comment_url;
    public String  contents_url;
    public String  compare_url;
    public String  merges_url;
    public String  archive_url;
    public String  downloads_url;
    public String  issues_url;
    public String  pulls_url;
    public String  milestones_url;
    public String  notifications_url;
    public String  labels_url;
    public String  releases_url;
    public String  deployments_url;
    public String  created_at;
    public String  updated_at;
    public String  pushed_at;
    public String  git_url;
    public String  ssh_url;
    public String  clone_url;
    public String  svn_url;
    public Object  homepage;
    public int     size;
    public int     stargazers_count;
    public int     watchers_count;
    public String  language;
    public boolean has_issues;
    public boolean has_downloads;
    public boolean has_wiki;
    public boolean has_pages;
    public int     forks_count;
    public Object  mirror_url;
    public int     open_issues_count;
    public int     forks;
    public int     open_issues;
    public int     watchers;
    public String  default_branch;

    public static class OwnerEntity {
        public String  login;
        public int     id;
        public String  avatar_url;
        public String  gravatar_id;
        public String  url;
        public String  html_url;
        public String  followers_url;
        public String  following_url;
        public String  gists_url;
        public String  starred_url;
        public String  subscriptions_url;
        public String  organizations_url;
        public String  repos_url;
        public String  events_url;
        public String  received_events_url;
        public String  type;
        public boolean site_admin;

    }

}

```
记得添加toString方法方法查看

更改接口返回值
```
public interface GitHubService {


    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
```

再次请求
```


        //建立retrofit对象
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/")
                //添加返回字符串的支持--不知道返回的是什么，添加字符串支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //添加GSON转换支持
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //获取接口
        GitHubService service = retrofit.create(GitHubService.class);

        //调用方法-返回 回调更换为对象
        Call<List<Repo>> call = service.listRepos("octocat");

        //异步调用
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

                L.d("vivi",response.message()+"  "+response.body());
                mTvResult.setText(response.message()+" \n结果: "+response.body().toString());
                Toast.makeText(FirstActivity.this, "结果:\n "+response.body().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

                t.printStackTrace();
                mTvResult.setText(t.getMessage());

            }
        });
    }
```
### 简单封装下Retrofit

可以发现每次写都要new
```
 //建立retrofit对象
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/")
                //添加返回字符串的支持--不知道返回的是什么，添加字符串支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //添加GSON转换支持
                .addConverterFactory(GsonConverterFactory.create())
                .build();

```

简单封装

```
public class RetrofitWrapper {
    //单例
    private static RetrofitWrapper INSTANCE;
    // Retrofit 对象
    private Retrofit mRetrofit;

    private RetrofitWrapper(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)  //添加baseurl
                .addConverterFactory(ScalarsConverterFactory.create()) //添加返回为字符串的支持
                .addConverterFactory(GsonConverterFactory.create()) //create中可以传入其它json对象，默认Gson
                .build();
    }
    public static RetrofitWrapper getInstance() {

        if(INSTANCE == null) {
            synchronized(RetrofitWrapper.class) {
                if(INSTANCE == null) {
                    INSTANCE = new RetrofitWrapper();
                }
            }
        }

        return INSTANCE;
    }


    /**
     * 转换为对象的Service
     * @param service
     * @param <T>
     * @return 传入的类型
     */
    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }
}

```

使用
```

        Call<List<Repo>> callLn0941 = RetrofitWrapper.getInstance().create(GitHubService.class).listRepos("octocat");

        callLn0941.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                // ...do something
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                  // ...do something

            }
        });
```






