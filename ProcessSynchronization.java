
class Process extends Thread{
	private int max = 10;
	private int min = 0;
	private int money = 0;
	
	public void run(){
		while(true){
			//Odd thread, increase the money
			long threadId = Thread.currentThread().getId();
			if (threadId % 2 == 1){
				try {
					increase(threadId);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//Even thread, decrease the money
			else {
				try {
					decrease(threadId);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//Sleep
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void increase(long id) throws InterruptedException {
		//Increase the money if money does not reach the maximum boundary 10
		if(money < max) {
			money++;
			System.out.println("Thread-ID is: " + id + "  Money is: " + money);
			notifyAll();
		}
		else {
			wait();
		}
		sleep(1000);
	}
	
	public synchronized void decrease(long id) throws InterruptedException {
		//Increase the money if money does not reach the minimum boundary 0
		if(money > min) {
			money--;
			System.out.println("Thread-ID is: " + id + "  Money is: " + money);
			notifyAll();
		}
		else {
			wait();
		}
		sleep(1000);
	}
}

public class ProcessSynchronization {
	
	public static void main(String[] args) throws InterruptedException {
		Process process = new Process();
		Thread[] t = new Thread[10];
		for(int i=0;i<10;i++) {
			t[i] = new Thread(process);
			t[i].start();		
		}		
	}
}

