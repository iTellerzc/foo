package com.iteller.foo.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Arrays;

import static org.apache.zookeeper.KeeperException.Code;

/**
 * Created by 18060903 on 2018/6/12.
 */
public class DataMonitor implements Watcher, AsyncCallback.StatCallback{

    private ZooKeeper zk;

    private String znode;

    private Watcher chainedWatcher;

    protected boolean dead;

    private DataMonitorListener listener;

    private byte prevData[];

    public DataMonitor(ZooKeeper zk, String znode, Watcher chainedWatcher, DataMonitorListener listener){
        this.zk = zk;
        this.znode = znode;
        this.chainedWatcher = chainedWatcher;
        this.listener = listener;
        zk.exists(znode, true, this, null);
    }

    public void processResult(int rc, String path, Object ctx, Stat stat) {
       boolean exists;
       switch (rc){
           case 0:
               exists = true;
               break;
           case -101:
               exists = false;
               break;
           case -112:
           case -102:
               dead = true;
               listener.closing(rc);
               return;
           default:
               zk.exists(znode, true, this, null);
               return;
       }

       byte []b = null;
       if(exists){
           try{
               b = zk.getData(znode, false, null);
           }catch (KeeperException e){
               e.printStackTrace();
           }catch (InterruptedException e){
               return;
           }
       }
       if((b== null && b!= prevData) || (b != null && !Arrays.equals(prevData, b))){
           listener.exists(b);
           prevData = b;
       }
    }

    public void process(WatchedEvent watchedEvent) {
        String path = watchedEvent.getPath();
        if(watchedEvent.getType() == Event.EventType.None){
            switch (watchedEvent.getState()){
                case SyncConnected:
                    break;
                case Expired:
                    dead = true;
                    listener.closing(Code.SESSIONEXPIRED.intValue());
            }
        }else{
            if(path != null && path.equals(znode)){
                zk.exists(znode, true, this, null);
            }
        }

        if(chainedWatcher != null){
            chainedWatcher.process(watchedEvent);
        }
    }

    public  interface DataMonitorListener {

        /**
         * 节点实例状态是否发生变化
         * @param data
         */
        void exists(byte data[]);

        void closing(int rc);
    }
}
