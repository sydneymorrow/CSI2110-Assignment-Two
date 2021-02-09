/* imports */
import java.util.*;


/**
* Name: Sydney Morrow
* Student Num: 300119030
* HashTable Method
**/
class Main{
	
	/**
	*	Constructors 
	**/ 
    public int bestK;
    public int[] currX;
	public int[] bestX;
	public int[] length;
	public int n;
	public int L;
	public int counter;
	public String[] placeHolder;
	public int lastN;
	public HashMap<Integer, Integer> visited;
   
   	/**
	*	Main method
	**/ 
	public static void main(String[] args){
		
        Main myWork = new Main();  
		Scanner sc = new Scanner(System.in);
		String str = "";
		while(sc.hasNextLine()){
			str = str + "\n" + sc.nextLine();
		}
		myWork.initialize(str);
		
		String print = "";
	
		for(int j = 0; j < myWork.counter; j++){
			myWork.reset();
			int k = 0; 
			int s = myWork.L;
			myWork.BacktrackSolve(k,s);
			
			if(j!=0){
				print = print + "\n" + myWork.bestK;
			}else{
				print = print + myWork.bestK;
			}
			

			for(int i = 0; i < myWork.bestX.length; i++){
				if(myWork.bestX[i] == 1){

					print = print + "\n" + "starboard";
				}else if(myWork.bestX[i] == 0){

					 print = print + "\n" + "port";
				}
			}
			
			if(j != myWork.counter - 1){

				print = print + "\n";
			}
				
				
		}
			System.out.println(print);
	}
		
	/**
	* Initializes input
	**/ 	
	void initialize(String test){
		test = test.replaceAll("[\n\r]+", "\n");
		placeHolder = test.split("\\r?\\n");
		counter = Integer.parseInt(placeHolder[1]);
		lastN = 0;	
	} 


	/**
	*	Resets the global variables so the next ferry can be loaded
	**/ 
	void reset(){

		ArrayList<Integer> hold = new ArrayList<Integer>();
		
		int j = 2+(lastN);
		
		while(Integer.parseInt(placeHolder[j].trim()) != 0){
				hold.add(Integer.parseInt(placeHolder[j].trim()));
				j++;
				
				if(Integer.parseInt(placeHolder[j].trim()) == 0){
					break;
				}
			}
			
			n = hold.size() - 1;
		
		
		length = new int[n];
		for(int i = 1; i < hold.size(); i++){
			length[i-1] = hold.get(i);
		}
		
		L = hold.get(0) * 100;
		
		lastN = lastN + hold.size() + 1;
		
		currX = new int[n];
		for(int i = 0; i < n; i++){
			currX[i] = -1;
		}
		
		bestK = -1;
		
		bestX = new int[n];
		for(int i = 0; i < n; i++){
			bestX[i] = -1;
		}
		
		visited = new HashMap<Integer, Integer>(50); 
	}

	/**
	*	BacktrackSolve recursive method given in assignment doccument
	**/ 
    void BacktrackSolve(int currK, int currS){

      if(currK > bestK){
			bestK = currK;
			for (int i=0;i<currX.length;i++){
					bestX[i] = currX[i];
				}
		  }
		  
		if(currK < bestX.length){
			
			int rightsideL = 0;
				for(int i = 0; i < currX.length; i++){
					if(currX[i] == 0){
						rightsideL = length[i] + rightsideL;
					}
				}
			
			if((L - rightsideL) - length[currK] >= 0 && (visited.containsKey((currK+1)*(currS)) == false)){
						currX[currK] = 0;
						BacktrackSolve(currK+1, currS);
						visited.put((currK+1)*(currS), 1);
				}
		
				if(currS-length[currK] >= 0 && (visited.containsKey((currK+1)*(currS-length[currK])) == false)){
						currX[currK] = 1; 
						int newS = currS-length[currK];
						BacktrackSolve(currK+1, newS);
						visited.put((currK+1)*(currS-length[currK]), 1);
				}
		}

	}
	
}
	
