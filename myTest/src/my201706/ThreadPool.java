package my201706;

import java.nio.ByteBuffer;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPool {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<Integer> aaa = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				return new Random().nextInt(9);
			}
		};
		
		ExecutorService bbb = Executors.newFixedThreadPool(1);
		Future<Integer> ccc =  bbb.submit(aaa);
		System.out.println(ccc.get());
		bbb.shutdown();
		
//		CallerRunsPolicy 
		ByteBuffer eee = ByteBuffer.allocate(32);
		eee.flip();
	}
}
