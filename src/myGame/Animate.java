package myGame;

public class Animate implements Runnable{
	BlockBreakerPanel b;
	Animate(BlockBreakerPanel b)
	{
		this.b = b;
	}
	@Override
	public void run() {
		while(true)
		{
			b.update();
			try 
			{
				Thread.sleep(10); // The animation wont run too fast
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}

}
