package com.tianya.zhg.rxandroid.rx;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tianya.zhg.rxandroid.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by nyzhang on 2016/1/15.
 */
public class AppMainFragment extends Fragment {

    @Bind(R.id.id_recycleView)
    RecyclerView mRecycleView;
    @Bind(R.id.id_swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private MyAdapter myAdapter;
    private String mFileDir;
    public static final String ROOT_IMAGE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() +
            "/tianya/images";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFileDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/tianya/images";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_fragment, container, false);
        ButterKnife.bind(this, rootView);
        myAdapter=new MyAdapter(getActivity(),new ArrayList<>());
        mRecycleView.setAdapter(myAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mSwipeRefreshLayout.setOnRefreshListener(this::refreshList);
//        mRecycleView.setHasFixedSize(true);

        return rootView;
    }



    private Observable<AppInfo> getApps() {
        return Observable.create(subscriber -> {
            List<AppInfoRich> apps = new ArrayList<AppInfoRich>();
            final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            List<ResolveInfo> infos = getActivity().getPackageManager().queryIntentActivities(mainIntent, 0);
            for (ResolveInfo info : infos) {
                AppInfoRich appInfoRich=new AppInfoRich(info,getActivity());
                appInfoRich.setLabel(info.loadLabel(getActivity().getPackageManager()).toString());
                appInfoRich.setIcon(info.loadIcon(getActivity().getPackageManager()));
                apps.add(appInfoRich);
            }
            for (AppInfoRich appInfo : apps) {
                Bitmap icon = Utils.drawableToBitmap(appInfo.getIcon());
                String name = appInfo.getName();
                Utils.storeBitmap(getActivity(), name, icon);
                String iconPath = mFileDir + "/" + name;
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                Log.e("info","name="+name);
                subscriber.onNext(new AppInfo(appInfo.getLastUpdateTime(), name, iconPath));

            }
            if (!subscriber.isUnsubscribed()) {
                subscriber.onCompleted();
            }
        });
    }

    private void test(){
        Observable<Integer> appObservable=Observable.interval(1,TimeUnit.SECONDS).subscribeOn(Schedulers.newThread()).map((aLong -> {
            return Integer.valueOf(aLong.toString());
        }));
        Observable<Long> observable1=Observable.interval(1,TimeUnit.SECONDS);
        appObservable.join(observable1, new Func1<Integer, Observable<Long>>() {
            @Override
            public Observable<Long> call(Integer integer) {
                return Observable.timer(2,TimeUnit.SECONDS);
            }
        }, new Func1<Long, Observable<Long>>() {
            @Override
            public Observable<Long> call(Long aLong) {
                return Observable.timer(0,TimeUnit.SECONDS);
            }
        }, new Func2<Integer, Long, Object>() {
            @Override
            public Object call(Integer integer, Long aLong) {
                return integer+":"+aLong;
            }
        }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                Log.e("info","o======="+o);
            }
        });
    }
    private List<AppInfo> getMyApps(){
        List<AppInfoRich> apps = new ArrayList<AppInfoRich>();
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> infos = getActivity().getPackageManager().queryIntentActivities(mainIntent, 0);
        for (ResolveInfo info : infos) {
            AppInfoRich appInfoRich=new AppInfoRich(info,getActivity());
            appInfoRich.setLabel(info.loadLabel(getActivity().getPackageManager()).toString());
            appInfoRich.setIcon(info.loadIcon(getActivity().getPackageManager()));
            apps.add(appInfoRich);
        }
        List<AppInfo> list=new ArrayList<AppInfo>();
        for (AppInfoRich appInfo : apps) {
            Bitmap icon = Utils.drawableToBitmap(appInfo.getIcon());
            String name = appInfo.getName();
            Utils.storeBitmap(getActivity(), name, icon);
            String iconPath = mFileDir + "/" + name;
            AppInfo appInfo1=new AppInfo(System.currentTimeMillis(),name,iconPath);
            list.add(appInfo1);
        }
        return list;
    }

    private void refreshList(){
        test();
//        Observable.from(getMyApps()).sample(30,TimeUnit.SECONDS)
//        Observable.from(getMyApps()).filter().take().takeLast().first().last().skip().skipLast().distinct().distinctUntilChanged().throttleFirst().elementAt().timeout().sample().debounce()
        Observable.from(getMyApps()).scan(((appInfo, appInfo2) -> {
            if (appInfo.getName().length() > appInfo2.getName().length()) {
                return appInfo;
            } else {
                return appInfo2;
            }
        })).distinct().map(new Func1<AppInfo, AppInfo>() {
            @Override
            public AppInfo call(AppInfo appInfo) {
                String name = appInfo.getName();
                appInfo.setName(name.toLowerCase());
                return appInfo;
            }
        }).subscribe(new Observer<AppInfo>() {
            @Override
            public void onCompleted() {
                Toast.makeText(getActivity(), "here is the list", Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onNext(AppInfo appInfo) {
                myAdapter.addApp(appInfo);
            }
        });
    }
    private void refreshList10(){
        Observable.interval(3,1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            Toast.makeText(getActivity(),"I say "+aLong,Toast.LENGTH_SHORT).show();
        });
    }
    private void refreshList6(){
        Observable.timer(3,TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            Toast.makeText(getActivity(), "i say " + aLong, Toast.LENGTH_SHORT).show();
        });
    }
    private void refreshList5(){
       Subscription subscription= Observable.interval(3, TimeUnit.SECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
           @Override
           public void onCompleted() {
               Toast.makeText(getActivity(), "YeaHH", Toast.LENGTH_SHORT).show();
               mSwipeRefreshLayout.setRefreshing(false);
           }

           @Override
           public void onError(Throwable e) {
               Toast.makeText(getActivity(), "something went wrong", Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onNext(Long aLong) {
               Log.e("info","thread in "+Thread.currentThread().getName());
               Toast.makeText(getActivity(), "I say " + aLong, Toast.LENGTH_SHORT).show();
           }
       });
    }
    private void refreshList4(){
        Observable.range(10,5).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Toast.makeText(getActivity(),"YeaHH",Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getActivity(),"something went wrong",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Integer integer) {
                Toast.makeText(getActivity(),"I say "+ integer,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void refreshList3(){
        AppInfo a1=ApplicationList.getInstance().getList().get(0);
        AppInfo a2=ApplicationList.getInstance().getList().get(2);
        AppInfo a3=ApplicationList.getInstance().getList().get(3);
        Observable.just(a1,a2,a3).repeat(3).subscribe(new Observer<AppInfo>() {
            @Override
            public void onCompleted() {
                Toast.makeText(getActivity(), "here is the list", Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onNext(AppInfo appInfo) {
                myAdapter.addApp(appInfo);
            }
        });

        Observable<Integer> defer=Observable.defer(this::getInt);
        defer.subscribe(integer -> {Log.e("info","value="+String.valueOf(integer));});

    }

    private Observable getInt(){
        return Observable.create(subscriber -> {
            if(subscriber.isUnsubscribed())return;
            Log.e("info","GETTER");
            subscriber.onNext(42);
            subscriber.onCompleted();
        });
    }

    private void refreshList2(){
        mRecycleView.setVisibility(View.VISIBLE);
        Observable.from(ApplicationList.getInstance().getList()).subscribe(new Observer<AppInfo>() {
            @Override
            public void onCompleted() {
                Toast.makeText(getActivity(),"here is the list",Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getActivity(),"error",Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onNext(AppInfo appInfo) {
                myAdapter.addApp(appInfo);
            }
        });
    }
    private void refreshList1(){

        getApps().onBackpressureBuffer().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).takeLast(5).repeat(3).distinct().toSortedList().subscribe(new Observer<List<AppInfo>>() {
            @Override
            public void onCompleted() {
                Toast.makeText(getActivity(), "here is the list", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "something is error ", Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onNext(List<AppInfo> appInfos) {
                mRecycleView.setVisibility(View.VISIBLE);
                myAdapter.addApp(appInfos);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
