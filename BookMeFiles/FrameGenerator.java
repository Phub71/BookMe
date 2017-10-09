package BookMeFiles;

public class FrameGenerator {

	HomePageFrame hpf;
	
	public FrameGenerator() {
		hpf = new HomePageFrame(this);
	}
	
	public static void main(String args[]){
		FrameGenerator frameGenerator = new FrameGenerator();
	}
}
