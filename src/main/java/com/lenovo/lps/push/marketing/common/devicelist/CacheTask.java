package com.lenovo.lps.push.marketing.common.devicelist;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class CacheTask {
	private static final Logger logger = Logger.getLogger(CacheTask.class);
	
	public CacheTask(File file, String listid, long listTTL, ListCache cache) {
		this.file = file;
		this.listid = listid;
		this.listTTL = listTTL;
		this.cache = cache;
	}

	private File file;
	private String listid;
	private long listTTL;
	private ListCache cache;
	private int workThreadCount = 60;
	private boolean executed = false;
	private boolean readCompelete = false;
	private LinkedBlockingQueue<String[]> deviceIdQueue = new  LinkedBlockingQueue<String[]>();
	private WorkThread[] workThreads;
	
	private class WorkThread extends Thread{
		private boolean waiting = false;
		
		WorkThread(String name){
			super(name);
		}
		
		@Override
		public void run() {
			while(true){
				try {
					waiting = true;
					String[] buffer = deviceIdQueue.take();
					cache.addToList(listid, buffer, listTTL);
				} catch (InterruptedException e) {
					return;
				} catch (Exception e) {
					logger.warn(e,e);
				}finally{
					waiting = false;
				}
			}
		}

		public boolean isWaiting() {
			return waiting;
		}
	}
	
	public void execute(){
		long start = System.currentTimeMillis();
		if(executed){
			return;
		}
		executed = true;
		startWrokers();
		readFile();
		waitForDone();
	}
		
	private void waitForDone() {
		while(!readCompelete || deviceIdQueue.size()>0){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				logger.warn(e);
			}
		}
		while(true){
			boolean allDone = true;
			for(int i=0;i<workThreads.length;i++){
				WorkThread wt = workThreads[i];
				if(wt.isWaiting()){
					wt.interrupt();
				}else{
					allDone = false;
				}
			}
			if(allDone){
				return;
			}else{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					logger.warn(e);
				}
			}
		}
	}

	private void readFile(){
		FileInputStream fis = null;
		try {
			 fis = new FileInputStream(file);
			 LineIterator lit = IOUtils.lineIterator(fis, "UTF-8");
			 String[] buffer = new String[1024*8];
			 int offset = 0;
			 while(lit.hasNext()){
				 while(deviceIdQueue.size()>500){
					 Thread.sleep(10);
				 }

				 String line = lit.nextLine().trim();
				 if(StringUtils.isNotBlank(line)){
					 buffer[offset] = line;
					 offset ++;
					 if(offset==buffer.length-1){
						 deviceIdQueue.put(Arrays.copyOfRange(buffer, 0, offset));
						 offset = 0;
					 }
				 }

			 }
			 if(offset>0){
				 deviceIdQueue.put(Arrays.copyOfRange(buffer, 0, offset));
			 }
		} catch (Exception e) {
			logger.warn(e,e);
		} finally{
			IOUtils.closeQuietly(fis);
			readCompelete = true;
		}
	}
	
	private void startWrokers(){
		workThreads = new WorkThread[workThreadCount];
		for(int i=0;i<workThreadCount;i++){
			WorkThread wt = new WorkThread("CacheTask-WorkTread-"+i);
			workThreads[i] = wt;
			wt.start();
		 }
	}

	public int getWorkThreadCount() {
		return workThreadCount;
	}

	public void setWorkThreadCount(int workThreadCount) {
		this.workThreadCount = workThreadCount;
	}

	public String getListid() {
		return listid;
	}

}
