package test;

import org.junit.Test;

public class TestThread {
	public void say(String msg) {
		System.out.println(msg);
	}

	int i = 10;

	@Test
	public void doit() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (i > 0) {
						System.out.println("hello");
						i--;
						Thread.currentThread().sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	public static void main(String[] args) {

		new Thread(new Runnable() {
			int i = 10;

			@Override
			public void run() {
				try {
					while (i > 0) {
						System.out.println("hello");
						i--;
						Thread.currentThread().sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
}
