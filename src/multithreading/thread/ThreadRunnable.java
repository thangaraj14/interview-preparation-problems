package multithreading.thread;

public class ThreadRunnable implements Runnable {

	public static void main(String[] args) throws InterruptedException {
		ThreadRunnable runnableThread = new ThreadRunnable();
		Thread t1 = new Thread(runnableThread);
		t1.setName("t1");
		Thread t2 = new Thread(runnableThread);
		t2.setName("t2");

		t1.setPriority(Thread.MIN_PRIORITY);
		t1.start();

		t2.setPriority(Thread.MAX_PRIORITY);
		t2.start();
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			if (Thread.currentThread().getName().equals("t1"))
				System.out.println(Thread.currentThread().getName() + "--" + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
