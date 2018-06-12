package com.iteller.foo.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by 18060903 on 2018/6/12.
 */
public class Executor
        implements Watcher, Runnable, DataMonitor.DataMonitorListener{

    private String znode;

    private DataMonitor dm;

    private ZooKeeper zk;

    private String filename;

    private String[] exec;

    Process child;

    public Executor(String hostPort, String znode, String filename, String[] exec)
        throws KeeperException, IOException{
        this.filename = filename;
        this.exec = exec;
        zk = new ZooKeeper(hostPort, 3000, this);
        dm = new DataMonitor(zk, znode, null, this);
    }

    public static void main(String[] args){
        if(args.length < 4){
            System.err.println("Usage:Executor hostPort znode filename program[args ...]");
            System.exit(2);
        }
        String hostPort = args[0];
        String znode = args[1];
        String filename = args[2];
        String[] exec = new String[args.length - 3];
        System.arraycopy(args, 3, exec,0, exec.length);
        try{
            new Executor(hostPort, znode, filename, exec).run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void exists(byte[] data) {
        if(data == null){
            if( child != null){
                System.out.println("killing process");
                child.destroy();
                try{
                    child.waitFor();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            child = null;
        }else{
            if(child != null){
                System.out.println("stoping child");
                child.destroy();
                try{
                    child.waitFor();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            try {
                FileOutputStream fos = new FileOutputStream(filename);
                fos.write(data);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                System.out.println("Starting child");
                child = Runtime.getRuntime().exec(exec);
                new StreamWriter(child.getInputStream(), System.out);
                new StreamWriter(child.getErrorStream(), System.err);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closing(int rc) {
        synchronized (this){
            notifyAll();
        }
    }

    public void run() {
        try{
            synchronized (this){
                while (!dm.dead){
                    wait();
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void process(WatchedEvent watchedEvent) {
        dm.process(watchedEvent);
    }

    static class StreamWriter extends  Thread{
        OutputStream os;
        InputStream is;
        StreamWriter( InputStream is, OutputStream os){
            this.is = is;
            this.os = os;
            start();
        }

        public void run(){
            byte[] b = new byte[80];
            int rc;
            try {
                while((rc =  is.read(b)) > 0){
                    os.write(b, 0, rc);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
