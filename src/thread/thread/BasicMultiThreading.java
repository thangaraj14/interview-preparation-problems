package thread.thread;

public class BasicMultiThreading extends Thread {

	public static void main(String[] args) {
		BasicMultiThreading thread = new BasicMultiThreading();
		BasicMultiThreading thread2 = new BasicMultiThreading();
		thread.start();
		thread2.start();

		thread.run();
		thread2.run();

		for (int i = 0; i < 5; i++) {
			System.out.println("Thread is running on track :" + i + "-->" + thread.getName() + " " + thread.getState());
			System.out.println("Thread 2 is running : " + thread2.getName() + " " + thread2.getState());
			try {
				thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void printThread() {

	}
}
