public class Maze {
    private int[][] mazeMap = {
            {1,1,1,1,1},
            {1,2,0,1,1},
            {1,1,0,1,1},
            {1,1,0,0,0},
            {1,1,1,1,3}
    };

    public void initMaze(){
        System.out.println("Init maze");
        printMaze();
        dfs(1,1); // init point
        System.out.println("Solved");
        printMaze();

    }

    private void dfs(int i, int j){
        //System.out.println(mazeMap[i][j] == 0);
        if(mazeMap[i][j] == 1) return;
        if(mazeMap[i][j] == 3) return;

        if(mazeMap[i][j] != 2){
            if(mazeMap[i][j] == 0){
                mazeMap[i][j] = 5;
            }
            else mazeMap[i][j] = -1;
        }

        if(i > 1 && mazeMap[i-1][j] != -1 && mazeMap[i-1][j] != 5) dfs(i-1, j);
        if(j > 1 && mazeMap[i][j-1] != -1 && mazeMap[i][j-1] != 5) dfs(i, j-1);
        if(i < mazeMap.length -1 && mazeMap[i+1][j] != -1 && mazeMap[i+1][j] != 5) dfs(i+1, j);
        if(j < mazeMap[0].length -1 && mazeMap[i][j+1] != -1 && mazeMap[i][j+1] != 5) dfs(i, j+1);

    }

    private void printMaze(){
        for(int i =0; i < mazeMap.length; i++){
            for(int j = 0; j < mazeMap[0].length; j++){
                System.out.print(mazeMap[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
